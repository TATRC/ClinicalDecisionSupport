/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.restlet.Client;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.StringRepresentation;

/**
 * Handle a new task event to send a iCalendar VEVENT.
 *
 * @author cmatser
 */
public class VEventHandler {

    /** Timestamp date format. */
    public DateFormat dTStampFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

    /** VEVENT template. */
    public String vEventMsg =
        "BEGIN:VCALENDAR\n" +
        "VERSION:2.0\n" +
        "PRODID:Zimbra-Calendar-Provider\n" +
        "BEGIN:VEVENT\n" +
        "DTSTAMP:{1}\n" +
        "SEQUENCE:1\n" +
        "UID:{0}\n" +
        "ORGANIZER:mailto:admin@example.com\n" +
        "DTSTART:{2}\n" +
        "DTEND:{3}\n" +
        "STATUS:NEEDS-ACTION\n" +
        "SUMMARY:{4}\n" +
        "X-ALT-DESC;FMTTYPE=text/html:<html><body><div style='font-family:Times New Roman; font-size: 12pt; color: #000000;'></div>{5}</body></html>\n" +
        "END:VEVENT\n" +
        "END:VCALENDAR";

    /** Logging. */
    private static Log log = LogFactory.getLog(VEventHandler.class);

    /**
     * Process task.
     */
    public void handleMessage(String taskTicket, TaskServiceRef serviceRef, TaskMessage taskMessage, TaskType task) {

        log.debug("Handling VEVENT task.");

        try {
            //Pull out username/password
            TaskRuleMessage ruleMessage = (TaskRuleMessage) taskMessage;
            List<TaskContact> providers = new LinkedList<TaskContact>();
            if (ruleMessage.getProviders().isEmpty()) {
                throw new Exception("Providers not found for task: " + ruleMessage.getTaskID());
            }

            //Create list of providers
            for (TaskContact provider: ruleMessage.getProviders()) {
                //Check for role
                if ((provider.getProvider() == null) || provider.getProvider().isEmpty()) {
                    String role = provider.getRole();
                    String location = provider.getLocation();

                    //Find based on role, location
                    List<String> providerLdaps = TaskManagerUtil.retrieveProviderLdaps(role, location);
                    for (String ldap : providerLdaps) {
                        TaskContact contact = new TaskContact();
                        contact.setProvider(ldap);
                        providers.add(contact);
                    }
                }
                else {
                    //Just add the provider to the list
                    providers.add(provider);
                }

            }

            //Now that we have the list of providers, create the event
            for (TaskContact provider: providers) {
                try {
                    addEventForProvider(taskTicket, provider, serviceRef, ruleMessage, task);
                }
                catch (Exception e) {
                    log.error("Error handling task ticket: " + taskTicket
                        + ". Error creating VTODO for provider: " + provider.getProvider(), e);
                }
            }

        }
        catch (Exception e) {
            log.error("Error handling task ticket: " + taskTicket
                + ". Error creating VEVENT.", e);
        }

    }

    /**
     * Add event for a single provider.
     * 
     * @param taskTicket
     * @param provider
     * @param serviceRef
     * @param taskMessage
     * @param task
     */
    public void addEventForProvider(String taskTicket, TaskContact provider, TaskServiceRef serviceRef, TaskRuleMessage ruleMessage, TaskType task) {

        try {
            Client client = new Client(Protocol.HTTP);
            client.setConnectTimeout(10);

            //Pull out username/password
            String access[] = TaskManagerUtil.retrieveCalendarAccess(provider.getProvider());
            String username = access[0];
            String password = access[1];

            //Check that we found access record
            if (username == null) {
                throw new Exception("Ldap record not found for: " + provider.getProvider());
            }

            //Create time stamps
            Date now = new Date();
            Date startDate = ruleMessage.getDeliveryDate();
            Date endDate = ruleMessage.getCompletionDate();

            //Create UID
            String uid = String.valueOf(now.getTime());

            //Create event message
            String data = MessageFormat.format(
                vEventMsg,
                uid,
                dTStampFormat.format(now),
                dTStampFormat.format(startDate),
                dTStampFormat.format(endDate),
                ruleMessage.getSubject(),
                ruleMessage.getMessage());

            //Debug
            log.debug("Creating VEVENT:\n" + data);

            //Build query string components
            String queryString = "username=" + username
                + "&password=" + password;
            String apiSalt = serviceRef.getServiceParam1();
            String checksum = DigestUtils.shaHex(queryString+apiSalt);

            //Build request
            Request request = new Request(
                Method.PUT,
                new Reference(
                    serviceRef.getLocation()
                        + "?" + queryString
                        + "&checksum=" + checksum),
                    new StringRepresentation(data, MediaType.TEXT_XML));
            Response response = client.handle(request);

            log.debug("Task ticket: " + taskTicket
                + ". VEVENT result: " + response.getStatus());
        }
        catch (Exception e) {
            log.error("Error handling task ticket: " + taskTicket
                + ". Error creating VEVENT.", e);
        }

    }

}
