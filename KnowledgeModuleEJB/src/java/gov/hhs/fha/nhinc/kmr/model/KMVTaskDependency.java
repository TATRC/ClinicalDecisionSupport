/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMVTaskDependencyType;
import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "KMV_TaskDependency")
@NamedQueries({
   @NamedQuery(name = "KMVTaskDependency.findAllDistinctType", query = "SELECT DISTINCT k.tDType FROM KMVTaskDependency k"),
   @NamedQuery(name = "KMVTaskDependency.findAllDistinctTypeByWildcard",
               query = "SELECT DISTINCT k.tDType FROM KMVTaskDependency k WHERE k.tDType LIKE :tDType"),

   @NamedQuery(name = "KMVTaskDependency.findAll", query = "SELECT k FROM KMVTaskDependency k"),
   @NamedQuery(name = "KMVTaskDependency.findByTtId", query = "SELECT k FROM KMVTaskDependency k WHERE k.ttId = :ttId"),
   @NamedQuery(name = "KMVTaskDependency.findByTDType", query = "SELECT k FROM KMVTaskDependency k WHERE k.tDType = :tDType")
})
public class KMVTaskDependency implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "TT_ID")
   private Integer ttId;
   @Basic(optional = false)
   @Column(name = "TD_Type")
   private String tDType;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "kMVTaskDependency", fetch = FetchType.LAZY)
   private Collection<TTSpecification> tTSpecificationCollection;
   @JoinColumn(name = "KMV_ID", referencedColumnName = "KMV_ID")
   @ManyToOne(fetch = FetchType.LAZY)
   private KMVersion kmvId;

   public KMVTaskDependency() {
   }

   public KMVTaskDependency(Integer ttId) {
      this.ttId = ttId;
   }

   public KMVTaskDependency(Integer ttId, String tDType) {
      this.ttId = ttId;
      this.tDType = tDType;
   }

   public Integer getTtId() {
      return ttId;
   }

   public void setTtId(Integer ttId) {
      this.ttId = ttId;
   }

   public String getTDType() {
      return tDType;
   }

   public void setTDType(String tDType) {
      this.tDType = tDType;
   }

   public Collection<TTSpecification> getTTSpecificationCollection() {
      return tTSpecificationCollection;
   }

   public void setTTSpecificationCollection(Collection<TTSpecification> tTSpecificationCollection) {
      this.tTSpecificationCollection = tTSpecificationCollection;
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
      hash += (ttId != null ? ttId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVTaskDependency)) {
         return false;
      }
      KMVTaskDependency other = (KMVTaskDependency) object;
      if ((this.ttId == null && other.ttId != null) || (this.ttId != null && !this.ttId.equals(other.ttId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVTaskDependency[ttId=" + ttId + "]";
   }
   
   // ======================================================
   //               USER DEFINEs
   // ======================================================

   /**
    * Constructor that builds from xml kmr object
    * @param kmr
    */
   public KMVTaskDependency(KMVTaskDependencyType kmr) {
      this.tDType = kmr.getTDType();
   }
   
   public gov.hhs.fha.nhinc.kmr.kmtypes.KMVTaskDependencyType toKmTypes() {
      KMVTaskDependencyType taskDep = new KMVTaskDependencyType();

      taskDep.setTDType(this.tDType);

      return taskDep;
   }
   


}
