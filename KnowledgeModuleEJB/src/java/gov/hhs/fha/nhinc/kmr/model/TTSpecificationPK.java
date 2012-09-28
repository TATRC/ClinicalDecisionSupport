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
public class TTSpecificationPK implements Serializable {
   @Basic(optional = false)
   @Column(name = "TT_ID")
   private int ttId;
   @Basic(optional = false)
   @Column(name = "TerminologyScheme")
   private String terminologyScheme;
   @Basic(optional = false)
   @Column(name = "TerminologyCode")
   private String terminologyCode;

   public TTSpecificationPK() {
   }

   public TTSpecificationPK(int ttId, String terminologyScheme, String terminologyCode) {
      this.ttId = ttId;
      this.terminologyScheme = terminologyScheme;
      this.terminologyCode = terminologyCode;
   }

   public int getTtId() {
      return ttId;
   }

   public void setTtId(int ttId) {
      this.ttId = ttId;
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

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (int) ttId;
      hash += (terminologyScheme != null ? terminologyScheme.hashCode() : 0);
      hash += (terminologyCode != null ? terminologyCode.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof TTSpecificationPK)) {
         return false;
      }
      TTSpecificationPK other = (TTSpecificationPK) object;
      if (this.ttId != other.ttId) {
         return false;
      }
      if ((this.terminologyScheme == null && other.terminologyScheme != null) || (this.terminologyScheme != null && !this.terminologyScheme.equals(other.terminologyScheme))) {
         return false;
      }
      if ((this.terminologyCode == null && other.terminologyCode != null) || (this.terminologyCode != null && !this.terminologyCode.equals(other.terminologyCode))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.TTSpecificationPK[ttId=" + ttId + ", terminologyScheme=" + terminologyScheme + ", terminologyCode=" + terminologyCode + "]";
   }

}
