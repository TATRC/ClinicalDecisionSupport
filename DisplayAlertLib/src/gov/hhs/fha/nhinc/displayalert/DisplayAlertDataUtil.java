/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.displayalert;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.alertservice.AlertUtil;
import gov.hhs.fha.nhinc.alertmanager.model.AlertAction;
import gov.hhs.fha.nhinc.alertmanager.model.AlertContact;
import gov.hhs.fha.nhinc.alertmanager.model.AlertStatus;
import gov.hhs.fha.nhinc.alertmanager.model.AlertTicket;
import gov.hhs.fha.nhinc.alertmanager.model.TicketQueryParams;
import gov.hhs.fha.nhinc.alertmanager.service.ActionConstants;
import gov.hhs.fha.nhinc.alertmanager.service.AlertService;
import gov.hhs.fha.nhinc.alertmanager.service.TicketConstants;
import gov.hhs.fha.nhinc.alertpayload.Action;
import gov.hhs.fha.nhinc.alertpayload.ActionParam;
import gov.hhs.fha.nhinc.alertpayload.Recommendation;
import gov.hhs.fha.nhinc.common.dda.DetailData;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetMessagesRequestType;
import gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType;
import gov.hhs.fha.nhinc.common.dda.NameValuesPair;
import gov.hhs.fha.nhinc.common.dda.ServiceError;
import gov.hhs.fha.nhinc.common.dda.SetMessageRequestType;
import gov.hhs.fha.nhinc.common.dda.SetMessageResponseType;
import gov.hhs.fha.nhinc.common.dda.SummaryData;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType;
import gov.hhs.fha.nhinc.common.task.AlertFact;
import gov.hhs.fha.nhinc.common.task.GetAlertFactsResponseType;
import gov.hhs.fha.nhinc.common.task.PatientContext;
import gov.hhs.fha.nhinc.common.task.RuleContext;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility to retrieve Alerts (both Alert and Message types).
 *
 * @author cmatser
 */
public class DisplayAlertDataUtil {

    /** Data Source name. */
    public static final String DATA_SOURCE_ALERTS = "MedAlerts";
    public static final String DATA_SOURCE_ALERTS_MOBILE = "MedAlerts - mobile";
    public static final String DATA_SOURCE_PT_ALERTS = "Patient Alerts";
    public static final String DATA_SOURCE_MSGS = "Messages";
    public static final String DATA_SOURCE_ALERT_FACTS = "Alert Facts";
    /** Provider id for patient directed alerts. */
    public static final String PATIENT_ALERTS_PROVIDER_ID = "0";
    /** Id for system user. */
    public static final String SYSTEM_USER_ID = "0";
    /** Status for new alerts. */
    public static final String ALERT_STATUS_NEW = "New";
    /** SetMessage Action Update. */
    public static final String ACTION_UPDATE = "Update";
    /** SetMessage - Starred Folder */
    public static final String FOLDER_STARRED = "Starred";
    /** SetMessage - Archive Status */
    public static final String ARCHIVE_STATUS = "ARCHIVED";
    /** Item names for name value pairs. */
    public static final String ITEM_PRIORITY = "Priority";
    public static final String ITEM_FOLDERS = "Folders";
    public static final String ITEM_STATUS = "Status";
    public static final String ITEM_PATIENT_UNIT_NUMBER = "Patient Unit Number";
    public static final String ITEM_PATIENT_SEX = "Patient Sex";
    public static final String ITEM_PATIENT_FMPSSN = "Patient FMP/SSN";
    public static final String ITEM_FACT_NCID = "Fact NCID";
    public static final String ITEM_FACT_NAME = "Fact Name";
    public static final String ITEM_FACT_VALUE = "Fact Value";
    public static final String ITEM_FACT_TYPE = "Fact Type";
    public static final String ITEM_ACTION_ID = "Action ID";
    public static final String ITEM_ACTION_TYPE = "Action Type";
    public static final String ITEM_RULE_ID = "Rule ID";
    public static final String ITEM_RULE_DESCRIPTION = "Rule Description";
    public static final String ITEM_RULE_NAME = "Rule Name";
    public static final String ITEM_UPDATE_REC_PREFIX = "update.";
    public static final String ITEM_UPDATE_REC_NAME = ".name";
    public static final String ITEM_UPDATE_REC_TIME = ".time";
    public static final String ITEM_UPDATE_REC_USER_ID = ".userId";
    public static final String ITEM_UPDATE_REC_USER_NAME = ".userName";
    public static final String ITEM_UPDATE_REC_USER_PROVIDER = ".userProvider";
    public static final String ITEM_UPDATE_REC_MESSAGE = ".message";
    /** Standard error code. */
    public static final Integer ERR_CODE = -1;
    /** Service error message. */
    public static final String ERR_MSG_ITEM_NOT_FOUND = "Item was not found.";
    /** Logging. */
    private static Log log = LogFactory.getLog(DisplayAlertDataUtil.class);

