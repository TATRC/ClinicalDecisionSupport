/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "ref_fact_type")
@NamedQueries({
   @NamedQuery(name = "RefFactType.findAll", query = "SELECT r FROM RefFactType r"),
   @NamedQuery(name = "RefFactType.findAllByWildcard", query = "SELECT r FROM RefFactType r WHERE r.facttype LIKE :facttype"),
   @NamedQuery(name = "RefFactType.findByFacttypeid", query = "SELECT r FROM RefFactType r WHERE r.facttypeid = :facttypeid"),
   @NamedQuery(name = "RefFactType.findByFacttype", query = "SELECT r FROM RefFactType r WHERE r.facttype = :facttype")
})
public class RefFactType implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "facttypeid")
   private Integer facttypeid;
   @Basic(optional = false)
   @Column(name = "facttype")
   private String facttype;
   @OneToMany(mappedBy = "facttypeid", fetch = FetchType.EAGER)
   private Collection<RefFactSchemeType> refFactSchemeTypeCollection;

   public RefFactType() {
   }

   public RefFactType(Integer facttypeid) {
      this.facttypeid = facttypeid;
   }

   public RefFactType(Integer facttypeid, String facttype) {
      this.facttypeid = facttypeid;
      this.facttype = facttype;
   }

   public Integer getFacttypeid() {
      return facttypeid;
   }

   public void setFacttypeid(Integer facttypeid) {
      this.facttypeid = facttypeid;
   }

   public String getFacttype() {
      return facttype;
   }

   public void setFacttype(String facttype) {
      this.facttype = facttype;
   }

   public Collection<RefFactSchemeType> getRefFactSchemeTypeCollection() {
      return refFactSchemeTypeCollection;
   }

   public void setRefFactSchemeTypeCollection(Collection<RefFactSchemeType> refFactSchemeTypeCollection) {
      this.refFactSchemeTypeCollection = refFactSchemeTypeCollection;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (facttypeid != null ? facttypeid.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefFactType)) {
         return false;
      }
      RefFactType other = (RefFactType) object;
      if ((this.facttypeid == null && other.facttypeid != null) || (this.facttypeid != null && !this.facttypeid.equals(other.facttypeid))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefFactType[facttypeid=" + facttypeid + "]";
   }

}
