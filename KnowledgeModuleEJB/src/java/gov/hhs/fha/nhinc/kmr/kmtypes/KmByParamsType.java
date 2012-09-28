
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmByParamsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmByParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdByAuthorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdByAuthorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCheckedOut" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="kmDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kmId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="kmKeywords" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kmName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kmOrganizationLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kmType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedByAuthorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedByAuthorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latestVersionNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="originInstitution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentKMId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="productionVersionNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="validationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Specialty" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KM_Specialty_SimpleType" minOccurs="0"/>
 *         &lt;element name="Fact" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_FactDependency_SimpleType" minOccurs="0"/>
 *         &lt;element name="PopulationDependency" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_PopulationDependency_SimpleType" minOccurs="0"/>
 *         &lt;element name="Inference" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_InferenceEngineDependency_SimpleType" minOccurs="0"/>
 *         &lt;element name="Task" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KMV_TaskDependency_SimpleType" minOccurs="0"/>
 *         &lt;element name="ACL" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ACL_SimpleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmByParamsType", propOrder = {
    "createdByAuthorID",
    "createdByAuthorName",
    "createdTimestamp",
    "isCheckedOut",
    "kmDescription",
    "kmId",
    "kmKeywords",
    "kmName",
    "kmOrganizationLevel",
    "kmType",
    "lastModifiedByAuthorID",
    "lastModifiedByAuthorName",
    "lastModifiedTimestamp",
    "latestVersionNum",
    "originInstitution",
    "parentKMId",
    "productionVersionNum",
    "validationStatus",
    "specialty",
    "fact",
    "populationDependency",
    "inference",
    "task",
    "acl"
})
public class KmByParamsType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String createdByAuthorID;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String createdByAuthorName;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String createdTimestamp;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Boolean isCheckedOut;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String kmDescription;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Integer kmId;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String kmKeywords;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String kmName;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String kmOrganizationLevel;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String kmType;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String lastModifiedByAuthorID;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String lastModifiedByAuthorName;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String lastModifiedTimestamp;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Integer latestVersionNum;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String originInstitution;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Integer parentKMId;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected Integer productionVersionNum;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected String validationStatus;
    @XmlElement(name = "Specialty", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected KMSpecialtySimpleType specialty;
    @XmlElement(name = "Fact", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected KMVFactDependencySimpleType fact;
    @XmlElement(name = "PopulationDependency", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected KMVPopulationDependencySimpleType populationDependency;
    @XmlElement(name = "Inference", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected KMVInferenceEngineDependencySimpleType inference;
    @XmlElement(name = "Task", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected KMVTaskDependencySimpleType task;
    @XmlElement(name = "ACL", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected ACLSimpleType acl;

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
     * Gets the value of the kmId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getKmId() {
        return kmId;
    }

    /**
     * Sets the value of the kmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setKmId(Integer value) {
        this.kmId = value;
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
     * Gets the value of the latestVersionNum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatestVersionNum() {
        return latestVersionNum;
    }

    /**
     * Sets the value of the latestVersionNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatestVersionNum(Integer value) {
        this.latestVersionNum = value;
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
     * Gets the value of the specialty property.
     * 
     * @return
     *     possible object is
     *     {@link KMSpecialtySimpleType }
     *     
     */
    public KMSpecialtySimpleType getSpecialty() {
        return specialty;
    }

    /**
     * Sets the value of the specialty property.
     * 
     * @param value
     *     allowed object is
     *     {@link KMSpecialtySimpleType }
     *     
     */
    public void setSpecialty(KMSpecialtySimpleType value) {
        this.specialty = value;
    }

    /**
     * Gets the value of the fact property.
     * 
     * @return
     *     possible object is
     *     {@link KMVFactDependencySimpleType }
     *     
     */
    public KMVFactDependencySimpleType getFact() {
        return fact;
    }

    /**
     * Sets the value of the fact property.
     * 
     * @param value
     *     allowed object is
     *     {@link KMVFactDependencySimpleType }
     *     
     */
    public void setFact(KMVFactDependencySimpleType value) {
        this.fact = value;
    }

    /**
     * Gets the value of the populationDependency property.
     * 
     * @return
     *     possible object is
     *     {@link KMVPopulationDependencySimpleType }
     *     
     */
    public KMVPopulationDependencySimpleType getPopulationDependency() {
        return populationDependency;
    }

    /**
     * Sets the value of the populationDependency property.
     * 
     * @param value
     *     allowed object is
     *     {@link KMVPopulationDependencySimpleType }
     *     
     */
    public void setPopulationDependency(KMVPopulationDependencySimpleType value) {
        this.populationDependency = value;
    }

    /**
     * Gets the value of the inference property.
     * 
     * @return
     *     possible object is
     *     {@link KMVInferenceEngineDependencySimpleType }
     *     
     */
    public KMVInferenceEngineDependencySimpleType getInference() {
        return inference;
    }

    /**
     * Sets the value of the inference property.
     * 
     * @param value
     *     allowed object is
     *     {@link KMVInferenceEngineDependencySimpleType }
     *     
     */
    public void setInference(KMVInferenceEngineDependencySimpleType value) {
        this.inference = value;
    }

    /**
     * Gets the value of the task property.
     * 
     * @return
     *     possible object is
     *     {@link KMVTaskDependencySimpleType }
     *     
     */
    public KMVTaskDependencySimpleType getTask() {
        return task;
    }

    /**
     * Sets the value of the task property.
     * 
     * @param value
     *     allowed object is
     *     {@link KMVTaskDependencySimpleType }
     *     
     */
    public void setTask(KMVTaskDependencySimpleType value) {
        this.task = value;
    }

    /**
     * Gets the value of the acl property.
     * 
     * @return
     *     possible object is
     *     {@link ACLSimpleType }
     *     
     */
    public ACLSimpleType getACL() {
        return acl;
    }

    /**
     * Sets the value of the acl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACLSimpleType }
     *     
     */
    public void setACL(ACLSimpleType value) {
        this.acl = value;
    }

}
