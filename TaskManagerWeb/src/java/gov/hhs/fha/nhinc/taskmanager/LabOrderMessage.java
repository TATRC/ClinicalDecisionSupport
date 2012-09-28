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
public class LabOrderMessage extends TaskMessage
        implements java.io.Serializable {

    private String labOrder;

    /**
     * @return the labOrder
     */
    public String getLabOrder() {
        return labOrder;
    }

    /**
     * @param labOrder the labOrder to set
     */
    public void setLabOrder(String labOrder) {
        this.labOrder = labOrder;
    }

}