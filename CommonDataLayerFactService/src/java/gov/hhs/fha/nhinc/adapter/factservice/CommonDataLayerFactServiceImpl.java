/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.factservice;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.CommonDataLayerServiceClient;
import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidCareProvisionCodeException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.CareRecordProfileRequestHelper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.CareRecordRequestHelper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.FindEncountersRequestHelper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.FindPatientsRequestHelper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.FindProvidersRequestHelper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.PatientInfoRequestHelper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.ResultQueryRequestHelper;
import gov.hhs.fha.nhinc.adapter.commondatalayer.helper.SlotQueryRequestHelper;
import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType;
import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.RecordOrderRequestType;
import gov.hhs.fha.nhinc.adapter.fact.RecordOrderResponseType;
import gov.hhs.fha.nhinc.adapter.fact.RegistryProfileFactType;
import gov.hhs.fha.nhinc.adapter.fact.ValueType;
import gov.hhs.fha.nhinc.adapter.fact.extractor.FactExtractorFactory;
import gov.hhs.fha.nhinc.adapter.fact.extractor.FactExtractor;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import gov.hhs.fha.nhinc.properties.PropertyAccessor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import mil.navy.med.dzreg.types.RegistryProfileType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.v3.CareRecordQUPCIN040100UV01RequestType;
import org.hl7.v3.CareRecordQUPCIN043100UV01RequestType;
import org.hl7.v3.FindEncountersPRPAIN900300UV02RequestType;
import org.hl7.v3.FindPatientsPRPAIN201305UVRequestType;
import org.hl7.v3.PatientDemographicsPRPAIN201307UV02RequestType;
import org.hl7.v3.ProviderDetailsPRPMIN306010UV1RequestType;
import org.hl7.v3.ResultQueryPOLBIN354000UV01MessageType;
import org.hl7.v3.SlotRequestPRSCIN030101UVMessageType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.w3c.dom.Document;

/**
 *
 * @author kim
 */
public class CommonDataLayerFactServiceImpl implements CommonDataLayerFactServicePort {

  public enum CareProvisionCode {

    ALLCAT, INTOLIST, COBSCAT, DICAT, IMMUCAT, LABCAT, MEDCCAT, PROBLIST, PSVCCAT,
    RXCAT, MEDLIST, CURMEDLIST, DISCHMEDLIST, HISTMEDLIST, OE, LABOE, MEDOE
  }
  private static Log log = LogFactory.getLog(CommonDataLayerFactServiceImpl.class);
  private final static String GET_ALLERGY_FACTS = "getAllergyFacts";
  private final static String GET_ADMISSION_FACTS = "getAdmissionFacts";
  private final static String GET_APPOINTMENT_FACTS = "getAppointmentFacts";
  private final static String GET_ENCOUNTER_DETAILS_FACTS = "getEncounterFacts";
  private final static String GET_IMMUNIZATION_FACTS = "getImmunizationFacts";
  private final static String GET_DEMOGRAPHICS_FACT = "getDemographicsFact";
  private final static String GET_ORDER_FACTS = "getOrderFacts";
  private final static String GET_PATIENT_FACTS = "getPatientFacts";
  private final static String GET_PROVIDER_FACTS = "getProviderFacts";
  private final static String GET_PROBLEM_FACTS = "getProblemFacts";
  private final static String GET_PROCEDURE_FACTS = "getProcedureFacts";
  private final static String GET_TEST_RESULT_FACTS = "getTestResultFacts";
  private final static String GET_TEST_RESULT_EVENT_FACTS = "getTestResultEventFacts";
  private final static String GET_MEDICATION_FACTS = "getMedicationFacts";
  private final static String GET_VITALS_FACTS = "getVitalsFacts";
  private final static String GET_IMAGING_RESULT_FACTS = "getImagingResultFacts";
  private final static String GET_APPT_SCHEDULE_FACTS = "getAppointmentScheduleFacts";
  private final static String CAL_CLIENT_BEAN_NAME = "cal.client";
  private final static String EXTRACTOR_TYPES_BEAN_NAME = "extractor.types";
  private final static String REGISTRY_PROPERTY_BEAN_NAME = "registry.property";
  private static CommonDataLayerFactServiceImpl instance = null;
  private ApplicationContext context;
  private CommonDataLayerServiceClient calClient = null;
  private Map extractorTypes;
  private String registryServiceEndpoint;
  private PatientInfoRequestHelper ptInfoHelper = null;
  private FindPatientsRequestHelper ptSearchHelper = null;
  private CareRecordProfileRequestHelper careRecordProfileHelper = null;
  private CareRecordRequestHelper careRecordHelper = null;
  private ResultQueryRequestHelper resultQueryHelper = null;
  private FindProvidersRequestHelper docSearchHelper = null;
  private FindEncountersRequestHelper encounterSearchHelper = null;
  private SlotQueryRequestHelper slotQueryHelper = null;

