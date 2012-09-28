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
@Table(name = "ref_fact_scheme")
@NamedQueries({
   @NamedQuery(name = "RefFactScheme.findAll", query = "SELECT r FROM RefFactScheme r"),
   @NamedQuery(name = "RefFactScheme.findByFactschemeid", query = "SELECT r FROM RefFactScheme r WHERE r.factschemeid = :factschemeid"),
   @NamedQuery(name = "RefFactScheme.findBySchemename", query = "SELECT r FROM RefFactScheme r WHERE r.schemename = :schemename"),
   @NamedQuery(name = "RefFactScheme.findByDescr", query = "SELECT r FROM RefFactScheme r WHERE r.descr = :descr")
})
public class RefFactScheme implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "factschemeid")
   private Integer factschemeid;
   @Basic(optional = false)
   @Column(name = "schemename")
   private String schemename;
   @Column(name = "descr")
   private String descr;
   @OneToMany(mappedBy = "factschemeid", fetch = FetchType.EAGER)
   private Collection<RefFactSchemeType> refFactSchemeTypeCollection;

   public RefFactScheme() {
   }

   public RefFactScheme(Integer factschemeid) {
      this.factschemeid = factschemeid;
   }

   public RefFactScheme(Integer factschemeid, String schemename) {
      this.factschemeid = factschemeid;
      this.schemename = schemename;
   }

   public Integer getFactschemeid() {
      return factschemeid;
   }

   public void setFactschemeid(Integer factschemeid) {
      this.factschemeid = factschemeid;
   }

   public String getSchemename() {
      return schemename;
   }

   public void setSchemename(String schemename) {
      this.schemename = schemename;
   }

   public String getDescr() {
      return descr;
   }

   public void setDescr(String descr) {
      this.descr = descr;
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
      hash += (factschemeid != null ? factschemeid.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefFactScheme)) {
         return false;
      }
      RefFactScheme other = (RefFactScheme) object;
      if ((this.factschemeid == null && other.factschemeid != null) || (this.factschemeid != null && !this.factschemeid.equals(other.factschemeid))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefFactScheme[factschemeid=" + factschemeid + "]";
   }

}
