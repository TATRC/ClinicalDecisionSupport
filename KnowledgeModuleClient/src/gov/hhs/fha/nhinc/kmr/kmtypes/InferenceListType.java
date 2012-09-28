
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InferenceListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InferenceListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KMV_InferenceEngineDependency" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_InferenceEngineDependencyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InferenceListType", propOrder = {
    "kmvInferenceEngineDependency"
})
public class InferenceListType {

    @XmlElement(name = "KMV_InferenceEngineDependency")
    protected List<KMVInferenceEngineDependencyType> kmvInferenceEngineDependency;

    /**
     * Gets the value of the kmvInferenceEngineDependency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kmvInferenceEngineDependency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKMVInferenceEngineDependency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KMVInferenceEngineDependencyType }
     * 
     * 
     */
    public List<KMVInferenceEngineDependencyType> getKMVInferenceEngineDependency() {
        if (kmvInferenceEngineDependency == null) {
            kmvInferenceEngineDependency = new ArrayList<KMVInferenceEngineDependencyType>();
        }
        return this.kmvInferenceEngineDependency;
    }

}
