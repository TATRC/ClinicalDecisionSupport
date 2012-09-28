/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMVFactDependencyType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "KMV_FactDependency")
@NamedQueries({
   @NamedQuery(name = "KMVFactDependency.findAllDistinctType", query = "SELECT DISTINCT k.type FROM KMVFactDependency k"),
   @NamedQuery(name = "KMVFactDependency.findAllDistinctTypeByWildcard",
               query = "SELECT DISTINCT k.type FROM KMVFactDependency k WHERE UPPER(k.type) LIKE :type"),

   @NamedQuery(name = "KMVFactDependency.findAll", query = "SELECT k FROM KMVFactDependency k"),
   @NamedQuery(name = "KMVFactDependency.findByFdId", query = "SELECT k FROM KMVFactDependency k WHERE k.fdId = :fdId"),
   @NamedQuery(name = "KMVFactDependency.findByType", query = "SELECT k FROM KMVFactDependency k WHERE k.type = :type"),
   @NamedQuery(name = "KMVFactDependency.findByDescription", query = "SELECT k FROM KMVFactDependency k WHERE k.description = :description")
})
public class KMVFactDependency implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "FD_ID")
   private Integer fdId;
   @Basic(optional = false)
   @Column(name = "Type")
   private String type;
   @Column(name = "Description")
   private String description;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "kMVFactDependency", fetch = FetchType.LAZY)
   private Collection<FactSpecification> factSpecificationCollection;
   @JoinColumn(name = "KMV_ID", referencedColumnName = "KMV_ID")
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   private KMVersion kmvId;

   public KMVFactDependency() {
   }

   public KMVFactDependency(Integer fdId) {
      this.fdId = fdId;
   }

   public KMVFactDependency(Integer fdId, String type) {
      this.fdId = fdId;
      this.type = type;
   }

   public Integer getFdId() {
      return fdId;
   }

   public void setFdId(Integer fdId) {
      this.fdId = fdId;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Collection<FactSpecification> getFactSpecificationCollection() {
      return factSpecificationCollection;
   }

   public void setFactSpecificationCollection(Collection<FactSpecification> factSpecificationCollection) {
      this.factSpecificationCollection = factSpecificationCollection;
   }

   public KMVersion getKmvId() {
      return kmvId;
   }

   public void setKmvId(KMVersion kmvId) {
      this.kmvId = kmvId;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (fdId != null ? fdId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVFactDependency)) {
         return false;
      }
      KMVFactDependency other = (KMVFactDependency) object;
      if ((this.fdId == null && other.fdId != null) || (this.fdId != null && !this.fdId.equals(other.fdId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {

      String msg = "\nFACTDEP: "
              + "fdId=" + this.fdId
              + "type=" + this.type
              + "descr=" + this.description
              ;//+ "\nFACTSPEC SIZE=" + this.factSpecificationCollection.size();
/*THIS CAUSES STUCK in SEARCH
      if ((factSpecificationCollection != null) && (factSpecificationCollection.size() > 0)) {
         Iterator<FactSpecification> iter = this.factSpecificationCollection.iterator();
         while (iter.hasNext()) {
               msg = msg + iter.next().toString();
         }
      }
 */
               
      return msg;
   }

   // ======================================================
   //               USER DEFINEs
   // ======================================================

   /**
    * Constructor that builds from xml kmr object
    * @param kmr
    */
   public KMVFactDependency(KMVFactDependencyType kmr) {
      this.description = (kmr.getDescription());
      this.type = (kmr.getType());
   }

   /**
    * Converting a kmr KMV (KMVFactDependencyType) to a model KMV (KMVFactDependency)
    * @param kmr
    * @return
    */
   public KMVFactDependency fromKmTypes(KMVFactDependencyType kmr) {
      KMVFactDependency factDep = new KMVFactDependency();

      factDep.setDescription(kmr.getDescription());
      factDep.setType(kmr.getType());
      return factDep;
   }

   /**
    * toKmTypes() - Converts an incoming DAO type to KMTYPES.
    * @return KMVFactDependencyType
    */
   public gov.hhs.fha.nhinc.kmr.kmtypes.KMVFactDependencyType toKmTypes() {
      KMVFactDependencyType factDep = new KMVFactDependencyType();
      factDep.setType(this.type);
      return factDep;
   }
}
