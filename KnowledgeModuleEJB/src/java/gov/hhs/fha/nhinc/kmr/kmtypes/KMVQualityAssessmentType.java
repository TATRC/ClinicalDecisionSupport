
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_QualityAssessmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_QualityAssessmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KM_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="KM_VersionNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_QualityAssessmentType", propOrder = {
    "kmid",
    "kmVersionNum"
})
public class KMVQualityAssessmentType {

    @XmlElement(name = "KM_ID")
    protected int kmid;
    @XmlElement(name = "KM_VersionNum")
    protected int kmVersionNum;

    /**
     * Gets the value of the kmid property.
     * 
     */
    public int getKMID() {
        return kmid;
    }

    /**
     * Sets the value of the kmid property.
     * 
     */
    public void setKMID(int value) {
        this.kmid = value;
    }

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

}
