package gov.hhs.fha.nhinc.kmr.ura;

import java.net.URISyntaxException;


/**
 * Generic Implementation for most
 *
 * @author  Jerry Goodnough
 */
public class GenericResourceAddressBean extends BaseResourceAddressBean
    implements UniversalResourceAddressBean
{


    /** Genric Type name support */
    protected String entityTypeName = "unknown";

    /** Path */
    protected String path = "";

    /** Scheme */
    protected String scheme = "";

    /** SubScheme */
    protected String schemeType = "";


    /**
     * Creates a new GenericResourceAddressBean object.
     */
    public GenericResourceAddressBean()
    {

    }

    /**
     * Creates a new GenericResourceAddressBean object.
     * The Entity Type will be custom
     *
     * @param  typeName    Unrestricted type name
     * @param  scheme      Scheme Name
     * @param  schemeType  SubScheme (if any)
     * @param  path        Path in Scheme
     */
    public GenericResourceAddressBean(String typeName, String scheme,
        String schemeType, String path)
    {
        this.entityTypeName = typeName;
        this.entityType=EntityType.CUSTOM;

        EntityType tempType = EntityTypeHelper.getType(typeName);
        if (EntityTypeHelper.isStandard(tempType))
        {
            this.entityType=tempType;
        }
        this.scheme = scheme;
        if(schemeType != null)
        {
            this.schemeType = schemeType;
        }
        this.path = path;
    }

    /**
     * Creates a new GenericResourceAddressBean object.
     *
     * @param  entityType  The Entity Type
     * @param  typeName    Unrestricted type name (Uss if type non standard)
     * @param  scheme      Scheme Name
     * @param  schemeType  SubScheme (if any)
     * @param  path        Path in Scheme
     */
    public GenericResourceAddressBean(EntityType entityType, String typeName,
        String scheme, String schemeType, String path)
    {
        this.entityTypeName = typeName;
        this.schemeType = schemeType;
        this.path = path;
        this.entityType = entityType;
        this.scheme = scheme;
                if(schemeType != null)
        {
            this.schemeType = schemeType;
        }

    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getEntityTypeName()
     */
    @Override public String getEntityTypeName()
    {

        if (!EntityTypeHelper.isStandard(entityType))
        {
            return entityTypeName;
        }
        else
        {
            return EntityTypeHelper.getTypeName(entityType);
        }
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getFullScheme()
     */
    public String getFullScheme()
    {

        if (schemeType.length() > 0)
        {
            return scheme + "." + schemeType;
        }
        else
        {
            return scheme;
        }
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getPath()
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getScheme()
     */
    public String getScheme()
    {
        return scheme;
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getSubScheme()
     */
    public String getSubScheme()
    {
        return schemeType;
    }

    /**
     * Creates UniversalResourceAddressBean based on a URA
     *
     * @param   ura  The URA to use to create the UniversalResourceAddressBean
     *
     * @return  UniversalResourceAddressBean represented by the URA
     *
     * @throws  URISyntaxException  If the URA is invalid
     */
    public static UniversalResourceAddressBean parse(String ura)
        throws URISyntaxException
    {
        return UniversalResourceAddressBeanFactory.getInstance()
            .createUniversalResourceBean(ura);
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getURA()
     */
    @Override public String getURA()
    {
        return getEntityTypeName() + "://" + getFullScheme() + "/" + path;
    }
}
