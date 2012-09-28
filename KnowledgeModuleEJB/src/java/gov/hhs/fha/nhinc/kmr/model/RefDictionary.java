/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tmn
 */
@Entity
@Table(name = "ref_dictionary")
@NamedQueries({@NamedQuery(name = "RefDictionary.findAll", query = "SELECT r FROM RefDictionary r"), @NamedQuery(name = "RefDictionary.findByDictionaryId", query = "SELECT r FROM RefDictionary r WHERE r.dictionaryId = :dictionaryId"), @NamedQuery(name = "RefDictionary.findByConceptName", query = "SELECT r FROM RefDictionary r WHERE r.conceptName = :conceptName"), @NamedQuery(name = "RefDictionary.findByActiveCodeSystemOid", query = "SELECT r FROM RefDictionary r WHERE r.activeCodeSystemOid = :activeCodeSystemOid")})
public class RefDictionary implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "dictionary_id")
   private Integer dictionaryId;
   @Column(name = "concept_name")
   private String conceptName;
   @Column(name = "active_code_system_oid")
   private String activeCodeSystemOid;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "refDictionary", fetch = FetchType.EAGER)
   private Collection<RefTaxonomyCode> refTaxonomyCodeCollection;

   public RefDictionary() {
   }

   public RefDictionary(Integer dictionaryId) {
      this.dictionaryId = dictionaryId;
   }

   public Integer getDictionaryId() {
      return dictionaryId;
   }

   public void setDictionaryId(Integer dictionaryId) {
      this.dictionaryId = dictionaryId;
   }

   public String getConceptName() {
      return conceptName;
   }

   public void setConceptName(String conceptName) {
      this.conceptName = conceptName;
   }

   public String getActiveCodeSystemOid() {
      return activeCodeSystemOid;
   }

   public void setActiveCodeSystemOid(String activeCodeSystemOid) {
      this.activeCodeSystemOid = activeCodeSystemOid;
   }

   public Collection<RefTaxonomyCode> getRefTaxonomyCodeCollection() {
      return refTaxonomyCodeCollection;
   }

   public void setRefTaxonomyCodeCollection(Collection<RefTaxonomyCode> refTaxonomyCodeCollection) {
      this.refTaxonomyCodeCollection = refTaxonomyCodeCollection;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (dictionaryId != null ? dictionaryId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof RefDictionary)) {
         return false;
      }
      RefDictionary other = (RefDictionary) object;
      if ((this.dictionaryId == null && other.dictionaryId != null) || (this.dictionaryId != null && !this.dictionaryId.equals(other.dictionaryId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.RefDictionary[dictionaryId=" + dictionaryId + "]";
   }

}
