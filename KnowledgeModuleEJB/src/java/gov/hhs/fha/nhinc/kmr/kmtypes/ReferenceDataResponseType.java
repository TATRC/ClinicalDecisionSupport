
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceData.ResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceData.ResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="refData" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ReferenceData.RefDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TotalReferencesFound" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Reference" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceData.ResponseType", propOrder = {
    "refData"
})
public class ReferenceDataResponseType {

    protected List<ReferenceDataRefDataType> refData;
    @XmlAttribute(name = "TotalReferencesFound", required = true)
    protected int totalReferencesFound;
    @XmlAttribute(name = "Reference", required = true)
    protected String reference;

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

    /**
     * Gets the value of the totalReferencesFound property.
     * 
     */
    public int getTotalReferencesFound() {
        return totalReferencesFound;
    }

    /**
     * Sets the value of the totalReferencesFound property.
     * 
     */
    public void setTotalReferencesFound(int value) {
        this.totalReferencesFound = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

}
