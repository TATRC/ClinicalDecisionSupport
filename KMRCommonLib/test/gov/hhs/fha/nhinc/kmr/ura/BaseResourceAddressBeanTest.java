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
public class BaseResourceAddressBeanTest {

    public BaseResourceAddressBeanTest() {
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
     * Test of getEntityType method, of class BaseResourceAddressBean.
     */
    @Test
    public void testGetEntityType() {
        System.out.println("getEntityType");
        BaseResourceAddressBean instance = new BaseResourceAddressBean();
        EntityType expResult = EntityType.UNKNOWN;
        EntityType result = instance.getEntityType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntityTypeName method, of class BaseResourceAddressBean.
     */
    @Test
    public void testGetEntityTypeName() {
        System.out.println("getEntityTypeName");
        BaseResourceAddressBean instance = new BaseResourceAddressBean();
        String expResult = "unknown";
        String result = instance.getEntityTypeName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getURA method, of class BaseResourceAddressBean.
     */
    @Test
    public void testGetURA() {
        System.out.println("getURA");
        BaseResourceAddressBean instance = new BaseResourceAddressBean();
        String expResult = "unknown://";
        String result = instance.getURA();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class BaseResourceAddressBean.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = "astring";
        BaseResourceAddressBean instance = new BaseResourceAddressBean();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class BaseResourceAddressBean.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        BaseResourceAddressBean instance = new BaseResourceAddressBean();
        //The hash code should match the following
        int expResult = "unknown://".hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class BaseResourceAddressBean.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        BaseResourceAddressBean instance = new BaseResourceAddressBean();
        String expResult = "unknown://";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}