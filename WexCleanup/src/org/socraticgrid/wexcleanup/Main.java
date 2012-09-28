/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcleanup;


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

        WexCleanupManager lm = new WexCleanupManager();
        RunStats stats = new RunStats();
        

        for (int i = 0; i < args.length; i++)
        {
            String arg = args[i];

            {

                if (arg.equalsIgnoreCase("all"))
                {
                    lm.copyICDWPIDS(stats);
                    lm.copyDiseaseWPIDS(stats);
                    lm.findPresentWPIDS(stats);
                    lm.copyCandidateArticles(stats);
                    lm.scanArticals(stats);
                }
                else if (arg.equalsIgnoreCase("icd"))
                {
                    lm.copyICDWPIDS(stats);
                }
                else if (arg.equalsIgnoreCase("disease"))
                {
                    lm.copyDiseaseWPIDS(stats);
                }
                else if (arg.equalsIgnoreCase("candidates"))
                {
                    lm.copyICDWPIDS(stats);
                    lm.copyDiseaseWPIDS(stats);
                }
                else if (arg.equalsIgnoreCase("getarticlewpids"))
                {
                    lm.findPresentWPIDS(stats);
                }
                else if (arg.equalsIgnoreCase("dumparticlewpids"))
                {
                    lm.dumpPresentWPIDS(stats);
                }
                else if (arg.equalsIgnoreCase("deletearticles"))
                {
                    lm.deleteCandidateArticles(stats);
                }
                else if (arg.equalsIgnoreCase("copyarticles"))
                {
                    lm.copyCandidateArticles(stats);
                }
                else if (arg.equalsIgnoreCase("deletesections"))
                {
                    lm.deleteCandidateSections(stats);
                }
                else if (arg.equalsIgnoreCase("copysections"))
                {
                    lm.copyCandidateSections(stats);
                }
                else if (arg.equalsIgnoreCase("scanarticles"))
                {
                    lm.scanArticals(stats);
                }
                else if (arg.equalsIgnoreCase("icd"))
                {
                    lm.copyICDWPIDS(stats);
                }
                else if (arg.equalsIgnoreCase("writeallow"))
                {
                    stats.setStatsOnly(false);
                }
                else if (arg.equalsIgnoreCase("readonly"))
                {
                    stats.setStatsOnly(true);

                }
                else if (arg.equalsIgnoreCase("deletemappings"))
                {
                    stats.setStatsOnly(false);
                    String file = args[i+1];
                    i++;
                    lm.deleteArticles(stats, file);
                }
                else if (arg.equalsIgnoreCase("loadmappings"))
                {
                    stats.setStatsOnly(false);
                    String file = args[i+1];
                    i++;
                    lm.loadArticles(stats, file);
                }
                else if (arg.equalsIgnoreCase("icd9find"))
                {
                    ExtractFreebaseMetadata efm = new ExtractFreebaseMetadata();
                    efm.deleteICD9Mappings(stats);
                    efm.findICD9References(stats);
                }
                else
                {
                    System.out.print("You entered: ");
                    System.out.println(arg);
                    System.out.println("Usage Help:  ");
                    System.out.println("   Valid Arguments are");
                    System.out.println("        all - Run full process");
                    System.out.println("        icd - Scan ICD table for candidates");
                    System.out.println("        disease - Scan Disease table for candidates");
                    System.out.println("        candidates - Scan for candidates");
                    System.out.println("        getarticlewpids - get the Artical wpids");
                    System.out.println("        dumparticlewpids - dump the Artical wpids");
                    System.out.println("        deletearticles - Deleet candiate articles from working store");
                    System.out.println("        copyarticles - Copy candiate articles to working store");
                    System.out.println("        scanarticles - Scan Articles to build ICD9 code association");
                    System.out.println("        reset - Reset Data Extraction");
                    System.out.println("        loadmappings - Load Article Mappings");
                    System.out.println("        deletemappings - Delete Article Mappings");
                    System.out.println("        icd9FIND - Articles for ICD");
                }
            }
        }

    }


}
