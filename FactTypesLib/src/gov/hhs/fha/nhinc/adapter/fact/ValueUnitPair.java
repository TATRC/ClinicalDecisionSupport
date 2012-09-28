package gov.hhs.fha.nhinc.adapter.fact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ValueUnitPair complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ValueUnitPair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="floatValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueUnitPair", propOrder = {
   "value",
   "floatValue",
   "unit"
})
public class ValueUnitPair {

   @XmlElement(name = "value", required = true)
   protected String value;
   @XmlElement(name = "floatValue", required = true)
   protected Float floatValue;
   @XmlElement(name = "unit", required = true)
   protected String unit;

   public ValueUnitPair() {
   }

   public ValueUnitPair(String value, String unit) {
      setValue(value);
      this.unit = unit;
   }

   /**
    * Gets the value of the value property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getValue() {
      return value;
   }

   /**
    * Sets the value of the value property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setValue(String value) {
      this.value = value;
      System.out.println("isNonDigits(" + value + ") = " + isNonDigits(value));    
      if (value != null && !value.isEmpty() && !isNonDigits(value)) {
         floatValue = Float.parseFloat(value);
      }
   }


   /**
    * Gets the value of the value property.
    *
    * @return
    *     possible object is
    *     {@link Float }
    *
    */
   public Float getFloatValue() {
      return floatValue;
   }

   /**
    * Sets the value of the value property.
    *
    * @param value
    *     allowed object is
    *     {@link Float }
    *
    */
   public void setValue(Float value) {
      this.floatValue = value;
   }

   /**
    * Gets the value of the unit property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getUnit() {
      return unit;
   }

   /**
    * Sets the value of the unit property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setUnit(String value) {
      this.unit = value;
   }

   //---------------------------------------------------------------------------
   // Add custom codes here
   //---------------------------------------------------------------------------
   private static String nonDigitsPattern = "[^0-9.]";
   
   private boolean isNonDigits(String str) {
      Pattern p = Pattern.compile(nonDigitsPattern);
      Matcher m = p.matcher(str);
      return m.find();
   }

   @Override
   public String toString() {
      return "ValueUnitPair(" + value + "|" + floatValue + "|" + unit + ")";
   }
}
