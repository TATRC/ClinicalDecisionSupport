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
public class PopulationSpecificationPK implements Serializable {
   @Basic(optional = false)
   @Column(name = "BP_ID")
   private int bpId;
   @Basic(optional = false)
   @Column(name = "TerminologyScheme")
   private String terminologyScheme;
   @Basic(optional = false)
   @Column(name = "TerminologyCode")
   private String terminologyCode;
   @Basic(optional = false)
   @Column(name = "TerminologyValue")
   private String terminologyValue;

   public PopulationSpecificationPK() {
   }

   public PopulationSpecificationPK(int bpId, String terminologyScheme, String terminologyCode, String terminologyValue) {
      this.bpId = bpId;
      this.terminologyScheme = terminologyScheme;
      this.terminologyCode = terminologyCode;
      this.terminologyValue = terminologyValue;
   }

   public int getBpId() {
      return bpId;
   }

   public void setBpId(int bpId) {
      this.bpId = bpId;
   }

   public String getTerminologyScheme() {
      return terminologyScheme;
   }

   public void setTerminologyScheme(String terminologyScheme) {
      this.terminologyScheme = terminologyScheme;
   }

   public String getTerminologyCode() {
      return terminologyCode;
   }

   public void setTerminologyCode(String terminologyCode) {
      this.terminologyCode = terminologyCode;
   }

   public String getTerminologyValue() {
      return terminologyValue;
   }

   public void setTerminologyValue(String terminologyValue) {
      this.terminologyValue = terminologyValue;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (int) bpId;
      hash += (terminologyScheme != null ? terminologyScheme.hashCode() : 0);
      hash += (terminologyCode != null ? terminologyCode.hashCode() : 0);
      hash += (terminologyValue != null ? terminologyValue.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof PopulationSpecificationPK)) {
         return false;
      }
      PopulationSpecificationPK other = (PopulationSpecificationPK) object;
      if (this.bpId != other.bpId) {
         return false;
      }
      if ((this.terminologyScheme == null && other.terminologyScheme != null) || (this.terminologyScheme != null && !this.terminologyScheme.equals(other.terminologyScheme))) {
         return false;
      }
      if ((this.terminologyCode == null && other.terminologyCode != null) || (this.terminologyCode != null && !this.terminologyCode.equals(other.terminologyCode))) {
         return false;
      }
      if ((this.terminologyValue == null && other.terminologyValue != null) || (this.terminologyValue != null && !this.terminologyValue.equals(other.terminologyValue))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.PopulationSpecificationPK[bpId=" + bpId + ", terminologyScheme=" + terminologyScheme + ", terminologyCode=" + terminologyCode + ", terminologyValue=" + terminologyValue + "]";
   }

}
