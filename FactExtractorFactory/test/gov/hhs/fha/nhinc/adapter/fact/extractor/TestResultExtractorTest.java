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
public class TestResultExtractorTest {
  
  private String[] testXmlFiles = {
    "test/data/static/results-battery-test.xml"
    //"test/data/ihs/results-nodata-30197.xml",
    //"test/data/ihs/results-multi-events-2.xml",
    //"test/data/ihs/results-multi-events-30354.xml",
    //"test/data/ihs/results-multi-events-14484.xml",
    //"test/data/ihs/results-multi-events-wzone-14484.xml"
  };

  private int[] factsCount = {4, 0, 6, 4, 50, 50};
  private String[] factsPatientId = {"7", "30197", "53009", "30354", "14484", "14484"};
  private FactExtractor extractor = null;

  public TestResultExtractorTest() {
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
  public void testResultsExtractor() {
    System.out.println("testResultsExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runResultExtractor(xmlFile);

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
  
  private List<Object> runResultExtractor(String testfile) throws Exception {
    String extractorType = "CareRecordTestResult";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
