
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_UsageStatisticType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_UsageStatisticType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KM_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="KM_VersionNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UST_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UST_Value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_UsageStatisticType", propOrder = {
    "kmid",
    "kmVersionNum",
    "ustid",
    "ustValue"
})
public class KMVUsageStatisticType {

    @XmlElement(name = "KM_ID")
    protected int kmid;
    @XmlElement(name = "KM_VersionNum")
    protected int kmVersionNum;
    @XmlElement(name = "UST_ID")
    protected int ustid;
    @XmlElement(name = "UST_Value", required = true)
    protected String ustValue;

    /**
     * Gets the value of the kmid property.
     * 
     */
    public int getKMID() {
        return kmid;
    }

    /**
     * Sets the value of the kmid property.
     * 
     */
    public void setKMID(int value) {
        this.kmid = value;
    }

    /**
     * Gets the value of the kmVersionNum property.
     * 
     */
    public int getKMVersionNum() {
        return kmVersionNum;
    }

    /**
     * Sets the value of the kmVersionNum property.
     * 
     */
    public void setKMVersionNum(int value) {
        this.kmVersionNum = value;
    }

    /**
     * Gets the value of the ustid property.
     * 
     */
    public int getUSTID() {
        return ustid;
    }

    /**
     * Sets the value of the ustid property.
     * 
     */
    public void setUSTID(int value) {
        this.ustid = value;
    }

    /**
     * Gets the value of the ustValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSTValue() {
        return ustValue;
    }

    /**
     * Sets the value of the ustValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSTValue(String value) {
        this.ustValue = value;
    }

}
