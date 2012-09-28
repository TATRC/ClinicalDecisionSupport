package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for StatusFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="StatusFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusConceptCode" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedStatus" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusFactType", propOrder = {
  "statusConceptCode",
  "codedStatus"
})
public class StatusFactType implements Serializable {

  @XmlElement(name = "statusConceptCode", required= true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair statusConceptCode;
  @XmlElement(name = "codedStatus", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedStatus;

  /**
   * Gets the value of the statusConceptCode property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getStatusConceptCode() {
    return statusConceptCode;
  }

  /**
   * Sets the value of the statusConceptCode property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setStatusConceptCode(CodeLabelPair value) {
    this.statusConceptCode = value;
  }

  /**
   * Gets the value of the codedStatus property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedStatus() {
    return codedStatus;
  }

  /**
   * Sets the value of the codedStatus property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedStatus(CodeLabelPair value) {
    this.codedStatus = value;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("StatusFactType(statusConceptCode=" + (statusConceptCode != null ? statusConceptCode : ""));
    str.append(",codedStatus=" + (codedStatus != null ? codedStatus : "") + ")");

    return str.toString();
  }
}
