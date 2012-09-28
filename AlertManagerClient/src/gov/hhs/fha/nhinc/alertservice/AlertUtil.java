/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertservice;

import gov.hhs.fha.nhinc.alertmanager.model.AlertAction;
import gov.hhs.fha.nhinc.alertmanager.model.AlertTicket;
import gov.hhs.fha.nhinc.alertmanager.service.ActionConstants;
import gov.hhs.fha.nhinc.alertmanager.service.AlertService;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility class to help with service calls.
 *
 * @author cmatser
 */
public class AlertUtil {

    public static Log log = LogFactory.getLog(AlertUtil.class);

    /**
     * Determine if the action is allowed for the ticket.
     *
     * @param userId
     * @param action
     * @param ticket
     * @return
     */
    public static boolean isActionAllowed(String userId, String action, AlertTicket ticket) {

        //Make sure action is valid
        if (!AlertService.getValidActions().contains(action)) {
            log.debug("Provided action is not valid: " + action);
            return false;
        }

        //Get current actions
        Set<AlertAction> history = ticket.getActionHistory();

        //No more actions after these
        for (AlertAction a : history) {
            if (ActionConstants.ACTION_ACCEPT.equals(a.getActionName())) {
                log.debug("Alert has been accepted, " + action + " not allowed.");
                return false;
            }
            if (ActionConstants.ACTION_ACKNOWLEDGE.equals(a.getActionName())) {
                log.debug("Alert has been acknowledged, " + action + " not allowed.");
                return false;
            }
            if (ActionConstants.ACTION_REJECT.equals(a.getActionName())) {
                log.debug("Alert has been rejected, " + action + " not allowed.");
                return false;
            }
            if (ActionConstants.ACTION_DISCARD.equals(a.getActionName())) {
                log.debug("Alert has been discarded, " + action + " not allowed.");
                return false;
            }
        }

        //Further, Escalation cannot happen under these circumstances
        if (ActionConstants.ACTION_ESCALATE.equals(action)
                || ActionConstants.ACTION_MANUAL_ESCALATE.equals(action)) {

            if (ticket.getEscalationPeriod() <= 0) {
                log.debug("Escalation is not configured for this alert, " + action + " not allowed.");
                return false;
            }

            for (AlertAction a : history) {
                if (ActionConstants.ACTION_ESCALATE.equals(a.getActionName())) {
                    log.debug("Alert has already been escalated, " + action + " not allowed.");
                    return false;
                }
                if (ActionConstants.ACTION_MANUAL_ESCALATE.equals(a.getActionName())) {
                    log.debug("Alert has been escalated, " + action + " not allowed.");
                    return false;
                }
                if (ActionConstants.ACTION_HOLD.equals(a.getActionName())) {
                    log.debug("Alert is on hold, " + action + " not allowed.");
                    return false;
                }
            }
        }

        //Original alerted user is not allowed any further action
        //  after manual escalation, unless user escalated to themself
        Iterator<AlertAction> historyIter = history.iterator();
        while (historyIter.hasNext()) {
            AlertAction a = historyIter.next();
            if (ActionConstants.ACTION_MANUAL_ESCALATE.equals(a.getActionName())
                    && (a.getUserId() != null)
                    && a.getUserId().equals(userId)) {
                //Go through the rest of the history to see if this user is one
                //  of the users escalated to
                while (historyIter.hasNext()) {
                    AlertAction actionAfterManualEscalate = historyIter.next();
                    if (ActionConstants.ACTION_ALERT.equals(actionAfterManualEscalate.getActionName())
                            && actionAfterManualEscalate.getUserId().equals(userId)) {
                        //Action is allowed because user is in the list of notifed
                        //  users, even after manual escalation
                        return true;
                    }
                }

                log.debug("Alert has been manually escalated, " + action + " not allowed for this user.");
                return false;
            } //if manual escalate by user
        }

        //No duplicate actions allowed for the same user
        for (AlertAction a : history) {
            if (a.getActionName().equals(action) && a.getUserId().equals(userId)) {
                log.debug("Alert already has " + action + " for user: " + userId);
                return false;
            }
        }

        //If we get here, action is allowed
        return true;
    }

    /**
     * Determine if the alert ticket is closed.
     *
     * @param userId
     * @param action
     * @param ticket
     * @return
     */
    public static boolean isTickedClosed(AlertTicket ticket) {

        //Get current actions
        Set<AlertAction> history = ticket.getActionHistory();

        //No more actions after these
        for (AlertAction a : history) {
            if (ActionConstants.ACTION_ACCEPT.equals(a.getActionName())) {
                return true;
            }
            if (ActionConstants.ACTION_ACKNOWLEDGE.equals(a.getActionName())) {
                return true;
            }
            if (ActionConstants.ACTION_REJECT.equals(a.getActionName())) {
                return true;
            }
            if (ActionConstants.ACTION_DISCARD.equals(a.getActionName())) {
                return true;
            }
        }

        //If we get here, ticket is open
        return false;
    }

}
