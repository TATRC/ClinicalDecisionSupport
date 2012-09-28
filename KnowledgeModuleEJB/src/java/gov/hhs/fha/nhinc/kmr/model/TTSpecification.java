/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.TTSpecificationType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "TT_Specification")
@NamedQueries({
   @NamedQuery(name = "TTSpecification.findAll", query = "SELECT t FROM TTSpecification t"),
   @NamedQuery(name = "TTSpecification.findByTtId", query = "SELECT t FROM TTSpecification t WHERE t.tTSpecificationPK.ttId = :ttId"),
   @NamedQuery(name = "TTSpecification.findByTerminologyScheme", query = "SELECT t FROM TTSpecification t WHERE t.tTSpecificationPK.terminologyScheme = :terminologyScheme"),
   @NamedQuery(name = "TTSpecification.findByTerminologyCode", query = "SELECT t FROM TTSpecification t WHERE t.tTSpecificationPK.terminologyCode = :terminologyCode"),
   @NamedQuery(name = "TTSpecification.findByComments", query = "SELECT t FROM TTSpecification t WHERE t.comments = :comments")
})
public class TTSpecification implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected TTSpecificationPK tTSpecificationPK;
   @Column(name = "Comments")
   private String comments;
   @JoinColumn(name = "TT_ID", referencedColumnName = "TT_ID", insertable = false, updatable = false)
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   private KMVTaskDependency kMVTaskDependency;

   public TTSpecification() {
   }

   public TTSpecification(TTSpecificationPK tTSpecificationPK) {
      this.tTSpecificationPK = tTSpecificationPK;
   }

   public TTSpecification(int ttId, String terminologyScheme, String terminologyCode) {
      this.tTSpecificationPK = new TTSpecificationPK(ttId, terminologyScheme, terminologyCode);
   }

   public TTSpecificationPK getTTSpecificationPK() {
      return tTSpecificationPK;
   }

   public void setTTSpecificationPK(TTSpecificationPK tTSpecificationPK) {
      this.tTSpecificationPK = tTSpecificationPK;
   }

   public String getComments() {
      return comments;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public KMVTaskDependency getKMVTaskDependency() {
      return kMVTaskDependency;
   }

   public void setKMVTaskDependency(KMVTaskDependency kMVTaskDependency) {
      this.kMVTaskDependency = kMVTaskDependency;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (tTSpecificationPK != null ? tTSpecificationPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof TTSpecification)) {
         return false;
      }
      TTSpecification other = (TTSpecification) object;
      if ((this.tTSpecificationPK == null && other.tTSpecificationPK != null) || (this.tTSpecificationPK != null && !this.tTSpecificationPK.equals(other.tTSpecificationPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.TTSpecification[tTSpecificationPK=" + tTSpecificationPK + "]";
   }

   // ======================================================
   //               USER DEFINEs
   // ======================================================

   /**
    * Constructor that builds from xml kmr object
    * @param kmr
    */
   public TTSpecification(TTSpecificationType kmr) {
      TTSpecificationPK pk = new TTSpecificationPK();
      pk.setTerminologyScheme(kmr.getTerminologyScheme());
      pk.setTerminologyCode(kmr.getTerminologyCode());
      this.tTSpecificationPK = pk;
   }

   public TTSpecificationType toKmTypes() {
      TTSpecificationType taskSpec = new TTSpecificationType();

      taskSpec.setComments(this.comments);
      taskSpec.setTerminologyCode(this.tTSpecificationPK.getTerminologyCode());
      taskSpec.setTerminologyScheme(this.tTSpecificationPK.getTerminologyScheme());

      return taskSpec;
   }

}
