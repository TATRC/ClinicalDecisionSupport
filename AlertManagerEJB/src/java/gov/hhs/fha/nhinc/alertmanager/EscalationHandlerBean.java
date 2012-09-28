/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
@Stateless
public class EscalationHandlerBean implements EscalationHandlerBeanLocal {

    @Resource
    TimerService timerService;

    private Timer timer = null;

    /** Logging. */
    private static Log log = LogFactory.getLog(EscalationHandlerBean.class);

    /** How often to check for escalations (ms). Should be every minute. */
    public static final long ESCALATION_INTERVAL = 60000;

    /** Propery file for queue connection factory. */
    public static final String PROPERTY_FILE = "taskmanager";

    /** Property name for queue factory. */
    public static final String PROPERTY_QUEUE_FACTORY = "taskQueueFactory";

    /** Alert queue name (AlertHandler MDB bound to this queue). */
    public static final String ALERT_QUEUE = "jms/AlertQueue";

    /**
     * Start one-time timer.
     *
     * @param ticket ticket to escalate
     * @param minutes number of minutes before time expires
     * @param isManual indicates manual escalation or automatic
     */
    public void initOneTimeTimer(String ticket, int minutes, String actionMessage,
            boolean isManual, String userId, String userName, boolean isUserProvider) {
        EscalationMessage msg = new EscalationMessage();
        msg.setTicket(ticket);
        msg.setActionMessage(actionMessage);
        msg.setManual(isManual);
        msg.setUserId(userId);
        msg.setUserName(userName);
        msg.setUserProvider(isUserProvider);

        timer = timerService.createTimer(minutes * 60000, msg);
    }

    @Timeout
    public void processEscalation(Timer timer) {
        log.debug("Escalation timed process started.");

        try {
            //Get info
            EscalationMessage msg = (EscalationMessage) timer.getInfo();
            queueEscalationMessage(msg);
        }
        catch (Exception e) {
            log.error("Error in timed escalation process.", e);
        }

        log.debug("Escalation timed process finished.");
    }

    /**
     * Helper method to queue an alert for immedicate escalation.  This is
     * public so that other parts of the alert service can use this.
     * @param info
     */
    public static void queueEscalationMessage(EscalationMessage msg)
        throws Exception {

        log.debug("Queue escalation request for ticket: " + msg.getTicket());

        //Setup queue connection for sending escaltion messages
        QueueConnection queueConnection = null;
        QueueSession queueSession = null;
        Queue queue = null;

        try {
            //Get task queue name & queue factory
            String taskQFactory = PropertyAccessor.getProperty(
                    PROPERTY_FILE, PROPERTY_QUEUE_FACTORY);

            //Get queue connection
            Context jndiContext = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory)
                jndiContext.lookup(taskQFactory);
            queue = (Queue) jndiContext.lookup(ALERT_QUEUE);

            //Create connection session
            queueConnection = queueConnectionFactory.createQueueConnection();
            queueSession = queueConnection.createQueueSession(false,
                Session.AUTO_ACKNOWLEDGE);
        }
        catch (Throwable t) {
            log.error("Exiting escalation.  Error establising connection to queue: " + ALERT_QUEUE, t);

            //Close queue
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {}
            }

            throw new Exception("Escalation aborted.", t);
        }

        try {
            sendEscalation(queueSession, queue, msg);
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            //Close queue
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {}
            }
        }
    }

    /**
     * Escalate the alert associated with this ticket.  We send this to the
     * alert processing queue so that it can be handled asyncronously.  We don't
     * want to bog down this escalation processor.
     *
     * @param ticket
     */
    private static void sendEscalation(QueueSession queueSession, Queue queue, EscalationMessage msg)
        throws Exception {

        try {
            log.debug("Sending message to escalate alert ticket: " + msg.getTicket());

            QueueSender queueSender = queueSession.createSender(queue);

            //Create JMS message
            ObjectMessage message = queueSession.createObjectMessage(msg);
            message.setJMSCorrelationID(msg.getTicket());

            //Send message
            queueSender.send(message);
        }
        catch (Throwable t) {
            throw new Exception("Error sending escalation.", t);
        }

    }

}
