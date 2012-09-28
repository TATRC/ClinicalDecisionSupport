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
@Table(name = "ndc")
@NamedQueries({@NamedQuery(name = "Ndc.findAll", query = "SELECT n FROM Ndc n"), @NamedQuery(name = "Ndc.findByConceptKey", query = "SELECT n FROM Ndc n WHERE n.conceptKey = :conceptKey"), @NamedQuery(name = "Ndc.findByNdcFormatted", query = "SELECT n FROM Ndc n WHERE n.ndcFormatted = :ndcFormatted"), @NamedQuery(name = "Ndc.findByBrand", query = "SELECT n FROM Ndc n WHERE n.brand = :brand"), @NamedQuery(name = "Ndc.findByRoute", query = "SELECT n FROM Ndc n WHERE n.route = :route"), @NamedQuery(name = "Ndc.findByDoseForm", query = "SELECT n FROM Ndc n WHERE n.doseForm = :doseForm"), @NamedQuery(name = "Ndc.findByProductStrength", query = "SELECT n FROM Ndc n WHERE n.productStrength = :productStrength"), @NamedQuery(name = "Ndc.findByActiveIngredient", query = "SELECT n FROM Ndc n WHERE n.activeIngredient = :activeIngredient"), @NamedQuery(name = "Ndc.findByStrengthNumAmount", query = "SELECT n FROM Ndc n WHERE n.strengthNumAmount = :strengthNumAmount"), @NamedQuery(name = "Ndc.findByStrengthNum", query = "SELECT n FROM Ndc n WHERE n.strengthNum = :strengthNum"), @NamedQuery(name = "Ndc.findByStrengthDenomAmount", query = "SELECT n FROM Ndc n WHERE n.strengthDenomAmount = :strengthDenomAmount"), @NamedQuery(name = "Ndc.findByStrengthDenom", query = "SELECT n FROM Ndc n WHERE n.strengthDenom = :strengthDenom")})
public class Ndc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "concept_key")
    private BigDecimal conceptKey;
    @Column(name = "ndc_formatted")
    private String ndcFormatted;
    @Basic(optional = false)
    @Column(name = "brand")
    private String brand;
    @Column(name = "route")
    private String route;
    @Column(name = "dose_form")
    private String doseForm;
    @Column(name = "product_strength")
    private String productStrength;
    @Column(name = "active_ingredient")
    private String activeIngredient;
    @Column(name = "strength_num_amount")
    private String strengthNumAmount;
    @Column(name = "strength_num")
    private String strengthNum;
    @Column(name = "strength_denom_amount")
    private String strengthDenomAmount;
    @Column(name = "strength_denom")
    private String strengthDenom;

    public Ndc() {
    }

    public Ndc(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public Ndc(BigDecimal conceptKey, String brand) {
        this.conceptKey = conceptKey;
        this.brand = brand;
    }

    public BigDecimal getConceptKey() {
        return conceptKey;
    }

    public void setConceptKey(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public String getNdcFormatted() {
        return ndcFormatted;
    }

    public void setNdcFormatted(String ndcFormatted) {
        this.ndcFormatted = ndcFormatted;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDoseForm() {
        return doseForm;
    }

    public void setDoseForm(String doseForm) {
        this.doseForm = doseForm;
    }

    public String getProductStrength() {
        return productStrength;
    }

    public void setProductStrength(String productStrength) {
        this.productStrength = productStrength;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public String getStrengthNumAmount() {
        return strengthNumAmount;
    }

    public void setStrengthNumAmount(String strengthNumAmount) {
        this.strengthNumAmount = strengthNumAmount;
    }

    public String getStrengthNum() {
        return strengthNum;
    }

    public void setStrengthNum(String strengthNum) {
        this.strengthNum = strengthNum;
    }

    public String getStrengthDenomAmount() {
        return strengthDenomAmount;
    }

    public void setStrengthDenomAmount(String strengthDenomAmount) {
        this.strengthDenomAmount = strengthDenomAmount;
    }

    public String getStrengthDenom() {
        return strengthDenom;
    }

    public void setStrengthDenom(String strengthDenom) {
        this.strengthDenom = strengthDenom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conceptKey != null ? conceptKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ndc)) {
            return false;
        }
        Ndc other = (Ndc) object;
        if ((this.conceptKey == null && other.conceptKey != null) || (this.conceptKey != null && !this.conceptKey.equals(other.conceptKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Ndc[conceptKey=" + conceptKey + "]";
    }

}
