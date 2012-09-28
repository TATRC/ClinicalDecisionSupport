
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceData.RefDataListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceData.RefDataListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="refData" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ReferenceData.RefDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceData.RefDataListType", propOrder = {
    "refData"
})
public class ReferenceDataRefDataListType {

    protected List<ReferenceDataRefDataType> refData;

    /**
     * Gets the value of the refData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceDataRefDataType }
     * 
     * 
     */
    public List<ReferenceDataRefDataType> getRefData() {
        if (refData == null) {
            refData = new ArrayList<ReferenceDataRefDataType>();
        }
        return this.refData;
    }

}
