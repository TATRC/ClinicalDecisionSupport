/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "UserRole")
@NamedQueries({@NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u"),
@NamedQuery(name = "UserRole.findByUrId", query = "SELECT u FROM UserRole u WHERE u.urId = :urId"),
@NamedQuery(name = "UserRole.findByURName", query = "SELECT u FROM UserRole u WHERE u.uRName = :uRName"),
@NamedQuery(name = "UserRole.findByURDescription", query = "SELECT u FROM UserRole u WHERE u.uRDescription = :uRDescription")
})
public class UserRole implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "UR_ID")
   private Integer urId;
   @Basic(optional = false)
   @Column(name = "UR_Name")
   private String uRName;
   @Column(name = "UR_Description")
   private String uRDescription;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "userRole", fetch = FetchType.LAZY)
   private Collection<ACLPermission> aCLPermissionCollection;

   public UserRole() {
   }

   public UserRole(Integer urId) {
      this.urId = urId;
   }

   public UserRole(Integer urId, String uRName) {
      this.urId = urId;
      this.uRName = uRName;
   }

   public Integer getUrId() {
      return urId;
   }

   public void setUrId(Integer urId) {
      this.urId = urId;
   }

   public String getURName() {
      return uRName;
   }

   public void setURName(String uRName) {
      this.uRName = uRName;
   }

   public String getURDescription() {
      return uRDescription;
   }

   public void setURDescription(String uRDescription) {
      this.uRDescription = uRDescription;
   }

   public Collection<ACLPermission> getACLPermissionCollection() {
      return aCLPermissionCollection;
   }

   public void setACLPermissionCollection(Collection<ACLPermission> aCLPermissionCollection) {
      this.aCLPermissionCollection = aCLPermissionCollection;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (urId != null ? urId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof UserRole)) {
         return false;
      }
      UserRole other = (UserRole) object;
      if ((this.urId == null && other.urId != null) || (this.urId != null && !this.urId.equals(other.urId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.UserRole[urId=" + urId + "]";
   }


   // ======================================================
   //               USER DEFINEs
   // ======================================================
   public UserRole(String uRName) {
      this.uRName = uRName;
   }
}
