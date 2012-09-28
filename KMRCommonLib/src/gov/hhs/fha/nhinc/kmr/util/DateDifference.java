package gov.hhs.fha.nhinc.kmr.util;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * <p>Class to handle a Difference in dates. The class does not account for odd
 * calendar updates, like the Gregorian reform or the Chesterfields Act on 1750.
 * This will only really have an effect if date from differnent locals/calendars
 * are being used.</p>
 *
 * <p>The 1582 gregorian reform would notmally mean that there is no Oct 1-10 in
 * those countries that adopted it the. The Chesterfied act both realigned the
 * year (1751 which went March 25th [Lady Day] to December 31st [282 days]), But
 * also elimnated Sept 3rd to 12th 1752. As Such the Dates Jan 1 to March 24
 * 1751 OS also do not exist england. The Jan 1 to March 24 1750 OS are what we
 * would generally think of as Jan 1 to March 24th 1751(NS)</p>
 *
 * <p>In the future relate to the java.xml.datatype.duration</p>
 *
 * @author  Jerry Goodnough
 */
public class DateDifference
{

    //~ Enums ------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @author   Jerry Goodnough
     * @version  $Revision$, $Date$
     */
    public enum Sign
    {

        //~ Enum constants -----------------------------------------------------

        NEGATIVE, POSITIVE
    }

    //~ Instance fields --------------------------------------------------------

    /** DOCUMENT ME! */
    private int sign = 1;

    /** DOCUMENT ME! */
    private int seconds;

    /** DOCUMENT ME! */
    private int hours;

    /** DOCUMENT ME! */
    private int minutes;

    /** DOCUMENT ME! */
    private int years;

    /** DOCUMENT ME! */
    private int months;

    /** DOCUMENT ME! */
    private int days;

    /** DOCUMENT ME! */
    private long timeMils;

    //~ Constructors -----------------------------------------------------------

