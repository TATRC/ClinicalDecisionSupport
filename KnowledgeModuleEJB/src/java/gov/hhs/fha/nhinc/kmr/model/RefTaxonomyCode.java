/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "ref_taxonomy_code")
@NamedQueries({@NamedQuery(name = "RefTaxonomyCode.findAll", query = "SELECT r FROM RefTaxonomyCode r"), @NamedQuery(name = "RefTaxonomyCode.findByConceptCode", query = "SELECT r FROM RefTaxonomyCode r WHERE r.refTaxonomyCodePK.conceptCode = :conceptCode"), @NamedQuery(name = "RefTaxonomyCode.findByConceptName", query = "SELECT r FROM RefTaxonomyCode r WHERE r.conceptName = :conceptName"), @NamedQuery(name = "RefTaxonomyCode.findByPreferredConceptName", query = "SELECT r FROM RefTaxonomyCode r WHERE r.preferredConceptName = :preferredConceptName"), @NamedQuery(name = "RefTaxonomyCode.findByPreferredAlternateCode", query = "SELECT r FROM RefTaxonomyCode r WHERE r.preferredAlternateCode = :preferredAlternateCode"), @NamedQuery(name = "RefTaxonomyCode.findByCodeSystemOid", query = "SELECT r FROM RefTaxonomyCode r WHERE r.refTaxonomyCodePK.codeSystemOid = :codeSystemOid"), @NamedQuery(name = "RefTaxonomyCode.findByCodeSystemName", query = "SELECT r FROM RefTaxonomyCode r WHERE r.codeSystemName = :codeSystemName"), @NamedQuery(name = "RefTaxonomyCode.findByCodeSystemCode", query = "SELECT r FROM RefTaxonomyCode r WHERE r.codeSystemCode = :codeSystemCode"), @NamedQuery(name = "RefTaxonomyCode.findByCodeSystemVersion", query = "SELECT r FROM RefTaxonomyCode r WHERE r.codeSystemVersion = :codeSystemVersion"), @NamedQuery(name = "RefTaxonomyCode.findByHl7Table0396Code", query = "SELECT r FROM RefTaxonomyCode r WHERE r.hl7Table0396Code = :hl7Table0396Code"), @NamedQuery(name = "RefTaxonomyCode.findBySourceFilename", query = "SELECT r FROM RefTaxonomyCode r WHERE r.sourceFilename = :sourceFilename"), @NamedQuery(name = "RefTaxonomyCode.findByDictionaryId", query = "SELECT r FROM RefTaxonomyCode r WHERE r.refTaxonomyCodePK.dictionaryId = :dictionaryId"), @NamedQuery(name = "RefTaxonomyCode.findByDisable", query = "SELECT r FROM RefTaxonomyCode r WHERE r.disable = :disable")})
public class RefTaxonomyCode implements Serializable {
   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected RefTaxonomyCodePK refTaxonomyCodePK;
   @Column(name = "concept_name")
   private String conceptName;
   @Column(name = "preferred_concept_name")
   private String preferredConceptName;
   @Column(name = "preferred_alternate_code")
   private String preferredAlternateCode;
   @Column(name = "code_system_name")
   private String codeSystemName;
   @Column(name = "code_system_code")
   private String codeSystemCode;
   @Column(name = "code_system_version")
   private String codeSystemVersion;
   @Column(name = "hl7_table_0396_code")
   private String hl7Table0396Code;
   @Column(name = "source_filename")
   private String sourceFilename;
   @Column(name = "disable")
   private Boolean disable;
   @JoinColumn(name = "dictionary_id", referencedColumnName = "dictionary_id", insertable = false, updatable = false)
   @ManyToOne(optional = false, fetch = FetchType.EAGER)
   private RefDictionary refDictionary;

   public RefTaxonomyCode() {
   }

   public RefTaxonomyCode(RefTaxonomyCodePK refTaxonomyCodePK) {
      this.refTaxonomyCodePK = refTaxonomyCodePK;
   }

   public RefTaxonomyCode(String conceptCode, String codeSystemOid, int dictionaryId) {
      this.refTaxonomyCodePK = new RefTaxonomyCodePK(conceptCode, codeSystemOid, dictionaryId);
   }

   public RefTaxonomyCodePK getRefTaxonomyCodePK() {
      return refTaxonomyCodePK;
   }

   public void setRefTaxonomyCodePK(RefTaxonomyCodePK refTaxonomyCodePK) {
      this.refTaxonomyCodePK = refTaxonomyCodePK;
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

   public String getSourceFilename() {
      return sourceFilename;
   }

   public void setSourceFilename(String sourceFilename) {
      this.sourceFilename = sourceFilename;
   }

   public Boolean getDisable() {
      return disable;
   }

   public void setDisable(Boolean disable) {
      this.disable = disable;
   }

   public RefDictionary getRefDictionary() {
      return refDictionary;
   }

   public void setRefDictionary(RefDictionary refDictionary) {
      this.refDictionary = refDictionary;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (refTaxonomyCodePK != null ? refTaxonomyCodePK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefTaxonomyCode)) {
         return false;
      }
      RefTaxonomyCode other = (RefTaxonomyCode) object;
      if ((this.refTaxonomyCodePK == null && other.refTaxonomyCodePK != null) || (this.refTaxonomyCodePK != null && !this.refTaxonomyCodePK.equals(other.refTaxonomyCodePK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefTaxonomyCode[refTaxonomyCodePK=" + refTaxonomyCodePK + "]";
   }

}
