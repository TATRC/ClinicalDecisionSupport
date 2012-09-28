/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "genotype_reference")
@NamedQueries({
    @NamedQuery(name = "GenotypeReference.findAll", query = "SELECT g FROM GenotypeReference g"),
    @NamedQuery(name = "GenotypeReference.findByGenotypeId", query = "SELECT g FROM GenotypeReference g WHERE g.genotypeReferencePK.genotypeId = :genotypeId"),
    @NamedQuery(name = "GenotypeReference.findByReferenceId", query = "SELECT g FROM GenotypeReference g WHERE g.genotypeReferencePK.referenceId = :referenceId")})
public class GenotypeReference implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GenotypeReferencePK genotypeReferencePK;
    @JoinColumn(name = "genotype_id", referencedColumnName = "genotype_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genotype genotype;

    public GenotypeReference() {
    }

    public GenotypeReference(GenotypeReferencePK genotypeReferencePK) {
        this.genotypeReferencePK = genotypeReferencePK;
    }

    public GenotypeReference(int genotypeId, int referenceId) {
        this.genotypeReferencePK = new GenotypeReferencePK(genotypeId, referenceId);
    }

    public GenotypeReferencePK getGenotypeReferencePK() {
        return genotypeReferencePK;
    }

    public void setGenotypeReferencePK(GenotypeReferencePK genotypeReferencePK) {
        this.genotypeReferencePK = genotypeReferencePK;
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public void setGenotype(Genotype genotype) {
        this.genotype = genotype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genotypeReferencePK != null ? genotypeReferencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenotypeReference)) {
            return false;
        }
        GenotypeReference other = (GenotypeReference) object;
        if ((this.genotypeReferencePK == null && other.genotypeReferencePK != null) || (this.genotypeReferencePK != null && !this.genotypeReferencePK.equals(other.genotypeReferencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.GenotypeReference[genotypeReferencePK=" + genotypeReferencePK + "]";
    }

}
