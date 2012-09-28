
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_PopulationDependencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_PopulationDependencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PD_Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PD_Scope" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PD_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PD_Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PopulationSpecification" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}PopulationSpecificationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_PopulationDependencyType", propOrder = {
    "pdStatus",
    "pdScope",
    "pdName",
    "pdDescription",
    "populationSpecification"
})
public class KMVPopulationDependencyType {

    @XmlElement(name = "PD_Status", required = true)
    protected String pdStatus;
    @XmlElement(name = "PD_Scope", required = true)
    protected String pdScope;
    @XmlElement(name = "PD_Name", required = true)
    protected String pdName;
    @XmlElement(name = "PD_Description", required = true)
    protected String pdDescription;
    @XmlElement(name = "PopulationSpecification")
    protected List<PopulationSpecificationType> populationSpecification;

    /**
     * Gets the value of the pdStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDStatus() {
        return pdStatus;
    }

    /**
     * Sets the value of the pdStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDStatus(String value) {
        this.pdStatus = value;
    }

    /**
     * Gets the value of the pdScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDScope() {
        return pdScope;
    }

    /**
     * Sets the value of the pdScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDScope(String value) {
        this.pdScope = value;
    }

    /**
     * Gets the value of the pdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDName() {
        return pdName;
    }

    /**
     * Sets the value of the pdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDName(String value) {
        this.pdName = value;
    }

    /**
     * Gets the value of the pdDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDDescription() {
        return pdDescription;
    }

    /**
     * Sets the value of the pdDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDDescription(String value) {
        this.pdDescription = value;
    }

    /**
     * Gets the value of the populationSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the populationSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPopulationSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PopulationSpecificationType }
     * 
     * 
     */
    public List<PopulationSpecificationType> getPopulationSpecification() {
        if (populationSpecification == null) {
            populationSpecification = new ArrayList<PopulationSpecificationType>();
        }
        return this.populationSpecification;
    }

}
