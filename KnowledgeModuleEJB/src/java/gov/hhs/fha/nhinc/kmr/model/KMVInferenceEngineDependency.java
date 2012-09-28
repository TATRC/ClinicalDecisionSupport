/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMVInferenceEngineDependencyType;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "KMV_InferenceEngineDependency")
@NamedQueries({@NamedQuery(name = "KMVInferenceEngineDependency.findAll", query = "SELECT k FROM KMVInferenceEngineDependency k"), @NamedQuery(name = "KMVInferenceEngineDependency.findByKmvId", query = "SELECT k FROM KMVInferenceEngineDependency k WHERE k.kmvId = :kmvId"), @NamedQuery(name = "KMVInferenceEngineDependency.findByTerminologyScheme", query = "SELECT k FROM KMVInferenceEngineDependency k WHERE k.terminologyScheme = :terminologyScheme"), @NamedQuery(name = "KMVInferenceEngineDependency.findByTerminologyCode", query = "SELECT k FROM KMVInferenceEngineDependency k WHERE k.terminologyCode = :terminologyCode"), @NamedQuery(name = "KMVInferenceEngineDependency.findByComments", query = "SELECT k FROM KMVInferenceEngineDependency k WHERE k.comments = :comments")})
public class KMVInferenceEngineDependency implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "KMV_ID")
   private Integer kmvId;
   @Basic(optional = false)
   @Column(name = "TerminologyScheme")
   private String terminologyScheme;
   @Basic(optional = false)
   @Column(name = "TerminologyCode")
   private String terminologyCode;
   @Column(name = "Comments")
   private String comments;
   @JoinColumn(name = "KMV_ID", referencedColumnName = "KMV_ID", insertable = false, updatable = false)
   @OneToOne(optional = false, fetch = FetchType.LAZY)
   private KMVersion kMVersion;

   public KMVInferenceEngineDependency() {
   }

   public KMVInferenceEngineDependency(Integer kmvId) {
      this.kmvId = kmvId;
   }

   public KMVInferenceEngineDependency(Integer kmvId, String terminologyScheme, String terminologyCode) {
      this.kmvId = kmvId;
      this.terminologyScheme = terminologyScheme;
      this.terminologyCode = terminologyCode;
   }

   public Integer getKmvId() {
      return kmvId;
   }

   public void setKmvId(Integer kmvId) {
      this.kmvId = kmvId;
   }

   public String getTerminologyScheme() {
      return terminologyScheme;
   }

   public void setTerminologyScheme(String terminologyScheme) {
      this.terminologyScheme = terminologyScheme;
   }

   public String getTerminologyCode() {
      return terminologyCode;
   }

   public void setTerminologyCode(String terminologyCode) {
      this.terminologyCode = terminologyCode;
   }

   public String getComments() {
      return comments;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public KMVersion getKMVersion() {
      return kMVersion;
   }

   public void setKMVersion(KMVersion kMVersion) {
      this.kMVersion = kMVersion;
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
      if (!(object instanceof KMVInferenceEngineDependency)) {
         return false;
      }
      KMVInferenceEngineDependency other = (KMVInferenceEngineDependency) object;
      if ((this.kmvId == null && other.kmvId != null) || (this.kmvId != null && !this.kmvId.equals(other.kmvId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVInferenceEngineDependency[kmvId=" + kmvId + "]";
   }

   // ======================================================
   //               USER DEFINEs
   // ======================================================

   /**
    * Constructor that builds from xml kmr object
    * @param kmr
    */
   public KMVInferenceEngineDependency(KMVInferenceEngineDependencyType kmr) {
      this.comments = kmr.getComments();
      this.terminologyScheme = kmr.getTerminologyScheme();
      this.terminologyCode = kmr.getTerminologyCode();
   }
   
   public KMVInferenceEngineDependencyType toKmTypes() {
      KMVInferenceEngineDependencyType inf = new KMVInferenceEngineDependencyType();

      inf.setComments(this.comments);
      inf.setTerminologyCode(this.getTerminologyCode());
      inf.setTerminologyScheme(this.getTerminologyScheme());

      return inf;
   }
}
