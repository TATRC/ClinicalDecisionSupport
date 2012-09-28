
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperationalConstraintElementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationalConstraintElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OC_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OCE_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OCE_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OCE_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OCE_Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OCE_Interpretation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OCE_AttributeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OCE_Comparator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OCE_AttributeValues" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OCE_UnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OperationalConstraint" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}OperationalConstraintType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationalConstraintElementType", propOrder = {
    "ocid",
    "oceid",
    "oceName",
    "oceType",
    "oceDescription",
    "oceInterpretation",
    "oceAttributeName",
    "oceComparator",
    "oceAttributeValues",
    "oceUnitOfMeasure",
    "operationalConstraint"
})
public class OperationalConstraintElementType {

    @XmlElement(name = "OC_ID")
    protected int ocid;
    @XmlElement(name = "OCE_ID")
    protected int oceid;
    @XmlElement(name = "OCE_Name", required = true)
    protected String oceName;
    @XmlElement(name = "OCE_Type", required = true)
    protected String oceType;
    @XmlElement(name = "OCE_Description", required = true)
    protected String oceDescription;
    @XmlElement(name = "OCE_Interpretation", required = true)
    protected String oceInterpretation;
    @XmlElement(name = "OCE_AttributeName", required = true)
    protected String oceAttributeName;
    @XmlElement(name = "OCE_Comparator", required = true)
    protected String oceComparator;
    @XmlElement(name = "OCE_AttributeValues", required = true)
    protected String oceAttributeValues;
    @XmlElement(name = "OCE_UnitOfMeasure", required = true)
    protected String oceUnitOfMeasure;
    @XmlElement(name = "OperationalConstraint", required = true)
    protected OperationalConstraintType operationalConstraint;

    /**
     * Gets the value of the ocid property.
     * 
     */
    public int getOCID() {
        return ocid;
    }

    /**
     * Sets the value of the ocid property.
     * 
     */
    public void setOCID(int value) {
        this.ocid = value;
    }

    /**
     * Gets the value of the oceid property.
     * 
     */
    public int getOCEID() {
        return oceid;
    }

    /**
     * Sets the value of the oceid property.
     * 
     */
    public void setOCEID(int value) {
        this.oceid = value;
    }

    /**
     * Gets the value of the oceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEName() {
        return oceName;
    }

    /**
     * Sets the value of the oceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEName(String value) {
        this.oceName = value;
    }

    /**
     * Gets the value of the oceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEType() {
        return oceType;
    }

    /**
     * Sets the value of the oceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEType(String value) {
        this.oceType = value;
    }

    /**
     * Gets the value of the oceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEDescription() {
        return oceDescription;
    }

    /**
     * Sets the value of the oceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEDescription(String value) {
        this.oceDescription = value;
    }

    /**
     * Gets the value of the oceInterpretation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEInterpretation() {
        return oceInterpretation;
    }

    /**
     * Sets the value of the oceInterpretation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEInterpretation(String value) {
        this.oceInterpretation = value;
    }

    /**
     * Gets the value of the oceAttributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEAttributeName() {
        return oceAttributeName;
    }

    /**
     * Sets the value of the oceAttributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEAttributeName(String value) {
        this.oceAttributeName = value;
    }

    /**
     * Gets the value of the oceComparator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEComparator() {
        return oceComparator;
    }

    /**
     * Sets the value of the oceComparator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEComparator(String value) {
        this.oceComparator = value;
    }

    /**
     * Gets the value of the oceAttributeValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEAttributeValues() {
        return oceAttributeValues;
    }

    /**
     * Sets the value of the oceAttributeValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEAttributeValues(String value) {
        this.oceAttributeValues = value;
    }

    /**
     * Gets the value of the oceUnitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCEUnitOfMeasure() {
        return oceUnitOfMeasure;
    }

    /**
     * Sets the value of the oceUnitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCEUnitOfMeasure(String value) {
        this.oceUnitOfMeasure = value;
    }

    /**
     * Gets the value of the operationalConstraint property.
     * 
     * @return
     *     possible object is
     *     {@link OperationalConstraintType }
     *     
     */
    public OperationalConstraintType getOperationalConstraint() {
        return operationalConstraint;
    }

    /**
     * Sets the value of the operationalConstraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationalConstraintType }
     *     
     */
    public void setOperationalConstraint(OperationalConstraintType value) {
        this.operationalConstraint = value;
    }

}
