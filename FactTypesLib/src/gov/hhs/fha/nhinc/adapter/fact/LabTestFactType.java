package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;

public class LabTestFactType
        extends FactType
        implements Serializable
{

  protected ValueType patientId;
  protected String specimenDate;
  protected String freeTextSpecimen;
  protected CodeLabelPair codedBattery;
  protected String freeTextBattery;

  /**
   * Sets the value of the patientId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setPatientId(ValueType value) {
    this.patientId = value;
  }

  /**
   * Gets the value of the freeTextProduct property.
   *
   * @return
   *     possible object is
   *     {@link ValueType }
   *
   */
  public ValueType getPatientId() {
    return patientId;
  }

  /**
   * Gets the value of the specimenDate property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSpecimenDate() {
    return specimenDate;
  }

  /**
   * Sets the value of the specimenDate property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSpecimenDate(String value) {
    this.specimenDate = value;
  }

  /**
   * Gets the value of the freeTextSpecimen property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextSpecimen() {
    return freeTextSpecimen;
  }

  /**
   * Sets the value of the freeTextSpecimen property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextSpecimen(String value) {
    this.freeTextSpecimen = value;
  }

  /**
   * Gets the value of the codedPanel property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedBattery() {
    return codedBattery;
  }

  /**
   * Sets the value of the codedBattery property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedBattery(CodeLabelPair value) {
    this.codedBattery = value;
  }

  /**
   * Gets the value of the freeTextBattery property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextBattery() {
    return freeTextBattery;
  }

  /**
   * Sets the value of the freeTextBattery property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextBattery(String value) {
    this.freeTextBattery = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("LabTestFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  specimentDate=" + specimenDate + System.getProperty("line.separator"));
    str.append("  freeTextSpecimen=" + freeTextSpecimen + System.getProperty("line.separator"));
    str.append("  codedBattery=" + codedBattery + System.getProperty("line.separator"));
    str.append("  freeTextBattery=" + freeTextBattery);
    str.append("]");

    return str.toString();
  }
}
