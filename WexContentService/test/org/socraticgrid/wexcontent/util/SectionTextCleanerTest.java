/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.util;

import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author Jerry Goodnough
 */
public class SectionTextCleanerTest
{

    private static String doc =
        "<section><paragraph><sentence id=\"4781088/29\"><italics>See:<space/><link><target>tuberculosis treatment</target></link>"+
        "</italics></sentence></paragraph><paragraph><sentence id=\"4781088/30\">The treatment of TB meningitis is<space/>"+
        "<link><target>isoniazid</target></link>,<space/><link><target>rifampicin</target></link>,<space/><link><target>pyrazinamide</target>"+
        "</link><space/>and<space/><link><target>ethambutol</target></link><space/>for two months, followed by isoniazid and rifampicin alone "+
        "for a further ten months.</sentence><sentence id=\"4781088/31\">Steroids are always used in the first six weeks of treatment "+
        "(and sometimes for longer).</sentence><extension extension_name=\"ref\"><template name=\"cite journal\"><param name=\"author\">"+
        "Thwaites GE, Nguyen DB, Nguyen HD,<space/><italics>et al.</italics></param><param name=\"title\">Dexamethasone for the treatment "+
        "of tuberculous meningitis in adolescents and adults</param><param name=\"journal\">N Engl J Med</param><param name=\"year\">2004</param>"+
        "<param name=\"volume\">351</param><param name=\"issue\">17</param><param name=\"pages\">1741&amp;ndash;1751</param><param name=\"pmid\">"+
        "15496623</param><param name=\"doi\">10.1056/NEJMoa040573</param></template><template name=\"cite journal\"><param name=\"author\">Thwaites "+
        "GE, Nguyen DB, Nguyen HD,<space/><italics>et al.</italics></param><param name=\"title\">Dexamethasone for the treatment of tuberculous "+
        "meningitis in adolescents and adults</param><param name=\"journal\">N Engl J Med</param><param name=\"year\">2004</param><param name=\"volume\">"+
        "351</param><param name=\"issue\">17</param><param name=\"pages\">1741&amp;ndash;1751</param><param name=\"pmid\">15496623</param>"+
        "<param name=\"doi\">10.1056/NEJMoa040573</param></template></extension><space/><sentence id=\"4781088/32\">A few patients may require<space/>"+
        "<link>	<target>Immunomodulator</target><part>immunomodulatory</part></link><space/>agents such as<space/><link><target>thalidomide</target>"+
        "</link>.</sentence></paragraph><paragraph><sentence id=\"4781088/33\">	<link synthetic=\"true\"><target>tuberculosis treatment</target>"+
        "<part>Treatment</part></link> must be started as soon as there is a reasonable suspicion of the diagnosis.</sentence><sentence id=\"4781088/34\">"+
        "<link synthetic=\"true\"><target>tuberculosis treatment</target><part>Treatment</part>	</link> must not be delayed while waiting for confirmation"+
        " of the diagnosis.</sentence></paragraph><paragraph><sentence id=\"4781088/35\"><link>	<target>Hydrocephalus</target></link>"+
        "<space/>occurs as a complication in about a third of patients with TB meningitis and will require a<space/><link><target>ventricular shunt"+
        "</target></link>.</sentence></paragraph></section>";

    public SectionTextCleanerTest()
    {
    }

    @BeforeClass public static void setUpClass() throws Exception
    {
    }

    @AfterClass public static void tearDownClass() throws Exception
    {
    }

    @Before public void setUp()
    {
    }

    @After public void tearDown()
    {
    }

    /**
     * Test of cleanupSectionText method, of class SectionTextCleaner.
     */
    @Test public void testCleanupSectionText()
    {
        System.out.println("cleanupSectionText");

        SectionTextCleaner instance = new SectionTextCleaner();
        String result = instance.cleanupSectionText(doc);
        assertTrue(result.length() > 50);
    }

}
