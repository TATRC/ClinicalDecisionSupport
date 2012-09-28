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
@Table(name = "nhin_request")
@NamedQueries({@NamedQuery(name = "NhinRequest.findAll", query = "SELECT n FROM NhinRequest n"), @NamedQuery(name = "NhinRequest.findByNhinRequestKey", query = "SELECT n FROM NhinRequest n WHERE n.nhinRequestKey = :nhinRequestKey"), @NamedQuery(name = "NhinRequest.findBySubjectkey", query = "SELECT n FROM NhinRequest n WHERE n.subjectkey = :subjectkey"), @NamedQuery(name = "NhinRequest.findByLastname", query = "SELECT n FROM NhinRequest n WHERE n.lastname = :lastname"), @NamedQuery(name = "NhinRequest.findByFirstname", query = "SELECT n FROM NhinRequest n WHERE n.firstname = :firstname"), @NamedQuery(name = "NhinRequest.findByMiddlename", query = "SELECT n FROM NhinRequest n WHERE n.middlename = :middlename"), @NamedQuery(name = "NhinRequest.findByDob", query = "SELECT n FROM NhinRequest n WHERE n.dob = :dob"), @NamedQuery(name = "NhinRequest.findByGender", query = "SELECT n FROM NhinRequest n WHERE n.gender = :gender"), @NamedQuery(name = "NhinRequest.findByAddress1", query = "SELECT n FROM NhinRequest n WHERE n.address1 = :address1"), @NamedQuery(name = "NhinRequest.findByAddress2", query = "SELECT n FROM NhinRequest n WHERE n.address2 = :address2"), @NamedQuery(name = "NhinRequest.findByCity", query = "SELECT n FROM NhinRequest n WHERE n.city = :city"), @NamedQuery(name = "NhinRequest.findByState", query = "SELECT n FROM NhinRequest n WHERE n.state = :state"), @NamedQuery(name = "NhinRequest.findByPostal", query = "SELECT n FROM NhinRequest n WHERE n.postal = :postal"), @NamedQuery(name = "NhinRequest.findByLocalId", query = "SELECT n FROM NhinRequest n WHERE n.localId = :localId"), @NamedQuery(name = "NhinRequest.findByReqStatusKey", query = "SELECT n FROM NhinRequest n WHERE n.reqStatusKey = :reqStatusKey")})
public class NhinRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nhin_request_key")
    private BigDecimal nhinRequestKey;
    @Basic(optional = false)
    @Column(name = "subjectkey")
    private int subjectkey;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "middlename")
    private String middlename;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "postal")
    private String postal;
    @Basic(optional = false)
    @Column(name = "local_id")
    private String localId;
    @Basic(optional = false)
    @Column(name = "req_status_key")
    private BigDecimal reqStatusKey;

    public NhinRequest() {
    }

    public NhinRequest(BigDecimal nhinRequestKey) {
        this.nhinRequestKey = nhinRequestKey;
    }

    public NhinRequest(BigDecimal nhinRequestKey, int subjectkey, String localId, BigDecimal reqStatusKey) {
        this.nhinRequestKey = nhinRequestKey;
        this.subjectkey = subjectkey;
        this.localId = localId;
        this.reqStatusKey = reqStatusKey;
    }

    public BigDecimal getNhinRequestKey() {
        return nhinRequestKey;
    }

    public void setNhinRequestKey(BigDecimal nhinRequestKey) {
        this.nhinRequestKey = nhinRequestKey;
    }

    public int getSubjectkey() {
        return subjectkey;
    }

    public void setSubjectkey(int subjectkey) {
        this.subjectkey = subjectkey;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public BigDecimal getReqStatusKey() {
        return reqStatusKey;
    }

    public void setReqStatusKey(BigDecimal reqStatusKey) {
        this.reqStatusKey = reqStatusKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nhinRequestKey != null ? nhinRequestKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhinRequest)) {
            return false;
        }
        NhinRequest other = (NhinRequest) object;
        if ((this.nhinRequestKey == null && other.nhinRequestKey != null) || (this.nhinRequestKey != null && !this.nhinRequestKey.equals(other.nhinRequestKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.NhinRequest[nhinRequestKey=" + nhinRequestKey + "]";
    }

}
