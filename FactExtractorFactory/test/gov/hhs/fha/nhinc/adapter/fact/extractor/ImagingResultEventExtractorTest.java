/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.ImagingResultFactType;
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
public class ImagingResultEventExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/imaging-results-single.xml"
  };
  private int[] factsCount = {1};
  private String[] factsPatientId = {"8237363"};
  private FactExtractor extractor = null;

  public ImagingResultEventExtractorTest() {
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
  public void imagingResultEventExtractor() {
    System.out.println("imagingResultEventExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runEventExtractor(xmlFile);

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
    ImagingResultFactType resultFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      resultFact = (ImagingResultFactType) fact;
      assertEquals(factsPatientId[testIndex], resultFact.getPatientId().getValue());
    }
  }
  
  private List<Object> runEventExtractor(String testfile) throws Exception {
    String extractorType = "CareRecordImagingResult";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
