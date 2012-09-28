
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for KMVersionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMVersionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KM_VersionNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="KMV_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedBy_AuthorID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedBy_AuthorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastModifiedTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ValidationStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuthorComments" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LMT_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Logic_IntermediateForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Logic_NativeForm" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="Logic_BinaryForm" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="FactSpecificationList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}FactSpecificationListType"/>
 *         &lt;element name="PopulationDependencyList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}PopulationDependencyListType"/>
 *         &lt;element name="InferenceList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}InferenceListType"/>
 *         &lt;element name="TaskList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}TaskListType"/>
 *         &lt;element name="ACL_PermissionList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ACL_PermissionListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMVersionType", propOrder = {
    "kmVersionNum",
    "kmvName",
    "createdByAuthorID",
    "createdByAuthorName",
    "createdTimestamp",
    "lastModifiedTimestamp",
    "validationStatus",
    "authorComments",
    "lmtid",
    "logicIntermediateForm",
    "logicNativeForm",
    "logicBinaryForm",
    "factSpecificationList",
    "populationDependencyList",
    "inferenceList",
    "taskList",
    "aclPermissionList"
})
public class KMVersionType {

    @XmlElement(name = "KM_VersionNum")
    protected int kmVersionNum;
    @XmlElement(name = "KMV_Name", required = true)
    protected String kmvName;
    @XmlElement(name = "CreatedBy_AuthorID", required = true)
    protected String createdByAuthorID;
    @XmlElement(name = "CreatedBy_AuthorName", required = true)
    protected String createdByAuthorName;
    @XmlElement(name = "CreatedTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTimestamp;
    @XmlElement(name = "LastModifiedTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTimestamp;
    @XmlElement(name = "ValidationStatus", required = true)
    protected String validationStatus;
    @XmlElement(name = "AuthorComments", required = true)
    protected String authorComments;
    @XmlElement(name = "LMT_ID")
    protected int lmtid;
    @XmlElement(name = "Logic_IntermediateForm", required = true)
    protected String logicIntermediateForm;
    @XmlElement(name = "Logic_NativeForm", required = true)
    protected byte[] logicNativeForm;
    @XmlElement(name = "Logic_BinaryForm", required = true)
    protected byte[] logicBinaryForm;
    @XmlElement(name = "FactSpecificationList", required = true)
    protected FactSpecificationListType factSpecificationList;
    @XmlElement(name = "PopulationDependencyList", required = true)
    protected PopulationDependencyListType populationDependencyList;
    @XmlElement(name = "InferenceList", required = true)
    protected InferenceListType inferenceList;
    @XmlElement(name = "TaskList", required = true)
    protected TaskListType taskList;
    @XmlElement(name = "ACL_PermissionList", required = true)
    protected ACLPermissionListType aclPermissionList;

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
     * Gets the value of the kmvName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKMVName() {
        return kmvName;
    }

    /**
     * Sets the value of the kmvName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKMVName(String value) {
        this.kmvName = value;
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
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Sets the value of the createdTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTimestamp(XMLGregorianCalendar value) {
        this.createdTimestamp = value;
    }

    /**
     * Gets the value of the lastModifiedTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    /**
     * Sets the value of the lastModifiedTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedTimestamp(XMLGregorianCalendar value) {
        this.lastModifiedTimestamp = value;
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
     * Gets the value of the authorComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorComments() {
        return authorComments;
    }

    /**
     * Sets the value of the authorComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorComments(String value) {
        this.authorComments = value;
    }

    /**
     * Gets the value of the lmtid property.
     * 
     */
    public int getLMTID() {
        return lmtid;
    }

    /**
     * Sets the value of the lmtid property.
     * 
     */
    public void setLMTID(int value) {
        this.lmtid = value;
    }

    /**
     * Gets the value of the logicIntermediateForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogicIntermediateForm() {
        return logicIntermediateForm;
    }

    /**
     * Sets the value of the logicIntermediateForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogicIntermediateForm(String value) {
        this.logicIntermediateForm = value;
    }

    /**
     * Gets the value of the logicNativeForm property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogicNativeForm() {
        return logicNativeForm;
    }

    /**
     * Sets the value of the logicNativeForm property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogicNativeForm(byte[] value) {
        this.logicNativeForm = ((byte[]) value);
    }

    /**
     * Gets the value of the logicBinaryForm property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogicBinaryForm() {
        return logicBinaryForm;
    }

    /**
     * Sets the value of the logicBinaryForm property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogicBinaryForm(byte[] value) {
        this.logicBinaryForm = ((byte[]) value);
    }

    /**
     * Gets the value of the factSpecificationList property.
     * 
     * @return
     *     possible object is
     *     {@link FactSpecificationListType }
     *     
     */
    public FactSpecificationListType getFactSpecificationList() {
        return factSpecificationList;
    }

    /**
     * Sets the value of the factSpecificationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactSpecificationListType }
     *     
     */
    public void setFactSpecificationList(FactSpecificationListType value) {
        this.factSpecificationList = value;
    }

    /**
     * Gets the value of the populationDependencyList property.
     * 
     * @return
     *     possible object is
     *     {@link PopulationDependencyListType }
     *     
     */
    public PopulationDependencyListType getPopulationDependencyList() {
        return populationDependencyList;
    }

    /**
     * Sets the value of the populationDependencyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PopulationDependencyListType }
     *     
     */
    public void setPopulationDependencyList(PopulationDependencyListType value) {
        this.populationDependencyList = value;
    }

    /**
     * Gets the value of the inferenceList property.
     * 
     * @return
     *     possible object is
     *     {@link InferenceListType }
     *     
     */
    public InferenceListType getInferenceList() {
        return inferenceList;
    }

    /**
     * Sets the value of the inferenceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link InferenceListType }
     *     
     */
    public void setInferenceList(InferenceListType value) {
        this.inferenceList = value;
    }

    /**
     * Gets the value of the taskList property.
     * 
     * @return
     *     possible object is
     *     {@link TaskListType }
     *     
     */
    public TaskListType getTaskList() {
        return taskList;
    }

    /**
     * Sets the value of the taskList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskListType }
     *     
     */
    public void setTaskList(TaskListType value) {
        this.taskList = value;
    }

    /**
     * Gets the value of the aclPermissionList property.
     * 
     * @return
     *     possible object is
     *     {@link ACLPermissionListType }
     *     
     */
    public ACLPermissionListType getACLPermissionList() {
        return aclPermissionList;
    }

    /**
     * Sets the value of the aclPermissionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACLPermissionListType }
     *     
     */
    public void setACLPermissionList(ACLPermissionListType value) {
        this.aclPermissionList = value;
    }

}
