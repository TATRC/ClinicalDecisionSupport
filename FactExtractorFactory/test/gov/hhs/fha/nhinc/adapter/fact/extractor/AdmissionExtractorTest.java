/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.AdmissionFactType;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

/**
 *
 * @author kim
 */
public class AdmissionExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/inpatient-encounters.xml"
  };
  private int[] factsCount = {1};
  private String[] factsPatientId = {"11428"};
  private FactExtractor extractor = null;

  public AdmissionExtractorTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  /**
   * Test of getPatientInfo method, of class CommonDataLayerServiceClient.
   */
  @Test
  public void testAppointmentExtractor() {
    System.out.println("testAdmissionExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runAdmissionExtractor(xmlFile);

        for (Object fact : facts.toArray()) {
          System.out.println(fact);
        }

        // check results
        checkFacts(i++, facts);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void checkFacts(int testIndex, List facts) {
    AdmissionFactType apptFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      apptFact = (AdmissionFactType) fact;
      assertEquals(factsPatientId[testIndex], apptFact.getPatientId().getValue());
    }
  }

  private List<Object> runAdmissionExtractor(String testfile) throws Exception {
    String extractorType = "InpatientEncounter";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
