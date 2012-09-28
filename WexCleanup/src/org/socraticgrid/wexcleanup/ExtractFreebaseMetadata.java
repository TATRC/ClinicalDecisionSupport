/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcleanup;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.ListIterator;
import org.hibernate.*;

import org.socraticgrid.wexcleanup.freebase.dao.*;

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
    private static boolean debug = false;
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
    public List<String> findICD9Code(EcsArticles a)
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

            if (a.getXml() != null)
            {
                ByteArrayInputStream ba = new ByteArrayInputStream(a.getXml()
                        .getBytes());
                saxParser.parse(ba, new HandleXMLMetaData(icd9Codes));
            }
            else
            {
                log.error(a.getName()+" does not have XML section");
            }

        }
        catch (Exception exp)
        {
            log.error("Exception finding ICD9 code", exp);
        }

        return icd9Codes;
    }

    public void deleteICD9Mappings(RunStats stats)
    {
        Session session=null;
        Transaction tx=null;
        try
        {
            System.out.println("Deleting ICD9 Article Mapping");
            //Clear the existing data set
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query qry = session.createQuery("delete EcsIcd9Wpids");
            int removed = qry.executeUpdate();
            tx.commit();
            System.out.print(removed);
            System.out.println(" entries removed.");
            tx=null;
        }
        catch(Exception exp)
        {
            System.out.println("Exception during Deletion of articles:"+exp.getMessage());
            exp.printStackTrace();
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

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public void findICD9References(RunStats stats)
    {
        Session session=null;
        Transaction tx=null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query qry = session.createQuery("from EcsArticles");
            qry.setFetchSize(100);

            ScrollableResults articleCursor = qry.scroll(ScrollMode.FORWARD_ONLY);
            int cnt = 0;
            int cntArtWithICD = 0;
            int cntTotalICDs = 0;

            while (articleCursor.next())
            {
                EcsArticles a = (EcsArticles) articleCursor.get(0);

                //Now Parse the content
                List<String> icd9Codes = findICD9Code(a);

                if (icd9Codes != null)
                {
                    if (icd9Codes.isEmpty() == false)
                    {
                        HashSet<String> icdSet = new HashSet<String>();
                        ListIterator<String> itr = (ListIterator<String>)icd9Codes.listIterator();
                        while(itr.hasNext())
                        {
                            String fnd = itr.next();
                            if (fnd.contains(","))
                            {
                                String[] sec = fnd.split(",");
                                for (int i=0;i<sec.length;i++)
                                {
                                    icdSet.add(sec[i].trim());
                                }
                            }
                            else
                            {
                                icdSet.add(fnd.trim());
                            }
                        }


                        //Process the code set to deal with duplicates and lists
                        int size = icdSet.size();

                        if (debug)
                        {
                            System.out.print(a.getWpid());
                            System.out.print(", Found ");
                            System.out.print(size);
                            if (size == 1)
                            {
                                System.out.print(" code (");
                            }
                            else
                            {
                                System.out.print(" codes (");
                            }
                        }

                        int i=0;
                        Iterator<String> setItr = icdSet.iterator();

                        while(setItr.hasNext())
                        {
                            String code = setItr.next();
                            EcsIcd9Wpids ref = new EcsIcd9Wpids();
                            ref.setCode(code);
                            ref.setWpid(a.getWpid());
                            session.save(ref);
                            
                            if (debug)
                            {
                                if (i>0)
                                {
                                    System.out.print(",");
                                }
                                System.out.print(code);
                            }

                            i++;
                        }
                        if (debug)
                        {
                            System.out.println(")");
                        }
                        cntArtWithICD++;
                        cntTotalICDs+=size;
                    }
                    else
                    {
                        //System.out.print(a.getWpid());
                        //System.out.println(", No ICD9 codes found");
                    }
                }

                if ((++cnt % 100) == 0)
                {
                    session.flush();
                    session.clear();
                }
            }
            System.out.print("Total articles: ");
            System.out.println(cnt);
            System.out.print("Total articles with ICDs: ");
            System.out.println(cntArtWithICD);
            System.out.print("Total ICD9 code found: ");
            System.out.println(cntTotalICDs);

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

            if (inICD9Code || (inICD9>0))
            {
                String code = new String(ch,start,length);
                code = code.trim();
                if (code.length()>0)
                {
                    icdList.add(code);
                }
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
