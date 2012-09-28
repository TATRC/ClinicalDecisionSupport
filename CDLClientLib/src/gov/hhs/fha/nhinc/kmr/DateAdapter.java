package gov.hhs.fha.nhinc.kmr;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Steven Clark
 */
public class DateAdapter
{
    public static Date parseDate(String s)
    {
        return DatatypeConverter.parseDate(s).getTime();
    }
    public static String printDate(Date dt)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime(dt);
        return DatatypeConverter.printDateTime(cal);
    }
    public static Date parseDateTime(String s)
    {
        return DatatypeConverter.parseDateTime(s).getTime();
    }
    public static String printDateTime(Date dt)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime(dt);
        return DatatypeConverter.printDateTime(cal);
    }
}