/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import gov.hhs.fha.nhinc.adapter.fact.LabOrderFactType;
import gov.hhs.fha.nhinc.adapter.fact.SlotFactType;
import gov.hhs.fha.nhinc.common.task.DestinationContext;
import gov.hhs.fha.nhinc.common.task.PatientContext;
import gov.hhs.fha.nhinc.common.task.RuleContext;
import gov.hhs.fha.nhinc.common.task.TaskManagerResponseType;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import java.io.StringWriter;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Task Manager notifies individuals or another process of an event.
 *
 * @author cmatser
 */
public class TaskManagerImpl {

    /** Logging. */
    private static Log log = LogFactory.getLog(TaskManagerImpl.class);

    /** TaskManager property file. */
    public static final String TASKMANAGER_PROPERTY_FILE = "taskmanager";

    /** TaskManager queue factory. */
    public static final String PROPERTY_TASK_QUEUE_FACTORY = "taskQueueFactory";

    /** TaskManager queue property. */
    public static final String PROPERTY_TASK_QUEUE = "taskQueue";

    /** Message: success */
    public static final String TASK_MESSAGE_SUCCESS = "Success";

    /** Message: failure */
    public static final String TASK_MESSAGE_FAILURE = "Failure";

    /** Message: failure id */
    public static final String TASK_MESSAGE_FAILURE_ID = "-1";

    /**
     * This is a non-blocking call that starts the notification process.
     * A message is sent to a JMS Queue so that the client gets an
     * immediate response that the notification is taking place.  Task processing
     * is initiated by the queue listener.
     * 
     * @param request - notification information
     * @return notification response
     */
    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType startTaskFromRule(gov.hhs.fha.nhinc.common.task.StartTaskFromRuleRequestType request) {
        TaskManagerResponseType response = new TaskManagerResponseType();

        //Get request parts
        DestinationContext destCtx = request.getDestination();
        if (destCtx == null) {
            destCtx = new DestinationContext();
        }
        PatientContext ptCtx = request.getPatient();
        if (ptCtx == null) {
            ptCtx = new PatientContext();
        }
        RuleContext ruleCtx = request.getRule();
        if (ruleCtx == null) {
            ruleCtx = new RuleContext();
        }

        //Create message
        TaskRuleMessage ruleMessage = new TaskRuleMessage();
        ruleMessage.setTaskID(request.getTaskID());
        ruleMessage.setSubject(request.getSubject());
        ruleMessage.setMessage(request.getMessage());
        ruleMessage.setPriority(request.getPriority());
        ruleMessage.setEscalationMinutes(destCtx.getEscalationMinutes());
        if (destCtx.getDeliveryDate() != null) {
            ruleMessage.setDeliveryDate(destCtx.getDeliveryDate().toGregorianCalendar().getTime());
        }
        if (destCtx.getCompletionDate() != null) {
            ruleMessage.setCompletionDate(destCtx.getCompletionDate().toGregorianCalendar().getTime());
        }
        ruleMessage.setPatientUnitNumber(ptCtx.getPatientUnitNumber());
        ruleMessage.setPatientName(ptCtx.getPatientName());
        ruleMessage.setPatientSex(ptCtx.getPatientSex());
        ruleMessage.setPatientFMPSSN(ptCtx.getPatientFMPSSN());
        ruleMessage.setFactNCID(ruleCtx.getFactNCID());
        ruleMessage.setFactName(ruleCtx.getFactName());
        ruleMessage.setFactValue(ruleCtx.getFactValue());
        ruleMessage.setFactType(ruleCtx.getFactType());
        ruleMessage.setActionID(ruleCtx.getActionID());
        ruleMessage.setActionType(ruleCtx.getActionType());
        ruleMessage.setRuleID(ruleCtx.getRuleID());
        ruleMessage.setRuleDesc(ruleCtx.getRuleDesc());
        ruleMessage.setRuleName(ruleCtx.getRuleName());

        //Add providers
        for (gov.hhs.fha.nhinc.common.task.ContactDetails requestContact : destCtx.getProviders()) {
            TaskContact contact = new TaskContact();
            contact.setMethod(requestContact.getMethod());
            contact.setProvider(requestContact.getProvider());
            contact.setOrganization(requestContact.getOrganization());
            contact.setClinic(requestContact.getClinic());
            contact.setRole(requestContact.getRole());
            contact.setLocation(requestContact.getLocation());
            ruleMessage.getProviders().add(contact);
        }

        //Add escalation providers
        for (gov.hhs.fha.nhinc.common.task.ContactDetails requestContact : destCtx.getEscalationProviders()) {
            TaskContact contact = new TaskContact();
            contact.setMethod(requestContact.getMethod());
            contact.setProvider(requestContact.getProvider());
            contact.setOrganization(requestContact.getOrganization());
            contact.setClinic(requestContact.getClinic());
            contact.setRole(requestContact.getRole());
            contact.setLocation(requestContact.getLocation());
            ruleMessage.getEscalationProviders().add(contact);
        }

        //Send message
        QueueResponse result = queueMessage(ruleMessage);

        //Set response info
        response.setTicket(result.ticket);
        response.setDetail(result.detail);

        return response;
    }

