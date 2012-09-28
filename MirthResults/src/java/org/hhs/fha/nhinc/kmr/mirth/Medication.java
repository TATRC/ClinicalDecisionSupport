/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "medication")
@NamedQueries({@NamedQuery(name = "Medication.findAll", query = "SELECT m FROM Medication m"), @NamedQuery(name = "Medication.findByClinicalItemKey", query = "SELECT m FROM Medication m WHERE m.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Medication.findByStrength", query = "SELECT m FROM Medication m WHERE m.strength = :strength"), @NamedQuery(name = "Medication.findByStrengthUnit", query = "SELECT m FROM Medication m WHERE m.strengthUnit = :strengthUnit"), @NamedQuery(name = "Medication.findByDoseForm", query = "SELECT m FROM Medication m WHERE m.doseForm = :doseForm"), @NamedQuery(name = "Medication.findByDoseQuantity", query = "SELECT m FROM Medication m WHERE m.doseQuantity = :doseQuantity"), @NamedQuery(name = "Medication.findByDoseQuantityUnit", query = "SELECT m FROM Medication m WHERE m.doseQuantityUnit = :doseQuantityUnit"), @NamedQuery(name = "Medication.findByRoute", query = "SELECT m FROM Medication m WHERE m.route = :route"), @NamedQuery(name = "Medication.findByExpirationDate", query = "SELECT m FROM Medication m WHERE m.expirationDate = :expirationDate"), @NamedQuery(name = "Medication.findByFrequency", query = "SELECT m FROM Medication m WHERE m.frequency = :frequency"), @NamedQuery(name = "Medication.findByCustomInstructions", query = "SELECT m FROM Medication m WHERE m.customInstructions = :customInstructions"), @NamedQuery(name = "Medication.findByFillMethod", query = "SELECT m FROM Medication m WHERE m.fillMethod = :fillMethod"), @NamedQuery(name = "Medication.findByRefillsTotal", query = "SELECT m FROM Medication m WHERE m.refillsTotal = :refillsTotal"), @NamedQuery(name = "Medication.findByRefillsRemaining", query = "SELECT m FROM Medication m WHERE m.refillsRemaining = :refillsRemaining"), @NamedQuery(name = "Medication.findByPharmacyName", query = "SELECT m FROM Medication m WHERE m.pharmacyName = :pharmacyName"), @NamedQuery(name = "Medication.findByPharmacyAddressKey", query = "SELECT m FROM Medication m WHERE m.pharmacyAddressKey = :pharmacyAddressKey"), @NamedQuery(name = "Medication.findByPharmacyVoiceContactKey", query = "SELECT m FROM Medication m WHERE m.pharmacyVoiceContactKey = :pharmacyVoiceContactKey")})
public class Medication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "strength")
    private String strength;
    @Column(name = "strength_unit")
    private String strengthUnit;
    @Column(name = "dose_form")
    private String doseForm;
    @Column(name = "dose_quantity")
    private String doseQuantity;
    @Column(name = "dose_quantity_unit")
    private String doseQuantityUnit;
    @Column(name = "route")
    private String route;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Column(name = "frequency")
    private String frequency;
    @Column(name = "custom_instructions")
    private String customInstructions;
    @Column(name = "fill_method")
    private String fillMethod;
    @Column(name = "refills_total")
    private String refillsTotal;
    @Column(name = "refills_remaining")
    private String refillsRemaining;
    @Column(name = "pharmacy_name")
    private String pharmacyName;
    @Basic(optional = false)
    @Column(name = "pharmacy_address_key")
    private BigDecimal pharmacyAddressKey;
    @Basic(optional = false)
    @Column(name = "pharmacy_voice_contact_key")
    private BigDecimal pharmacyVoiceContactKey;

    public Medication() {
    }

    public Medication(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public Medication(BigDecimal clinicalItemKey, BigDecimal pharmacyAddressKey, BigDecimal pharmacyVoiceContactKey) {
        this.clinicalItemKey = clinicalItemKey;
        this.pharmacyAddressKey = pharmacyAddressKey;
        this.pharmacyVoiceContactKey = pharmacyVoiceContactKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getStrengthUnit() {
        return strengthUnit;
    }

    public void setStrengthUnit(String strengthUnit) {
        this.strengthUnit = strengthUnit;
    }

    public String getDoseForm() {
        return doseForm;
    }

    public void setDoseForm(String doseForm) {
        this.doseForm = doseForm;
    }

    public String getDoseQuantity() {
        return doseQuantity;
    }

    public void setDoseQuantity(String doseQuantity) {
        this.doseQuantity = doseQuantity;
    }

    public String getDoseQuantityUnit() {
        return doseQuantityUnit;
    }

    public void setDoseQuantityUnit(String doseQuantityUnit) {
        this.doseQuantityUnit = doseQuantityUnit;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getCustomInstructions() {
        return customInstructions;
    }

    public void setCustomInstructions(String customInstructions) {
        this.customInstructions = customInstructions;
    }

    public String getFillMethod() {
        return fillMethod;
    }

    public void setFillMethod(String fillMethod) {
        this.fillMethod = fillMethod;
    }

    public String getRefillsTotal() {
        return refillsTotal;
    }

    public void setRefillsTotal(String refillsTotal) {
        this.refillsTotal = refillsTotal;
    }

    public String getRefillsRemaining() {
        return refillsRemaining;
    }

    public void setRefillsRemaining(String refillsRemaining) {
        this.refillsRemaining = refillsRemaining;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public BigDecimal getPharmacyAddressKey() {
        return pharmacyAddressKey;
    }

    public void setPharmacyAddressKey(BigDecimal pharmacyAddressKey) {
        this.pharmacyAddressKey = pharmacyAddressKey;
    }

    public BigDecimal getPharmacyVoiceContactKey() {
        return pharmacyVoiceContactKey;
    }

    public void setPharmacyVoiceContactKey(BigDecimal pharmacyVoiceContactKey) {
        this.pharmacyVoiceContactKey = pharmacyVoiceContactKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemKey != null ? clinicalItemKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medication)) {
            return false;
        }
        Medication other = (Medication) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Medication[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
