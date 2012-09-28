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
@Table(name = "usermessagetag")
@NamedQueries({@NamedQuery(name = "Usermessagetag.findAll", query = "SELECT u FROM Usermessagetag u"), @NamedQuery(name = "Usermessagetag.findByUsermessagetagkey", query = "SELECT u FROM Usermessagetag u WHERE u.usermessagetagkey = :usermessagetagkey"), @NamedQuery(name = "Usermessagetag.findByMirthresultsuserkey", query = "SELECT u FROM Usermessagetag u WHERE u.mirthresultsuserkey = :mirthresultsuserkey"), @NamedQuery(name = "Usermessagetag.findByLabel", query = "SELECT u FROM Usermessagetag u WHERE u.label = :label")})
public class Usermessagetag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "usermessagetagkey")
    private BigDecimal usermessagetagkey;
    @Basic(optional = false)
    @Column(name = "mirthresultsuserkey")
    private int mirthresultsuserkey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "descr")
    private String descr;

    public Usermessagetag() {
    }

    public Usermessagetag(BigDecimal usermessagetagkey) {
        this.usermessagetagkey = usermessagetagkey;
    }

    public Usermessagetag(BigDecimal usermessagetagkey, int mirthresultsuserkey, String label) {
        this.usermessagetagkey = usermessagetagkey;
        this.mirthresultsuserkey = mirthresultsuserkey;
        this.label = label;
    }

    public BigDecimal getUsermessagetagkey() {
        return usermessagetagkey;
    }

    public void setUsermessagetagkey(BigDecimal usermessagetagkey) {
        this.usermessagetagkey = usermessagetagkey;
    }

    public int getMirthresultsuserkey() {
        return mirthresultsuserkey;
    }

    public void setMirthresultsuserkey(int mirthresultsuserkey) {
        this.mirthresultsuserkey = mirthresultsuserkey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        hash += (usermessagetagkey != null ? usermessagetagkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usermessagetag)) {
            return false;
        }
        Usermessagetag other = (Usermessagetag) object;
        if ((this.usermessagetagkey == null && other.usermessagetagkey != null) || (this.usermessagetagkey != null && !this.usermessagetagkey.equals(other.usermessagetagkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Usermessagetag[usermessagetagkey=" + usermessagetagkey + "]";
    }

}
