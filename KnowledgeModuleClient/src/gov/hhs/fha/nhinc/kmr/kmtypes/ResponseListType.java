
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="km" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KmFullResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseListType", propOrder = {
    "km"
})
public class ResponseListType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected List<KmFullResponse> km;

    /**
     * Gets the value of the km property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the km property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KmFullResponse }
     * 
     * 
     */
    public List<KmFullResponse> getKm() {
        if (km == null) {
            km = new ArrayList<KmFullResponse>();
        }
        return this.km;
    }

}
