/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "KMV_SupportingReference")
@NamedQueries({@NamedQuery(name = "KMVSupportingReference.findAll", query = "SELECT k FROM KMVSupportingReference k"), @NamedQuery(name = "KMVSupportingReference.findByKmvId", query = "SELECT k FROM KMVSupportingReference k WHERE k.kmvId = :kmvId"), @NamedQuery(name = "KMVSupportingReference.findBySrId", query = "SELECT k FROM KMVSupportingReference k WHERE k.srId = :srId"), @NamedQuery(name = "KMVSupportingReference.findBySRName", query = "SELECT k FROM KMVSupportingReference k WHERE k.sRName = :sRName"), @NamedQuery(name = "KMVSupportingReference.findBySRType", query = "SELECT k FROM KMVSupportingReference k WHERE k.sRType = :sRType"), @NamedQuery(name = "KMVSupportingReference.findBySRDescription", query = "SELECT k FROM KMVSupportingReference k WHERE k.sRDescription = :sRDescription"), @NamedQuery(name = "KMVSupportingReference.findBySRReference", query = "SELECT k FROM KMVSupportingReference k WHERE k.sRReference = :sRReference")})
public class KMVSupportingReference implements Serializable {
   private static final long serialVersionUID = 1L;
   @Basic(optional = false)
   @Column(name = "KMV_ID")
   private int kmvId;
   @Id
   @Basic(optional = false)
   @Column(name = "SR_ID")
   private Integer srId;
   @Column(name = "SR_Name")
   private String sRName;
   @Column(name = "SR_Type")
   private String sRType;
   @Column(name = "SR_Description")
   private String sRDescription;
   @Column(name = "SR_Reference")
   private String sRReference;
   @Lob
   @Column(name = "SR_Document")
   private byte[] sRDocument;

   public KMVSupportingReference() {
   }

   public KMVSupportingReference(Integer srId) {
      this.srId = srId;
   }

   public KMVSupportingReference(Integer srId, int kmvId) {
      this.srId = srId;
      this.kmvId = kmvId;
   }

   public int getKmvId() {
      return kmvId;
   }

   public void setKmvId(int kmvId) {
      this.kmvId = kmvId;
   }

   public Integer getSrId() {
      return srId;
   }

   public void setSrId(Integer srId) {
      this.srId = srId;
   }

   public String getSRName() {
      return sRName;
   }

   public void setSRName(String sRName) {
      this.sRName = sRName;
   }

   public String getSRType() {
      return sRType;
   }

   public void setSRType(String sRType) {
      this.sRType = sRType;
   }

   public String getSRDescription() {
      return sRDescription;
   }

   public void setSRDescription(String sRDescription) {
      this.sRDescription = sRDescription;
   }

   public String getSRReference() {
      return sRReference;
   }

   public void setSRReference(String sRReference) {
      this.sRReference = sRReference;
   }

   public byte[] getSRDocument() {
      return sRDocument;
   }

   public void setSRDocument(byte[] sRDocument) {
      this.sRDocument = sRDocument;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (srId != null ? srId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVSupportingReference)) {
         return false;
      }
      KMVSupportingReference other = (KMVSupportingReference) object;
      if ((this.srId == null && other.srId != null) || (this.srId != null && !this.srId.equals(other.srId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVSupportingReference[srId=" + srId + "]";
   }
   
   // ======================================================
   //               USER DEFINEs
   // ======================================================


}
