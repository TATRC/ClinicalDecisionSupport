
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpecialtyListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpecialtyListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KM_Specialty" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KM_SpecialtyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecialtyListType", propOrder = {
    "kmSpecialty"
})
public class SpecialtyListType {

    @XmlElement(name = "KM_Specialty")
    protected List<KMSpecialtyType> kmSpecialty;

    /**
     * Gets the value of the kmSpecialty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kmSpecialty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKMSpecialty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KMSpecialtyType }
     * 
     * 
     */
    public List<KMSpecialtyType> getKMSpecialty() {
        if (kmSpecialty == null) {
            kmSpecialty = new ArrayList<KMSpecialtyType>();
        }
        return this.kmSpecialty;
    }

}
