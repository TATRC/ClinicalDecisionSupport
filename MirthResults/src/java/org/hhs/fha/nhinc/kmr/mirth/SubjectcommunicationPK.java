/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Duane DeCouteau
 */
@Embeddable
public class SubjectcommunicationPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "communicationkey")
    private BigDecimal communicationkey;
    @Basic(optional = false)
    @Column(name = "subjectkey")
    private int subjectkey;

    public SubjectcommunicationPK(BigDecimal communicationkey, int subjectkey) {
        this.communicationkey = communicationkey;
        this.subjectkey = subjectkey;
    }

    public BigDecimal getCommunicationkey() {
        return communicationkey;
    }

    public void setCommunicationkey(BigDecimal communicationkey) {
        this.communicationkey = communicationkey;
    }

    public int getSubjectkey() {
        return subjectkey;
    }

    public void setSubjectkey(int subjectkey) {
        this.subjectkey = subjectkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (communicationkey != null ? communicationkey.hashCode() : 0);
        hash += (int) subjectkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectcommunicationPK)) {
            return false;
        }
        SubjectcommunicationPK other = (SubjectcommunicationPK) object;
        if ((this.communicationkey == null && other.communicationkey != null) || (this.communicationkey != null && !this.communicationkey.equals(other.communicationkey))) {
            return false;
        }
        if (this.subjectkey != other.subjectkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectcommunicationPK[communicationkey=" + communicationkey + ", subjectkey=" + subjectkey + "]";
    }

}
