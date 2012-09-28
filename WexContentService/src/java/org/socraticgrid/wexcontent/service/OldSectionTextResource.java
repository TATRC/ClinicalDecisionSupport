/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.socraticgrid.ecs.hibernate.HibernateUtil;
import org.socraticgrid.ecs.hibernate.dao.EcsSections;
import org.socraticgrid.wexcontent.util.SectionTextCleaner;

/**
 * REST Web Service
 *
 * @author Jerry Goodnough
 */

@Path("/sections/{id}/text")
public class OldSectionTextResource {
    @Context
    private UriInfo context;

    private final String SectionQuery = "from EcsSections s where s.id = :id";

    /** Creates a new instance of OldSectionTextResource */
    public OldSectionTextResource() {
    }

    /**
     * Retrieves representation of an instance of org.socraticgrid.wexcontent.service.OldSectionTextResource
     * @param id resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml(@PathParam("id")
    String id, @QueryParam("readinglevel") int readinglevel, @HeaderParam("Accept-Language")String language) {

        String out = "";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        long lid = Long.parseLong(id);

        try
        {
            Query qry = session.createQuery(SectionQuery).setLong("id", lid);
            List matches = qry.list();

            if (!matches.isEmpty())
            {
                EcsSections sect = (EcsSections) matches.get(0);
                SectionTextCleaner stc = new SectionTextCleaner();
                out = stc.cleanupSectionText(sect.getXml());
            }
        }
        finally
        {

            tx.commit();
        }


        return out;

    }

    @GET
    @Produces("application/xml")
    public String getXML(@PathParam("id")
    String id, @QueryParam("readinglevel") int readinglevel, @HeaderParam("Accept-Language")String language) {

        String out = "";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        long lid = Long.parseLong(id);

        try
        {
            Query qry = session.createQuery(SectionQuery).setLong("id", lid);
            List matches = qry.list();

            if (!matches.isEmpty())
            {
                EcsSections sect = (EcsSections) matches.get(0);
                SectionTextCleaner stc = new SectionTextCleaner();
                out = sect.getXml();
            }
        }
        finally
        {

            tx.commit();
        }


        return out;

    }

}
