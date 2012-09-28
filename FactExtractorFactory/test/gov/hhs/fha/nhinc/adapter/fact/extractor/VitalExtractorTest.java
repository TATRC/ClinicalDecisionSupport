/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.VitalFactType;
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
public class VitalExtractorTest {

  private String[] testXmlFiles = {
    "test/data/ihs/vitals-nodata.xml.xml",
    "test/data/ihs/vitals-response-14484-09-10.xml",
    "test/data/ihs/vitals-multi-events-14484.xml"
  };
  private int[] factsCount = {0, 42, 15};
  private String[] factsPatientId = {"14484", "14484", "14484"};
  private FactExtractor extractor = null;

  public VitalExtractorTest() {
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
  public void testVitalsExtractor() {
    System.out.println("testVitalsExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runVitalsExtractor(xmlFile);

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
    VitalFactType vitalFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      vitalFact = (VitalFactType) fact;
      assertEquals(factsPatientId[testIndex], vitalFact.getPatientId().getValue());
    }
  }

  private List<Object> runVitalsExtractor(String testfile) throws Exception {
    String extractorType = "CareRecordVitals";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
