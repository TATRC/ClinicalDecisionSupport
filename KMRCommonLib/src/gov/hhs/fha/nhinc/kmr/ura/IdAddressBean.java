

package gov.hhs.fha.nhinc.kmr.ura;

import java.net.URISyntaxException;


/**
 * Concrete Implementation of the UniversalResourceAddressBean for the Id Scheme
 * The class add the fields subScheme and id to the generic iterface.
 *
 * <p>Example URA: patient://id/578855</p>
 *
 * @author  Jerry Goodnough
 */
public class IdAddressBean extends BaseResourceAddressBean
    implements UniversalResourceAddressBean
{

    /** Teh actual Id. */
    private String id;

    /** The Sub type of the Id. */
    private String idSubScheme = "";

    /**
     * Creates a new IdAddressBean object.
     *
     * @param  entityType   Entity Type (e.g. EntityType.PATIENT
     * @param  id           The actual Id that this bean represents (e.g 100 or
     *                      88390-3873)
     * @param  idSubScheme  The SubScheme of the Id (Optional)
     */
    public IdAddressBean(EntityType entityType, String id, String idSubScheme)
    {
        this.entityType = entityType;
        this.id = id;

        if (idSubScheme != null)
        {
            this.idSubScheme = idSubScheme;
        }
    }

    /**
     * Check if a given URA string is a Valid IdAddressBean. For example
     * patient://id.test/10 should be valid, while patient://ldap/cn=georgebush
     * should be invalid since the valid ura is not in the id scheme.
     *
     * @param   ura  The ura to check if is a valid Id URA
     *
     * @return  true if the ura is a valid Id Address.
     */
    public static boolean isIdAddressBean(String ura)
    {

        if (ura != null)
        {

            if (!ura.isEmpty())
            {

                try
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
                        return bean.getScheme().compareTo("id") == 0;
                    }
                }
                catch (URISyntaxException e)
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Get the Id. The Id is formed from the path of the ura.
     *
     * @return  The Id string associaited with the IdAddress bean
     */
    public String getId()
    {
        return id;
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getPath()
     */
    public String getPath()
    {
        return id;
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getScheme()
     */
    public String getScheme()
    {
        return "id";
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getSubScheme()
     */
    public String getSubScheme()
    {
        return idSubScheme;
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getFullScheme()
     */
    public String getFullScheme()
    {

        if (idSubScheme.length() > 0)
        {
            return "id." + idSubScheme;
        }
        else
        {
            return "id";
        }
    }

    /**
     * Creates a IdAddressBean from a valid Id URA.
     *
     * @param   ura  An Id URA
     *
     * @return  A IdAddress Bean or null
     *
     * @throws  URISyntaxException  If the URA is ill formed
     */
    public static IdAddressBean parse(String ura) throws URISyntaxException
    {
        return UniversalResourceAddressBeanFactory.getInstance()
            .createIdAddressBean(ura);
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean##getURA()
     */
    @Override public String getURA()
    {
        return getEntityTypeName() + "://" + getFullScheme() + "/" + id;
    }
}