    /**
     * This is a non-blocking call that starts the mail process.
     * A message is sent to a JMS Queue so that the client gets an
     * immediate response that the mail is taking place.  Task processing
     * is initiated by the queue listener.
     *
     * @param request - notification information
     * @return notification response
     */
    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType sendMailTask(gov.hhs.fha.nhinc.common.task.SendMailTaskRequestType request) {
        TaskManagerResponseType response = new TaskManagerResponseType();

        //Create message
        TaskMailMessage message = new TaskMailMessage();
        message.setTaskID(request.getTaskID());
        message.setFromUser(request.getFromUser());
        message.setFromUserProvider(request.isFromUserProvider());
        message.setToUser(request.getToUser());
        message.setToUserProvider(request.isToUserProvider());
        message.setSubject(request.getSubject());
        message.setMessage(request.getMessage());

        //Send message
        QueueResponse result = queueMessage(message);

        //Set response info
        response.setTicket(result.ticket);
        response.setDetail(result.detail);

        return response;
    }

    /**
     * Register a patient in the disease registry.
     * 
     * @param registerPersonDiseaseRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType registerPersonDisease(gov.hhs.fha.nhinc.common.task.RegisterPersonDiseaseRequestType request) {
        TaskManagerResponseType response = new TaskManagerResponseType();

        //Create message
        DiseaseRegistryMessage message = new DiseaseRegistryMessage();
        message.setActionRegister(true);
        message.setTaskID(request.getTaskID());
        message.setDiseaseType(request.getDiseaseType());
        message.setPatientId(request.getPatientId());
        message.setPatientName(request.getPatientName());
        if (request.getPatientDOB() != null) {
            message.setPatientDOB(request.getPatientDOB().toGregorianCalendar());
        }

        //Send message
        QueueResponse result = queueMessage(message);

        //Set response info
        response.setTicket(result.ticket);
        response.setDetail(result.detail);

        return response;
    }

    /**
     * Unregister a patient from the disease registry.
     *
     * @param unRegisterPersonDiseaseRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType unRegisterPersonDisease(gov.hhs.fha.nhinc.common.task.UnRegisterPersonDiseaseRequestType request) {
        TaskManagerResponseType response = new TaskManagerResponseType();

        //Create message
        DiseaseRegistryMessage message = new DiseaseRegistryMessage();
        message.setActionRegister(false);
        message.setTaskID(request.getTaskID());
        message.setDiseaseType(request.getDiseaseType());
        message.setPatientId(request.getPatientId());

        //Send message
        QueueResponse result = queueMessage(message);

        //Set response info
        response.setTicket(result.ticket);
        response.setDetail(result.detail);

        return response;
    }


    /**
     * Creates a lab order.
     * 
     * @param createLabOrderRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType createLabOrder(gov.hhs.fha.nhinc.common.task.CreateLabOrderRequestType request) {
        TaskManagerResponseType response = new TaskManagerResponseType();

        try {
            //Serialize lab order
            JAXBContext context = JAXBContext.newInstance(LabOrderFactType.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(new JAXBElement(new QName("uri","local"),
                LabOrderFactType.class, request.getLabOrderFact()), sw);

            //Create message
            LabOrderMessage message = new LabOrderMessage();
            message.setTaskID(request.getTaskID());
            message.setLabOrder(sw.toString());

            //Send message
            QueueResponse result = queueMessage(message);

            //Set response info
            response.setTicket(result.ticket);
            response.setDetail(result.detail);
        }
        catch (Exception e) {
            String msg = e.getMessage();
            if ((msg == null) || msg.isEmpty()) {
                msg = "Error handling lab order message.";
            }
            log.error("Error handling lab order message.", e);
            response.setTicket(TASK_MESSAGE_FAILURE_ID);
            response.setDetail(msg);
        }

        return response;
    }


    /**
     * Schedule appointment.
     * 
     * @param scheduleApptRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType scheduleAppt(gov.hhs.fha.nhinc.common.task.ScheduleApptRequestType request) {
        TaskManagerResponseType response = new TaskManagerResponseType();

        try {
            //Serialize lab order
            JAXBContext context = JAXBContext.newInstance(SlotFactType.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(new JAXBElement(new QName("uri","local"),
                SlotFactType.class, request.getSlotRequest()), sw);

            //Create message
            SlotRequestMessage message = new SlotRequestMessage();
            message.setTaskID(request.getTaskID());
            message.setSlotRequest(sw.toString());

            //Send message
            QueueResponse result = queueMessage(message);

            //Set response info
            response.setTicket(result.ticket);
            response.setDetail(result.detail);
        }
        catch (Exception e) {
            String msg = e.getMessage();
            if ((msg == null) || msg.isEmpty()) {
                msg = "Error handling schedule appointment message.";
            }
            log.error("Error handling schedule appointment message.", e);
            response.setTicket(TASK_MESSAGE_FAILURE_ID);
            response.setDetail(msg);
        }

        return response;
    }


    /**
     * Queue the message to the task handler.
     *
     * @param msgObject
     * @return
     */
    private QueueResponse queueMessage(java.io.Serializable msgObject) {
        QueueResponse response = new QueueResponse();
        String taskQ = null;
        QueueConnection queueConnection = null;

        try {
            //Get task queue name & queue factory
            taskQ = PropertyAccessor.getProperty(TASKMANAGER_PROPERTY_FILE, PROPERTY_TASK_QUEUE);
            String taskQFactory = PropertyAccessor.getProperty(TASKMANAGER_PROPERTY_FILE, PROPERTY_TASK_QUEUE_FACTORY);

            //Get queue connection
            Context jndiContext = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory)
                jndiContext.lookup(taskQFactory);
            Queue queue = (Queue) jndiContext.lookup(taskQ);

            //Create connection session
            queueConnection = queueConnectionFactory.createQueueConnection();
            QueueSession queueSession = queueConnection.createQueueSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            QueueSender queueSender = queueSession.createSender(queue);

            //Create message
            ObjectMessage message = queueSession.createObjectMessage(msgObject);

            //Send message
            queueSender.send(message);

            //Set response info
            response.ticket = message.getJMSMessageID();
            response.detail = TASK_MESSAGE_SUCCESS;
        }
        catch (PropertyAccessException pae) {
            String msg = TASK_MESSAGE_FAILURE + ": error accessing task properties in file:"
                    + TASKMANAGER_PROPERTY_FILE + ".";
            log.error(msg, pae);
            response.ticket = TASK_MESSAGE_FAILURE_ID;
            response.detail = msg;
        }
        catch (NamingException ne) {
            String msg = TASK_MESSAGE_FAILURE + ": error creating connection to queue: " + taskQ + ".";
            log.error(msg, ne);
            response.ticket = TASK_MESSAGE_FAILURE_ID;
            response.detail = msg;
        }
        catch (JMSException jmse) {
            String msg = TASK_MESSAGE_FAILURE + ": error occurred trying to send notificaiton to task queue: "
                    + taskQ + ".";
            log.error(msg, jmse);
            response.ticket = TASK_MESSAGE_FAILURE_ID;
            response.detail = msg;
        }
        finally {
            //Close queue
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {}
            }
        }

        return response;
    }

    class QueueResponse {
        String ticket;
        String detail;
    }
}