    /**
     * Update Inbox status.
     *
     * @param request
     * @return
     */
    public UpdateComponentInboxStatusResponseType updateComponentInboxStatus(String source, UpdateComponentInboxStatusRequestType request) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Retrieve detail data.
     *
     * @param request
     * @return
     */
    public GetComponentDetailDataResponseType getComponentDetailDataForUser(String source, GetComponentDetailDataForUserRequestType request) {
        GetComponentDetailDataResponseType response = new GetComponentDetailDataResponseType();

        log.debug("Retrieving " + source + " detail for ticket for user: " + request.getUserId());

        try {
            //Query based on alert ticket id
            AlertService service = new AlertService();
            TicketQueryParams query = new TicketQueryParams();
            query.setTicketUniqueId(request.getItemId());
            List<AlertTicket> tickets = service.ticketQuery(query);

            if ((tickets == null) || (tickets.size() == 0)) {
                log.debug("Cound not find " + source + " ticket: " + request.getItemId());
                throw new Exception(ERR_MSG_ITEM_NOT_FOUND);
            }

            //Poplulate detail data object
            GregorianCalendar cal = new GregorianCalendar();
            AlertTicket ticket = tickets.get(0);
            DetailData detailData = new DetailData();
            detailData.setAuthor(ticket.getAlertOriginator());
            detailData.setFrom(ticket.getAlertOriginator());
            detailData.setData(sanitizePayload(request.getUserId(), ticket.getPayload(), ticket));
            detailData.setDataSource(source);
            cal.setTime(ticket.getAlertTimestamp());
            detailData.setDateCreated(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
            detailData.setDescription(ticket.getDescription());
            detailData.setItemId(ticket.getTicketUniqueId());
            detailData.setPatient(ticket.getPatientName());

            //Object specific name/value pairs
            addNameValue(detailData.getItemValues(), ITEM_PRIORITY, ticket.getPriority());
            addNameValue(detailData.getItemValues(), ITEM_PATIENT_UNIT_NUMBER, ticket.getPatientUnitNumber());
            addNameValue(detailData.getItemValues(), ITEM_PATIENT_SEX, ticket.getPatientSex());
            addNameValue(detailData.getItemValues(), ITEM_PATIENT_FMPSSN, ticket.getPatientFMPSSN());
            addNameValue(detailData.getItemValues(), ITEM_FACT_NCID, ticket.getFactNCID());
            addNameValue(detailData.getItemValues(), ITEM_FACT_NAME, ticket.getFactName());
            addNameValue(detailData.getItemValues(), ITEM_FACT_VALUE, ticket.getFactValue());
            addNameValue(detailData.getItemValues(), ITEM_FACT_TYPE, ticket.getFactType());
            addNameValue(detailData.getItemValues(), ITEM_ACTION_ID, ticket.getActionId());
            addNameValue(detailData.getItemValues(), ITEM_ACTION_TYPE, ticket.getActionType());
            addNameValue(detailData.getItemValues(), ITEM_RULE_ID, ticket.getRuleId());
            addNameValue(detailData.getItemValues(), ITEM_RULE_DESCRIPTION, ticket.getRuleDesc());
            addNameValue(detailData.getItemValues(), ITEM_RULE_NAME, ticket.getRuleName());

            //Go through action history and add to name/value
            //  Also, check if alert is new for this user
            //  Also, hold onto last action performed by user or system user
            //  Also, hold onto last action
            int i = 1;
            boolean isNewAlert = true;
            AlertAction lastAction = null;
            AlertAction lastUserAction = null;
            for (AlertAction action : ticket.getActionHistory()) {
                addNameValue(detailData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_NAME, action.getActionName());
                cal.setTime(action.getActionTimestamp());
                addNameValue(detailData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_TIME, DatatypeFactory.newInstance().newXMLGregorianCalendar(cal).toString());
                addNameValue(detailData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_USER_ID, action.getUserId());
                addNameValue(detailData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_USER_NAME, action.getUserName());
                addNameValue(detailData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_USER_PROVIDER, action.getUserProvider().toString());
                addNameValue(detailData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_MESSAGE, action.getMessage());
                i++;

                //Check if alert is new for this user
                if (SYSTEM_USER_ID.equals(action.getUserId())
                        || ((request.getUserId() != null) && request.getUserId().equals(action.getUserId()))) {

                    if (ActionConstants.ACTION_READ.equals(action.getActionName())) {
                        isNewAlert = false;
                    }

                    lastUserAction = action;
                }

                //Set last action
                lastAction = action;
            }

            //Set appropriate status value
            String status = "";
            if (isNewAlert) {
                //The alert may be new to this user, but if it is closed and no further
                //  action can be done, then set the status to the last action
                if (AlertUtil.isTickedClosed(ticket)) {
                    status = lastAction.getActionName();
                } else {
                    status = ALERT_STATUS_NEW;
                }
            } else if (lastUserAction != null) {
                status = lastUserAction.getActionName();
            } else if (lastAction != null) {
                status = lastAction.getActionName();
            }
            addNameValue(detailData.getItemValues(), ITEM_STATUS, status);

            response.setDetailObject(detailData);
        } catch (Exception e) {
            log.error("Error retriving detail for alert ticket: " + request.getItemId(), e);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(e.getMessage());
            response.getErrorList().add(serviceError);
        }

        return response;
    }

    /**
     * Retreive summary objects for all alerts.
     *
     * @param request
     * @return
     */
    public GetComponentSummaryDataResponseType getComponentSummaryDataForUser(String source, GetComponentSummaryDataForUserRequestType request) {
        GetComponentSummaryDataResponseType response = new GetComponentSummaryDataResponseType();

        log.debug("Retrieving " + source + " summaries for user: " + request.getUserId());

        try {

            if (DATA_SOURCE_PT_ALERTS.equals(source)) {
                //If no patient id is passed, just return
                if ((request.getPatientId() == null) || request.getPatientId().isEmpty()) {
                    return response;
                }
            } else { //check for provider
                //If no provider id is passed, just return
                if ((request.getProviderId() == null) || request.getProviderId().isEmpty()) {
                    return response;
                }
            }

            //Find allTickets based on data source
            List<AlertTicket> tickets = findTickets(source, request.getPatientId(), request.getProviderId(), request.isArchive());
            if (tickets == null) {
                throw new Exception("Null ticket query result.");
            }

            log.debug("Found " + tickets.size() + " tickets found for provider: " + request.getProviderId());

            GregorianCalendar cal = new GregorianCalendar();
            List<Long> usedIds = new LinkedList<Long>();
            for (AlertTicket ticket : tickets) {

                //If the ticket has already been added, ignore
                if (usedIds.contains(ticket.getTicketId())) {
                    continue;
                }
                usedIds.add(ticket.getTicketId());

                //Poplulate summary data object
                SummaryData summaryData = new SummaryData();
                summaryData.setAuthor(ticket.getAlertOriginator());
                summaryData.setFrom(ticket.getAlertOriginator());
                summaryData.setDataSource(source);
                cal.setTime(ticket.getAlertTimestamp());
                summaryData.setDateCreated(
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
                summaryData.setDescription(ticket.getDescription());
                summaryData.setItemId(ticket.getTicketUniqueId());
                summaryData.setPatient(ticket.getPatientName());
                Set<AlertStatus> statuses = ticket.getStatus();
                for (AlertStatus status : statuses) {
                    if (status.getTicket().getTicketId().equals(ticket.getTicketId()));
                    if (status.isFlagged()) {
                        summaryData.setFolder("Starred");
                    } else {
                        summaryData.setFolder("");
                    }
                }

                //Object specific name/value pairs
                addNameValue(summaryData.getItemValues(), ITEM_PRIORITY, ticket.getPriority());
                addNameValue(summaryData.getItemValues(), ITEM_FOLDERS, summaryData.getFolder());

                //Go through action history and add to name/value
                //  Also, check if alert is new for this user
                //  Also, hold onto last action
                int i = 1;
                boolean isNewAlert = true;
                AlertAction lastAction = null;
                for (AlertAction action : ticket.getActionHistory()) {
                    //For mobile, we don't add actions
                    if (!DATA_SOURCE_ALERTS_MOBILE.equals(source)) {
                        addNameValue(summaryData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_NAME, action.getActionName());
                        cal.setTime(action.getActionTimestamp());
                        addNameValue(summaryData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_TIME, DatatypeFactory.newInstance().newXMLGregorianCalendar(cal).toString());
                        addNameValue(summaryData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_USER_ID, action.getUserId());
                        addNameValue(summaryData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_USER_NAME, action.getUserName());
                        addNameValue(summaryData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_USER_PROVIDER, action.getUserProvider().toString());
                        addNameValue(summaryData.getItemValues(), ITEM_UPDATE_REC_PREFIX + i + ITEM_UPDATE_REC_MESSAGE, action.getMessage());
                        i++;
                    }

                    //Check if alert is new for this user
                    if ((request.getUserId() != null)
                            && request.getUserId().equals(action.getUserId())
                            && ActionConstants.ACTION_READ.equals(action.getActionName())) {
                        isNewAlert = false;
                    }

                    //Set last action
                    lastAction = action;
                }

                //Set appropriate status value
                String status = "";
                if (isNewAlert) {
                    //The alert may be new to this user, but if it is closed and no further
                    //  action can be done, then set the status to the last action
                    if (AlertUtil.isTickedClosed(ticket)) {
                        status = lastAction.getActionName();
                    } else {
                        status = ALERT_STATUS_NEW;
                    }
                } else {
                    status = lastAction.getActionName();
                }
                addNameValue(summaryData.getItemValues(), ITEM_STATUS, status);

                //Check if we are only return new/needing action items
                if (request.isOnlyNew()) {
                    //Items of concern have actions that are still allowed
                    if (actionsAvailable(request.getUserId(), ticket.getPayload(), ticket)) {
                        response.getSummaryObjects().add(summaryData);
                    }
                } else {
                    response.getSummaryObjects().add(summaryData);
                }

            }

        } catch (Exception e) {
            log.error("Error retriving summary " + source + " for provider: " + request.getProviderId(), e);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(e.getMessage());
            response.getErrorList().add(serviceError);
        }

        return response;
    }

    public gov.hhs.fha.nhinc.common.task.GetAlertFactsResponseType getAlertFacts(gov.hhs.fha.nhinc.common.task.GetAlertFactsRequestType request) {
        GetAlertFactsResponseType response = new GetAlertFactsResponseType();

        log.debug("Retrieving alert facts for user: " + request.getUserId());

        try {

            //If no user id is passed, just return
            if ((request.getUserId() == null) || request.getUserId().isEmpty()) {
                throw new Exception("User id empty.");
            }

            //Find allTickets based on data source
            List<AlertTicket> tickets = null;
            if (request.isUserProvider()) {
                tickets = findTickets(DATA_SOURCE_ALERT_FACTS, null, request.getUserId(), null);
            } else {
                tickets = findTickets(DATA_SOURCE_ALERT_FACTS, request.getUserId(), null, null);
            }

            log.debug("Found " + tickets.size() + " tickets found for user: " + request.getUserId());

            GregorianCalendar cal = new GregorianCalendar();
            List<Long> usedIds = new LinkedList<Long>();
            for (AlertTicket ticket : tickets) {

                //If the ticket has already been added, ignore
                if (usedIds.contains(ticket.getTicketId())) {
                    continue;
                }
                usedIds.add(ticket.getTicketId());

                //Poplulate fact object
                AlertFact fact = new AlertFact();
                fact.setTicketId(ticket.getTicketUniqueId());
                cal.setTime(ticket.getAlertTimestamp());
                fact.setDateCreated(
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
                fact.setDescription(ticket.getDescription());
                fact.setPayload(ticket.getPayload());
                fact.setPriority(ticket.getPriority());
                PatientContext ptCtx = new PatientContext();
                ptCtx.setPatientName(ticket.getPatientName());
                ptCtx.setPatientFMPSSN(ticket.getPatientFMPSSN());
                ptCtx.setPatientSex(ticket.getPatientSex());
                ptCtx.setPatientUnitNumber(ticket.getPatientUnitNumber());
                fact.setPatient(ptCtx);
                RuleContext ruleCtx = new RuleContext();
                ruleCtx.setActionID(ticket.getActionId());
                ruleCtx.setActionType(ticket.getActionType());
                ruleCtx.setFactNCID(ticket.getFactNCID());
                ruleCtx.setFactName(ticket.getFactName());
                ruleCtx.setFactType(ticket.getFactType());
                ruleCtx.setFactValue(ticket.getFactValue());
                ruleCtx.setRuleDesc(ticket.getRuleDesc());
                ruleCtx.setRuleID(ticket.getRuleId());
                ruleCtx.setRuleName(ticket.getRuleName());
                fact.setRule(ruleCtx);

                //Go through action history
                //   deterimine status
                boolean isNewAlert = true;
                AlertAction lastAction = null;
                for (AlertAction action : ticket.getActionHistory()) {

                    //Check if alert is new for this user
                    if ((request.getUserId() != null)
                            && request.getUserId().equals(action.getUserId())
                            && ActionConstants.ACTION_READ.equals(action.getActionName())) {
                        isNewAlert = false;
                    }

                    //Set last action
                    lastAction = action;
                }

                //Set appropriate status value
                String status = "";
                if (isNewAlert) {
                    //The alert may be new to this user, but if it is closed and no further
                    //  action can be done, then set the status to the last action
                    if (AlertUtil.isTickedClosed(ticket)) {
                        status = lastAction.getActionName();
                    } else {
                        status = ALERT_STATUS_NEW;
                    }
                } else {
                    status = lastAction.getActionName();
                }
                fact.setStatus(status);

                //Add to list of facts
                response.getFactObjects().add(fact);
            }

        } catch (Exception e) {
            log.error("Error retriving alert facts for user: " + request.getUserId(), e);
            response.setStatusCode(ERR_CODE);
            response.setStatusDetail(e.getMessage());
        }

        return response;
    }

    /**
     * Find allTickets based on source type.
     * <br>
     * MedAlerts - Find alerts for provider or updated by provider.
     *             If patientId is passed, return only alerts regarding that patient.
     *             otherwise, return all.
     * Pt Alerts - Find alerts specifically directed to the passed patientId.
     *             This is a special case where providerId is zero.
     * Messages  - Find messages for provider or updated by provider.
     *             If patientId is passed, return only messages regarding that patient.
     *             otherwise, return all.
     * @param source
     * @param request
     * @return
     */
    private List<AlertTicket> findTickets(String source, String patientId, String providerId, Boolean archive) {

        //Query based on provider id and patient id, look for both alerts for a provider
        //  or updated by a provider
        AlertService service = new AlertService();
        TicketQueryParams query1 = new TicketQueryParams();

        //Set provider
        if ((providerId != null) && !providerId.isEmpty()) {
            query1.setActionUserId(providerId);
        }

        //Set patient
        if ((patientId != null) && !patientId.isEmpty()) {
            query1.setPatientId(patientId);
        }

        //Set Archive
        if (archive != null) {
            query1.setArchive(archive.booleanValue());
        }

        //Set ticket type
        if (DATA_SOURCE_ALERTS.equals(source)
                || DATA_SOURCE_PT_ALERTS.equals(source)
                || DATA_SOURCE_ALERTS_MOBILE.equals(source)
                || DATA_SOURCE_ALERT_FACTS.equals(source)) {
            query1.setType(TicketConstants.TICKET_TYPE_ALERT);
        } else if (DATA_SOURCE_MSGS.equals(source)) {
            query1.setType(TicketConstants.TICKET_TYPE_MESSAGE);
        }

        //Get allTickets
        List<AlertTicket> tickets = service.ticketQuery(query1);

        return tickets;
    }

    public GetMessagesResponseType getMessages(String DATA_SOURCE_ALERTS, GetMessagesRequestType request) {
        GetMessagesResponseType resp = new GetMessagesResponseType();
        
        log.debug("getMessages");

        try {
            AlertService service = new AlertService();
            TicketQueryParams params = new TicketQueryParams();
            List<AlertContact> contactList = service.getContactsByProvider(request.getProviderId());

            GetMessagesResponseType.GetMessageResponse response =
                    new GetMessagesResponseType.GetMessageResponse();
            for (AlertContact contact : contactList) {
                AlertTicket ticket = contact.getTicket();
                if (ticket.getPatientUnitNumber().equals(request.getPatientId())) {
                    response.setMessageType("Alerts");
                    response.setDescription(ticket.getDescription());
                    response.setFrom(contact.getProviderName());

                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(ticket.getAlertTimestamp());
                    XMLGregorianCalendar xgcDate =
                            DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                    response.setMessageDate(xgcDate);

                    response.setMessageId(ticket.getTicketId().toString());

                    for (AlertStatus status : ticket.getStatus()) {
                        if (status.isFlagged()) {
                            response.getFolders().add("STARRED");
                        }
                    }

                    response.setPriority(ticket.getPriority());

                    response.setMessageStatus(ticket.getActionType()); // ?????

                    response.setSuccessStatus(true);
                    response.setTitle(ticket.getAlertOriginator());
                    response.setTasksComplete(ticket.getActionHistory().size());
                    response.setTasksCount(ticket.getActionHistory().size());
                }
            }

            resp.getGetMessageResponse().add(response);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    private void addNameValue(List<NameValuesPair> pairList, String name, String value) {
        NameValuesPair nameVal = new NameValuesPair();
        nameVal.setName(name);
        nameVal.getValues().add(value);
        pairList.add(nameVal);

        return;
    }

    /**
     * Parses the actions in the payload to ensure the only actions returned
     * are the ones that are allowed.
     * 
     * @param userId
     * @param payload
     * @param alertTicket
     * @return
     */
    private String sanitizePayload(String userId, String payload, AlertTicket alertTicket) {
        Recommendation alertPayload = null;
        String retPayload = payload;

        //Init xml parser
        XStream xstream = new XStream();
        xstream.alias("Recommendation", Recommendation.class);
        xstream.alias("Action", Action.class);
        xstream.alias("ActionParam", ActionParam.class);

        //Check if payload is a recommendation
        try {
            alertPayload = (Recommendation) xstream.fromXML(payload);

            if (alertPayload != null) {
                //Get allowed actions
                TreeSet<Action> allowedActions = new TreeSet<Action>();
                for (Action a : alertPayload.getActions()) {
                    if (AlertUtil.isActionAllowed(userId, a.getName(), alertTicket)) {
                        allowedActions.add(a);
                    }

                }

                //Set allowed actions
                alertPayload.getActions().clear();
                alertPayload.getActions().addAll(allowedActions);

                //Convert back to stream
                retPayload = xstream.toXML(alertPayload);
            }

        } catch (Throwable t) {
            //ignore
        }

        return retPayload;
    }

    /**
     * Checks the payload and sees if any of the configured actions are still available.
     *
     * @param userId
     * @param payload
     * @param alertTicket
     * @return
     */
    private boolean actionsAvailable(String userId, String payload, AlertTicket alertTicket) {
        boolean actionsAvailable = false;

        //Init xml parser
        XStream xstream = new XStream();
        xstream.alias("Recommendation", Recommendation.class);
        xstream.alias("Action", Action.class);
        xstream.alias("ActionParam", ActionParam.class);

        //Check if payload is a recommendation
        try {
            Recommendation alertPayload = (Recommendation) xstream.fromXML(payload);

            if (alertPayload != null) {
                //Get actions and see if any are allowed
                for (Action a : alertPayload.getActions()) {
                    if (AlertUtil.isActionAllowed(userId, a.getName(), alertTicket)) {
                        actionsAvailable = true;
                    }

                }
            }

        } catch (Throwable t) {
            //ignore
        }

        //If there are no configured actions available, let's see if the
        //  user can still peform a read action on the item
        if (!actionsAvailable
                && AlertUtil.isActionAllowed(userId, ActionConstants.ACTION_READ, alertTicket)) {
            actionsAvailable = true;
        }

        return actionsAvailable;
    }

    public SetMessageResponseType setMessage(String DATA_SOURCE_ALERTS, SetMessageRequestType request) {
        SetMessageResponseType response = new SetMessageResponseType();

        log.debug("Set Message");

        try {

            AlertService service = new AlertService();
            TicketQueryParams query = new TicketQueryParams();
            AlertTicket ticket = new AlertTicket();

            //Set UserID
            if ((request.getUserId() != null) && !request.getUserId().isEmpty()) {
                log.debug("UserId:" + request.getUserId());
                query.setActionUserId(request.getUserId());
            }

            //Set MessageID
            if ((request.getMessageId() != null) && !request.getMessageId().isEmpty()) {
                log.debug("Message ID:" + request.getMessageId());
                query.setTicketUniqueId(request.getMessageId());
            }

            //Set Action
            if ((request.getAction() != null) && !request.getAction().isEmpty()) {
                log.debug("Action:" + request.getAction());
                if (request.getAction().equalsIgnoreCase(ACTION_UPDATE)) {
                    if (((request.getUserId() != null) && !request.getUserId().isEmpty())
                            && ((request.getMessageId() != null) && !request.getMessageId().isEmpty())) {
                        log.debug("Retrieving Tickets with the given UserID and TicketID : " + query.getTicketUniqueId() + "," + query.getActionUserId());
                        ticket = service.getTicket(Long.valueOf(request.getMessageId()));
                        log.debug("Found " + ticket + " tickets found for user: " + request.getUserId());
                    }
                    boolean foundStatus = false;
                    boolean foundFolder = false;
                    if (null != ticket.getStatus()) {
                        for (AlertStatus status : ticket.getStatus()) {

                            if (status.getUserId().equals(request.getUserId())) {
                                if ((request.getFolder() != null) && (!request.getFolder().isEmpty())) {
                                    foundFolder = true;
                                    log.debug("Folder:" + request.getFolder());
                                    if (null == request.getFolder().get(0) || request.getFolder().get(0).trim().isEmpty()) {
                                        status.setFlagged(false);
                                    } else if (null != request.getFolder().get(0) && request.getFolder().get(0).equalsIgnoreCase(FOLDER_STARRED)) {
                                        status.setFlagged(true);
                                    }
                                }
                                // Set Status of the Message
                                if (request.getStatus() != null) {
                                    if (!request.getStatus().isEmpty()) {
                                        foundStatus = true;
                                        log.debug("Status:" + request.getStatus());

                                        if (request.getStatus().equalsIgnoreCase(ARCHIVE_STATUS)) {
                                            status.setArchive(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (((request.getStatus() != null && !request.getStatus().isEmpty()) && !foundStatus)
                            || (request.getFolder() != null && !foundFolder)) {
                        log.error("No row in Status Table for the given UserID and TicketID " + request.getUserId() + "," + request.getMessageId());
                        response.setMessage("No row in Status Table for the given UserID and TicketID");
                        response.setSuccessStatus(false);
                    }
                    if (foundFolder || foundStatus) {
                        service.saveTicket(ticket);
                    }
                }
            }

            response.setMessage("Message Updated Successfully");
            response.setSuccessStatus(true);
        } catch (Exception e) {
            log.error("Error setting the message: " + request.getUserId(), e);
            response.setMessage(e.getMessage());
            response.setSuccessStatus(false);
        }
        return response;
    }
}
