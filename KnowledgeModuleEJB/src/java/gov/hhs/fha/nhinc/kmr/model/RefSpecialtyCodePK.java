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
public class RefSpecialtyCodePK implements Serializable {
   @Basic(optional = false)
   @Column(name = "schemetypeid")
   private int schemetypeid;
   @Basic(optional = false)
   @Column(name = "concept_code")
   private String conceptCode;

   public RefSpecialtyCodePK() {
   }

   public RefSpecialtyCodePK(int schemetypeid, String conceptCode) {
      this.schemetypeid = schemetypeid;
      this.conceptCode = conceptCode;
   }

   public int getSchemetypeid() {
      return schemetypeid;
   }

   public void setSchemetypeid(int schemetypeid) {
      this.schemetypeid = schemetypeid;
   }

   public String getConceptCode() {
      return conceptCode;
   }

   public void setConceptCode(String conceptCode) {
      this.conceptCode = conceptCode;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (int) schemetypeid;
      hash += (conceptCode != null ? conceptCode.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefSpecialtyCodePK)) {
         return false;
      }
      RefSpecialtyCodePK other = (RefSpecialtyCodePK) object;
      if (this.schemetypeid != other.schemetypeid) {
         return false;
      }
      if ((this.conceptCode == null && other.conceptCode != null) || (this.conceptCode != null && !this.conceptCode.equals(other.conceptCode))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefSpecialtyCodePK[schemetypeid=" + schemetypeid + ", conceptCode=" + conceptCode + "]";
   }

}
