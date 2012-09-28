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
public class SupportExtractorTest {

  private String[] testXmlFiles = {
    "test/data/ihs/demographics-52841.xml"
  };
  private int[] factsCount = {2};

  private FactExtractor extractor = null;

  public SupportExtractorTest() {
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
   * Test using DemographicsFactExtractor to extract metadata
   */
  @Test
  public void testSupportFactExtractor() {
   System.out.println("testSupportFactExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runSupportExtractor(xmlFile);

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

    int count = 0;
    for (Object fact : facts.toArray()) {
      if (fact.getClass().getName().equalsIgnoreCase("gov.hhs.fha.nhinc.adapter.fact.SupportContactFactType"))
        count++;
    }
    assertEquals(factsCount[testIndex], count);
  }

  private List<Object> runSupportExtractor(String testfile) throws Exception {
    String extractorType = "PatientDemographics";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
