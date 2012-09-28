
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_OperationalConstraintDependencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_OperationalConstraintDependencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KM_VersionNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OC_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_OperationalConstraintDependencyType", propOrder = {
    "kmVersionNum",
    "ocid"
})
public class KMVOperationalConstraintDependencyType {

    @XmlElement(name = "KM_VersionNum")
    protected int kmVersionNum;
    @XmlElement(name = "OC_ID")
    protected int ocid;

    /**
     * Gets the value of the kmVersionNum property.
     * 
     */
    public int getKMVersionNum() {
        return kmVersionNum;
    }

    /**
     * Sets the value of the kmVersionNum property.
     * 
     */
    public void setKMVersionNum(int value) {
        this.kmVersionNum = value;
    }

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

}
