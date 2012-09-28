/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "genotype")
@NamedQueries({
    @NamedQuery(name = "Genotype.findAll", query = "SELECT g FROM Genotype g"),
    @NamedQuery(name = "Genotype.findByGenotypeId", query = "SELECT g FROM Genotype g WHERE g.genotypePK.genotypeId = :genotypeId"),
    @NamedQuery(name = "Genotype.findByPatientId", query = "SELECT g FROM Genotype g WHERE g.genotypePK.patientId = :patientId"),
    @NamedQuery(name = "Genotype.findByGenotypeType", query = "SELECT g FROM Genotype g WHERE g.genotypeType = :genotypeType"),
    @NamedQuery(name = "Genotype.findByAccessionId", query = "SELECT g FROM Genotype g WHERE g.accessionId = :accessionId"),
    @NamedQuery(name = "Genotype.findByGeneId", query = "SELECT g FROM Genotype g WHERE g.geneId = :geneId"),
    @NamedQuery(name = "Genotype.findByChromosome", query = "SELECT g FROM Genotype g WHERE g.chromosome = :chromosome"),
    @NamedQuery(name = "Genotype.findByStartPos", query = "SELECT g FROM Genotype g WHERE g.startPos = :startPos"),
    @NamedQuery(name = "Genotype.findByEndPos", query = "SELECT g FROM Genotype g WHERE g.endPos = :endPos"),
    @NamedQuery(name = "Genotype.findByResult", query = "SELECT g FROM Genotype g WHERE g.result = :result")})
public class Genotype implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GenotypePK genotypePK;
    @Basic(optional = false)
    @Column(name = "genotype_type")
    private int genotypeType;
    @Basic(optional = false)
    @Column(name = "accession_id")
    private String accessionId;
    @Column(name = "gene_id")
    private String geneId;
    @Column(name = "chromosome")
    private String chromosome;
    @Column(name = "start_pos")
    private String startPos;
    @Column(name = "end_pos")
    private String endPos;
    @Lob
    @Column(name = "comment")
    private String comment;
    @Column(name = "result")
    private String result;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genotype")
    private Collection<GenotypeReference> genotypeReferenceCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "genotype")
    private GenotypeSequence genotypeSequence;

    public Genotype() {
    }

    public Genotype(GenotypePK genotypePK) {
        this.genotypePK = genotypePK;
    }

    public Genotype(GenotypePK genotypePK, int genotypeType, String accessionId) {
        this.genotypePK = genotypePK;
        this.genotypeType = genotypeType;
        this.accessionId = accessionId;
    }

    public Genotype(int genotypeId, int patientId) {
        this.genotypePK = new GenotypePK(genotypeId, patientId);
    }

    public GenotypePK getGenotypePK() {
        return genotypePK;
    }

    public void setGenotypePK(GenotypePK genotypePK) {
        this.genotypePK = genotypePK;
    }

    public int getGenotypeType() {
        return genotypeType;
    }

    public void setGenotypeType(int genotypeType) {
        this.genotypeType = genotypeType;
    }

    public String getAccessionId() {
        return accessionId;
    }

    public void setAccessionId(String accessionId) {
        this.accessionId = accessionId;
    }

    public String getGeneId() {
        return geneId;
    }

    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getStartPos() {
        return startPos;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public String getEndPos() {
        return endPos;
    }

    public void setEndPos(String endPos) {
        this.endPos = endPos;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Collection<GenotypeReference> getGenotypeReferenceCollection() {
        return genotypeReferenceCollection;
    }

    public void setGenotypeReferenceCollection(Collection<GenotypeReference> genotypeReferenceCollection) {
        this.genotypeReferenceCollection = genotypeReferenceCollection;
    }

    public GenotypeSequence getGenotypeSequence() {
        return genotypeSequence;
    }

    public void setGenotypeSequence(GenotypeSequence genotypeSequence) {
        this.genotypeSequence = genotypeSequence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genotypePK != null ? genotypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genotype)) {
            return false;
        }
        Genotype other = (Genotype) object;
        if ((this.genotypePK == null && other.genotypePK != null) || (this.genotypePK != null && !this.genotypePK.equals(other.genotypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Genotype[genotypePK=" + genotypePK + "]";
    }

}
