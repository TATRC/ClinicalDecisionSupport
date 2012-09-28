/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.knowledgemodule.client;

import gov.hhs.fha.nhinc.kmr.kmtypes.KmLatestLogicResponseType;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tmn
 */
public class KnowledgeModuleServiceClientTest {


    public KnowledgeModuleServiceClientTest() {
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

   @Test
   public void testGetModelIds() {
      System.out.println("GetModelIds");
      String requestId = "99";
      List expResult = null;
      List result = KnowledgeModuleServiceClient.getModelIds(requestId);
      assertEquals(expResult, result);
      fail("The test case is a prototype.");
   }

   /**
    * TODO: Use our KMR return type to send back for now, until we
    *       know what ORYX wants returned.
    */
   @Test
   public void testGetModelForm() {
      System.out.println("GetModelForm");
      String requestId = "1";
      List expResult = null;
      KmLatestLogicResponseType result = KnowledgeModuleServiceClient.getModelForm(requestId);
      assertEquals(expResult, result);
      fail("The test case is a prototype.");
   }

}