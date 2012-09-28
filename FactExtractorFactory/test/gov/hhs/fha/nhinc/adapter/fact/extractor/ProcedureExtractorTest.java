/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.ProcedureFactType;
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
public class ProcedureExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/procedures.xml"
  };
  private int[] factsCount = {1};
  private String[] factsPatientId = {"8237363"};
  private FactExtractor extractor = null;

  public ProcedureExtractorTest() {
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
  public void testVitalsExtractor() {
    System.out.println("testProcedureExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runProcedureExtractor(xmlFile);

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
    ProcedureFactType procedureFact = null;

    assertEquals(factsCount[testIndex], facts.size());

    for (Object fact : facts.toArray()) {
      procedureFact = (ProcedureFactType) fact;
      assertEquals(factsPatientId[testIndex], procedureFact.getPatientId().getValue());
    }
  }

  private List<Object> runProcedureExtractor(String testfile) throws Exception {
    String extractorType = "CareRecordProcedure";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
