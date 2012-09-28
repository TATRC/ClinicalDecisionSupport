/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.socraticgrid.ecs.hibernate.HibernateUtil;
import org.socraticgrid.ecs.hibernate.dao.EcsSections;

import org.socraticgrid.wexcontent.artifacts.Section;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.socraticgrid.wexcontent.util.SectionTextCleaner;


/**
 * REST Web Service
 *
 * @author Jerry Goodnough
 */

public class SectionResource
{

    private long id;

    private final String SectionQuery = "from EcsSections s where s.id = :id";

    /** Creates a new instance of SectionResource */
    private SectionResource(String id)
    {

        try
        {
            this.id = Long.parseLong(id);
        }
        catch (NumberFormatException exp)
        {
            this.id = 0;
        }
    }

    /** Get instance of the SectionResource */
    public static SectionResource getInstance(String id)
    {

        // The user may use some kind of persistence mechanism
        // to store and restore instances of SectionResource class.
        return new SectionResource(id);
    }

    /**
     * Retrieves representation of an instance of org.socraticgrid.wexcontent.service.SectionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json,application/xml")
    public Section getContent(@QueryParam("readinglevel") int readinglevel, @HeaderParam("Accept-Language")String language)
    {

        Section out = new Section();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {
            Query qry = session.createQuery(SectionQuery).setLong("id", id);
            List matches = qry.list();

            if (!matches.isEmpty())
            {
                EcsSections sect = (EcsSections) matches.get(0);
                out.name = sect.getName();
                SectionTextCleaner stc = new SectionTextCleaner();
                out.body = stc.cleanupSectionText(sect.getXml());
            }
        }
        finally
        {

            tx.commit();
        }


        return out;

    }

    @GET
    @Produces("text/html")
    public String getHtml(
            @QueryParam("readinglevel") int readinglevel, @HeaderParam("Accept-Language")String language)
    {
                String out = "";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {
            Query qry = session.createQuery(SectionQuery).setLong("id", id);
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

}