  /**
   *
   */
  private CommonDataLayerFactServiceImpl() {
    if (instance == null) {
      String[] cfiles = new String[]{
        PropertyAccessor.getPropertyFileLocation() + "facts/service/fact-service-context.xml"
      };

      context = new FileSystemXmlApplicationContext(cfiles);
      calClient = (CommonDataLayerServiceClient) context.getBean(CAL_CLIENT_BEAN_NAME);

      log.debug("CAL properties=" + calClient.toString());
      log.debug("CAL service endpoint=" + calClient.getServiceEndpoint());

      extractorTypes = (Map) context.getBean(EXTRACTOR_TYPES_BEAN_NAME);

      Map registryProps = (Map) context.getBean(REGISTRY_PROPERTY_BEAN_NAME);
      registryServiceEndpoint = (String) registryProps.get("endpoint");
    }
  }

  public static CommonDataLayerFactServicePort getInstance() {
    synchronized (CommonDataLayerFactServiceImpl.class) {
      if (instance == null) {
        instance = new CommonDataLayerFactServiceImpl();
      }
    }

    return instance;
  }

  public FactQueryResponseType getDemographicsFact(FactQueryRequestType request) throws Exception {

    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient id in CareRecordPayload!");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (ptInfoHelper == null) {
      ptInfoHelper = new PatientInfoRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    PatientDemographicsPRPAIN201307UV02RequestType ptInfoRequest = ptInfoHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.getPatientInfo(ptInfoRequest);
    XMLUtils.printDOM(data, System.out);

    //----------------------------------------------------------------------
    // get person fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_DEMOGRAPHICS_FACT));

    //----------------------------------------------------------------------
    // extract demographics facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getSupportContactFacts(FactQueryRequestType request) throws Exception {

    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient id in CareRecordPayload!");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    PatientDemographicsPRPAIN201307UV02RequestType ptInfoRequest = ptInfoHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.getPatientInfo(ptInfoRequest);
    XMLUtils.printDOM(data, System.out);

    //----------------------------------------------------------------------
    // get fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_DEMOGRAPHICS_FACT));

    //----------------------------------------------------------------------
    // extract facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    for (Object fact : extractor.getFacts()) {
      System.out.println("Fact is of class=" + fact.getClass().getName());
      if (fact.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.SupportContactFactType")) {
        response.getFacts().add((FactType) fact);
      }
    }

