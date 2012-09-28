/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

/**
 * JMS message for disease registry actions.
 *
 * @author cmatser
 */
public class SlotRequestMessage extends TaskMessage
        implements java.io.Serializable {

    private String slotRequest;

    /**
     * @return the slotRequest
     */
    public String getSlotRequest() {
        return slotRequest;
    }

    /**
     * @param slotRequest the slotRequest to set
     */
    public void setSlotRequest(String slotRequest) {
        this.slotRequest = slotRequest;
    }

}