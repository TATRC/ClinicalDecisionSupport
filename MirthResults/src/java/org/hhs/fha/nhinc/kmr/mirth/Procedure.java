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
@Table(name = "procedure")
@NamedQueries({@NamedQuery(name = "Procedure.findAll", query = "SELECT p FROM Procedure p"), @NamedQuery(name = "Procedure.findByClinicalItemKey", query = "SELECT p FROM Procedure p WHERE p.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Procedure.findByCodeMethod", query = "SELECT p FROM Procedure p WHERE p.codeMethod = :codeMethod"), @NamedQuery(name = "Procedure.findByFunctionalType", query = "SELECT p FROM Procedure p WHERE p.functionalType = :functionalType"), @NamedQuery(name = "Procedure.findByDuration", query = "SELECT p FROM Procedure p WHERE p.duration = :duration"), @NamedQuery(name = "Procedure.findByAnesthesiaCode", query = "SELECT p FROM Procedure p WHERE p.anesthesiaCode = :anesthesiaCode"), @NamedQuery(name = "Procedure.findByAnesthesiaDuration", query = "SELECT p FROM Procedure p WHERE p.anesthesiaDuration = :anesthesiaDuration"), @NamedQuery(name = "Procedure.findByPriority", query = "SELECT p FROM Procedure p WHERE p.priority = :priority"), @NamedQuery(name = "Procedure.findByActionCode", query = "SELECT p FROM Procedure p WHERE p.actionCode = :actionCode")})
public class Procedure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "code_method")
    private String codeMethod;
    @Column(name = "functional_type")
    private String functionalType;
    @Column(name = "duration")
    private String duration;
    @Column(name = "anesthesia_code")
    private String anesthesiaCode;
    @Column(name = "anesthesia_duration")
    private String anesthesiaDuration;
    @Column(name = "priority")
    private String priority;
    @Column(name = "action_code")
    private String actionCode;

    public Procedure() {
    }

    public Procedure(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getCodeMethod() {
        return codeMethod;
    }

    public void setCodeMethod(String codeMethod) {
        this.codeMethod = codeMethod;
    }

    public String getFunctionalType() {
        return functionalType;
    }

    public void setFunctionalType(String functionalType) {
        this.functionalType = functionalType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAnesthesiaCode() {
        return anesthesiaCode;
    }

    public void setAnesthesiaCode(String anesthesiaCode) {
        this.anesthesiaCode = anesthesiaCode;
    }

    public String getAnesthesiaDuration() {
        return anesthesiaDuration;
    }

    public void setAnesthesiaDuration(String anesthesiaDuration) {
        this.anesthesiaDuration = anesthesiaDuration;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
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
        if (!(object instanceof Procedure)) {
            return false;
        }
        Procedure other = (Procedure) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Procedure[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
