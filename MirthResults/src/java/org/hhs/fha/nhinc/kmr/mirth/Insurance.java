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
@Table(name = "insurance")
@NamedQueries({@NamedQuery(name = "Insurance.findAll", query = "SELECT i FROM Insurance i"), @NamedQuery(name = "Insurance.findByInsurancekey", query = "SELECT i FROM Insurance i WHERE i.insurancekey = :insurancekey"), @NamedQuery(name = "Insurance.findBySubjectkey", query = "SELECT i FROM Insurance i WHERE i.subjectkey = :subjectkey"), @NamedQuery(name = "Insurance.findByPlanid", query = "SELECT i FROM Insurance i WHERE i.planid = :planid"), @NamedQuery(name = "Insurance.findByCompanyid", query = "SELECT i FROM Insurance i WHERE i.companyid = :companyid"), @NamedQuery(name = "Insurance.findByCompanyname", query = "SELECT i FROM Insurance i WHERE i.companyname = :companyname"), @NamedQuery(name = "Insurance.findByGroupnumber", query = "SELECT i FROM Insurance i WHERE i.groupnumber = :groupnumber"), @NamedQuery(name = "Insurance.findByGroupname", query = "SELECT i FROM Insurance i WHERE i.groupname = :groupname"), @NamedQuery(name = "Insurance.findByEmployerid", query = "SELECT i FROM Insurance i WHERE i.employerid = :employerid"), @NamedQuery(name = "Insurance.findByEmployername", query = "SELECT i FROM Insurance i WHERE i.employername = :employername"), @NamedQuery(name = "Insurance.findByPlantype", query = "SELECT i FROM Insurance i WHERE i.plantype = :plantype"), @NamedQuery(name = "Insurance.findByInsuredrelationship", query = "SELECT i FROM Insurance i WHERE i.insuredrelationship = :insuredrelationship"), @NamedQuery(name = "Insurance.findByInsureddob", query = "SELECT i FROM Insurance i WHERE i.insureddob = :insureddob"), @NamedQuery(name = "Insurance.findByInsuredgender", query = "SELECT i FROM Insurance i WHERE i.insuredgender = :insuredgender"), @NamedQuery(name = "Insurance.findByInsuredidnumber", query = "SELECT i FROM Insurance i WHERE i.insuredidnumber = :insuredidnumber"), @NamedQuery(name = "Insurance.findByInsuredbirthplace", query = "SELECT i FROM Insurance i WHERE i.insuredbirthplace = :insuredbirthplace"), @NamedQuery(name = "Insurance.findByCompanyplancode", query = "SELECT i FROM Insurance i WHERE i.companyplancode = :companyplancode"), @NamedQuery(name = "Insurance.findByPolicynumber", query = "SELECT i FROM Insurance i WHERE i.policynumber = :policynumber"), @NamedQuery(name = "Insurance.findByPolicylimitdays", query = "SELECT i FROM Insurance i WHERE i.policylimitdays = :policylimitdays"), @NamedQuery(name = "Insurance.findByPolicylimitamount", query = "SELECT i FROM Insurance i WHERE i.policylimitamount = :policylimitamount"), @NamedQuery(name = "Insurance.findByCoveragetype", query = "SELECT i FROM Insurance i WHERE i.coveragetype = :coveragetype"), @NamedQuery(name = "Insurance.findByEffectivedate", query = "SELECT i FROM Insurance i WHERE i.effectivedate = :effectivedate"), @NamedQuery(name = "Insurance.findByExpirationdate", query = "SELECT i FROM Insurance i WHERE i.expirationdate = :expirationdate"), @NamedQuery(name = "Insurance.findByRowversion", query = "SELECT i FROM Insurance i WHERE i.rowversion = :rowversion"), @NamedQuery(name = "Insurance.findByCurrentemployeraddresskey", query = "SELECT i FROM Insurance i WHERE i.currentemployeraddresskey = :currentemployeraddresskey"), @NamedQuery(name = "Insurance.findByCurrentinsurednamekey", query = "SELECT i FROM Insurance i WHERE i.currentinsurednamekey = :currentinsurednamekey"), @NamedQuery(name = "Insurance.findByCurrentinsuredaddresskey", query = "SELECT i FROM Insurance i WHERE i.currentinsuredaddresskey = :currentinsuredaddresskey"), @NamedQuery(name = "Insurance.findByCurrentinsurancecompanycontactnamekey", query = "SELECT i FROM Insurance i WHERE i.currentinsurancecompanycontactnamekey = :currentinsurancecompanycontactnamekey"), @NamedQuery(name = "Insurance.findByCurrentinsurancecompanyaddresskey", query = "SELECT i FROM Insurance i WHERE i.currentinsurancecompanyaddresskey = :currentinsurancecompanyaddresskey"), @NamedQuery(name = "Insurance.findByCurrentinsurancecompanyphonekey", query = "SELECT i FROM Insurance i WHERE i.currentinsurancecompanyphonekey = :currentinsurancecompanyphonekey")})
public class Insurance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "insurancekey")
    private BigDecimal insurancekey;
    @Column(name = "subjectkey")
    private Integer subjectkey;
    @Column(name = "planid")
    private String planid;
    @Column(name = "companyid")
    private String companyid;
    @Column(name = "companyname")
    private String companyname;
    @Column(name = "groupnumber")
    private String groupnumber;
    @Column(name = "groupname")
    private String groupname;
    @Column(name = "employerid")
    private String employerid;
    @Column(name = "employername")
    private String employername;
    @Column(name = "plantype")
    private String plantype;
    @Column(name = "insuredrelationship")
    private String insuredrelationship;
    @Column(name = "insureddob")
    @Temporal(TemporalType.DATE)
    private Date insureddob;
    @Column(name = "insuredgender")
    private String insuredgender;
    @Column(name = "insuredidnumber")
    private String insuredidnumber;
    @Column(name = "insuredbirthplace")
    private String insuredbirthplace;
    @Column(name = "companyplancode")
    private String companyplancode;
    @Column(name = "policynumber")
    private String policynumber;
    @Column(name = "policylimitdays")
    private String policylimitdays;
    @Column(name = "policylimitamount")
    private String policylimitamount;
    @Column(name = "coveragetype")
    private String coveragetype;
    @Column(name = "effectivedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectivedate;
    @Column(name = "expirationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationdate;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Column(name = "currentemployeraddresskey")
    private BigDecimal currentemployeraddresskey;
    @Column(name = "currentinsurednamekey")
    private BigDecimal currentinsurednamekey;
    @Column(name = "currentinsuredaddresskey")
    private BigDecimal currentinsuredaddresskey;
    @Column(name = "currentinsurancecompanycontactnamekey")
    private BigDecimal currentinsurancecompanycontactnamekey;
    @Column(name = "currentinsurancecompanyaddresskey")
    private BigDecimal currentinsurancecompanyaddresskey;
    @Column(name = "currentinsurancecompanyphonekey")
    private BigDecimal currentinsurancecompanyphonekey;

    public Insurance() {
    }

    public Insurance(BigDecimal insurancekey) {
        this.insurancekey = insurancekey;
    }

    public Insurance(BigDecimal insurancekey, short rowversion) {
        this.insurancekey = insurancekey;
        this.rowversion = rowversion;
    }

    public BigDecimal getInsurancekey() {
        return insurancekey;
    }

    public void setInsurancekey(BigDecimal insurancekey) {
        this.insurancekey = insurancekey;
    }

    public Integer getSubjectkey() {
        return subjectkey;
    }

    public void setSubjectkey(Integer subjectkey) {
        this.subjectkey = subjectkey;
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getEmployerid() {
        return employerid;
    }

    public void setEmployerid(String employerid) {
        this.employerid = employerid;
    }

    public String getEmployername() {
        return employername;
    }

    public void setEmployername(String employername) {
        this.employername = employername;
    }

    public String getPlantype() {
        return plantype;
    }

    public void setPlantype(String plantype) {
        this.plantype = plantype;
    }

    public String getInsuredrelationship() {
        return insuredrelationship;
    }

    public void setInsuredrelationship(String insuredrelationship) {
        this.insuredrelationship = insuredrelationship;
    }

    public Date getInsureddob() {
        return insureddob;
    }

    public void setInsureddob(Date insureddob) {
        this.insureddob = insureddob;
    }

    public String getInsuredgender() {
        return insuredgender;
    }

    public void setInsuredgender(String insuredgender) {
        this.insuredgender = insuredgender;
    }

    public String getInsuredidnumber() {
        return insuredidnumber;
    }

    public void setInsuredidnumber(String insuredidnumber) {
        this.insuredidnumber = insuredidnumber;
    }

    public String getInsuredbirthplace() {
        return insuredbirthplace;
    }

    public void setInsuredbirthplace(String insuredbirthplace) {
        this.insuredbirthplace = insuredbirthplace;
    }

    public String getCompanyplancode() {
        return companyplancode;
    }

    public void setCompanyplancode(String companyplancode) {
        this.companyplancode = companyplancode;
    }

    public String getPolicynumber() {
        return policynumber;
    }

    public void setPolicynumber(String policynumber) {
        this.policynumber = policynumber;
    }

    public String getPolicylimitdays() {
        return policylimitdays;
    }

    public void setPolicylimitdays(String policylimitdays) {
        this.policylimitdays = policylimitdays;
    }

    public String getPolicylimitamount() {
        return policylimitamount;
    }

    public void setPolicylimitamount(String policylimitamount) {
        this.policylimitamount = policylimitamount;
    }

    public String getCoveragetype() {
        return coveragetype;
    }

    public void setCoveragetype(String coveragetype) {
        this.coveragetype = coveragetype;
    }

    public Date getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    public BigDecimal getCurrentemployeraddresskey() {
        return currentemployeraddresskey;
    }

    public void setCurrentemployeraddresskey(BigDecimal currentemployeraddresskey) {
        this.currentemployeraddresskey = currentemployeraddresskey;
    }

    public BigDecimal getCurrentinsurednamekey() {
        return currentinsurednamekey;
    }

    public void setCurrentinsurednamekey(BigDecimal currentinsurednamekey) {
        this.currentinsurednamekey = currentinsurednamekey;
    }

    public BigDecimal getCurrentinsuredaddresskey() {
        return currentinsuredaddresskey;
    }

    public void setCurrentinsuredaddresskey(BigDecimal currentinsuredaddresskey) {
        this.currentinsuredaddresskey = currentinsuredaddresskey;
    }

    public BigDecimal getCurrentinsurancecompanycontactnamekey() {
        return currentinsurancecompanycontactnamekey;
    }

    public void setCurrentinsurancecompanycontactnamekey(BigDecimal currentinsurancecompanycontactnamekey) {
        this.currentinsurancecompanycontactnamekey = currentinsurancecompanycontactnamekey;
    }

    public BigDecimal getCurrentinsurancecompanyaddresskey() {
        return currentinsurancecompanyaddresskey;
    }

    public void setCurrentinsurancecompanyaddresskey(BigDecimal currentinsurancecompanyaddresskey) {
        this.currentinsurancecompanyaddresskey = currentinsurancecompanyaddresskey;
    }

    public BigDecimal getCurrentinsurancecompanyphonekey() {
        return currentinsurancecompanyphonekey;
    }

    public void setCurrentinsurancecompanyphonekey(BigDecimal currentinsurancecompanyphonekey) {
        this.currentinsurancecompanyphonekey = currentinsurancecompanyphonekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insurancekey != null ? insurancekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insurance)) {
            return false;
        }
        Insurance other = (Insurance) object;
        if ((this.insurancekey == null && other.insurancekey != null) || (this.insurancekey != null && !this.insurancekey.equals(other.insurancekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Insurance[insurancekey=" + insurancekey + "]";
    }

}
