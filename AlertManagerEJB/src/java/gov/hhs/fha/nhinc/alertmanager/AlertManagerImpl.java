/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager;

import gov.hhs.fha.nhinc.alertservice.AlertUtil;
import gov.hhs.fha.nhinc.common.task.UpdateAlertResponseType;
import gov.hhs.fha.nhinc.alertmanager.model.AlertAction;
import gov.hhs.fha.nhinc.alertmanager.model.AlertTicket;
import gov.hhs.fha.nhinc.alertmanager.model.TicketQueryParams;
import gov.hhs.fha.nhinc.alertmanager.service.ActionConstants;
import gov.hhs.fha.nhinc.alertmanager.service.AlertService;
import gov.hhs.fha.nhinc.displayalert.DisplayAlertDataUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation for AlertManagerService for service the acknowledgement and
 * discarding of alerts.
 *
 * @author cmatser
 */
public class AlertManagerImpl {

    /** Logging. */
    private static Log log = LogFactory.getLog(AlertManagerImpl.class);

    /** Status code success. */
    public static final int STATUS_FAILURE_CODE = -1;

    /** Status code success. */
    public static final int STATUS_SUCCESS_CODE = 1;

    /** Status detail succes. */
    public static final String STATUS_SUCCESS_DETAIL = "Success";

    public gov.hhs.fha.nhinc.common.task.UpdateAlertResponseType updateAlert(gov.hhs.fha.nhinc.common.task.UpdateAlertRequestType request) {
        UpdateAlertResponseType response = new UpdateAlertResponseType();
        Date now = new Date();

        log.debug("Got request to update ticket: " + request.getTicket());

        try {
            //Make sure update action is valid
            AlertService alertDBService = new AlertService();
            List<String> validActions = AlertService.getValidActions();
            if (!validActions.contains(request.getAction())) {
                response.setStatusCode(STATUS_FAILURE_CODE);
                response.setStatusDetail("Valid actions are: " + validActions);
                return response;
            }

            //Find ticket
            TicketQueryParams query = new TicketQueryParams();
            query.setTicketUniqueId(request.getTicket());
            List<AlertTicket> tickets = alertDBService.ticketQuery(query);
            AlertTicket ticket = null;

            if (tickets.isEmpty()) {
                throw new Exception("Unknown ticket: " + query.getTicketUniqueId());
            }

            if (tickets.size() > 1) {
                throw new Exception("Found too many tickets: " + query.getTicketUniqueId());
            }

            //Pull out ticket
            ticket = tickets.get(0);

            //Make sure action is allowed
            if (!AlertUtil.isActionAllowed(request.getUserID(), request.getAction(), ticket)) {
                throw new Exception("Cancelling update, action(" + request.getAction() + ") is not allowed for ticket: " + query.getTicketUniqueId());
            }

            //Check for manual ESCALATE action, it's handled specially
            if (ActionConstants.ACTION_ESCALATE.equals(request.getAction())) {
                EscalationMessage msg = new EscalationMessage();
                msg.setManual(true);
                msg.setTicket(request.getTicket());
                msg.setUserId(request.getUserID());
                msg.setUserName(request.getUserName());
                msg.setUserProvider(request.isUserProvider());
                msg.setActionMessage(request.getMessage());

                //Kickoff for immmediate escalation
                EscalationHandlerBean.queueEscalationMessage(msg);

                //Set response
                response.setStatusCode(STATUS_SUCCESS_CODE);
                response.setStatusDetail(STATUS_SUCCESS_DETAIL);

                log.debug("Escalated ticket: " + request.getTicket()
                    + ", by: " + request.getUserID());

                return response;
            }

            //Create new action
            AlertAction action = new AlertAction();
            action.setActionName(request.getAction());
            action.setActionTimestamp(now);
            action.setMessage(request.getMessage());
            action.setUserId(request.getUserID());
            action.setUserName(request.getUserName());
            action.setUserProvider(request.isUserProvider());
            action.setTicket(ticket);
            ticket.getActionHistory().add(action);

            //Update ticket
            alertDBService.saveTicket(ticket);

            response.setStatusCode(STATUS_SUCCESS_CODE);
            response.setStatusDetail(STATUS_SUCCESS_DETAIL);

            log.debug("Updated ticket: " + request.getTicket()
                    + ", with action: " + request.getAction()
                    + ", by: " + request.getUserID());
        }
        catch (Throwable t) {
            log.error("AlertManagerImpl, error handling alert acknowledgement.", t);
            response.setStatusCode(STATUS_FAILURE_CODE);
            response.setStatusDetail(t.getMessage());
        }

        return response;
    }

    /**
     * Retreives alerts for fact history request (AlertManagerImpl).
     * 
     * @param request
     * @return
     */
    public gov.hhs.fha.nhinc.common.task.GetAlertFactsResponseType getAlertFacts(gov.hhs.fha.nhinc.common.task.GetAlertFactsRequestType request) {
        return new DisplayAlertDataUtil().getAlertFacts(request);
    }

}
