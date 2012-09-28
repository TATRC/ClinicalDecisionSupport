package org.socraticgrid.wexcontent.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.socraticgrid.ecs.hibernate.HibernateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import org.socraticgrid.ecs.hibernate.dao.EcsSectionData;


/**
 * Handle the Alias mapping of Schema data. Note: Could be generalized in the
 * future.
 *
 * @author  Jerry Goodnough
 */
public class SectionAliasHelper
{

    /** Singleton for this class */
    static private SectionAliasHelper singleton;

    /** Synchronization Object */
    final static private Object sync = new Object();

    /** Default Check Period = 5 Minutes */
    static private long updateCheckFreq = 1000 * 60 * 5;

    /** Alias Map */
    private HashMap<String, String> aliasMap;
    /** Alias Map */
    private HashMap<String, String> nameMap;

    /** DOCUMENT ME! */
    private final String AliasQuery = "from EcsSectionData s where s.scheme = '2.16.840.1.113883.3.348.3.1' and section ='0'";

    /** Time of Last Update */
    private long lastUpdate = 0;

    /**
     * Fetch the common SchemaAleasHandler
     *
     * @return  The SchemeAliasHandler
     */
    static public SectionAliasHelper getSectionAliasHelper()
    {

        synchronized (sync)
        {

            if (singleton == null)
            {
                SectionAliasHelper instance = new SectionAliasHelper();
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

    public String getDescption(String name)
    {
        updateMappingsIfRequired();

        if (!nameMap.containsKey(name))
        {
            return name;
        }

        return nameMap.get(name);
    }


    /**
     * Get a current map of the existing alias. Note: This strategy is only
     * suitable for a relatively small number of alias mappings
     *
     * @return  Map of current mappings
     */
    private void getNewMappings(HashMap<String, String> map, HashMap<String, String> names)
    {

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
                    EcsSectionData sd = (EcsSectionData) itr.next();
                    String code = sd.getCode();
                    String description = sd.getXml();

                    map.put(description, code);
                    names.put(code, description);
                    map.put(description.toLowerCase(), code);
                    map.put(description.toUpperCase(), code);
                    map.put(code,code);
                }
            }
        }
        finally
        {

            tx.commit();
        }

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
        HashMap<String, String> newAlias = new HashMap<String, String>();
        HashMap<String, String> newNames = new HashMap<String, String>();
        getNewMappings(newAlias, newNames);
        aliasMap = newAlias;
        nameMap = newNames;
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
