/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import org.hibernate.Query;
import org.hibernate.Transaction;

import org.hibernate.classic.Session;

import org.socraticgrid.ecs.hibernate.HibernateUtil;
import org.socraticgrid.ecs.hibernate.dao.EcsSectionData;

import org.socraticgrid.wexcontent.artifacts.AvailableCodeSchemes;
import org.socraticgrid.wexcontent.artifacts.CodeReference;
import org.socraticgrid.wexcontent.artifacts.CodesInScheme;
import org.socraticgrid.wexcontent.artifacts.SchemeList;
import org.socraticgrid.wexcontent.artifacts.SearchResult;
import org.socraticgrid.wexcontent.artifacts.Section;
import org.socraticgrid.wexcontent.util.LanguageHelper;
import org.socraticgrid.wexcontent.util.SchemeAliasHandler;
import org.socraticgrid.wexcontent.util.SearchHelper;
import org.socraticgrid.wexcontent.util.SectionAliasHelper;

import java.net.URI;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.socraticgrid.ecs.hibernate.dao.EcsSchemeAlias;
import org.socraticgrid.wexcontent.artifacts.SchemeInfo;


/**
 * REST Web Service
 *
 * @author  Jerry Goodnough
 */

@Path("/codeinfo")
public class CodeinfoResource
{

    /** DOCUMENT ME! */
    @Context private UriInfo context;

    /**
     * Creates a new instance of CodeinfoResource
     */
    public CodeinfoResource()
    {
    }

