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
@Table(name = "KMV_UsageStatistic")
@NamedQueries({@NamedQuery(name = "KMVUsageStatistic.findAll", query = "SELECT k FROM KMVUsageStatistic k"), @NamedQuery(name = "KMVUsageStatistic.findByKmvId", query = "SELECT k FROM KMVUsageStatistic k WHERE k.kmvId = :kmvId"), @NamedQuery(name = "KMVUsageStatistic.findByUstId", query = "SELECT k FROM KMVUsageStatistic k WHERE k.ustId = :ustId"), @NamedQuery(name = "KMVUsageStatistic.findByUSTValue", query = "SELECT k FROM KMVUsageStatistic k WHERE k.uSTValue = :uSTValue")})
public class KMVUsageStatistic implements Serializable {
   private static final long serialVersionUID = 1L;
   @Basic(optional = false)
   @Column(name = "KMV_ID")
   private int kmvId;
   @Id
   @Basic(optional = false)
   @Column(name = "UST_ID")
   private Integer ustId;
   @Column(name = "UST_Value")
   private String uSTValue;

   public KMVUsageStatistic() {
   }

   public KMVUsageStatistic(Integer ustId) {
      this.ustId = ustId;
   }

   public KMVUsageStatistic(Integer ustId, int kmvId) {
      this.ustId = ustId;
      this.kmvId = kmvId;
   }

   public int getKmvId() {
      return kmvId;
   }

   public void setKmvId(int kmvId) {
      this.kmvId = kmvId;
   }

   public Integer getUstId() {
      return ustId;
   }

   public void setUstId(Integer ustId) {
      this.ustId = ustId;
   }

   public String getUSTValue() {
      return uSTValue;
   }

   public void setUSTValue(String uSTValue) {
      this.uSTValue = uSTValue;
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
      if (!(object instanceof KMVUsageStatistic)) {
         return false;
      }
      KMVUsageStatistic other = (KMVUsageStatistic) object;
      if ((this.ustId == null && other.ustId != null) || (this.ustId != null && !this.ustId.equals(other.ustId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVUsageStatistic[ustId=" + ustId + "]";
   }

   // ======================================================
   //               USER DEFINEs
   // ======================================================
   
}
