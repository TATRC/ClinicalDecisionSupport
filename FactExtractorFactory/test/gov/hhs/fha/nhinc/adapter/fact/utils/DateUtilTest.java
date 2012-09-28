/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.utils;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kim
 */
public class DateUtilTest {

  private static final String cdaTestDate = "20090629144357-0700";
  
  public DateUtilTest() {
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

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  @Test
  public void parseCDADateFormatTest() {
    
    Date myDate = DateUtil.parseCDADateFormat(cdaTestDate);
    System.out.println(myDate.toString());
  }
}
