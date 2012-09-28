/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.properties;

import gov.hhs.fha.nhinc.kmr.util.DateDifference;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author  Jerry Goodnough
 */
public class DateParse
{

    /**
     * Date format: '-'? yyyy '-' mm '-' dd 'T' hh ':' mm ':' ss ('.' s+)?
     * (zzzzzz)
     */

    private static SimpleDateFormat[] dateFormats;

    static
    {
        SimpleDateFormat[] dateFormats = new SimpleDateFormat[5];
        dateFormats[0] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'z"); // YYYYMMDDHHmmss-0000
        dateFormats[1] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // YYYYMMDDHHmmss-0000
        dateFormats[2] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); // YYYYMMDDHHmmss-0000
        dateFormats[3] = new SimpleDateFormat("yyyy-MM-dd'T'"); // YYYYMMDDHHmmss-0000
        dateFormats[4] = new SimpleDateFormat("yyyy-MM-dd"); // YYYYMMDDHHmmss-0000
    }


    /**
     * DOCUMENT ME!
     *
     * @author   $author$
     * @version  $Revision$, $Date$
     */
    public static enum Type
    {
        ABSOLUTE, DYNAMIC, EMPTY, ERROR, UNBOUNDED
    }

    /** DOCUMENT ME! */
    private GregorianCalendar cal;

    /** DOCUMENT ME! */
    private DateDifference diff;

    /** DOCUMENT ME! */
    private String error;

    /** DOCUMENT ME! */
    private Type type = Type.EMPTY;

    /**
     * DOCUMENT ME!
     *
     * @param   date  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static DateParse parseDateString(String date)
    {
        DateParse out = new DateParse();

        if (!((date == null) || (date.length() == 0)))
        {

            //Ok we now have a none null value to work with
            if ((date.compareTo("0") == 0) ||
                    ((date.compareTo("Unbounded")) == 0))
            {
                out.type = Type.UNBOUNDED;
            }
            else if (date.startsWith("T"))
            {
                out.type = Type.DYNAMIC;

                if (date.length() == 1)
                {
                    out.diff = new DateDifference();
                }
                else
                {
                    char c = date.charAt(1);

                    if ((c == '-') || (c == '+'))
                    {
                        out.diff = DateDifference.getDateDifference(
                                date.substring(2));

                        if (out.diff == null)
                        {
                            out.type = Type.ERROR;
                            out.error = "Unable to parse " + date;
                        }

                        if (c == '-')
                        {
                            out.diff.setSign(DateDifference.Sign.NEGATIVE);
                        }
                    }
                    else
                    {
                        out.type = Type.ERROR;
                        out.error = "Unable to parse " + date;
                    }
                }
            }
            else
            {

                try
                {
                    out.cal = parse(date);
                    out.type = Type.ABSOLUTE;
                }
                catch (IllegalArgumentException ex)
                {
                    out.error = ex.getMessage();
                    out.type = Type.ERROR;
                }
            }
        }

        return out;
    }


    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public GregorianCalendar getCalendar()
    {
        GregorianCalendar xcal = null;

        if (type == Type.DYNAMIC)
        {
            xcal = new GregorianCalendar();
            xcal = diff.apply(xcal);
        }
        else if (type == Type.ABSOLUTE)
        {
            xcal = cal;
        }
        else if (type == Type.UNBOUNDED)
        {
            return null;
        }

        return xcal;

    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Type getType()
    {
        return type;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   dt  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  IllegalArgumentException  DOCUMENT ME!
     */
    private static GregorianCalendar parse(String dt)
        throws IllegalArgumentException
    {
        Date date = null;

        for (int i = 0; i < 5; i++)
        {

            try
            {
                date = dateFormats[i].parse(dt);

                break;
            }
            catch (ParseException exp)
            {
                //
            }
        }

        if (date == null)
        {
            throw new IllegalArgumentException("Unable to parse " + dt);
        }

        GregorianCalendar out = new GregorianCalendar();
        out.setTime(date);

        return out;

    }


}
