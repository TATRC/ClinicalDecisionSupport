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
public class PtSearchFactExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/find-patients-single.xml",
    "test/data/static/find-patients-multiple.xml"
  };
  private int[] factsCount = {2, 5};

  private FactExtractor extractor = null;

  public PtSearchFactExtractorTest() {
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
      String extractorType = "PatientSearch";
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
  public void testPersonFactExtractor() {
    System.out.println("testPtSearchFactExtractor");
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
