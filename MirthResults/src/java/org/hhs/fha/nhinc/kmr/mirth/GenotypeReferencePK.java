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
public class GenotypeReferencePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "genotype_id")
    private int genotypeId;
    @Basic(optional = false)
    @Column(name = "reference_id")
    private int referenceId;

    public GenotypeReferencePK() {
    }

    public GenotypeReferencePK(int genotypeId, int referenceId) {
        this.genotypeId = genotypeId;
        this.referenceId = referenceId;
    }

    public int getGenotypeId() {
        return genotypeId;
    }

    public void setGenotypeId(int genotypeId) {
        this.genotypeId = genotypeId;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) genotypeId;
        hash += (int) referenceId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenotypeReferencePK)) {
            return false;
        }
        GenotypeReferencePK other = (GenotypeReferencePK) object;
        if (this.genotypeId != other.genotypeId) {
            return false;
        }
        if (this.referenceId != other.referenceId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.GenotypeReferencePK[genotypeId=" + genotypeId + ", referenceId=" + referenceId + "]";
    }

}
