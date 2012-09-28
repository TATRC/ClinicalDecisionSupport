/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcleanup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.socraticgrid.wexcleanup.freebase.dao.Articles;
import org.socraticgrid.wexcleanup.freebase.dao.EcsCandidateGuid;
import org.socraticgrid.wexcleanup.freebase.dao.EcsCandidateWpids;
import org.socraticgrid.wexcleanup.freebase.dao.EcsCodesToWpids;
import org.socraticgrid.wexcleanup.freebase.dao.FreebaseWpid;
import org.socraticgrid.wexcleanup.freebase.dao.HibernateUtil;

/**
 *
 * @author Jerry Goodnough
 */
public class CandidateLoader {
    private static boolean debug = false;

    private static String  wikiPrefix = "http://en.wikipedia.org/wiki/";

    public void removeSource(RunStats rs,String sourceName)
    {
        Session session=null;
        Transaction tx=null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery("from EcsCandidateGuid a where a.source = :name").setString("name", sourceName);
            List matches = qry.list();
            int fnd = 0;
            ListIterator li = matches.listIterator();

            while(li.hasNext())
            {
                fnd++;
                EcsCandidateGuid cand = (EcsCandidateGuid) li.next();
                int id = cand.getId();

                Query qry1 = session.createQuery("delete EcsCandidateWpids s where s.id = :id").setInteger("id",id);
                qry1.executeUpdate();

                qry1 = session.createQuery("delete EcsCodesToWpids s where s.id = :id").setInteger("id",id);
                qry1.executeUpdate();

                //When done we will delele it
                session.delete(cand);
            }
        }
        catch(Exception exp)
        {
            System.out.println("Exception during copy:"+exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            if (tx != null)
            {
                tx.commit();
            }
            if (session != null)
            {
                session.close();
                HibernateUtil.shutdown();
            }
        }

    }

    public void loadCandidateWPIDS(RunStats rs,String fileName)
    {
        File f = new File(fileName);
        try
        {
            FileReader fr = new FileReader(f);
            LineNumberReader lnr = new LineNumberReader(fr);

            String line = lnr.readLine();
            //Skip the first line
            while (line != null)
            {
                line =  lnr.readLine();
                //The first line should have been skipped - Since it sould be header data
                if (line != null)
                {
                    searchForArticle(line,fileName,rs);
                }
            }

        }
        catch(FileNotFoundException exp)
        {
            System.out.println("FileNotFoundException during load:"+exp.getMessage());
            exp.printStackTrace();
            
        }
        catch(IOException exp)
        {
            System.out.println("Exception during load:"+exp.getMessage());
            exp.printStackTrace();

        }

    }

    public void searchForArticle(String line,String fileName, RunStats rs)
    {
        String[] sections = line.split(",");

        String type = sections[0];
        //Strip out spaces on codes
        String code = sections[1].replaceAll(" ", "");
        String url = sections[2];

        //Fix up the URL entry
        url = fixupURL(url);
        rs.addStat("loadCandidateWPIDS", "Searchs", 1);

        System.out.println("Searching for "+type+", "+code);
        int wpid = findWpid(url);

        if (wpid!=0)
        {
            rs.addStat("loadCandidateWPIDS", "ArticleWpidFound", 1);
            String guid = findGuid(wpid);

            saveMapping(type, code, wpid, guid, url, fileName);
        }
        else
        {
            System.out.println("Missing "+code+"("+type+") - "+sections[2]);
            // Update Stats
            rs.addStat("loadCandidateWPIDS", "MissingArticle", 1);
        }

    }

    public String fixupURL(String url)
    {
        String out = url;
        if (url.startsWith(wikiPrefix))
        {
            out = url.substring(wikiPrefix.length());
        }

        out = out.replaceAll("_", " ");

        return out;
    }

    public int findWpid(String url)
    {
        int wpid =0;
        //Search for the wpid in Article based on the name
        Session session=null;
        Transaction tx=null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery("from Articles a where a.name = :name").setString("name", url);
            List matches = qry.list();
            int fnd = 0;
            ListIterator li = matches.listIterator();

            while(li.hasNext())
            {
                fnd++;
                Articles art = (Articles) li.next();
                if (fnd == 1)
                {
                    wpid = art.getWpid();
                }
                else
                {
                    //Weird case
                    System.out.print("Mutliple Articles found for ");
                    System.out.println(url);
                }
            }
        }
        catch(Exception exp)
        {
            System.out.println("Exception during copy:"+exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            if (tx != null)
            {
                tx.commit();
            }
            if (session != null)
            {
                session.close();
                HibernateUtil.shutdown();
            }
        }
        return wpid;
    }

    public String findGuid(int wpid)
    {
        String guid = "";
        //Search for the wpid in Article based on the name
        Session session=null;
        Transaction tx=null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery("from FreebaseWpid a where a.wpid = :wpid").setInteger("wpid", wpid);
            List matches = qry.list();
            int fnd = 0;
            ListIterator li = matches.listIterator();

            while(li.hasNext())
            {
                fnd++;
                FreebaseWpid mtch = (FreebaseWpid) li.next();
                if (fnd == 1)
                {
                    guid  = mtch.getGuid();
                }
                else
                {
                    //Weird case
                    System.out.print("Unable to find guid for wpid of ");
                    System.out.println(wpid);
                }
            }
        }
        catch(Exception exp)
        {
            System.out.println("Exception during copy:"+exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            if (tx != null)
            {
                tx.commit();
            }
            if (session != null)
            {
                session.close();
                HibernateUtil.shutdown();
            }
        }

        return guid;
    }

    public void saveMapping(String type, String code, int wpid, String guid, String url, String fileName)
    {
        Session session=null;
        Transaction tx=null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();


            EcsCandidateGuid cg = new EcsCandidateGuid();
            cg.setGuid(guid);
            cg.setName(url);
            cg.setScheme(type);
            cg.setSource(fileName);
                
            Integer id = (Integer) session.save(cg);

            EcsCandidateWpids candidate = new EcsCandidateWpids();
            candidate.setGuid(guid);
            candidate.setScheme(type);
            candidate.setId(id.intValue());
            candidate.setWpid(wpid);

            session.save(candidate);

            //We need the ID
            EcsCodesToWpids mapping = new EcsCodesToWpids();
            mapping.setCode(code);
            mapping.setDisable(Boolean.FALSE);
            mapping.setScheme(type);
            mapping.setWpid(wpid);
            mapping.setId(id.intValue());
            session.save(mapping);
        }
        catch(Exception exp)
        {
            System.out.println("Exception during copy:"+exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            if (tx != null)
            {
                try
                {
                    tx.commit();
                }
                catch(ConstraintViolationException exp)
                {
                    System.out.println("Exception during copy:"+exp.getMessage());
                    exp.printStackTrace();
                    System.out.println("Cause = "+exp.getCause().getMessage());
                }
            }
            if (session != null)
            {
                session.close();
                HibernateUtil.shutdown();
            }
        }

    }
}
