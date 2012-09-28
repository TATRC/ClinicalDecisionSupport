/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.ecs.dbutil;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.exception.DataException;

import org.socraticgrid.ecs.hibernate.HibernateUtilJDBC;
import org.socraticgrid.ecs.hibernate.dao.EcsArticles;
import org.socraticgrid.ecs.hibernate.dao.EcsCodesToWpids;
import org.socraticgrid.ecs.hibernate.dao.EcsSectionData;
import org.socraticgrid.ecs.hibernate.dao.EcsSections;

import org.socraticgrid.wexcontent.util.FreebaseImageSearch;
import org.socraticgrid.wexcontent.util.SectionTextCleaner;

import java.sql.BatchUpdateException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;


/**
 * @author  Jerry Goodnough
 */
public class SeedData
{

    /** DOCUMENT ME! */
    private Map<String, String> section2Code = new HashMap<String, String>();

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(String[] args)
    {
        SeedData app = new SeedData();
        app.loadMap();
        app.run(args);
    }

    /**
     * DOCUMENT ME!
     */
    public void CreateDescriptions()
    {
        Session session = null;
        Transaction tx = null;


        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();
            tx = session.beginTransaction();


            Query qry = session.createQuery("from EcsCodesToWpids");

            qry.setFetchSize(100);

            ScrollableResults cursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;

            while (cursor.next())
            {
                EcsCodesToWpids code = (EcsCodesToWpids) cursor.get(0);
                Query qry1 = session.createQuery(
                        "from EcsArticles a where a.wpid = :wpid");

                qry1.setInteger("wpid", code.getWpid());

                List fnd = qry1.list();

                if (!fnd.isEmpty())
                {

                    EcsArticles a = (EcsArticles) fnd.get(0);

                    EcsSectionData d0 = new EcsSectionData();
                    d0.setSection("0"); //Name

                    d0.setSource("WEX");
                    d0.setLanguage("en");
                    d0.setReadinglevel("0");
                    d0.setScheme(code.getScheme());
                    d0.setCode(code.getCode());
                    d0.setXml(a.getName());
                    session.save(d0);

                    EcsSectionData d1 = new EcsSectionData();
                    d1.setSection("1"); //General Info
                    d1.setSource("WEX");
                    d1.setLanguage("en");
                    d1.setReadinglevel("0");
                    d1.setScheme(code.getScheme());
                    d1.setCode(code.getCode());

                    String txt = null;

                    if (a.getText() != null)
                    {

                        if (!a.getText().isEmpty())
                        {
                            txt = a.getText();
                        }
                    }

                    if ((txt == null) && (fnd.size() > 1))
                    {
                        a = (EcsArticles) fnd.get(1);
                        txt = a.getText();
                    }

                    if (txt == null)
                    {
                        txt = "";
                    }

                    txt = txt.replaceAll("(&)", "&amp;");
                    txt = txt.replaceAll("(<)", "&lt;");
                    txt = txt.replaceAll("(>)", "&gt;");

                    if (txt.isEmpty())
                    {
                        txt = null;
                    }
                    d1.setXml(txt);
                    session.save(d1);

                }

                //Flush the buffer
                if ((++cnt % 100) == 0)
                {

                    try
                    {
                        session.flush();
                        session.clear();

                    }
                    catch (Throwable e)
                    {
                        e.printStackTrace(System.out);

                        if (e instanceof DataException)
                        {
                            DataException de = (DataException) e;
                            Throwable e1 = de.getCause();

                            if (e1 instanceof BatchUpdateException)
                            {
                                BatchUpdateException bue =
                                    (BatchUpdateException) e1;
                                System.out.println(bue.getCause().toString());
                            }
                        }
                    }

                }

            }

            System.out.print("Total entries created ");
            System.out.println(cnt);

        }
        finally
        {

            if (tx != null)
            {
                tx.commit();
            }

            if (session != null)
            {
                session.flush();

                session.close();
            }


        }
    }

    /**
     * DOCUMENT ME!
     */
    public void CreateImageSections()
    {
        Session session = null;
        Transaction tx = null;


        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();
            tx = session.beginTransaction();


            Query qry = session.createQuery("from EcsCodesToWpids");

            qry.setFetchSize(100);

            ScrollableResults cursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;

            while (cursor.next())
            {
                EcsCodesToWpids code = (EcsCodesToWpids) cursor.get(0);
                Query qry1 = session.createQuery(
                        "from EcsArticles a where a.wpid = :wpid");

                qry1.setInteger("wpid", code.getWpid());

                List fnd = qry1.list();

                if (!fnd.isEmpty())
                {

                    List<String> images = FreebaseImageSearch.findImagesForId(
                            code.getWpid());

                    if (!images.isEmpty())
                    {
                        StringBuilder sb = new StringBuilder();
                        ListIterator<String> iitr = images.listIterator();

                        while (iitr.hasNext())
                        {
                            String image = iitr.next();
                            sb.append("<img id='wpid_");
                            sb.append(code.getWpid());
                            sb.append("' src='");
                            sb.append(image);
                            sb.append("' />");
                        }

                        EcsArticles a = (EcsArticles) fnd.get(0);

                        EcsSectionData d20 = new EcsSectionData();
                        d20.setSection("20"); //Images

                        d20.setSource("WEX");
                        d20.setLanguage("en");
                        d20.setReadinglevel("0");
                        d20.setScheme(code.getScheme());
                        d20.setCode(code.getCode());
                        d20.setXml(sb.toString());
                        session.save(d20);
                    }

                }

                //Flush the buffer
                if ((++cnt % 100) == 0)
                {

                    try
                    {
                        session.flush();
                        session.clear();
                    }
                    catch (Throwable e)
                    {
                        e.printStackTrace(System.out);

                        if (e instanceof DataException)
                        {
                            DataException de = (DataException) e;
                            Throwable e1 = de.getCause();

                            if (e1 instanceof BatchUpdateException)
                            {
                                BatchUpdateException bue =
                                    (BatchUpdateException) e1;
                                System.out.println(bue.getCause().toString());
                            }
                        }
                    }
                }

            }

            System.out.print("Total image entries created ");
            System.out.println(cnt);

        }
        finally
        {

            if (tx != null)
            {
                tx.commit();
            }

            if (session != null)
            {
                session.flush();

                session.close();
            }


        }
    }

    /**
     * DOCUMENT ME!
     */
    public void CreateSections()
    {
        Session session = null;
        Transaction tx = null;
        SectionTextCleaner stc = new SectionTextCleaner();

        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery("from EcsCodesToWpids");

            qry.setFetchSize(100);

            ScrollableResults cursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;
            int cdCnt = 0;

            while (cursor.next())
            {

                cdCnt++;

                EcsCodesToWpids code = (EcsCodesToWpids) cursor.get(0);
                Query qry1 = session.createQuery(
                        "from EcsSections s where s.articleWpid = :wpid and s.disable = false");

                qry1.setInteger("wpid", code.getWpid());

                Set set = new HashSet();

                List fnd = qry1.list();
                Iterator itr = fnd.listIterator();

                while (itr.hasNext())
                {
                    EcsSections s = (EcsSections) itr.next();
                    String nm = s.getName();

                    if (section2Code.containsKey(nm))
                    {
                        String section = section2Code.get(nm);

                        if (!set.contains(section))
                        {

                            //tx = session.beginTransaction();
                            EcsSectionData d = new EcsSectionData();

                            d.setSection(section); //Name
                            d.setSource("WEX");
                            d.setLanguage("en");
                            d.setReadinglevel("0");
                            d.setScheme(code.getScheme());
                            d.setCode(code.getCode());

                            String txt = stc.cleanupSectionText(s.getXml());

                            if (txt == null)
                            {
                                System.out.println("Null Section");

                                break;
                            }

                            if (txt.isEmpty())
                            {
                                System.out.println("Empty Section");

                                break;
                            }

                            //Prevent Duplicate sections
                            set.add(section);

                            d.setXml(txt);

                            session.save(d);

                            //tx.commit();
                            //tx=null;
                            //Flush the buffer
                            if ((++cnt % 100) == 0)
                            {

                                //System.out.println(cnt + ", " + d.getScheme() +
                                //    ", " + d.getCode() + ", " + d.getSection());
                                try
                                {
                                    session.flush();
                                    session.clear();
                                }
                                catch (Throwable e)
                                {
                                    e.printStackTrace(System.out);

                                    if (e instanceof DataException)
                                    {
                                        DataException de = (DataException) e;
                                        Throwable e1 = de.getCause();

                                        if (e1 instanceof BatchUpdateException)
                                        {
                                            BatchUpdateException bue =
                                                (BatchUpdateException) e1;
                                            System.out.println(bue.getCause()
                                                .toString());
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Duplicate Section Hit " + nm);
                        }
                    }
                }

            }

            System.out.print("Total sections created ");
            System.out.print(cnt);
            System.out.print(" for ");
            System.out.print(cdCnt);
            System.out.println(" Codes ");


        }
        finally
        {

            if (tx != null)
            {
                tx.commit();
            }

            if (session != null)
            {
                session.flush();

                session.close();
            }


        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public void run(String[] args)
    {

        if (args.length == 0)
        {
            args = new String[1];
            args[0] = "all";
        }

        for (int i = 0; i < args.length; i++)
        {
            String arg = args[i];


            if (arg.equalsIgnoreCase("all"))
            {

                this.WipeoutDescriptions();
                this.CreateDescriptions();
                this.WipeoutSectionData();
                this.CreateSections();


            }
            else if (arg.equalsIgnoreCase("updatedescriptions"))
            {
                this.WipeoutDescriptions();
                this.CreateDescriptions();
            }
            else if (arg.equalsIgnoreCase("updatesections"))
            {
                this.WipeoutSectionData();
                this.CreateSections();
            }
            else if (arg.equalsIgnoreCase("updateimages"))
            {
                this.WipeoutImageSectionData();
                this.CreateImageSections();
            }
            else
            {
                System.out.print("You entered: ");
                System.out.println(arg);
                System.out.println("Usage Help:  ");
                System.out.println("   Valid Arguments are");
                System.out.println("        all - Run full process");
                System.out.println(
                    "        updatedescriptions - Clears and rebuil;ds the descriptions");
                System.out.println(
                    "        updateimages - Clears and rebuilds the Image Links");
                System.out.println(
                    "        updatesections - Clears an rebuild section data");
            }
        }


    }

    /**
     * Fixes the Alias table to have the canonical name as the actual scheme.
     * Make sure the old canonical name is added to the table with the correct
     * scheme.
     */
    public void WipeoutDescriptions()
    {
        Session session = null;
        Transaction tx = null;
        String deleteDescriptions =
            "delete EcsSectionData d where d.source ='WEX' and d.section ='0' or d.section = '1'";

        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery(deleteDescriptions);
            int i = qry.executeUpdate();
            System.out.print("Descriptions deleted = ");
            System.out.println(i);
        }
        finally
        {

            if (tx != null)
            {
                tx.commit();
            }

            if (session != null)
            {
                session.flush();

                session.close();
            }
        }

    }

    /**
     * DOCUMENT ME!
     */
    public void WipeoutImageSectionData()
    {
        Session session = null;
        Transaction tx = null;
        String deleteAllOtherSections =
            "delete EcsSectionData d where d.source ='WEX' and d.section = '20'";

        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery(deleteAllOtherSections);
            int i = qry.executeUpdate();
            System.out.print("Image Sections Sections deleted = ");
            System.out.println(i);
        }
        finally
        {

            if (tx != null)
            {
                tx.commit();
            }

            if (session != null)
            {
                session.flush();

                session.close();
            }
        }

    }

    /**
     * DOCUMENT ME!
     */
    public void WipeoutSectionData()
    {
        Session session = null;
        Transaction tx = null;
        String deleteAllOtherSections =
            "delete EcsSectionData d where d.source ='WEX' and d.section != '1' and d.section != '0'";

        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery(deleteAllOtherSections);
            int i = qry.executeUpdate();
            System.out.print("Other Sections deleted = ");
            System.out.println(i);
        }
        finally
        {

            if (tx != null)
            {
                tx.commit();
            }

            if (session != null)
            {
                session.flush();

                session.close();
            }
        }

    }

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */

    private void loadMap()
    {
        section2Code.put("Summary", "2");
        section2Code.put("Complications", "2");
        section2Code.put("Description", "4");
        section2Code.put("References", "5");
        section2Code.put("Causes", "101");
        section2Code.put("Cause", "101");
        section2Code.put("Signs and symptoms", "104");
        section2Code.put("Presentation", "104");
        section2Code.put("Signs and Symptoms", "104");
        section2Code.put("Signs", "104");
        section2Code.put("Synonyms", "104");
        section2Code.put("Diagnosis", "107");
        section2Code.put("Diagnosis and treatment", "107");
        section2Code.put("Treatment and prevention", "107");
        section2Code.put("Treatments", "108");
        section2Code.put("Treatment and prognosis", "108");
        section2Code.put("Treatment", "108");
    }
}
