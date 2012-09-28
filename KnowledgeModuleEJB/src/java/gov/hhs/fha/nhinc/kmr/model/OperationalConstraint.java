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
@Table(name = "OperationalConstraint")
@NamedQueries({@NamedQuery(name = "OperationalConstraint.findAll", query = "SELECT o FROM OperationalConstraint o"), @NamedQuery(name = "OperationalConstraint.findByOcId", query = "SELECT o FROM OperationalConstraint o WHERE o.ocId = :ocId"), @NamedQuery(name = "OperationalConstraint.findByOCName", query = "SELECT o FROM OperationalConstraint o WHERE o.oCName = :oCName"), @NamedQuery(name = "OperationalConstraint.findByOCType", query = "SELECT o FROM OperationalConstraint o WHERE o.oCType = :oCType"), @NamedQuery(name = "OperationalConstraint.findByOCDescription", query = "SELECT o FROM OperationalConstraint o WHERE o.oCDescription = :oCDescription"), @NamedQuery(name = "OperationalConstraint.findByOCKeywords", query = "SELECT o FROM OperationalConstraint o WHERE o.oCKeywords = :oCKeywords")})
public class OperationalConstraint implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "OC_ID")
   private Integer ocId;
   @Basic(optional = false)
   @Column(name = "OC_Name")
   private String oCName;
   @Column(name = "OC_Type")
   private String oCType;
   @Column(name = "OC_Description")
   private String oCDescription;
   @Column(name = "OC_Keywords")
   private String oCKeywords;

   public OperationalConstraint() {
   }

   public OperationalConstraint(Integer ocId) {
      this.ocId = ocId;
   }

   public OperationalConstraint(Integer ocId, String oCName) {
      this.ocId = ocId;
      this.oCName = oCName;
   }

   public Integer getOcId() {
      return ocId;
   }

   public void setOcId(Integer ocId) {
      this.ocId = ocId;
   }

   public String getOCName() {
      return oCName;
   }

   public void setOCName(String oCName) {
      this.oCName = oCName;
   }

   public String getOCType() {
      return oCType;
   }

   public void setOCType(String oCType) {
      this.oCType = oCType;
   }

   public String getOCDescription() {
      return oCDescription;
   }

   public void setOCDescription(String oCDescription) {
      this.oCDescription = oCDescription;
   }

   public String getOCKeywords() {
      return oCKeywords;
   }

   public void setOCKeywords(String oCKeywords) {
      this.oCKeywords = oCKeywords;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (ocId != null ? ocId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof OperationalConstraint)) {
         return false;
      }
      OperationalConstraint other = (OperationalConstraint) object;
      if ((this.ocId == null && other.ocId != null) || (this.ocId != null && !this.ocId.equals(other.ocId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.OperationalConstraint[ocId=" + ocId + "]";
   }

}
