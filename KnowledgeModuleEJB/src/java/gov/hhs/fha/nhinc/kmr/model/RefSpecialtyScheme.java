/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "ref_specialty_scheme")
@NamedQueries({@NamedQuery(name = "RefSpecialtyScheme.findAll", query = "SELECT r FROM RefSpecialtyScheme r"), @NamedQuery(name = "RefSpecialtyScheme.findBySpecialtyschemeid", query = "SELECT r FROM RefSpecialtyScheme r WHERE r.specialtyschemeid = :specialtyschemeid"), @NamedQuery(name = "RefSpecialtyScheme.findBySchemename", query = "SELECT r FROM RefSpecialtyScheme r WHERE r.schemename = :schemename"), @NamedQuery(name = "RefSpecialtyScheme.findByDescr", query = "SELECT r FROM RefSpecialtyScheme r WHERE r.descr = :descr")})
public class RefSpecialtyScheme implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "specialtyschemeid")
   private Integer specialtyschemeid;
   @Basic(optional = false)
   @Column(name = "schemename")
   private String schemename;
   @Column(name = "descr")
   private String descr;

   public RefSpecialtyScheme() {
   }

   public RefSpecialtyScheme(Integer specialtyschemeid) {
      this.specialtyschemeid = specialtyschemeid;
   }

   public RefSpecialtyScheme(Integer specialtyschemeid, String schemename) {
      this.specialtyschemeid = specialtyschemeid;
      this.schemename = schemename;
   }

   public Integer getSpecialtyschemeid() {
      return specialtyschemeid;
   }

   public void setSpecialtyschemeid(Integer specialtyschemeid) {
      this.specialtyschemeid = specialtyschemeid;
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

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (specialtyschemeid != null ? specialtyschemeid.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefSpecialtyScheme)) {
         return false;
      }
      RefSpecialtyScheme other = (RefSpecialtyScheme) object;
      if ((this.specialtyschemeid == null && other.specialtyschemeid != null) || (this.specialtyschemeid != null && !this.specialtyschemeid.equals(other.specialtyschemeid))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefSpecialtyScheme[specialtyschemeid=" + specialtyschemeid + "]";
   }

}
