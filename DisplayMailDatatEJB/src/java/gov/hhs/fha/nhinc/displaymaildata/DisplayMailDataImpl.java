/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymaildata;

import gov.hhs.fha.nhinc.common.dda.DetailData;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType;
import gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType;
import gov.hhs.fha.nhinc.common.dda.NameValuesPair;
import gov.hhs.fha.nhinc.common.dda.ServiceError;
import gov.hhs.fha.nhinc.common.dda.SummaryData;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.ldapaccess.ContactAcctDTO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.xml.datatype.DatatypeFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Return a user's mail inbox data.
 *
 * @author cmatser
 */
public class DisplayMailDataImpl {

    /** Mail host. */
    private String host;

    /** Mail protocol. */
    private String protocol;

    /** Property constants. */
    public static final String PROPERTY_FILE = "displayAggregator";
    public static final String MAIL_HOST = "mail.host";
    public static final String MAIL_PROTOCOL = "mail.protocol";

    /** LDAP attribute for provider's user id. */
    public static final String LDAP_PROVIDER_ID_ATT = "employeeNumber";

    /** LDAP attribute for patient's user id. */
    public static final String LDAP_PATIENT_ID_ATT = "uid";

    /** Item identifier for provider ids. */
    public static final String ITEM_ID_PROVIDER = "P";

    /** Item identifier for patient ids. */
    public static final String ITEM_ID_PATIENT = "T";

    /** Item id separater between userId and message number. */
    public static final String ITEM_ID_SEPARATER = "?";

    /** Data source name. */
    public static final String DATA_SOURCE = "Mail";

    /** Item names for name value pairs. */
    public static final String ITEM_READ = "Read";
    public static final String ITEM_REPLIED = "Replied";

    /** Standard error code. */
    public static final Integer ERR_CODE = -1;

    /** Service error message. */
    public static final String ERR_MSG_ITEM_NOT_FOUND = "Item was not found.";

    /** Logging. */
    private static Log log = LogFactory.getLog(DisplayMailDataImpl.class);

    public DisplayMailDataImpl() {

        try {
            host = PropertyAccessor.getProperty(PROPERTY_FILE, MAIL_HOST);
            if (host == null) {
                throw new PropertyAccessException("Property was null: " + MAIL_HOST);
            }

            protocol = PropertyAccessor.getProperty(PROPERTY_FILE, MAIL_PROTOCOL);
            if (protocol == null) {
                throw new PropertyAccessException("Property was null: " + MAIL_PROTOCOL);
            }
        }
        catch (PropertyAccessException e) {
            log.error("Error accessing properties in file:" + PROPERTY_FILE, e);
        }

    }

    public GetDataSourceNameResponseType getDataSourceName(GetDataSourceNameRequestType getDataSourceNameRequest) {
        GetDataSourceNameResponseType response = new GetDataSourceNameResponseType();
        response.setReturn(DATA_SOURCE);

        return response;
    }

