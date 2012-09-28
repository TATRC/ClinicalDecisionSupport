/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager;

import gov.hhs.fha.nhinc.alertservice.AlertUtil;
import gov.hhs.fha.nhinc.common.task.AlertedComponent;
import gov.hhs.fha.nhinc.alertmanager.model.AlertAction;
import gov.hhs.fha.nhinc.alertmanager.model.AlertContact;
import gov.hhs.fha.nhinc.alertmanager.model.AlertEscalationContact;
import gov.hhs.fha.nhinc.alertmanager.model.AlertServiceRef;
import gov.hhs.fha.nhinc.alertmanager.model.AlertSpec;
import gov.hhs.fha.nhinc.alertmanager.model.AlertTicket;
import gov.hhs.fha.nhinc.alertmanager.model.AlertType;
import gov.hhs.fha.nhinc.alertmanager.model.TicketQueryParams;
import gov.hhs.fha.nhinc.alertmanager.service.ActionConstants;
import gov.hhs.fha.nhinc.alertmanager.service.AlertService;
import gov.hhs.fha.nhinc.alertmanager.service.ServiceConstants;
import gov.hhs.fha.nhinc.alertmanager.service.SpecConstants;
import gov.hhs.fha.nhinc.alertmanager.service.TicketConstants;
import gov.hhs.fha.nhinc.common.task.AlertContext;
import gov.hhs.fha.nhinc.common.task.DestinationContext;
import gov.hhs.fha.nhinc.common.task.PatientContext;
import gov.hhs.fha.nhinc.common.task.RuleContext;
import gov.hhs.fha.nhinc.common.task.StartTaskFromRuleRequestType;
import gov.hhs.fha.nhinc.common.task.TaskManagerResponseType;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;
import gov.hhs.fha.nhinc.ldapaccess.RoleDAO;
import gov.hhs.fha.nhinc.ldapaccess.RoleDTO;
import gov.hhs.fha.nhinc.common.task.TaskManager;
import gov.hhs.fha.nhinc.taskmanagerclient.ContactDetails;
import gov.hhs.fha.nhinc.taskmanagerclient.TaskHandlerMessage;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.WebServiceRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
@MessageDriven(mappedName = "jms/AlertQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class AlertHandlerBean implements MessageListener {
    @EJB
    private EscalationHandlerBeanLocal escalationHandlerBean;

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/AlertedComponent.wsdl")
    private AlertedComponent alertedComponentService;

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/TaskManager.wsdl")
    private TaskManager taskManagerService;

    @Resource
    private MessageDrivenContext mdc;

    /** Available escalation types. */
    private List<String> availableEscalationTypes = new LinkedList<String>();

    /** Property file. */
    public static final String PROPERTY_FILE = "taskmanager";

    /** Property name for task web service location. */
    public static final String PROPERTY_TASK_LOCATION = "taskURL";

    /** Property name for sms task id. */
    public static final String PROPERTY_SMS_TASK = "taskIDForSMSEscalation";

    /** Property name for alert originator. */
    public static final String PROPERTY_ORIGINATOR = "alertOriginator";

    /** Default alert originator. */
    public static final String ORIGINATOR_DEFAULT = "DDSS";

    /** Name for task specification attribute for the alert profile. */
    public static final String TASK_ATTR_NAME_ALERT_ID = "alertProfileId";

    /** Message for auto-escalation (timeout). */
    public static final String MESSAGE_ESCALATION_TIMEOUT = "Auto-escalated alert.";

    /** Message for manual-escalation. */
    public static final String MESSAGE_ESCALATION_MANUAL = "Escalated alert.";

    /** Message for sms notification. */
    public static final String MESSAGE_NOTIFICATION_SMS = "Notification via SMS.";

    /** Alert Type: Alert (default) */
    public static final String ALERT_TYPE_ALERT = "Alert";
    public static final String ALERT_TYPE_DEFAULT = ALERT_TYPE_ALERT;

    /** Alert Type: SMS */
    public static final String ALERT_TYPE_SMS = "SMS";

    /** Special provider ids and names. */
    public static final String SYSTEM_USER_ID = "0";
    public static final String SYSTEM_USER_NAME = "System";
    public static final String PATIENT_DIRECTED_USER_ID = "-1";
    public static final String PATIENT_DIRECTED_USER_NAME = "Patient";

    /** Action history message for Alert notification action. */
    public static final String ACTION_ALERT_HISTORY_MSG = "Notification generated.";

    /** Logging. */
    private static Log log = LogFactory.getLog(AlertHandlerBean.class);

    public AlertHandlerBean() {
        availableEscalationTypes.add(ALERT_TYPE_ALERT);
        availableEscalationTypes.add(ALERT_TYPE_SMS);

        //Init web services
        if (alertedComponentService == null) {
            alertedComponentService = new AlertedComponent();
        }
        if (taskManagerService == null) {
            taskManagerService = new TaskManager();
        }
    }

    public void onMessage(Message message) {
        String ticket = null;

        try {
            ticket = message.getJMSCorrelationID();
            log.debug("Alert handler processing ticket: " + ticket);

            if (message instanceof ObjectMessage) {
                ObjectMessage oMsg = (ObjectMessage) message;
                Object object = oMsg.getObject();
                if (object instanceof TaskHandlerMessage) {
                    handleTask((TaskHandlerMessage) object);
                }
                else if (object instanceof EscalationMessage) {
                    handleEscalation((EscalationMessage) object);
                }
                else {
                    throw new Exception("Found unknown object in message: " + object.getClass().getName());
                }
            }

        }
        catch (JMSException jmse) {
            log.error("Rollback, error processing message for alert handler, ticket: " + ticket, jmse);
            //Don't rollback else, message gets stuck in queue
            //mdc.setRollbackOnly();
        }
        catch (Throwable te) {
            log.error("Error processing message for alert handler, ticket: " + ticket, te);
        }

    }

    /**
     * Process task messages.  This typically ends up creating an alert.
     *
     * @param taskMessage
     */
    private void handleTask(TaskHandlerMessage taskMessage) {

        //Check if task was found
        if (taskMessage == null) {
            log.error("Null task message, ignoring.");
            return;
        }

        log.debug("Handling task, ticket: " + taskMessage.getTaskTicket());

        try {
            //Pull out alert profile from database
            String alertId = taskMessage.getTaskAttributes().get(TASK_ATTR_NAME_ALERT_ID);
            AlertService alertService = new AlertService();
            AlertType alert = alertService.getAlert(new Long(alertId));

            if (alert == null) {
                throw new Exception("Alert profle id(" + alertId + ") was not found.");
            }

            //Find alert service
            String serviceRefId = null;
            for (AlertSpec spec : alert.getSpecifications()) {
                if (SpecConstants.NAME_SERVICE_REF.equals(spec.getName())) {
                    serviceRefId = spec.getValue();
                    break;
                }
            }

            if (serviceRefId == null) {
                throw new Exception("Alert id(" + alertId + ") had no specification(" + SpecConstants.NAME_SERVICE_REF + ").");
            }

            AlertServiceRef serviceRef = null;
            serviceRef = alertService.getServiceRef(new Long(serviceRefId));

            if (serviceRef == null) {
                throw new Exception("Could not find service reference id(" + serviceRefId + ").");
            }

            //Create ticket
            AlertTicket ticket = createTicket(alert, taskMessage);

            //Start escalation timer if needed
            if (ticket.getEscalationPeriod() > 0) {
                escalationHandlerBean.initOneTimeTimer(
                    ticket.getTicketUniqueId(),
                    ticket.getEscalationPeriod(),
                    MESSAGE_ESCALATION_TIMEOUT,
                    false,
                    SYSTEM_USER_ID,
                    SYSTEM_USER_NAME,
                    true);
            }

            //Handle each service type appropriately
            if (ServiceConstants.TYPE_WEB_SERVICE.equals(serviceRef.getType())) {
                //For each provider: create request, send request, update ticket
                for (AlertContact provider : ticket.getProviders()) {
                    invokeWS(createAlertProviderRequest(provider, false, ticket, alert),
                        provider, ticket, serviceRef);
                }
            }
            else {
                throw new UnsupportedOperationException("Unsupported service type: " + serviceRef.getType());
            }

        } catch (Throwable t) {
            log.error("Alert handler error processing ticket: " + taskMessage.getTaskTicket(), t);
        }

    }

    /**
     * Process escalation messages.  Escalate alert by notifying another provider.
     *
     * @param escalationMessage
     */
    private void handleEscalation(EscalationMessage escalationMessage) {

        //Check if message was found
        if (escalationMessage == null) {
            log.error("Null escalation message, ignoring.");
            return;
        }

        //Check if ticket was sent
        if (escalationMessage.getTicket() == null) {
            log.error("Null escalation ticket, ignoring.");
            return;
        }

        log.debug("Handling escalation, ticket: " + escalationMessage.getTicket());

        try {
            //Pull out ticket from database
            TicketQueryParams query = new TicketQueryParams();
            query.setTicketUniqueId(escalationMessage.getTicket());

            AlertService alertDBService = new AlertService();
            List<AlertTicket> tickets = alertDBService.ticketQuery(query);
            AlertTicket ticket = null;

            if (tickets.isEmpty()) {
                throw new Exception("Aborting escalation, could not find ticket.");
            }

            //Ensure ticket is configured for escalation
            ticket = tickets.get(0);
            if (ticket.getEscalationPeriod() <= 0) {
                log.info("Aborting escalation, ticket not configured for escalation.");
                return;
            }

            //Ensure ticket can be escalated
            if (!escalationMessage.isManual() && !AlertUtil.isActionAllowed(SYSTEM_USER_ID, ActionConstants.ACTION_ESCALATE, ticket)) {
                log.info("Aborting escalation, action not allowed for " + ticket.getTicketUniqueId());
                return;
            }
            if (escalationMessage.isManual() && !AlertUtil.isActionAllowed(ticket.getProviders().iterator().next().getProviderId(), ActionConstants.ACTION_MANUAL_ESCALATE, ticket)) {
                throw new Exception("Aborting manual escalation, action not allowed for " + ticket.getTicketUniqueId());
            }

            //Find alert corresponding to ticket
            AlertType alert = null;
            alert = alertDBService.getAlert(ticket.getAlertId());

            if (alert == null) {
                throw new Exception("Aborting escalation, could not find alert profile for ticket.");
            }

            //Find alert service
            String serviceRefId = null;
            for (AlertSpec spec : alert.getSpecifications()) {
                if (SpecConstants.NAME_SERVICE_REF.equals(spec.getName())) {
                    serviceRefId = spec.getValue();
                    break;
                }
            }

            if (serviceRefId == null) {
                throw new Exception("Alert id(" + alert.getAlertTypeId() + ") had no specification(" + SpecConstants.NAME_SERVICE_REF + ").");
            }

            AlertServiceRef serviceRef = null;
            serviceRef = alertDBService.getServiceRef(new Long(serviceRefId));

            if (serviceRef == null) {
                throw new Exception("Could not find service reference id(" + serviceRefId + ").");
            }

            //Add escalation action
            AlertAction action = new AlertAction();
            if (escalationMessage.isManual()) {
                action.setActionName(ActionConstants.ACTION_MANUAL_ESCALATE);
            }
            else {
                action.setActionName(ActionConstants.ACTION_ESCALATE);
            }
            action.setActionTimestamp(new Date());
            action.setMessage(escalationMessage.getActionMessage());
            action.setUserId(escalationMessage.getUserId());
            action.setUserName(escalationMessage.getUserName());
            action.setUserProvider(escalationMessage.isUserProvider());
            action.setTicket(ticket);
            ticket.getActionHistory().add(action);
            alertDBService.saveTicket(ticket);

            //Discover all escalation providers
            List<AlertEscalationContact> escalationProviders = new LinkedList<AlertEscalationContact>();
            for (AlertEscalationContact contact : ticket.getEscalationProviders()) {

                try {
                    //Search LDAP for current provider and role information
                    List<ContactInfo> providersInfo = getProviderIdAndName(contact.getProviderLdap(), contact.getRole(), contact.getLocation());
                    for (ContactInfo providerInfo : providersInfo) {
                        //Create providers contact for this escalation
                        AlertEscalationContact escalationContact = new AlertEscalationContact();
                        escalationContact.setMethod(contact.getMethod());
                        escalationContact.setProviderId(providerInfo.id);
                        escalationContact.setProviderName(providerInfo.name);
                        escalationContact.setProviderLdap(providerInfo.ldap);
                        escalationContact.setOrganizationId(contact.getOrganizationId());
                        escalationContact.setClinicId(contact.getClinicId());
                        escalationContact.setRole(contact.getRole());
                        escalationContact.setLocation(contact.getLocation());
                        escalationProviders.add(escalationContact);
                    }
                }
                catch (Exception e) {
                    //ignore, already logged
                }

            }

            //For each provider: create request, send request, update ticket
            for (AlertEscalationContact provider : escalationProviders) {
                if (ALERT_TYPE_SMS.equals(provider.getMethod())) {
                    handleSMSEscalation(provider, ticket);
                }
                else if (ServiceConstants.TYPE_WEB_SERVICE.equals(serviceRef.getType())) {
                    invokeWS(createAlertProviderRequest(provider, true, ticket, alert),
                        provider, ticket, serviceRef);
                }
                else {
                    throw new UnsupportedOperationException("Unsupported service type: " + serviceRef.getType());
                }
            }

        } catch (Throwable t) {
            log.error("Alert handler error processing ticket: " + escalationMessage.getTicket(), t);
        }

    }

    /**
     * Notify via web service.  This could be a new alert or an escalation.
     *
     * @param alertProviderRequest
     * @param ticket
     * @param serviceRef
     */
    private void invokeWS(
        gov.hhs.fha.nhinc.common.task.AlertProviderRequestType alertProviderRequest,
        AlertContact provider,
        AlertTicket ticket,
        AlertServiceRef serviceRef) {

        try {
            //Setup web service call
            gov.hhs.fha.nhinc.common.task.AlertedComponentPortType port = alertedComponentService.getAlertedComponentPortSoap11();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                serviceRef.getLocation());

            //Update alert time in request
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(ticket.getAlertTimestamp());
            alertProviderRequest.getAlert().setAlertDateTime(
               DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));

            //Update ticket history to note notification
            AlertService alertDBService = new AlertService();
            AlertAction action = new AlertAction();
            action.setUserId(provider.getProviderId());
            action.setUserName(provider.getProviderName());
            action.setUserProvider(Boolean.TRUE);
            action.setActionName(ActionConstants.ACTION_ALERT);
            action.setActionTimestamp(new Date());
            action.setMessage(ACTION_ALERT_HISTORY_MSG);
            action.setTicket(ticket);
            ticket.getActionHistory().add(action);
            alertDBService.saveTicket(ticket);

            //Send alert
            gov.hhs.fha.nhinc.common.task.AlertProviderResponseType result = port.alertProvider(alertProviderRequest);

            //Log result
            if (log.isDebugEnabled()) {
                StringBuilder msg = new StringBuilder();
                msg.append("Sent alert ticket: ");
                msg.append(alertProviderRequest.getAlert().getTicket());
                msg.append(", to endpoint: ");
                msg.append(serviceRef.getLocation());
                msg.append(", status: ");
                if (result != null) {
                    msg.append(result.getStatusCode());
                    msg.append(", detail: ");
                    msg.append(result.getStatusDetail());
                    msg.append(".");
                }
                else {
                    msg.append("null.");
                }

                log.debug(msg.toString());
            }

        } catch (Exception ex) {
            log.error("Error sending alert ticket:" + alertProviderRequest.getAlert().getTicket(), ex);
        }

    }

    /**
     * Create ticket entry for the database based on request to alerted component
     * and alert profile.
     *
     * @param alert
     * @param taskMessage
     * @return
     * @throws java.lang.Exception
     */
    private AlertTicket createTicket(AlertType alert, TaskHandlerMessage taskMessage)
            throws Exception {

        int escalationMinutes = -1;

        //Get escalationPeriod
        try {
            escalationMinutes = taskMessage.getEscalationMinutes();
        }
        catch (NullPointerException npe) {
            log.warn("Setting escalationMinutes to: "  + escalationMinutes, npe);
        }

        //Get alert originator
        String alertOriginator = PropertyAccessor.getProperty(
                    PROPERTY_FILE, PROPERTY_ORIGINATOR);
        if ((alertOriginator == null) || alertOriginator.isEmpty()) {
            alertOriginator = ORIGINATOR_DEFAULT;
        }

        //Create ticket from request
        AlertTicket ticket = new AlertTicket();
        ticket.setType((taskMessage instanceof MsgTypeAlertMessage) ?
            TicketConstants.TICKET_TYPE_MESSAGE :
            TicketConstants.TICKET_TYPE_ALERT);
        ticket.setTicketUniqueId(taskMessage.getTaskTicket());
        ticket.setAlertOriginator(alertOriginator);
        ticket.setAlertTimestamp(new Date());
        ticket.setEscalationPeriod(escalationMinutes);
        ticket.setAlertId(alert.getAlertTypeId());
        if ((taskMessage.getSubject() != null) && !taskMessage.getSubject().isEmpty()) {
            ticket.setDescription(taskMessage.getSubject());
        }
        else {
            ticket.setDescription(alert.getDescription());
        }
        ticket.setPayload(taskMessage.getMessage());
        ticket.setPriority(taskMessage.getPriority());
        ticket.setPatientUnitNumber(taskMessage.getPatientUnitNumber());
        ticket.setPatientName(taskMessage.getPatientName());
        ticket.setPatientSex(taskMessage.getPatientSex());
        ticket.setPatientFMPSSN(taskMessage.getPatientFMPSSN());
        ticket.setFactNCID(taskMessage.getFactNCID());
        ticket.setFactName(taskMessage.getFactName());
        ticket.setFactValue(taskMessage.getFactValue());
        ticket.setFactType(taskMessage.getFactType());
        ticket.setActionId(taskMessage.getActionID());
        ticket.setActionType(taskMessage.getActionType());
        ticket.setRuleId(taskMessage.getRuleID());
        ticket.setRuleDesc(taskMessage.getRuleDesc());
        ticket.setRuleName(taskMessage.getRuleName());

        //Test for provider info
        if ((taskMessage.getProviders() == null) || taskMessage.getProviders().isEmpty()) {
            throw new Exception("Providers not found for task.");
        }

        //Pull out provider info
        for (ContactDetails contact : taskMessage.getProviders()) {

            try {
                //Search LDAP for provider information
                List<ContactInfo> providersInfo = getProviderIdAndName(contact.getProvider(), contact.getRole(), contact.getLocation());
                for (ContactInfo providerInfo : providersInfo) {
                    //Create providers contact for this alert
                    AlertContact providerContact = new AlertContact();
                    providerContact.setMethod(ALERT_TYPE_ALERT);
                    providerContact.setProviderId(providerInfo.id);
                    providerContact.setProviderName(providerInfo.name);
                    providerContact.setProviderLdap(providerInfo.ldap);
                    providerContact.setOrganizationId(contact.getOrganization());
                    providerContact.setClinicId(contact.getClinic());
                    providerContact.setRole(contact.getRole());
                    providerContact.setLocation(contact.getLocation());
                    providerContact.setTicket(ticket);
                    ticket.getProviders().add(providerContact);
                }
            }
            catch (Exception e) {
                //ignore, already logged
            }

        }

        //Check that we have at least one provider
        if (ticket.getProviders().isEmpty()) {
            throw new Exception("No providers found for task.");
        }

        //Pull out escalation provider info
        //  Don't search ldap till escalation time
        for (ContactDetails contact : taskMessage.getEscalationProviders()) {

            String escalationType = contact.getMethod();
            if (!availableEscalationTypes.contains(escalationType)) {
                log.warn("Changing escalationType from: " + escalationType
                    + " to: "  + ALERT_TYPE_DEFAULT);
                escalationType = ALERT_TYPE_DEFAULT;
            }

            AlertEscalationContact providerContact = new AlertEscalationContact();
            providerContact.setMethod(escalationType);
            providerContact.setProviderLdap(contact.getProvider());
            providerContact.setOrganizationId(contact.getOrganization());
            providerContact.setClinicId(contact.getClinic());
            providerContact.setRole(contact.getRole());
            providerContact.setLocation(contact.getLocation());
            providerContact.setTicket(ticket);
            ticket.getEscalationProviders().add(providerContact);
        }

        //Check for escalation
        if ((ticket.getEscalationPeriod() > 0) && ticket.getEscalationProviders().isEmpty()) {
            throw new Exception("No escalation providers found for task.");
        }

        //Save ticket
        AlertService alertDBService = new AlertService();
        alertDBService.saveTicket(ticket);

        return ticket;
    }

    /**
     * Create AlertedComponet.notify request.
     *
     * @param provider
     * @param isEscalation
     * @param ticket
     * @param alert
     * @return
     * @throws java.lang.Exception
     */
    gov.hhs.fha.nhinc.common.task.AlertProviderRequestType createAlertProviderRequest(
        AlertContact provider, boolean isEscalation, AlertTicket ticket, AlertType alert)
            throws Exception {

        gov.hhs.fha.nhinc.common.task.AlertProviderRequestType request = new gov.hhs.fha.nhinc.common.task.AlertProviderRequestType();
        request.setAlert(new AlertContext());
        request.getAlert().setTicket(ticket.getTicketUniqueId());
        request.getAlert().setType(TicketConstants.TICKET_TYPE_ALERT);
        request.setProviderID(provider.getProviderId());
        request.setOrganizationID(provider.getOrganizationId());
        request.setClinicID(provider.getClinicId());
        request.setEscalation(isEscalation);
        request.getAlert().setAlertName(alert.getName());
        request.getAlert().setAlertDescription(alert.getDescription());
        request.setSubject(ticket.getDescription());
        request.setMessage(ticket.getPayload());
        request.setPriority(ticket.getPriority());
        request.setPatient(new PatientContext());
        request.getPatient().setPatientUnitNumber(ticket.getPatientUnitNumber());
        request.getPatient().setPatientName(ticket.getPatientName());
        request.getPatient().setPatientSex(ticket.getPatientSex());
        request.getPatient().setPatientFMPSSN(ticket.getPatientFMPSSN());
        request.setRule(new RuleContext());
        request.getRule().setFactNCID(ticket.getFactNCID());
        request.getRule().setFactName(ticket.getFactName());
        request.getRule().setFactValue(ticket.getFactValue());
        request.getRule().setFactType(ticket.getFactType());
        request.getRule().setActionID(ticket.getActionId());
        request.getRule().setActionType(ticket.getActionType());
        request.getRule().setRuleID(ticket.getRuleId());
        request.getRule().setRuleDesc(ticket.getRuleDesc());
        request.getRule().setRuleName(ticket.getRuleName());

        return request;
    }

    private void handleSMSEscalation(AlertContact provider, AlertTicket ticket) {

        try { // Call Web Service Operation

            //Get task manager location
            String taskManagerLocation = PropertyAccessor.getProperty(
                    PROPERTY_FILE, PROPERTY_TASK_LOCATION);

            //Get task id for SMS
            String smsTaskId = PropertyAccessor.getProperty(
                    PROPERTY_FILE, PROPERTY_SMS_TASK);

            if ((smsTaskId == null) || smsTaskId.isEmpty()) {
                log.error("SMS task is for escalation is not defined in properties.");
                return;
            }

            gov.hhs.fha.nhinc.common.task.TaskManagerPortType port = taskManagerService.getTaskManagerPortSoap11();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                taskManagerLocation);

            //Initialize WS operation arguments here
            StartTaskFromRuleRequestType startTaskFromRuleRequest = new StartTaskFromRuleRequestType();
            startTaskFromRuleRequest.setTaskID(smsTaskId);
            startTaskFromRuleRequest.setMessage(ticket.getDescription());
            DestinationContext dest = new DestinationContext();
            gov.hhs.fha.nhinc.common.task.ContactDetails contact = new gov.hhs.fha.nhinc.common.task.ContactDetails();
            contact.setProvider(provider.getProviderLdap());
            contact.setClinic(provider.getClinicId());
            contact.setOrganization(provider.getOrganizationId());
            contact.setRole(provider.getRole());
            contact.setLocation(provider.getLocation());
            dest.getProviders().add(contact);
            startTaskFromRuleRequest.setDestination(dest);

            //Update ticket history to note notification
            AlertService alertDBService = new AlertService();
            AlertAction action = new AlertAction();
            action.setUserId(provider.getProviderId());
            action.setUserName(provider.getProviderName());
            action.setUserProvider(Boolean.TRUE);
            action.setActionName(ActionConstants.ACTION_ALERT);
            action.setActionTimestamp(new Date());
            action.setMessage(MESSAGE_NOTIFICATION_SMS);
            action.setTicket(ticket);
            ticket.getActionHistory().add(action);
            alertDBService.saveTicket(ticket);

            //Process result here
            TaskManagerResponseType result = port.startTaskFromRule(startTaskFromRuleRequest);

            if ((result == null) || "-1".equals(result.getTicket())) {
                log.error("Error with SMS escalation. " + result.getDetail());
            }
            else {
                log.debug("Got SMS escalation ticket: " + result.getTicket());
            }

        } catch (Exception e) {
            log.error("Error handling SMS escalation.", e);
        }

    }

    /**
     * Retreive the provider id and name based on the passed parameters.  Use
     * ldap access as necessary.
     *
     * @param providerLdap
     * @param role
     * @param location
     * @return
     */
    private List<ContactInfo> getProviderIdAndName(String providerLdap, String role, String location)
            throws Exception {
        List<ContactInfo> results = new LinkedList<ContactInfo>();
        List<String> ldapSearches = new LinkedList<String>();

        //Check if we have role based provider or not
        if ((providerLdap == null) || providerLdap.isEmpty()) {

            //Find provider based on role, location
            ldapSearches = retrieveProviderLdaps(role, location);

            //Check if provider was found
            if (ldapSearches.isEmpty()) {
                String errMsg = "Provider not found for role: " + role + ", " + location + ".";
                log.error(errMsg);
                throw new Exception(errMsg);
            }
        }
        else {
            ldapSearches.add(providerLdap);
        }

        //Search LDAP for providerId
        for (String ldapSearch : ldapSearches) {
            ContactInfo result = ldapRetrieveContactInfo(ldapSearch);
            if (result != null) {
                results.add(result);
            }
            else {
                String errMsg = "Provider not found for: " + providerLdap + ", " + ldapSearch;
                log.error(errMsg);
                throw new Exception(errMsg);
            }
        }

        return results;
    }

    /**
     * Search the ldap roles to find the provider occupying that role.  The
     * return is a ldap query string that will find the provider object.
     *
     * @param role
     * @param location
     * @return
     */
    private List<String> retrieveProviderLdaps(String role, String location) {
        List<String> providers = new LinkedList<String>();

        //Search for role occupant
        RoleDAO roleDAO = LdapService.getRoleDAO();
        List<RoleDTO> roles = roleDAO.findLocationRole(location, role);
        if (!roles.isEmpty()
                && !roles.get(0).getRoleOccupants().isEmpty()) {
            providers = roles.get(0).getRoleOccupants();
        }

        return providers;
    }

    /**
     * Use LDAP to get provider Id, name, and finally return the ldap lookup
     * string that was used.
     *
     * @param provider ldap lookup string (or provider id)
     * @return
     */
    private ContactInfo ldapRetrieveContactInfo(String provider) {
        ContactInfo result = null;

        if ((provider == null) || provider.isEmpty()) {
            return null;
        }

        //If the provider field appears to be a uid, use it to search LDAP
        provider = provider.trim();
        if (provider.length() > 0) {
            boolean isNumber = true;
            int i = 0;
            if ((provider.charAt(i) == '-') && (provider.length() > 1)) {
                i++;
            }
            for (; i < provider.length(); i++) {
                if (!Character.isDigit(provider.charAt(i))) {
                    isNumber = false;
                    break;
                }
            }

            if (isNumber) {
                //Special case for system user
                if (SYSTEM_USER_ID.equals(provider)) {
                    result = new ContactInfo();
                    result.id = SYSTEM_USER_ID;
                    result.name = SYSTEM_USER_NAME;
                    result.ldap = "";
                    return result;
                }

                //Special case for patient directed alerts
                if (PATIENT_DIRECTED_USER_ID.equals(provider)) {
                    result = new ContactInfo();
                    result.id = PATIENT_DIRECTED_USER_ID;
                    result.name = PATIENT_DIRECTED_USER_NAME;
                    result.ldap = "";
                    return result;
                }

                //Otherwise create ldap lookup string for passed id
                provider = "employeeNumber=" + provider;
            }
        }

        //Search LDAP
        ContactDAO contactDAO = LdapService.getContactDAO();
        List<ContactDTO> contacts = contactDAO.findContact(provider);
        if (contacts.size() > 0) {
            //For providers, the IEN is in the employeeNumber
            result = new ContactInfo();
            result.id = contacts.get(0).getEmployeeNumber();
            result.name = contacts.get(0).getDisplayName();
            result.ldap = provider;
        }

        return result;
    }

    class ContactInfo {
        public String id;
        public String name;
        public String ldap;
    }
}
