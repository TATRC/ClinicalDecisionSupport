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
import javax.persistence.Lob;
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
@Table(name = "subject_group_member")
@NamedQueries({@NamedQuery(name = "SubjectGroupMember.findAll", query = "SELECT s FROM SubjectGroupMember s"), @NamedQuery(name = "SubjectGroupMember.findBySubjectGroupMemberKey", query = "SELECT s FROM SubjectGroupMember s WHERE s.subjectGroupMemberKey = :subjectGroupMemberKey"), @NamedQuery(name = "SubjectGroupMember.findBySubjectGroupKey", query = "SELECT s FROM SubjectGroupMember s WHERE s.subjectGroupKey = :subjectGroupKey"), @NamedQuery(name = "SubjectGroupMember.findBySubjectKey", query = "SELECT s FROM SubjectGroupMember s WHERE s.subjectKey = :subjectKey"), @NamedQuery(name = "SubjectGroupMember.findByAddedTime", query = "SELECT s FROM SubjectGroupMember s WHERE s.addedTime = :addedTime"), @NamedQuery(name = "SubjectGroupMember.findByContextSignifier", query = "SELECT s FROM SubjectGroupMember s WHERE s.contextSignifier = :contextSignifier"), @NamedQuery(name = "SubjectGroupMember.findBySubjectGroupStatusKey", query = "SELECT s FROM SubjectGroupMember s WHERE s.subjectGroupStatusKey = :subjectGroupStatusKey")})
public class SubjectGroupMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subject_group_member_key")
    private BigDecimal subjectGroupMemberKey;
    @Basic(optional = false)
    @Column(name = "subject_group_key")
    private BigDecimal subjectGroupKey;
    @Basic(optional = false)
    @Column(name = "subject_key")
    private int subjectKey;
    @Basic(optional = false)
    @Column(name = "added_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedTime;
    @Column(name = "context_signifier")
    private String contextSignifier;
    @Lob
    @Column(name = "context_data")
    private String contextData;
    @Column(name = "subject_group_status_key")
    private BigDecimal subjectGroupStatusKey;

    public SubjectGroupMember() {
    }

    public SubjectGroupMember(BigDecimal subjectGroupMemberKey) {
        this.subjectGroupMemberKey = subjectGroupMemberKey;
    }

    public SubjectGroupMember(BigDecimal subjectGroupMemberKey, BigDecimal subjectGroupKey, int subjectKey, Date addedTime) {
        this.subjectGroupMemberKey = subjectGroupMemberKey;
        this.subjectGroupKey = subjectGroupKey;
        this.subjectKey = subjectKey;
        this.addedTime = addedTime;
    }

    public BigDecimal getSubjectGroupMemberKey() {
        return subjectGroupMemberKey;
    }

    public void setSubjectGroupMemberKey(BigDecimal subjectGroupMemberKey) {
        this.subjectGroupMemberKey = subjectGroupMemberKey;
    }

    public BigDecimal getSubjectGroupKey() {
        return subjectGroupKey;
    }

    public void setSubjectGroupKey(BigDecimal subjectGroupKey) {
        this.subjectGroupKey = subjectGroupKey;
    }

    public int getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(int subjectKey) {
        this.subjectKey = subjectKey;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public String getContextSignifier() {
        return contextSignifier;
    }

    public void setContextSignifier(String contextSignifier) {
        this.contextSignifier = contextSignifier;
    }

    public String getContextData() {
        return contextData;
    }

    public void setContextData(String contextData) {
        this.contextData = contextData;
    }

    public BigDecimal getSubjectGroupStatusKey() {
        return subjectGroupStatusKey;
    }

    public void setSubjectGroupStatusKey(BigDecimal subjectGroupStatusKey) {
        this.subjectGroupStatusKey = subjectGroupStatusKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectGroupMemberKey != null ? subjectGroupMemberKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectGroupMember)) {
            return false;
        }
        SubjectGroupMember other = (SubjectGroupMember) object;
        if ((this.subjectGroupMemberKey == null && other.subjectGroupMemberKey != null) || (this.subjectGroupMemberKey != null && !this.subjectGroupMemberKey.equals(other.subjectGroupMemberKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectGroupMember[subjectGroupMemberKey=" + subjectGroupMemberKey + "]";
    }

}
