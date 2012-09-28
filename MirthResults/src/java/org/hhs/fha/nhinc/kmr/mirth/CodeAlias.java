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
@Table(name = "code_alias")
@NamedQueries({@NamedQuery(name = "CodeAlias.findAll", query = "SELECT c FROM CodeAlias c"), @NamedQuery(name = "CodeAlias.findByCodeAliasKey", query = "SELECT c FROM CodeAlias c WHERE c.codeAliasKey = :codeAliasKey"), @NamedQuery(name = "CodeAlias.findByAlias", query = "SELECT c FROM CodeAlias c WHERE c.alias = :alias"), @NamedQuery(name = "CodeAlias.findByConceptKey", query = "SELECT c FROM CodeAlias c WHERE c.conceptKey = :conceptKey"), @NamedQuery(name = "CodeAlias.findByFacilityKey", query = "SELECT c FROM CodeAlias c WHERE c.facilityKey = :facilityKey")})
public class CodeAlias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code_alias_key")
    private BigDecimal codeAliasKey;
    @Column(name = "alias")
    private String alias;
    @Column(name = "concept_key")
    private BigDecimal conceptKey;
    @Column(name = "facility_key")
    private Integer facilityKey;

    public CodeAlias() {
    }

    public CodeAlias(BigDecimal codeAliasKey) {
        this.codeAliasKey = codeAliasKey;
    }

    public BigDecimal getCodeAliasKey() {
        return codeAliasKey;
    }

    public void setCodeAliasKey(BigDecimal codeAliasKey) {
        this.codeAliasKey = codeAliasKey;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getConceptKey() {
        return conceptKey;
    }

    public void setConceptKey(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public Integer getFacilityKey() {
        return facilityKey;
    }

    public void setFacilityKey(Integer facilityKey) {
        this.facilityKey = facilityKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeAliasKey != null ? codeAliasKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodeAlias)) {
            return false;
        }
        CodeAlias other = (CodeAlias) object;
        if ((this.codeAliasKey == null && other.codeAliasKey != null) || (this.codeAliasKey != null && !this.codeAliasKey.equals(other.codeAliasKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.CodeAlias[codeAliasKey=" + codeAliasKey + "]";
    }

}
