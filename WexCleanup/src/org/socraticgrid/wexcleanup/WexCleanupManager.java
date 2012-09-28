/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcleanup;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jerry Goodnough
 */
public class WexCleanupManager {


    private List<ICD9Ref> links = new LinkedList<ICD9Ref>();
    
    public List<ICD9Ref> getLinkList()
    {
        return links;
    }

    public void copyICDWPIDS(RunStats stats)
    {
        BuildCandidateGUIDS build = new BuildCandidateGUIDS();
        build.copyICDGUIDS(stats);
    }

    public void copyDiseaseWPIDS(RunStats stats)
    {
        BuildCandidateGUIDS build = new BuildCandidateGUIDS();
        build.copyDiseaseGUIDS(stats);

    }

    public void findPresentWPIDS(RunStats stats)
    {
        ArticleHandler ah = new ArticleHandler();
        ah.deleteCandidateWpids(stats);
        ah.getCandidateWPIDS(stats);
    }

    public void dumpPresentWPIDS(RunStats stats)
    {
        ArticleHandler ah = new ArticleHandler();
        ah.dumpCandidateWPIDS(stats);

    }

    public void loadArticles(RunStats stats, String file)
    {
        CandidateLoader cl = new CandidateLoader();
        System.out.println("Loading Articles Mappings from "+file);
        cl.loadCandidateWPIDS(stats, file);
        System.out.println("Load of Articles Mappings Complete");
    }

    public void deleteArticles(RunStats stats, String source)
    {
        CandidateLoader cl = new CandidateLoader();
        System.out.println("Delete Articles Mappings from "+source);
        cl.removeSource(stats, source);
        System.out.println("Delete of Articles Mappings Complete");
    }

    public void copyCandidateArticles(RunStats stats)
    {
        ArticleHandler ah = new ArticleHandler();
        ah.deleteArticles(stats);
        ah.copyCandidateArticle(stats);
    }

    public void copyCandidateSections(RunStats stats)
    {
        ArticleHandler ah = new ArticleHandler();
        ah.deleteSections(stats);
        ah.copyCandidateSections(stats);
    }

    public void deleteCandidateArticles(RunStats stats)
    {
        ArticleHandler ah = new ArticleHandler();
        ah.deleteArticles(stats);
    }
    
    public void deleteCandidateSections(RunStats stats)
    {
        ArticleHandler ah = new ArticleHandler();
        ah.deleteSections(stats);
    }

    public void scanArticals(RunStats stats)
    {
        ArticleHandler ah = new ArticleHandler();
        //ah.copyCandidateArticle(stats);
    }

    public void loadICD9Links()
    {
        ExtractFirstTierStructure tier1 = new ExtractFirstTierStructure();
        List<String> tier2Links = tier1.getSecondTier();
        ExtractSecondLevelDetail detail = new ExtractSecondLevelDetail();
        detail.parseTier2Pages(tier2Links, links);
    }


    public void filterErrors()
    {
        Iterator<ICD9Ref> itr = links.iterator();
        List<ICD9Ref> newList = new LinkedList<ICD9Ref>();

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            if (ref.links.size()!=0)
            {
                newList.add(ref);
            }

        }

        links = newList;
    }

    public void pruneMultiples()
    {
        Iterator<ICD9Ref> itr = links.iterator();
        List<ICD9Ref> newList = new LinkedList<ICD9Ref>();

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            if (ref.links.size()<2)
            {
                newList.add(ref);
            }

        }

        links = newList;
    }
    public void onlyMultiples(int min)
    {
        Iterator<ICD9Ref> itr = links.iterator();
        List<ICD9Ref> newList = new LinkedList<ICD9Ref>();

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            if (ref.links.size()>min)
            {
                newList.add(ref);
            }

        }

        links = newList;
    }
    public void dumpRaw()
    {
        Iterator<ICD9Ref> itr = links.iterator();

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            System.out.println(ref.toString());
        }

    }

    public void dumpCodes()
    {
        Iterator<ICD9Ref> itr = links.iterator();

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            System.out.println(ref.ICD9Code);
        }
    }

    public void dumpCSV()
    {
        Iterator<ICD9Ref> itr = links.iterator();

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            System.out.print(ref.ICD9Code);
            System.out.print(",");
            int size = ref.links.size();
            if (size > 1)
            {
                for (int i =0; i<size; i++)
                {
                    System.out.print(ref.links.get(i));
                    if (i != (size-1))
                    {
                        System.out.print("|");
                    }
                }
            }
            else if (size ==1)
            {
                System.out.print(ref.links.get(0));
            }
            System.out.println();
        }

    }

    public void reportErrors()
    {
        Iterator<ICD9Ref> itr = links.iterator();
        int errors=0;

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            if (ref.links.size()==0)
            {
                errors++;
                System.out.println("ICD9 Code "+ref.getICD9Code()+" no links");
            }

        }
        System.out.println("Total Codes = "+Integer.toString(links.size()));
        System.out.println("Total Errors = "+Integer.toString(errors));
    }

   public void reportTotals()
   {
        Iterator<ICD9Ref> itr = links.iterator();
        int[] tots = new int[10];

        for (int i =0; i<10; i++)
        {
            tots[i]=0;
        }

        while(itr.hasNext())
        {
            ICD9Ref ref = itr.next();
            int size;
            size = ref.links.size();

            if (size>8)
            {
                size=9;
            }
            tots[size]++;
        }
        System.out.println("Total Codes = "+Integer.toString(links.size()));
        System.out.println("Total Errors = "+Integer.toString(tots[0]));
        for (int i = 1; i<10; i++)
        {
            if (tots[i]>0)
            {
                System.out.print("Codes with ");
                System.out.print(i);
                if (i==9)
                {
                    System.out.print(" or more");
                }
                System.out.print(" link");
                if (i > 1)
                {
                    System.out.print("s");
                }
                System.out.print(" = ");
                System.out.println(tots[i]);

            }
        }

   }

   public void saveToFile(String fileName)
   {
       File fl = new File(fileName);
       try
       {
           FileOutputStream outStream = new FileOutputStream(fl);
           try
           {
                saveAsXMLToStream(outStream);
           }
           finally
           {
                   outStream.close();
           }
       }
       catch(FileNotFoundException exp)
       {
           System.err.println(exp.getMessage());
           exp.printStackTrace(System.err);
       }
       catch(IOException exp)
       {
           System.err.println(exp.getMessage());
           exp.printStackTrace(System.err);
       }
   }

   public void loadFromFile(String fileName)
   {
       File fl = new File(fileName);
       try
       {
           FileInputStream inStream = new FileInputStream(fl);
           try
           {
                loadAsXMLFromStream(inStream);
           }
           finally
           {
                inStream.close();
           }
       }
       catch(FileNotFoundException exp)
       {
           System.err.println(exp.getMessage());
           exp.printStackTrace(System.err);
       }
       catch(IOException exp)
       {
           System.err.println(exp.getMessage());
           exp.printStackTrace(System.err);
       }
    }
   public void saveAsXMLToStream(OutputStream outStream)
   {
       XStream xstream = new XStream();
       xstream.alias("icd9mapping", ICD9Ref.class);
       xstream.toXML(this.links,outStream);
   }

   public void loadAsXMLFromStream(InputStream inStream)
   {


       XStream xstream = new XStream();
       xstream.alias("icd9mapping", ICD9Ref.class);


       links = (List<ICD9Ref>)xstream.fromXML(inStream);
   }

   public void dumpXML()
   {
       saveAsXMLToStream(System.out);
   }
}
