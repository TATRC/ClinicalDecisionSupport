/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcleanup;

import org.hibernate.*;

import org.socraticgrid.wexcleanup.freebase.dao.*;

/**
 *
 * @author Jerry Goodnough
 */
public class BuildCandidateGUIDS {

    public void copyICDGUIDS(RunStats stats)
    {
        Session session=null;
        Transaction tx=null;
        try
        {
            Query qry;
            int removed=0;
            session = HibernateUtil.getSessionFactory().openSession();
            if (!stats.isStatsOnly())
            {
                tx = session.beginTransaction();
                qry = session.createQuery("delete EcsCandidateGuid c where c.source = 'icd9'");
                removed = qry.executeUpdate();
                tx.commit();
            }

            stats.addStat("CopyICDGuids","Prior enties removed", removed);

            tx = session.beginTransaction();

            qry = session.createQuery("from FreebaseMedIcd9");
            qry.setFetchSize(100);

            ScrollableResults icdCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;

            while (icdCursor.next())
            {
                FreebaseMedIcd9 a = (FreebaseMedIcd9) icdCursor.get(0);
                System.out.println(a.getName());

                if (!stats.isStatsOnly())
                {
                    EcsCandidateGuid candidate = new EcsCandidateGuid();
                    candidate.setName(a.getName());
                    candidate.setSource("icd9");
                    candidate.setScheme("icd9");
                    candidate.setGuid(StripGUIDPrefix(a.getId()));
                    session.save(candidate);
                }

                if ((++cnt % 100) == 0)
                {
                    session.flush();
                    session.clear();
                }
            }
            stats.addStat("CopyICDGuids","Candidates", removed);

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

    public void copyDiseaseGUIDS(RunStats stats)
    {
        Session session=null;
        Transaction tx=null;
        try
        {
            Query qry;
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            if (!stats.isStatsOnly())
            {
                qry = session.createQuery("delete EcsCandidateGuid c where c.source = 'DISEASE'");
                int removed = qry.executeUpdate();
                tx.commit();
            }
            tx = session.beginTransaction();

            qry = session.createQuery("from FreebaseMedDisease");
            qry.setFetchSize(100);

            ScrollableResults icdCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;

            while (icdCursor.next())
            {
                FreebaseMedDisease a = (FreebaseMedDisease) icdCursor.get(0);
                System.out.println(a.getName());

                if (!stats.isStatsOnly())
                {
                    EcsCandidateGuid candidate = new EcsCandidateGuid();
                    candidate.setName(a.getName());
                    candidate.setSource("DISEASE");
                    candidate.setGuid(StripGUIDPrefix(a.getId()));
                    candidate.setScheme("icd9");
                    session.save(candidate);
                }

                if ((++cnt % 100) == 0)
                {
                    session.flush();
                    session.clear();
                }
            }
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
    public static String StripGUIDPrefix(String g)
    {
        if (g.startsWith("/guid/"))
        {
            return g.substring(6);
        }
        else
        {
            return g;
        }
    }
}
