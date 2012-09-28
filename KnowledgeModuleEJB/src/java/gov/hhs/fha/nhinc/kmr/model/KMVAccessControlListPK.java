/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author tmn
 */
@Embeddable
public class KMVAccessControlListPK implements Serializable {
   @Basic(optional = false)
   @Column(name = "KMV_ID")
   private int kmvId;
   @Basic(optional = false)
   @Column(name = "UR_ID")
   private int urId;

   public KMVAccessControlListPK() {
   }

   public KMVAccessControlListPK(int kmvId, int urId) {
      this.kmvId = kmvId;
      this.urId = urId;
   }

   public int getKmvId() {
      return kmvId;
   }

   public void setKmvId(int kmvId) {
      this.kmvId = kmvId;
   }

   public int getUrId() {
      return urId;
   }

   public void setUrId(int urId) {
      this.urId = urId;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (int) kmvId;
      hash += (int) urId;
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVAccessControlListPK)) {
         return false;
      }
      KMVAccessControlListPK other = (KMVAccessControlListPK) object;
      if (this.kmvId != other.kmvId) {
         return false;
      }
      if (this.urId != other.urId) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVAccessControlListPK[kmvId=" + kmvId + ", urId=" + urId + "]";
   }

}
