/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertpayload;

/**
 *
 * @author cmatser
 */
public class ActionParam 
        implements Comparable<ActionParam> {

    private String paramId;
    private String name;
    private String value;

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Sorts by id (lexical, ignoring case).
     *
     * @param that
     * @return
     */
    public int compareTo(ActionParam that) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (paramId == null)
            return BEFORE;

        if ((that == null) || (that.getParamId() == null))
            return AFTER;

        return paramId.compareToIgnoreCase(that.getParamId());
    }

}
