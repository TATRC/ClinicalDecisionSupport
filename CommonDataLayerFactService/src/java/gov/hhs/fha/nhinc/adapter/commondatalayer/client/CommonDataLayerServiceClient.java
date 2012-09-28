/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.client;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.CommonDataLayerPortType;
import gov.hhs.fha.nhinc.adapter.commondatalayer.CommonDataLayerService;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.v3.CareRecordQUPCIN040100UV01RequestType;
import org.hl7.v3.CareRecordQUPCIN040200UV01MessageType;
import org.hl7.v3.CareRecordQUPCIN043100UV01RequestType;
import org.hl7.v3.CareRecordQUPCIN043200UV01MessageType;
import org.hl7.v3.FindEncountersPRPAIN900300UV02RequestType;
import org.hl7.v3.FindEncountersPRPAIN900350UV02MessageType;
import org.hl7.v3.FindPatientsPRPAIN201305UVRequestType;
import org.hl7.v3.FindPatientsPRPAMT201310UVResponseType;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.PatientDemographicsPRPAIN201307UV02RequestType;
import org.hl7.v3.PatientDemographicsPRPAMT201303UVResponseType;
import org.hl7.v3.ProviderDetailsPRPMIN306010UV1RequestType;
import org.hl7.v3.ProviderDetailsPRPMIN306011UV01ResponseType;
import org.hl7.v3.ResultEventPOLBIN364000UV01MessageType;
import org.hl7.v3.ResultQueryPOLBIN354000UV01MessageType;
import org.hl7.v3.SlotRequestPRSCIN030101UVMessageType;
import org.hl7.v3.SlotsQueryResponsePRSCIN030102UVMessageType;
import org.w3c.dom.Document;

/**
 *
 * @author kim
 */
public class CommonDataLayerServiceClient {

  private static Log log = LogFactory.getLog(CommonDataLayerServiceClient.class);
  public static final String COMMON_DATA_LAYER_QNAME = "urn:gov:hhs:fha:nhinc:adapter:commondatalayer";
  /**  A set of expected response codes  */
  public final static String RESPONSE_OK = "OK";
  public final static String RESPONSE_APPLICATION_ERROR = "AE";
  public final static String RESPONSE_NO_DATA_FOUND = "NF";
  public final static String RESPONSE_QUERY_PARAMS_ERROR = "QE";

  public enum CareProvisionCode {
    ALLCAT, INTOLIST, COBSCAT, DICAT, IMMUCAT, LABCAT, MEDCCAT, PROBLIST, PSVCCAT,
    RXCAT, MEDLIST, CURMEDLIST, DISCHMEDLIST, HISTMEDLIST, OE, LABOE, MEDOE
  }

  /**  object factory  */
  private ObjectFactory objectFactory = null;
  /**  Custom XML prefix  */
  NamespacePrefixMapper prefixMapper = null;
  /**  Custom HL7 properties  */
  private Properties hl7Props = null;
  /**  Handle to Common Data Access Layer web services  */
  private CommonDataLayerService calService = null;
  /**  Common Data Access Layer web services URL  */
  private String serviceEndpoint;
  /**  JAXB context object  */
  private JAXBContext jaxbContext = null;

  public CommonDataLayerServiceClient() {
    initJAXBContext("org.hl7.v3");
  }

  private void initJAXBContext(String factoryClass) {
    try {
      jaxbContext = JAXBContext.newInstance(factoryClass);
    } catch (JAXBException ex) {
      log.error("Failed to instanstialize JABContext object");
    }
  }

  private void initService(String serviceEndpoint) {
    URL baseUrl;

    baseUrl = gov.hhs.fha.nhinc.adapter.commondatalayer.CommonDataLayerService.class.getResource(".");
    try {
      URL url = new URL(baseUrl, serviceEndpoint);
      calService = new CommonDataLayerService(url, new QName(COMMON_DATA_LAYER_QNAME, "CommonDataLayerService"));
    } catch (MalformedURLException e) {
      log.error("Failed to create URL for the wsdl Location: " + serviceEndpoint);
    }
  }

