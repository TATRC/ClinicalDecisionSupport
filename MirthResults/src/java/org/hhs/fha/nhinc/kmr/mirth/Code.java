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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "code")
@NamedQueries({@NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c"), @NamedQuery(name = "Code.findByCodekey", query = "SELECT c FROM Code c WHERE c.codekey = :codekey"), @NamedQuery(name = "Code.findByCode", query = "SELECT c FROM Code c WHERE c.code = :code"), @NamedQuery(name = "Code.findByLabel", query = "SELECT c FROM Code c WHERE c.label = :label"), @NamedQuery(name = "Code.findByCodetypekey", query = "SELECT c FROM Code c WHERE c.codetypekey = :codetypekey")})
public class Code implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codekey")
    private BigDecimal codekey;
    @Column(name = "code")
    private String code;
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "codetypekey")
    private BigDecimal codetypekey;

    public Code() {
    }

    public Code(BigDecimal codekey) {
        this.codekey = codekey;
    }

    public BigDecimal getCodekey() {
        return codekey;
    }

    public void setCodekey(BigDecimal codekey) {
        this.codekey = codekey;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCodetypekey() {
        return codetypekey;
    }

    public void setCodetypekey(BigDecimal codetypekey) {
        this.codetypekey = codetypekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codekey != null ? codekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Code)) {
            return false;
        }
        Code other = (Code) object;
        if ((this.codekey == null && other.codekey != null) || (this.codekey != null && !this.codekey.equals(other.codekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Code[codekey=" + codekey + "]";
    }

}
