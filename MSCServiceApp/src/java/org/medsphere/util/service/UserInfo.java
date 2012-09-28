package org.medsphere.util.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author apardue
 */
@XmlType(name="UserInfo")
public class UserInfo {

    @XmlElement(name="DUZ")
    public String duz;

    @XmlElement(name="UserName")
    public String userName;

    @XmlElement(name="UserFullName")
    public String userFullName;

    @XmlElement(name="DivisionID")
    public String divisionID;

    @XmlElement(name="StationName")
    public String stationName;

    @XmlElement(name="StationNumber")
    public String stationNumber;

    @XmlElement(name="Title")
    public String title;

    @XmlElement(name="ServiceSection")
    public String serviceSection;

    @XmlElement(name="Language")
    public String language;

    @XmlElement(name="DTime")
    public String dTime;

    @XmlElement(name="ErrorMessage")
    public String errorMessage;

    @Override
    public String toString() {
        return
                "duz=["+duz+"]"
                + " userName=["+userName+"]"
                + " userFullName=["+userFullName+"]"
                + " divisionID=["+divisionID+"]"
                + " stationName=["+stationName+"]"
                + " stationNumber=["+stationNumber+"]"
                + " title=["+title+"]"
                + " serviceSection=["+serviceSection+"]"
                + " language=["+language+"]"
                + " dTime=["+dTime+"]"
                + " errorMessage=["+errorMessage+"]"
                ;
    }
}