    public DateDifference()
    {
        this.years = 0;
        this.months = 0;
        this.days = 0;
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public DateDifference(DateDifference dd)
    {
        this.years = dd.years;
        this.months = dd.months;
        this.days = dd.days;
        this.hours = dd.hours;
        this.minutes = dd.minutes;
        this.seconds = dd.seconds;
    }

    public DateDifference(int years, int months, int days)
    {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public DateDifference(int years, int months, int days, int hours,
        int minutes, int seconds)
    {
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }


    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   dd1  DOCUMENT ME!
     * @param   dd2  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static DateDifference add(DateDifference dd1, DateDifference dd2)
    {
        DateDifference out = new DateDifference(dd1);
        out.add(dd2);

        return out;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   parseString  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static DateDifference getDateDifference(String parseString)
    {
        DateDifference d = new DateDifference();
        //Break into Tokens Numeric/Code pairs

        StringBuffer buf = new StringBuffer();
        for (int i=0;i<parseString.length();i++)
        {
            //Grab amount
            char c = parseString.charAt(i);

            if (Character.isDigit(c))
            {
                buf.append(c);
            }
            else
            {
                int val = Integer.parseInt(buf.toString());
                buf = new StringBuffer();
                switch(c)
                {
                    case 'y':
                    case 'Y':
                    {
                        d.years+=val;
                        break;
                    }
                    case 'd':
                    case 'D':
                    {
                        d.days+=val;
                        break;
                    }
                    case 'h':
                    case 'H':
                    {
                        d.hours+=val;
                        break;
                    }
                    case 's':
                    case 'S':
                    {
                        d.seconds+=val;
                    }
                    case 'm':
                    {
                        d.months+=val;
                        break;
                    }
                    case 'M':
                    {
                        d.minutes+=val;
                        break;
                    }
                    default:
                    {
                        return null;
                    }
                }
            }

        }
        return d;
    }

    //~ ------------------------------------------------------------------------

    /**
     * Calaculate the Data differnece between to dates.
     *
     * @param   d1  minuend
     * @param   d2  subtrahend
     *
     * @return  difference
     */
    public static DateDifference getDateDifference(GregorianCalendar d1,
        GregorianCalendar d2)
    {
        DateDifference d = new DateDifference();
        GregorianCalendar srt, end;

        //Flip Start and End if required
        if (d1.getTime().after(d2.getTime()))
        {
            end = d1;
            srt = d2;
            d.sign = 1;
        }
        else
        {
            end = d2;
            srt = d1;
            d.sign = -1;
        }

        //Inital Year
        int srty = srt.get(Calendar.YEAR);
        int endy = end.get(Calendar.YEAR);
        d.years = endy - srty;

        int srtm = srt.get(Calendar.MONTH);
        int endm = end.get(Calendar.MONTH);
        int srtd = srt.get(Calendar.DAY_OF_MONTH);
        int endd = end.get(Calendar.DAY_OF_MONTH);

        if (srtm > endm)
        {

            //A Whole years had not yet pass.
            d.years--;

            d.months = (12 - srtm) + endm;

            if (srtd > endd)
            {
                d.months--;

                int sdim = getDaysInMonth(srtm, srty);
                d.days = sdim - srtd + endd;
            }
            else
            {
                d.days = endd - srtd;
            }


        }
        else if (srtm == endm)
        {

            if (srtd > endd)
            {
                d.years--;
                d.months = 11;

                int sdim = getDaysInMonth(srtm, srty);
                d.days = sdim - srtd + endd;
            }
            else
            {
                d.months = 0;
                d.days = endd - srtd;
            }
        }
        else
        {
            d.months = 12 - endm + srtm;

            if (srtd > endd)
            {
                d.months--;
            }

            int sdim = getDaysInMonth(srtm, srty);
            d.days = sdim - srtd + endd;

        }

        //Inital Year
        int srthr = srt.get(Calendar.HOUR);
        int endhr = end.get(Calendar.HOUR);
        int srtmn = srt.get(Calendar.MINUTE);
        int endmn = end.get(Calendar.MINUTE);
        int srtsc = srt.get(Calendar.SECOND);
        int endsc = end.get(Calendar.SECOND);

        if (srthr > endhr)
        {
            d.days--;
            d.hours = 23 - srthr + endhr;
        }

        if (srtmn > endmn)
        {
            d.hours--;
            d.minutes = 59 - srtmn + endmn;
        }
        else
        {
            d.minutes = endmn - srtmn;
        }

        if (srtsc > endsc)
        {
            d.minutes--;
            d.seconds = 59 - srtsc + endsc;
        }
        else
        {
            d.seconds = endsc - srtsc;
        }

        //Adujust for backward roll
        if (d.minutes == -1)
        {
            d.minutes = 59;
            d.hours--;
        }

        if (d.hours == -1)
        {
            d.days--;
            d.hours = 23;
        }

        d.timeMils = end.getTimeInMillis() - srt.getTimeInMillis();

        return d;
    }

    //~ ------------------------------------------------------------------------

    /**
     * Creates a new Date difference object which is the addition of is object
     * to the input differn.
     *
     * @param  dd  Difference to Add to the current date diffe
     */
    public void add(DateDifference dd)
    {

        if (sign == 1)
        {
            years += dd.getYears();
            months += dd.getMonths();
            days += dd.getDays();
            hours += dd.getHours();
            minutes += dd.getMinutes();
            seconds += dd.getSeconds();
        }
        else
        {
            years -= dd.getYears();
            months -= dd.getMonths();
            days -= dd.getDays();
            hours -= dd.getHours();
            minutes -= dd.getMinutes();
            seconds -= dd.getSeconds();

        }

        normalize();

        if ((timeMils == -1) || (dd.timeMils == -1))
        {
            timeMils = -1;
        }
        else
        {
            timeMils += (dd.sign * dd.timeMils);
        }
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   in  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public GregorianCalendar add(GregorianCalendar in)
    {
        GregorianCalendar out = (GregorianCalendar) in.clone();
        out.add(GregorianCalendar.YEAR, years);
        out.add(GregorianCalendar.MONTH, months);
        out.add(GregorianCalendar.DAY_OF_YEAR, days);
        out.add(GregorianCalendar.HOUR, hours);
        out.add(GregorianCalendar.MINUTE, minutes);
        out.add(GregorianCalendar.SECOND, seconds);

        return out;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   in  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public GregorianCalendar apply(GregorianCalendar in)
    {
        GregorianCalendar out = (GregorianCalendar) in.clone();
        out.add(GregorianCalendar.YEAR, sign * years);
        out.add(GregorianCalendar.MONTH, sign * months);
        out.add(GregorianCalendar.DAY_OF_YEAR, sign * days);
        out.add(GregorianCalendar.HOUR, sign * hours);
        out.add(GregorianCalendar.MINUTE, sign * minutes);
        out.add(GregorianCalendar.SECOND, sign * seconds);

        return out;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   o  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public boolean equals(Object o)
    {

        if (o instanceof DateDifference)
        {
            DateDifference dd = (DateDifference) o;

            return ((years == dd.years) && (months == dd.months) &&
                    (days == dd.days) && (hours == dd.hours) &&
                    (minutes == dd.minutes) && (seconds == dd.seconds) &&
                    (sign == dd.sign));
        }
        else
        {
            return false;
        }
    }

    //~ ------------------------------------------------------------------------

    /**
     * Compare if two DateDifference objects are exactly equal.
     *
     * @param   dd  o DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public boolean equals(DateDifference dd)
    {
        return ((years == dd.years) && (months == dd.months) &&
                (days == dd.days) && (hours == dd.hours) &&
                (minutes == dd.minutes) && (seconds == dd.seconds) &&
                (sign == dd.sign));
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getAbsDays()
    {
        return days;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getAbsHours()
    {
        return hours;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getAbsMinutes()
    {
        return minutes;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getAbsMonths()
    {
        return months;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getAbsSeconds()
    {
        return seconds;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getAbsYears()
    {
        return years;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getDays()
    {
        return sign * days;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getHours()
    {
        return sign * hours;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getMinutes()
    {
        return sign * minutes;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getMonths()
    {
        return sign * months;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getSeconds()
    {
        return sign * seconds;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Sign getSign()
    {
        return (sign < 0) ? Sign.NEGATIVE : Sign.POSITIVE;
    }

    //~ ------------------------------------------------------------------------

    /**
     * Determines the total days the date difference represents based on the
     * absolute number of Millseconds between the two. When real dates are used
     * or differences of less then a month this value ius valid. Is a
     * DateDifference is created with a Years or Months value it is not onlt
     *
     * @return  DOCUMENT ME!
     */
    public int getTotalDays()
    {
        return (int) ((timeMils) / (86400000L)) + 1;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getTotalMonths()
    {
        return (years * 12) + months;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public int getYears()
    {
        return sign * years;
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  days  DOCUMENT ME!
     */
    public void setDays(int days)
    {

        if (days < 0)
        {
            throw new IllegalArgumentException("Days must be Zero or greater");
        }

        this.days = days;
        recomputeTimeMils();

    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  hours  DOCUMENT ME!
     */
    public void setHours(int hours)
    {

        if ((hours < 0) || (hours > 23))
        {
            throw new IllegalArgumentException(
                "Hours must be between 0 and 23");
        }

        this.hours = hours;
        recomputeTimeMils();

    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  minutes  DOCUMENT ME!
     */
    public void setMinutes(int minutes)
    {

        if ((minutes < 0) || (minutes > 59))
        {
            throw new IllegalArgumentException(
                "Minutes must be between 0 and 59");
        }

        this.minutes = minutes;
        recomputeTimeMils();

    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  months  DOCUMENT ME!
     */
    public void setMonths(int months)
    {

        if ((months < 0) || (months > 11))
        {
            throw new IllegalArgumentException(
                "Months must be between 0 and 11");
        }

        this.months = months;
        recomputeTimeMils();
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  seconds  DOCUMENT ME!
     */
    public void setSecond(int seconds)
    {

        if ((seconds < 0) || (seconds > 59))
        {
            throw new IllegalArgumentException(
                "Seconds must be between 0 and 59");
        }

        this.seconds = seconds;
        recomputeTimeMils();
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  s  DOCUMENT ME!
     */
    public void setSign(Sign s)
    {

        if (s == Sign.NEGATIVE)
        {
            sign = -1;
        }
        else
        {
            sign = 0;
        }
    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param  years  DOCUMENT ME!
     */
    public void setYears(int years)
    {

        if (years < 0)
        {
            throw new IllegalArgumentException("Year must be 0 of Greater");
        }

        this.years = Math.abs(years);
        recomputeTimeMils();

    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   in  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public GregorianCalendar subtract(GregorianCalendar in)
    {
        GregorianCalendar out = (GregorianCalendar) in.clone();
        out.add(GregorianCalendar.YEAR, -1 * years);
        out.add(GregorianCalendar.MONTH, -1 * months);
        out.add(GregorianCalendar.DAY_OF_YEAR, -1 * days);
        out.add(GregorianCalendar.HOUR, -1 * hours);
        out.add(GregorianCalendar.MINUTE, -1 * minutes);
        out.add(GregorianCalendar.SECOND, -1 * seconds);

        return out;
    }

    //~ ------------------------------------------------------------------------

    /**
     * Helper Function to determine the number of days in a Month.
     *
     * @param   month  DOCUMENT ME!
     * @param   year   DOCUMENT ME!
     *
     * @return  Number of days normally in a Month.
     */
    static int getDaysInMonth(int month, int year)
    {
        int days = 0;

        switch (month)
        {

            case 1: //Jan
            case 3: //March
            case 5: //May
            case 7: //July
            case 8: //Aug
            case 10: //Oct
            case 12: //Dec
            {
                days = 31;

                break;
            }

            case 4: //Apr
            case 6: //June
            case 9: //Sept
            case 11: //Nov
            {
                days = 30;

                break;
            }

            case 2: //Feb
            {
                days = 28;

                GregorianCalendar cal = (GregorianCalendar) GregorianCalendar
                    .getInstance();

                if (cal.isLeapYear(year))
                {
                    days = 29;
                }

                break;
            }

            default:
            {
                days = 0;
            }

        }

        return days;
    }

    //~ ------------------------------------------------------------------------

    /**
     * Helper function to normalize the Fields - Will insure that all fields
     * with the exception of days & years will be in a "normal" range. In other
     * words under/over flows of the months, hours, minutes, seconds will cause
     * the related unit to be adjusted while bring the value back in range.
     *
     * <ul>
     *   <li>Months (1-12)</li>
     *   <li>Hours (0-23)</li>
     *   <li>Minutes (0-59)</li>
     *   <li>Secondss (0-59)</li>
     * </ul>
     */
    private void normalize()
    {

        if (seconds > 59)
        {
            minutes++;
            seconds -= 60;
        }
        else if (hours < 0)
        {
            minutes--;
            seconds += 60;
        }

        if (minutes > 59)
        {
            hours++;
            minutes -= 60;
        }
        else if (minutes < 0)
        {
            hours--;
            minutes += 60;
        }

        if (hours > 23)
        {
            days++;
            hours -= 24;
        }
        else if (hours < 0)
        {
            days--;
            hours += 24;
        }
        //Ok things get tricky with days - Since we if we roll the question is
        //what happens? For now we will leave the days as is.

        if (months > 11)
        {
            years++;
            months -= 12;
        }
        else if (months < 0)
        {
            years--;
            months += 12;
        }

    }

    //~ ------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    private void recomputeTimeMils()
    {

        if ((years == 0) && (months == 0))
        {
            timeMils = (days * 1000L * 60L * 60L * 24L) +
                (hours * 100L * 60L * 60L) + (minutes * 1000L * 60) +
                (seconds * 1000L);
        }
        else
        {
            timeMils = 0;
        }
    }
}
