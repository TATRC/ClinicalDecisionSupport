/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "UsageStatisticType")
@NamedQueries({@NamedQuery(name = "UsageStatisticType.findAll", query = "SELECT u FROM UsageStatisticType u"), @NamedQuery(name = "UsageStatisticType.findByUstId", query = "SELECT u FROM UsageStatisticType u WHERE u.ustId = :ustId"), @NamedQuery(name = "UsageStatisticType.findByUSTName", query = "SELECT u FROM UsageStatisticType u WHERE u.uSTName = :uSTName"), @NamedQuery(name = "UsageStatisticType.findByUSTDescription", query = "SELECT u FROM UsageStatisticType u WHERE u.uSTDescription = :uSTDescription"), @NamedQuery(name = "UsageStatisticType.findByUSTUnitOfMeasure", query = "SELECT u FROM UsageStatisticType u WHERE u.uSTUnitOfMeasure = :uSTUnitOfMeasure")})
public class UsageStatisticType implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "UST_ID")
   private Integer ustId;
   @Basic(optional = false)
   @Column(name = "UST_Name")
   private String uSTName;
   @Column(name = "UST_Description")
   private String uSTDescription;
   @Column(name = "UST_UnitOfMeasure")
   private String uSTUnitOfMeasure;

   public UsageStatisticType() {
   }

   public UsageStatisticType(Integer ustId) {
      this.ustId = ustId;
   }

   public UsageStatisticType(Integer ustId, String uSTName) {
      this.ustId = ustId;
      this.uSTName = uSTName;
   }

   public Integer getUstId() {
      return ustId;
   }

   public void setUstId(Integer ustId) {
      this.ustId = ustId;
   }

   public String getUSTName() {
      return uSTName;
   }

   public void setUSTName(String uSTName) {
      this.uSTName = uSTName;
   }

   public String getUSTDescription() {
      return uSTDescription;
   }

   public void setUSTDescription(String uSTDescription) {
      this.uSTDescription = uSTDescription;
   }

   public String getUSTUnitOfMeasure() {
      return uSTUnitOfMeasure;
   }

   public void setUSTUnitOfMeasure(String uSTUnitOfMeasure) {
      this.uSTUnitOfMeasure = uSTUnitOfMeasure;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (ustId != null ? ustId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof UsageStatisticType)) {
         return false;
      }
      UsageStatisticType other = (UsageStatisticType) object;
      if ((this.ustId == null && other.ustId != null) || (this.ustId != null && !this.ustId.equals(other.ustId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.UsageStatisticType[ustId=" + ustId + "]";
   }

}
