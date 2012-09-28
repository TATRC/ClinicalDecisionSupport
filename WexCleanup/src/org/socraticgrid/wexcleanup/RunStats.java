/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcleanup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Jerry Goodnough
 */
public class RunStats {

    private static Log log = LogFactory.getLog(RunStats.class);

    private boolean statsOnly = false;

    public boolean isStatsOnly()
    {
        return statsOnly;
    }

    public void setStatsOnly(boolean val)
    {
        statsOnly = val;
    }

    public void addStat(String task, String stat, int count)
    {
        log.info(task+", "+stat+" = "+Integer.toString(count));
    }
}
