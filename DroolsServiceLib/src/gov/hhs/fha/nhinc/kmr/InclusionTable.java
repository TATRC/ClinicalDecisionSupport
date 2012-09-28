package gov.hhs.fha.nhinc.kmr;

import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for InclusionTable complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InclusionTable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inclusionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codes" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InclusionTable", propOrder = {
    "domain",
    "inclusionType",
    "codes"
})
/**
 *
 * @author Steven Clark
 */
public class InclusionTable
{
    @XmlElement(required = true)
    private String domain;
    @XmlElement(required = true)
    private String inclusionType;
    @XmlElement(required = true)
    private List<CodeLabelPair> codes;

    /**
     * Gets the value of the domain property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the inclusionType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInclusionType() {
        return inclusionType;
    }

    /**
     * Sets the value of the inclusionType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInclusionType(String value) {
        this.inclusionType = value;
    }

    /**
     * Gets the value of the codes property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codes property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodes().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeLabelPair }
     *
     *
     */
    public List<CodeLabelPair> getCodes()
    {
        if (codes == null) {
            codes = new ArrayList<CodeLabelPair>();
        }
        return this.codes;
    }
}
