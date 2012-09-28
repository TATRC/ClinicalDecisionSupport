
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for KMV_UserCommentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_UserCommentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KM_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="KM_VersionNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UC_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UC_UserRole" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UC_UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UC_UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UC_UserAffiliation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UC_UserContact" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UC_CreatedTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UC_Comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_UserCommentType", propOrder = {
    "kmid",
    "kmVersionNum",
    "ucid",
    "ucUserRole",
    "ucUserName",
    "ucUserID",
    "ucUserAffiliation",
    "ucUserContact",
    "ucCreatedTimestamp",
    "ucComment"
})
public class KMVUserCommentType {

    @XmlElement(name = "KM_ID")
    protected int kmid;
    @XmlElement(name = "KM_VersionNum")
    protected int kmVersionNum;
    @XmlElement(name = "UC_ID")
    protected int ucid;
    @XmlElement(name = "UC_UserRole", required = true)
    protected String ucUserRole;
    @XmlElement(name = "UC_UserName", required = true)
    protected String ucUserName;
    @XmlElement(name = "UC_UserID", required = true)
    protected String ucUserID;
    @XmlElement(name = "UC_UserAffiliation", required = true)
    protected String ucUserAffiliation;
    @XmlElement(name = "UC_UserContact", required = true)
    protected String ucUserContact;
    @XmlElement(name = "UC_CreatedTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ucCreatedTimestamp;
    @XmlElement(name = "UC_Comment", required = true)
    protected String ucComment;

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

    /**
     * Gets the value of the ucid property.
     * 
     */
    public int getUCID() {
        return ucid;
    }

    /**
     * Sets the value of the ucid property.
     * 
     */
    public void setUCID(int value) {
        this.ucid = value;
    }

    /**
     * Gets the value of the ucUserRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUCUserRole() {
        return ucUserRole;
    }

    /**
     * Sets the value of the ucUserRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUCUserRole(String value) {
        this.ucUserRole = value;
    }

    /**
     * Gets the value of the ucUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUCUserName() {
        return ucUserName;
    }

    /**
     * Sets the value of the ucUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUCUserName(String value) {
        this.ucUserName = value;
    }

    /**
     * Gets the value of the ucUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUCUserID() {
        return ucUserID;
    }

    /**
     * Sets the value of the ucUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUCUserID(String value) {
        this.ucUserID = value;
    }

    /**
     * Gets the value of the ucUserAffiliation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUCUserAffiliation() {
        return ucUserAffiliation;
    }

    /**
     * Sets the value of the ucUserAffiliation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUCUserAffiliation(String value) {
        this.ucUserAffiliation = value;
    }

    /**
     * Gets the value of the ucUserContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUCUserContact() {
        return ucUserContact;
    }

    /**
     * Sets the value of the ucUserContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUCUserContact(String value) {
        this.ucUserContact = value;
    }

    /**
     * Gets the value of the ucCreatedTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUCCreatedTimestamp() {
        return ucCreatedTimestamp;
    }

    /**
     * Sets the value of the ucCreatedTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUCCreatedTimestamp(XMLGregorianCalendar value) {
        this.ucCreatedTimestamp = value;
    }

    /**
     * Gets the value of the ucComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUCComment() {
        return ucComment;
    }

    /**
     * Sets the value of the ucComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUCComment(String value) {
        this.ucComment = value;
    }

}
