package gov.hhs.fha.nhinc.kmr;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Steven Clark
 */
public class ClinicalReference
{
    private List<InclusionTable> inclusionTables;
    /**
     * Gets the value of the inclusionTables property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inclusionTables property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInclusionTables().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InclusionTable }
     *
     *
     */
    public List<InclusionTable> getInclusionTables()
    {
        if (inclusionTables == null) {
            inclusionTables = new ArrayList<InclusionTable>();
        }
        return this.inclusionTables;
    }
}
