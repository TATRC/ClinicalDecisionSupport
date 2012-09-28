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
@Table(name = "timing")
@NamedQueries({@NamedQuery(name = "Timing.findAll", query = "SELECT t FROM Timing t"), @NamedQuery(name = "Timing.findByClinicalItemKey", query = "SELECT t FROM Timing t WHERE t.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Timing.findByQuantity", query = "SELECT t FROM Timing t WHERE t.quantity = :quantity"), @NamedQuery(name = "Timing.findByQuantityUnits", query = "SELECT t FROM Timing t WHERE t.quantityUnits = :quantityUnits"), @NamedQuery(name = "Timing.findByInterval", query = "SELECT t FROM Timing t WHERE t.interval = :interval"), @NamedQuery(name = "Timing.findByDuration", query = "SELECT t FROM Timing t WHERE t.duration = :duration"), @NamedQuery(name = "Timing.findByCondition", query = "SELECT t FROM Timing t WHERE t.condition = :condition"), @NamedQuery(name = "Timing.findByInstruction", query = "SELECT t FROM Timing t WHERE t.instruction = :instruction"), @NamedQuery(name = "Timing.findByOccurrenceDuration", query = "SELECT t FROM Timing t WHERE t.occurrenceDuration = :occurrenceDuration"), @NamedQuery(name = "Timing.findByTotalOccurrences", query = "SELECT t FROM Timing t WHERE t.totalOccurrences = :totalOccurrences")})
public class Timing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "quantity_units")
    private String quantityUnits;
    @Column(name = "interval")
    private String interval;
    @Column(name = "duration")
    private String duration;
    @Column(name = "condition")
    private String condition;
    @Column(name = "instruction")
    private String instruction;
    @Column(name = "occurrence_duration")
    private String occurrenceDuration;
    @Column(name = "total_occurrences")
    private String totalOccurrences;

    public Timing() {
    }

    public Timing(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnits() {
        return quantityUnits;
    }

    public void setQuantityUnits(String quantityUnits) {
        this.quantityUnits = quantityUnits;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getOccurrenceDuration() {
        return occurrenceDuration;
    }

    public void setOccurrenceDuration(String occurrenceDuration) {
        this.occurrenceDuration = occurrenceDuration;
    }

    public String getTotalOccurrences() {
        return totalOccurrences;
    }

    public void setTotalOccurrences(String totalOccurrences) {
        this.totalOccurrences = totalOccurrences;
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
        if (!(object instanceof Timing)) {
            return false;
        }
        Timing other = (Timing) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Timing[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
