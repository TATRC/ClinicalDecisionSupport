
package org.medsphere.util.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.medsphere.util.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValidateAuthenticationInformation_QNAME = new QName("http://service.util.medsphere.org/", "ValidateAuthenticationInformation");
    private final static QName _ValidateAuthenticationInformationResponse_QNAME = new QName("http://service.util.medsphere.org/", "ValidateAuthenticationInformationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.medsphere.util.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidateAuthenticationInformationResponse }
     * 
     */
    public ValidateAuthenticationInformationResponse createValidateAuthenticationInformationResponse() {
        return new ValidateAuthenticationInformationResponse();
    }

    /**
     * Create an instance of {@link ValidateAuthenticationInformation }
     * 
     */
    public ValidateAuthenticationInformation createValidateAuthenticationInformation() {
        return new ValidateAuthenticationInformation();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateAuthenticationInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.util.medsphere.org/", name = "ValidateAuthenticationInformation")
    public JAXBElement<ValidateAuthenticationInformation> createValidateAuthenticationInformation(ValidateAuthenticationInformation value) {
        return new JAXBElement<ValidateAuthenticationInformation>(_ValidateAuthenticationInformation_QNAME, ValidateAuthenticationInformation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateAuthenticationInformationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.util.medsphere.org/", name = "ValidateAuthenticationInformationResponse")
    public JAXBElement<ValidateAuthenticationInformationResponse> createValidateAuthenticationInformationResponse(ValidateAuthenticationInformationResponse value) {
        return new JAXBElement<ValidateAuthenticationInformationResponse>(_ValidateAuthenticationInformationResponse_QNAME, ValidateAuthenticationInformationResponse.class, null, value);
    }

}
