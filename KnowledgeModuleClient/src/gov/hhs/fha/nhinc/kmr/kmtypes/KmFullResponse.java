
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmFullResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmFullResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kmId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="kmName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kmDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kmKeywords" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kmOrganizationLevel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kmType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastModifiedByAuthorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedByAuthorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdByAuthorID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="createdByAuthorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTimestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isCheckedOut" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="originInstitution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentKMId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latestVersionNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productionVersionNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="validationStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SpecialtyList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}SpecialtyListType"/>
 *         &lt;element name="KmVersionList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KmVersionListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmFullResponse", propOrder = {
    "kmId",
    "kmName",
    "kmDescription",
    "kmKeywords",
    "kmOrganizationLevel",
    "kmType",
    "lastModifiedByAuthorID",
    "lastModifiedByAuthorName",
    "lastModifiedTimestamp",
    "createdByAuthorID",
    "createdByAuthorName",
    "createdTimestamp",
    "isCheckedOut",
    "originInstitution",
    "parentKMId",
    "latestVersionNum",
    "productionVersionNum",
    "validationStatus",
    "specialtyList",
    "kmVersionList"
})
public class KmFullResponse {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected int kmId;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String kmName;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String kmDescription;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String kmKeywords;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String kmOrganizationLevel;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String kmType;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String lastModifiedByAuthorID;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String lastModifiedByAuthorName;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String lastModifiedTimestamp;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String createdByAuthorID;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String createdByAuthorName;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String createdTimestamp;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Boolean isCheckedOut;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String originInstitution;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Integer parentKMId;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected int latestVersionNum;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Integer productionVersionNum;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String validationStatus;
    @XmlElement(name = "SpecialtyList", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected SpecialtyListType specialtyList;
    @XmlElement(name = "KmVersionList", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected KmVersionListType kmVersionList;

    /**
     * Gets the value of the kmId property.
     * 
     */
    public int getKmId() {
        return kmId;
    }

    /**
     * Sets the value of the kmId property.
     * 
     */
    public void setKmId(int value) {
        this.kmId = value;
    }

    /**
     * Gets the value of the kmName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKmName() {
        return kmName;
    }

    /**
     * Sets the value of the kmName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKmName(String value) {
        this.kmName = value;
    }

    /**
     * Gets the value of the kmDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKmDescription() {
        return kmDescription;
    }

    /**
     * Sets the value of the kmDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKmDescription(String value) {
        this.kmDescription = value;
    }

    /**
     * Gets the value of the kmKeywords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKmKeywords() {
        return kmKeywords;
    }

    /**
     * Sets the value of the kmKeywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKmKeywords(String value) {
        this.kmKeywords = value;
    }

    /**
     * Gets the value of the kmOrganizationLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKmOrganizationLevel() {
        return kmOrganizationLevel;
    }

    /**
     * Sets the value of the kmOrganizationLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKmOrganizationLevel(String value) {
        this.kmOrganizationLevel = value;
    }

    /**
     * Gets the value of the kmType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKmType() {
        return kmType;
    }

    /**
     * Sets the value of the kmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKmType(String value) {
        this.kmType = value;
    }

    /**
     * Gets the value of the lastModifiedByAuthorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedByAuthorID() {
        return lastModifiedByAuthorID;
    }

    /**
     * Sets the value of the lastModifiedByAuthorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedByAuthorID(String value) {
        this.lastModifiedByAuthorID = value;
    }

    /**
     * Gets the value of the lastModifiedByAuthorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedByAuthorName() {
        return lastModifiedByAuthorName;
    }

    /**
     * Sets the value of the lastModifiedByAuthorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedByAuthorName(String value) {
        this.lastModifiedByAuthorName = value;
    }

    /**
     * Gets the value of the lastModifiedTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    /**
     * Sets the value of the lastModifiedTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedTimestamp(String value) {
        this.lastModifiedTimestamp = value;
    }

    /**
     * Gets the value of the createdByAuthorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedByAuthorID() {
        return createdByAuthorID;
    }

    /**
     * Sets the value of the createdByAuthorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedByAuthorID(String value) {
        this.createdByAuthorID = value;
    }

    /**
     * Gets the value of the createdByAuthorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedByAuthorName() {
        return createdByAuthorName;
    }

    /**
     * Sets the value of the createdByAuthorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedByAuthorName(String value) {
        this.createdByAuthorName = value;
    }

    /**
     * Gets the value of the createdTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Sets the value of the createdTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedTimestamp(String value) {
        this.createdTimestamp = value;
    }

    /**
     * Gets the value of the isCheckedOut property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsCheckedOut() {
        return isCheckedOut;
    }

    /**
     * Sets the value of the isCheckedOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsCheckedOut(Boolean value) {
        this.isCheckedOut = value;
    }

    /**
     * Gets the value of the originInstitution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginInstitution() {
        return originInstitution;
    }

    /**
     * Sets the value of the originInstitution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginInstitution(String value) {
        this.originInstitution = value;
    }

    /**
     * Gets the value of the parentKMId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentKMId() {
        return parentKMId;
    }

    /**
     * Sets the value of the parentKMId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentKMId(Integer value) {
        this.parentKMId = value;
    }

    /**
     * Gets the value of the latestVersionNum property.
     * 
     */
    public int getLatestVersionNum() {
        return latestVersionNum;
    }

    /**
     * Sets the value of the latestVersionNum property.
     * 
     */
    public void setLatestVersionNum(int value) {
        this.latestVersionNum = value;
    }

    /**
     * Gets the value of the productionVersionNum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProductionVersionNum() {
        return productionVersionNum;
    }

    /**
     * Sets the value of the productionVersionNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProductionVersionNum(Integer value) {
        this.productionVersionNum = value;
    }

    /**
     * Gets the value of the validationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationStatus() {
        return validationStatus;
    }

    /**
     * Sets the value of the validationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationStatus(String value) {
        this.validationStatus = value;
    }

    /**
     * Gets the value of the specialtyList property.
     * 
     * @return
     *     possible object is
     *     {@link SpecialtyListType }
     *     
     */
    public SpecialtyListType getSpecialtyList() {
        return specialtyList;
    }

    /**
     * Sets the value of the specialtyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecialtyListType }
     *     
     */
    public void setSpecialtyList(SpecialtyListType value) {
        this.specialtyList = value;
    }

    /**
     * Gets the value of the kmVersionList property.
     * 
     * @return
     *     possible object is
     *     {@link KmVersionListType }
     *     
     */
    public KmVersionListType getKmVersionList() {
        return kmVersionList;
    }

    /**
     * Sets the value of the kmVersionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link KmVersionListType }
     *     
     */
    public void setKmVersionList(KmVersionListType value) {
        this.kmVersionList = value;
    }

}
