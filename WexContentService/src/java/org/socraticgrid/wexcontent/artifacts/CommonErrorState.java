/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.artifacts;

/**
 *
 * @author Jerry Goodnough
 */
public class CommonErrorState {

    public boolean successStatus=false;
    /**
     *   0  = OK
     *  >0  = Warning
     *  <0  = Error
     *
     * 1 = No Data Found
     * 2 = Multiple Matches found, first returned.
     */

    public static int STATUS_NODATAFOUND = 1;
    public static int STATUS_MULTIMATCH = 2;
    public static int STATUS_OK = 0;
    public static int STATUS_ERROR = -1;


    public int statusCode=0;
    public String statusMessage;
}
