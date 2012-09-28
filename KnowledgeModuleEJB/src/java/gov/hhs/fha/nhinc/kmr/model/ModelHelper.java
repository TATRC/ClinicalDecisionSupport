package gov.hhs.fha.nhinc.kmr.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ModelHelper {

    public ModelHelper() {
    }

    public XMLGregorianCalendar Date2XMLDate(Date d)
            throws DatatypeConfigurationException {

        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        DatatypeFactory df = DatatypeFactory.newInstance();

        XMLGregorianCalendar x =
                df.newXMLGregorianCalendar(year,
                                               month,
                                               day,
                                               c.get(Calendar.HOUR),
                                               c.get(Calendar.MINUTE),
                                               c.get(Calendar.SECOND),
                                               c.get(Calendar.MILLISECOND),
                                               c.getTimeZone().getOffset(d.getTime()) / (60 * 60 * 1000));

        //x.toGregorianCalendar().setTime(d);
        return x;
    }
}

