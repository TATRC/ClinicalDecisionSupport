/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.ura;

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
public class IdAddressBeanTest {

    private IdAddressBean testBean =null;
    private String testURA = "provider://id.npid/995687676/16";
    public IdAddressBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        try
        {
            testBean = IdAddressBean.parse(testURA);
        }
        catch(Exception e)
        {
            //Silently fail for now
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isIdAddressBean method, of class IdAddressBean.
     */
    @Test
    public void testIsIdAddressBean() {
        System.out.println("isIdAddressBean");
        String ura = "provider://id.npid/995687676";
        boolean expResult = true;
        boolean result = IdAddressBean.isIdAddressBean(ura);
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class IdAddressBean.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        IdAddressBean instance = testBean;
        String expResult = "995687676/16";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class IdAddressBean.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        IdAddressBean instance = testBean;
        String expResult = "995687676/16";
        String result = instance.getPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScheme method, of class IdAddressBean.
     */
    @Test
    public void testGetScheme() {
        System.out.println("getScheme");
        IdAddressBean instance = testBean;
        String expResult = "id";
        String result = instance.getScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubScheme method, of class IdAddressBean.
     */
    @Test
    public void testGetSubScheme() {
        System.out.println("getSubScheme");
        IdAddressBean instance = testBean;
        String expResult = "npid";
        String result = instance.getSubScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullScheme method, of class IdAddressBean.
     */
    @Test
    public void testGetFullScheme() {
        System.out.println("getFullScheme");
        IdAddressBean instance = testBean;
        String expResult = "id.npid";
        String result = instance.getFullScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of parse method, of class IdAddressBean.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        String ura = testURA;
        IdAddressBean expResult = testBean;
        IdAddressBean result = IdAddressBean.parse(ura);
        assertEquals(expResult, result);
    }

    /**
     * Test of getURA method, of class IdAddressBean.
     */
    @Test
    public void testGetURA() {
        System.out.println("getURA");
        IdAddressBean instance = testBean;
        String expResult = testURA;
        String result = instance.getURA();
        assertEquals(expResult, result);
    }

}