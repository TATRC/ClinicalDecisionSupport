/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Handle Create Lab Order task events.  Forward to mirth.
 *
 * @author cmatser
 */
public class SlotRequestHandler {

    /** Failure code. */
    public static final String RESPONSE_FAILURE = "-1";

    /** Logging. */
    private static Log log = LogFactory.getLog(SlotRequestHandler.class);

    /**
     * Process task.
     */
    public void handleMessage(String taskTicket, TaskServiceRef serviceRef, TaskMessage taskMessage, TaskType task) {
        SlotRequestMessage apptMessage = null;

        log.debug("Handling schedule appointment task.");

        if (taskMessage instanceof SlotRequestMessage) {
            apptMessage = (SlotRequestMessage) taskMessage;
        }

        if (apptMessage == null) {
            log.error("Error, invalid schedule appointment task for ticket: " + taskTicket);
            return;
        }

        //Same interfaces as for orders
        try { // This code block invokes the Orders:acceptMessage operation on web service
            org.mirth.orders._Proxy7_Stub stub =
                (org.mirth.orders._Proxy7_Stub)
                    new org.mirth.orders._Proxy7Service_Impl().getPort(
                        org.mirth.orders._Proxy7.class);
            stub._setProperty(
                org.mirth.orders._Proxy7_Stub.ENDPOINT_ADDRESS_PROPERTY,
                serviceRef.getLocation());
            stub.acceptMessage(apptMessage.getSlotRequest());

            log.debug("Appointment request sent to mirth, ticket: " + taskTicket);
        } catch(Exception e) {
            log.error("Error sending appointment request to mirth, ticket: " + taskTicket, e);
        }

    }

}