/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.med.dzreg;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author kim
 */
public class Utils {

  private static DateFormat shortDtFmt = new SimpleDateFormat("MM/dd/yyyy");
  
  public static Date toDate(Timestamp timestamp) {
    long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
    return new java.util.Date(milliseconds);
  }

  public static Timestamp toTimestamp(Date date) {
    java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
    return timeStampDate;
  }

  public static String dateToString(Date date) {
    // Using DateFormat format method create a string representation of a date
    // with the defined format.
    return shortDtFmt.format(date);
  }
}
