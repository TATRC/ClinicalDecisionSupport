/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.ImmunizationFactType;
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
public class ImmunizationsExtractorTest {

   //------------------------
   //testXmlFiles --> test data file(s) to be tested against.
   //factsCount --> total number of the repeating facts (pertinentInformation3)
   //------------------------
   private String[] testXmlFiles = {
               "test/data/ihs/immunizations-single-event-14400.xml"
              ,"test/data/ihs/immunizations-multi-events-14402.xml"
   };
   private int[] factsCount = {1, 3};
   private String[] factsPatientId = {"14400, 14402"};
   private FactExtractor extractor = null;

   public ImmunizationsExtractorTest() {
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
    * Test of ...
    */
   @Test
   public void testImmunizationsExtractor() {
      System.out.println("testImmunizationExtractor");
      int i = 0;

      try {
         for (String xmlFile : testXmlFiles) {

System.out.println("\t\t\t=================================================");
System.out.println("\t\t\t"+xmlFile);
System.out.println("\t\t\t=================================================");

            List<Object> facts = runExtractor(xmlFile);

            for (Object fact : facts.toArray()) {
               System.out.println(fact);
            }

            // check results
//            checkFacts(i++, facts);
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void checkFacts(int testIndex, List facts) {
      ImmunizationFactType immuneFact = null;

      assertEquals(factsCount[testIndex], facts.size());

      for (Object fact : facts.toArray()) {
         immuneFact = (ImmunizationFactType) fact;
         assertEquals(factsPatientId[testIndex], immuneFact.getPatientId().getValue());
      }
   }

   private List<Object> runExtractor(String testfile) throws Exception {
      String extractorType = "CareRecordImmunization";
      extractor = FactExtractorFactory.getInstance().getExtractor(extractorType);

      // read local test file
      Document result = TestUtilities.getXML(testfile);
      //System.out.println(XMLUtils.documentToString(result));
      XMLUtils.printDOM(result, System.out);

      extractor.extract(result);
      return extractor.getFacts();
   }
}
