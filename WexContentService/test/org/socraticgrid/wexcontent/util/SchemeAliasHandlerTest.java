/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.util;

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
public class SchemeAliasHandlerTest {

    public SchemeAliasHandlerTest() {
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
     * Test of getSchemeAliasHandler method, of class SchemeAliasHandler.
     */
    @Test
    public void testGetSchemeAliasHandler() {
        System.out.println("getSchemeAliasHandler");
        SchemeAliasHandler expResult = null;
        SchemeAliasHandler result = SchemeAliasHandler.getSchemeAliasHandler();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAlias method, of class SchemeAliasHandler.
     */
    @Test
    public void testIsAlias() {
        System.out.println("isAlias");
        String name = "";
        SchemeAliasHandler instance = new SchemeAliasHandler();
        boolean expResult = false;
        boolean result = instance.isAlias(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resolveAlias method, of class SchemeAliasHandler.
     */
    @Test
    public void testResolveAlias() {
        System.out.println("resolveAlias");
        String name = "";
        SchemeAliasHandler instance = new SchemeAliasHandler();
        String expResult = "";
        String result = instance.resolveAlias(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}