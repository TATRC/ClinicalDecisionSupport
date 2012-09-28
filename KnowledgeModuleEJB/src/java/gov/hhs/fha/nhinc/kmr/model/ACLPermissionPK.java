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
public class ACLPermissionPK implements Serializable {
   @Basic(optional = false)
   @Column(name = "ACL_ID")
   private int aclId;
   @Basic(optional = false)
   @Column(name = "UR_ID")
   private int urId;

   public ACLPermissionPK() {
   }

   public ACLPermissionPK(int aclId, int urId) {
      this.aclId = aclId;
      this.urId = urId;
   }

   public int getAclId() {
      return aclId;
   }

   public void setAclId(int aclId) {
      this.aclId = aclId;
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
      hash += (int) aclId;
      hash += (int) urId;
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof ACLPermissionPK)) {
         return false;
      }
      ACLPermissionPK other = (ACLPermissionPK) object;
      if (this.aclId != other.aclId) {
         return false;
      }
      if (this.urId != other.urId) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.ACLPermissionPK[aclId=" + aclId + ", urId=" + urId + "]";
   }

}
