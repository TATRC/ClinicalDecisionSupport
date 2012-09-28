/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import org.hibernate.Query;
import org.hibernate.Transaction;

import org.hibernate.classic.Session;

import org.socraticgrid.ecs.hibernate.HibernateUtil;

import org.socraticgrid.wexcontent.util.SchemeAliasHandler;

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
import org.socraticgrid.ecs.hibernate.dao.EcsSchemeAlias;
import org.socraticgrid.wexcontent.artifacts.AvailableCodeSchemes;
import org.socraticgrid.wexcontent.artifacts.SchemeInfo;


/**
 * REST Web Service
 *
 * @author  Jerry Goodnough
 */

@Path("/codesysteminfo")
public class CodeSystemInfo
{

    /** DOCUMENT ME! */
    @Context private UriInfo context;

    /**
     * Creates a new instance of CodeinfoResource
     */
    public CodeSystemInfo()
    {
    }

    /**
     * Fetch the Info on a specific scheme!
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
    public SchemeInfo getScheme(@PathParam("scheme") String scheme)
    {
        SchemeInfo out = new SchemeInfo();

        String CodeQueryString =
            "from EcsSchemeAlias a where a.scheme = :scheme";


        String actualScheme = SchemeAliasHandler.getSchemeAliasHandler()
            .resolveAlias(scheme);

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {
            Query qry = session.createQuery(CodeQueryString);
            qry.setString("scheme", actualScheme);
  
            List matches = qry.list();
            ListIterator li = matches.listIterator();

            System.out.print("Found ");
            System.out.print(matches.size());
            System.out.println(" matching entries");

            while (li.hasNext())
            {
                EcsSchemeAlias id = (EcsSchemeAlias) li.next();
                System.out.println(actualScheme+", "+scheme+", "+id.toString());
                if (id != null)
                {

                    if (id.getCanonical())
                    {
                        out.name=id.getAlias();
                    }
                    else
                    {
                        out.aliases.add(id.getAlias());
                    }
                }
            }
        }
        finally
        {
            tx.commit();
        }


        return out;
    }

    @PUT
    @Path("{scheme}")
    @Consumes("application/xml,application/json")
    public void saveScheme(@PathParam("scheme") String scheme,
        @HeaderParam("Accept-Language") String language,
        SchemeInfo inputScheme )
    {
        //
        //Save a new code system
        System.out.println("Put new scheme called");
    }

    
    /**
     * 
     * Get all schemes
     *
     * @return  an instance of java.lang.String
     */
    @GET
    @Produces("application/json,application/xml")
    public AvailableCodeSchemes getCodeSystems( )
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
                cb.path("/codesysteminfo/{id}");

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