  public Document getPatientInfo(PatientDemographicsPRPAIN201307UV02RequestType request) throws Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        PatientDemographicsPRPAMT201303UVResponseType response = port.getPatienInfo(request);
        xmlDocument = marshalToDocument(objectFactory.createPatientDemographicsPRPAMT201303UVResponse(response));
      }
      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  public Document findPatients(FindPatientsPRPAIN201305UVRequestType request) throws Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        FindPatientsPRPAMT201310UVResponseType response = port.findPatients(request);
        xmlDocument = marshalToDocument(objectFactory.createFindPatientsPRPAMT201310UVResponse(response));
      }
      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  public Document findProviders(ProviderDetailsPRPMIN306010UV1RequestType request) throws Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        ProviderDetailsPRPMIN306011UV01ResponseType response = port.findProviders(request);
        xmlDocument = marshalToDocument(objectFactory.createProviderDetailsPRPMIN306011UV01Response(response));
      }
      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  public Document getTestResultEvents(ResultQueryPOLBIN354000UV01MessageType request)
          throws NonMatchingQueryIdException, ResponseCodeErrorException, Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        ResultEventPOLBIN364000UV01MessageType response = port.findResultEventQuery(request);

        // check for valid response to request
        String requestId = request.getQuery().getControlActProcess().getQueryByParameter().getValue().getQueryId().getExtension();
        String responseId = response.getMessage().getControlActProcess().getQueryAck().getQueryId().getExtension();

        if (!responseId.equals(requestId)) {
          throw new NonMatchingQueryIdException("Test Result Events query id - " + responseId + " - returned in response did not matched request query id " + requestId);
        }

        String responseCode = response.getMessage().getControlActProcess().getQueryAck().getQueryResponseCode().getCode();
        log.debug("Response status received - " + responseCode);

        if (responseCode.equalsIgnoreCase(RESPONSE_OK) ||
                responseCode.equalsIgnoreCase(RESPONSE_NO_DATA_FOUND)) {
          xmlDocument = marshalToDocument(objectFactory.createResultEventPOLBIN364000UV01Message(response));
        } else {
          throw new ResponseCodeErrorException("Received response status " + responseCode);
        }
      }

      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  public Document findEncounters(FindEncountersPRPAIN900300UV02RequestType request)
          throws NonMatchingQueryIdException, ResponseCodeErrorException, Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        FindEncountersPRPAIN900350UV02MessageType response = port.findEncounters(request);

        // check for valid response to request
        String requestId = request.getQuery().getControlActProcess().getQueryByParameterPayload().getValue().getQueryId().getExtension();
        String responseId = response.getMessage().getControlActProcess().getQueryAck().getQueryId().getExtension();

        if (!responseId.equals(requestId)) {
          throw new NonMatchingQueryIdException("Encounters query id - " + responseId + " - returned in response did not matched request query id " + requestId);
        }

        String responseCode = response.getMessage().getControlActProcess().getQueryAck().getQueryResponseCode().getCode();
        log.debug("Response status received - " + responseCode);

        if (responseCode.equalsIgnoreCase(RESPONSE_OK) ||
                responseCode.equalsIgnoreCase(RESPONSE_NO_DATA_FOUND)) {
          xmlDocument = marshalToDocument(objectFactory.createFindEncountersPRPAIN900350UV02Message(response));
        } else {
          throw new ResponseCodeErrorException("Received response status " + responseCode);
        }
      }
      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  public Document getAmbEncounterDetails(CareRecordQUPCIN040100UV01RequestType request)
          throws NonMatchingQueryIdException, ResponseCodeErrorException, Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        CareRecordQUPCIN040200UV01MessageType response = port.getAmbEncounterDetails(request);

        // check for valid response to request
        String requestId = request.getQuery().getControlActProcess().getQueryByParameter().getValue().getQueryId().getExtension();
        String responseId = response.getResponse().getControlActProcess().getQueryAck().getQueryId().getExtension();

        if (!responseId.equals(requestId)) {
          throw new NonMatchingQueryIdException("Amb encounter details query id - " + responseId + " - returned in response did not matched request query id " + requestId);
        }

        String responseCode = response.getResponse().getControlActProcess().getQueryAck().getQueryResponseCode().getCode();
        log.debug("Response status received - " + responseCode);

        if (responseCode.equalsIgnoreCase(RESPONSE_OK) ||
                responseCode.equalsIgnoreCase(RESPONSE_NO_DATA_FOUND)) {
          xmlDocument = marshalToDocument(objectFactory.createCareRecordQUPCIN040200UV01Message(response));
        } else {
          throw new ResponseCodeErrorException("Received response status " + responseCode);
        }
      }
      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  public Document getImpEncounterDetails(CareRecordQUPCIN040100UV01RequestType request)
          throws NonMatchingQueryIdException, ResponseCodeErrorException, Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        CareRecordQUPCIN040200UV01MessageType response = port.getImpEncounterDetails(request);

        // check for valid response to request
        String requestId = request.getQuery().getControlActProcess().getQueryByParameter().getValue().getQueryId().getExtension();
        String responseId = response.getResponse().getControlActProcess().getQueryAck().getQueryId().getExtension();

        if (!responseId.equals(requestId)) {
          throw new NonMatchingQueryIdException("Inaptient encounter details query id - " + responseId + " - returned in response did not matched request query id " + requestId);
        }

        String responseCode = response.getResponse().getControlActProcess().getQueryAck().getQueryResponseCode().getCode();
        log.debug("Response status received - " + responseCode);

        if (responseCode.equalsIgnoreCase(RESPONSE_OK) ||
                responseCode.equalsIgnoreCase(RESPONSE_NO_DATA_FOUND)) {
          xmlDocument = marshalToDocument(objectFactory.createCareRecordQUPCIN040200UV01Message(response));
        } else {
          throw new ResponseCodeErrorException("Received response status " + responseCode);
        }
      }
      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  public Document getCareRecordQUPCIN043200UV01(CareRecordQUPCIN043100UV01RequestType request, String careRecordCode)
          throws NonMatchingQueryIdException, ResponseCodeErrorException, Exception {
    Document xmlDocument = null;

    if (request == null) {
      return xmlDocument;
    }

    try {
      CareProvisionCode cpc = CareProvisionCode.valueOf(careRecordCode);

      if (calService == null) {
        initService(serviceEndpoint);
      }

      CommonDataLayerPortType port = calService.getCommonDataLayerPort();
      CareRecordQUPCIN043200UV01MessageType response = null;

      log.debug("getCareRecordQUPCIN043200UV01(): cpc=" + cpc);
      
      switch (cpc) {
        case ALLCAT:
        case INTOLIST:
          response = port.getAllergies(request);
          break;
        case COBSCAT:
          response = port.getVitals(request);
          break;
        case DICAT:
          response = port.getImagingResults(request);
          break;
        case IMMUCAT:
          response = port.getImmunizations(request);
          break;
        case LABCAT:
          response = port.getTestResults(request);
          break;
        case MEDCCAT:
        case PROBLIST:
          log.debug("getCareRecordQUPCIN043200UV01(): calling getProblems() ...");
          response = port.getProblems(request);
          break;
        case PSVCCAT:
          response = port.getProcedures(request);
          break;
        case RXCAT:
        case MEDLIST:
        case CURMEDLIST:
        case DISCHMEDLIST:
        case HISTMEDLIST:
          response = port.getMedications(request);
          break;
        case OE:
        case LABOE:
        case MEDOE:
          response = port.getOrders(request);
          break;
        default:
          throw new InvalidCareProvisionCodeException("Invalid care provision code " + careRecordCode +
              ", valid codes: ALLCAT, INTOLIST, COBSCAT, DICAT, IMMUCAT, LABCAT, MEDCCAT, PROBLIST, PSVCCAT, RXCAT, MEDLIST, " +
              "CURMEDLIST, DISCHMEDLIST, HISTMEDLIST, OE, LABOE or MEDOE");
      }

      // check for valid response to request
      String requestId = request.getQuery().getControlActProcess().getQueryByParameter().getValue().getQueryId().getExtension();
      String responseId = response.getResponse().getControlActProcess().getQueryAck().getQueryId().getExtension();

      if (!responseId.equals(requestId)) {
        throw new NonMatchingQueryIdException("Response query id " + responseId + " did not matched request query id " + requestId);
      }
      else {
         String responseCode = response.getResponse().getControlActProcess().getQueryAck().getQueryResponseCode().getCode();
         log.debug("Response status received - " + responseCode);

         if (responseCode.equalsIgnoreCase(RESPONSE_OK) ||
                 responseCode.equalsIgnoreCase(RESPONSE_NO_DATA_FOUND)) {
           xmlDocument = marshalToDocument(objectFactory.createCareRecordQUPCIN043200UV01Message(response));
         } else {
           throw new ResponseCodeErrorException(responseCode);
         }

         return xmlDocument;
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public Document findSlots(SlotRequestPRSCIN030101UVMessageType request) throws Exception {
    Document xmlDocument = null;
    try {
      if (request != null) {
        if (calService == null) {
          initService(serviceEndpoint);
        }

        CommonDataLayerPortType port = calService.getCommonDataLayerPort();
        SlotsQueryResponsePRSCIN030102UVMessageType response = port.findSlots(request);
        xmlDocument = marshalToDocument(objectFactory.createSlotsQueryResponsePRSCIN030102UVMessage(response));
      }
      return xmlDocument;
    } catch (Exception e) {
      log.error(e);
      throw e;
    }
  }

  private Document marshalToDocument(javax.xml.bind.JAXBElement o) throws Exception {
    Document doc = null;
    if (o == null) {
      return null;
    }

    //JAXBContext jc = JAXBContext.newInstance(org.hl7.v3.class);
    if (jaxbContext == null) {
      initJAXBContext("org.hl7.v3");
    }

    Marshaller marshaller = jaxbContext.createMarshaller();
    // In the JAXB RI, this is in com.sun.xml.bind.marshaller,
    // but in Java6, it's in com.sun.xml.internal.bind.marshaller.
    marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", prefixMapper);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    //dbf.setNamespaceAware(true);
    DocumentBuilder db = dbf.newDocumentBuilder();
    doc = db.newDocument();
    marshaller.marshal(o, doc);

    return doc;
  }

  public void setHl7Props(Properties hl7Props) {
    this.hl7Props = hl7Props;
  }

  public Properties getHl7Props() {
    return hl7Props;
  }

  public void setPrefixMapper(NamespacePrefixMapper prefixMapper) {
    this.prefixMapper = prefixMapper;
  }

  public void setObjectFactory(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }

  public void setServiceEndpoint(String serviceEndpoint) {
    this.serviceEndpoint = serviceEndpoint;
  }

  public String getServiceEndpoint() {
    return serviceEndpoint;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("Properties[");
    Enumeration propsEnum = hl7Props.propertyNames();
    while (propsEnum.hasMoreElements()) {
      String name = (String) propsEnum.nextElement();
      str.append(hl7Props.getProperty(name) + "|");
    }

    return str.toString();
  }
}
