
package gov.hhs.fha.nhinc.kmr.properties;

import java.util.HashMap;

/**
 * Provide access date information
 *
 * @author Jerry Goodnough
 */
public class DatePropertyAccessor {


    private static HashMap<String,DateProperty> cache = new HashMap<String,DateProperty>();

    public static DateProperty getDateProperty(String fileName,String subject,String domain, String qualifer)
            throws PropertyAccessException
    {
        DateProperty out=null;
        DateParse dtParse;
        String key;
        String fullkey;
        //Process the default tree...
        key = subject+"-"+domain+"-"+qualifer;
        fullkey = fileName+"+"+key;
        //First check to see if it is already fetched
        if (!PropertyAccessor.isRefreshNeeded(fileName) && (cache.containsKey(fullkey)))
        {
            return cache.get(fullkey);
        }

        String val;
        val = PropertyAccessor.getProperty(fileName, key);
        dtParse = DateParse.parseDateString(val);
        if (dtParse.getType()==DateParse.Type.EMPTY)
        {
            key = subject+"-Default-"+qualifer;
            val = PropertyAccessor.getProperty(fileName, key);
            dtParse = DateParse.parseDateString(val);
            if (dtParse.getType()==DateParse.Type.EMPTY)
            {
                key = "Default-"+qualifer;
                val = PropertyAccessor.getProperty(fileName, key);
                dtParse = DateParse.parseDateString(val);
                if (dtParse.getType()==DateParse.Type.EMPTY)
                {
                    // Same as unbounded
                    dtParse = DateParse.parseDateString("0");
                }
            }
        }
        out = DateProperty.getDatePropertyFromParse(dtParse);
        //Save the value to cache
        cache.put(fullkey, out);
        return out;
    }
}
