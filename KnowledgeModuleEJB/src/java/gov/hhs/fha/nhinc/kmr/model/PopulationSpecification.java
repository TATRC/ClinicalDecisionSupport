/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.PopulationSpecificationType;
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
@Table(name = "PopulationSpecification")
@NamedQueries({
//   @NamedQuery(name = "PopulationSpecification.findAllDistinctScheme",
//               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyScheme FROM PopulationSpecification p"),
   @NamedQuery(name = "PopulationSpecification.findAllDistinctSchemeByWildcard",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyScheme FROM PopulationSpecification p " +
                       "WHERE UPPER(p.populationSpecificationPK.terminologyScheme) LIKE :terminologyScheme"),

   @NamedQuery(name = "PopulationSpecification.findAllDistinctTypeByWildcard", 
               query = "SELECT DISTINCT p.type FROM PopulationSpecification p WHERE UPPER(p.type) LIKE :type"),

   @NamedQuery(name = "PopulationSpecification.findAllDistinctCode",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyCode FROM PopulationSpecification p"),
   @NamedQuery(name = "PopulationSpecification.findAllDistinctCodeByWildcardCode",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyCode " +
               "FROM PopulationSpecification p WHERE UPPER(p.populationSpecificationPK.terminologyCode) LIKE :terminologyCode"),
   @NamedQuery(name = "PopulationSpecification.findAllDistinctCodeByWildcardScheme",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyCode " +
               "FROM PopulationSpecification p WHERE UPPER(p.populationSpecificationPK.terminologyScheme) LIKE :terminologyScheme"),
   @NamedQuery(name = "PopulationSpecification.findAllDistinctCodeByWildcardSchemeAndCode",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyCode " +
               "FROM PopulationSpecification p WHERE UPPER(p.populationSpecificationPK.terminologyScheme) LIKE :terminologyScheme " +
               "AND p.populationSpecificationPK.terminologyCode = :terminologyCode"),

   @NamedQuery(name = "PopulationSpecification.findAllDistinctValue",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyValue FROM PopulationSpecification p"),
   @NamedQuery(name = "PopulationSpecification.findAllDistinctValueByWildcardValue",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyValue " +
               "FROM PopulationSpecification p WHERE UPPER(p.populationSpecificationPK.terminologyValue) LIKE :terminologyValue"),
   @NamedQuery(name = "PopulationSpecification.findAllDistinctValueByWildcardScheme",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyValue " +
               "FROM PopulationSpecification p WHERE UPPER(p.populationSpecificationPK.terminologyScheme) LIKE :terminologyScheme"),

   @NamedQuery(name = "PopulationSpecification.findAllDistinctValueByTypeAndWildcardValue",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyValue " +
               "FROM PopulationSpecification p WHERE p.type = :type " +
               "AND UPPER(p.populationSpecificationPK.terminologyValue) LIKE :terminologyValue"),

   @NamedQuery(name = "PopulationSpecification.findAllDistinctCodeByTypeAndWildcardCode",
               query = "SELECT DISTINCT p.populationSpecificationPK.terminologyCode " +
               "FROM PopulationSpecification p WHERE p.type = :type " +
               "AND UPPER(p.populationSpecificationPK.terminologyCode) LIKE :terminologyCode"),

   @NamedQuery(name = "PopulationSpecification.findAll", query = "SELECT p FROM PopulationSpecification p"),
   @NamedQuery(name = "PopulationSpecification.findByBpId", query = "SELECT p FROM PopulationSpecification p WHERE p.populationSpecificationPK.bpId = :bpId"),
   @NamedQuery(name = "PopulationSpecification.findByTerminologyScheme", query = "SELECT p FROM PopulationSpecification p WHERE p.populationSpecificationPK.terminologyScheme = :terminologyScheme"),
   @NamedQuery(name = "PopulationSpecification.findByTerminologyCode", query = "SELECT p FROM PopulationSpecification p WHERE p.populationSpecificationPK.terminologyCode = :terminologyCode"),
   @NamedQuery(name = "PopulationSpecification.findByTerminologyValue", query = "SELECT p FROM PopulationSpecification p WHERE p.populationSpecificationPK.terminologyValue = :terminologyValue"),
   @NamedQuery(name = "PopulationSpecification.findByType", query = "SELECT p FROM PopulationSpecification p WHERE p.type = :type")
})
public class PopulationSpecification implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected PopulationSpecificationPK populationSpecificationPK;
   @Column(name = "Type")
   private String type;
   @JoinColumn(name = "BP_ID", referencedColumnName = "BP_ID", insertable = false, updatable = false)
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   private KMVPopulationDependency kMVPopulationDependency;

   public PopulationSpecification() {
   }

   public PopulationSpecification(PopulationSpecificationPK populationSpecificationPK) {
      this.populationSpecificationPK = populationSpecificationPK;
   }

   public PopulationSpecification(int bpId, String terminologyScheme, String terminologyCode, String terminologyValue) {
      this.populationSpecificationPK = new PopulationSpecificationPK(bpId, terminologyScheme, terminologyCode, terminologyValue);
   }

   public PopulationSpecificationPK getPopulationSpecificationPK() {
      return populationSpecificationPK;
   }

   public void setPopulationSpecificationPK(PopulationSpecificationPK populationSpecificationPK) {
      this.populationSpecificationPK = populationSpecificationPK;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public KMVPopulationDependency getKMVPopulationDependency() {
      return kMVPopulationDependency;
   }

   public void setKMVPopulationDependency(KMVPopulationDependency kMVPopulationDependency) {
      this.kMVPopulationDependency = kMVPopulationDependency;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (populationSpecificationPK != null ? populationSpecificationPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof PopulationSpecification)) {
         return false;
      }
      PopulationSpecification other = (PopulationSpecification) object;
      if ((this.populationSpecificationPK == null && other.populationSpecificationPK != null) || (this.populationSpecificationPK != null && !this.populationSpecificationPK.equals(other.populationSpecificationPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.PopulationSpecification[populationSpecificationPK=" + populationSpecificationPK + "]";
   }

   // ======================================================
   //               USER DEFINEs
   // ======================================================

   /**
    * Constructor that builds from xml kmr object
    * @param kmr
    */
   public PopulationSpecification(PopulationSpecificationType kmr) {
      this.type = kmr.getType();
      PopulationSpecificationPK pk = new PopulationSpecificationPK();
      pk.setTerminologyScheme(kmr.getTerminologyScheme());
      pk.setTerminologyCode(kmr.getTerminologyCode());
      pk.setTerminologyValue(kmr.getTerminologyValue());
      this.populationSpecificationPK = pk;
   }

   /**
    * 
    * @return
    */
   public PopulationSpecificationType toKmTypes() {

      PopulationSpecificationType psType = new PopulationSpecificationType();
      
      psType.setTerminologyCode(this.populationSpecificationPK.getTerminologyCode());
      psType.setTerminologyScheme(this.populationSpecificationPK.getTerminologyScheme());
      psType.setTerminologyValue(this.getPopulationSpecificationPK().getTerminologyValue());
      psType.setType(this.type);

      return psType;
      
   }
}
