package gov.hhs.fha.nhinc.kmr;

import com.thoughtworks.xstream.XStream;

import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.PersonFactType;
import gov.hhs.fha.nhinc.adapter.fact.SlotFactType;
import gov.hhs.fha.nhinc.adapter.fact.SlotRequestType;
import gov.hhs.fha.nhinc.alertpayload.Action;
import gov.hhs.fha.nhinc.alertpayload.ActionParam;
import gov.hhs.fha.nhinc.alertpayload.PayloadMeta;
import gov.hhs.fha.nhinc.alertpayload.Recommendation;
import gov.hhs.fha.nhinc.common.task.ContactDetails;
import gov.hhs.fha.nhinc.common.task.DestinationContext;
import gov.hhs.fha.nhinc.common.task.PatientContext;
import gov.hhs.fha.nhinc.common.task.RuleContext;
import gov.hhs.fha.nhinc.common.task.ScheduleApptRequestType;
import gov.hhs.fha.nhinc.common.task.SendMailTaskRequestType;
import gov.hhs.fha.nhinc.common.task.StartTaskFromRuleRequestType;
import gov.hhs.fha.nhinc.common.task.RegisterPersonDiseaseRequestType;
import gov.hhs.fha.nhinc.common.task.UnRegisterPersonDiseaseRequestType;
import gov.hhs.fha.nhinc.common.task.TaskManagerResponseType;
import gov.hhs.fha.nhinc.kmr.util.DateUtil;
import gov.hhs.fha.nhinc.taskmanager.TaskManager;
import gov.hhs.fha.nhinc.taskmanager.TaskManagerPortType;

import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Steven Clark
 */
public class TaskManagerHelper {

   private static Log log = LogFactory.getLog(TaskManagerHelper.class);
   private String serviceURL;
   private PersonFactType patient;

   /**
    * default-no argument constructor required for Drools
    */
   public TaskManagerHelper() {
      // use defaults
      this.serviceURL = "http://nhinint01.asu.edu:8080/TaskManager/TaskManagerService";

      System.out.println("Instantiated with no arguments");
   }

   /**
    * Constructor with patient demographic information and task manager service endpoint
    *
    * @param person - PersonFactType object containing patient demographic information
    * @param serviceURL - String containing the task manager service endpoint
    */
   public TaskManagerHelper(PersonFactType patient, String serviceURL) {
      if (serviceURL != null && !serviceURL.isEmpty()) {
         this.serviceURL = serviceURL;
         System.out.println("Instantiated with default serviceURL: " + serviceURL);
      } else {
         // use defaults
         this.serviceURL = "http://nhinint01.asu.edu:8080/TaskManager/TaskManagerService";
         System.out.println("Instantiated with default serviceURL: " + this.serviceURL);
      }

      this.patient = patient;
   }

   /**
    * Set the patient information
    *
    * @param patient - PersonFactType object containing patient demographic information
    */
   public void setPatient(PersonFactType patient) {
      if (patient != null) {
         this.patient = patient;
      }
   }

   /**
    * Set the task manager service URL
    *
    * @param serviceURL - String containing the task manager service endpoint
    */
   public void setServiceURL(String serviceURL) {
      if (serviceURL != null && !serviceURL.isEmpty()) {
         this.serviceURL = serviceURL;
      }
   }

   /**
    * @return the patient name
    */
   public String getPatientName() {
      String patientName = null;
      if (patient != null) {
         patientName = patient.getLegalName().getFirstName() + " " + patient.getLegalName().getFamilyName();
      }
      return patientName;
   }

