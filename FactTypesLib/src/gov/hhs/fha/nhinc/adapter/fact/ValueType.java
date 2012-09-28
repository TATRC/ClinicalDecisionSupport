package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ValueType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ValueType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeSystemPair">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="floatValue" type="{http://www.w3.org/2001/XMLSchema}float"/>*
 *         &lt;element name="displayable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueType", propOrder = {
   "value",
   "floatValue",
   "displayable"
})
public class ValueType
        extends CodeSystemPair
        implements Serializable {

   @XmlElement(name = "value", required = true)
   protected String value;
   @XmlElement(name = "floatValue", required = true)
   protected float floatValue;
   @XmlElement(name = "displayable")
   protected Boolean displayable;

   public ValueType() {
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
    * Gets the value of the floatValue property.
    *
    */
   public float getFloatValue() {
      return floatValue;
   }

   /**
    * Sets the value of the floatValue property.
    *
    */
   public void setFloatValue(float value) {
      this.floatValue = value;
   }

   public Boolean getDisplayable() {
      return displayable;
   }

   public void setDisplayable(Boolean displayable) {
      this.displayable = displayable;
   }
   //---------------------------------------------------------------------------
   // Add custom codes here
   //---------------------------------------------------------------------------
   private static String nonDigitsPattern = "[^0-9.]";

   public ValueType(String value, Boolean displayable) {
      super();
      setValue(value);
      this.displayable = displayable;
   }

   public ValueType(String value, Boolean displayable, String codeSystem, String codeSystemName) {
      super(codeSystem, codeSystemName);
      setValue(value);
      this.displayable = displayable;
   }

   private boolean isNonDigits(String str) {
      Pattern p = Pattern.compile(nonDigitsPattern);
      Matcher m = p.matcher(str);
      return m.find();
   }

   @Override
   public String toString() {
      return "ValueType(" + value + "|" + floatValue + "|" + displayable + "|" + super.toString() + ")";
      //return "IdType(" + value + ")";
   }
}
