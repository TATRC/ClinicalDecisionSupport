/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

import org.socraticgrid.wexcontent.util.FreebaseImageSearch;
import java.util.List;
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
public class FreebaseImageSearchTest {

    public FreebaseImageSearchTest() {
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
     * Test of findImagesForId method, of class FreebaseImageSearch.
     */
    @Test
    public void testFindImagesForId() {
        System.out.println("findImagesForId");
        int wpid = 0;
        List expResult = null;
        List result = FreebaseImageSearch.findImagesForId(wpid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}