    /**
     * DOCUMENT ME!
     *
     * @param   readinglevel  DOCUMENT ME!
     * @param   language      DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @GET
    @Produces("application/json,application/xml")
    public SchemeList getSchemes(
        @QueryParam("readinglevel") int readinglevel,
        @HeaderParam("Accept-Language") String language)
    {
        SchemeList out = new SchemeList();

        Set schemes = new java.util.TreeSet<String>();
        //Get a canoical list of all schemes

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        //First we grab all schemes from the alias table
        String canoicalSchemeList =
            "select distinct a.scheme from EcsSchemeAlias a";

        try
        {


            UriBuilder ub = context.getBaseUriBuilder(); //UriBuilder.fromResource(Icd9CodesResource.class);

            Query qry = session.createQuery(canoicalSchemeList);

            List matches = qry.list();

            Iterator li = matches.iterator();

            while (li.hasNext())
            {
                String scheme = (String) li.next();
                schemes.add(scheme);
            }

            //Now we get all the schemes actually used
            String usedSchemes =
                "select distinct a.scheme from EcsSectionData a";
            qry = session.createQuery(usedSchemes);

            matches = qry.list();

            li = matches.iterator();

            while (li.hasNext())
            {
                String scheme = (String) li.next();
                schemes.add(scheme);
            }

            //Ok now we will loop will loop the schemes and create the Info
            Iterator itr = schemes.iterator();

            while (itr.hasNext())
            {
                String scheme = (String) itr.next();
                SchemeInfo si = new SchemeInfo();
                si.canonicalId = scheme;
                si.name = "";

                UriBuilder cb = ub.clone();
                cb.path("/codeinfo/{id}");

                URI uri = cb.build(scheme);
                si.codes=uri.toString();

                qry = session.createQuery("from EcsSchemeAlias where scheme = :scheme");
                qry.setString("scheme", scheme);

                matches = qry.list();
                li = matches.listIterator();
                while (li.hasNext())
                {
                    EcsSchemeAlias al = (EcsSchemeAlias) li.next();
                    //Check is this is the Canonic Alias
                    if (al.getName().booleanValue())
                    {
                        si.name = al.getAlias();
                    }
                    if (al.getCanonical().booleanValue()==false)
                    {
                        si.aliases.add(al.getAlias());
                    }
                }
                if (si.name.isEmpty())
                {
                    si.name = si.canonicalId;
                }

                out.schemes.add(si);

            }
            //We are done Sort the Result
            out.sort();
        }
        finally
        {
            tx.commit();
        }

        return out;

    }

    /**
     * Return matching codes in scheme
     *
     * @param   scheme        DOCUMENT ME!
     * @param   code          DOCUMENT ME!
     * @param   readinglevel  DOCUMENT ME!
     * @param   language      DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @GET
    @Path("{scheme}")
    @Produces("application/json,application/xml")
    public CodesInScheme getCodeInScheme(@PathParam("scheme") String scheme,
        @QueryParam("matching") String code,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language)
    {
        CodesInScheme out = new CodesInScheme();
        language = LanguageHelper.normalizeToLanguage639dot2(language);

        String CodeQueryString =
            "select distinct a.code from EcsSectionData a where a.scheme = :scheme and a.code like :code order by a.code";

        if (code == null)
        {
            code = "%%";
        }
        else if (code.isEmpty())
        {
            code = "%%";
        }


        String actualScheme = SchemeAliasHandler.getSchemeAliasHandler()
            .resolveAlias(scheme);

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {
            Query qry = session.createQuery(CodeQueryString);
            qry.setString("scheme", actualScheme);
            qry.setString("code", code);

            List matches = qry.list();
            ListIterator li = matches.listIterator();

            UriBuilder ub = context.getBaseUriBuilder(); //UriBuilder.fromResource(Icd9CodesResource.class);

            while (li.hasNext())
            {
                String id = (String) li.next();

                CodeReference cr = new CodeReference();
                UriBuilder cb = ub.clone();
                cb.path("/codeinfo/{scheme}/{id}");

                URI uri = cb.build(actualScheme, id);
                cr.scheme = actualScheme;
                cr.code = id;
                cr.link = uri.toString();
                out.schemes.add(cr);
            }
        }
        finally
        {
            tx.commit();
        }


        return out;
    }

    /**
     * Get Info on a specific code set.
     *
     * @param   scheme        DOCUMENT ME!
     * @param   id            DOCUMENT ME!
     * @param   exact         DOCUMENT ME!
     * @param   readinglevel  DOCUMENT ME!
     * @param   language      DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @GET
    @Path("{scheme}/{id}")
    @Produces("application/json,application/xml")
    public SearchResult getCodeInScheme(@PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @QueryParam("exact") boolean exact,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language)
    {

        language = LanguageHelper.normalizeToLanguage639dot2(language);

        String actualScheme = SchemeAliasHandler.getSchemeAliasHandler()
            .resolveAlias(scheme);

        return SearchHelper.findMatchingCodes(actualScheme, id, exact, context,
                readinglevel, language);
    }



    /**
     * DOCUMENT ME!
     *
     * @param   scheme        DOCUMENT ME!
     * @param   id            DOCUMENT ME!
     * @param   sectionId     DOCUMENT ME!
     * @param   exact         DOCUMENT ME!
     * @param   readinglevel  DOCUMENT ME!
     * @param   aliasUrlId    DOCUMENT ME!
     * @param   language      DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @GET
    @Path("{scheme}/{id}/{sectionId}")
    @Produces("application/json,application/xml")
    public Section getSectionInCodeInScheme(@PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @PathParam("sectionId") String sectionId,
        @QueryParam("exact") boolean exact,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language)
    {

        language = LanguageHelper.normalizeToLanguage639dot2(language);


        String actualScheme = SchemeAliasHandler.getSchemeAliasHandler()
            .resolveAlias(scheme);

        String actualSection = SectionAliasHelper.getSectionAliasHelper()
            .resolveAlias(sectionId);

        Section result = SearchHelper.findSection(actualScheme, id,
                actualSection, language, readinglevel);

        UriBuilder ub = context.getBaseUriBuilder(); //UriBuilder.fromResource(Icd9CodesResource.class);

        UriBuilder cb = ub.clone();
        cb.path("/codeinfo/{scheme}/{id}");

        URI uri = cb.build(actualScheme, id);
        result.metadata.parent = uri.toString();

        return result;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   scheme        DOCUMENT ME!
     * @param   id            DOCUMENT ME!
     * @param   sectionId     DOCUMENT ME!
     * @param   exact         DOCUMENT ME!
     * @param   readinglevel  DOCUMENT ME!
     * @param   aliasUrlId    DOCUMENT ME!
     * @param   language      DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @GET
    @Path("{scheme}/{id}/{sectionId}")
    @Produces("text/html")
    public String getSectionInCodeInSchemeInHtml(
        @PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @PathParam("sectionId") String sectionId,
        @QueryParam("exact") boolean exact,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language)
    {

        language = LanguageHelper.normalizeToLanguage639dot2(language);


        String actualScheme = SchemeAliasHandler.getSchemeAliasHandler()
            .resolveAlias(scheme);

        String actualSection = SectionAliasHelper.getSectionAliasHelper()
            .resolveAlias(sectionId);

        Section result = SearchHelper.findSection(actualScheme, id,
                actualSection, language, readinglevel);


        return result.body;

    }

    /**
     * DOCUMENT ME!
     *
     * @param  scheme        DOCUMENT ME!
     * @param  id            DOCUMENT ME!
     * @param  sectionId     DOCUMENT ME!
     * @param  readinglevel  DOCUMENT ME!
     * @param  language      DOCUMENT ME!
     * @param  inputSection  DOCUMENT ME!
     */
    @PUT
    @Path("{scheme}/{id}/{sectionId}")
    @Consumes("application/json,application/xml")
    public void saveSection(@PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @PathParam("sectionId") String sectionId,
        @QueryParam("readinglevel") int readinglevel,
        @HeaderParam("Accept-Language") String language, Section inputSection)
    {

        //
        language = LanguageHelper.normalizeToLanguage639dot2(language);

        String actualScheme = SchemeAliasHandler.getSchemeAliasHandler()
            .resolveAlias(scheme);

        String actualSection = SectionAliasHelper.getSectionAliasHelper()
            .resolveAlias(sectionId);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try
        {
            List<EcsSectionData> fnd = SearchHelper.findSectionData(session,
                    actualScheme, id, actualSection, language, readinglevel);
            EcsSectionData da = null;

            if (fnd.isEmpty())
            {

                //Not on file create a nerw one
                da = new EcsSectionData();

            }
            else
            {

                //Update the old one
                da = (EcsSectionData) fnd.get(0);
            }

            da.setXml(inputSection.body);

            //No name - it comes for the section codes
            //if (!inputSection.name.isEmpty()) da.setName(inputSection.name);
            if (!inputSection.metadata.language.isEmpty())
            {
                da.setLanguage(language);
            }

            da.setCode(id);
            da.setScheme(actualScheme);
            da.setSection(actualSection);

            if (!inputSection.metadata.readinglevel.isEmpty())
            {
                da.setReadinglevel(inputSection.metadata.readinglevel);
            }
            else if (da.getReadinglevel() == null)
            {
                da.setReadinglevel("0");
            }
            else if (da.getReadinglevel().isEmpty())
            {
                da.setReadinglevel("0");
            }

            if ((inputSection.metadata.source == null) ||
                    inputSection.metadata.source.isEmpty())
            {

                if (da.getSource() == null)
                {
                    da.setSource("Unknown");
                }
            }
            else
            {
                da.setSource(inputSection.metadata.source);
            }

            session.save(da);
        }
        finally
        {
            tx.commit();

        }


    }

}
