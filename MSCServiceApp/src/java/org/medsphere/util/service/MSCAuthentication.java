package org.medsphere.util.service;

import com.medsphere.cia.CIABrokerConnection;
import com.medsphere.ovid.security.RPCConnectionProperties;
import com.medsphere.vistarpc.RPCBrokerConnection;
import com.medsphere.vistarpc.RPCConnection;
import com.medsphere.vistarpc.RPCException;
import com.medsphere.vistarpc.RPCResponse;
import com.medsphere.vistarpc.RPCResponse.ResponseType;
import com.medsphere.vistarpc.VistaRPC;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.apache.log4j.Logger;

/**
 *
 * @author
 */
@WebService(name="AuthenticationService")
public class MSCAuthentication {

    private Logger logger = Logger.getLogger(MSCAuthentication.class);
    private final int DUZ_IDX = 0;
    private final int USER_NAME_IDX = 1;
    private final int USER_FULL_NAME_IDX = 2;
    private final int DIVISION_INFO_IDX = 3;
    private final int TITLE_IDX = 4;
    private final int SERVICE_SECTION_IDX = 5;
    private final int LANGUAGE_IDX = 6;
    private final int DTIME_IDX = 7;
    /**
     * This is a utility method that takes an authentication token for OpenRPMS/OpenVista
     * and a user DUZ (unique numeric primary key) and validates it against the
     * OpenRPMS/OpenVista instance
     */
    @WebMethod(operationName = "ValidateAuthenticationInformation", action="ValidateAuthenticationInformation")
    public UserInfo validateAuthenticationInformation(String duz, String token) {
        UserInfo userInfo = new UserInfo();

        RPCConnection connection = null;
        try {
            connection = connectWithToken(token);
            if (connection == null) {
                userInfo.errorMessage = "connection is null";
                logger.error( userInfo.errorMessage);
            } else if (!connection.getDUZ().equals(duz)) {
                userInfo.errorMessage = "DUZ returned [" + connection.getDUZ() + "] does not match DUZ supplied [" + duz + "]";
                logger.error(userInfo.errorMessage);
            } else {
                VistaRPC rpc = new VistaRPC("XUS GET USER INFO", ResponseType.ARRAY);
                RPCResponse response = connection.execute(rpc);
                if (response == null) {
                    userInfo.errorMessage = "Response to XUS GET USER INFO is null";
                    logger.error(userInfo.errorMessage);
                } else if (response.getError() != null) {
                    userInfo.errorMessage = response.getError();
                    logger.error(userInfo.errorMessage);
                } else {
                    String[] items = response.getArray();
                    if (items == null) {
                        userInfo.errorMessage = "Nothing returned from XUS GET USER INFO RPC";
                        logger.error(userInfo.errorMessage);
                    } else if (items.length < 8) {
                        userInfo.errorMessage = "Expecting 8 items returned from XUS GET USER INFO RPC, but got " + items.length;
                        logger.error(userInfo.errorMessage);
                    } else {
                        userInfo.errorMessage = null;
                        userInfo.duz = items[DUZ_IDX];
                        userInfo.userName = items[USER_NAME_IDX];
                        userInfo.userFullName = items[USER_FULL_NAME_IDX];
                        String divInfo = items[DIVISION_INFO_IDX];
                        if (divInfo != null) {
                            String divItems[] = divInfo.split("\\^");
                            if (divItems.length >= 3) {
                                userInfo.stationNumber = divItems[2];
                            }
                            if (divItems.length >= 2) {
                                userInfo.stationName = divItems[1];
                            }
                            if (divItems.length >= 1) {
                                userInfo.divisionID = divItems[0];
                            }
                        }
                        userInfo.title = items[TITLE_IDX];
                        userInfo.serviceSection = items[SERVICE_SECTION_IDX];
                        userInfo.language = items[LANGUAGE_IDX];
                        userInfo.dTime = items[DTIME_IDX];
                    }
                }

            }

        } catch (RPCException ex) {
            userInfo.errorMessage = ex.getMessage();
            logger.error(userInfo.errorMessage);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (RPCException ex) {
                    
                }
            }
        }
        return userInfo;
    }

    private RPCConnection connectWithToken(String token) throws RPCException {
        RPCConnection connection = null;

        RPCConnectionProperties properties = getConnectionProperties();

        properties.put("token", token);
        String brokerType = properties.get("brokerType");

        if (brokerType != null) {
            if (brokerType.contains("CIABrokerConnector")) {
                connection = getCIABrokerConnection(properties);
            } else {
                connection = getRPCBrokerConnection(properties);
            }
        }

        return connection;
    }

    public RPCConnection getRPCBrokerConnection(RPCConnectionProperties properties) throws RPCException {
        String server = properties.get("server");
        String port = properties.get("port");
        String accessCode = properties.get("userAccessCode");
        String verifyCode = properties.get("userVerifyCode");
        String token = properties.get("token");

        int portnum;
        try {
            portnum = Integer.parseInt( port );
        } catch (NumberFormatException ex) {
            logger.error("Port number not an integer", ex);
            return null;
        }

        if (token != null) {
            return new RPCBrokerConnection(server, portnum, token);
        } else {
            return new RPCBrokerConnection(server, portnum, accessCode, verifyCode);
        }


    }
    public RPCConnection getCIABrokerConnection(RPCConnectionProperties properties) throws RPCException {
        String server = properties.get("server");
        String port = properties.get("port");
        String accessCode = properties.get("userAccessCode");
        String verifyCode = properties.get("userVerifyCode");
        String uci = properties.get("uci");
        String token = properties.get("token");

        int portnum;
        try {
            portnum = Integer.parseInt( port );
        } catch (NumberFormatException ex) {
            logger.error("Port number not an integer", ex);
            return null;
        }

        return new CIABrokerConnection(server, portnum, accessCode, verifyCode, token, uci);
        
    }

    private static final String NHINC_PROPERTIES_DIR = "NHINC_PROPERTIES_DIR";
    public RPCConnectionProperties getConnectionProperties() {
        try {
            String nhincPropertiesDirectory = System.getenv(NHINC_PROPERTIES_DIR);
            if (nhincPropertiesDirectory != null) {
                if (new File(nhincPropertiesDirectory).isDirectory()) {
                    if (!nhincPropertiesDirectory.endsWith(System.getProperty("file.separator"))) {
                        nhincPropertiesDirectory += System.getProperty("file.separator");
                    }
                } else {
                    nhincPropertiesDirectory = null;
                }
            }
            if (nhincPropertiesDirectory == null) {
                nhincPropertiesDirectory = System.getProperty("user.dir", "config");                
            }
            if (nhincPropertiesDirectory == null) {
                nhincPropertiesDirectory = "";
            }
            String fileName = "ovid-connection.properties";
            String path = nhincPropertiesDirectory + System.getProperty("file.separator") + fileName;
            logger.info("Using connection information in [" + path + "]");
            File propertyFile = null;
            FileReader reader = null;
            try {
                propertyFile = new File(path);
                reader = new FileReader(propertyFile);
            } catch (IOException ex) {
                // this can happen when running unit tests or from command line
                propertyFile = new File("config" + System.getProperty("file.separator") + fileName);
                reader = new FileReader(propertyFile);
            }
            Properties p = new Properties();
            p.load(reader);
            RPCConnectionProperties properties = new RPCConnectionProperties(p);
            logger.info("properties: " + properties);
            return properties;
        } catch (IOException ex) {
            logger.error(ex);
            return new RPCConnectionProperties(); // return an empty set
        }
    }

}
