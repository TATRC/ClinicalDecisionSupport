/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.cohort;

import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohortQueryRequestType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jerry Goodnough
 */
public class PatientCohortQueryServiceImplTest {

    public PatientCohortQueryServiceImplTest() {
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
     * Test of getSinglton method, of class PatientCohortQueryServiceImpl.
     */
    @Test
    public void testGetSinglton() {
        System.out.println("getSinglton");
        PatientCohortQueryServiceImpl expResult = null;
        PatientCohortQueryServiceImpl result = PatientCohortQueryServiceImpl.getSinglton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of patientCohortQuery method, of class PatientCohortQueryServiceImpl.
     */
    @Test
    public void testPatientCohortQuery() {
        System.out.println("patientCohortQuery");
        PatientCohortQueryRequestType cohortQueryRequest = null;
        PatientCohortQueryServiceImpl instance = new PatientCohortQueryServiceImpl();
        PatientCohort expResult = null;
        PatientCohort result = instance.patientCohortQuery(cohortQueryRequest);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}