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
public class EntityTypeHelperTest {

    public EntityTypeHelperTest() {
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
     * Test of getType method, of class EntityTypeHelper.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String type = "device";
        EntityType expResult = EntityType.DEVICE;
        EntityType result = EntityTypeHelper.getType(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypeName method, of class EntityTypeHelper.
     */
    @Test
    public void testGetTypeName() {
        System.out.println("getTypeName");
        EntityType et = EntityType.GROUP;
        String expResult = "group";
        String result = EntityTypeHelper.getTypeName(et);
        assertEquals(expResult, result);
    }

    /**
     * Test of isStandard method, of class EntityTypeHelper.
     */
    @Test
    public void testIsStandard() {
        System.out.println("isStandard");
        EntityType et = EntityType.SERVICE;
        boolean expResult = true;
        boolean result = EntityTypeHelper.isStandard(et);
        assertEquals(expResult, result);
    }
    /**
     * Test of isStandard method, of class EntityTypeHelper.
     */
    @Test
    public void testIsStandardCustom() {
        System.out.println("isStandard");
        EntityType et = EntityType.CUSTOM;
        boolean expResult = false;
        boolean result = EntityTypeHelper.isStandard(et);
        assertEquals(expResult, result);
    }

}