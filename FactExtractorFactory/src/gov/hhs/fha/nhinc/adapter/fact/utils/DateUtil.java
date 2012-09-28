/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author kim
 */
public class DateUtil {

  private static DateFormat dfYYYYMMDD = null;
  private static DateFormat cdaDateTimeFormat = null;
  private static DateFormat cdaDateTimeNoZoneFormat = null;
  private static DateFormat cdaDateFormat = null;
  

  static {
    dfYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    cdaDateFormat = new SimpleDateFormat("yyyyMMddZ");                // YYYYMMDD-0000
    cdaDateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmssZ");      // YYYYMMDDHHmmss-0000
    cdaDateTimeNoZoneFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // YYYYMMDDHHmmss
  }

  public static String formatYYYYMMDD(GregorianCalendar gCal) {
    return String.valueOf(gCal.get(GregorianCalendar.YEAR)) +
            String.valueOf(gCal.get(GregorianCalendar.MONTH)) +
            String.valueOf(gCal.get(GregorianCalendar.DATE));
  }

  public static Date unmarshalYYYYMMDD(String date) {
    try {
      return dfYYYYMMDD.parse(date);
    } catch (ParseException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
  * Marshall CDA date/time/zone format to java Date type.
  *
  * HL7 supports following date/time format: YYYYMMDDHHMMSS.SSSS+/-ZZZZ  with the time zone (+/-ZZZZ) 
  * represented as +/-HHMM offset from UTC (formerly Greenwich Mean Time (GMT)), where +0000 or -0000
  * both represent UTC (without offset). Example:  19760704010159-0700 where UTC is 7 hours ahead of
  * PDT time zone.
  *
  */
  public static Date parseCDADateFormat(String xsdDate) {
    Date jDate = null;
    try {
      // Assume there are zone part in date string, use date/time with zone format
      jDate = cdaDateTimeFormat.parse(xsdDate);
    } catch (ParseException ex) {
      try {
        // If zone is not available, use date/time with no zone format
        jDate = cdaDateTimeNoZoneFormat.parse(xsdDate);
      } catch (ParseException ex1) {
        try {
          // No time portion in date string, use date with zone format
          jDate = cdaDateFormat.parse(xsdDate);
        } catch (ParseException ex2) {
          try {
            // No time and zone portion in date string, use date with no time and zone format
            jDate = dfYYYYMMDD.parse(xsdDate);
          } catch (ParseException ex3) {
            ex3.printStackTrace();
          }
        }
      }
    }

    //System.out.println("+++++++++++++++  xsdDate=" + xsdDate + " converted to javaDate=" + jDate);
    return jDate;
  }

  public static String marshalYYYYMMDD(Date date) {
    return dfYYYYMMDD.format(date);
  }

  public static String convertToCDATime(Date date) {
    return cdaDateTimeFormat.format(date);
  }

  /**
   * convert T-format date to CDA format date.
   * 
   * @param tDate
   * @return
   */
  public static String convertTFormatToCDATime(String tDate) throws Exception {
     if (tDate == null || tDate.length() < 0)
        return null;
     
     // today date
     if (tDate.equalsIgnoreCase("T"))
        convertToCDATime(Calendar.getInstance().getTime());

     throw new Exception("Need to be implemented!");
  }  
}
