/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertpayload;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author cmatser
 */
public class Action
        implements Comparable<Action> {

    private String actionId;
    private String name;
    private Set<ActionParam> parameters;

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ActionParam> getParameters() {
        if (parameters == null) {
            parameters = new HashSet<ActionParam>();
        }

        return parameters;
    }

    /**
     * Sorts by action id (lexical, ignoring case).
     * 
     * @param that
     * @return
     */
    public int compareTo(Action that) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (actionId == null)
            return BEFORE;

        if ((that == null) || (that.getActionId() == null))
            return AFTER;

        return actionId.compareToIgnoreCase(that.getActionId());
    }

}
