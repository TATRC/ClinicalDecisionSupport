/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.MedicationFactType;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import static org.junit.Assert.*;

/**
 *
 * @author kim
 */
public class MedicationExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/meds-single-event.xml",
    "test/data/ihs/meds-multi-events-14484.xml",
    "test/data/ihs/meds-multi-events-41519.xml",    
    "test/data/ihs/meds-multi-events-wzone-14484.xml",
    "test/data/openvista/meds-multiples-7.xml"
  };
  private int[] factsCount = {1, 8, 0, 8, 29};
  private String[] factsPatientId = {"7", "14484", "41519", "14484", "7"};

  private FactExtractor extractor = null;

  public MedicationExtractorTest() {
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

  @After
  public void tearDown() {
  }

  /**
   * Test of testxtractorOneEvent method.
   */
  @Test
  public void testMedicationsExtractor() {
    System.out.println("testMedicationsExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runMedsExtractor(xmlFile);

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
    MedicationFactType allergyFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      allergyFact = (MedicationFactType) fact;
      assertEquals(factsPatientId[testIndex], allergyFact.getPatientId().getValue());
    }
  }

  private List<Object> runMedsExtractor(String testfile) throws Exception {
    String extractorType = "CareRecordMedication";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
