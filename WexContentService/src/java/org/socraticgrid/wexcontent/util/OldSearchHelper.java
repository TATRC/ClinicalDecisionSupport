/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.socraticgrid.ecs.hibernate.HibernateUtil;
import org.socraticgrid.ecs.hibernate.dao.EcsArticles;
import org.socraticgrid.ecs.hibernate.dao.EcsCodesToWpids;
import org.socraticgrid.ecs.hibernate.dao.EcsSectionAlias;
import org.socraticgrid.ecs.hibernate.dao.EcsSections;

import org.socraticgrid.wexcontent.artifacts.*;

import java.net.URI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javax.naming.directory.SearchResult;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.socraticgrid.ecs.hibernate.dao.EcsSectionData;


/**
 * @author  Jerry Goodnough
 */
public class OldSearchHelper
{

    /** DOCUMENT ME! */
    private final static String ArticleQuery =
        "from EcsArticles a where a.wpid = :wpid";

    /** DOCUMENT ME! */
    private final static String CodeQuery =
        "from EcsCodesToWpids a where a.scheme = :scheme and a.code like :code and a.disable = false";

    /** DOCUMENT ME! */
    private final static String CodeInSchemeQuery =
        "select distinct a.code from EcsSectionData a where a.scheme = :scheme and a.code like :code";

    private final static String CodeInfoQuery =
        "select a.code from EcsSectionData a where a.scheme = :scheme and a.code = :code";

    /** DOCUMENT ME! */
    private final static String SectionQuery =
        "from EcsSections s where s.articleWpid = :wpid and s.disable = false";

    /** DOCUMENT ME! */
    private final static String SectionAliasQuery =
        "from EcsSectionAlias sa where sa.scheme = :scheme";

    /** DOCUMENT ME! */
    private final static String SectionLookupQuery =
        "from EcsSectionData s where s.scheme = :scheme and s.code = :code and s.section = :section";

