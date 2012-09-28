/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.cohort;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Difference in dates by
 * @author Jerry Goodnough
 */
public class DateDifference {

    private int years;
    private int months;
    private int days;
    private int absDays;
    private GregorianCalendar srt;
    private GregorianCalendar end;

    public int getYears() {
        return years;
    }

    public int getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }

    public int getAbdDays() {
        return absDays;
    }

    private DateDifference()
    {

    }
    public boolean equals(Object o)
    {
        if (o instanceof DateDifference)
        {
            DateDifference dd = (DateDifference) o;
            return ((years == dd.years) && (months == dd.months) && (days == dd.days));
        }
        else
        {
            return false;
        }
    }

    public boolean equals(DateDifference o)
    {
        return ((years == o.years) && (months == o.months) && (days == o.days));
    }

    public static DateDifference getDateDifference(GregorianCalendar d1, GregorianCalendar d2) {
        DateDifference d = new DateDifference();
        //Flip Start and End if required
        if (d1.getTime().after(d2.getTime())) {
            d.end = d1;
            d.srt = d2;
        } else {
            d.end = d2;
            d.srt = d1;
        }

        //Inital Year
        int srty = d.srt.get(Calendar.YEAR);
        int endy = d.end.get(Calendar.YEAR);
        d.years = endy - srty;
        int srtm = d.srt.get(Calendar.MONTH);
        int endm = d.end.get(Calendar.MONTH);
        int srtd = d.srt.get(Calendar.DAY_OF_MONTH);
        int endd = d.end.get(Calendar.DAY_OF_MONTH);

        if (srtm > endm) {
            //A Whole years had not yet pass.
            d.years--;

            d.months = (12 - srtm) + endm;

            if (srtd > endd) {
                d.months--;

                int sdim = getDaysInMonth(srtm, srty);
                d.days = sdim - srtd + endd;
            } else {
                d.days = endd - srtd;
            }


        } else if (srtm == endm) {

            if (srtd > endd) {
                d.years--;
                d.months = 11;
                int sdim = getDaysInMonth(srtm, srty);
                d.days = sdim - srtd + endd;
            } else {
                d.months = 0;
                d.days = endd - srtd;
            }
        } else {
            d.months = 12 - endm + srtm;
            if (srtd > endd) {
                d.months--;
            }

            int sdim = getDaysInMonth(srtm, srty);
            d.days = sdim - srtd + endd;

        }


        d.absDays = (int) ((d.end.getTimeInMillis() - d.srt.getTimeInMillis()) / (86400000L))+1;

        return d;
    }

    static int getDaysInMonth(int month, int year) {
        int days = 0;
        switch (month) {
            case 1:  //Jan
            case 3:  //March
            case 5:  //May
            case 7:  //July
            case 8:  //Aug
            case 10: //Oct
            case 12: //Dec
            {
                days = 31;
                break;
            }
            case 4:  //Apr
            case 6:  //June
            case 9:  //Sept
            case 11: //Nov
            {
                days = 30;
                break;
            }
            case 2: //Feb
            {
                days = 28;
                GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
                if (cal.isLeapYear(year)) {
                    days = 29;
                }
                break;
            }
            default: {
                days = 0;
            }

        }
        return days;
    }
}
