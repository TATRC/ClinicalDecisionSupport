package gov.hhs.fha.nhinc.kmr.ura;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Factory used to Create UniversalResourceAddressBean. Handles issues like
 * insuring the best class is used to represent the bean type.
 *
 * @see     https://socraticgrid.org/display/docs/Universal+Resource+Addresses
 * @author  Jerry Goodnough
 */
public class UniversalResourceAddressBeanFactory
{

    /** Singlton for shared factory instance. */
    private static UniversalResourceAddressBeanFactory singleton = null;

    /**
     * Get the shared instance of the factory.
     *
     * @return  The shared instance of the UniversalResourceAddressBeanFactory
     */
    public static UniversalResourceAddressBeanFactory getInstance()
    {

        // There is a slight chance that in a mutlithreaded enviroment duplicate
        // factories could be created. At this point there is no impact to this
        // event so no guard log is present
        if (singleton == null)
        {
            singleton = new UniversalResourceAddressBeanFactory();
        }

        return singleton;
    }

    /**
     * Creates a UniversalResourceAddressBean based on a URA.
     *
     * @param   address  A URA to parse and turn into a
     *                   UniversalResourceAddressBean
     *
     * @return  A UniversalResourceAddressBean or null
     *
     * @throws  URISyntaxException  DOCUMENT ME!
     *
     * @see     https://socraticgrid.org/display/docs/Universal+Resource+Addresses
     */
    public UniversalResourceAddressBean createUniversalResourceBean(
        String address) throws URISyntaxException
    {

        UniversalResourceAddressBean out = null;

        if ((address == null) || (address.isEmpty()))
        {
            throw new URISyntaxException("", "Null or Empty Address sent");
        }

        URI u = new URI(address);

        String entity = u.getScheme();
        String scheme = getScheme(u.getHost());
        String path = u.getPath();

        if (!path.isEmpty())
        {
            path = path.substring(1);
        }

        EntityType et = EntityTypeHelper.getType(entity);

        if (scheme == null)
        {
            out = new GenericResourceAddressBean(et, entity, "unknown",
                    u.getHost(), "");
        }
        else if (scheme.compareToIgnoreCase("id") == 0)
        {
            out = new IdAddressBean(et, path, getSubScheme(u.getHost()));
        }
        else
        {
            out = new GenericResourceAddressBean(et, entity, scheme,
                    getSubScheme(u.getHost()), path);
        }

        return out;
    }

    /**
     * Creates a IdAddressBean based on a URA.
     *
     * @param   address  A URA which must use the ID scheme
     *
     * @return  A IdAddressBean or null
     *
     * @throws  URISyntaxException  If the URA is ill formed
     */
    public IdAddressBean createIdAddressBean(String address)
        throws URISyntaxException
    {

        IdAddressBean out = null;

        if ((address == null) || (address.isEmpty()))
        {
            throw new URISyntaxException("", "Null or Empty Address sent");
        }

        URI u = new URI(address);
        String entity = u.getScheme();
        String scheme = getScheme(u.getHost());

        String path = u.getPath();

        if (!path.isEmpty())
        {
            path = path.substring(1);
        }

        EntityType et = EntityTypeHelper.getType(entity);

        if (scheme != null)
        {

            if (scheme.compareToIgnoreCase("id") == 0)
            {
                out = new IdAddressBean(et, path, getSubScheme(u.getHost()));
            }
        }

        return out;
    }

    /**
     * Internal Helper to fetch the scheme from the full scheme For excample the
     * for "id.ssn" to string "id" would be returned.
     *
     * @param   s  full scheme reference
     *
     * @return  The scheme
     */
    private static String getScheme(String s)
    {

        if (s != null)
        {

            if (s.contains("."))
            {
                return s.substring(0, s.indexOf("."));
            }
            else
            {
                return s;
            }
        }
        else
        {
            return "unknown";
        }
    }

    /**
     * Internal Helper to fetch the sub-scheme from the full scheme For excample
     * the for "id.nid.ssn" to string "nid.ssn" would be returned.
     *
     * @param   s  full scheme reference
     *
     * @return  The sub-scheme
     */
    private static String getSubScheme(String s)
    {
        String out = "";

        if (s != null)
        {

            if (s.contains("."))
            {
                out = s.substring(s.indexOf(".") + 1);
            }
        }

        return out;
    }

    /**
     * Checks if a URA is a UniversalResourceAddressBean.
     *
     * @param   ura  A string with might be a URA
     *
     * @return  true if the URA is a well fromed URA
     */
    public static boolean isAddressBean(String ura)
    {

        try
        {

            if (!ura.isEmpty())
            {
                UniversalResourceAddressBean bean =
                    UniversalResourceAddressBeanFactory.getInstance()
                    .createUniversalResourceBean(ura);

                if (bean == null)
                {
                    return false;
                }
                else
                {
                    return !(bean.getScheme().compareTo("unknown") == 0);
                }
            }
            else
            {
                return false;
            }
        }
        catch (URISyntaxException e)
        {
            return false;
        }

    }

}