    /**
     * Update Inbox status.
     * 
     * @param updateComponentInboxStatusRequest
     * @return
     */
    public UpdateComponentInboxStatusResponseType updateComponentInboxStatus(UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Retrieves the message detail.
     *
     * @param request
     * @return
     */
    public GetComponentDetailDataResponseType getComponentDetailDataForUser(GetComponentDetailDataForUserRequestType request) {
        GetComponentDetailDataResponseType response = new GetComponentDetailDataResponseType();
        String userId = null;
        String userType;

        try {
            //Pull out userId
            userId = request.getItemId().substring(1, request.getItemId().indexOf(ITEM_ID_SEPARATER));
            userType = String.valueOf(request.getItemId().charAt(0));
            if ((userId == null) || userId.isEmpty()) {
                throw new Exception("Invalid item ID provided.");
            }

            //Get login info from ldap
            String access[] = retrieveMailAccess(userType, userId);
            if ((access[0] == null) || access[0].isEmpty()) {
                throw new Exception("Contact record not found for user: " + userId);
            }

            //Retrieve message
            DetailData detailData = retrieveMailMessage(access[0], access[1], request.getItemId());

            response.setDetailObject(detailData);
        }
        catch (Throwable t) {
            log.error("Error getting mail summary data.", t);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(t.getMessage());
            response.getErrorList().add(serviceError);
        }

        return response;
    }

    /**
     * Retrieves the message summaries for a user's inbox.
     *
     * @param request
     * @return
     */
    public GetComponentSummaryDataResponseType getComponentSummaryDataForUser(GetComponentSummaryDataForUserRequestType request) {
        GetComponentSummaryDataResponseType response = new GetComponentSummaryDataResponseType();
        String userId;
        String userType;
        boolean isProvider = true;

        try {
            //Check for provider id
            userId = request.getProviderId();
            if ((userId == null) || userId.isEmpty()) {
                //Try patient id
                userId = request.getPatientId();
                isProvider = false;
            }

            //Check that we have an id
            if ((userId == null) || userId.isEmpty()) {
                throw new Exception("No provider/patient id provided.");
            }

            //Setup user type
            userType = isProvider ? ITEM_ID_PROVIDER : ITEM_ID_PATIENT;

            //Get login info from ldap
            String access[] = retrieveMailAccess(userType, userId);
            if ((access[0] == null) || access[0].isEmpty()) {
                throw new Exception("Contact record not found for user: " + userId);
            }

            //Retrieve messages
            List<SummaryData> summaryDataList = retrieveMail(userType, userId, access[0], access[1], request.isOnlyNew());
            response.getSummaryObjects().addAll(summaryDataList);

        }
        catch (Throwable t) {
            log.error("Error getting mail summary data.", t);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(t.getMessage());
            response.getErrorList().add(serviceError);
        }

        return response;
    }

    /**
     * Find LDAP record from userId.
     *
     * @param userId
     * @return
     */
    private String[] retrieveMailAccess(String userType, String userId) {
        String access[] = new String[2];

        ContactDAO contactDAO = LdapService.getContactDAO();
        List<ContactDTO> contacts = contactDAO.findContact(
            (ITEM_ID_PROVIDER.equals(userType) ?
                LDAP_PROVIDER_ID_ATT
                : LDAP_PATIENT_ID_ATT) + "=" + userId);
        if (contacts.size() > 0) {
            //Get mail login info
            List<ContactAcctDTO> accts = contactDAO.findContactAcct(
                contacts.get(0).getCommonName(), ContactAcctDTO.CN_MAIL);
            if (accts.size() > 0) {
                access[0] = accts.get(0).getUid();
                access[1] = accts.get(0).getClearPassword();
            }
        }

        return access;
    }

    /**
     * Retrieve mail for user.
     *
     * @param login
     * @param pswd
     * @return
     * @throws java.lang.Exception
     */
    private List<SummaryData> retrieveMail(String userType, String userId, String login, String pswd, boolean onlyNew)
            throws Exception {
        List<SummaryData> dataList = new LinkedList<SummaryData>();
        Store store = null;
        Folder folder = null;

        try {
            //Get session
            Session session = Session.getInstance(new Properties());

            //Get the store
            store = session.getStore(protocol);
            store.connect(host, login, pswd);

            //Get folder
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            //Get messages
            Message messages[] = folder.getMessages();

            //Add messages to response, need to do this before we close
            //  the connection
            GregorianCalendar cal = new GregorianCalendar();
            for (Message msg : messages) {
                SummaryData summaryData = new SummaryData();
                summaryData.setDataSource(DATA_SOURCE);
                summaryData.setFrom(msg.getFrom()[0].toString());
                summaryData.setAuthor(summaryData.getFrom());
                cal.setTime(msg.getReceivedDate());
                summaryData.setDateCreated(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
                summaryData.setDescription(msg.getSubject());
                summaryData.setItemId(
                    userType + userId + ITEM_ID_SEPARATER
                    + msg.getFolder().getName() + ITEM_ID_SEPARATER
                    + String.valueOf(msg.getMessageNumber()));
                addNameValue(summaryData.getItemValues(), ITEM_READ, String.valueOf(msg.isSet(Flags.Flag.SEEN)));
                addNameValue(summaryData.getItemValues(), ITEM_REPLIED, String.valueOf(msg.isSet(Flags.Flag.ANSWERED)));

                if (onlyNew) {
                    if (!msg.isSet(Flags.Flag.SEEN)) {
                        dataList.add(summaryData);
                    }
                }
                else {
                    dataList.add(summaryData);
                }
            }

        }
        finally {
            // Close connection
            if (folder != null) {
                try { folder.close(false); } catch (Exception e) { }
            }

            if (store != null) {
                try { store.close(); } catch (Exception e) { }
            }
        }

        return dataList;
    }

    /**
     * Retrieve mail message for user.
     *
     * @param login
     * @param pswd
     * @param msgnum
     * @return
     * @throws java.lang.Exception
     */
    private DetailData retrieveMailMessage(String login, String pswd, String itemId)
            throws Exception {
        DetailData data = null;
        Store store = null;
        Folder folder = null;

        try {
            String msgId[] = parseMsgId(itemId);

            //Get session
            Session session = Session.getInstance(new Properties());

            //Get the store
            store = session.getStore(protocol);
            store.connect(host, login, pswd);

            //Get folder
            folder = store.getFolder(msgId[1]);
            folder.open(Folder.READ_ONLY);

            //Get messages
            Message msgs[] = folder.getMessages(new int[] { Integer.parseInt(msgId[2]) });
            if (msgs.length == 0) {
                return null;
            }

            //Add messages to response
            GregorianCalendar cal = new GregorianCalendar();
            data = new DetailData();
            data.setDataSource(DATA_SOURCE);
            if ((msgs[0].getFrom() != null) && (msgs[0].getFrom().length > 0)) {
                data.setFrom(msgs[0].getFrom()[0].toString());
                data.setAuthor(data.getFrom());
            }
            cal.setTime(msgs[0].getReceivedDate());
            data.setDateCreated(
               DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
            data.setDescription(msgs[0].getSubject());
            data.setItemId(msgId[0] + ITEM_ID_SEPARATER
                + msgs[0].getFolder().getName() + ITEM_ID_SEPARATER
                + String.valueOf(msgs[0].getMessageNumber()));
            data.setData(fetchMsgContent(msgs[0]));
            addNameValue(data.getItemValues(), ITEM_READ, String.valueOf(msgs[0].isSet(Flags.Flag.SEEN)));
            addNameValue(data.getItemValues(), ITEM_REPLIED, String.valueOf(msgs[0].isSet(Flags.Flag.ANSWERED)));

        }
        finally {
            // Close connection
            if (folder != null) {
                try { folder.close(false); } catch (Exception e) { }
            }

            if (store != null) {
                try { store.close(); } catch (Exception e) { }
            }
        }

        return data;
    }

    /**
     * Extract folder and message number from item id.
     * 
     * @param itemId
     * @return
     */
    private String[] parseMsgId(String itemId) {
        String msgId[] = new String[3];
        StringTokenizer st = new StringTokenizer(itemId, ITEM_ID_SEPARATER);

        msgId[0] = st.nextToken();//userId
        msgId[1] = st.nextToken();//folder
        msgId[2] = st.nextToken();//msg num

        return msgId;
    }

    /**
     * Extract/format mail message content.
     * 
     * @param msg
     * @return
     */
    private String fetchMsgContent(Message msg)
            throws Exception {
        String content;

        //We understand plain text
        if (msg.isMimeType("text/plain")) {
            content = (String) msg.getContent();
        }
        else {
            content = "Unknown mail type: " + msg.getContentType();
        }

        return content;
    }

    private void addNameValue(List<NameValuesPair> pairList, String name, String value) {
        NameValuesPair nameVal = new NameValuesPair();
        nameVal.setName(name);
        nameVal.getValues().add(value);
        pairList.add(nameVal);

        return;
    }

}
