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
@Table(name = "subject_group_status")
@NamedQueries({@NamedQuery(name = "SubjectGroupStatus.findAll", query = "SELECT s FROM SubjectGroupStatus s"), @NamedQuery(name = "SubjectGroupStatus.findBySubjectGroupStatusKey", query = "SELECT s FROM SubjectGroupStatus s WHERE s.subjectGroupStatusKey = :subjectGroupStatusKey"), @NamedQuery(name = "SubjectGroupStatus.findByLabel", query = "SELECT s FROM SubjectGroupStatus s WHERE s.label = :label"), @NamedQuery(name = "SubjectGroupStatus.findByDescription", query = "SELECT s FROM SubjectGroupStatus s WHERE s.description = :description")})
public class SubjectGroupStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subject_group_status_key")
    private BigDecimal subjectGroupStatusKey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "description")
    private String description;

    public SubjectGroupStatus() {
    }

    public SubjectGroupStatus(BigDecimal subjectGroupStatusKey) {
        this.subjectGroupStatusKey = subjectGroupStatusKey;
    }

    public SubjectGroupStatus(BigDecimal subjectGroupStatusKey, String label) {
        this.subjectGroupStatusKey = subjectGroupStatusKey;
        this.label = label;
    }

    public BigDecimal getSubjectGroupStatusKey() {
        return subjectGroupStatusKey;
    }

    public void setSubjectGroupStatusKey(BigDecimal subjectGroupStatusKey) {
        this.subjectGroupStatusKey = subjectGroupStatusKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectGroupStatusKey != null ? subjectGroupStatusKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectGroupStatus)) {
            return false;
        }
        SubjectGroupStatus other = (SubjectGroupStatus) object;
        if ((this.subjectGroupStatusKey == null && other.subjectGroupStatusKey != null) || (this.subjectGroupStatusKey != null && !this.subjectGroupStatusKey.equals(other.subjectGroupStatusKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectGroupStatus[subjectGroupStatusKey=" + subjectGroupStatusKey + "]";
    }

}
