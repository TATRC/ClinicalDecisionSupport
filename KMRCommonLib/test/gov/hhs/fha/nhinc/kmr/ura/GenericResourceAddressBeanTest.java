/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package gov.hhs.fha.nhinc.kmr.ura;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * DOCUMENT ME!
 *
 * @author  Jerry Goodnough
 */
public class GenericResourceAddressBeanTest
{

    /**
     * Creates a new GenericResourceAddressBeanTest object.
     */
    public GenericResourceAddressBeanTest()
    {
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    @BeforeClass public static void setUpClass() throws Exception
    {
    }

    /**
     * DOCUMENT ME!
     *
     * @throws  Exception  DOCUMENT ME!
     */
    @AfterClass public static void tearDownClass() throws Exception
    {
    }

    /**
     * DOCUMENT ME!
     */
    @Before public void setUp()
    {
    }

    /**
     * DOCUMENT ME!
     */
    @After public void tearDown()
    {
    }

    /**
     * Test of getEntityType method, of class GenericResourceAddressBean.
     */
    @Test public void testGetEntityType()
    {
        System.out.println("getEntityType - Default Unknown");

        GenericResourceAddressBean instance = new GenericResourceAddressBean();
        EntityType expResult = EntityType.UNKNOWN;
        EntityType result = instance.getEntityType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEntityTypeName method, of class GenericResourceAddressBean.
     */
    @Test public void testGetEntityTypeName()
    {
        System.out.println("getEntityTypeName - Custon Type Name");

        GenericResourceAddressBean instance = new GenericResourceAddressBean("testtype","1","1","1");
        String expResult = "testtype";
        String result = instance.getEntityTypeName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullScheme method, of class GenericResourceAddressBean.
     */
    @Test public void testGetFullScheme()
    {
        System.out.println("getFullScheme");

        GenericResourceAddressBean instance = new GenericResourceAddressBean(EntityType.PATIENT,"","id","ssn","10");
        String expResult = "id.ssn";
        String result = instance.getFullScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class GenericResourceAddressBean.
     */
    @Test public void testGetPath()
    {
        System.out.println("getPath");

        GenericResourceAddressBean instance = new GenericResourceAddressBean(EntityType.PATIENT,"","id","ssn","555/66/7777");
        String expResult = "555/66/7777";
        String result = instance.getPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScheme method, of class GenericResourceAddressBean.
     */
    @Test public void testGetScheme()
    {
        System.out.println("getScheme");

        GenericResourceAddressBean instance = new GenericResourceAddressBean(EntityType.PATIENT,"","id","ssn","555/66/7777");
        String expResult = "id";
        String result = instance.getScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubScheme method, of class GenericResourceAddressBean.
     */
    @Test public void testGetSubScheme()
    {
        System.out.println("getSubScheme");

        GenericResourceAddressBean instance = new GenericResourceAddressBean(EntityType.PATIENT,"","id","ssn","555/66/7777");
        String expResult = "ssn";
        String result = instance.getSubScheme();
        assertEquals(expResult, result);
    }

    /**
     * Test of parse method, of class GenericResourceAddressBean.
     *
     * @throws  Exception  DOCUMENT ME!
     */
    @Test public void testParse() throws Exception
    {
        System.out.println("parse and equals");

        String ura = "patient://id.ssn/555/66/7777";
        GenericResourceAddressBean expResult = new GenericResourceAddressBean(EntityType.PATIENT,"","id","ssn","555/66/7777");
        UniversalResourceAddressBean result = GenericResourceAddressBean.parse(ura);
        assertEquals(ura+" not equal "+result.getURA(),expResult, result);


    }

    /**
     * Test of toString method, of class GenericResourceAddressBean.
     */
    @Test public void testToString()
    {
        System.out.println("toString");

        GenericResourceAddressBean instance = new GenericResourceAddressBean(EntityType.PATIENT,"","id","ssn","555/66/7777");
        String expResult =  "patient://id.ssn/555/66/7777";
        String result = instance.toString();
        assertTrue(expResult.compareTo(result)==0);
    }

    @Test public void testGetURA()
    {
        System.out.println("getURA");

        GenericResourceAddressBean instance = new GenericResourceAddressBean(EntityType.PATIENT,"","id","ssn","555/66/7777");
        String expResult =  "patient://id.ssn/555/66/7777";
        String result = instance.getURA();
        assertEquals("Length mismatch",expResult.length(),result.length());
        assertEquals(expResult+" not equal "+result,expResult, result);
    }

}
