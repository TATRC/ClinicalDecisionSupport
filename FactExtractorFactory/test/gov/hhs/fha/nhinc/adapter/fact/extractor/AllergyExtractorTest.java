/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.AllergyFactType;
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
public class AllergyExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/allergies-multi-events.xml",
    "test/data/ihs/allergies-single-event-30197.xml",
    "test/data/ihs/allergies-multi-events-41519.xml",
    "test/data/ihs/allergies-multi-events-wzone-41519.xml"
  };
  private int[] factsCount = {3, 1, 2, 2};
  private String[] factsPatientId = {"7", "30197", "41519", "41519"};
  private FactExtractor extractor = null;

  public AllergyExtractorTest() {
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
  public void testAllergiesExtractor() {
    System.out.println("testAllergiesExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runAllergiesExtractor(xmlFile);

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
    AllergyFactType allergyFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      allergyFact = (AllergyFactType) fact;
      assertEquals(factsPatientId[testIndex], allergyFact.getPatientId().getValue());
    }
  }

  private List<Object> runAllergiesExtractor(String testfile) throws Exception {
    String extractorType = "CareRecordAllergy";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
