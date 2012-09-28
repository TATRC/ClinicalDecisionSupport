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
@Table(name = "subject")
@NamedQueries({@NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"), @NamedQuery(name = "Subject.findBySubjectkey", query = "SELECT s FROM Subject s WHERE s.subjectkey = :subjectkey"), @NamedQuery(name = "Subject.findByGender", query = "SELECT s FROM Subject s WHERE s.gender = :gender"), @NamedQuery(name = "Subject.findBySsn", query = "SELECT s FROM Subject s WHERE s.ssn = :ssn"), @NamedQuery(name = "Subject.findByDob", query = "SELECT s FROM Subject s WHERE s.dob = :dob"), @NamedQuery(name = "Subject.findByFaxcontact", query = "SELECT s FROM Subject s WHERE s.faxcontact = :faxcontact"), @NamedQuery(name = "Subject.findByAccountNumber", query = "SELECT s FROM Subject s WHERE s.accountNumber = :accountNumber"), @NamedQuery(name = "Subject.findByEthnicityCode", query = "SELECT s FROM Subject s WHERE s.ethnicityCode = :ethnicityCode"), @NamedQuery(name = "Subject.findByLanguageCode", query = "SELECT s FROM Subject s WHERE s.languageCode = :languageCode"), @NamedQuery(name = "Subject.findByMaritalstatuscode", query = "SELECT s FROM Subject s WHERE s.maritalstatuscode = :maritalstatuscode"), @NamedQuery(name = "Subject.findByReligioncode", query = "SELECT s FROM Subject s WHERE s.religioncode = :religioncode"), @NamedQuery(name = "Subject.findByDriverslicenseinfo", query = "SELECT s FROM Subject s WHERE s.driverslicenseinfo = :driverslicenseinfo"), @NamedQuery(name = "Subject.findByEthnicgroupcode", query = "SELECT s FROM Subject s WHERE s.ethnicgroupcode = :ethnicgroupcode"), @NamedQuery(name = "Subject.findByBirthplace", query = "SELECT s FROM Subject s WHERE s.birthplace = :birthplace"), @NamedQuery(name = "Subject.findByBirthorder", query = "SELECT s FROM Subject s WHERE s.birthorder = :birthorder"), @NamedQuery(name = "Subject.findByCitizenshipcode", query = "SELECT s FROM Subject s WHERE s.citizenshipcode = :citizenshipcode"), @NamedQuery(name = "Subject.findByVetmilitarystatuscode", query = "SELECT s FROM Subject s WHERE s.vetmilitarystatuscode = :vetmilitarystatuscode"), @NamedQuery(name = "Subject.findByNationalitycode", query = "SELECT s FROM Subject s WHERE s.nationalitycode = :nationalitycode"), @NamedQuery(name = "Subject.findByRowversion", query = "SELECT s FROM Subject s WHERE s.rowversion = :rowversion"), @NamedQuery(name = "Subject.findByGuid", query = "SELECT s FROM Subject s WHERE s.guid = :guid"), @NamedQuery(name = "Subject.findByFacilitykey", query = "SELECT s FROM Subject s WHERE s.facilitykey = :facilitykey"), @NamedQuery(name = "Subject.findByCurrentnamekey", query = "SELECT s FROM Subject s WHERE s.currentnamekey = :currentnamekey"), @NamedQuery(name = "Subject.findByCurrentaddresskey", query = "SELECT s FROM Subject s WHERE s.currentaddresskey = :currentaddresskey"), @NamedQuery(name = "Subject.findByCurrenthomephonekey", query = "SELECT s FROM Subject s WHERE s.currenthomephonekey = :currenthomephonekey"), @NamedQuery(name = "Subject.findByCurrentalternatephonekey", query = "SELECT s FROM Subject s WHERE s.currentalternatephonekey = :currentalternatephonekey"), @NamedQuery(name = "Subject.findByCurrentemailkey", query = "SELECT s FROM Subject s WHERE s.currentemailkey = :currentemailkey"), @NamedQuery(name = "Subject.findByCurrentfaxnumberkey", query = "SELECT s FROM Subject s WHERE s.currentfaxnumberkey = :currentfaxnumberkey"), @NamedQuery(name = "Subject.findByRegisteredwithmpi", query = "SELECT s FROM Subject s WHERE s.registeredwithmpi = :registeredwithmpi"), @NamedQuery(name = "Subject.findByPrimarycareproviderkey", query = "SELECT s FROM Subject s WHERE s.primarycareproviderkey = :primarycareproviderkey"), @NamedQuery(name = "Subject.findByTimeofdeath", query = "SELECT s FROM Subject s WHERE s.timeofdeath = :timeofdeath")})
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subjectkey")
    private Integer subjectkey;
    @Column(name = "gender")
    private String gender;
    @Column(name = "ssn")
    private String ssn;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "faxcontact")
    private String faxcontact;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "ethnicityCode")
    private String ethnicityCode;
    @Column(name = "languageCode")
    private String languageCode;
    @Column(name = "maritalstatuscode")
    private String maritalstatuscode;
    @Column(name = "religioncode")
    private String religioncode;
    @Column(name = "driverslicenseinfo")
    private String driverslicenseinfo;
    @Column(name = "ethnicgroupcode")
    private String ethnicgroupcode;
    @Column(name = "birthplace")
    private String birthplace;
    @Column(name = "birthorder")
    private String birthorder;
    @Column(name = "citizenshipcode")
    private String citizenshipcode;
    @Column(name = "vetmilitarystatuscode")
    private String vetmilitarystatuscode;
    @Column(name = "nationalitycode")
    private String nationalitycode;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;
    @Column(name = "currentnamekey")
    private BigDecimal currentnamekey;
    @Column(name = "currentaddresskey")
    private BigDecimal currentaddresskey;
    @Column(name = "currenthomephonekey")
    private BigDecimal currenthomephonekey;
    @Column(name = "currentalternatephonekey")
    private BigDecimal currentalternatephonekey;
    @Column(name = "currentemailkey")
    private BigDecimal currentemailkey;
    @Column(name = "currentfaxnumberkey")
    private BigDecimal currentfaxnumberkey;
    @Column(name = "registeredwithmpi")
    private Short registeredwithmpi;
    @Column(name = "primarycareproviderkey")
    private Integer primarycareproviderkey;
    @Column(name = "timeofdeath")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeofdeath;

    public Subject() {
    }

    public Subject(Integer subjectkey) {
        this.subjectkey = subjectkey;
    }

    public Subject(Integer subjectkey, short rowversion, String guid, int facilitykey) {
        this.subjectkey = subjectkey;
        this.rowversion = rowversion;
        this.guid = guid;
        this.facilitykey = facilitykey;
    }

    public Integer getSubjectkey() {
        return subjectkey;
    }

    public void setSubjectkey(Integer subjectkey) {
        this.subjectkey = subjectkey;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFaxcontact() {
        return faxcontact;
    }

    public void setFaxcontact(String faxcontact) {
        this.faxcontact = faxcontact;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEthnicityCode() {
        return ethnicityCode;
    }

    public void setEthnicityCode(String ethnicityCode) {
        this.ethnicityCode = ethnicityCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getMaritalstatuscode() {
        return maritalstatuscode;
    }

    public void setMaritalstatuscode(String maritalstatuscode) {
        this.maritalstatuscode = maritalstatuscode;
    }

    public String getReligioncode() {
        return religioncode;
    }

    public void setReligioncode(String religioncode) {
        this.religioncode = religioncode;
    }

    public String getDriverslicenseinfo() {
        return driverslicenseinfo;
    }

    public void setDriverslicenseinfo(String driverslicenseinfo) {
        this.driverslicenseinfo = driverslicenseinfo;
    }

    public String getEthnicgroupcode() {
        return ethnicgroupcode;
    }

    public void setEthnicgroupcode(String ethnicgroupcode) {
        this.ethnicgroupcode = ethnicgroupcode;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthorder() {
        return birthorder;
    }

    public void setBirthorder(String birthorder) {
        this.birthorder = birthorder;
    }

    public String getCitizenshipcode() {
        return citizenshipcode;
    }

    public void setCitizenshipcode(String citizenshipcode) {
        this.citizenshipcode = citizenshipcode;
    }

    public String getVetmilitarystatuscode() {
        return vetmilitarystatuscode;
    }

    public void setVetmilitarystatuscode(String vetmilitarystatuscode) {
        this.vetmilitarystatuscode = vetmilitarystatuscode;
    }

    public String getNationalitycode() {
        return nationalitycode;
    }

    public void setNationalitycode(String nationalitycode) {
        this.nationalitycode = nationalitycode;
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

    public int getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(int facilitykey) {
        this.facilitykey = facilitykey;
    }

    public BigDecimal getCurrentnamekey() {
        return currentnamekey;
    }

    public void setCurrentnamekey(BigDecimal currentnamekey) {
        this.currentnamekey = currentnamekey;
    }

    public BigDecimal getCurrentaddresskey() {
        return currentaddresskey;
    }

    public void setCurrentaddresskey(BigDecimal currentaddresskey) {
        this.currentaddresskey = currentaddresskey;
    }

    public BigDecimal getCurrenthomephonekey() {
        return currenthomephonekey;
    }

    public void setCurrenthomephonekey(BigDecimal currenthomephonekey) {
        this.currenthomephonekey = currenthomephonekey;
    }

    public BigDecimal getCurrentalternatephonekey() {
        return currentalternatephonekey;
    }

    public void setCurrentalternatephonekey(BigDecimal currentalternatephonekey) {
        this.currentalternatephonekey = currentalternatephonekey;
    }

    public BigDecimal getCurrentemailkey() {
        return currentemailkey;
    }

    public void setCurrentemailkey(BigDecimal currentemailkey) {
        this.currentemailkey = currentemailkey;
    }

    public BigDecimal getCurrentfaxnumberkey() {
        return currentfaxnumberkey;
    }

    public void setCurrentfaxnumberkey(BigDecimal currentfaxnumberkey) {
        this.currentfaxnumberkey = currentfaxnumberkey;
    }

    public Short getRegisteredwithmpi() {
        return registeredwithmpi;
    }

    public void setRegisteredwithmpi(Short registeredwithmpi) {
        this.registeredwithmpi = registeredwithmpi;
    }

    public Integer getPrimarycareproviderkey() {
        return primarycareproviderkey;
    }

    public void setPrimarycareproviderkey(Integer primarycareproviderkey) {
        this.primarycareproviderkey = primarycareproviderkey;
    }

    public Date getTimeofdeath() {
        return timeofdeath;
    }

    public void setTimeofdeath(Date timeofdeath) {
        this.timeofdeath = timeofdeath;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectkey != null ? subjectkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.subjectkey == null && other.subjectkey != null) || (this.subjectkey != null && !this.subjectkey.equals(other.subjectkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Subject[subjectkey=" + subjectkey + "]";
    }

}
