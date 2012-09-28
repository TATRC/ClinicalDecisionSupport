/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.ProblemFactType;
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
public class ProblemExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/problems-1-event.xml",
    "test/data/ihs/problems-multi-events-53009.xml",
    "test/data/ihs/problems-multi-events-14484.xml",
    "test/data/ihs/problems-multi-events-wzone-14484.xml"
  };
  private int[] factsCount = {4, 2, 10, 10};
  private String[] factsPatientId = { "7", "53009", "14484", "14484" };

  private FactExtractor extractor = null;

  public ProblemExtractorTest() {
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
   * Test of testProblemExtractorOneEvent method.
   */
  @Test
  public void testProblemExtractor() {
    System.out.println("testProblemExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runProblemExtractor(xmlFile);

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
    ProblemFactType problemFact = null;
    
    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      problemFact = (ProblemFactType) fact;
      assertEquals(factsPatientId[testIndex], problemFact.getPatientId().getValue());
    }
  }

  private List<Object> runProblemExtractor(String testfile) throws Exception {
    String extractorType = "CareRecordProblem";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
