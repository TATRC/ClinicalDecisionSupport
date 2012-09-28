/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "ref_fact_scheme_type")
@NamedQueries({
   @NamedQuery(name = "RefFactSchemeType.findAll", query = "SELECT r FROM RefFactSchemeType r"),
   @NamedQuery(name = "RefFactSchemeType.findBySchemetypeid", query = "SELECT r FROM RefFactSchemeType r WHERE r.schemetypeid = :schemetypeid"),
   @NamedQuery(name = "RefFactSchemeType.findByFacttypeid", query = "SELECT r FROM RefFactSchemeType r WHERE r.facttypeid = :facttypeid"),
   @NamedQuery(name = "RefFactSchemeType.findByFactschemeid", query = "SELECT r FROM RefFactSchemeType r WHERE r.factschemeid = :factschemeid"),
   @NamedQuery(name = "RefFactSchemeType.findByDescr", query = "SELECT r FROM RefFactSchemeType r WHERE r.descr = :descr")
})
public class RefFactSchemeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "schemetypeid")
   private Integer schemetypeid;
   @Column(name = "descr")
   private String descr;
   @JoinColumn(name = "factschemeid", referencedColumnName = "factschemeid")
   @ManyToOne(fetch = FetchType.EAGER)
   private RefFactScheme factschemeid;
   @JoinColumn(name = "facttypeid", referencedColumnName = "facttypeid")
   @ManyToOne(fetch = FetchType.EAGER)
   private RefFactType facttypeid;

   public RefFactSchemeType() {
   }

   public RefFactSchemeType(Integer schemetypeid) {
      this.schemetypeid = schemetypeid;
   }

   public Integer getSchemetypeid() {
      return schemetypeid;
   }

   public void setSchemetypeid(Integer schemetypeid) {
      this.schemetypeid = schemetypeid;
   }

   public String getDescr() {
      return descr;
   }

   public void setDescr(String descr) {
      this.descr = descr;
   }

   public RefFactScheme getFactschemeid() {
      return factschemeid;
   }

   public void setFactschemeid(RefFactScheme factschemeid) {
      this.factschemeid = factschemeid;
   }

   public RefFactType getFacttypeid() {
      return facttypeid;
   }

   public void setFacttypeid(RefFactType facttypeid) {
      this.facttypeid = facttypeid;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (schemetypeid != null ? schemetypeid.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefFactSchemeType)) {
         return false;
      }
      RefFactSchemeType other = (RefFactSchemeType) object;
      if ((this.schemetypeid == null && other.schemetypeid != null) || (this.schemetypeid != null && !this.schemetypeid.equals(other.schemetypeid))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefFactSchemeType[schemetypeid=" + schemetypeid + "]";
   }

}
