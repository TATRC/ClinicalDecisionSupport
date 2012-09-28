
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PopulationDependencyListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PopulationDependencyListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KMV_PopulationDependency" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_PopulationDependencyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PopulationDependencyListType", propOrder = {
    "kmvPopulationDependency"
})
public class PopulationDependencyListType {

    @XmlElement(name = "KMV_PopulationDependency")
    protected List<KMVPopulationDependencyType> kmvPopulationDependency;

    /**
     * Gets the value of the kmvPopulationDependency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kmvPopulationDependency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKMVPopulationDependency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KMVPopulationDependencyType }
     * 
     * 
     */
    public List<KMVPopulationDependencyType> getKMVPopulationDependency() {
        if (kmvPopulationDependency == null) {
            kmvPopulationDependency = new ArrayList<KMVPopulationDependencyType>();
        }
        return this.kmvPopulationDependency;
    }

}
