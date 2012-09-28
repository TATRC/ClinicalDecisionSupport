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
@Table(name = "diagnosis")
@NamedQueries({@NamedQuery(name = "Diagnosis.findAll", query = "SELECT d FROM Diagnosis d"), @NamedQuery(name = "Diagnosis.findByClinicalItemKey", query = "SELECT d FROM Diagnosis d WHERE d.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Diagnosis.findByCodeMethod", query = "SELECT d FROM Diagnosis d WHERE d.codeMethod = :codeMethod"), @NamedQuery(name = "Diagnosis.findByClassification", query = "SELECT d FROM Diagnosis d WHERE d.classification = :classification"), @NamedQuery(name = "Diagnosis.findByPriority", query = "SELECT d FROM Diagnosis d WHERE d.priority = :priority"), @NamedQuery(name = "Diagnosis.findByType", query = "SELECT d FROM Diagnosis d WHERE d.type = :type"), @NamedQuery(name = "Diagnosis.findByMajorCategoryKey", query = "SELECT d FROM Diagnosis d WHERE d.majorCategoryKey = :majorCategoryKey"), @NamedQuery(name = "Diagnosis.findByRelatedGroupKey", query = "SELECT d FROM Diagnosis d WHERE d.relatedGroupKey = :relatedGroupKey"), @NamedQuery(name = "Diagnosis.findByActionCode", query = "SELECT d FROM Diagnosis d WHERE d.actionCode = :actionCode")})
public class Diagnosis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "code_method")
    private String codeMethod;
    @Column(name = "classification")
    private String classification;
    @Column(name = "priority")
    private String priority;
    @Column(name = "type")
    private String type;
    @Column(name = "major_category_key")
    private BigDecimal majorCategoryKey;
    @Column(name = "related_group_key")
    private BigDecimal relatedGroupKey;
    @Column(name = "action_code")
    private String actionCode;

    public Diagnosis() {
    }

    public Diagnosis(BigDecimal clinicalItemKey) {
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMajorCategoryKey() {
        return majorCategoryKey;
    }

    public void setMajorCategoryKey(BigDecimal majorCategoryKey) {
        this.majorCategoryKey = majorCategoryKey;
    }

    public BigDecimal getRelatedGroupKey() {
        return relatedGroupKey;
    }

    public void setRelatedGroupKey(BigDecimal relatedGroupKey) {
        this.relatedGroupKey = relatedGroupKey;
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
        if (!(object instanceof Diagnosis)) {
            return false;
        }
        Diagnosis other = (Diagnosis) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Diagnosis[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
