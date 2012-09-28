/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.GenericOrderFactType;
import gov.hhs.fha.nhinc.adapter.fact.LabOrderFactType;
import gov.hhs.fha.nhinc.adapter.fact.MedOrderFactType;
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
public class OrderExtractorTest {

  private String[] testXmlFiles = {
    "test/data/static/lab-orders-single.xml",
    "test/data/static/med-orders-single.xml",
    "test/data/static/multi-orders-events.xml",
  };
  private int[] labFactsCount = {1, 0, 1};
  private int[] medFactsCount = {0, 1, 1};
  private String[] factsPatientId = {"8237363", "8237363", "8237363", "8237363"};
  private FactExtractor extractor = null;

  public OrderExtractorTest() {
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
   * Test extractor
   */
  @Test
  public void testOrderExtractor() {
    System.out.println("testOrderExtractor");
    int i = 0;

    try {
      for (String xmlFile : testXmlFiles) {
        List<Object> facts = runOrderExtractor(xmlFile);

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
    GenericOrderFactType orderFact = null;

    //Count fact types
    int labCount = 0;
    int medCount = 0;
    for (Object fact : facts.toArray()) {
        if (fact instanceof LabOrderFactType) {
            labCount++;
        }
        if (fact instanceof MedOrderFactType) {
            medCount++;
        }
    }

    assertEquals(labFactsCount[testIndex], labCount);
    assertEquals(medFactsCount[testIndex], medCount);

    for (Object fact : facts.toArray()) {
      orderFact = (GenericOrderFactType) fact;
      assertEquals(factsPatientId[testIndex], orderFact.getPatientId().getValue());
    }
  }

  private List<Object> runOrderExtractor(String testfile) throws Exception {
    String extractorType = "OrderHistory";
    extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

    // read local test file
    Document result = TestUtilities.getXML(testfile);
    //System.out.println(XMLUtils.documentToString(result));
    XMLUtils.printDOM(result, System.out);

    extractor.extract(result);
    return extractor.getFacts();
  }
}
