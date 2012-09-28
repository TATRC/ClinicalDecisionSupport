/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcleanup;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.*;

import org.socraticgrid.wexcleanup.freebase.dao.*;

/**
 *
 * @author Jerry Goodnough
 */
public class ArticleHandler {
    private static boolean debug = false;
    
    public void getCandidateWPIDS(RunStats rs)
    {
        Session session=null;
        Transaction tx=null;
        String wpidQueryString = "from FreebaseWpid w where w.guid = :guid";
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            System.out.println("Creating new Candidate Wipds");

            tx = session.beginTransaction();

            Query qry = session.createQuery("from EcsCandidateGuid c order by c.guid");
            qry.setFetchSize(100);

            ScrollableResults icdCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 1;
            String lastGuid = "";

            while (icdCursor.next())
            {
                EcsCandidateGuid a = (EcsCandidateGuid) icdCursor.get(0);

                if (lastGuid.compareTo(a.getGuid())!=0)
                {
                    //Ok we now have a guid to check for we will now find candidate wpid
                    Query qry2 = session.createQuery(wpidQueryString).setString("guid", a.getGuid());
                    List matches = qry2.list();
                    lastGuid = a.getGuid();

                    ListIterator li = matches.listIterator();
                    System.out.print(cnt);
                    System.out.print(":  "+lastGuid+": ");

                    while (li.hasNext())
                    {
                        FreebaseWpid wpid = (FreebaseWpid)li.next();
                        if (checkIfArticlePresent(session,wpid.getWpid())>0)
                        {
                            EcsCandidateWpids candidate = new EcsCandidateWpids();
                            candidate.setGuid(lastGuid);
                            candidate.setWpid(wpid.getWpid());
                            candidate.setScheme(a.getScheme());
                            session.save(candidate);
                            System.out.print("+");
                        }
                        else
                        {
                            System.out.print(".");
                        }
                    }
                    System.out.print("(");
                    System.out.print(cnt);
                    System.out.println(")");
                }

                if ((++cnt % 100) == 0)
                {
                    session.flush();
                    session.clear();
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

    }

    public long checkIfArticlePresent(Session session, int wpid)
    {
        String artQueryString = "select count(a) from Articles a where a.wpid = :wpid";

        session = HibernateUtil.getSessionFactory().openSession();
        Long count = (Long) session.createQuery(artQueryString).setInteger("wpid", wpid).uniqueResult();

        return count.longValue();
    }

    public long checkIfEcsArticlePresent(Session session, int wpid)
    {
        String artQueryString = "select count(a) from EcsArticles a where a.wpid = :wpid";

        session = HibernateUtil.getSessionFactory().openSession();
        Long count = (Long) session.createQuery(artQueryString).setInteger("wpid", wpid).uniqueResult();

        return count.longValue();
    }

    public void copyCandidateArticle(RunStats rs)
    {
        Session session=null;
        Transaction tx=null;
        int copied = 0;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
                       
            System.out.println("Creating New Candidate Articles Cache");

            tx = session.beginTransaction();

            Query qry = session.createQuery("from EcsCandidateWpids w order by w.wpid");
            qry.setFetchSize(100);

            ScrollableResults icdCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;
            int lastWpid = 0;
            
            while (icdCursor.next())
            {
               EcsCandidateWpids a = (EcsCandidateWpids) icdCursor.get(0);

               if (lastWpid!=a.getWpid())
               {
                    lastWpid = a.getWpid();
                    Query qry2 = session.createQuery("from Articles a where a.wpid = :wpid").setInteger("wpid", a.getWpid());
                    List matches = qry2.list();
                    int fnd = 0;
                    ListIterator li = matches.listIterator();

                    while(li.hasNext())
                    {
                        fnd++;
                        Articles art = (Articles) li.next();

                        if (fnd == 1)
                        {
                            if (!rs.isStatsOnly())
                            {
                                EcsArticles newArt = new EcsArticles();
                                newArt.setName(art.getName());
                                newArt.setText(art.getText());
                                newArt.setWpid(art.getWpid());
                                newArt.setXml(art.getXml());
                                newArt.setUpdated(art.getUpdated());
                                session.save(newArt);
                            }
                            System.out.print("+");
                            copied++;
                        }
                        else
                        {
                            //Weird case
                            System.out.print("Mutliple Articles found for ");
                            System.out.println(lastWpid);
                        }
                    }
                    if (debug && fnd == 0)
                    {
                        System.out.print(a.getWpid());
                        System.out.print(" no articles for Candidate, Count Check = ");
                        System.out.println(checkIfArticlePresent(session,a.getWpid()));
                    }

                    if ((++cnt % 50) == 0)
                    {
                        System.out.print("(");
                        System.out.print(copied);
                        System.out.print(",");
                        System.out.print(cnt);
                        System.out.println(")");
                        session.flush();
                        session.clear();
                    }
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

    }

    public void copyCandidateSections(RunStats rs)
    {
        Session session=null;
        Transaction tx=null;
        int copied = 0;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();

            System.out.println("Creating New Candidate Sections Cache");

            tx = session.beginTransaction();

            Query qry = session.createQuery("from EcsCandidateWpids w order by w.wpid");
            qry.setFetchSize(100);

            ScrollableResults icdCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;
            int lastWpid = 0;

            while (icdCursor.next())
            {
               EcsCandidateWpids a = (EcsCandidateWpids) icdCursor.get(0);

               if (lastWpid!=a.getWpid())
               {
                    lastWpid = a.getWpid();
                    Query qry2 = session.createQuery("from Sections a where a.articleWpid = :wpid").setInteger("wpid", a.getWpid());
                    List matches = qry2.list();
                    int fnd = 0;
                    ListIterator li = matches.listIterator();

                    while(li.hasNext())
                    {
                        fnd++;
                        Sections sec = (Sections) li.next();

                        if (!rs.isStatsOnly())
                        {
                            EcsSections newSec = new EcsSections();
                            newSec.setArticleWpid(lastWpid);
                            System.out.println(lastWpid);
                            newSec.setId(sec.getId());
                            newSec.setName(sec.getName());
                            newSec.setOrdinal(sec.getOrdinal());
                            newSec.setParentId(sec.getParentId());
                            newSec.setXml(sec.getXml());
                            newSec.setDisable(false);
                            session.save(newSec);
                        }
                        System.out.print("+");
                        copied++;
                    }
                    if (debug && fnd == 0)
                    {
                        System.out.print(a.getWpid());
                        System.out.print(" no sections for Candidate, Count Check = ");
                        System.out.println(checkIfArticlePresent(session,a.getWpid()));
                    }

                    if ((++cnt % 50) == 0)
                    {
                        System.out.print("(");
                        System.out.print(copied);
                        System.out.print(",");
                        System.out.print(cnt);
                        System.out.println(")");
                        session.flush();
                        session.clear();
                    }
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

    }

    public void deleteSections(RunStats stats)
    {
        if (!stats.isStatsOnly())
        {
            Session session=null;
            Transaction tx=null;
            try
            {
                System.out.println("Deleting Candidate Articles Cache");
                //Clear the existing data set
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Query qry = session.createQuery("delete EcsSections s");
                int removed = qry.executeUpdate();
                tx.commit();
                System.out.print(removed);
                System.out.println(" entries removed.");
                tx=null;
            }
            catch(Exception exp)
            {
                System.out.println("Exception during Deletion of articles:"+exp.getMessage());
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
    }

    public void deleteArticles(RunStats stats)
    {
        if (!stats.isStatsOnly())
        {
            Session session=null;
            Transaction tx=null;
            try
            {
                System.out.println("Deleting Candidate Articles Cache");
                //Clear the existing data set
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Query qry = session.createQuery("delete EcsArticles a");
                int removed = qry.executeUpdate();
                tx.commit();
                System.out.print(removed);
                System.out.println(" entries removed.");
                tx=null;
            }
            catch(Exception exp)
            {
                System.out.println("Exception during Deletion of articles:"+exp.getMessage());
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
    }

    public void deleteCandidateWpids(RunStats stats)
    {
        if (!stats.isStatsOnly())
        {
            Session session=null;
            Transaction tx=null;
            try
            {
                System.out.println("Deleting Candidate Wipds");
                //Clear the existing data set
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Query qry = session.createQuery("delete EcsCandidateWpids w");
                int removed = qry.executeUpdate();
                tx.commit();
                System.out.print(removed);
                System.out.println(" entries removed.");
                tx=null;
            }
            catch(Exception exp)
            {
                System.out.println("Exception during Deletion of articles:"+exp.getMessage());
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
    }

   public void dumpCandidateWPIDS(RunStats rs)
    {
        Session session=null;
        Transaction tx=null;
        String wpidQueryString = "from FreebaseWpid w where w.guid = :guid";
        int cnt = 0;
        int fnd = 0;
        try
        {

            System.out.println("Creating new Candidate Wipds");

            session = HibernateUtil.getSessionFactory().openSession();

            tx = session.beginTransaction();

            Query qry = session.createQuery("from EcsCandidateGuid c order by c.guid");
            qry.setFetchSize(100);

            ScrollableResults icdCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            String lastGuid = "";

            while (icdCursor.next())
            {
                EcsCandidateGuid a = (EcsCandidateGuid) icdCursor.get(0);

                if (lastGuid.compareTo(a.getGuid())!=0)
                {
                    //Ok we now have a guid to check for we will now find candidate wpid
                    Query qry2 = session.createQuery(wpidQueryString).setString("guid", a.getGuid());
                    List matches = qry2.list();
                    lastGuid = a.getGuid();

                    ListIterator li = matches.listIterator();

                    while (li.hasNext())
                    {
                        FreebaseWpid wpid = (FreebaseWpid)li.next();
                        if (checkIfArticlePresent(session,wpid.getWpid())>0)
                        {
                            System.out.print(lastGuid);
                            System.out.print(",");
                            System.out.print(wpid.getWpid());
                            System.out.print(",");
                            System.out.println(cnt);
                            fnd++;
                        }
                    }
                }

                if ((++cnt % 100) == 0)
                {
                    session.flush();
                    session.clear();
                    System.out.print("Searched: ");
                    System.out.print(cnt);
                    System.out.print(", Found: ");
                    System.out.println(fnd);
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
        System.out.print("Searched: ");
        System.out.println(cnt);
        System.out.print("Found: ");
        System.out.println(fnd);

       }


}
