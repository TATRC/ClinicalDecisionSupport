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
@Table(name = "ref_fact_code")
@NamedQueries({
   @NamedQuery(name = "RefFactCode.findAll", query = "SELECT r FROM RefFactCode r"),
   @NamedQuery(name = "RefFactCode.findBySchemetypeid", query = "SELECT r FROM RefFactCode r WHERE r.refFactCodePK.schemetypeid = :schemetypeid"),
   @NamedQuery(name = "RefFactCode.findByConceptCode", query = "SELECT r FROM RefFactCode r WHERE r.refFactCodePK.conceptCode = :conceptCode"),
   @NamedQuery(name = "RefFactCode.findByConceptName", query = "SELECT r FROM RefFactCode r WHERE r.conceptName = :conceptName"),
   @NamedQuery(name = "RefFactCode.findByPreferredConceptName", query = "SELECT r FROM RefFactCode r WHERE r.preferredConceptName = :preferredConceptName"),
   @NamedQuery(name = "RefFactCode.findByPreferredAlternateCode", query = "SELECT r FROM RefFactCode r WHERE r.preferredAlternateCode = :preferredAlternateCode"),
   @NamedQuery(name = "RefFactCode.findByCodeSystemOid", query = "SELECT r FROM RefFactCode r WHERE r.codeSystemOid = :codeSystemOid"),
   @NamedQuery(name = "RefFactCode.findByCodeSystemName", query = "SELECT r FROM RefFactCode r WHERE r.codeSystemName = :codeSystemName"),
   @NamedQuery(name = "RefFactCode.findByCodeSystemCode", query = "SELECT r FROM RefFactCode r WHERE r.codeSystemCode = :codeSystemCode"),
   @NamedQuery(name = "RefFactCode.findByCodeSystemVersion", query = "SELECT r FROM RefFactCode r WHERE r.codeSystemVersion = :codeSystemVersion"),
   @NamedQuery(name = "RefFactCode.findByHl7Table0396Code", query = "SELECT r FROM RefFactCode r WHERE r.hl7Table0396Code = :hl7Table0396Code")
})
public class RefFactCode implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected RefFactCodePK refFactCodePK;
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

   public RefFactCode() {
   }

   public RefFactCode(RefFactCodePK refFactCodePK) {
      this.refFactCodePK = refFactCodePK;
   }

   public RefFactCode(int schemetypeid, String conceptCode) {
      this.refFactCodePK = new RefFactCodePK(schemetypeid, conceptCode);
   }

   public RefFactCodePK getRefFactCodePK() {
      return refFactCodePK;
   }

   public void setRefFactCodePK(RefFactCodePK refFactCodePK) {
      this.refFactCodePK = refFactCodePK;
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
      hash += (refFactCodePK != null ? refFactCodePK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefFactCode)) {
         return false;
      }
      RefFactCode other = (RefFactCode) object;
      if ((this.refFactCodePK == null && other.refFactCodePK != null) || (this.refFactCodePK != null && !this.refFactCodePK.equals(other.refFactCodePK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefFactCode[refFactCodePK=" + refFactCodePK + "]";
   }

}
