
package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fills" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="reFills" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="quantityOrdered" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="orderExpirationDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderingProvider" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PersonFactType" minOccurs="0"/>
 *         &lt;element name="fullfillmentInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderInfoType", propOrder = {
    "orderNumber",
    "fills",
    "reFills",
    "quantityOrdered",
    "orderExpirationDateTime",
    "orderDateTime",
    "orderingProvider",
    "fullfillmentInstructions"
})
public class OrderInfoType implements Serializable {

    @XmlElement(required = true)
    protected String orderNumber;
    protected Integer fills;
    protected Integer reFills;
    @XmlElement(required = true)
    protected ValueUnitPair quantityOrdered;
    protected String orderExpirationDateTime;
    protected String orderDateTime;
    protected PersonFactType orderingProvider;
    protected String fullfillmentInstructions;

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the fills property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFills() {
        return fills;
    }

    /**
     * Sets the value of the fills property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFills(Integer value) {
        this.fills = value;
    }

    /**
     * Gets the value of the reFills property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReFills() {
        return reFills;
    }

    /**
     * Sets the value of the reFills property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReFills(Integer value) {
        this.reFills = value;
    }

    /**
     * Gets the value of the quantityOrdered property.
     * 
     * @return
     *     possible object is
     *     {@link ValueUnitPair }
     *     
     */
    public ValueUnitPair getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * Sets the value of the quantityOrdered property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueUnitPair }
     *     
     */
    public void setQuantityOrdered(ValueUnitPair value) {
        this.quantityOrdered = value;
    }

    /**
     * Gets the value of the orderExpirationDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderExpirationDateTime() {
        return orderExpirationDateTime;
    }

    /**
     * Sets the value of the orderExpirationDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderExpirationDateTime(String value) {
        this.orderExpirationDateTime = value;
    }

    /**
     * Gets the value of the orderDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderDateTime() {
        return orderDateTime;
    }

    /**
     * Sets the value of the orderDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDateTime(String value) {
        this.orderDateTime = value;
    }

    /**
     * Gets the value of the orderingProvider property.
     * 
     * @return
     *     possible object is
     *     {@link PersonFactType }
     *     
     */
    public PersonFactType getOrderingProvider() {
        return orderingProvider;
    }

    /**
     * Sets the value of the orderingProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonFactType }
     *     
     */
    public void setOrderingProvider(PersonFactType value) {
        this.orderingProvider = value;
    }

    /**
     * Gets the value of the fullfillmentInstructions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullfillmentInstructions() {
        return fullfillmentInstructions;
    }

    /**
     * Sets the value of the fullfillmentInstructions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullfillmentInstructions(String value) {
        this.fullfillmentInstructions = value;
    }

}
