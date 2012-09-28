/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMSpecialtyType;
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
@Table(name = "KM_Specialty")
@NamedQueries({
   @NamedQuery(name = "KMSpecialty.findAllDistinctScheme",
               query = "SELECT DISTINCT k.kMSpecialtyPK.terminologyScheme FROM KMSpecialty k"),
   @NamedQuery(name = "KMSpecialty.findAllDistinctSchemeByWildcard",
               query = "SELECT DISTINCT k.kMSpecialtyPK.terminologyScheme FROM KMSpecialty k " +
                       "WHERE UPPER(k.kMSpecialtyPK.terminologyScheme) LIKE :terminologyScheme"),

   @NamedQuery(name = "KMSpecialty.findAllDistinctCode",
               query = "SELECT DISTINCT k.kMSpecialtyPK.terminologyCode FROM KMSpecialty k"),

   @NamedQuery(name = "KMSpecialty.findAllDistinctCodeByWildcardCode",
               query = "SELECT DISTINCT k.kMSpecialtyPK.terminologyCode FROM KMSpecialty k " +
                        "WHERE UPPER(k.kMSpecialtyPK.terminologyCode) LIKE :terminologyCode"),

   @NamedQuery(name = "KMSpecialty.findAllDistinctCodeByWildcardScheme",
               query = "SELECT DISTINCT k.kMSpecialtyPK.terminologyCode FROM KMSpecialty k " +
                        "WHERE UPPER(k.kMSpecialtyPK.terminologyScheme) LIKE :terminologyScheme"),

   @NamedQuery(name = "KMSpecialty.findAllDistinctCodeByWildcardSchemeAndCode",
               query = "SELECT DISTINCT k.kMSpecialtyPK.terminologyCode FROM KMSpecialty k " +
                        "WHERE UPPER(k.kMSpecialtyPK.terminologyScheme) LIKE :terminologyScheme " +
                        "AND UPPER(k.kMSpecialtyPK.terminologyCode) LIKE :terminologyCode"),

   @NamedQuery(name = "KMSpecialty.findExistSchemeCodeCombo", 
               query = "SELECT k FROM KMSpecialty k " +
                        "WHERE k.kMSpecialtyPK.kmId = :kmId "+
                        "AND UPPER(k.kMSpecialtyPK.terminologyScheme) = :terminologyScheme "+
                        "AND UPPER(k.kMSpecialtyPK.terminologyCode) = :terminologyCode"),



   @NamedQuery(name = "KMSpecialty.findAll", query = "SELECT k FROM KMSpecialty k"),
   @NamedQuery(name = "KMSpecialty.findByKmId", query = "SELECT k FROM KMSpecialty k WHERE k.kMSpecialtyPK.kmId = :kmId"),
   @NamedQuery(name = "KMSpecialty.findByTerminologyScheme", query = "SELECT k FROM KMSpecialty k WHERE k.kMSpecialtyPK.terminologyScheme = :terminologyScheme"),
   @NamedQuery(name = "KMSpecialty.findByTerminologyCode", query = "SELECT k FROM KMSpecialty k WHERE k.kMSpecialtyPK.terminologyCode = :terminologyCode"),
   @NamedQuery(name = "KMSpecialty.findByComments", query = "SELECT k FROM KMSpecialty k WHERE k.comments = :comments")
})
public class KMSpecialty implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected KMSpecialtyPK kMSpecialtyPK;
   @Column(name = "Comments")
   private String comments;
   @JoinColumn(name = "KM_ID", referencedColumnName = "KM_ID", insertable = false, updatable = false)
   @ManyToOne(fetch = FetchType.EAGER) //(optional = false, fetch = FetchType.EAGER)
   private KnowledgeModule knowledgeModule;

   public KMSpecialty() {
   }

   public KMSpecialty(KMSpecialtyPK kMSpecialtyPK) {
      this.kMSpecialtyPK = kMSpecialtyPK;
   }

   public KMSpecialty(int kmId, String terminologyScheme, String terminologyCode) {
      this.kMSpecialtyPK = new KMSpecialtyPK(kmId, terminologyScheme, terminologyCode);
   }

   public KMSpecialtyPK getKMSpecialtyPK() {
      return kMSpecialtyPK;
   }

   public void setKMSpecialtyPK(KMSpecialtyPK kMSpecialtyPK) {
      this.kMSpecialtyPK = kMSpecialtyPK;
   }

   public String getComments() {
      return comments;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public KnowledgeModule getKnowledgeModule() {
      return knowledgeModule;
   }

   public void setKnowledgeModule(KnowledgeModule knowledgeModule) {
      this.knowledgeModule = knowledgeModule;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (kMSpecialtyPK != null ? kMSpecialtyPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMSpecialty)) {
         return false;
      }
      KMSpecialty other = (KMSpecialty) object;
      if ((this.kMSpecialtyPK == null && other.kMSpecialtyPK != null) || (this.kMSpecialtyPK != null && !this.kMSpecialtyPK.equals(other.kMSpecialtyPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMSpecialty[kMSpecialtyPK=" + kMSpecialtyPK + "]";
   }
   
   // ======================================================
   //               USER DEFINEs
   // ======================================================
   
   /**
    * Constructor that builds from xml kmr object
    * @param kmr
    */
   public KMSpecialty (KMSpecialtyType kmr) {
      this.comments = kmr.getComments();
      KMSpecialtyPK pk = new KMSpecialtyPK();
      pk.setTerminologyScheme(kmr.getTerminologyScheme());
      pk.setTerminologyCode(kmr.getTerminologyCode());
      this.kMSpecialtyPK = pk;
   }

   /**
    * 
    * @return KMSpecialtyType
    */
   public KMSpecialtyType toKmTypes() {
      KMSpecialtyType sp = new KMSpecialtyType();

      sp.setComments(this.comments);
      sp.setTerminologyCode(this.kMSpecialtyPK.getTerminologyCode());
      sp.setTerminologyScheme(this.kMSpecialtyPK.getTerminologyScheme());

      return sp;
   }
}
