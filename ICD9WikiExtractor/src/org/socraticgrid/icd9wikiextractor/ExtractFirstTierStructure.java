/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.icd9wikiextractor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * DOCUMENT ME!
 *
 * @author  Jerry Goodnough
 */
public class ExtractFirstTierStructure
{

    //~ Static fields/initializers ---------------------------------------------

    /** DOCUMENT ME! */
    public static String ICD9_WIKI_ROOT =
        "http://en.wikipedia.org/wiki/List_of_ICD-9_codes";

    public static String WIKI_ROOT = "http://en.wikipedia.org";
    /** DOCUMENT ME! */
    private static Log log = LogFactory.getLog(ExtractFirstTierStructure.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   addr  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static StringBuilder getPage(String addr)
    {
        HttpURLConnection connection = null;
        StringBuilder sb = null;
        BufferedReader rd = null;
        String line;

        log.info("Fetching page " + addr);

        try
        {

            URL address = new URL(addr);

            connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);

            connection.connect();

            rd = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
            sb = new StringBuilder();

            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }
        }
        catch (Exception exp)
        {
            log.fatal("Exection getting first tier", exp);
        }
        finally
        {

            if (connection != null)
            {
                connection.disconnect();
            }
        }

        return sb;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<String> getSecondTier()
    {
        log.info("Started First Tier Extraction");

        LinkedList<String> out = new LinkedList<String>();

        //StringBuilder sb = getPage(ICD9_WIKI_ROOT);
        //System.out.print(sb.toString());
        parsePage(ICD9_WIKI_ROOT, out);
        log.info("Ended First Tier Extraction");

        return out;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  addr  DOCUMENT ME!
     * @param  refs  DOCUMENT ME!
     */
    public void parsePage(String addr, List<String> refs)
    {
        HttpURLConnection connection = null;

        log.info("Fetching page " + addr);

        try
        {

            URL address = new URL(addr);

            connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);

            connection.connect();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);

            SAXParser saxParser = factory.newSAXParser();

            if (saxParser.isValidating())
            {
                log.error("Parser is validating!");
            }

            saxParser.parse(connection.getInputStream(), new HandleTier1(refs));


        }
        catch (Exception exp)
        {
            log.fatal("Exception getting first tier", exp);
            System.out.println(exp.getMessage());
            exp.printStackTrace(System.out);
        }
        finally
        {

            if (connection != null)
            {
                connection.disconnect();
            }
        }

    }

    //~ Inner Classes ----------------------------------------------------------

    public class HandleTier1 extends DefaultHandler
    {

        //~ Instance fields ----------------------------------------------------

        public List<String> linkList = null;
        private String hRefMatch = "/wiki/List_of_ICD-9_codes_";
        private String hRefEnd = "/wiki/List_of_ICD-9_codes";
        boolean notAtEnd = true;
        boolean inBodyContent = false;
        int liCnt = 0;
        int divStack = 0;

        //~ Constructors -------------------------------------------------------

        HandleTier1(List<String> linkList)
        {
            this.linkList = linkList;
        }

        //~ Methods ------------------------------------------------------------

        @Override public void endElement(String uri, String localName,
            String qName) throws SAXException
        {

            if (inBodyContent)
            {

                if (qName.compareTo("div") == 0)
                {
                    divStack--;

                    if (divStack == 0)
                    {
                        inBodyContent = false;
                    }
                }
                else if (qName.compareTo("li")==0)
                {
                    liCnt--;
                }
            }

        }

        //~ --------------------------------------------------------------------

        @Override public InputSource resolveEntity(String publicId,
            String systemId) throws IOException, SAXException
        {
            InputSource out = null;

            out = new InputSource(new ByteArrayInputStream(
                        "<?xml version='1.0' encoding='UTF-8'?>".getBytes()));

            return out;
        }

        //~ --------------------------------------------------------------------

        @Override public void startElement(String uri, String localName,
            String qName, Attributes attb)
        {


            if (inBodyContent)
            {

                if (qName.compareTo("li")==0)
                {
                    liCnt++;
                }
                else if ((liCnt>0) && (qName.compareTo("a") == 0))
                {
                    String href = attb.getValue("href");

                    if (href != null)
                    {

                        if (notAtEnd)
                        {

                            if (href.startsWith(hRefMatch))
                            {
                                //System.out.println(qName + " -> " + href);
                                linkList.add(WIKI_ROOT+href);
                            }
                            else if (href.compareTo(hRefEnd) == 0)
                            {
                                notAtEnd = false;
                            }
                        }
                    }
                }
                else if (qName.compareTo("div") == 0)
                {
                    divStack++;
                }
            }
            else
            {

                if (qName.compareTo("div") == 0)
                {
                    String id = attb.getValue("id");

                    if ((id != null) && (id.compareTo("bodyContent") == 0))
                    {
                        inBodyContent = true;
                        divStack = 1;
                    }
                }

            }
        }


    }
}