    /**
     * DOCUMENT ME!
     *
     * @param   scheme        DOCUMENT ME!
     * @param   matchId       DOCUMENT ME!
     * @param   exact         DOCUMENT ME!
     * @param   context       DOCUMENT ME!
     * @param   readinglevel  DOCUMENT ME!
     * @param   language      DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static OldSearchResult findMatchingArticles(String scheme,
        String matchId, boolean exact, UriInfo context, int readinglevel,
        String language)
    {
        OldSearchResult out = new OldSearchResult();

        Session session = HibernateUtil.getSessionFactory().openSession();

        //Currently only ICD9 supports a matching scheme
        if (scheme.compareTo("icd9") == 0)
        {

            //If it is not a wild card request
            if (!matchId.contains("%") && !exact)
            {
                matchId = getBestMatch(scheme, matchId, session);
            }
        }

        Transaction tx = session.beginTransaction();

        try
        {
            out.searchCodeScheme = scheme;
            out.searchCode = matchId;

            Query qry = session.createQuery(CodeQuery);
            qry.setString("scheme", scheme);
            qry.setString("code", matchId);

            List matches = qry.list();

            if (!matches.isEmpty())
            {
                ListIterator li = matches.listIterator();

                while (li.hasNext())
                {
                    EcsCodesToWpids aid = (EcsCodesToWpids) li.next();

                    //boolean enabled = id.getDisable()!= Boolean.TRUE;
                    HashMap<String, String> sectionAlias = getAliasMapForScheme(
                            scheme);

                    if (aid.getWpid() > 0)
                    {

                        //Ok we have the wpid now grab the actual article here
                        Query qry1 = session.createQuery(ArticleQuery)
                            .setInteger("wpid", aid.getWpid());
                        qry1.setMaxResults(1);

                        EcsArticles art = (EcsArticles) qry1.uniqueResult();

                        if (art != null)
                        {
                            OldArticle article = new OldArticle();

                            article.codeScheme = scheme;
                            article.code = aid.getCode();
                            article.name = art.getName();
                            article.text = art.getText();
                            article.images = FreebaseImageSearch
                                .findImagesForId(aid.getWpid());

                            //Todo All Link to sections
                            article.sections = getArticleSections(aid.getWpid(),
                                    session, context, sectionAlias);
                            out.matchingArticles.add(article);

                        }
                    }
                }
            }
            else
            {
                //out.append("<xml></xml>");
            }
        }
        finally
        {

            tx.commit();
        }

        return out;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   scheme     DOCUMENT ME!
     * @param   code       DOCUMENT ME!
     * @param   sectionId  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Section findSection(String scheme, String code,
        String sectionId, String language, int readingLevel)
    {
        Section out = new Section();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Section Ids are defined by the code set 2.16.840.1.113883.3.348.3.1

        try
        {

            List matches = findSectionData(session, scheme, code, sectionId, language, readingLevel);
            if (!matches.isEmpty())
            {
                out.metadata.id=sectionId;
                out.metadata.source="?";
                out.metadata.parent="TDB";
                out.callStatus.successStatus=true;
                out.callStatus.statusCode=matches.size()>0?CommonErrorState.STATUS_MULTIMATCH:CommonErrorState.STATUS_OK;

                EcsSectionData sd = (EcsSectionData) matches.get(0);
                out.body=sd.getXml();
                out.metadata.language=sd.getLanguage();
                out.metadata.readinglevel=sd.getReadinglevel();
            }
            else
            {
                out.body="";
                out.name="";
                out.metadata.id=sectionId;
                out.metadata.source="";
                out.metadata.parent="";
                out.callStatus.statusCode=CommonErrorState.STATUS_NODATAFOUND;
                out.callStatus.successStatus=true;
                out.callStatus.statusMessage="No Data on file";
                out.metadata.language="en";
                out.metadata.readinglevel="0";
            }

        }
        finally
        {
            tx.commit();

        }

        return out;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   session    DOCUMENT ME!
     * @param   scheme     DOCUMENT ME!
     * @param   code       DOCUMENT ME!
     * @param   sectionId  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static List<EcsSectionData> findSectionData(Session session, String scheme,
        String code, String sectionId, String language, int readingLevel)
    {
        Section out = new Section();

        //TODO:  Update for readign level and Language

        Query qry = session.createQuery(SectionLookupQuery);
        qry.setString("scheme", scheme);
        qry.setString("code", code);
        qry.setString("section", sectionId);

        //TODO:  In future subselect for reading level and language.

        return qry.list();


    }

    /**
     * DOCUMENT ME!
     *
     * @param   scheme  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static HashMap<String, String> getAliasMapForScheme(String scheme)
    {
        HashMap<String, String> out = null;

        //TODO Load the mapping Table
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {

            Query qry = session.createQuery(SectionAliasQuery);
            qry.setString("scheme", scheme);

            List matches = qry.list();

            if (!matches.isEmpty())
            {
                ListIterator li = matches.listIterator();

                out = new HashMap<String, String>();

                while (li.hasNext())
                {
                    EcsSectionAlias esa = (EcsSectionAlias) li.next();
                    out.put(esa.getSection(), esa.getAlias());
                }
            }
        }
        finally
        {

            tx.commit();
        }

        //TODO Consider an Object Cache

        return out;
    }


    /**
     * DOCUMENT ME!
     *
     * @param   wpid          DOCUMENT ME!
     * @param   session       DOCUMENT ME!
     * @param   context       DOCUMENT ME!
     * @param   sectionAlias  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static List<SectionRef> getArticleSections(int wpid,
        Session session, UriInfo context, HashMap<String, String> sectionAlias)
    {
        List<SectionRef> out = new LinkedList<SectionRef>();

        //Ok we have the wpid now grab the actual article here
        Query qry = session.createQuery(SectionQuery).setInteger("wpid", wpid);
        List matches = qry.list();

        if (!matches.isEmpty())
        {
            UriBuilder ub = context.getBaseUriBuilder(); //UriBuilder.fromResource(Icd9CodesResource.class);
            ListIterator li = matches.listIterator();

            while (li.hasNext())
            {
                UriBuilder cb = ub.clone();
                cb.path("/sections/{id}");

                EcsSections sec = (EcsSections) li.next();
                SectionRef ref = new SectionRef();
                ref.name = sec.getName();

                if (sectionAlias != null)
                {

                    if (sectionAlias.containsKey(ref.name))
                    {
                        ref.name = sectionAlias.get(ref.name);
                    }
                }

                URI uri = cb.build(sec.getId());
                ref.link = uri.toString();
                out.add(ref);
            }
        }

        return out;

    }

    /**
     * DOCUMENT ME!
     *
     * @param   scheme   DOCUMENT ME!
     * @param   code     DOCUMENT ME!
     * @param   session  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static String getBestMatch(String scheme, String code,
        Session session)
    {

        //Guard againt code with no periods or a ternminal period
        if (!code.contains(".") || (code.charAt(code.length() - 1) == '.'))
        {
            return code;
        }

        //Ok we parse everythihng after the period
        String[] parts = code.split("\\.");

        //We do this first to not waste a query
        if (parts.length < 2)
        {

            // We have done as much wild carding as possible and it not found
            // We will use the root part
            return parts[0];
        }


        if (isMatchFnd(scheme, code, session))
        {
            return code;
        }
        else
        {

            //Ok if the old code ends in zeroswe should start by chopping them off
            if (parts[1].endsWith("0"))
            {

                //We try removing zeros....
                if (parts[1].length() == 1)
                {

                    //Try using the root topic
                    return parts[0];
                }
                else if (parts[1].length() == 0)
                {
                    return code;
                }
                else
                {
                    String newCode = parts[0] + "." +
                        parts[1].substring(0, parts[1].length() - 1);

                    return getBestMatch(scheme, newCode, session);
                }

            }
            else
            {
                //Ok we have a few possible routes
                //The question is how long is part 2
            }

            return code;

        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   scheme   DOCUMENT ME!
     * @param   code     DOCUMENT ME!
     * @param   session  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static boolean isMatchFnd(String scheme, String code,
        Session session)
    {
        Transaction tx = session.beginTransaction();
        Query qry = session.createQuery(CodeQuery);
        qry.setString("scheme", scheme);
        qry.setString("code", code);

        List matches = qry.list();
        boolean ok = !matches.isEmpty();
        tx.commit();

        return ok;
    }
}
