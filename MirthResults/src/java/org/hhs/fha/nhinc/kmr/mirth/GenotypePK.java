/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Duane DeCouteau
 */
@Embeddable
public class GenotypePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "genotype_id")
    private int genotypeId;
    @Basic(optional = false)
    @Column(name = "patient_id")
    private int patientId;

    public GenotypePK() {
    }

    public GenotypePK(int genotypeId, int patientId) {
        this.genotypeId = genotypeId;
        this.patientId = patientId;
    }

    public int getGenotypeId() {
        return genotypeId;
    }

    public void setGenotypeId(int genotypeId) {
        this.genotypeId = genotypeId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) genotypeId;
        hash += (int) patientId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenotypePK)) {
            return false;
        }
        GenotypePK other = (GenotypePK) object;
        if (this.genotypeId != other.genotypeId) {
            return false;
        }
        if (this.patientId != other.patientId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.GenotypePK[genotypeId=" + genotypeId + ", patientId=" + patientId + "]";
    }

}
