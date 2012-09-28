/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.properties;

import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Jerry Goodnough
 */
public class DateProperty {

    private DateParse dtParse;
    private static DateFormat cdaDateFormat = null;

    static
    {
        cdaDateFormat = new SimpleDateFormat("yyyyMMddHHmmssZ"); // YYYYMMDDHHmmss-0000
    }

    public boolean isUnbounded()
    {
        return dtParse.getType()==DateParse.Type.UNBOUNDED;
    }

    private DateProperty(DateParse dtParse)
    {
        this.dtParse = dtParse;
    }
    static public DateProperty getDatePropertyFromParse(DateParse dtParse)
    {
        DateProperty out = null;

        if (dtParse.getType() != DateParse.Type.ERROR)
        {
            out = new DateProperty(dtParse);
        }

        return out;
    }

    public GregorianCalendar getCalendar()
    {
        return dtParse.getCalendar();
    }

    public XMLGregorianCalendar getXMLGregorianCalendar() throws Exception
    {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(dtParse.getCalendar());
    }

    public String getCDATime()
    {
        return cdaDateFormat.format(dtParse.getCalendar().getTime());
    }
}
