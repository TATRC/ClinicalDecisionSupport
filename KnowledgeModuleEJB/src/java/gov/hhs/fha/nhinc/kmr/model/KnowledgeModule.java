/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.KmFullResponse;
import gov.hhs.fha.nhinc.kmr.util.DateUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "KnowledgeModule")
@NamedQueries({

@NamedQuery(name = "KnowledgeModule.findDistinctKMType",
query = "SELECT DISTINCT k.kMType FROM KnowledgeModule k WHERE UPPER(k.kMType) LIKE :kMType", hints={@QueryHint(name="toplink.refresh", value="true")}),

@NamedQuery(name = "KnowledgeModule.findDistinctKMOrganizationLevel",
query = "SELECT DISTINCT k.kMOrganizationLevel FROM KnowledgeModule k WHERE UPPER(k.kMOrganizationLevel) LIKE :kMOrganizationLevel", hints={@QueryHint(name="toplink.refresh", value="true")}),

@NamedQuery(name = "KnowledgeModule.findDistinctValidationStatus",
query = "SELECT DISTINCT k.validationStatus FROM KnowledgeModule k WHERE UPPER(k.validationStatus) LIKE :validationStatus", hints={@QueryHint(name="toplink.refresh", value="true")}),

@NamedQuery(name = "KnowledgeModule.findAll", query = "SELECT k FROM KnowledgeModule k", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByKmId", query = "SELECT k FROM KnowledgeModule k WHERE k.kmId = :kmId", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByKMName", query = "SELECT k FROM KnowledgeModule k WHERE k.kMName = :kMName", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByKMType", query = "SELECT k FROM KnowledgeModule k WHERE k.kMType = :kMType", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByKMOrganizationLevel", query = "SELECT k FROM KnowledgeModule k WHERE k.kMOrganizationLevel = :kMOrganizationLevel", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByKMKeywords", query = "SELECT k FROM KnowledgeModule k WHERE k.kMKeywords = :kMKeywords", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByKMDescription", query = "SELECT k FROM KnowledgeModule k WHERE k.kMDescription = :kMDescription", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByParentKMID", query = "SELECT k FROM KnowledgeModule k WHERE k.parentKMID = :parentKMID", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByOriginInstitution", query = "SELECT k FROM KnowledgeModule k WHERE k.originInstitution = :originInstitution", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByCreatedByAuthorID", query = "SELECT k FROM KnowledgeModule k WHERE k.createdByAuthorID = :createdByAuthorID", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByCreatedByAuthorName", query = "SELECT k FROM KnowledgeModule k WHERE k.createdByAuthorName = :createdByAuthorName", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByCreatedTimestamp", query = "SELECT k FROM KnowledgeModule k WHERE k.createdTimestamp = :createdTimestamp", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByLatestVersionNum", query = "SELECT k FROM KnowledgeModule k WHERE k.latestVersionNum = :latestVersionNum", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByLastModifiedByAuthorID", query = "SELECT k FROM KnowledgeModule k WHERE k.lastModifiedByAuthorID = :lastModifiedByAuthorID", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByLastModifiedByAuthorName", query = "SELECT k FROM KnowledgeModule k WHERE k.lastModifiedByAuthorName = :lastModifiedByAuthorName", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByLastModifiedTimestamp", query = "SELECT k FROM KnowledgeModule k WHERE k.lastModifiedTimestamp = :lastModifiedTimestamp", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByProductionVersionNum", query = "SELECT k FROM KnowledgeModule k WHERE k.productionVersionNum = :productionVersionNum", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByValidationStatus", query = "SELECT k FROM KnowledgeModule k WHERE k.validationStatus = :validationStatus", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KnowledgeModule.findByIsCheckedOut", query = "SELECT k FROM KnowledgeModule k WHERE k.isCheckedOut = :isCheckedOut", hints={@QueryHint(name="toplink.refresh", value="true")})
})
public class KnowledgeModule implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   //@GeneratedValue(strategy = GenerationType.IDENTITY)
   //@Basic(optional = false)
   @Column(name = "KM_ID")
   private Integer kmId;
   //@Basic(optional = false)
   @Column(name = "KM_Name")
   private String kMName;
   @Column(name = "KM_Type")
   private String kMType;
   @Column(name = "KM_OrganizationLevel")
   private String kMOrganizationLevel;
   @Column(name = "KM_Keywords")
   private String kMKeywords;
   @Column(name = "KM_Description")
   private String kMDescription;
   @Column(name = "Parent_KM_ID")
   private Integer parentKMID;
   @Column(name = "OriginInstitution")
   private String originInstitution;
   @Column(name = "CreatedBy_AuthorID")
   private String createdByAuthorID;
   @Column(name = "CreatedBy_AuthorName")
   private String createdByAuthorName;
   @Column(name = "CreatedTimestamp")
   @Temporal(TemporalType.TIMESTAMP)
   private Date createdTimestamp;
   @Column(name = "LatestVersionNum")
   private Integer latestVersionNum;
   @Column(name = "LastModifiedBy_AuthorID")
   private String lastModifiedByAuthorID;
   @Column(name = "LastModifiedBy_AuthorName")
   private String lastModifiedByAuthorName;
   @Column(name = "LastModifiedTimestamp")
   @Temporal(TemporalType.TIMESTAMP)
   private Date lastModifiedTimestamp;
   @Column(name = "ProductionVersionNum")
   private Integer productionVersionNum;
   @Column(name = "ValidationStatus")
   private String validationStatus;
   @Column(name = "isCheckedOut")
   private Boolean isCheckedOut;
   
   @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "kmId", fetch = FetchType.EAGER)
   private Collection<KMVersion> kMVersionCollection;

   @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "knowledgeModule", fetch = FetchType.EAGER)
   private Collection<KMSpecialty> kMSpecialtyCollection;

   public KnowledgeModule() {
   }

   public KnowledgeModule(Integer kmId) {
      this.kmId = kmId;
   }

   public KnowledgeModule(Integer kmId, String kMName) {
      this.kmId = kmId;
      this.kMName = kMName;
   }

   public Integer getKmId() {
      return kmId;
   }

   public void setKmId(Integer kmId) {
      this.kmId = kmId;
   }

   public String getKMName() {
      return kMName;
   }

   public void setKMName(String kMName) {
      this.kMName = kMName;
   }

   public String getKMType() {
      return kMType;
   }

   public void setKMType(String kMType) {
      this.kMType = kMType;
   }

   public String getKMOrganizationLevel() {
      return kMOrganizationLevel;
   }

   public void setKMOrganizationLevel(String kMOrganizationLevel) {
      this.kMOrganizationLevel = kMOrganizationLevel;
   }

   public String getKMKeywords() {
      return kMKeywords;
   }

   public void setKMKeywords(String kMKeywords) {
      this.kMKeywords = kMKeywords;
   }

   public String getKMDescription() {
      return kMDescription;
   }

   public void setKMDescription(String kMDescription) {
      this.kMDescription = kMDescription;
   }

   public Integer getParentKMID() {
      return parentKMID;
   }

   public void setParentKMID(Integer parentKMID) {
      this.parentKMID = parentKMID;
   }

   public String getOriginInstitution() {
      return originInstitution;
   }

   public void setOriginInstitution(String originInstitution) {
      this.originInstitution = originInstitution;
   }

   public String getCreatedByAuthorID() {
      return createdByAuthorID;
   }

   public void setCreatedByAuthorID(String createdByAuthorID) {
      this.createdByAuthorID = createdByAuthorID;
   }

   public String getCreatedByAuthorName() {
      return createdByAuthorName;
   }

   public void setCreatedByAuthorName(String createdByAuthorName) {
      this.createdByAuthorName = createdByAuthorName;
   }

   public Date getCreatedTimestamp() {
      return createdTimestamp;
   }

   public void setCreatedTimestamp(Date createdTimestamp) {
      this.createdTimestamp = createdTimestamp;
   }

   public Integer getLatestVersionNum() {
      return latestVersionNum;
   }

   public void setLatestVersionNum(Integer latestVersionNum) {
      this.latestVersionNum = latestVersionNum;
   }

   public String getLastModifiedByAuthorID() {
      return lastModifiedByAuthorID;
   }

   public void setLastModifiedByAuthorID(String lastModifiedByAuthorID) {
      this.lastModifiedByAuthorID = lastModifiedByAuthorID;
   }

   public String getLastModifiedByAuthorName() {
      return lastModifiedByAuthorName;
   }

   public void setLastModifiedByAuthorName(String lastModifiedByAuthorName) {
      this.lastModifiedByAuthorName = lastModifiedByAuthorName;
   }

   public Date getLastModifiedTimestamp() {
      return lastModifiedTimestamp;
   }

   public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
      this.lastModifiedTimestamp = lastModifiedTimestamp;
   }

   public Integer getProductionVersionNum() {
      return productionVersionNum;
   }

   public void setProductionVersionNum(Integer productionVersionNum) {
      this.productionVersionNum = productionVersionNum;
   }

   public String getValidationStatus() {
      return validationStatus;
   }

   public void setValidationStatus(String validationStatus) {
      this.validationStatus = validationStatus;
   }

   public Boolean getIsCheckedOut() {
      return isCheckedOut;
   }

   public void setIsCheckedOut(Boolean isCheckedOut) {
      this.isCheckedOut = isCheckedOut;
   }

   public Collection<KMVersion> getKMVersionCollection() {
      return kMVersionCollection;
   }

   public void setKMVersionCollection(Collection<KMVersion> kMVersionCollection) {
      this.kMVersionCollection = kMVersionCollection;
   }

   public Collection<KMSpecialty> getKMSpecialtyCollection() {
      return kMSpecialtyCollection;
   }

   public void setKMSpecialtyCollection(Collection<KMSpecialty> kMSpecialtyCollection) {
      this.kMSpecialtyCollection = kMSpecialtyCollection;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (kmId != null ? kmId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KnowledgeModule)) {
         return false;
      }
      KnowledgeModule other = (KnowledgeModule) object;
      if ((this.kmId == null && other.kmId != null) || (this.kmId != null && !this.kmId.equals(other.kmId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KnowledgeModule[kmId=" + kmId + "]";
   }


   // ======================================================
   //               USER DEFINEs
   // ======================================================


   public KnowledgeModule(KmFullResponse kmr) {
      DateUtils helper = new DateUtils();

      this.kmId = kmr.getKmId();
      this.kMName = kmr.getKmName();
      
      this.createdByAuthorID = kmr.getCreatedByAuthorID();
      this.createdByAuthorName = kmr.getCreatedByAuthorName();      
      try {
         String crTime = null;
         if (kmr.getCreatedTimestamp().isEmpty()) {
            crTime = helper.getCurrDateTimeAsXMLGregorian().toXMLFormat();
         } else {
            crTime = kmr.getCreatedTimestamp();
         }
         this.createdTimestamp = helper.XMLString2Date(crTime);         
      }
      catch (DatatypeConfigurationException ex) {
         ex.printStackTrace();
         System.out.println("ERROR CreatedTime=" + ex.getMessage());
         Logger.getLogger(KMVersion.class.getName()).log(Level.SEVERE, null, ex);
      }

      this.isCheckedOut = kmr.isIsCheckedOut();
      this.kMDescription = kmr.getKmDescription();
      this.kMKeywords = kmr.getKmKeywords();
      this.kMOrganizationLevel = kmr.getKmOrganizationLevel();
      this.kMType = kmr.getKmType();

      this.lastModifiedByAuthorID = kmr.getLastModifiedByAuthorID();
      this.lastModifiedByAuthorName = kmr.getLastModifiedByAuthorName();
      try {
         this.lastModifiedTimestamp = helper.XMLString2Date(kmr.getLastModifiedTimestamp());
      } catch (DatatypeConfigurationException ex) {
         ex.printStackTrace();
         System.out.println("ERROR ModifiedTime=" + ex.getMessage());
         Logger.getLogger(KMVersion.class.getName()).log(Level.SEVERE, null, ex);
      }
      this.latestVersionNum = new Integer(kmr.getLatestVersionNum());
      this.originInstitution = kmr.getOriginInstitution();
      this.parentKMID = kmr.getParentKMId();
      this.productionVersionNum = kmr.getProductionVersionNum();
      this.validationStatus = kmr.getValidationStatus();
   }

   /*
   public KnowledgeModule fromKmTypes(KmFullResponse kmr) {
      KnowledgeModule km = new KnowledgeModule();
      DateUtils helper = new DateUtils();

      km.setKmId(kmr.getKmId());
      km.setKMName(kmr.getKmName());

      km.setCreatedByAuthorID(kmr.getCreatedByAuthorID());
      km.setCreatedByAuthorName(kmr.getCreatedByAuthorName());           
      try {
         String crTime = null;
         if (kmr.getCreatedTimestamp().isEmpty()) {
            crTime = helper.getCurrDateTimeAsXMLGregorian().toXMLFormat();
         } else {
            crTime = kmr.getCreatedTimestamp();
         }
         km.setCreatedTimestamp(helper.XMLString2Date(crTime));
      } catch (DatatypeConfigurationException ex) {
         ex.printStackTrace();
         
         System.out.println("ERROR CreatedTime=" + ex.getMessage());
         Logger.getLogger(KMVersion.class.getName()).log(Level.SEVERE, null, ex);
      }

      km.setIsCheckedOut(kmr.isIsCheckedOut());
      km.setKMDescription(kmr.getKmDescription());
      km.setKMKeywords(kmr.getKmKeywords());
      km.setKMOrganizationLevel(kmr.getKmOrganizationLevel());
      km.setKMType(kmr.getKmType());

      km.setLastModifiedByAuthorID(kmr.getLastModifiedByAuthorID());
      km.setLastModifiedByAuthorName(kmr.getLastModifiedByAuthorName());
      try {
         km.setLastModifiedTimestamp(helper.XMLString2Date(kmr.getLastModifiedTimestamp()));
      } catch (DatatypeConfigurationException ex) {
         ex.printStackTrace();
         System.out.println("ERROR ModifiedTime=" + ex.getMessage());
         Logger.getLogger(KMVersion.class.getName()).log(Level.SEVERE, null, ex);
      }
      km.setLatestVersionNum((new Integer(kmr.getLatestVersionNum())));
      km.setOriginInstitution(kmr.getOriginInstitution());
      km.setParentKMID(kmr.getParentKMId());
      km.setProductionVersionNum(kmr.getProductionVersionNum());
      km.setValidationStatus(kmr.getValidationStatus());      
      return km;
   }*/
    

   /**
    * toKmTypes()
    *    Returns a KmFullResponse type object, filed with data found from
    *    matching record from KnowledgeModule table.
    *
    * @return KmFullResponse
    */
   public KmFullResponse toKmTypes() {
      KmFullResponse km = new KmFullResponse();

      km.setKmId(this.kmId);
      km.setKmName(this.kMName);
      km.setCreatedByAuthorID(this.createdByAuthorID);
      km.setCreatedByAuthorName(this.createdByAuthorName);

      if (this.createdTimestamp != null)
         km.setCreatedTimestamp(this.createdTimestamp.toString());

      km.setIsCheckedOut(this.isCheckedOut);
      km.setKmDescription(this.kMDescription);
      km.setKmKeywords(this.kMKeywords);
      km.setKmOrganizationLevel(this.kMOrganizationLevel);
      km.setKmType(this.kMType);
      km.setLastModifiedByAuthorID(this.lastModifiedByAuthorID);
      km.setLastModifiedByAuthorName(this.lastModifiedByAuthorName);

      if (this.lastModifiedTimestamp != null)
         km.setLastModifiedTimestamp(this.lastModifiedTimestamp.toString());

      if (latestVersionNum != null) km.setLatestVersionNum(this.latestVersionNum.intValue());
      km.setOriginInstitution(this.originInstitution);
      if (parentKMID != null) km.setParentKMId(this.parentKMID.intValue());
      if (productionVersionNum != null) km.setProductionVersionNum(this.productionVersionNum.intValue());
      km.setValidationStatus(this.validationStatus);

      return km;
   }


   /**
    * toKmTypes_simple()
    *    Returns a KmFullResponse type object, filed with limitted data found from
    *    matching record from KnowledgeModule table.
    *
    * @return KmFullResponse
    */
   public KmFullResponse toKmTypes_simple() {
      KmFullResponse km = new KmFullResponse();

      km.setKmId(this.kmId);
      km.setKmName(this.kMName);
      km.setValidationStatus(this.validationStatus);

      return km;
   }

}
