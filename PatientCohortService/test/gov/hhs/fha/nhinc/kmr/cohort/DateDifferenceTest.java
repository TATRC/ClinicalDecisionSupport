/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.cohort;

import java.util.GregorianCalendar;
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
public class DateDifferenceTest {

    private static GregorianCalendar[] cals;
    public DateDifferenceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        cals = new GregorianCalendar[11];
        cals[0] = (GregorianCalendar) GregorianCalendar.getInstance();
        cals[1] = new GregorianCalendar(1964,10,30);
        cals[2] = new GregorianCalendar(1900,2,28);
        cals[3] = new GregorianCalendar(1992,2,28);
        cals[4] = new GregorianCalendar(2000,2,29);
        cals[5] = new GregorianCalendar(1974,11,30);
        cals[6] = new GregorianCalendar(1980,7,15);
        cals[7] = new GregorianCalendar(1920,1,1);
        cals[8] = new GregorianCalendar(1964,2,30);
        cals[9] = new GregorianCalendar(1979,8,12);
        cals[10] = new GregorianCalendar(2010,7,23);

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
     * Test of getYears method, of class DateDifference.
     */
    @Test
    public void testGetYears() {
        System.out.println("getYears");
        DateDifference instance = DateDifference.getDateDifference(cals[1],cals[10]);
        int expResult = 45;
        int result = instance.getYears();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonths method, of class DateDifference.
     */
    @Test
    public void testGetMonths() {
        System.out.println("getMonths");

        DateDifference instance = DateDifference.getDateDifference(cals[1],cals[10]);
        int expResult = 8;
        int result = instance.getMonths();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDays method, of class DateDifference.
     */
    @Test
    public void testGetDays() {
        System.out.println("getDays");
        DateDifference instance = DateDifference.getDateDifference(cals[1],cals[10]);
        int expResult = 24;
        int result = instance.getDays();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAbdDays method, of class DateDifference.
     */
    @Test
    public void testGetAbdDays() {
        System.out.println("getAbdDays");
        DateDifference instance = DateDifference.getDateDifference(cals[10],cals[1]);
        int expResult = (45*365)+11+(3*30)+28+1+(4*31)+23;  //44 Years, 11 Leap Days  (1 Day on Oct, 23 in July + Months between)
        int result = instance.getAbdDays();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateDifference method, of class DateDifference.
     */
    @Test
    public void testGetDateDifference() {
        System.out.println("getDateDifference");
        GregorianCalendar d1 = cals[0];
        GregorianCalendar d2 = cals[2];
        DateDifference result = DateDifference.getDateDifference(d1, d2);
        assertNotNull(result);
    }

    /**
     * Test of getDaysInMonth method, of class DateDifference.
     */
    @Test
    public void testGetDaysInMonth() {
        System.out.println("getDaysInMonth");
        int expResult = 29;
        int result = DateDifference.getDaysInMonth(2, 2000);
        assertEquals(expResult, result);
   }

    /**
     * Test of equals method, of class DateDifference.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        assertTrue(cals[0].equals(cals[0]));
        assertFalse(cals[2].equals(cals[3]));
    }

}