/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import org.hibernate.Query;
import org.hibernate.Transaction;

import org.hibernate.classic.Session;

import org.socraticgrid.ecs.hibernate.HibernateUtil;



import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.socraticgrid.ecs.hibernate.dao.EcsSectionData;
import org.socraticgrid.wexcontent.artifacts.SectionMetadata;
import org.socraticgrid.wexcontent.artifacts.StandardSections;
/**
 *
 * @author Jerry Goodnough
 */
@Path("metadata")
public class MetadataResource {
    @GET
    @Path("/standard/sections")
    @Produces("application/json,application/xml")
    public StandardSections getScheme(@QueryParam("readinglevel") int readinglevel, @HeaderParam("Accept-Language")String language)
    {
        System.out.println("Now getting standard section names");
        StandardSections out = new StandardSections();

        //TODO Add reading level and language
        //FindBestLanguage
        //For now force to en

        language = "en";

        String SectionDataQuery =
            "from EcsSectionData a where a.scheme =  '2.16.840.1.113883.3.348.3.1' and a.section ='0' and a.language = :language";


        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {
            Query qry = session.createQuery(SectionDataQuery);
            qry.setString("language", language);

            List matches = qry.list();
            ListIterator li = matches.listIterator();

            while (li.hasNext())
            {
                EcsSectionData id = (EcsSectionData) li.next();
                System.out.println(id.getCode()+" "+id.getXml());
                out.sections.add(new SectionMetadata(id.getXml(),id.getCode()));
            }
        }
        finally
        {
            tx.commit();
        }
        System.out.println("Sorting standard section names");
        out.sort();
        return out;
    }

}
