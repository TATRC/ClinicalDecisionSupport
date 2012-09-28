/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "coded_element")
@NamedQueries({@NamedQuery(name = "CodedElement.findAll", query = "SELECT c FROM CodedElement c"), @NamedQuery(name = "CodedElement.findByCodedElementKey", query = "SELECT c FROM CodedElement c WHERE c.codedElementKey = :codedElementKey"), @NamedQuery(name = "CodedElement.findByConceptKey", query = "SELECT c FROM CodedElement c WHERE c.conceptKey = :conceptKey"), @NamedQuery(name = "CodedElement.findByCode", query = "SELECT c FROM CodedElement c WHERE c.code = :code"), @NamedQuery(name = "CodedElement.findByLabel", query = "SELECT c FROM CodedElement c WHERE c.label = :label"), @NamedQuery(name = "CodedElement.findByCodeSystem", query = "SELECT c FROM CodedElement c WHERE c.codeSystem = :codeSystem"), @NamedQuery(name = "CodedElement.findByAlternateCode", query = "SELECT c FROM CodedElement c WHERE c.alternateCode = :alternateCode"), @NamedQuery(name = "CodedElement.findByAlternateLabel", query = "SELECT c FROM CodedElement c WHERE c.alternateLabel = :alternateLabel"), @NamedQuery(name = "CodedElement.findByAlternateCodeSystem", query = "SELECT c FROM CodedElement c WHERE c.alternateCodeSystem = :alternateCodeSystem")})
public class CodedElement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "coded_element_key")
    private BigDecimal codedElementKey;
    @Column(name = "concept_key")
    private BigDecimal conceptKey;
    @Column(name = "code")
    private String code;
    @Column(name = "label")
    private String label;
    @Column(name = "code_system")
    private String codeSystem;
    @Column(name = "alternate_code")
    private String alternateCode;
    @Column(name = "alternate_label")
    private String alternateLabel;
    @Column(name = "alternate_code_system")
    private String alternateCodeSystem;

    public CodedElement() {
    }

    public CodedElement(BigDecimal codedElementKey) {
        this.codedElementKey = codedElementKey;
    }

    public BigDecimal getCodedElementKey() {
        return codedElementKey;
    }

    public void setCodedElementKey(BigDecimal codedElementKey) {
        this.codedElementKey = codedElementKey;
    }

    public BigDecimal getConceptKey() {
        return conceptKey;
    }

    public void setConceptKey(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCodeSystem() {
        return codeSystem;
    }

    public void setCodeSystem(String codeSystem) {
        this.codeSystem = codeSystem;
    }

    public String getAlternateCode() {
        return alternateCode;
    }

    public void setAlternateCode(String alternateCode) {
        this.alternateCode = alternateCode;
    }

    public String getAlternateLabel() {
        return alternateLabel;
    }

    public void setAlternateLabel(String alternateLabel) {
        this.alternateLabel = alternateLabel;
    }

    public String getAlternateCodeSystem() {
        return alternateCodeSystem;
    }

    public void setAlternateCodeSystem(String alternateCodeSystem) {
        this.alternateCodeSystem = alternateCodeSystem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codedElementKey != null ? codedElementKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodedElement)) {
            return false;
        }
        CodedElement other = (CodedElement) object;
        if ((this.codedElementKey == null && other.codedElementKey != null) || (this.codedElementKey != null && !this.codedElementKey.equals(other.codedElementKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.CodedElement[codedElementKey=" + codedElementKey + "]";
    }

}
