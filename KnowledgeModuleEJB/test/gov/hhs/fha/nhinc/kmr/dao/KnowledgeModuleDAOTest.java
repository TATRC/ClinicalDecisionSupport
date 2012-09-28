/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ACLPermissionListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ACLPermissionType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ACLSimpleType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FactSpecificationListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FactSpecificationType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FindKmIdsResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ImportRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ImportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.InferenceListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMSpecialtyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVFactDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVInferenceEngineDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVPopulationDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVTaskDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVersionType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmFullResponse;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmImportRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmVersionListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmsListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.PopulationDependencyListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.PopulationSpecificationType;
import gov.hhs.fha.nhinc.kmr.kmtypes.RequestRefType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.SpecialtyListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.TTSpecificationType;
import gov.hhs.fha.nhinc.kmr.kmtypes.TaskListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.UserRoleType;
import gov.hhs.fha.nhinc.kmr.model.KnowledgeModule;
import gov.hhs.fha.nhinc.kmr.util.DateUtils;
import javax.xml.datatype.DatatypeConfigurationException;
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
public class KnowledgeModuleDAOTest {

    public KnowledgeModuleDAOTest() {
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
    * Test of getInstance method, of class KnowledgeModuleDAO.
    */
   //@Test
   public void testGetInstance() {
      System.out.println("getInstance");
      KnowledgeModuleManager expResult = null;
      KnowledgeModuleManager result = KnowledgeModuleDAO.getInstance();
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of findKmIds method, of class KnowledgeModuleDAO.
    */
   //@Test
   public void testFindKmIds() {
      System.out.println("findKmIds");
      KmRequestType params = null;
      KnowledgeModuleDAO instance = null;
      FindKmIdsResponseListType expResult = null;
      FindKmIdsResponseListType result = instance.findKmIds(params);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of findByParams method, of class KnowledgeModuleDAO.
    */
   //@Test
   public void testFindByParams() {
      System.out.println("findByParams");
      KmRequestType params = null;
      KnowledgeModuleDAO instance = null;
      ResponseListType expResult = null;
      ResponseListType result = instance.findByParams(params);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   /**
    * Test of findByParamsForRuntime method, of class KnowledgeModuleDAO.
    */
   //@Test
   public void testFindByParamsForRuntime() {
      System.out.println("findByParamsForRuntime");
      KmRequestType params = null;
      KnowledgeModuleDAO instance = null;
      ResponseListType expResult = null;
      ResponseListType result = instance.findByParamsForRuntime(params);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }


   /**
    * Test of insertKms method, of class KnowledgeModuleDAO.
    */
   @Test
   public void testInsertKms_KmImportRequestType() throws DatatypeConfigurationException {
      System.out.println("insertKms");
      
      KmImportRequestType params = this.createImportTestRequest();

      KnowledgeModuleManager instance = KnowledgeModuleDAO.getInstance();
      
      ImportResponseListType expResult = null;
      ImportResponseListType result = instance.insertKms(params);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }


   /**
    * Test of updateKms method, of class KnowledgeModuleDAO.
    */
   //@Test
   public void testUpdateKms() {
      System.out.println("updateKms");
      KmImportRequestType params = null;
      KnowledgeModuleDAO instance = null;
      ImportResponseListType expResult = null;
      ImportResponseListType result = instance.updateKms(params);
      assertEquals(expResult, result);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

   private KmImportRequestType createImportTestRequest() throws DatatypeConfigurationException {
      DateUtils helper = new DateUtils();
      KmImportRequestType request = new KmImportRequestType();
      ImportRequestType r = new ImportRequestType();    
      
      RequestRefType rr = new RequestRefType();
      rr.setRequestDate(helper.getCurrDateTimeAsXMLGregorian().toXMLFormat());
      rr.setRequestId("9090");
      r.setRequestReference(rr);

      ACLSimpleType acl = new ACLSimpleType();
      acl.setURName("Providers-Physicians");
      acl.setUserOrganizationLevel("Local");
      acl.setAuthorId("89");
      r.setACL(acl);

      r.setImportFilename("ImportTestFile9090");

      //------ BUILD KM -------------
      KmsListType kmlist = new KmsListType();
      kmlist.getKm().add(createTestData("testKM_1"));
      //kmlist.getKm().add(createTestData("testKM_2"));
      
      //------ ADD TO LIST -------------
      r.setKms(kmlist);
      request.setRequest(r);
      return request;
   }

   private KmFullResponse createTestData(String kmname) throws DatatypeConfigurationException {
      DateUtils helper = new DateUtils();

      //TEST KM #1
      KmFullResponse aKm = new KmFullResponse();
      aKm.setKmName(kmname);
      aKm.setKmType("testKMtype");
      aKm.setKmDescription("KM description");
      aKm.setKmOrganizationLevel("LOCAL");
      aKm.setCreatedTimestamp("2010-05-22T00:03:22.000-00:07");
      aKm.setIsCheckedOut(Boolean.TRUE);
      //-------- SPECIALTY 1 ------------
      SpecialtyListType s = new SpecialtyListType();
      KMSpecialtyType aSpecialty = new KMSpecialtyType();
      aSpecialty.setTerminologyScheme("specialtyScheme1");
      aSpecialty.setTerminologyCode("88");
      s.getKMSpecialty().add(aSpecialty);
      aKm.setSpecialtyList(s);
      //------- SPECIALTY 2 ---------------      
      aSpecialty = new KMSpecialtyType();
      aSpecialty.setTerminologyScheme("specialtyScheme2");
      aSpecialty.setTerminologyCode("8888");
      s.getKMSpecialty().add(aSpecialty);
      aKm.setSpecialtyList(s);
      //---------- KMV ----------------
      KmVersionListType kmvs = new KmVersionListType();
      KMVersionType aKmv = new KMVersionType();
      aKmv.setKMVName(kmname +" KMV");
      aKmv.setLMTID(0);
      String logic = "UjBsR09EbGhjZ0dTQUxNQUFBUUNBRU1tQ1p0dU1GUXhEUzhi";
      aKmv.setLogicNativeForm(logic.getBytes());
      {
         FactSpecificationListType facts = new FactSpecificationListType();
         //=========================================
         //----------- FACT 1 ---------------
         KMVFactDependencyType factdep = new KMVFactDependencyType();
         factdep.setDescription("FACT DEP 1");
         factdep.setType("LAB");
         //----------- FACT 1/SPEC ---------
         FactSpecificationType factspec = new FactSpecificationType();
         factspec.setTerminologyScheme("FACTSPEC 1 SCHEME");
         factspec.setTerminologyCode("FACTSPEC 1 CODE");
         factdep.getFactSpecification().add(factspec);
         //----------- FACT 1/SPEC ---------
         factspec = new FactSpecificationType();
         factspec.setTerminologyScheme("FACTSPEC 2 SCHEME");
         factspec.setTerminologyCode("FACTSPEC 2 CODE");
         factdep.getFactSpecification().add(factspec);
         //----
         facts.getKMVFactDependency().add(factdep);
         //=========================================
         aKmv.setFactSpecificationList(facts);
      }
      {
         PopulationDependencyListType pops = new PopulationDependencyListType();
         //=========================================
         //----------- POPULATION 1 ---------------
         KMVPopulationDependencyType popdep = new KMVPopulationDependencyType();
         popdep.setPDDescription("POPULATION DEP 1");
         popdep.setPDName("POPULATION DEP 1");
         popdep.setPDScope("LOCAL");
         popdep.setPDStatus("UNDER REVIEW");
         //----------- POPULATION 1 SPEC 1 ---------------
         PopulationSpecificationType popspec = new PopulationSpecificationType();
         popspec.setTerminologyScheme("FACTSPEC 1 SCHEME");
         popspec.setTerminologyCode("FACTSPEC 1 CODE");
         popspec.setComments("NO COMMENTS");
         popspec.setTerminologyValue("7777");
         popdep.getPopulationSpecification().add(popspec);
         //----
         pops.getKMVPopulationDependency().add(popdep);
         //=========================================
         aKmv.setPopulationDependencyList(pops);
      }
      {
         TaskListType tasks = new TaskListType();
         //=========================================
         //----------- TASK 1 ---------------
         KMVTaskDependencyType taskdep = new KMVTaskDependencyType();
         taskdep.setTDType("TASK DEP 1");
         //----------- TASK 1 SPEC 1 ---------------
         TTSpecificationType taskspec = new TTSpecificationType();
         taskspec.setTerminologyScheme("TASKSPEC 1 SCHEME");
         taskspec.setTerminologyCode("TASKSPEC 1 CODE");
         taskspec.setComments("NO COMMENTS");
         taskdep.getTTSpecification().add(taskspec);
         //----
         tasks.getKMVTaskDependency().add(taskdep);
         //----------- TASK 2 ---------------
         taskdep = new KMVTaskDependencyType();
         taskdep.setTDType("TASK DEP 2");
         //----
         tasks.getKMVTaskDependency().add(taskdep);
         //=========================================
         aKmv.setTaskList(tasks);
      }
      {
         InferenceListType infs = new InferenceListType();
         //=========================================
         //----------- INFERENCE 1 ---------------
         KMVInferenceEngineDependencyType infdep = new KMVInferenceEngineDependencyType();
         infdep.setComments("INFERENCE DEP 1");
         infdep.setTerminologyScheme("NFERENCE DEP 1 SCHEME");
         infdep.setTerminologyCode("INFERENCE DEP 1 CODE");
         //----
         infs.getKMVInferenceEngineDependency().add(infdep);
         //=========================================
         aKmv.setInferenceList(infs);
      }
      {
         //TODO add in ACL here
         ACLPermissionListType acls = new ACLPermissionListType();
         //=========================================
         //----------- ACL 1 ---------------
         ACLPermissionType acl = new ACLPermissionType();
         acl.setURPermission("RWX");
         UserRoleType ur = new UserRoleType();
         ur.setURName("Providers-Nurse");
         acl.setUserRole(ur);
         //----
         acls.getACLPermission().add(acl);
         //----------- ACL 2 ---------------
         acl = new ACLPermissionType();
         acl.setURPermission("R");
         ur = new UserRoleType();
         ur.setURName("External Domain Expert");
         acl.setUserRole(ur);
         //----
         acls.getACLPermission().add(acl);
         //=========================================
         aKmv.setACLPermissionList(acls);
      }
      
      kmvs.getKmVersion().add(aKmv);
      aKm.setKmVersionList(kmvs);

      return aKm;
      
   }
}