package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for MedOrderFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MedOrderFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}GenericOrderFactType">
 *       &lt;sequence>
 *         &lt;element name="freeTextProductName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codedProductName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="sigFreeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedSigRoute" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="sigDose" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair" minOccurs="0"/>
 *         &lt;element name="fills" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="quantityOrdered" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedOrderFactType", propOrder = {
  "freeTextProductName",
  "codedProductName",
  "sigFreeText",
  "codedSigRoute",
  "sigDose",
  "fills",
  "quantityOrdered"
})
public class MedOrderFactType
        extends GenericOrderFactType
        implements Serializable
{

  @XmlElement(name = "fills", required = true)
  protected Integer fills = 0;
  @XmlElement(name = "quantityOrdered", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair quantityOrdered;
  @XmlElement(name = "freeTextProductName", required = true)
  protected String freeTextProductName;
  @XmlElement(name = "codedProductName", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedProductName;
  @XmlElement(name = "sigFreeText")
  protected String sigFreeText;
  @XmlElement(name = "codedSigRoute", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedSigRoute;
  @XmlElement(name = "sigDose", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair sigDose;
  

  /**
   * Gets the value of the freeTextProductName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextProductName() {
    return freeTextProductName;
  }

  /**
   * Sets the value of the freeTextProductName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextProductName(String value) {
    this.freeTextProductName = value;
  }

  /**
   * Gets the value of the codedProductName property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedProductName() {
    return codedProductName;
  }

  /**
   * Sets the value of the codedProductName property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedProductName(CodeLabelPair value) {
    this.codedProductName = value;
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
   * Gets the value of the sigFreeText property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSigFreeText() {
    return sigFreeText;
  }

  /**
   * Sets the value of the sigFreeText property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSigFreeText(String value) {
    this.sigFreeText = value;
  }

  /**
   * Gets the value of the codedSigRoute property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedSigRoute() {
    return codedSigRoute;
  }

  /**
   * Sets the value of the codedSigRoute property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedSigRoute(CodeLabelPair value) {
    this.codedSigRoute = value;
  }

  /**
   * Gets the value of the sigDose property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getSigDose() {
    return sigDose;
  }

  /**
   * Sets the value of the sigDose property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setSigDose(ValueUnitPair value) {
    this.sigDose = value;
  }

  
  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("MedOrderFactType(");
    str.append(super.toString());
    str.append(System.getProperty("line.separator"));
    str.append("  fills=" + fills + System.getProperty("line.separator"));
    str.append("  quantityOrdered=" + quantityOrdered + System.getProperty("line.separator"));
    str.append("  freeTextProductName=" + freeTextProductName + System.getProperty("line.separator"));
    str.append("  codedProductName=" + codedProductName + System.getProperty("line.separator"));
    str.append("  sigFreeText=" + sigFreeText + System.getProperty("line.separator"));
    str.append("  codedSigRoute=" + codedSigRoute + System.getProperty("line.separator"));
    str.append("  sigDose=" + sigDose + System.getProperty("line.separator"));
    str.append(")");

    return str.toString();
  }
}
