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
public class Recommendation {

    private String body;
    private PayloadMeta metadata;
    private Set<Action> actions;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PayloadMeta getMetadata() {
        return metadata;
    }

    public void setMetadata(PayloadMeta metadata) {
        this.metadata = metadata;
    }

    public Set<Action> getActions() {
        if (actions == null) {
            actions = new HashSet<Action>();
        }

        return actions;
    }

}
