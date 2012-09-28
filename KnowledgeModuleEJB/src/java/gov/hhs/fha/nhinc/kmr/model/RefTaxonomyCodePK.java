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
public class RefTaxonomyCodePK implements Serializable {
   @Basic(optional = false)
   @Column(name = "concept_code")
   private String conceptCode;
   @Basic(optional = false)
   @Column(name = "code_system_oid")
   private String codeSystemOid;
   @Basic(optional = false)
   @Column(name = "dictionary_id")
   private int dictionaryId;

   public RefTaxonomyCodePK() {
   }

   public RefTaxonomyCodePK(String conceptCode, String codeSystemOid, int dictionaryId) {
      this.conceptCode = conceptCode;
      this.codeSystemOid = codeSystemOid;
      this.dictionaryId = dictionaryId;
   }

   public String getConceptCode() {
      return conceptCode;
   }

   public void setConceptCode(String conceptCode) {
      this.conceptCode = conceptCode;
   }

   public String getCodeSystemOid() {
      return codeSystemOid;
   }

   public void setCodeSystemOid(String codeSystemOid) {
      this.codeSystemOid = codeSystemOid;
   }

   public int getDictionaryId() {
      return dictionaryId;
   }

   public void setDictionaryId(int dictionaryId) {
      this.dictionaryId = dictionaryId;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (conceptCode != null ? conceptCode.hashCode() : 0);
      hash += (codeSystemOid != null ? codeSystemOid.hashCode() : 0);
      hash += (int) dictionaryId;
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefTaxonomyCodePK)) {
         return false;
      }
      RefTaxonomyCodePK other = (RefTaxonomyCodePK) object;
      if ((this.conceptCode == null && other.conceptCode != null) || (this.conceptCode != null && !this.conceptCode.equals(other.conceptCode))) {
         return false;
      }
      if ((this.codeSystemOid == null && other.codeSystemOid != null) || (this.codeSystemOid != null && !this.codeSystemOid.equals(other.codeSystemOid))) {
         return false;
      }
      if (this.dictionaryId != other.dictionaryId) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefTaxonomyCodePK[conceptCode=" + conceptCode + ", codeSystemOid=" + codeSystemOid + ", dictionaryId=" + dictionaryId + "]";
   }

}
