/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.icd9wikiextractor;


/**
 * DOCUMENT ME!
 *
 * @author  Jerry Goodnough
 */
public class Main
{

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  args  the command line arguments
     */
    public static void main(String[] args)
    {

        ICD9LinkManager lm = new ICD9LinkManager();
        

        for (int i = 0; i < args.length; i++)
        {
            String arg = args[i];

            {

                if (arg.equalsIgnoreCase("codes"))
                {
                    lm.dumpCodes();
                }
                else if (arg.equalsIgnoreCase("csv"))
                {
                    lm.dumpCSV();
                }
                else if (arg.equalsIgnoreCase("dump"))
                {
                    lm.dumpRaw();
                }
                else if (arg.equalsIgnoreCase("dumpXML"))
                {
                    lm.dumpXML();
                }
                else if (arg.equalsIgnoreCase("errors"))
                {
                    lm.reportErrors();
                }
                else if (arg.equalsIgnoreCase("extract"))
                {
                    lm.loadICD9Links();;
                }
                else if (arg.equalsIgnoreCase("filter"))
                {
                    lm.filterErrors();
                }
                else if (arg.equalsIgnoreCase("gap"))
                {
                    System.out.println();
                }
                else if (arg.equalsIgnoreCase("icd9patch"))
                {
                    ExtractFreebaseMetadata efm = new ExtractFreebaseMetadata();
                    efm.fixICD9References();
                }
                else if (arg.equalsIgnoreCase("load"))
                {
                    String file = "ICD9Wiki.xml";
                    if (i<args.length-1)
                    {
                       file = args[i+1];
                       i++;

                    }
                    lm.loadFromFile(file);
                }
                else if (arg.equalsIgnoreCase("multiples"))
                {
                    int min = 2;
                    if (i<args.length-1)
                    {
                       if (args[i+1].matches("[0-9]"))
                       {
                           min = Integer.parseInt(args[i+1]);
                           i++;
                       }
                    }

                    lm.onlyMultiples(min);
                }
                else if (arg.equalsIgnoreCase("prune"))
                {
                    lm.pruneMultiples();
                }
                else if (arg.equalsIgnoreCase("save"))
                {
                    String file = "ICD9Wiki.xml";
                    if (i<args.length-1)
                    {
                       file = args[i+1];
                       i++;

                    }
                    lm.saveToFile(file);
                }
                else if (arg.equalsIgnoreCase("totals"))
                {
                    lm.reportTotals();
                }
                else
                {
                    System.out.println("Usage Help:  ");
                    System.out.println("   Valid Arguments are");
                    System.out.println("        codes - List ICD9 Codes");
                    System.out.println("        csv - Comma Delimited save");
                    System.out.println("        dump - Raw Dump");
                    System.out.println("        dumpXML - XML Dump");
                    System.out.println("        errors - Reports the errors");
                    System.out.println("        extract - Extract from wikipedia");
                    System.out.println(
                        "        filter - Filer out empty references");
                    System.out.println("        gap - Prints a Gap");
                    System.out.println("        icd9patch - Fix freebase ICD");
                    System.out.println("        load [filename] - Load from XML file");
                    System.out.println(
                        "        multiples [n] - Filters to have only multples");
                    System.out.println(
                        "        prune - Remove nodes with duplicates links");
                    System.out.println("        save [filename] - Reports Statistics  ");
                    System.out.println("        totals - Reports Statistics  ");
                }
            }
        }

    }


}
