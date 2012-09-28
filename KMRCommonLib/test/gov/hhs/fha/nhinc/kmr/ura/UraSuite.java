/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.ura;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;


/**
 *
 * @author Jerry Goodnough
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
    {GenericResourceAddressBeanTest.class, EntityTypeTest.class, EntityTypeHelperTest.class, UniversalResourceAddressBeanFactoryTest.class, IdAddressBeanTest.class, BaseResourceAddressBeanTest.class})
public class UraSuite
{

    @BeforeClass public static void setUpClass() throws Exception
    {
    }

    @AfterClass public static void tearDownClass() throws Exception
    {
    }

    @Before public void setUp() throws Exception
    {
    }

    @After public void tearDown() throws Exception
    {
    }

}
