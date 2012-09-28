/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.properties.PropertyAccessor;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author kim
 */
public class FactExtractorFactory {

  private final static Log log = LogFactory.getLog(FactExtractorFactory.class);
  private final static String NAME_TYPES_MAP_BEAN = "demographics.NameTypes";
  private final static String FACTORY_BEAN_NAME = "extractor.factory";
  private final static String CONTACT_TYPES_MAP_BEAN = "support.ContactTypes";
  private final static String TELECOM_USE_TYPES_MAP_BEAN = "telecom.UseTypes";
  private final static String ADDRESS_USE_TYPES_MAP_BEAN = "address.UseTypes";
  
  public static String LEGAL_NAME_INDICATOR = "L";
  public static String CONTACT_TYPE_SYSTEM = "2.16.840.1.113883.3.88.12.3221.3.2";
  public static String CONTACT_TYPE_SYSTEM_NAME = "HL7 Role Class";
  public static String NPI_EXTENSION = "2.16.840.1.113883.4.6";
  
  private Map<String, String> factExtractors;
  private static FactExtractorFactory instance = null;
  private static ApplicationContext context = null;
  private List contextFiles;
  /**
   * A set of codes represent the different types of name, i.e. legal name, alias, former name
   */
  private static Map nameTypes;
  /**
   * A set of codes represent the different types of name, i.e. legal name, alias, former name
   */
  private static Map contactTypes;
  /**
   * A set of codes represent the different types of usages for phone numbers
   */
  private static Map telecomUseTypes;
  /**
   * A set of codes represent the different types of usages for address
   */
  private static Map addressUseTypes;

  public static FactExtractorFactory getInstance() {
    synchronized (FactExtractorFactory.class) {
      if (instance == null) {
        String[] cfiles = new String[]{
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/xpath-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/demographics-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/support-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/allergy-message-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/allergy-reaction-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/admission-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/appointment-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/encounter-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/problem-message-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/procedure-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/testresult-event-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/testresult-message-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/labtest-event-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/labtest-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/results-event-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/results-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/medication-message-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/med-order-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/ptsearch-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/provider-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/immunization-message-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/immune-reaction-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/vital-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/order-history-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/lab-order-history-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/med-order-history-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/imaging-result-message-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/imaging-result-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/schedule-fact-context.xml",
          PropertyAccessor.getPropertyFileLocation() + "facts/extractor/extractors-context.xml"
        };

        context = new FileSystemXmlApplicationContext(cfiles);
        instance = (FactExtractorFactory) context.getBean(FACTORY_BEAN_NAME);

        nameTypes = (Map) context.getBean(NAME_TYPES_MAP_BEAN);

        contactTypes = (Map) context.getBean(CONTACT_TYPES_MAP_BEAN);

        telecomUseTypes = (Map) context.getBean(TELECOM_USE_TYPES_MAP_BEAN);

        addressUseTypes = (Map) context.getBean(ADDRESS_USE_TYPES_MAP_BEAN);
      }
    }

    return instance;
  }

  /**
   * Return a FactExtractor object for a request fact type.
   * Use getExtractorTypes() to get a list of valid fact types
   *
   * @param type A fact type, i.e. PatientDemographics, CareRecordAllergy.
   *             Uses getExtractorTypes() to get a list of valid fact types.
   * @return FactExtractor
   * @throws java.lang.Exception
   */
  public FactExtractor getExtractor(String type) throws Exception {
    if (type == null || type.isEmpty()) {
      log.error("Type = " + type + " is not supported!");
      throw new InvalidFactExtractorTypeException("Type = " + type + " is not supported!");
    }

    try {
      if (!factExtractors.containsKey(type)) {
        log.error("Type = " + type + " is not supported!");
        throw new InvalidFactExtractorTypeException("Type = " + type + " is not supported!");
      }

      String extractorBean = (String) factExtractors.get(type);
      //System.out.println("extractorBean= "+ extractorBean); //tmn
      return (FactExtractor) context.getBean(extractorBean);
    } catch (Exception e) {
      log.error("Exception: " + e);
      throw new Exception("Failed to construct FactExtractor object!");
    }
  }

  /**
   * Returns all available extractor types.
   *
   * @return  Set<String>
   * @throws java.lang.Exception
   */
  public Set<String> getExtractorTypes() throws Exception {
    return factExtractors.keySet();
  }

  public void setFactExtractors(Map<String, String> factExtractors) {
    this.factExtractors = factExtractors;
  }

  public void setContextFiles(List contextFiles) {
    this.contextFiles = contextFiles;
  }

  public String getLegalNameLabel() {
    return (String) nameTypes.get(LEGAL_NAME_INDICATOR);
  }

  public String getNameTypeLabel(String code) {
    if (code != null && !code.isEmpty())
      return (String) nameTypes.get(code);

    return "";
  }

  public String getContactType(String type) {
    if (type != null && !type.isEmpty())
      return (String) contactTypes.get(type);

    return "";
  }

  public String getTelecomUseTypes(String type) {
    if (type != null && !type.isEmpty())
      return (String) telecomUseTypes.get(type);

    return "";
  }

  public String getAddressUseTypes(String type) {
    if (type != null && !type.isEmpty())
      return (String) addressUseTypes.get(type);

    return "";
  }
}
