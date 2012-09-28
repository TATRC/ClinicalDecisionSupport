
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UsageStatisticTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsageStatisticTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UST_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UST_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UST_Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UST_UnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsageStatisticTypeType", propOrder = {
    "ustid",
    "ustName",
    "ustDescription",
    "ustUnitOfMeasure"
})
public class UsageStatisticTypeType {

    @XmlElement(name = "UST_ID")
    protected int ustid;
    @XmlElement(name = "UST_Name", required = true)
    protected String ustName;
    @XmlElement(name = "UST_Description", required = true)
    protected String ustDescription;
    @XmlElement(name = "UST_UnitOfMeasure", required = true)
    protected String ustUnitOfMeasure;

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
     * Gets the value of the ustName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSTName() {
        return ustName;
    }

    /**
     * Sets the value of the ustName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSTName(String value) {
        this.ustName = value;
    }

    /**
     * Gets the value of the ustDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSTDescription() {
        return ustDescription;
    }

    /**
     * Sets the value of the ustDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSTDescription(String value) {
        this.ustDescription = value;
    }

    /**
     * Gets the value of the ustUnitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSTUnitOfMeasure() {
        return ustUnitOfMeasure;
    }

    /**
     * Sets the value of the ustUnitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSTUnitOfMeasure(String value) {
        this.ustUnitOfMeasure = value;
    }

}
