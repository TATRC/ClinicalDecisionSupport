/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;
import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Handle "Mail" task events.  Send mail message via IMAP server.
 *
 * @author cmatser
 */
public class MailHandler {

    /** LDAP attribute for provider's user id. */
    public static final String LDAP_PROVIDER_ID_ATT = "employeeNumber";

    /** LDAP attribute for patient's user id. */
    public static final String LDAP_PATIENT_ID_ATT = "uid";

    /** X-Mailer header. */
    public static final String X_MAILER = "KMR Mailer";

    /** Logging. */
    private static Log log = LogFactory.getLog(MailHandler.class);

    /**
     * Process task.
     */
    public void handleMessage(String taskTicket, TaskServiceRef serviceRef, TaskMessage taskMessage, TaskType task) {
        TaskMailMessage mailMessage = null;

        log.debug("Handling Mail task.");

        if (taskMessage instanceof TaskMailMessage) {
            mailMessage = (TaskMailMessage) taskMessage;
        }

        if (mailMessage == null) {
            log.error("Error, invalid mail task for ticket: " + taskTicket);
            return;
        }

        boolean result = sendMail(serviceRef.getLocation(), mailMessage.getFromUser(),
            mailMessage.isFromUserProvider(), mailMessage.getToUser(),
            mailMessage.isToUserProvider(), mailMessage.getSubject(),
            mailMessage.getMessage());

        log.debug("Task ticket: " + taskTicket
            + ". Mail result: " + (result?"success.":"failed."));
    }

    private boolean sendMail(String host, String fromUser, boolean isFromUserProvider,
            String toUser, boolean isToUserProvider, String subject, String text) {
        boolean retVal = true;

        try {
            String fromAddr = getEmailAddr(isFromUserProvider, fromUser);
            String toAddr = getEmailAddr(isToUserProvider, toUser);

            //Get session
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            Session session = Session.getInstance(props);

            //Create messages
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromAddr));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddr));
            msg.setSubject(subject);
            msg.setText(text);
            msg.setHeader("X-Mailer", X_MAILER);
            msg.setSentDate(new Date());

            // send the thing off
            Transport.send(msg);

            log.debug("Mail was sent successfully.");
        }
        catch (Exception e) {
            log.error("Error sending mail.", e);
            retVal = false;
        }

        return retVal;
    }

    /**
     * Use LDAP to get email address
     *
     * @param user ldap lookup
     * @return
     */
    private String getEmailAddr(boolean isProvider, String lookup) {
        String email = null;

        //If the lookup field appears to be a number, use it
        //  as a userId to search LDAP
        lookup = lookup.trim();
        if (lookup.length() > 0) {
            boolean isNumber = true;
            for (int i = 0; i < lookup.length(); i++) {
                if (!Character.isDigit(lookup.charAt(i))) {
                    isNumber = false;
                    break;
                }
            }

            if (isNumber) {
                lookup = (isProvider ? LDAP_PROVIDER_ID_ATT : LDAP_PATIENT_ID_ATT) + lookup;
            }
        }

        //Search LDAP
        ContactDAO contactDAO = LdapService.getContactDAO();
        List<ContactDTO> contacts = contactDAO.findContact(lookup);
        if (contacts.size() > 0) {
            email = contacts.get(0).getMail();
        }

        return email;
    }

}
