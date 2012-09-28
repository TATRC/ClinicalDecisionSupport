/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.EncounterFactType;
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
public class EncounterExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/outpatient-encounter-details.xml",
    "test/data/static/inpatient-encounter-details.xml",
  };
  private int[] factsCount = {1, 1};
  private String[] factsPatientId = {"83452", "83452"};
  private FactExtractor extractor = null;

  public EncounterExtractorTest() {
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
  public void testEncounterExtractor() {
    System.out.println("testEncounterExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runEncounterExtractor(xmlFile);

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
    EncounterFactType apptFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      apptFact = (EncounterFactType) fact;
      assertEquals(factsPatientId[testIndex], apptFact.getPatientId().getValue());
    }
  }

  private List<Object> runEncounterExtractor(String testfile) throws Exception {
    String extractorType = "EncounterDetails";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
