/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.service;

/**
 * Action names.  If you want to create a new alert:
 * 1) Add action here
 * 2) Add action to list of valid actions in AlertService (in this project)
 * 3) Update AlertUtil.isActionAllowed() to define the rules for when
 *    the action is allowed.
 * 4) To Test:
 *     a) add the action in the alert metadata
 *     b) perform action in inbox client
 *     c) verify that action occured on the alert
 *     d) verify that appropriate post-action effects are in place
 *
 * @author cmatser
 */
public interface ActionConstants {
    public static final String ACTION_ALERT = "Alert";
    public static final String ACTION_ACCEPT = "Accept";
    public static final String ACTION_ACKNOWLEDGE = "Acknowledge";
    public static final String ACTION_DISCARD = "Discard";
    public static final String ACTION_ESCALATE = "Escalate";
    public static final String ACTION_HOLD = "Hold";
    public static final String ACTION_MANUAL_ESCALATE = "Manual Escalate";
    public static final String ACTION_MODIFY = "Modify";
    public static final String ACTION_READ = "Read";
    public static final String ACTION_REJECT = "Reject";
}
