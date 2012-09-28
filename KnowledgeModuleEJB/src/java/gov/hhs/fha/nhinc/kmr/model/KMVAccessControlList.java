/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import gov.hhs.fha.nhinc.kmr.kmtypes.ACLPermissionType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVAccessControlListType;
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
@Table(name = "KMV_AccessControlList")
@NamedQueries({@NamedQuery(name = "KMVAccessControlList.findAll", query = "SELECT k FROM KMVAccessControlList k"), @NamedQuery(name = "KMVAccessControlList.findByAclId", query = "SELECT k FROM KMVAccessControlList k WHERE k.aclId = :aclId"), @NamedQuery(name = "KMVAccessControlList.findByACLName", query = "SELECT k FROM KMVAccessControlList k WHERE k.aCLName = :aCLName"), @NamedQuery(name = "KMVAccessControlList.findByACLDescription", query = "SELECT k FROM KMVAccessControlList k WHERE k.aCLDescription = :aCLDescription")})
public class KMVAccessControlList implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "ACL_ID")
   private Integer aclId;
   @Basic(optional = false)
   @Column(name = "ACL_Name")
   private String aCLName;
   @Column(name = "ACL_Description")
   private String aCLDescription;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "kMVAccessControlList", fetch = FetchType.LAZY)
   private Collection<ACLPermission> aCLPermissionCollection;
   @OneToMany(mappedBy = "aclId", fetch = FetchType.LAZY)
   private Collection<KMVersion> kMVersionCollection;

   public KMVAccessControlList() {
   }

   public KMVAccessControlList(Integer aclId) {
      this.aclId = aclId;
   }

   public KMVAccessControlList(Integer aclId, String aCLName) {
      this.aclId = aclId;
      this.aCLName = aCLName;
   }

   public Integer getAclId() {
      return aclId;
   }

   public void setAclId(Integer aclId) {
      this.aclId = aclId;
   }

   public String getACLName() {
      return aCLName;
   }

   public void setACLName(String aCLName) {
      this.aCLName = aCLName;
   }

   public String getACLDescription() {
      return aCLDescription;
   }

   public void setACLDescription(String aCLDescription) {
      this.aCLDescription = aCLDescription;
   }

   public Collection<ACLPermission> getACLPermissionCollection() {
      return aCLPermissionCollection;
   }

   public void setACLPermissionCollection(Collection<ACLPermission> aCLPermissionCollection) {
      this.aCLPermissionCollection = aCLPermissionCollection;
   }

   public Collection<KMVersion> getKMVersionCollection() {
      return kMVersionCollection;
   }

   public void setKMVersionCollection(Collection<KMVersion> kMVersionCollection) {
      this.kMVersionCollection = kMVersionCollection;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (aclId != null ? aclId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVAccessControlList)) {
         return false;
      }
      KMVAccessControlList other = (KMVAccessControlList) object;
      if ((this.aclId == null && other.aclId != null) || (this.aclId != null && !this.aclId.equals(other.aclId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVAccessControlList[aclId=" + aclId + "]";
   }



   // ======================================================
   //               USER DEFINEs
   // ======================================================
   public KMVAccessControlList(String aclname) {
      this.aCLName = aclname;
      this.aCLDescription = aclname;
   }
}
