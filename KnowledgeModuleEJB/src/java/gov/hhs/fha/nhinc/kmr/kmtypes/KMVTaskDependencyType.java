
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_TaskDependencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_TaskDependencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TD_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TT_Specification" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}TT_SpecificationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_TaskDependencyType", propOrder = {
    "tdType",
    "ttSpecification"
})
public class KMVTaskDependencyType {

    @XmlElement(name = "TD_Type", required = true)
    protected String tdType;
    @XmlElement(name = "TT_Specification")
    protected List<TTSpecificationType> ttSpecification;

    /**
     * Gets the value of the tdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTDType() {
        return tdType;
    }

    /**
     * Sets the value of the tdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTDType(String value) {
        this.tdType = value;
    }

    /**
     * Gets the value of the ttSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ttSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTTSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTSpecificationType }
     * 
     * 
     */
    public List<TTSpecificationType> getTTSpecification() {
        if (ttSpecification == null) {
            ttSpecification = new ArrayList<TTSpecificationType>();
        }
        return this.ttSpecification;
    }

}
