/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.ACLPermissionType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ACLPermissionType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "ACL_Permission")
@NamedQueries({@NamedQuery(name = "ACLPermission.findAll", query = "SELECT a FROM ACLPermission a"),
@NamedQuery(name = "ACLPermission.findByAclId", query = "SELECT a FROM ACLPermission a WHERE a.aCLPermissionPK.aclId = :aclId"),
@NamedQuery(name = "ACLPermission.findByUrId", query = "SELECT a FROM ACLPermission a WHERE a.aCLPermissionPK.urId = :urId"),
@NamedQuery(name = "ACLPermission.findByURPermission", query = "SELECT a FROM ACLPermission a WHERE a.uRPermission = :uRPermission")
})
public class ACLPermission implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected ACLPermissionPK aCLPermissionPK;
   @Column(name = "UR_Permission")
   private String uRPermission;
   @JoinColumn(name = "ACL_ID", referencedColumnName = "ACL_ID", insertable = false, updatable = false)
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   private KMVAccessControlList kMVAccessControlList;
   @JoinColumn(name = "UR_ID", referencedColumnName = "UR_ID", insertable = false, updatable = false)
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   private UserRole userRole;

   public ACLPermission() {
   }

   public ACLPermission(ACLPermissionPK aCLPermissionPK) {
      this.aCLPermissionPK = aCLPermissionPK;
   }

   public ACLPermission(int aclId, int urId) {
      this.aCLPermissionPK = new ACLPermissionPK(aclId, urId);
   }

   public ACLPermissionPK getACLPermissionPK() {
      return aCLPermissionPK;
   }

   public void setACLPermissionPK(ACLPermissionPK aCLPermissionPK) {
      this.aCLPermissionPK = aCLPermissionPK;
   }

   public String getURPermission() {
      return uRPermission;
   }

   public void setURPermission(String uRPermission) {
      this.uRPermission = uRPermission;
   }

   public KMVAccessControlList getKMVAccessControlList() {
      return kMVAccessControlList;
   }

   public void setKMVAccessControlList(KMVAccessControlList kMVAccessControlList) {
      this.kMVAccessControlList = kMVAccessControlList;
   }

   public UserRole getUserRole() {
      return userRole;
   }

   public void setUserRole(UserRole userRole) {
      this.userRole = userRole;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (aCLPermissionPK != null ? aCLPermissionPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof ACLPermission)) {
         return false;
      }
      ACLPermission other = (ACLPermission) object;
      if ((this.aCLPermissionPK == null && other.aCLPermissionPK != null) || (this.aCLPermissionPK != null && !this.aCLPermissionPK.equals(other.aCLPermissionPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.ACLPermission[aCLPermissionPK=" + aCLPermissionPK + "]";
   }


   // ======================================================
   //               USER DEFINEs
   // ======================================================
   public ACLPermission(ACLPermissionType kmr) {
      this.uRPermission = kmr.getURPermission();
   }

}
