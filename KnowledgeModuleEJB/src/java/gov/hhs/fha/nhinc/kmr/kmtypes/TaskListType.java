
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TaskListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaskListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KMV_TaskDependency" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_TaskDependencyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaskListType", propOrder = {
    "kmvTaskDependency"
})
public class TaskListType {

    @XmlElement(name = "KMV_TaskDependency")
    protected List<KMVTaskDependencyType> kmvTaskDependency;

    /**
     * Gets the value of the kmvTaskDependency property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kmvTaskDependency property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKMVTaskDependency().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KMVTaskDependencyType }
     * 
     * 
     */
    public List<KMVTaskDependencyType> getKMVTaskDependency() {
        if (kmvTaskDependency == null) {
            kmvTaskDependency = new ArrayList<KMVTaskDependencyType>();
        }
        return this.kmvTaskDependency;
    }

}
