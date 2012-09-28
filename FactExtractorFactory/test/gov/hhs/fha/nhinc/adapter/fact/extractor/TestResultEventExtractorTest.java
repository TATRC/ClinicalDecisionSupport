/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.ResultFactType;
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
public class TestResultEventExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/result-event-query-response_singletest.xml",
    "test/data/static/result-event-query-response_wbattery.xml"
  };
  private int[] factsCount = {1, 2};
  private String[] factsPatientId = {"99000002768", "99000002768"};
  private FactExtractor extractor = null;

  public TestResultEventExtractorTest() {
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
  public void testResultsEventExtractor() {
    System.out.println("testResultsEventExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runResultEventExtractor(xmlFile);

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
    ResultFactType resultFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      resultFact = (ResultFactType) fact;
      assertEquals(factsPatientId[testIndex], resultFact.getPatientId().getValue());
    }
  }
  
  private List<Object> runResultEventExtractor(String testfile) throws Exception {
    String extractorType = "ResultEventMessage";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
