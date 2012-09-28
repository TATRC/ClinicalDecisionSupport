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
@Table(name = "clinical_item_access")
@NamedQueries({@NamedQuery(name = "ClinicalItemAccess.findAll", query = "SELECT c FROM ClinicalItemAccess c"), @NamedQuery(name = "ClinicalItemAccess.findByClinicalItemAccessKey", query = "SELECT c FROM ClinicalItemAccess c WHERE c.clinicalItemAccessKey = :clinicalItemAccessKey"), @NamedQuery(name = "ClinicalItemAccess.findByClinicalItemAccessTypeKey", query = "SELECT c FROM ClinicalItemAccess c WHERE c.clinicalItemAccessTypeKey = :clinicalItemAccessTypeKey"), @NamedQuery(name = "ClinicalItemAccess.findByPersonKey", query = "SELECT c FROM ClinicalItemAccess c WHERE c.personKey = :personKey"), @NamedQuery(name = "ClinicalItemAccess.findByClinicalItemKey", query = "SELECT c FROM ClinicalItemAccess c WHERE c.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "ClinicalItemAccess.findByAccessDate", query = "SELECT c FROM ClinicalItemAccess c WHERE c.accessDate = :accessDate"), @NamedQuery(name = "ClinicalItemAccess.findByFileName", query = "SELECT c FROM ClinicalItemAccess c WHERE c.fileName = :fileName")})
public class ClinicalItemAccess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_access_key")
    private Integer clinicalItemAccessKey;
    @Basic(optional = false)
    @Column(name = "clinical_item_access_type_key")
    private BigDecimal clinicalItemAccessTypeKey;
    @Basic(optional = false)
    @Column(name = "person_key")
    private int personKey;
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "access_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessDate;
    @Column(name = "file_name")
    private String fileName;

    public ClinicalItemAccess() {
    }

    public ClinicalItemAccess(Integer clinicalItemAccessKey) {
        this.clinicalItemAccessKey = clinicalItemAccessKey;
    }

    public ClinicalItemAccess(Integer clinicalItemAccessKey, BigDecimal clinicalItemAccessTypeKey, int personKey, BigDecimal clinicalItemKey) {
        this.clinicalItemAccessKey = clinicalItemAccessKey;
        this.clinicalItemAccessTypeKey = clinicalItemAccessTypeKey;
        this.personKey = personKey;
        this.clinicalItemKey = clinicalItemKey;
    }

    public Integer getClinicalItemAccessKey() {
        return clinicalItemAccessKey;
    }

    public void setClinicalItemAccessKey(Integer clinicalItemAccessKey) {
        this.clinicalItemAccessKey = clinicalItemAccessKey;
    }

    public BigDecimal getClinicalItemAccessTypeKey() {
        return clinicalItemAccessTypeKey;
    }

    public void setClinicalItemAccessTypeKey(BigDecimal clinicalItemAccessTypeKey) {
        this.clinicalItemAccessTypeKey = clinicalItemAccessTypeKey;
    }

    public int getPersonKey() {
        return personKey;
    }

    public void setPersonKey(int personKey) {
        this.personKey = personKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemAccessKey != null ? clinicalItemAccessKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalItemAccess)) {
            return false;
        }
        ClinicalItemAccess other = (ClinicalItemAccess) object;
        if ((this.clinicalItemAccessKey == null && other.clinicalItemAccessKey != null) || (this.clinicalItemAccessKey != null && !this.clinicalItemAccessKey.equals(other.clinicalItemAccessKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItemAccess[clinicalItemAccessKey=" + clinicalItemAccessKey + "]";
    }

}
