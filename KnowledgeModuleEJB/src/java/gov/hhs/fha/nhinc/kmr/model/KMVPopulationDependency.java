/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMVPopulationDependencyType;
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
@Table(name = "KMV_PopulationDependency")
@NamedQueries({
    @NamedQuery(name = "KMVPopulationDependency.findAll", query = "SELECT k FROM KMVPopulationDependency k"),
    @NamedQuery(name = "KMVPopulationDependency.findByBpId", query = "SELECT k FROM KMVPopulationDependency k WHERE k.bpId = :bpId"),
    @NamedQuery(name = "KMVPopulationDependency.findByPDStatus", query = "SELECT k FROM KMVPopulationDependency k WHERE k.pDStatus = :pDStatus"),
    @NamedQuery(name = "KMVPopulationDependency.findByPDScope", query = "SELECT k FROM KMVPopulationDependency k WHERE k.pDScope = :pDScope"),

    @NamedQuery(name = "KMVPopulationDependency.findDistinctPDStatusByWildcard",
                query = "SELECT DISTINCT k.pDStatus FROM KMVPopulationDependency k WHERE UPPER(k.pDStatus) LIKE :pDStatus"),
    @NamedQuery(name = "KMVPopulationDependency.findDistinctPDScopeByWildcard",
                query = "SELECT DISTINCT k.pDScope FROM KMVPopulationDependency k WHERE UPPER(k.pDScope) LIKE :pDScope"),

    @NamedQuery(name = "KMVPopulationDependency.findByPDName", query = "SELECT k FROM KMVPopulationDependency k WHERE k.pDName = :pDName"),
    @NamedQuery(name = "KMVPopulationDependency.findByPDDescription", query = "SELECT k FROM KMVPopulationDependency k WHERE k.pDDescription = :pDDescription")
})
public class KMVPopulationDependency implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "BP_ID")
   private Integer bpId;
   @Column(name = "PD_Status")
   private String pDStatus;
   @Column(name = "PD_Scope")
   private String pDScope;
   @Column(name = "PD_Name")
   private String pDName;
   @Column(name = "PD_Description")
   private String pDDescription;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "kMVPopulationDependency", fetch = FetchType.LAZY)
   private Collection<PopulationSpecification> populationSpecificationCollection;
   @JoinColumn(name = "KMV_ID", referencedColumnName = "KMV_ID")
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   private KMVersion kmvId;

   public KMVPopulationDependency() {
   }

   public KMVPopulationDependency(Integer bpId) {
      this.bpId = bpId;
   }

   public Integer getBpId() {
      return bpId;
   }

   public void setBpId(Integer bpId) {
      this.bpId = bpId;
   }

   public String getPDStatus() {
      return pDStatus;
   }

   public void setPDStatus(String pDStatus) {
      this.pDStatus = pDStatus;
   }

   public String getPDScope() {
      return pDScope;
   }

   public void setPDScope(String pDScope) {
      this.pDScope = pDScope;
   }

   public String getPDName() {
      return pDName;
   }

   public void setPDName(String pDName) {
      this.pDName = pDName;
   }

   public String getPDDescription() {
      return pDDescription;
   }

   public void setPDDescription(String pDDescription) {
      this.pDDescription = pDDescription;
   }

   public Collection<PopulationSpecification> getPopulationSpecificationCollection() {
      return populationSpecificationCollection;
   }

   public void setPopulationSpecificationCollection(Collection<PopulationSpecification> populationSpecificationCollection) {
      this.populationSpecificationCollection = populationSpecificationCollection;
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
      hash += (bpId != null ? bpId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVPopulationDependency)) {
         return false;
      }
      KMVPopulationDependency other = (KMVPopulationDependency) object;
      if ((this.bpId == null && other.bpId != null) || (this.bpId != null && !this.bpId.equals(other.bpId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVPopulationDependency[bpId=" + bpId + "]";
   }

   // ======================================================
   //               USER DEFINEs
   // ======================================================
   
   /**
    * Constructor that builds from xml kmr object
    * @param kmr
    */
   public KMVPopulationDependency(KMVPopulationDependencyType kmr) {
      this.pDDescription = (kmr.getPDDescription());
      this.pDName = kmr.getPDName();
      this.pDScope = kmr.getPDScope();
      this.pDStatus = kmr.getPDStatus();
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.KMVPopulationDependencyType toKmTypes() {
      KMVPopulationDependencyType popDep = new KMVPopulationDependencyType();

      popDep.setPDStatus(this.pDStatus);
      popDep.setPDScope(this.pDScope);
      popDep.setPDName(this.pDName);
      popDep.setPDDescription(this.pDDescription);

      return popDep;
   }
}
