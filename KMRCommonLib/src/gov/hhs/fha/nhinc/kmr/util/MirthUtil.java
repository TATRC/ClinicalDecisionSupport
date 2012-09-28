/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.util;

/**
 *
 * @author Jerry Goodnough
 */
public class MirthUtil {

    public static String getFloatRep(String num)
    {
       String out = "0.0";

       if (num != null)
       {
           try
           {
                float x =Float.parseFloat(num);
                out = Float.toString(x);
           }
           catch(NumberFormatException exp)
           {

           }
       }
       return out;
    }
}
