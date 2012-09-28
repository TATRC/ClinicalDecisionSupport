package gov.hhs.fha.nhinc.adapter.fact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RecordOrderResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecordOrderResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="placerOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecordOrderResponseType", propOrder = {
  "queryId",
  "placerOrderNumber"
})
public class RecordOrderResponseType {

  @XmlElement(required = true)
  protected String queryId;
  @XmlElement(required = true)
  protected String placerOrderNumber;

  /**
   * Gets the value of the queryId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getQueryId() {
    return queryId;
  }

  /**
   * Sets the value of the queryId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setQueryId(String value) {
    this.queryId = value;
  }

  /**
   * Gets the value of the placerOrderNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPlacerOrderNumber() {
    return placerOrderNumber;
  }

  /**
   * Sets the value of the placerOrderNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPlacerOrderNumber(String value) {
    this.placerOrderNumber = value;
  }
}
