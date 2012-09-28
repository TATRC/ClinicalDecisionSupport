/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import org.hibernate.Query;
import org.hibernate.Transaction;

import org.hibernate.classic.Session;

import org.socraticgrid.ecs.hibernate.HibernateUtil;
import org.socraticgrid.ecs.hibernate.dao.EcsCodesToWpids;

import org.socraticgrid.wexcontent.artifacts.OldCodeReference;
import org.socraticgrid.wexcontent.artifacts.OldCodesInScheme;
import org.socraticgrid.wexcontent.artifacts.OldSearchResult;
import org.socraticgrid.wexcontent.artifacts.Section;
import org.socraticgrid.wexcontent.util.LanguageHelper;
import org.socraticgrid.wexcontent.util.SchemeAliasHandler;
import org.socraticgrid.wexcontent.util.OldSearchHelper;

import java.net.URI;

import java.util.List;
import java.util.ListIterator;
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
import org.socraticgrid.wexcontent.artifacts.AvailableCodeSchemes;


/**
 * REST Web Service
 *
 * @author  Jerry Goodnough
 */

@Path("sectioninfo")
public class SectionInfo
{

    /** DOCUMENT ME! */
    @Context private UriInfo context;

    /**
     * Creates a new instance of CodeinfoResource
     */
    public SectionInfo()
    {
    }

    /**
     * DOCUMENT ME!
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
    public OldCodesInScheme getCodeInScheme(@PathParam("scheme") String scheme,
        @QueryParam("matching") String code,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language)
    {
        OldCodesInScheme out = new OldCodesInScheme();
        language = LanguageHelper.normalizeToLanguage639dot2(language);

        String CodeQueryString =
            "from EcsCodesToWpids a where a.scheme = :scheme and a.code like :code and a.disable = false order by a.code asc";

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
                EcsCodesToWpids id = (EcsCodesToWpids) li.next();

                OldCodeReference cr = new OldCodeReference();
                UriBuilder cb = ub.clone();
                cb.path("/codeinfo/{scheme}/{id}");

                //Todo - Figure out how to get this to work
                //cb.path(Icd9CodesResource.class,"getIcd9CodeResource");
                URI uri = cb.build(actualScheme, id.getCode());
                cr.scheme = id.getScheme();
                cr.code = id.getCode();
                cr.link = uri.toString();
                cr.wpid = id.getWpid();
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
     * DOCUMENT ME!
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
    public OldSearchResult getCodeInScheme(@PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @QueryParam("exact") boolean exact,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language)
    {

        language = LanguageHelper.normalizeToLanguage639dot2(language);

        String actualScheme = SchemeAliasHandler.getSchemeAliasHandler()
            .resolveAlias(scheme);

        return OldSearchHelper.findMatchingArticles(actualScheme, id, exact,

                context, readinglevel, language);
    }

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

        OldSearchResult search = OldSearchHelper.findMatchingArticles(actualScheme, id, exact,
                context, readinglevel, language);
        Section result = new Section();

        result.metadata.source="WEX";
        UriBuilder ub = context.getBaseUriBuilder(); //UriBuilder.fromResource(Icd9CodesResource.class);
        OldCodeReference cr = new OldCodeReference();
        UriBuilder cb = ub.clone();
        cb.path("/codeinfo/{scheme}/{id}");
        URI uri = cb.build(actualScheme, id);
        result.metadata.parent= uri.toString();
        if (search.matchingArticles.isEmpty())
        {
            result.callStatus.successStatus=true;
            result.callStatus.statusCode=1;
            result.callStatus.statusMessage="No Matches Found";
            result.body="<p>No information is available</p>";

        }
        else if (search.matchingArticles.size()>1)
        {
            result.callStatus.successStatus=false;
            result.callStatus.statusCode=2;
            result.callStatus.statusMessage="Warning Multiple Matches Found";
            result.body = search.matchingArticles.get(0).text;
            result.name = search.matchingArticles.get(0).name;
        }
        else
        {
            result.callStatus.successStatus=false;
            result.callStatus.statusCode=0;
            result.body = search.matchingArticles.get(0).text;
            result.name = search.matchingArticles.get(0).name;

        }

        return result;
    }

    @GET
    @Path("{scheme}/{id}/{sectionId}")
    @Produces("text/html")
    public String getSectionInCodeInSchemeInHtml(@PathParam("scheme") String scheme,
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

        OldSearchResult search = OldSearchHelper.findMatchingArticles(actualScheme, id, exact,
                context, readinglevel, language);
        String result ="";

        if (search.matchingArticles.isEmpty())
        {
            result="<p>No information is available</p>";
        }
        else if (search.matchingArticles.size()>1)
        {
            result = search.matchingArticles.get(0).text;
        }
        else
        {
            result = search.matchingArticles.get(0).text;

        }

        return result;
    }

    @PUT
    @Path("{scheme}/{id}/{sectionId}")
    @Consumes("application/xml,application/json")
    public void saveSectionAsXML(@PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @PathParam("sectionId") String sectionId,
        @QueryParam("exact") boolean exact,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language,
        Section inputSection )
    {
        //
    }

    @PUT
    @Path("{scheme}/{id}")
    @Consumes("application/xml,application/json")
    public void saveCodeAsXML(@PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @PathParam("sectionId") String sectionId,
        @QueryParam("exact") boolean exact,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language,
        Section inputSection )
    {
        //
    }

    @PUT
    @Path("{scheme}")
    @Consumes("application/xml,application/json")
    public void saveScheme(@PathParam("scheme") String scheme,
        @PathParam("id") String id,
        @PathParam("sectionId") String sectionId,
        @QueryParam("exact") boolean exact,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language,
        Section inputSection )
    {
        //Create or update and existing scheme

    }
    
    /**
     * Retrieves representation of an instance of
     * org.socraticgrid.wexcontent.service.CodeinfoResource
     *
     * @return  an instance of java.lang.String
     */
    @GET
    @Produces("application/json,application/xml")
    public AvailableCodeSchemes getCodeSystems(
        @QueryParam("matching") String code,
        @QueryParam("readinglevel") int readinglevel,
        @QueryParam("aliasurlid") String aliasUrlId,
        @HeaderParam("Accept-Language") String language
        )
    {
        String SchemeQueryString =
            "select distinct a.scheme from EcsCodesToWpids a order by a.scheme asc";

        AvailableCodeSchemes out = new AvailableCodeSchemes();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {
            Query qry = session.createQuery(SchemeQueryString);

            List matches = qry.list();
            ListIterator li = matches.listIterator();

            UriBuilder ub = context.getBaseUriBuilder(); //UriBuilder.fromResource(Icd9CodesResource.class);

            while (li.hasNext())
            {
                String id = (String) li.next();


                UriBuilder cb = ub.clone();
                cb.path("/codeinfo/{id}");

                URI uri = cb.build(id);
                out.schemes.add(uri.toString());

            }
        }
        finally
        {
            tx.commit();
        }

        return out;
    }

}
