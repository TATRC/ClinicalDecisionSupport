/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.FactSpecificationType;
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
@Table(name = "FactSpecification")
@NamedQueries({
   @NamedQuery(name = "FactSpecification.findDistinctTerminologyScheme", query = "SELECT DISTINCT f.factSpecificationPK.terminologyScheme FROM FactSpecification f"), 
   @NamedQuery(name = "FactSpecification.findDistinctTerminologyCode", query = "SELECT DISTINCT f.factSpecificationPK.terminologyCode FROM FactSpecification f"), 
   @NamedQuery(name = "FactSpecification.findAll", query = "SELECT f FROM FactSpecification f"),
   @NamedQuery(name = "FactSpecification.findByFdId", query = "SELECT f FROM FactSpecification f WHERE f.factSpecificationPK.fdId = :fdId"),
   @NamedQuery(name = "FactSpecification.findByTerminologyScheme", query = "SELECT f FROM FactSpecification f WHERE f.factSpecificationPK.terminologyScheme = :terminologyScheme"),
   @NamedQuery(name = "FactSpecification.findByTerminologyCode", query = "SELECT f FROM FactSpecification f WHERE f.factSpecificationPK.terminologyCode = :terminologyCode"),
   @NamedQuery(name = "FactSpecification.findByComments", query = "SELECT f FROM FactSpecification f WHERE f.comments = :comments")
})
public class FactSpecification implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected FactSpecificationPK factSpecificationPK;
   @Column(name = "Comments")
   private String comments;
   @JoinColumn(name = "FD_ID", referencedColumnName = "FD_ID", insertable = false, updatable = false)
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   private KMVFactDependency kMVFactDependency;

   public FactSpecification() {
   }

   public FactSpecification(FactSpecificationPK factSpecificationPK) {
      this.factSpecificationPK = factSpecificationPK;
   }

   public FactSpecification(int fdId, String terminologyScheme, String terminologyCode) {
      this.factSpecificationPK = new FactSpecificationPK(fdId, terminologyScheme, terminologyCode);
   }

   public FactSpecificationPK getFactSpecificationPK() {
      return factSpecificationPK;
   }

   public void setFactSpecificationPK(FactSpecificationPK factSpecificationPK) {
      this.factSpecificationPK = factSpecificationPK;
   }

   public String getComments() {
      return comments;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public KMVFactDependency getKMVFactDependency() {
      return kMVFactDependency;
   }

   public void setKMVFactDependency(KMVFactDependency kMVFactDependency) {
      this.kMVFactDependency = kMVFactDependency;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (factSpecificationPK != null ? factSpecificationPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof FactSpecification)) {
         return false;
      }
      FactSpecification other = (FactSpecification) object;
      if ((this.factSpecificationPK == null && other.factSpecificationPK != null) || (this.factSpecificationPK != null && !this.factSpecificationPK.equals(other.factSpecificationPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "\nFACTSPEC: "
              +"\t"+ this.factSpecificationPK.getTerminologyScheme()
              +"\t"+ this.factSpecificationPK.getTerminologyCode()
              +"\t"+ this.comments;
   }
   
   // ======================================================
   //               USER DEFINEs
   // ======================================================

   public FactSpecification(FactSpecificationType kmr) {
      this.comments = (kmr.getComments());
      FactSpecificationPK fsPK = new FactSpecificationPK();
      fsPK.setTerminologyScheme(kmr.getTerminologyScheme());
      fsPK.setTerminologyCode(kmr.getTerminologyCode());      
      this.factSpecificationPK = fsPK;      
   }

   /**
    * Converting a kmr KMV (KMVFactDependencyType) to a model KMV (KMVFactDependency)
    * @param kmr
    * @return
    */
   public FactSpecification fromKmTypes(FactSpecificationType kmr) {
      FactSpecification factspec = new FactSpecification();

      factspec.setComments(kmr.getComments());
      FactSpecificationPK fsPK = new FactSpecificationPK();
      fsPK.setTerminologyScheme(kmr.getTerminologyScheme());
      fsPK.setTerminologyCode(kmr.getTerminologyCode());
      factspec.setFactSpecificationPK(fsPK);
      return factspec;
   }

   public FactSpecificationType toKmTypes() {
      FactSpecificationType factSpec = new FactSpecificationType();

      factSpec.setComments(this.comments);
      factSpec.setTerminologyCode(this.factSpecificationPK.getTerminologyCode());
      factSpec.setTerminologyScheme(this.factSpecificationPK.getTerminologyScheme());

      return factSpec;
   }
}
