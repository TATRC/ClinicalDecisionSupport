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
@Table(name = "observation")
@NamedQueries({@NamedQuery(name = "Observation.findAll", query = "SELECT o FROM Observation o"), @NamedQuery(name = "Observation.findByClinicalItemKey", query = "SELECT o FROM Observation o WHERE o.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Observation.findBySubCode", query = "SELECT o FROM Observation o WHERE o.subCode = :subCode"), @NamedQuery(name = "Observation.findByMethod", query = "SELECT o FROM Observation o WHERE o.method = :method"), @NamedQuery(name = "Observation.findByValue", query = "SELECT o FROM Observation o WHERE o.value = :value"), @NamedQuery(name = "Observation.findByValueType", query = "SELECT o FROM Observation o WHERE o.valueType = :valueType"), @NamedQuery(name = "Observation.findByValueNumber", query = "SELECT o FROM Observation o WHERE o.valueNumber = :valueNumber"), @NamedQuery(name = "Observation.findByValueDate", query = "SELECT o FROM Observation o WHERE o.valueDate = :valueDate"), @NamedQuery(name = "Observation.findByValueInRange", query = "SELECT o FROM Observation o WHERE o.valueInRange = :valueInRange"), @NamedQuery(name = "Observation.findByReferenceRange", query = "SELECT o FROM Observation o WHERE o.referenceRange = :referenceRange"), @NamedQuery(name = "Observation.findByUnits", query = "SELECT o FROM Observation o WHERE o.units = :units")})
public class Observation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "sub_code")
    private String subCode;
    @Column(name = "method")
    private String method;
    @Column(name = "value")
    private String value;
    @Column(name = "value_type")
    private String valueType;
    @Column(name = "value_number")
    private BigDecimal valueNumber;
    @Column(name = "value_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valueDate;
    @Column(name = "value_in_range")
    private Short valueInRange;
    @Column(name = "reference_range")
    private String referenceRange;
    @Column(name = "units")
    private String units;

    public Observation() {
    }

    public Observation(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public BigDecimal getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(BigDecimal valueNumber) {
        this.valueNumber = valueNumber;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Short getValueInRange() {
        return valueInRange;
    }

    public void setValueInRange(Short valueInRange) {
        this.valueInRange = valueInRange;
    }

    public String getReferenceRange() {
        return referenceRange;
    }

    public void setReferenceRange(String referenceRange) {
        this.referenceRange = referenceRange;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
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
        if (!(object instanceof Observation)) {
            return false;
        }
        Observation other = (Observation) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Observation[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
