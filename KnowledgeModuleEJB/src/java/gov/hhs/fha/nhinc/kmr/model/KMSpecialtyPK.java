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
public class KMSpecialtyPK implements Serializable {
   @Basic(optional = false)
   @Column(name = "KM_ID")
   private int kmId;
   @Basic(optional = false)
   @Column(name = "TerminologyScheme")
   private String terminologyScheme;
   @Basic(optional = false)
   @Column(name = "TerminologyCode")
   private String terminologyCode;

   public KMSpecialtyPK() {
   }

   public KMSpecialtyPK(int kmId, String terminologyScheme, String terminologyCode) {
      this.kmId = kmId;
      this.terminologyScheme = terminologyScheme;
      this.terminologyCode = terminologyCode;
   }

   public int getKmId() {
      return kmId;
   }

   public void setKmId(int kmId) {
      this.kmId = kmId;
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
      hash += (int) kmId;
      hash += (terminologyScheme != null ? terminologyScheme.hashCode() : 0);
      hash += (terminologyCode != null ? terminologyCode.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof KMSpecialtyPK)) {
         return false;
      }
      KMSpecialtyPK other = (KMSpecialtyPK) object;
      if (this.kmId != other.kmId) {
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
      return "gov.hhs.fha.nhinc.kmr.model.KMSpecialtyPK[kmId=" + kmId + ", terminologyScheme=" + terminologyScheme + ", terminologyCode=" + terminologyCode + "]";
   }

}
