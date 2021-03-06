
package org.medsphere.util.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3.1-hudson-749-SNAPSHOT
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "MSCAuthenticationService", targetNamespace = "http://service.util.medsphere.org/", wsdlLocation = "WEB-INF/wsdl/MSCAuthenticationService.wsdl")
public class MSCAuthenticationService
    extends Service
{

    private final static URL MSCAUTHENTICATIONSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(org.medsphere.util.service.MSCAuthenticationService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = org.medsphere.util.service.MSCAuthenticationService.class.getResource(".");
            url = new URL(baseUrl, "WEB-INF/wsdl/MSCAuthenticationService.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'WEB-INF/wsdl/MSCAuthenticationService.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        MSCAUTHENTICATIONSERVICE_WSDL_LOCATION = url;
    }

    public MSCAuthenticationService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MSCAuthenticationService() {
        super(MSCAUTHENTICATIONSERVICE_WSDL_LOCATION, new QName("http://service.util.medsphere.org/", "MSCAuthenticationService"));
    }

    /**
     * 
     * @return
     *     returns AuthenticationService
     */
    @WebEndpoint(name = "AuthenticationServicePort")
    public AuthenticationService getAuthenticationServicePort() {
        return super.getPort(new QName("http://service.util.medsphere.org/", "AuthenticationServicePort"), AuthenticationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuthenticationService
     */
    @WebEndpoint(name = "AuthenticationServicePort")
    public AuthenticationService getAuthenticationServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.util.medsphere.org/", "AuthenticationServicePort"), AuthenticationService.class, features);
    }

}
