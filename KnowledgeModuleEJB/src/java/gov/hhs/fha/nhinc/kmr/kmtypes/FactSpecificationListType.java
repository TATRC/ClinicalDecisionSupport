
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FactSpecificationListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FactSpecificationListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KMV_FactDependency" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_FactDependencyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactSpecificationListType", propOrder = {
    "kmvFactDependency"
})
public class FactSpecificationListType {

    @XmlElement(name = "KMV_FactDependency")
    protected List<KMVFactDependencyType> kmvFactDependency;

    /**
     * Gets the value of the kmvFactDependency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kmvFactDependency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKMVFactDependency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KMVFactDependencyType }
     * 
     * 
     */
    public List<KMVFactDependencyType> getKMVFactDependency() {
        if (kmvFactDependency == null) {
            kmvFactDependency = new ArrayList<KMVFactDependencyType>();
        }
        return this.kmvFactDependency;
    }

}
