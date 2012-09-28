/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMVersionType;

import gov.hhs.fha.nhinc.kmr.util.DateUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "KM_Version")
@NamedQueries({
@NamedQuery(name = "KMVersion.findAll", query = "SELECT k FROM KMVersion k", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByKmvId", query = "SELECT k FROM KMVersion k WHERE k.kmvId = :kmvId", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByKMVersionNum", query = "SELECT k FROM KMVersion k WHERE k.kMVersionNum = :kMVersionNum", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByKMVName", query = "SELECT k FROM KMVersion k WHERE k.kMVName = :kMVName", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByLmtId", query = "SELECT k FROM KMVersion k WHERE k.lmtId = :lmtId", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByValidationStatus", query = "SELECT k FROM KMVersion k WHERE k.validationStatus = :validationStatus", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByLastModifiedTimestamp", query = "SELECT k FROM KMVersion k WHERE k.lastModifiedTimestamp = :lastModifiedTimestamp", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByCreatedByAuthorID", query = "SELECT k FROM KMVersion k WHERE k.createdByAuthorID = :createdByAuthorID", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByCreatedByAuthorName", query = "SELECT k FROM KMVersion k WHERE k.createdByAuthorName = :createdByAuthorName", hints={@QueryHint(name="toplink.refresh", value="true")}),
@NamedQuery(name = "KMVersion.findByCreatedTimestamp", query = "SELECT k FROM KMVersion k WHERE k.createdTimestamp = :createdTimestamp", hints={@QueryHint(name="toplink.refresh", value="true")})
})
public class KMVersion implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   //@Basic(optional = false)
   @Column(name = "KMV_ID")
   private Integer kmvId;
   //@Basic(optional = false)
   @Column(name = "KM_VersionNum")
   private int kMVersionNum;
   @Column(name = "KMV_Name")
   private String kMVName;
   @Column(name = "LMT_ID")
   private Integer lmtId;
   @Column(name = "ValidationStatus")
   private String validationStatus;
   @Lob
   @Column(name = "Logic_IntermediateForm")
   private String logicIntermediateForm;
   @Lob
   @Column(name = "Logic_NativeForm")
   private String logicNativeForm;
   //@Lob
   @Column(name = "Logic_BinaryForm")
   private byte[] logicBinaryForm;
   @Lob
   @Column(name = "AuthorComments")
   private String authorComments;
   @Column(name = "LastModifiedTimestamp")
   @Temporal(TemporalType.TIMESTAMP)
   private Date lastModifiedTimestamp;
   @Column(name = "CreatedBy_AuthorID")
   private String createdByAuthorID;
   @Column(name = "CreatedBy_AuthorName")
   private String createdByAuthorName;
   @Column(name = "CreatedTimestamp")
   @Temporal(TemporalType.TIMESTAMP)
   private Date createdTimestamp;
   
   @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "kMVersion", fetch = FetchType.EAGER)
   private KMVInferenceEngineDependency kMVInferenceEngineDependency;
   
   @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "kmvId", fetch = FetchType.EAGER)
   private Collection<KMVPopulationDependency> kMVPopulationDependencyCollection;
   
   @OneToMany(cascade ={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "kmvId", fetch = FetchType.EAGER)
   private Collection<KMVFactDependency> kMVFactDependencyCollection;

   @JoinColumn(name = "ACL_ID", referencedColumnName = "ACL_ID")
   @ManyToOne(fetch = FetchType.EAGER)
   private KMVAccessControlList aclId;

   @JoinColumn(name = "KM_ID", referencedColumnName = "KM_ID")
   @ManyToOne(fetch = FetchType.EAGER) //(optional = false, fetch = FetchType.EAGER)
   private KnowledgeModule kmId;

   @OneToMany(mappedBy = "kmvId", fetch = FetchType.EAGER)
   private Collection<KMVTaskDependency> kMVTaskDependencyCollection;

   public KMVersion() {
   }

   public KMVersion(Integer kmvId) {
      this.kmvId = kmvId;
   }

   public KMVersion(Integer kmvId, int kMVersionNum) {
      this.kmvId = kmvId;
      this.kMVersionNum = kMVersionNum;
   }

   public Integer getKmvId() {
      return kmvId;
   }

   public void setKmvId(Integer kmvId) {
      this.kmvId = kmvId;
   }

   public int getKMVersionNum() {
      return kMVersionNum;
   }

   public void setKMVersionNum(int kMVersionNum) {
      this.kMVersionNum = kMVersionNum;
   }

   public String getKMVName() {
      return kMVName;
   }

   public void setKMVName(String kMVName) {
      this.kMVName = kMVName;
   }

   public Integer getLmtId() {
      return lmtId;
   }

   public void setLmtId(Integer lmtId) {
      this.lmtId = lmtId;
   }

   public String getValidationStatus() {
      return validationStatus;
   }

   public void setValidationStatus(String validationStatus) {
      this.validationStatus = validationStatus;
   }

   public String getLogicIntermediateForm() {
      return logicIntermediateForm;
   }

   public void setLogicIntermediateForm(String logicIntermediateForm) {
      this.logicIntermediateForm = logicIntermediateForm;
   }

   public String getLogicNativeForm() {
      return logicNativeForm;
   }

   public void setLogicNativeForm(String logicNativeForm) {
      this.logicNativeForm = logicNativeForm;
   }

   public byte[] getLogicBinaryForm() {
      return logicBinaryForm;
   }

   public void setLogicBinaryForm(byte[] logicBinaryForm) {
      this.logicBinaryForm = logicBinaryForm;
   }

   public String getAuthorComments() {
      return authorComments;
   }

   public void setAuthorComments(String authorComments) {
      this.authorComments = authorComments;
   }

   public Date getLastModifiedTimestamp() {
      return lastModifiedTimestamp;
   }

   public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
      this.lastModifiedTimestamp = lastModifiedTimestamp;
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

   public KMVInferenceEngineDependency getKMVInferenceEngineDependency() {
      return kMVInferenceEngineDependency;
   }

   public void setKMVInferenceEngineDependency(KMVInferenceEngineDependency kMVInferenceEngineDependency) {
      this.kMVInferenceEngineDependency = kMVInferenceEngineDependency;
   }

   public Collection<KMVPopulationDependency> getKMVPopulationDependencyCollection() {
      return kMVPopulationDependencyCollection;
   }

   public void setKMVPopulationDependencyCollection(Collection<KMVPopulationDependency> kMVPopulationDependencyCollection) {
      this.kMVPopulationDependencyCollection = kMVPopulationDependencyCollection;
   }

   public Collection<KMVFactDependency> getKMVFactDependencyCollection() {
      return kMVFactDependencyCollection;
   }

   public void setKMVFactDependencyCollection(Collection<KMVFactDependency> kMVFactDependencyCollection) {
      this.kMVFactDependencyCollection = kMVFactDependencyCollection;
   }

   public KMVAccessControlList getAclId() {
      return aclId;
   }

   public void setAclId(KMVAccessControlList aclId) {
      this.aclId = aclId;
   }

   public KnowledgeModule getKmId() {
      return kmId;
   }

   public void setKmId(KnowledgeModule kmId) {
      this.kmId = kmId;
   }

   public Collection<KMVTaskDependency> getKMVTaskDependencyCollection() {
      return kMVTaskDependencyCollection;
   }

   public void setKMVTaskDependencyCollection(Collection<KMVTaskDependency> kMVTaskDependencyCollection) {
      this.kMVTaskDependencyCollection = kMVTaskDependencyCollection;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (kmvId != null ? kmvId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVersion)) {
         return false;
      }
      KMVersion other = (KMVersion) object;
      if ((this.kmvId == null && other.kmvId != null) || (this.kmvId != null && !this.kmvId.equals(other.kmvId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVersion[kmvId=" + kmvId + "]";
   }

   // ======================================================
   //               USER DEFINEs
   // ======================================================

   public KMVersion(KMVersionType kmr) {
      DateUtils helper = new DateUtils();

      // TODO ACL - have to determine ACL_ID of ACL_Persmission record that
      // matches up to incoming ACL criterias.
      //KMVAccessControlList inACLid
      //km.setAclId(inACL);

      this.kMVName = kmr.getKMVName();
      this.kMVersionNum = (kmr.getKMVersionNum());
      this.authorComments = kmr.getAuthorComments();
      this.createdByAuthorID = (kmr.getCreatedByAuthorID());
      this.createdByAuthorName = (kmr.getCreatedByAuthorName());

      this.createdTimestamp = (helper.XMLGregorian2Date(kmr.getCreatedTimestamp()));
      this.lastModifiedTimestamp = (helper.XMLGregorian2Date(kmr.getLastModifiedTimestamp()));

      this.lmtId = ( (new Integer(kmr.getLMTID())) );

      this.logicIntermediateForm = (kmr.getLogicIntermediateForm());
      this.logicNativeForm = (kmr.getLogicNativeForm());
      this.logicBinaryForm = (kmr.getLogicBinaryForm());

      this.validationStatus = (kmr.getValidationStatus());

   }

   /**
    * Converting a kmr KMV (KMVersionType) to a model KMV (KMVersion)
    * @param kmr
    * @return
    */
   public KMVersion fromKmTypes(KMVersionType kmr) {
      KMVersion km = new KMVersion();
      DateUtils helper = new DateUtils();

      // TODO ACL - have to determine ACL_ID of ACL_Persmission record that
      // matches up to incoming ACL criterias.
      //KMVAccessControlList inACLid
      //km.setAclId(inACL);

      km.setKMVName(kmr.getKMVName());
      km.setKMVersionNum(kmr.getKMVersionNum());
      km.setAuthorComments(kmr.getAuthorComments());
      km.setCreatedByAuthorID(kmr.getCreatedByAuthorID());
      km.setCreatedByAuthorName(kmr.getCreatedByAuthorName());

      km.setCreatedTimestamp(helper.XMLGregorian2Date(kmr.getCreatedTimestamp()));
      km.setLastModifiedTimestamp(helper.XMLGregorian2Date(kmr.getLastModifiedTimestamp()));

      km.setLmtId( (new Integer(kmr.getLMTID())) );

      km.setLogicIntermediateForm(kmr.getLogicIntermediateForm());
      km.setLogicNativeForm(kmr.getLogicNativeForm());
      km.setLogicBinaryForm(kmr.getLogicBinaryForm());

      km.setValidationStatus(kmr.getValidationStatus());

      return km;
   }

   /**
    * Converting a model KMV (KMVersion) to a kmr KMV (KMVersionType)
    * @return
    */
   public KMVersionType toKmTypes() {
      KMVersionType km = new KMVersionType();
      DateUtils helper = new DateUtils();

      km.setKMVName(this.kMVName);
      km.setKMVersionNum(this.kMVersionNum);
      km.setAuthorComments(this.authorComments);
      km.setCreatedByAuthorID(this.createdByAuthorID);
      km.setCreatedByAuthorName(this.createdByAuthorName);

      System.out.println("KMVersion.toKmTypes --> " + this.kMVersionNum + " CREATOR+" + this.createdByAuthorName);

      try {
         km.setCreatedTimestamp(helper.Date2XMLDate(this.createdTimestamp));
         
      } catch (DatatypeConfigurationException ex) {
         System.out.println("FAILing CreatedTime=" + ex.getMessage());
         Logger.getLogger(KMVersion.class.getName()).log(Level.SEVERE, null, ex);
      }           
      
      if (lmtId != null) {
         km.setLMTID(this.lmtId.intValue());
      }
      try {
         km.setLastModifiedTimestamp(helper.Date2XMLDate(this.lastModifiedTimestamp));
      } catch (DatatypeConfigurationException ex) {
         System.out.println("FAILing ModifiedTime=" + ex.getMessage());
         Logger.getLogger(KMVersion.class.getName()).log(Level.SEVERE, null, ex);
      }
      km.setLogicIntermediateForm(this.logicIntermediateForm);
      km.setLogicNativeForm(this.logicNativeForm);
      km.setLogicBinaryForm(this.logicBinaryForm);
      km.setValidationStatus(this.validationStatus);

      System.out.println("km.getValidationStatus --> " + km.getValidationStatus());

      return km;
   }

   public KMVersionType toKmTypes_simple() {
      KMVersionType km = new KMVersionType();

      km.setKMVersionNum(this.kMVersionNum);
      km.setKMVName(this.kMVName);
      km.setLogicIntermediateForm(this.logicIntermediateForm);
      km.setLogicNativeForm(this.logicNativeForm);
      km.setLogicBinaryForm(this.logicBinaryForm);
      km.setValidationStatus(this.validationStatus);

      return km;
   }
}
