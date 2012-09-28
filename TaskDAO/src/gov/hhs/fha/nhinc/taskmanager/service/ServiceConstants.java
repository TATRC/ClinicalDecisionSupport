/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.service;

/**
 *
 * @author cmatser
 */
public interface ServiceConstants {

    /** Task service type: jms queue . */
    public static final String TYPE_JMS_QUEUE = "JMS Queue";

    /** Task service type: swift sms message . */
    public static final String TYPE_SWIFT_SMS = "Swift SMS";

    /** Task service type: TODO task message . */
    public static final String TYPE_ZIMBRA_VTODO = "Zimbra VTODO";

    /** Task service type: EVENT task message . */
    public static final String TYPE_ZIMBRA_VEVENT = "Zimbra VEVENT";

    /** Task service type: SMTP task message . */
    public static final String TYPE_SMTP = "SMTP";

    /** Task service type: Disease Registy task message . */
    public static final String TYPE_DISEASE_REGISTRY = "Disease Registry";

    /** Task service type: Lab Order task message . */
    public static final String TYPE_LAB_ORDER = "Lab Order";

    /** Task service type: Slot Rquest task message . */
    public static final String TYPE_SLOT_REQUEST = "Slot Request";

}
