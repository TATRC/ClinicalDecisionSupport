/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.icd9wikiextractor;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Iterator;
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
 *
 * @author Jerry Goodnough
 */
public class ExtractSecondLevelDetail {
    /** DOCUMENT ME! */
    private static Log log = LogFactory.getLog(ExtractSecondLevelDetail.class);

     /**
     * DOCUMENT ME!
     *
     * @param  addr  DOCUMENT ME!
     * @param  refs  DOCUMENT ME!
     */

    public void parseTier2Pages(List<String> pages,List<ICD9Ref> list)
    {
        Iterator<String> itr = pages.listIterator();
        while(itr.hasNext())
        {
            String page = itr.next();
            this.parsePage(page,list);
        }
    }

    public void parsePage(String addr,List<ICD9Ref> list)
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

            saxParser.parse(connection.getInputStream(), new HandleTier2(list));


        }
        catch (Exception exp)
        {
            log.fatal("Exception getting 2nd tier", exp);
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

    public class HandleTier2 extends DefaultHandler
    {

        //~ Instance fields ----------------------------------------------------

        public List<ICD9Ref> linkList = null;
        private String hRefEnd = "/wiki/List_of_ICD-9_codes";
        private String hICDRef = "http://www.icd9data.com/getICD9Code.ashx?icd9=";
        public final static String WIKI_ROOT = "http://en.wikipedia.org";

        boolean notAtEnd = true;
        boolean inBodyContent = false;
        ICD9Ref curICDRef = null;
        int liCnt = 0;
        int divStack = 0;

        public HandleTier2(List<ICD9Ref> linkList)
        {
            this.linkList=linkList;
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
                        resetContext();
                    }
                }
                else if (qName.compareTo("li")==0)
                {
                    liCnt--;
                    //resetContext();
                }
                else if ((qName.compareTo("ul")==0))
                {
                    //resetContext();
                }
            }

        }

        public void resetContext()
        {
            if (curICDRef != null)
            {
                linkList.add(curICDRef);
                //System.out.println(curICDRef.toString());

                curICDRef = null;
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

                            if (href.startsWith(hICDRef))
                            {
                                resetContext();
                                //System.out.println(qName + " -> " + href);
                                curICDRef=new ICD9Ref(href);
                            }
                            else if (href.compareTo(hRefEnd) == 0)
                            {
                                notAtEnd = false;
                            }
                            else if (curICDRef!= null)
                            {
                                if (href.startsWith("/"))
                                {
                                    href = WIKI_ROOT+href;
                                }
                                curICDRef.addLink(href);

                            }
                        }
                    }
                }
                else if (qName.compareTo("div") == 0)
                {
                    divStack++;
                }
                else if (qName.compareTo("ul")==0)
                {
                    resetContext();
                }
            }
            else if ((qName.compareTo("ul")==0))
            {
                //Make an Exception for nested items if the parent does not have context yet.
                if (curICDRef != null && curICDRef.links.size()>0)
                {
                    resetContext();
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
