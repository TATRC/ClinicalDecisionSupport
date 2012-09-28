/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.icd9wikiextractor;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.*;

import org.socraticgrid.icd9wikiextractor.freebase.dao.*;

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
public class ExtractFreebaseMetadata
{

    //~ Static fields/initializers ---------------------------------------------

    /** DOCUMENT ME! */
    private static Log log = LogFactory.getLog(ExtractFreebaseMetadata.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   a  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public List<String> findICD9Code(Articles a)
    {
        List<String> icd9Codes = null;

        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);

            SAXParser saxParser = factory.newSAXParser();
            icd9Codes = new LinkedList<String>();

            if (saxParser.isValidating())
            {
                log.error("Parser is validating!");
            }

            ByteArrayInputStream ba = new ByteArrayInputStream(a.getXml()
                    .getBytes());
            saxParser.parse(ba, new HandleXMLMetaData(icd9Codes));

        }
        catch (Exception exp)
        {
            log.error("Exception finding ICD9 code", exp);
        }

        return icd9Codes;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public void fixICD9References()
    {
        Session session=null;
        Transaction tx=null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery("from Articles");
            qry.setFetchSize(100);

            ScrollableResults articleCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;

            while (articleCursor.next())
            {
                Articles a = (Articles) articleCursor.get(0);
                System.out.println(a.getName());

                //Now Parse the content
                List<String> icd9Codes = findICD9Code(a);

                if (icd9Codes != null)
                {
                    //Update freebase_med_disease
                    //Update freebase_med_icd_9
                    System.out.print("Found ");
                    System.out.print(icd9Codes.size());
                    System.out.println(" codes");
                }

                if ((++cnt % 100) == 0)
                {
                    session.flush();
                    session.clear();
                }
            }
        }
        finally
        {
            if (tx != null)
            {
                tx.commit();
            }
            if (session != null)
            {
                session.close();
                HibernateUtil.shutdown();
            }
        }

    }

    //~ Inner Classes ----------------------------------------------------------

    public class ElementData
    {

        //~ Instance fields ----------------------------------------------------

        public String wpid;
        public String guid;
        public String icd9;

    }

    public class HandleXMLMetaData extends DefaultHandler
    {

        //~ Instance fields ----------------------------------------------------

        List<String> icdList;

        int inICD9 = 0;
        boolean inICD9Code = false;

        //~ Constructors -------------------------------------------------------

        public HandleXMLMetaData(List<String> icdList)
        {
            this.icdList = icdList;
        }

        //~ Methods ------------------------------------------------------------

        @Override public void characters(char[] ch, int start, int length)
        {

            if (inICD9Code)
            {
                String code = new String(ch);
                icdList.add(code);
                System.out.print("Found code: ");
                System.out.println(code);
            }
        }

        //~ --------------------------------------------------------------------

        @Override public void endElement(String uri, String localName,
            String qName) throws SAXException
        {

            if (inICD9 > 0)
            {
                inICD9--;
                inICD9Code = false;
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

            if (qName.compareTo("param") == 0)
            {

                //Check here if in ICD9
                String paramName = attb.getValue("name");

                if (paramName != null)
                {

                    if (inICD9 > 0)
                    {
                        inICD9Code = true;
                    }
                    else if (paramName.compareTo("ICD9") == 0)
                    {
                        inICD9++;
                    }
                }
            }
        }


    }
}
