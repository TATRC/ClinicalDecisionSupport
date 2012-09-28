/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.socraticgrid.ecs.hibernate.HibernateUtil;
import org.socraticgrid.ecs.hibernate.dao.EcsSectionData;

import org.socraticgrid.wexcontent.artifacts.*;

import java.net.URI;

import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


/**
 * @author  Jerry Goodnough
 */
public class SearchHelper
{


    /** DOCUMENT ME! */
    private final static String CodeInSchemeQuery =
        "select distinct a.code from EcsSectionData a where a.scheme = :scheme and a.code like :code";

    /** DOCUMENT ME! */
    private final static String CodeInfoQuery =
        "from EcsSectionData a where a.scheme = :scheme and a.code = :code";

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
    public static SearchResult findMatchingCodes(String scheme, String matchId,
        boolean exact, UriInfo context, int readinglevel, String language)
    {
        SearchResult out = new SearchResult();

        Session session = HibernateUtil.getSessionFactory().openSession();

        //TODO: Consider a Match Scheme
        //TODO: Update top Support reading levels
        //TODO:
        Transaction tx = session.beginTransaction();

        try
        {
            out.searchCodeScheme = scheme;
            out.searchCode = matchId;

            Query qry = session.createQuery(CodeInSchemeQuery);
            qry.setString("scheme", scheme);
            qry.setString("code", matchId);

            List matches = qry.list();

            if (!matches.isEmpty())
            {
                UriBuilder ub = context.getBaseUriBuilder(); //UriBuilder.fromResource(Icd9CodesResource.class);
                ListIterator li = matches.listIterator();

                while (li.hasNext())
                {
                    String actualCode = (String) li.next();

                    Article article = new Article();
                    article.code = actualCode;
                    article.codeScheme = scheme;
                    article.name = "";


                    //We have a Entry - Excatly what data is a good question
                    //No we have the code let us grab all sections
                    Query qryCode = session.createQuery(CodeInfoQuery);

                    qryCode.setString("scheme", scheme);
                    qryCode.setString("code", actualCode);

                    List sectionMatch = qryCode.list();
                    ListIterator itrSect = sectionMatch.listIterator();

                    while (itrSect.hasNext())
                    {
                        EcsSectionData sd = (EcsSectionData) itrSect.next();

                        String secId = sd.getSection();

                        if (secId.compareToIgnoreCase("0") == 0)
                        {

                            //This is the Name Section
                            article.name = sd.getXml();
                        }
                        else
                        {
                            SectionRef sect = new SectionRef();
                            sect.name = SectionAliasHelper
                                .getSectionAliasHelper().getDescption(secId);

                            UriBuilder cb = ub.clone();
                            cb.path("/codeinfo/{scheme}/{id}/{section}");

                            URI uri = cb.build(scheme, actualCode, secId);

                            sect.link = uri.toString();
                            article.sections.add(sect);
                        }
                    }

                    out.codes.add(article);
                }
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

            List matches = findSectionData(session, scheme, code, sectionId,
                    language, readingLevel);

            if (!matches.isEmpty())
            {
                out.metadata.id = sectionId;
                out.metadata.source = "?";
                out.metadata.parent = "TDB";
                out.callStatus.successStatus = true;
                out.callStatus.statusCode = (matches.size() > 0)
                    ? CommonErrorState.STATUS_MULTIMATCH
                    : CommonErrorState.STATUS_OK;

                EcsSectionData sd = (EcsSectionData) matches.get(0);
                out.body = sd.getXml();
                out.metadata.language = sd.getLanguage();
                out.metadata.readinglevel = sd.getReadinglevel();
            }
            else
            {
                out.body = "";
                out.name = "";
                out.metadata.id = sectionId;
                out.metadata.source = "";
                out.metadata.parent = "";
                out.callStatus.statusCode = CommonErrorState.STATUS_NODATAFOUND;
                out.callStatus.successStatus = true;
                out.callStatus.statusMessage = "No Data on file";
                out.metadata.language = "en";
                out.metadata.readinglevel = "0";
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
    public static List<EcsSectionData> findSectionData(Session session,
        String scheme, String code, String sectionId, String language,
        int readingLevel)
    {

        //TODO:  Update for readign level and Language

        Query qry = session.createQuery(SectionLookupQuery);
        qry.setString("scheme", scheme);
        qry.setString("code", code);
        qry.setString("section", sectionId);

        //TODO:  In future subselect for reading level and language.

        return qry.list();


    }


}
