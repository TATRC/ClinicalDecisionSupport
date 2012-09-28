package org.socraticgrid.wexcontent.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.socraticgrid.ecs.hibernate.HibernateUtil;
import org.socraticgrid.ecs.hibernate.dao.EcsSchemeAlias;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;


/**
 * Handle the Alias mapping of Schema data. Note: Could be generalized in the
 * future.
 *
 * @author  Jerry Goodnough
 */
public class SchemeAliasHandler
{

    /** Singleton for this class */
    static private SchemeAliasHandler singleton;

    /** Synchronization Object */
    final static private Object sync = new Object();

    /** Default Check Period = 5 Minutes */
    static private long updateCheckFreq = 1000 * 60 * 5;

    /** Alias Map */
    private HashMap<String, String> aliasMap;

    /** DOCUMENT ME! */
    private final String AliasQuery = "from EcsSchemeAlias s";

    /** Time of Last Update */
    private long lastUpdate = 0;

    /**
     * Fetch the common SchemaAleasHandler
     *
     * @return  The SchemeAliasHandler
     */
    static public SchemeAliasHandler getSchemeAliasHandler()
    {

        synchronized (sync)
        {

            if (singleton == null)
            {
                SchemeAliasHandler instance = new SchemeAliasHandler();
                instance.initialize();
                singleton = instance;
            }
        }

        return singleton;
    }

    /**
     * Determine is a name is an alias. A name that is not aliased may not be a
     * canonical name.
     *
     * @param   name
     *
     * @return  true if the name is and alias for a canonical name.
     */
    public boolean isAlias(String name)
    {
        updateMappingsIfRequired();

        return aliasMap.containsKey(name);
    }

    /**
     *  External call to to force the alias handler to reload cache the next
     *  fetch.
     */
    public void invalidateCache()
    {
           synchronized (sync)
           {
               lastUpdate = 0;
           }
    }
    /**
     * Return the resolution for a requested alias. If not aliased the original
     * name will be returned
     *
     * @param   name  The name to resolve
     *
     * @return  Canonical name
     */
    public String resolveAlias(String name)
    {
        updateMappingsIfRequired();

        if (!aliasMap.containsKey(name))
        {
            return name;
        }

        return aliasMap.get(name);
    }

    /**
     * Get a current map of the existing alias. Note: This strategy is only
     * suitable for a relatively small number of alias mappings
     *
     * @return  Map of current mappings
     */
    private HashMap<String, String> getNewMappings()
    {
        HashMap<String, String> map = new HashMap<String, String>();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        try
        {
            Query qry = session.createQuery(AliasQuery);
            List matches = qry.list();

            if (!matches.isEmpty())
            {
                ListIterator itr = matches.listIterator();

                while (itr.hasNext())
                {
                    EcsSchemeAlias alias = (EcsSchemeAlias) itr.next();
                    map.put(alias.getAlias(), alias.getScheme());
                }
            }
        }
        finally
        {

            tx.commit();
        }


        return map;
    }

    /**
     * Initialize a SchemeAlias Handler
     */
    private void initialize()
    {
        updateAliasMappings();
    }

    /**
     * Check is an update is required
     *
     * @return  true if the cached data requires update.
     */
    private boolean isUpdateRequired()
    {

        if ((System.currentTimeMillis() - lastUpdate) > updateCheckFreq)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Update all Mappings and the update Time
     */
    private void updateAliasMappings()
    {
        aliasMap = getNewMappings();
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * Provides a synchronized check to see if the mappings need to be updated
     * and if required it will update the mappings.
     */
    private void updateMappingsIfRequired()
    {

        synchronized (sync)
        {

            if (isUpdateRequired())
            {
                updateAliasMappings();
            }
        }
    }


}
