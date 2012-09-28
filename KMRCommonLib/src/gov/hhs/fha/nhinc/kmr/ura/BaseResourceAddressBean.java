package gov.hhs.fha.nhinc.kmr.ura;

/**
 * Common helper class for UniversalResourceAddressBean Implementation.
 *
 * @author  Jerry Goodnough
 */
public class BaseResourceAddressBean
{

    /** The Type of this Id. */
    protected EntityType entityType = EntityType.UNKNOWN;

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getEntityType()
     */
    public EntityType getEntityType()
    {
        return entityType;
    }

    /**
     * @see  gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean#getEntityTypeName()
     */
    public String getEntityTypeName()
    {
        return EntityTypeHelper.getTypeName(entityType);
    }

    /**
     * Return the URA String
     *
     * @return  URA of this Bean
     */
    public String getURA()
    {
        return getEntityTypeName() + "://";
    }

    /**
     * Indicates whether some other object is "equal to" this one. Two Id's are
     * equal if the URAs are equal.
     *
     * @param   obj  the reference object with which to compare.
     *
     * @return  true if this object is the same as the obj argument; false
     *          otherwise.
     */
    @Override public boolean equals(Object obj)
    {

        if (!(obj instanceof UniversalResourceAddressBean))
        {
            return false;
        }

        return getURA().compareTo(((UniversalResourceAddressBean) obj)
                .getURA()) == 0;

    }

    /**
     * Returns a hash code value for the object.
     *
     * @return  a hash code value for this object.
     */
    @Override public int hashCode()
    {
        return toString().hashCode();
    }

    /**
     * @see  java.lang.Object#toString()
     */
    @Override public String toString()
    {
        return this.getURA();
    }
}
