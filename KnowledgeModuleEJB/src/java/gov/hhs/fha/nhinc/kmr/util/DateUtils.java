package gov.hhs.fha.nhinc.kmr.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtils {

   //2010-03-22T00:03:22.000-00:07
   //public static final String XMLGREGORDATE_FORMAT = "yyyy-MM-dd HH:mm:sss";
   public static final String XMLDATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

   /**
    * Trasnforms String -->
    * @param dateFormat
    * @return
    */
   public static String now(String dateFormat) {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
      return sdf.format(cal.getTime());
   }

   public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
   public static String now() {
      return now(DATE_FORMAT_NOW);
   }

   
   //--------------------------------------------------------
   // CONVERTING:
   //    XMLGregorianCalendar --> GregorianCalendar --> Date
   //--------------------------------------------------------
   // where inDate = 2010-05-22T00:03:22.000-00:07
   public Date XMLString2Date(String inDate) throws DatatypeConfigurationException {

      Date retDate = null;

      if (inDate == null || inDate.isEmpty()) {
         retDate = null;
      } else {
         DatatypeFactory df = DatatypeFactory.newInstance();
         XMLGregorianCalendar myXtime = df.newXMLGregorianCalendar(inDate);
         retDate = myXtime.toGregorianCalendar().getTime();
      }
      return retDate;
   }

   public Date XMLGregorian2Date(XMLGregorianCalendar cal){
      if (cal == null) 
         return null;
      else
         return cal.toGregorianCalendar().getTime();
   }

   /**
    * Get current Date Time as XMLGregorianCalendar
    * @return XMLGregorianCalendar
    * @throws DatatypeConfigurationException
    */
   public XMLGregorianCalendar getCurrDateTimeAsXMLGregorian() throws DatatypeConfigurationException {
      Calendar cal = Calendar.getInstance();
      return this.Date2XMLDate(cal.getTime());
   }

   /**
    * Get current Date time as Date object
    * @return Date
    * @throws DatatypeConfigurationException
    */
   public Date getCurrDateTimeAsDate() throws DatatypeConfigurationException {
      //return XMLString2Date(getCurrDateTimeAsXMLGregorian().toXMLFormat());
      return Calendar.getInstance().getTime();
   }

   /**
    * Transforms object from Date --> XMLGregorianCalendar
    * @param d
    * @return
    * @throws DatatypeConfigurationException
    */
   public XMLGregorianCalendar Date2XMLDate(Date d)
           throws DatatypeConfigurationException {

      if (d == null) {
         return null;
      } else {
         Calendar c = new GregorianCalendar();
         c.setTime(d);
         int month = c.get(Calendar.MONTH) + 1;
         int day = c.get(Calendar.DAY_OF_MONTH);
         int year = c.get(Calendar.YEAR);
         DatatypeFactory df = DatatypeFactory.newInstance();

         XMLGregorianCalendar x = df.newXMLGregorianCalendar(year,
                 month,
                 day,
                 c.get(Calendar.HOUR),
                 c.get(Calendar.MINUTE),
                 c.get(Calendar.SECOND),
                 c.get(Calendar.MILLISECOND),
                 c.getTimeZone().getOffset(d.getTime()) / (60 * 60 * 1000));
         return x;
      }
   }

  public static void debug() {
     System.out.println(DateUtils.now("dd MMMMM yyyy"));
     System.out.println(DateUtils.now("yyyyMMdd"));
     System.out.println(DateUtils.now("dd.MM.yy"));
     System.out.println(DateUtils.now("MM/dd/yy"));
     System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
     System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
     System.out.println(DateUtils.now("h:mm a"));
     System.out.println(DateUtils.now("H:mm:ss:SSS"));
     System.out.println(DateUtils.now("K:mm a,z"));
     System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));

     Calendar c = Calendar.getInstance();
     Date mydate = new Date();
mydate = c.getTime();

     c.setTime(mydate);
  }
}