    //System.out.println("# of support contact facts=" + response.getFacts().size());
    return response;
  }

  public FactQueryResponseType getAllergyFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("ALLCAT") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("INTOLIST")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of ALLCAT or INTOLIST.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getAllergies(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_ALLERGY_FACTS));

    //----------------------------------------------------------------------
    // extract allergy facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getImmunizationFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("IMMUCAT")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of IMMUCAT.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getImmunizations(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_IMMUNIZATION_FACTS));

    //----------------------------------------------------------------------
    // extract allergy facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getProblemFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("MEDCCAT") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("PROBLIST")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of MEDCCAT or PROBLIST.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getProblems(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get problems fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_PROBLEM_FACTS));

    //----------------------------------------------------------------------
    // extract problem facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getTestResultFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("LABCAT")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of LABCAT.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getTestResults(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_TEST_RESULT_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getMedicationFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("RXCAT") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("MEDLIST") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("CURMEDLIST") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("DISCHMEDLIST") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("HISTMEDLIST")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of RXCAT, MEDLIST, CURMEDLIST, DISCHMEDLIST, HISTMEDLIST.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getMedications(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get medications fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_MEDICATION_FACTS));

    //----------------------------------------------------------------------
    // extract medication facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getVitalsFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("COBSCAT")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of COBSCAT.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getVitals(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_VITALS_FACTS));

    //----------------------------------------------------------------------
    // extract allergy facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getProcedureFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("PSVCCAT")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of PSVCCAT.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getProcedures(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_PROCEDURE_FACTS));

    //----------------------------------------------------------------------
    // extract allergy facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getPatientFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getPatientSearchPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required PatientSearchPayload!");
    }

    if (request.getPatientSearchPayload().getId() == null &&
            request.getPatientSearchPayload().getName() == null) {
      throw new InvalidParameterException("Missing search criteria (id or name) in PatientSearchPayload!");
    }

    if (ptSearchHelper == null) {
      ptSearchHelper = new FindPatientsRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    FindPatientsPRPAIN201305UVRequestType ptSearchRequest = ptSearchHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.findPatients(ptSearchRequest);
    XMLUtils.printDOM(data, System.out);

    //----------------------------------------------------------------------
    // get person fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_PATIENT_FACTS));

    //----------------------------------------------------------------------
    // extract demographics facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getProviderFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getProviderSearchPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required ProviderSearchPayload!");
    }

    if ((request.getProviderSearchPayload().getId() == null ||
            request.getProviderSearchPayload().getId().isEmpty()) &&
            request.getProviderSearchPayload().getName() == null) {
      throw new InvalidParameterException("Missing search criteria (id or name) in ProviderSearchPayload!");
    }

    if (docSearchHelper == null) {
      docSearchHelper = new FindProvidersRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    ProviderDetailsPRPMIN306010UV1RequestType docSearchRequest = docSearchHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.findProviders(docSearchRequest);
    XMLUtils.printDOM(data, System.out);

    //----------------------------------------------------------------------
    // get person fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_PROVIDER_FACTS));

    //----------------------------------------------------------------------
    // extract demographics facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getOrderFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("OE") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("LABOE") &&
            !request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("MEDOR")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of OE, LABOE, or MEDOR.");
    }

    log.debug("PARAMS: id=" + request.getCareRecordPayload().getPatientId() +
            ",code=" + request.getCareRecordPayload().getCareProvisionCode());

    if (careRecordProfileHelper == null) {
      careRecordProfileHelper = new CareRecordProfileRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN043100UV01RequestType orderRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.getCareRecordQUPCIN043200UV01(orderRequest, request.getCareRecordPayload().getCareProvisionCode());

    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_ORDER_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public RecordOrderResponseType recordLabOrder(RecordOrderRequestType v) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public FactQueryResponseType getTestResultEventFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getResultEventPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required ResultEventPayload!");
    }

    if (request.getResultEventPayload().getPatientId() == null ||
            request.getResultEventPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in ResultEventPayload!");
    }

    if ((request.getResultEventPayload().getActId() == null ||
            request.getResultEventPayload().getActId().isEmpty()) &&
            (request.getResultEventPayload().getObservationType() == null)) {
      throw new InvalidParameterException("Missing lab result event id (accession number) and/or lab test id, i.e. LOINC Code, parameters!");
    }

    log.debug("PARAMS: actId=" + request.getResultEventPayload().getActId() +
            ",observationType=" + request.getResultEventPayload().getObservationType());

    if (resultQueryHelper == null) {
      resultQueryHelper = new ResultQueryRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    ResultQueryPOLBIN354000UV01MessageType resultQueryRequest = resultQueryHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.getTestResultEvents(resultQueryRequest);
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_TEST_RESULT_EVENT_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getAppointmentFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getEncounterPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required EncounterPayload!");
    }

    if (request.getEncounterPayload().getPatientId() == null ||
            request.getEncounterPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in EncounterPayload!");
    }

    if (request.getEncounterPayload().getTypeOfEncounter() == null ||
            request.getEncounterPayload().getTypeOfEncounter().isEmpty()) {
      throw new InvalidParameterException("Missing required typeOfEncounter parameter!");
    }

    // must have correct encounter type
    if (!request.getEncounterPayload().getTypeOfEncounter().equalsIgnoreCase("AMB")) {
      throw new InvalidParameterException("Required typeOfEncounter value of AMB.");
    }

    log.debug("PARAMS: id=" + request.getEncounterPayload().getPatientId() +
            ",type=" + request.getEncounterPayload().getTypeOfEncounter());

    if (encounterSearchHelper == null) {
      encounterSearchHelper = new FindEncountersRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    FindEncountersPRPAIN900300UV02RequestType encounterSearchRequest = encounterSearchHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.findEncounters(encounterSearchRequest);
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_APPOINTMENT_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getEncounterFacts(FactQueryRequestType request) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public FactQueryResponseType getEncounterDetailFacts(FactQueryRequestType request) throws Exception {

    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getRecordQueryPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required RecordQueryPayload!");
    }

    if (request.getRecordQueryPayload().getRecordId() == null ||
            request.getRecordQueryPayload().getRecordId().isEmpty()) {
      throw new InvalidParameterException("Missing required record id in RecordQueryPayload!");
    }

    if (request.getRecordQueryPayload().getTypeOfEncounter() == null ||
            request.getRecordQueryPayload().getTypeOfEncounter().isEmpty()) {
      throw new InvalidParameterException("Missing required typeOfEncounter parameter!");
    }

    // must have correct encounter type
    if (!request.getRecordQueryPayload().getTypeOfEncounter().equalsIgnoreCase("IMP") &&
            !request.getRecordQueryPayload().getTypeOfEncounter().equalsIgnoreCase("AMB")) {
      throw new InvalidParameterException("Required typeOfEncounter value of IMP or AMB.");
    }

    log.debug("PARAMS: record id=" + request.getRecordQueryPayload().getRecordId() +
            ",type=" + request.getRecordQueryPayload().getTypeOfEncounter() +
            ",patient=" + request.getRecordQueryPayload().getPatientId());

    if (careRecordHelper == null) {
      careRecordHelper = new CareRecordRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    CareRecordQUPCIN040100UV01RequestType encounterDetailRequest = careRecordHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = null;
    if ("IMP".equals(request.getRecordQueryPayload().getTypeOfEncounter())) {
      data = calClient.getImpEncounterDetails(encounterDetailRequest);
    } else if ("AMB".equals(request.getRecordQueryPayload().getTypeOfEncounter())) {
      data = calClient.getAmbEncounterDetails(encounterDetailRequest);
    } else {
      throw new InvalidParameterException("Unknown encounter detail type: " + request.getEncounterPayload());
    }

    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_ENCOUNTER_DETAILS_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getInsuranceFacts(FactQueryRequestType request) {

    // TODO:
    //
    // HELPER CLASS:  PatientInfoRequestHelper
    //
    // CAL METHOD:    CALL getPatientInfo()
    //

    throw new UnsupportedOperationException("Not supported yet.");
  }

  public FactQueryResponseType getAdmissionFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getEncounterPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required EncounterPayload!");
    }

    if (request.getEncounterPayload().getPatientId() == null ||
            request.getEncounterPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in EncounterPayload!");
    }

    if (request.getEncounterPayload().getTypeOfEncounter() == null ||
            request.getEncounterPayload().getTypeOfEncounter().isEmpty()) {
      throw new InvalidParameterException("Missing required typeOfEncounter parameter!");
    }

    // must have correct encounter type
    if (!request.getEncounterPayload().getTypeOfEncounter().equalsIgnoreCase("IMP")) {
      throw new InvalidParameterException("Required typeOfEncounter value of IMP.");
    }

    log.debug("PARAMS: id=" + request.getEncounterPayload().getPatientId() +
            ",type=" + request.getEncounterPayload().getTypeOfEncounter());

    if (encounterSearchHelper == null) {
      encounterSearchHelper = new FindEncountersRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    FindEncountersPRPAIN900300UV02RequestType encounterSearchRequest = encounterSearchHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.findEncounters(encounterSearchRequest);
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_ADMISSION_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getRegistryFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    long ptId = Long.parseLong(request.getCareRecordPayload().getPatientId());

    //----------------------------------------------------------------------
    // Init registry service
    //----------------------------------------------------------------------
    mil.navy.med.dzreg.service.RegistriesService service = new mil.navy.med.dzreg.service.RegistriesService();
    mil.navy.med.dzreg.service.RegistriesServicePortType port = service.getRegistriesServicePort();
    ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
            javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
            registryServiceEndpoint);

    //----------------------------------------------------------------------
    // Call registry service
    //----------------------------------------------------------------------
    mil.navy.med.dzreg.types.PersonRegistryProfileRequestType parameters = new mil.navy.med.dzreg.types.PersonRegistryProfileRequestType();
    parameters.setId(ptId);
    mil.navy.med.dzreg.types.PersonRegistryProfileResponseType result = port.getRegistryProfile(parameters);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    if ((result != null) && (result.getProfile() != null) && (result.getProfile().getRegistry() != null) && (result.getProfile().getRegistry().size() > 0)) {
      Iterator<RegistryProfileType> iterator = result.getProfile().getRegistry().iterator();
      while (iterator.hasNext()) {
        RegistryProfileType profile = iterator.next();
        RegistryProfileFactType fact = new RegistryProfileFactType();

        ValueType ptValue = new ValueType();
        ptValue.setValue(String.valueOf(result.getProfile().getPerson().getId()));
        fact.setPatientId(ptValue);

        String name = result.getProfile().getPerson().getName();
        NameFactType ptName = new NameFactType();
        int lastIdx = name.lastIndexOf(' ');
        if (lastIdx > 0) {
          ptName.setFamilyName(name.substring(name.lastIndexOf(' ')));
          ptName.setFirstName(name.substring(0, name.lastIndexOf(' ')));
        } else {
          ptName.setFamilyName(name);
        }
        fact.setPatientName(ptName);

        CodeLabelPair registryCode = new CodeLabelPair();
        registryCode.setCode(String.valueOf(profile.getRegistryId()));
        registryCode.setLabel(profile.getRegistryDesc());
        fact.setRegistry(registryCode);

        fact.setRegisteredDate(profile.getRegisteredDate().toGregorianCalendar().getTime());
        fact.setActive(profile.isActive());
        fact.setDataSource(result.getProfile().getDataSource());

        //Add fact to response
        response.getFacts().add(fact);
      }
    }

    return response;
  }

  public FactQueryResponseType getImagingResultFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in CareRecordPayload!");
    }

    if (request.getCareRecordPayload().getCareProvisionCode() == null ||
            request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
      throw new InvalidParameterException("Missing required sareProvisionCode parameter!");
    }

    // must have correct care provision code
    if (!request.getCareRecordPayload().getCareProvisionCode().equalsIgnoreCase("DICAT")) {
      throw new InvalidCareProvisionCodeException("Required careProvisionCode value of DICAT.");
    }

    log.debug("id=" + request.getCareRecordPayload().getPatientId());

    CareRecordQUPCIN043100UV01RequestType careRecordRequest = careRecordProfileHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    //Document data = calClient.getTestResults(careRecordRequest);
    Document data = calClient.getCareRecordQUPCIN043200UV01(careRecordRequest, request.getCareRecordPayload().getCareProvisionCode());
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_IMAGING_RESULT_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }

  public FactQueryResponseType getAppointmentScheduleFacts(FactQueryRequestType request) throws Exception {
    FactQueryResponseType response = new FactQueryResponseType();

    if (request == null || request.getScheduleSearchPayload() == null) {
      throw new InvalidParameterException("Bad request and/or missing required ScheduleSearchPayload!");
    }

    if (request.getScheduleSearchPayload().getScheduleId() == null &&
        request.getScheduleSearchPayload().getSdlcId() == null &&
        request.getScheduleSearchPayload().getSlotType() == null &&
        request.getScheduleSearchPayload().getScheduleId().isEmpty() &&
        request.getScheduleSearchPayload().getSdlcId().isEmpty() &&
        request.getScheduleSearchPayload().getSlotType().isEmpty()) {
      throw new InvalidParameterException("Missing required search parameters: scheduleId, sdlcId, slotType!");
    }

    log.debug("scheduleId=" + request.getScheduleSearchPayload().getScheduleId() +
              ",slotType=" + request.getScheduleSearchPayload().getSlotType() +
              ",sdlcId=" + request.getScheduleSearchPayload().getSdlcId() +
              ",performerId=" + request.getScheduleSearchPayload().getPerformerId());

    if (slotQueryHelper == null) {
      slotQueryHelper = new SlotQueryRequestHelper(calClient.getHl7Props(), calClient.getObjectFactory());
    }

    SlotRequestPRSCIN030101UVMessageType slotRequest = slotQueryHelper.createRequest(request);

    //----------------------------------------------------------------------
    // get results from data sources
    //----------------------------------------------------------------------
    Document data = calClient.findSlots(slotRequest);
    if (log.isDebugEnabled()) {
      XMLUtils.printDOM(data, System.out);
    }

    //----------------------------------------------------------------------
    // get test results fact extractor to use
    //----------------------------------------------------------------------
    FactExtractor extractor = FactExtractorFactory.getInstance().getExtractor((String) extractorTypes.get(GET_APPT_SCHEDULE_FACTS));

    //----------------------------------------------------------------------
    // extract test result facts
    //----------------------------------------------------------------------
    extractor.extract(data);

    //----------------------------------------------------------------------
    // formulate response
    //----------------------------------------------------------------------
    response.getFacts().addAll((ArrayList) extractor.getFacts());

    return response;
  }
}
