
package gov.hhs.fha.nhinc.kmr.ura;

import java.util.HashMap;


/**
 * Helper class for dealing with EntityTypes.
 *
 * @author  Jerry Goodnough
 */
public class EntityTypeHelper
{

    /** Referense Name Map to Enumeration Type. */
    private static HashMap<String, EntityType> nameMap =
        new HashMap<String, EntityType>();

    static
    {
        nameMap.put("unknown", EntityType.UNKNOWN);
        nameMap.put("custom", EntityType.CUSTOM);
        nameMap.put("patient", EntityType.PATIENT);
        nameMap.put("provider", EntityType.PROVIDER);
        nameMap.put("device", EntityType.DEVICE);
        nameMap.put("group", EntityType.GROUP);
        nameMap.put("org", EntityType.ORG);
        nameMap.put("service", EntityType.SERVICE);
        nameMap.put("registry", EntityType.REGISTRY);
        nameMap.put("code", EntityType.CODE);
    }

    /**
     * Given a type name return the EntiyTypoe Enumeration that matches it.
     *
     * @param   type  Type name in any case
     *
     * @return  EntityType (Will be EntityType.UNKNOWN is type not matched)
     */
    public static EntityType getType(String type)
    {
        EntityType out;

        if (type != null)
        {
            out = nameMap.get(type.toLowerCase());

            if (out == null)
            {
                out = EntityType.UNKNOWN;
            }
        }
        else
        {
            out = EntityType.UNKNOWN;
        }

        return out;
    }

    /**
     * Given a EntityType get a string name for it in lowercase.
     *
     * @param   et  The EntityType to find the name of
     *
     * @return  Name of entity Type in lowercase (if not know "unkniown" will be
     *          returned
     */
    public static String getTypeName(EntityType et)
    {
        String out = "unknown";

        switch (et)
        {

            case CUSTOM:
            {
                out = "custom";

                break;
            }

            case PATIENT:
            {
                out = "patient";

                break;
            }

            case PROVIDER:
            {
                out = "provider";

                break;
            }

            case ROLE:
            {
                out = "role";

                break;
            }

            case DEVICE:
            {
                out = "device";

                break;
            }

            case GROUP:
            {
                out = "group";

                break;
            }

            case ORG:
            {
                out = "org";

                break;
            }

            case SERVICE:
            {
                out = "service";

                break;
            }

            case REGISTRY:
            {
                out = "registry";

                break;
            }

            case CODE:
            {
                out = "code";

                break;
            }
        }

        return out;
    }

    /**
     * Check if an EntityType is a standard form.
     *
     * @param   et  The EntityType to check
     *
     * @return  true if the EntityType is a standard name
     */
    public static boolean isStandard(EntityType et)
    {

        switch (et)
        {

            case CUSTOM:
            case UNKNOWN:
            {
                return false;
            }

            default:
            {
                return true;
            }
        }
    }
}
