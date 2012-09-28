package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for OrderFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fills" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="reFills" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="quantityOrdered" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="orderExpirationDateTime" type="{http://www.w3.org/2001/XMLSchema}datetime" minOccurs="0"/>
 *         &lt;element name="orderDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderingProvider" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="fulfillmentInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderFactType", propOrder = {
  "orderNumber",
  "fills",
  "reFills",
  "quantityOrdered",
  "orderExpirationDateTime",
  "orderDateTime",
  "orderingProvider",
  "fulfillmentInstructions"
})
public class OrderFactType implements Serializable {

  @XmlElement(name = "orderNumber", required = true)
  protected String orderNumber;
  @XmlElement(name = "fills", required = true)
  protected Integer fills = 0;
  @XmlElement(name = "reFills")
  protected Integer reFills;
  @XmlElement(name = "quantityOrdered", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair quantityOrdered;
  @XmlElement(name = "orderExpirationDateTime", type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date orderExpirationDateTime;
  @XmlElement(name = "orderDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date orderDateTime;
  @XmlElement(name = "orderingProvider", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType orderingProvider;
  @XmlElement(name = "fulfillmentInstructions")
  protected String fulfillmentInstructions;

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
   *     {@link Date }
   *
   */
  public Date getOrderExpirationDateTime() {
    return orderExpirationDateTime;
  }

  /**
   * Sets the value of the orderExpirationDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setOrderExpirationDateTime(Date value) {
    this.orderExpirationDateTime = value;
  }

  /**
   * Gets the value of the orderDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getOrderDateTime() {
    return orderDateTime;
  }

  /**
   * Sets the value of the orderDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setOrderDateTime(Date value) {
    this.orderDateTime = value;
  }

  /**
   * Gets the value of the orderingProvider property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getOrderingProvider() {
    return orderingProvider;
  }

  /**
   * Sets the value of the orderingProvider property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setOrderingProvider(NameFactType value) {
    this.orderingProvider = value;
  }

  /**
   * Gets the value of the fulfillmentInstructions property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFulfillmentInstructions() {
    return fulfillmentInstructions;
  }

  /**
   * Sets the value of the fulfillmentInstructions property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFulfillmentInstructions(String value) {
    this.fulfillmentInstructions = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("OrderFactType(orderNumber=" + orderNumber + ",fills=" + fills);
    str.append(",reFills=" + reFills + ",quantityOrdered=" + quantityOrdered);
    str.append(",orderExpirationDateTime=" + orderExpirationDateTime + ",orderDateTime=" + orderDateTime);
    str.append(",orderingProvider=" + (orderingProvider != null ? orderingProvider : ""));
    str.append(",fulfillmentInstructions=" + fulfillmentInstructions + ")");

    return str.toString();
  }
}
