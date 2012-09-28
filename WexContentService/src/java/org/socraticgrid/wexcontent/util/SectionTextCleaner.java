/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * @author  Jerry Goodnough
 */
public class SectionTextCleaner
{

    /** DOCUMENT ME! */
    private static Log log = LogFactory.getLog(SectionTextCleaner.class);

    /**
     * DOCUMENT ME!
     *
     * @param   text  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String cleanupSectionText(String text)
    {
        StringBuffer out = new StringBuffer();
        String ret = "";

        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);

            SAXParser saxParser = factory.newSAXParser();

            if (saxParser.isValidating())
            {
                log.error("Parser is validating!");
            }

            //out.append("<html><body>");
            StringReader in = new StringReader(text);
            InputSource ins = new InputSource(in);
            HandleParse hndl = new HandleParse(out);
            saxParser.parse(ins, hndl);

            if (hndl.isWellFormed())
            {
                ret = out.toString();
                ret = ret.replaceAll(
                        "((&amp;)(NBSP|nbsp|Nbsp))|((^p)|(^r)|(^n))", "");
                ret = ret.replaceAll(
                        "(&amp;)(MDASH|mdash|NDASH|ndash|minus|MINUS)", "-");
                //ret = ret.replaceAll("(&lt;http://).*(>)", "");
            }
            else
            {
                ret = "<p>Original Source was Ill formed</p>";
            }
        }
        catch (Exception exp)
        {
            log.fatal("Exception getting parsing section text", exp);
            ret = "<p>Invalid Original Material</p>";
        }
        finally
        {
            //out.append("</html></body>");

        }


        return ret;

    }

    /**
     * DOCUMENT ME!
     *
     * @author   $author$
     * @version  $Revision$, $Date$
     */
    public class HandleParse extends DefaultHandler
    {

        /** DOCUMENT ME! */
        public StringBuffer buf;

        /** DOCUMENT ME! */
        boolean allowOutput = true;

        /** DOCUMENT ME! */
        private int cntI = 0;

        /** DOCUMENT ME! */
        private int cntP = 0;

        /**
         * Creates a new HandleParse object.
         *
         * @param  buf  DOCUMENT ME!
         */
        public HandleParse(StringBuffer buf)
        {
            this.buf = buf;
        }

        /**
         * @see  org.xml.sax.helpers.DefaultHandler
         */
        @Override public void characters(char[] ch, int start, int len)
        {

            if (allowOutput)
            {
                StringBuffer tmp = new StringBuffer();
                tmp.append(ch, start, len);

                String s = tmp.toString();
                s = s.replaceAll("(&)", "&amp;");
                s = s.replaceAll("(<)", "&lt;");
                s = s.replaceAll("(>)", "&gt;");
                buf.append(s);
            }
        }

        /**
         * @see  org.xml.sax.helpers.DefaultHandler
         */
        @Override public void endElement(String uri, String localName,
            String qName) throws SAXException
        {


            if (qName.compareTo("paragraph") == 0)
            {
                buf.append("</p>");
                cntP--;
            }
            else if (qName.compareTo("italics") == 0)
            {
                buf.append("</i>");
                cntI--;
            }
            else if (qName.compareTo("sentence") == 0)
            {
                buf.append("<br/>");
            }
            else if (qName.compareTo("param") == 0)
            {
                allowOutput = true;
            }
            else if (qName.compareTo("part") == 0)
            {
                allowOutput = true;
            }

        }

        /**
         * @see  org.xml.sax.helpers.DefaultHandler
         */
        @Override public void ignorableWhitespace(char[] ch, int start, int len)
        {

            if (allowOutput)
            {
                //buf.append(ch, start, len);
            }
        }

        /**
         * DOCUMENT ME!
         *
         * @return  DOCUMENT ME!
         */
        public boolean isWellFormed()
        {
            return ((cntP == 0) && (cntI == 0));
        }


        /**
         * @see  org.xml.sax.helpers.DefaultHandler
         */
        @Override public InputSource resolveEntity(String publicId,
            String systemId) throws IOException, SAXException
        {
            InputSource out = null;

            out = new InputSource(new ByteArrayInputStream(
                        "<?xml version='1.0' encoding='UTF-8'?>".getBytes()));

            return out;
        }

        /**
         * @see  org.xml.sax.helpers.DefaultHandler
         */
        @Override public void startElement(String uri, String localName,
            String qName, Attributes attb)
        {


            if ((qName.compareTo("paragraph") == 0))
            {
                allowOutput = true;
                buf.append("<p>");
                cntP++;
            }
            else if (qName.compareTo("italics") == 0)
            {
                buf.append("<i>");
                cntI++;
            }
            else if (qName.compareTo("space") == 0)
            {

                if (allowOutput)
                {
                    buf.append(" ");
                }
            }
            else if (qName.compareTo("param") == 0)
            {
                allowOutput = false;
            }
            else if (qName.compareTo("part") == 0)
            {
                allowOutput = false;
            }
        }

    }

}
