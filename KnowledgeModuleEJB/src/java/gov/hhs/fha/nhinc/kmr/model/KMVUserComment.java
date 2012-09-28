/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "KMV_UserComment")
@NamedQueries({@NamedQuery(name = "KMVUserComment.findAll", query = "SELECT k FROM KMVUserComment k"), @NamedQuery(name = "KMVUserComment.findByKmvId", query = "SELECT k FROM KMVUserComment k WHERE k.kmvId = :kmvId"), @NamedQuery(name = "KMVUserComment.findByUcId", query = "SELECT k FROM KMVUserComment k WHERE k.ucId = :ucId"), @NamedQuery(name = "KMVUserComment.findByUCUserRole", query = "SELECT k FROM KMVUserComment k WHERE k.uCUserRole = :uCUserRole"), @NamedQuery(name = "KMVUserComment.findByUCUserName", query = "SELECT k FROM KMVUserComment k WHERE k.uCUserName = :uCUserName"), @NamedQuery(name = "KMVUserComment.findByUCUserID", query = "SELECT k FROM KMVUserComment k WHERE k.uCUserID = :uCUserID"), @NamedQuery(name = "KMVUserComment.findByUCUserAffiliation", query = "SELECT k FROM KMVUserComment k WHERE k.uCUserAffiliation = :uCUserAffiliation"), @NamedQuery(name = "KMVUserComment.findByUCUserContact", query = "SELECT k FROM KMVUserComment k WHERE k.uCUserContact = :uCUserContact"), @NamedQuery(name = "KMVUserComment.findByUCCreatedTimestamp", query = "SELECT k FROM KMVUserComment k WHERE k.uCCreatedTimestamp = :uCCreatedTimestamp")})
public class KMVUserComment implements Serializable {
   private static final long serialVersionUID = 1L;
   @Basic(optional = false)
   @Column(name = "KMV_ID")
   private int kmvId;
   @Id
   @Basic(optional = false)
   @Column(name = "UC_ID")
   private Integer ucId;
   @Column(name = "UC_UserRole")
   private String uCUserRole;
   @Column(name = "UC_UserName")
   private String uCUserName;
   @Column(name = "UC_UserID")
   private String uCUserID;
   @Column(name = "UC_UserAffiliation")
   private String uCUserAffiliation;
   @Column(name = "UC_UserContact")
   private String uCUserContact;
   @Column(name = "UC_CreatedTimestamp")
   @Temporal(TemporalType.TIMESTAMP)
   private Date uCCreatedTimestamp;
   @Lob
   @Column(name = "UC_Comment")
   private String uCComment;

   public KMVUserComment() {
   }

   public KMVUserComment(Integer ucId) {
      this.ucId = ucId;
   }

   public KMVUserComment(Integer ucId, int kmvId) {
      this.ucId = ucId;
      this.kmvId = kmvId;
   }

   public int getKmvId() {
      return kmvId;
   }

   public void setKmvId(int kmvId) {
      this.kmvId = kmvId;
   }

   public Integer getUcId() {
      return ucId;
   }

   public void setUcId(Integer ucId) {
      this.ucId = ucId;
   }

   public String getUCUserRole() {
      return uCUserRole;
   }

   public void setUCUserRole(String uCUserRole) {
      this.uCUserRole = uCUserRole;
   }

   public String getUCUserName() {
      return uCUserName;
   }

   public void setUCUserName(String uCUserName) {
      this.uCUserName = uCUserName;
   }

   public String getUCUserID() {
      return uCUserID;
   }

   public void setUCUserID(String uCUserID) {
      this.uCUserID = uCUserID;
   }

   public String getUCUserAffiliation() {
      return uCUserAffiliation;
   }

   public void setUCUserAffiliation(String uCUserAffiliation) {
      this.uCUserAffiliation = uCUserAffiliation;
   }

   public String getUCUserContact() {
      return uCUserContact;
   }

   public void setUCUserContact(String uCUserContact) {
      this.uCUserContact = uCUserContact;
   }

   public Date getUCCreatedTimestamp() {
      return uCCreatedTimestamp;
   }

   public void setUCCreatedTimestamp(Date uCCreatedTimestamp) {
      this.uCCreatedTimestamp = uCCreatedTimestamp;
   }

   public String getUCComment() {
      return uCComment;
   }

   public void setUCComment(String uCComment) {
      this.uCComment = uCComment;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (ucId != null ? ucId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMVUserComment)) {
         return false;
      }
      KMVUserComment other = (KMVUserComment) object;
      if ((this.ucId == null && other.ucId != null) || (this.ucId != null && !this.ucId.equals(other.ucId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.KMVUserComment[ucId=" + ucId + "]";
   }

}
