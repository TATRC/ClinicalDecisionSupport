/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import java.util.List;
import org.junit.After;
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
public class ScheduleFactExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/slots-query-response.xml"
  };
  private int[] factsCount = {5};

  private FactExtractor extractor = null;

  public ScheduleFactExtractorTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
    try {
      String extractorType = "ScheduleSearch";
      extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @After
  public void tearDown() {
  }

  /**
   * Test using DemographicsFactExtractor to extract metadata
   */
  @Test
  public void testScheduleExtractor() {
    System.out.println("testScheduleExtractor");
    int i = 0;
    Document result = null;

    try {
      for (String xmlFile : testXmlFiles) {
        // read local test file
        result = TestUtilities.getXML(xmlFile);
        //System.out.println(XMLUtils.documentToString(result));
        XMLUtils.printDOM(result, System.out);

        extractor.extract(result);
        List<Object> facts = extractor.getFacts();

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
    assertEquals(factsCount[testIndex], facts.size());
  }
}