   /**
    * Send Alert request message to the task manager
    *
    * @param subject - String containing subject line or description
    * @param priority - String containing priority
    * @param recipientList - Delimited string containing recipient URLs
    * @param escalationList - Delimited string containing escalation recipient URLs
    * @param escalationMinutes - Minutes of inaction before escalation occurs
    * @param escalationMethod - Alert or SMS
    * @param payload - Alert Payload
    * @param valueList - Delimited string of values to insert into payload
    */
   public void sendAlert(String subject, String priority, String recipientList, String escalationList, int escalationMinutes, String escalationMethod, String payload, String valueList) {
      DestinationContext destination = null;
      try {
         if (!recipientList.isEmpty()) {
            destination = new DestinationContext();
            ContactDetails contact = new ContactDetails();
            contact.setProvider(recipientList);
            destination.getProviders().add(contact);
            ContactDetails escalationContact = new ContactDetails();

            if (!recipientList.isEmpty()) {
               escalationContact.setMethod(escalationMethod);
               escalationContact.setProvider(escalationList);
               destination.getEscalationProviders().add(escalationContact);
               destination.setEscalationMinutes(escalationMinutes);
            }
         }
         Recommendation recommendation = new Recommendation();
         recommendation.setBody(payload);
         PayloadMeta meta = new PayloadMeta();
         meta.setType("MedAlert");
         recommendation.setMetadata(meta);
         Action action = new Action();
         action.setActionId("aid1");
         action.setName("Accept");
         recommendation.getActions().add(action);
         sendAlert(subject, priority, destination, recommendation, null);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   /**
    * Send Alert request message to the task manager
    *
    * @param subject - String containing subject line or description
    * @param priority - String containing priority
    * @param destination - DestinationContext object containing destination and escalation information
    * @param recommendation - Recommendation object containing recommended actions and parameters
    * @param rule - RuleContext object containing rule and action information
    */
   public void sendAlert(String subject, String priority, DestinationContext destination, Recommendation recommendation, RuleContext rule) {
      TaskManagerResponseType res = null;
      try {
         StartTaskFromRuleRequestType request = new StartTaskFromRuleRequestType();
         request.setTaskID("11");
         request.setSubject(subject);
         request.setPriority(priority);
         request.setDestination(destination);

         if (patient != null) {
            PatientContext patContext = new PatientContext();
            patContext.setPatientUnitNumber(patient.getPrimaryKey());
            patContext.setPatientSex(patient.getGender().getLabel());
            patContext.setPatientName(patient.getLegalName().getFirstName() + " " + patient.getLegalName().getFamilyName());
            request.setPatient(patContext);
         }

         XStream xstream = new XStream();
         xstream.alias("Recommendation", Recommendation.class);
         xstream.alias("Action", Action.class);
         xstream.alias("ActionParam", ActionParam.class);

         request.setMessage(xstream.toXML(recommendation));
         System.out.println("sendAlert: Task manager recommendation payload = " + request.getMessage());
         if (rule != null) {
            request.setRule(rule);
         }

         TaskManager service = new TaskManager();
         TaskManagerPortType port = service.getTaskManagerPortSoap11();
         System.out.println("sendAlert: serviceURL=" + serviceURL);
         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
         res = port.startTaskFromRule(request);
         System.out.println("sendAlert: Task manager alert response = " + res.getTicket());
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   /**
    * Send SMS request message to the task manager
    *
    * @param message - String containing text to send
    * @param destination - DestinationContext object containing destination and escalation information
    * @param rule - RuleContext object containing rule and action information
    */
   public void sendSMSRequest(String message, DestinationContext destination, RuleContext rule) {
      TaskManagerResponseType res = null;
      try {
         StartTaskFromRuleRequestType request = new StartTaskFromRuleRequestType();
         request.setTaskID("13");
         request.setDestination(destination);

         if (patient != null) {
            PatientContext patContext = new PatientContext();
            patContext.setPatientUnitNumber(patient.getPrimaryKey());
            patContext.setPatientSex(patient.getGender().getLabel());
            patContext.setPatientName(patient.getLegalName().getFirstName() + " " + patient.getLegalName().getFamilyName());
            request.setPatient(patContext);
         }

         request.setMessage(message);
         System.out.println("Task manager sms message = " + request.getMessage());

         if (rule != null) {
            request.setRule(rule);
         }

         TaskManager service = new TaskManager();
         TaskManagerPortType port = service.getTaskManagerPortSoap11();
         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
         res = port.startTaskFromRule(request);
         System.out.println("Task manager sms response = " + res.getTicket());
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   /**
    * Send Email request message to the task manager
    *
    * @param recipient - String containing sender ID
    * @param subject - String containing subject of email
    * @param message - String containing text to send
    * @param providerFlag - boolean Flag indicating if recipient is a provider
    */
   public void sendEmailRequest(String recipient, String subject, String message, boolean providerFlag) {
      TaskManagerResponseType res = null;
      try {
         SendMailTaskRequestType request = new SendMailTaskRequestType();
         request.setTaskID("17");
         request.setToUser(recipient);
         request.setToUserProvider(providerFlag);
         request.setSubject(subject);
         request.setMessage(message);

         System.out.println("Task manager email message = " + request.getMessage());

         TaskManager service = new TaskManager();
         TaskManagerPortType port = service.getTaskManagerPortSoap11();
         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
         res = port.sendMailTask(request);
         System.out.println("Task manager email response = " + res.getTicket());
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   /**
    * Send ScheduleAppt request to the task manager
    *
    * @param type - String containing appointment type
    * @param complaint - String containing chief complaint
    */
   public void scheduleAppt(String type, String complaint) {
      TaskManagerResponseType res = null;
      try {
         ScheduleApptRequestType request = new ScheduleApptRequestType();
         request.setTaskID("20");

         //SlotRequestType slotRequest = new SlotRequestType();
         SlotFactType slotRequest = new SlotFactType();

         if (patient != null) {
            //slotRequest.setPatientId(patient.getId().get(0));
            //slotRequest.setPatient(patient.getLegalName());
         }
         CodeLabelPair codedApptType = new CodeLabelPair();
         codedApptType.setCode(type);
         
         slotRequest.setCodedAppointmentType(codedApptType);
         //slotRequest.setChiefComplaint(complaint);

         request.setSlotRequest(slotRequest);
         //System.out.println("Task manager appt request = " + slotRequest.getChiefComplaint());

         TaskManager service = new TaskManager();
         TaskManagerPortType port = service.getTaskManagerPortSoap11();
         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
         res = port.scheduleAppt(request);
         System.out.println("Task manager email response = " + res.getTicket());
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   /**
    * Send registerPersonDisease message to the task manager
    *
    * @param String disease
    */
   public void registerPersonDisease(String disease) {
      TaskManagerResponseType res = null;
      try {
         RegisterPersonDiseaseRequestType request = new RegisterPersonDiseaseRequestType();
         request.setTaskID("18");
         if (patient != null) {
            request.setPatientId(patient.getPrimaryKey());
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(DateUtil.XMLGregorianCalendarToDate(patient.getDateOfBirth()));
            request.setPatientDOB(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
            request.setPatientName(patient.getLegalName().getFirstName() + " " + patient.getLegalName().getFamilyName());
         }
         request.setDiseaseType(disease);
         System.out.println("Task manager registering disease: " + request.getDiseaseType() + " for " + request.getPatientName());

         TaskManager service = new TaskManager();
         TaskManagerPortType port = service.getTaskManagerPortSoap11();
         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
         res = port.registerPersonDisease(request);
         System.out.println("Task manager sms response = " + res.getTicket());
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   /**
    * Send unRegisterPersonDisease message to the task manager
    *
    * @param String patientId
    * @param String disease
    */
   public void unRegisterPersonDisease(String disease) {
      TaskManagerResponseType res = null;
      try {
         UnRegisterPersonDiseaseRequestType request = new UnRegisterPersonDiseaseRequestType();
         request.setTaskID("18");
         if (patient != null) {
            request.setPatientId(patient.getPrimaryKey());
         }
         request.setDiseaseType(disease);
         System.out.println("Task manager unregistering disease: " + request.getDiseaseType() + " for " + request.getPatientId());

         TaskManager service = new TaskManager();
         TaskManagerPortType port = service.getTaskManagerPortSoap11();
         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
         res = port.unRegisterPersonDisease(request);
         System.out.println("Task manager sms response = " + res.getTicket());
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
