
package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FulfillmentFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FulfillmentFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="prescriptionNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dispensingPharmacy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dispensingPharmacyLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispenseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="quantityDispensed" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="fillNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fillStatus" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FulfillmentFactType", propOrder = {
    "prescriptionNumber",
    "dispensingPharmacy",
    "dispensingPharmacyLocation",
    "dispenseDate",
    "quantityDispensed",
    "fillNumber",
    "fillStatus"
})
public class FulfillmentFactType implements Serializable {

    @XmlElement(name = "prescriptionNumber", required = true)
    protected String prescriptionNumber;
    @XmlElement(name = "dispensingPharmacy")
    protected String dispensingPharmacy;
    @XmlElement(name = "dispensingPharmacyLocation")
    protected String dispensingPharmacyLocation;
    @XmlElement(name = "dispenseDate", type = Date.class, required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date dispenseDate;
    @XmlElement(name = "quantityDispensed", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
    protected ValueUnitPair quantityDispensed;
    @XmlElement(name = "fillNumber", required = true)
    protected Integer fillNumber;
    @XmlElement(name = "fillStatus", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
    protected CodeLabelPair fillStatus;

    /**
     * Gets the value of the prescriptionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    /**
     * Sets the value of the prescriptionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionNumber(String value) {
        this.prescriptionNumber = value;
    }

    /**
     * Gets the value of the dispensingPharmacy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensingPharmacy() {
        return dispensingPharmacy;
    }

    /**
     * Sets the value of the dispensingPharmacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensingPharmacy(String value) {
        this.dispensingPharmacy = value;
    }

    /**
     * Gets the value of the dispensingPharmacyLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensingPharmacyLocation() {
        return dispensingPharmacyLocation;
    }

    /**
     * Sets the value of the dispensingPharmacyLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensingPharmacyLocation(String value) {
        this.dispensingPharmacyLocation = value;
    }

    /**
     * Gets the value of the dispenseDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDispenseDate() {
        return dispenseDate;
    }

    /**
     * Sets the value of the dispenseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDispenseDate(Date value) {
        this.dispenseDate = value;
    }

    /**
     * Gets the value of the quantityDispensed property.
     * 
     * @return
     *     possible object is
     *     {@link ValueUnitPair }
     *     
     */
    public ValueUnitPair getQuantityDispensed() {
        return quantityDispensed;
    }

    /**
     * Sets the value of the quantityDispensed property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueUnitPair }
     *     
     */
    public void setQuantityDispensed(ValueUnitPair value) {
        this.quantityDispensed = value;
    }

    /**
     * Gets the value of the fillNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFillNumber() {
        return fillNumber;
    }

    /**
     * Sets the value of the fillNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFillNumber(Integer value) {
        this.fillNumber = value;
    }

    /**
     * Gets the value of the fillStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CodeLabelPair }
     *     
     */
    public CodeLabelPair getFillStatus() {
        return fillStatus;
    }

    /**
     * Sets the value of the fillStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeLabelPair }
     *     
     */
    public void setFillStatus(CodeLabelPair value) {
        this.fillStatus = value;
    }

}
