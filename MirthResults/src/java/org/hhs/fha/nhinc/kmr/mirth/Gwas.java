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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "GWAS")
@NamedQueries({
    @NamedQuery(name = "Gwas.findAll", query = "SELECT g FROM Gwas g"),
    @NamedQuery(name = "Gwas.findByTraitId", query = "SELECT g FROM Gwas g WHERE g.traitId = :traitId"),
    @NamedQuery(name = "Gwas.findByTraitName", query = "SELECT g FROM Gwas g WHERE g.traitName = :traitName"),
    @NamedQuery(name = "Gwas.findBySnpId", query = "SELECT g FROM Gwas g WHERE g.snpId = :snpId"),
    @NamedQuery(name = "Gwas.findByRiskAllele", query = "SELECT g FROM Gwas g WHERE g.riskAllele = :riskAllele"),
    @NamedQuery(name = "Gwas.findByChromosome", query = "SELECT g FROM Gwas g WHERE g.chromosome = :chromosome"),
    @NamedQuery(name = "Gwas.findByRegion", query = "SELECT g FROM Gwas g WHERE g.region = :region"),
    @NamedQuery(name = "Gwas.findByGene", query = "SELECT g FROM Gwas g WHERE g.gene = :gene")})
public class Gwas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "trait_id")
    private Integer traitId;
    @Basic(optional = false)
    @Column(name = "trait_name")
    private String traitName;
    @Basic(optional = false)
    @Column(name = "snp_id")
    private String snpId;
    @Column(name = "risk_allele")
    private String riskAllele;
    @Column(name = "chromosome")
    private String chromosome;
    @Column(name = "region")
    private String region;
    @Column(name = "gene")
    private String gene;

    public Gwas() {
    }

    public Gwas(Integer traitId) {
        this.traitId = traitId;
    }

    public Gwas(Integer traitId, String traitName, String snpId) {
        this.traitId = traitId;
        this.traitName = traitName;
        this.snpId = snpId;
    }

    public Integer getTraitId() {
        return traitId;
    }

    public void setTraitId(Integer traitId) {
        this.traitId = traitId;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public String getSnpId() {
        return snpId;
    }

    public void setSnpId(String snpId) {
        this.snpId = snpId;
    }

    public String getRiskAllele() {
        return riskAllele;
    }

    public void setRiskAllele(String riskAllele) {
        this.riskAllele = riskAllele;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traitId != null ? traitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gwas)) {
            return false;
        }
        Gwas other = (Gwas) object;
        if ((this.traitId == null && other.traitId != null) || (this.traitId != null && !this.traitId.equals(other.traitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Gwas[traitId=" + traitId + "]";
    }

}
