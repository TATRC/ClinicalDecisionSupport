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
@Table(name = "nhin_process_status")
@NamedQueries({@NamedQuery(name = "NhinProcessStatus.findAll", query = "SELECT n FROM NhinProcessStatus n"), @NamedQuery(name = "NhinProcessStatus.findByNhinProcessStatusKey", query = "SELECT n FROM NhinProcessStatus n WHERE n.nhinProcessStatusKey = :nhinProcessStatusKey"), @NamedQuery(name = "NhinProcessStatus.findByLabel", query = "SELECT n FROM NhinProcessStatus n WHERE n.label = :label"), @NamedQuery(name = "NhinProcessStatus.findByIdentifier", query = "SELECT n FROM NhinProcessStatus n WHERE n.identifier = :identifier"), @NamedQuery(name = "NhinProcessStatus.findByDescr", query = "SELECT n FROM NhinProcessStatus n WHERE n.descr = :descr")})
public class NhinProcessStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nhin_process_status_key")
    private BigDecimal nhinProcessStatusKey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Basic(optional = false)
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "descr")
    private String descr;

    public NhinProcessStatus() {
    }

    public NhinProcessStatus(BigDecimal nhinProcessStatusKey) {
        this.nhinProcessStatusKey = nhinProcessStatusKey;
    }

    public NhinProcessStatus(BigDecimal nhinProcessStatusKey, String label, String identifier) {
        this.nhinProcessStatusKey = nhinProcessStatusKey;
        this.label = label;
        this.identifier = identifier;
    }

    public BigDecimal getNhinProcessStatusKey() {
        return nhinProcessStatusKey;
    }

    public void setNhinProcessStatusKey(BigDecimal nhinProcessStatusKey) {
        this.nhinProcessStatusKey = nhinProcessStatusKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nhinProcessStatusKey != null ? nhinProcessStatusKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhinProcessStatus)) {
            return false;
        }
        NhinProcessStatus other = (NhinProcessStatus) object;
        if ((this.nhinProcessStatusKey == null && other.nhinProcessStatusKey != null) || (this.nhinProcessStatusKey != null && !this.nhinProcessStatusKey.equals(other.nhinProcessStatusKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.NhinProcessStatus[nhinProcessStatusKey=" + nhinProcessStatusKey + "]";
    }

}
