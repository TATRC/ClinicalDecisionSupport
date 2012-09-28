/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "ref_specialty_code")
@NamedQueries({
   @NamedQuery(name = "RefSpecialtyCode.findAll", query = "SELECT r FROM RefSpecialtyCode r"),
   @NamedQuery(name = "RefSpecialtyCode.findBySchemetypeid", query = "SELECT r FROM RefSpecialtyCode r WHERE r.refSpecialtyCodePK.schemetypeid = :schemetypeid"),
   @NamedQuery(name = "RefSpecialtyCode.findByConceptCode", query = "SELECT r FROM RefSpecialtyCode r WHERE r.refSpecialtyCodePK.conceptCode = :conceptCode"),
   @NamedQuery(name = "RefSpecialtyCode.findByConceptName", query = "SELECT r FROM RefSpecialtyCode r WHERE r.conceptName = :conceptName"),
   @NamedQuery(name = "RefSpecialtyCode.findByPreferredConceptName", query = "SELECT r FROM RefSpecialtyCode r WHERE r.preferredConceptName = :preferredConceptName"),
   @NamedQuery(name = "RefSpecialtyCode.findByPreferredAlternateCode", query = "SELECT r FROM RefSpecialtyCode r WHERE r.preferredAlternateCode = :preferredAlternateCode"),
   @NamedQuery(name = "RefSpecialtyCode.findByCodeSystemOid", query = "SELECT r FROM RefSpecialtyCode r WHERE r.codeSystemOid = :codeSystemOid"),
   @NamedQuery(name = "RefSpecialtyCode.findByCodeSystemName", query = "SELECT r FROM RefSpecialtyCode r WHERE r.codeSystemName = :codeSystemName"),
   @NamedQuery(name = "RefSpecialtyCode.findByCodeSystemCode", query = "SELECT r FROM RefSpecialtyCode r WHERE r.codeSystemCode = :codeSystemCode"),
   @NamedQuery(name = "RefSpecialtyCode.findByCodeSystemVersion", query = "SELECT r FROM RefSpecialtyCode r WHERE r.codeSystemVersion = :codeSystemVersion"),
   @NamedQuery(name = "RefSpecialtyCode.findByHl7Table0396Code", query = "SELECT r FROM RefSpecialtyCode r WHERE r.hl7Table0396Code = :hl7Table0396Code")
})
public class RefSpecialtyCode implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected RefSpecialtyCodePK refSpecialtyCodePK;
   @Column(name = "concept_name")
   private String conceptName;
   @Column(name = "preferred_concept_name")
   private String preferredConceptName;
   @Column(name = "preferred_alternate_code")
   private String preferredAlternateCode;
   @Column(name = "code_system_oid")
   private String codeSystemOid;
   @Column(name = "code_system_name")
   private String codeSystemName;
   @Column(name = "code_system_code")
   private String codeSystemCode;
   @Column(name = "code_system_version")
   private String codeSystemVersion;
   @Column(name = "hl7_table_0396_code")
   private String hl7Table0396Code;

   public RefSpecialtyCode() {
   }

   public RefSpecialtyCode(RefSpecialtyCodePK refSpecialtyCodePK) {
      this.refSpecialtyCodePK = refSpecialtyCodePK;
   }

   public RefSpecialtyCode(int schemetypeid, String conceptCode) {
      this.refSpecialtyCodePK = new RefSpecialtyCodePK(schemetypeid, conceptCode);
   }

   public RefSpecialtyCodePK getRefSpecialtyCodePK() {
      return refSpecialtyCodePK;
   }

   public void setRefSpecialtyCodePK(RefSpecialtyCodePK refSpecialtyCodePK) {
      this.refSpecialtyCodePK = refSpecialtyCodePK;
   }

   public String getConceptName() {
      return conceptName;
   }

   public void setConceptName(String conceptName) {
      this.conceptName = conceptName;
   }

   public String getPreferredConceptName() {
      return preferredConceptName;
   }

   public void setPreferredConceptName(String preferredConceptName) {
      this.preferredConceptName = preferredConceptName;
   }

   public String getPreferredAlternateCode() {
      return preferredAlternateCode;
   }

   public void setPreferredAlternateCode(String preferredAlternateCode) {
      this.preferredAlternateCode = preferredAlternateCode;
   }

   public String getCodeSystemOid() {
      return codeSystemOid;
   }

   public void setCodeSystemOid(String codeSystemOid) {
      this.codeSystemOid = codeSystemOid;
   }

   public String getCodeSystemName() {
      return codeSystemName;
   }

   public void setCodeSystemName(String codeSystemName) {
      this.codeSystemName = codeSystemName;
   }

   public String getCodeSystemCode() {
      return codeSystemCode;
   }

   public void setCodeSystemCode(String codeSystemCode) {
      this.codeSystemCode = codeSystemCode;
   }

   public String getCodeSystemVersion() {
      return codeSystemVersion;
   }

   public void setCodeSystemVersion(String codeSystemVersion) {
      this.codeSystemVersion = codeSystemVersion;
   }

   public String getHl7Table0396Code() {
      return hl7Table0396Code;
   }

   public void setHl7Table0396Code(String hl7Table0396Code) {
      this.hl7Table0396Code = hl7Table0396Code;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (refSpecialtyCodePK != null ? refSpecialtyCodePK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefSpecialtyCode)) {
         return false;
      }
      RefSpecialtyCode other = (RefSpecialtyCode) object;
      if ((this.refSpecialtyCodePK == null && other.refSpecialtyCodePK != null) || (this.refSpecialtyCodePK != null && !this.refSpecialtyCodePK.equals(other.refSpecialtyCodePK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefSpecialtyCode[refSpecialtyCodePK=" + refSpecialtyCodePK + "]";
   }

}
