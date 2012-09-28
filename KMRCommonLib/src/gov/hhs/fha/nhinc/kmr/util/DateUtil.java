package gov.hhs.fha.nhinc.kmr.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author  Kim Pham
 */
public class DateUtil {

   /** DOCUMENT ME! */
   private static DateFormat dfYYYYMMDD = null;
   /** DOCUMENT ME! */
   private static DateFormat cdaDateTimeFormat = null;
   /** DOCUMENT ME! */
   private static DateFormat cdaDateTimeNoZoneFormat = null;
   /** DOCUMENT ME! */
   private static DateFormat cdaDateFormat = null;
   private static DateFormat HL7DateFmt = null;
   private static DateFormat XMLDateFmt = null;
   private static DateFormat HL7ShortDateFmt = null;
   private static DateFormat XMLShortDateFmt = null;
   private static DatatypeFactory df = null;

   static {
      dfYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
      cdaDateFormat = new SimpleDateFormat("yyyyMMddZ"); // YYYYMMDD-0000
      cdaDateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmssZ"); // YYYYMMDDHHmmss-0000
      cdaDateTimeNoZoneFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // YYYYMMDDHHmmss

      HL7DateFmt = new SimpleDateFormat("yyyyMMddHHmmss");
      XMLDateFmt = new SimpleDateFormat("yyyy'-'MM'-'dd'T'hh:mm:ss");
      HL7ShortDateFmt = new SimpleDateFormat("yyyyMMdd");
      XMLShortDateFmt = new SimpleDateFormat("yyyy'-'MM'-'dd'T00:00:00'");

      try {
         df = DatatypeFactory.newInstance();
      } catch (DatatypeConfigurationException dce) {
         System.err.println("Exception while obtaining DatatypeFactory instance - " + dce);
      }
   }

   /**
    * convert T-format date to CDA format date.
    *
    * @param   tDate
    *
    * @return
    */
   public static String convertTFormatToCDATime(String tDate) throws Exception {

      if ((tDate == null) || (tDate.length() < 0)) {
         return null;
      }

      // today date
      if (tDate.equalsIgnoreCase("T")) {
         convertToCDATime(Calendar.getInstance().getTime());
      }

      throw new Exception("Need to be implemented!");
   }

   /**
    * DOCUMENT ME!
    *
    * @param   date  DOCUMENT ME!
    *
    * @return  DOCUMENT ME!
    */
   public static String convertToCDATime(Date date) {
      return cdaDateTimeFormat.format(date);
   }

   /**
    * DOCUMENT ME!
    *
    * @param   gCal  DOCUMENT ME!
    *
    * @return  DOCUMENT ME!
    */
   public static String formatYYYYMMDD(GregorianCalendar gCal) {
      return String.valueOf(gCal.get(GregorianCalendar.YEAR)) +
              String.valueOf(gCal.get(GregorianCalendar.MONTH)) +
              String.valueOf(gCal.get(GregorianCalendar.DATE));
   }

   /**
    * DOCUMENT ME!
    *
    * @param   date  DOCUMENT ME!
    *
    * @return  DOCUMENT ME!
    */
   public static String marshalYYYYMMDD(Date date) {
      return dfYYYYMMDD.format(date);
   }

   /**
    * Marshall CDA date/time/zone format to java Date type. HL7 supports
    * following date/time format: YYYYMMDDHHMMSS.SSSS+/-ZZZZ with the time zone
    * (+/-ZZZZ) represented as +/-HHMM offset from UTC (formerly Greenwich Mean
    * Time (GMT)), where +0000 or -0000 both represent UTC (without offset).
    * Example: 19760704010159-0700 where UTC is 7 hours ahead of PDT time zone.
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

      System.out.println("+++++++++++++++  xsdDate=" + xsdDate +
              " converted to javaDate=" + jDate);

      return jDate;
   }

   /**
    * DOCUMENT ME!
    *
    * @param   date  DOCUMENT ME!
    *
    * @return  DOCUMENT ME!
    */
   public static Date unmarshalYYYYMMDD(String date) {

      try {
         return dfYYYYMMDD.parse(date);
      } catch (ParseException ex) {
         ex.printStackTrace();

         return null;
      }
   }

   public static String HL72XML(String HL7Date) {
      String out = "";
      Date d;
      try {
         if (HL7Date.length() == 8) {
            d = HL7ShortDateFmt.parse(HL7Date);
            out = XMLShortDateFmt.format(d);
         } else {
            d = HL7DateFmt.parse(HL7Date);
            out = XMLDateFmt.format(d);
         }

      } catch (Exception exp) {
         System.err.println(exp.getMessage());
      }
      return out;
   }

   public static String XML2HL7(String XMLDate) {
      String out = "";
      Date d;
      try {
         d = XMLDateFmt.parse(XMLDate);
         out = HL7DateFmt.format(d);

      } catch (Exception exp) {
         System.err.println(exp.getMessage());
      }
      return out;

   }

   public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) {
      if (date != null) {
         GregorianCalendar gCal = new GregorianCalendar();
         gCal.setTimeInMillis(date.getTime());
         return df.newXMLGregorianCalendar(gCal);
      }

      return null;
   }

   public static Date XMLGregorianCalendarToDate(XMLGregorianCalendar date) {
      if (date != null) {
         GregorianCalendar gCal = date.toGregorianCalendar();
         return gCal.getTime();
      }

      return null;
   }
}
