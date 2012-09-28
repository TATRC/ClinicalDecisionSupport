/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.ecs.dbutil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.socraticgrid.ecs.hibernate.HibernateUtilJDBC;
import org.socraticgrid.ecs.hibernate.dao.EcsSchemeAlias;

import java.util.List;
import java.util.ListIterator;


/**
 * @author  Jerry Goodnough
 */
public class FixupIds
{

    /**
     * DOCUMENT ME!
     *
     * @param  args  DOCUMENT ME!
     */
    public static void main(String[] args)
    {
        FixupIds app = new FixupIds();
        app.run(args);
    }

    /**
     * Fixes the Alias table to have the canonical name as the actual scheme.
     * Make sure the old canonical name is added to the table with the correct
     * scheme.
     */
    public void AddCanonicalAlias()
    {
        Session session = null;
        Transaction tx = null;

        String SanityCheckQueryString =
            "select a from EcsSchemeAlias a where a.alias = 'ICD9'";


        String SchemeQueryString =
            "select a from EcsSchemeAlias a where a.canonical = true";
        String SchemeUpdateString =
            "update EcsSchemeAlias a set a.scheme = :newScheme where scheme = :oldScheme";

        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();

            Query sqry = session.createQuery(SanityCheckQueryString);
            List sanList = sqry.list();

            if (!sanList.isEmpty())
            {
                EcsSchemeAlias id = (EcsSchemeAlias) sanList.get(0);

                if (!(id.getScheme().equalsIgnoreCase("icd9")))
                {
                    System.out.println(
                        "It apprears that the Canonical Alias fix is already in place");

                    return;
                }
            }

            tx = session.beginTransaction();

            Query qry = session.createQuery(SchemeQueryString);

            List matches = qry.list();
            ListIterator li = matches.listIterator();


            while (li.hasNext())
            {
                EcsSchemeAlias id = (EcsSchemeAlias) li.next();
                System.out.print(id.getAlias() + ", " + id.getScheme());

                //Create the secondary code
                EcsSchemeAlias reverse = new EcsSchemeAlias();
                reverse.setAlias(id.getScheme());
                reverse.setCanonical(false);
                reverse.setScheme(id.getAlias());

                session.save(reverse);


                Query qry1 = session.createQuery(SchemeUpdateString);
                qry1.setString("newScheme", id.getAlias());
                qry1.setString("oldScheme", id.getScheme());

                int updates = qry1.executeUpdate();
                System.out.print(", Updates = ");
                System.out.println(updates);
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
                session.flush();

                session.close();
            }

            HibernateUtilJDBC.shutdown();
        }
    }

    /**
     * DOCUMENT ME!
     */
    public void FixupCodeMappingTable()
    {
        Session session = null;
        Transaction tx = null;

        String SchemeQuery =
            "select distinct a.scheme from EcsCodesToWpids a order by a.scheme asc";


        String SchemeUpdateString =
            "update EcsCodesToWpids a set a.scheme = :newScheme where scheme = :oldScheme";

        try
        {
            session = HibernateUtilJDBC.getSessionFactory().openSession();

            tx = session.beginTransaction();

            Query qry = session.createQuery(SchemeQuery);

            List matches = qry.list();
            ListIterator li = matches.listIterator();


            while (li.hasNext())
            {
                String id = (String) li.next();
                System.out.print("Chekcing " + id);

                EcsSchemeAlias alias = this.getAliasInfo(id, session);

                if (alias.getCanonical().booleanValue() == false)
                {
                    Query qry1 = session.createQuery(SchemeUpdateString);
                    qry1.setString("newScheme", alias.getScheme());
                    qry1.setString("oldScheme", id);

                    int updates = qry1.executeUpdate();
                    System.out.print(" Changing to ");
                    System.out.print(alias.getScheme());
                    System.out.print(", Updates = ");
                    System.out.println(updates);
                }
                else
                {
                    System.out.println(
                        " is already canonical,no updates required");
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
                session.flush();

                session.close();
            }

            HibernateUtilJDBC.shutdown();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param   name     DOCUMENT ME!
     * @param   session  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public EcsSchemeAlias getAliasInfo(String name, Session session)
    {
        EcsSchemeAlias out = new EcsSchemeAlias();
        out.setCanonical(Boolean.TRUE);
        out.setAlias(name);
        out.setScheme(name);
        out.setId(-1);

        String SchemeQueryString =
            "select a from EcsSchemeAlias a where a.alias = :find";


        Query qry = session.createQuery(SchemeQueryString);
        qry.setString("find", name);

        List matches = qry.list();

        //Grab the first alias
        if (!matches.isEmpty())
        {
            out = (EcsSchemeAlias) matches.get(0);
        }

        return out;

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
                this.AddCanonicalAlias();
                this.FixupCodeMappingTable();
            }
            else if (arg.equalsIgnoreCase("fixaliastable"))
            {
                this.AddCanonicalAlias();
            }
            else if (arg.equalsIgnoreCase("fixcodemappingtable"))
            {
                this.FixupCodeMappingTable();
            }
            else
            {
                System.out.print("You entered: ");
                System.out.println(arg);
                System.out.println("Usage Help:  ");
                System.out.println("   Valid Arguments are");
                System.out.println("        all - Run full process");
                System.out.println(
                    "        fixaliastable - Fixes the Alias table to have the right canonical value");
                System.out.println(
                    "        fixcodemappingtable - Fixes the Code Mapping table to use canonical ids");
            }
        }


    }
}
