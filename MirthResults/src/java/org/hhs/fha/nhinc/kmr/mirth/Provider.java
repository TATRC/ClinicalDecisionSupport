/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
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
@Table(name = "provider")
@NamedQueries({@NamedQuery(name = "Provider.findAll", query = "SELECT p FROM Provider p"), @NamedQuery(name = "Provider.findByProviderkey", query = "SELECT p FROM Provider p WHERE p.providerkey = :providerkey"), @NamedQuery(name = "Provider.findByLastname", query = "SELECT p FROM Provider p WHERE p.lastname = :lastname"), @NamedQuery(name = "Provider.findByFirstname", query = "SELECT p FROM Provider p WHERE p.firstname = :firstname"), @NamedQuery(name = "Provider.findByMiddle", query = "SELECT p FROM Provider p WHERE p.middle = :middle"), @NamedQuery(name = "Provider.findBySuffix", query = "SELECT p FROM Provider p WHERE p.suffix = :suffix"), @NamedQuery(name = "Provider.findByPrefix", query = "SELECT p FROM Provider p WHERE p.prefix = :prefix"), @NamedQuery(name = "Provider.findByDegree", query = "SELECT p FROM Provider p WHERE p.degree = :degree"), @NamedQuery(name = "Provider.findByVoicecontact", query = "SELECT p FROM Provider p WHERE p.voicecontact = :voicecontact"), @NamedQuery(name = "Provider.findByEmailcontact", query = "SELECT p FROM Provider p WHERE p.emailcontact = :emailcontact"), @NamedQuery(name = "Provider.findByFaxcontact", query = "SELECT p FROM Provider p WHERE p.faxcontact = :faxcontact"), @NamedQuery(name = "Provider.findByRowversion", query = "SELECT p FROM Provider p WHERE p.rowversion = :rowversion"), @NamedQuery(name = "Provider.findByGuid", query = "SELECT p FROM Provider p WHERE p.guid = :guid"), @NamedQuery(name = "Provider.findBySpecialty1", query = "SELECT p FROM Provider p WHERE p.specialty1 = :specialty1"), @NamedQuery(name = "Provider.findBySpecialty2", query = "SELECT p FROM Provider p WHERE p.specialty2 = :specialty2"), @NamedQuery(name = "Provider.findByDob", query = "SELECT p FROM Provider p WHERE p.dob = :dob"), @NamedQuery(name = "Provider.findByGender", query = "SELECT p FROM Provider p WHERE p.gender = :gender"), @NamedQuery(name = "Provider.findByDeanumber", query = "SELECT p FROM Provider p WHERE p.deanumber = :deanumber"), @NamedQuery(name = "Provider.findByNpi", query = "SELECT p FROM Provider p WHERE p.npi = :npi"), @NamedQuery(name = "Provider.findByMedicallicensenumber", query = "SELECT p FROM Provider p WHERE p.medicallicensenumber = :medicallicensenumber"), @NamedQuery(name = "Provider.findByUpin", query = "SELECT p FROM Provider p WHERE p.upin = :upin")})
public class Provider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "providerkey")
    private Integer providerkey;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "middle")
    private String middle;
    @Column(name = "suffix")
    private String suffix;
    @Column(name = "prefix")
    private String prefix;
    @Column(name = "degree")
    private String degree;
    @Column(name = "voicecontact")
    private String voicecontact;
    @Column(name = "emailcontact")
    private String emailcontact;
    @Column(name = "faxcontact")
    private String faxcontact;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;
    @Column(name = "specialty1")
    private String specialty1;
    @Column(name = "specialty2")
    private String specialty2;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "deanumber")
    private String deanumber;
    @Column(name = "npi")
    private String npi;
    @Column(name = "medicallicensenumber")
    private String medicallicensenumber;
    @Column(name = "upin")
    private String upin;

    public Provider() {
    }

    public Provider(Integer providerkey) {
        this.providerkey = providerkey;
    }

    public Provider(Integer providerkey, String lastname, short rowversion, String guid) {
        this.providerkey = providerkey;
        this.lastname = lastname;
        this.rowversion = rowversion;
        this.guid = guid;
    }

    public Integer getProviderkey() {
        return providerkey;
    }

    public void setProviderkey(Integer providerkey) {
        this.providerkey = providerkey;
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

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getVoicecontact() {
        return voicecontact;
    }

    public void setVoicecontact(String voicecontact) {
        this.voicecontact = voicecontact;
    }

    public String getEmailcontact() {
        return emailcontact;
    }

    public void setEmailcontact(String emailcontact) {
        this.emailcontact = emailcontact;
    }

    public String getFaxcontact() {
        return faxcontact;
    }

    public void setFaxcontact(String faxcontact) {
        this.faxcontact = faxcontact;
    }

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSpecialty1() {
        return specialty1;
    }

    public void setSpecialty1(String specialty1) {
        this.specialty1 = specialty1;
    }

    public String getSpecialty2() {
        return specialty2;
    }

    public void setSpecialty2(String specialty2) {
        this.specialty2 = specialty2;
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

    public String getDeanumber() {
        return deanumber;
    }

    public void setDeanumber(String deanumber) {
        this.deanumber = deanumber;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getMedicallicensenumber() {
        return medicallicensenumber;
    }

    public void setMedicallicensenumber(String medicallicensenumber) {
        this.medicallicensenumber = medicallicensenumber;
    }

    public String getUpin() {
        return upin;
    }

    public void setUpin(String upin) {
        this.upin = upin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (providerkey != null ? providerkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provider)) {
            return false;
        }
        Provider other = (Provider) object;
        if ((this.providerkey == null && other.providerkey != null) || (this.providerkey != null && !this.providerkey.equals(other.providerkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Provider[providerkey=" + providerkey + "]";
    }

}
