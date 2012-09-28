/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.fact;

import gov.hhs.fha.nhinc.adapter.fact.extractor.FactExtractorFactory;
import gov.hhs.fha.nhinc.adapter.fact.extractor.FactExtractor;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kim
 */
public class FactExtractorFactoryTest {

    public FactExtractorFactoryTest() {
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
    * Test get allergies extractor.
    */
   @Test
   public void testGetAllergyExtractor() throws Exception {
      System.out.println("GetAllergyExtractor");
      String type = "CareRecordAllergy";
      String expResultOfType = "gov.hhs.fha.nhinc.adapter.fact.extractor.AllergiesFactExtractor";
      FactExtractor result = FactExtractorFactory.getExtractor(type);
      assertEquals(expResultOfType, result.getClass().getName());
   }

   /**
    * Test get demographics extractor.
    */
   @Test
   public void testGetDemographicsExtractor() throws Exception {
      System.out.println("GetDemographicsExtractor");
      String type = "PatientDemographics";
      String expResultOfType = "gov.hhs.fha.nhinc.adapter.fact.extractor.FactExtractorImpl";
      FactExtractor result = FactExtractorFactory.getExtractor(type);
      assertEquals(expResultOfType, result.getClass().getName());
   }

   /**
    * Test of getExtractorTypes method, of class FactExtractorFactory.
    */
   @Test
   public void testGetExtractorTypes() throws Exception {
      System.out.println("getExtractorTypes");
      int count = 2;
      Set<String> result = FactExtractorFactory.getExtractorTypes();
      assertEquals(count, result.size());

      for (Object type: result.toArray()) {
         System.out.println("Extractor type: " + type);
      }
   }
}