package gov.hhs.fha.nhinc.kmr.util;

import gov.hhs.fha.nhinc.account.model.UserSession;
import gov.hhs.fha.nhinc.account.service.AccountService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.Resource;
import org.restlet.resource.StringRepresentation;

/**
 * This is a class that contains common refactored utility methods for session
 * oriented requirements.
 *
 * @author jharby
 */
public class SessionUtilities {

    public static final Logger logger = Logger.getLogger(SessionUtilities.class.getName());

    /**
     * This method sets the response headers for Cross-Origin Resource Sharing in the
     * resource response headers.
     * 
     * @param resource
     */
    public static void setCORSHeaders(Resource resource) {
        logger.log(Level.INFO, "Setting CORS headers for " + resource.getClass().getName());
        Form responseHeaders = (Form) resource.getResponse().getAttributes().get("org.restlet.http.headers");
        if (responseHeaders == null) {
            responseHeaders = new Form();
            resource.getResponse().getAttributes().put("org.restlet.http.headers", responseHeaders);
        }
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Allow-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        responseHeaders.add("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
    }

    public static boolean verifyToken(String token) {
        AccountService service = new AccountService();
        UserSession session = service.getUserSession(token);

        if (session == null) {
            return false;
        }

        else {
            session.setLastUpdateTime(new Date());
            service.saveUserSession(session);
            return true;
        }

    }

   public static void storeTokenData(String username, String providerId, String patientId, String tokenValue) {
        AccountService accountService = new AccountService();
        UserSession session = new UserSession();
        session.setToken(tokenValue);
        session.setProviderId(providerId);
        session.setUserId(username);
        accountService.saveUserSession(session);
    }

    public static String encryptToken(String tokenValue) throws IOException, GeneralSecurityException {
        String encryptedToken;
        AESEncryption aesEncryption = new AESEncryption();
        encryptedToken = AESEncryption.toHexString(aesEncryption.aesEncrypt(tokenValue));
        return encryptedToken;
    }

    public static void generateErrorRepresentation(String errorMessage,
            String errorCode, Response response) {
        // This is an error
        response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
        StringRepresentation representation =
                    new StringRepresentation(errorMessage, MediaType.APPLICATION_JSON);
        response.setEntity(representation);

    }
}
