package gov.hhs.fha.nhinc.kmr;

import java.util.UUID;
import gov.hhs.fha.nhinc.adapter.fact.FactType;

/**
 *
 * @author Steven Clark
 */
public class FactWrapper {
    private String uuid;
    private String primaryKey;
    private FactType fact;

    public FactWrapper(String primaryKey, FactType fact)
    {
        uuid = UUID.randomUUID().toString();
        this.primaryKey = primaryKey;
        this.fact = fact;
    }

    /**
     * @return the UUID
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * @return the primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @return the fact
     */
    public FactType getFact() {
        return fact;
    }
}
