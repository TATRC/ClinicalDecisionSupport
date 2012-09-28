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
public class EntityTypeTest {

    public EntityTypeTest() {
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
     * Test of values method, of class EntityType.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        EntityType[] result = EntityType.values();
        assertTrue(result.length>10);
    }

    /**
     * Test of valueOf method, of class EntityType.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "PATIENT";
        EntityType expResult = EntityType.PATIENT;
        EntityType result = EntityType.valueOf(name);
        assertEquals(expResult, result);
    }

}