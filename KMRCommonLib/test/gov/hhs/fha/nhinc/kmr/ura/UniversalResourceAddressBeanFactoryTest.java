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
public class UniversalResourceAddressBeanFactoryTest {

    public UniversalResourceAddressBeanFactoryTest() {
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
     * Test of getInstance method, of class UniversalResourceAddressBeanFactory.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        UniversalResourceAddressBeanFactory result = UniversalResourceAddressBeanFactory.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of createUniversalResourceBean method, of class UniversalResourceAddressBeanFactory.
     */
    @Test
    public void testCreateUniversalResourceBean() throws Exception {
        System.out.println("createUniversalResourceBean");
        String address = "patient://id/7474";
        UniversalResourceAddressBeanFactory instance = UniversalResourceAddressBeanFactory.getInstance();
        UniversalResourceAddressBean result = instance.createUniversalResourceBean(address);
        assertNotNull("Address Null",result);
        assertTrue("Entity should be a patient",result.getEntityType() == EntityType.PATIENT);
        assertTrue("Path not what expected: "+result.getPath(),result.getPath().compareTo("7474")==0);
        assertTrue("Address does not match toString(): "+result.toString(),result.toString().compareTo(address)==0);
        assertTrue("Not a IdAddressBean", result instanceof IdAddressBean );
        assertTrue("Scheme not as expected",result.getScheme().compareTo("id")==0);
        IdAddressBean id = (IdAddressBean) result;
        assertTrue("Id not the exepected value", id.getId().compareTo("7474")==0 );
    }

    @Test
    public void testCreateUniversalResourceBeanLdap() throws Exception {
        System.out.println("createUniversalResourceBean - Ldap");
   	String address = "patient://ldap/cn=adam.adam,sn=Adam";
        UniversalResourceAddressBeanFactory instance = UniversalResourceAddressBeanFactory.getInstance();
        UniversalResourceAddressBean result = instance.createUniversalResourceBean(address);
        assertNotNull("Address Null",result);
        assertTrue("Entity should be a patient",result.getEntityType() == EntityType.PATIENT);
        assertTrue("Scheme should be ldap, appears to be "+result.getScheme(), result.getScheme().compareTo("ldap")==0 );
        assertTrue("Path did not translate",result.getPath().compareTo("cn=adam.adam,sn=Adam")==0);
        assertTrue("Address does not match toString(): "+result.toString(),result.toString().compareTo(address)==0);
    }

    /**
     * Test of createUniversalResourceBean method, of class UniversalResourceAddressBeanFactory.
     */
    @Test
    public void testCreateIdAddressBean() throws Exception {
        System.out.println("createIdAddressBean");
        String address = "patient://id/7474";
        UniversalResourceAddressBeanFactory instance = UniversalResourceAddressBeanFactory.getInstance();
        IdAddressBean result = instance.createIdAddressBean(address);
        assertNotNull("Address Null",result);
        assertTrue("Path not what expected: "+result.getPath(),result.getPath().compareTo("7474")==0);
        assertTrue("Address does not match toString(): "+result.toString(),result.toString().compareTo(address)==0);
        assertTrue("Entity should be a patient",result.getEntityType() == EntityType.PATIENT);
    }

    @Test
    public void testCreateIdAddressBeanLdap() throws Exception {
        System.out.println("createIdAddressBean - With LDAP scheme Id");
        UniversalResourceAddressBeanFactory instance = UniversalResourceAddressBeanFactory.getInstance();
	String address = "patient://ldap/cn=adam.adam,sn=Adam";
        IdAddressBean result = instance.createIdAddressBean(address);
        assertNull("Null expected Expected - Id request for a Ldap id",result);
    }


    @Test
    public void testCreateIdAddressBeanSubScheme() throws Exception {
        System.out.println("createIdAddressBean - With chcsien subscheme");
        UniversalResourceAddressBeanFactory instance = UniversalResourceAddressBeanFactory.getInstance();
        String address = "patient://id.chcsien/7474";
        IdAddressBean result = instance.createIdAddressBean(address);
        assertNotNull("Subscheme failed",result);
        assertTrue("Should have a subscheme of chcsien",result.getSubScheme().compareTo("chcsien")==0);
        assertTrue("Path not what expected: "+result.getPath(),result.getPath().compareTo("7474")==0);
        assertTrue("Address does not match toString(): "+result.toString(),result.toString().compareTo(address)==0);
    }


    @Test
    public void testIsAddressBean() throws Exception
    {
        System.out.println("isIdAddressBean");
        String address = "patient://id/7474";
        boolean result = UniversalResourceAddressBeanFactory.isAddressBean(address);
        assertTrue("Address Not showing as valid and is",result);
        address="10";
        result = UniversalResourceAddressBeanFactory.isAddressBean(address);
        assertFalse("Address Showing as valid and should not",result);
    }

    @Test
    public void testIsAddressBeanNot() throws Exception
    {
        System.out.println("isIdAddressBean - Not Bean");
        String address="10";
        boolean result = UniversalResourceAddressBeanFactory.isAddressBean(address);
        assertFalse("Address Showing as valid and should not",result);
    }

    @Test
    public void testIsAddressBeanNotEmpty() throws Exception
    {
        System.out.println("isIdAddressBean - Not Bean(Empty)");
        String address="";
        boolean result = UniversalResourceAddressBeanFactory.isAddressBean(address);
        assertFalse("Address Showing as valid and should not",result);
    }

}