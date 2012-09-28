
package org.medsphere.util.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DUZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DivisionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceSection" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInfo", propOrder = {
    "duz",
    "userName",
    "userFullName",
    "divisionID",
    "stationName",
    "stationNumber",
    "title",
    "serviceSection",
    "language",
    "dTime",
    "errorMessage"
})
public class UserInfo {

    @XmlElement(name = "DUZ")
    protected String duz;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "UserFullName")
    protected String userFullName;
    @XmlElement(name = "DivisionID")
    protected String divisionID;
    @XmlElement(name = "StationName")
    protected String stationName;
    @XmlElement(name = "StationNumber")
    protected String stationNumber;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "ServiceSection")
    protected String serviceSection;
    @XmlElement(name = "Language")
    protected String language;
    @XmlElement(name = "DTime")
    protected String dTime;
    @XmlElement(name = "ErrorMessage")
    protected String errorMessage;

    /**
     * Gets the value of the duz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDUZ() {
        return duz;
    }

    /**
     * Sets the value of the duz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDUZ(String value) {
        this.duz = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the userFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFullName() {
        return userFullName;
    }

    /**
     * Sets the value of the userFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFullName(String value) {
        this.userFullName = value;
    }

    /**
     * Gets the value of the divisionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivisionID() {
        return divisionID;
    }

    /**
     * Sets the value of the divisionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivisionID(String value) {
        this.divisionID = value;
    }

    /**
     * Gets the value of the stationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Sets the value of the stationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationName(String value) {
        this.stationName = value;
    }

    /**
     * Gets the value of the stationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationNumber() {
        return stationNumber;
    }

    /**
     * Sets the value of the stationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationNumber(String value) {
        this.stationNumber = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the serviceSection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceSection() {
        return serviceSection;
    }

    /**
     * Sets the value of the serviceSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceSection(String value) {
        this.serviceSection = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the dTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDTime() {
        return dTime;
    }

    /**
     * Sets the value of the dTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDTime(String value) {
        this.dTime = value;
    }

    /**
     * Gets the value of the errorMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

}
