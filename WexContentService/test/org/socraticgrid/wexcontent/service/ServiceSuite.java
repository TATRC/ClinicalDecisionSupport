/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.service;

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
@Suite.SuiteClasses({org.socraticgrid.wexcontent.service.FreebaseImageSearchTest.class,org.socraticgrid.wexcontent.service.SectionsResourceTest.class,org.socraticgrid.wexcontent.service.SectionResourceTest.class})
public class ServiceSuite {

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