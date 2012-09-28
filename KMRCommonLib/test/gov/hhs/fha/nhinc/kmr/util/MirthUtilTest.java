/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.util;

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
public class MirthUtilTest {

    public MirthUtilTest() {
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
     * Test of getFloatRep method, of class MirthUtil.
     */
    @Test
    public void testGetFloatRep() {
        System.out.println("getFloatRep");
        String num = "1.02222";
        String expResult = "1.02222";
        String result = MirthUtil.getFloatRep(num);
        assertEquals(expResult, result);
        num = "1";
        expResult = "1.0";
        result = MirthUtil.getFloatRep(num);
        assertEquals(expResult, result);
        num = "1E-2";
        expResult = "0.01";
        result = MirthUtil.getFloatRep(num);
        assertEquals(expResult, result);
        num = "347";
        expResult = "347.0";
        result = MirthUtil.getFloatRep(num);
        assertEquals(expResult, result);
    }

}