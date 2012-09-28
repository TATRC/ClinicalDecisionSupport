/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.fact.extractor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author kim
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  gov.hhs.fha.nhinc.adapter.fact.extractor.TestResultExtractorTest.class,
  gov.hhs.fha.nhinc.adapter.fact.extractor.ProblemExtractorTest.class,
  gov.hhs.fha.nhinc.adapter.fact.extractor.MedicationExtractorTest.class,
  gov.hhs.fha.nhinc.adapter.fact.extractor.AllergyExtractorTest.class,
  gov.hhs.fha.nhinc.adapter.fact.extractor.SupportExtractorTest.class,
  gov.hhs.fha.nhinc.adapter.fact.extractor.PersonFactExtractorTest.class
})
public class ExtractorTestSuite {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

}