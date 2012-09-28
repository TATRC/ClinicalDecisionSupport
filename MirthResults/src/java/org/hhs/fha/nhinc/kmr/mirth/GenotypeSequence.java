/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "genotype_sequence")
@NamedQueries({
    @NamedQuery(name = "GenotypeSequence.findAll", query = "SELECT g FROM GenotypeSequence g"),
    @NamedQuery(name = "GenotypeSequence.findByGenotypeId", query = "SELECT g FROM GenotypeSequence g WHERE g.genotypeId = :genotypeId")})
public class GenotypeSequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "genotype_id")
    private Integer genotypeId;
    @Lob
    @Column(name = "sequence")
    private String sequence;
    @JoinColumn(name = "genotype_id", referencedColumnName = "genotype_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Genotype genotype;

    public GenotypeSequence() {
    }

    public GenotypeSequence(Integer genotypeId) {
        this.genotypeId = genotypeId;
    }

    public Integer getGenotypeId() {
        return genotypeId;
    }

    public void setGenotypeId(Integer genotypeId) {
        this.genotypeId = genotypeId;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
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
        hash += (genotypeId != null ? genotypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenotypeSequence)) {
            return false;
        }
        GenotypeSequence other = (GenotypeSequence) object;
        if ((this.genotypeId == null && other.genotypeId != null) || (this.genotypeId != null && !this.genotypeId.equals(other.genotypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.GenotypeSequence[genotypeId=" + genotypeId + "]";
    }

}
