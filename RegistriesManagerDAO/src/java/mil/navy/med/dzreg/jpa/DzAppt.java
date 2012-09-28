/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.med.dzreg.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kim
 */
@Entity
@Table(name = "DZ_APPT")
@NamedQueries({@NamedQuery(name = "DzAppt.findAll", query = "SELECT d FROM DzAppt d"), @NamedQuery(name = "DzAppt.findByPatid", query = "SELECT d FROM DzAppt d WHERE d.patid = :patid"), @NamedQuery(name = "DzAppt.findByApptdate", query = "SELECT d FROM DzAppt d WHERE d.apptdate = :apptdate"), @NamedQuery(name = "DzAppt.findByInsertedDt", query = "SELECT d FROM DzAppt d WHERE d.insertedDt = :insertedDt"), @NamedQuery(name = "DzAppt.findByDiseasetypeId", query = "SELECT d FROM DzAppt d WHERE d.diseasetypeId = :diseasetypeId"), @NamedQuery(name = "DzAppt.findByDataSource", query = "SELECT d FROM DzAppt d WHERE d.dataSource = :dataSource"), @NamedQuery(name = "DzAppt.findByAppointmentId", query = "SELECT d FROM DzAppt d WHERE d.appointmentId = :appointmentId"), @NamedQuery(name = "DzAppt.findByPcmid", query = "SELECT d FROM DzAppt d WHERE d.pcmid = :pcmid"), @NamedQuery(name = "DzAppt.findByAppointmentStatusId", query = "SELECT d FROM DzAppt d WHERE d.appointmentStatusId = :appointmentStatusId"), @NamedQuery(name = "DzAppt.findByFlag", query = "SELECT d FROM DzAppt d WHERE d.flag = :flag")})
public class DzAppt implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "PATID")
  private Long patid;
  @Basic(optional = false)
  @Column(name = "APPTDATE")
  @Temporal(TemporalType.DATE)
  private Date apptdate;
  @Column(name = "INSERTED_DT")
  @Temporal(TemporalType.DATE)
  private Date insertedDt;
  @Column(name = "DISEASETYPE_ID")
  private Integer diseasetypeId;
  @Basic(optional = false)
  @Column(name = "DATA_SOURCE")
  private String dataSource;
  @Column(name = "APPOINTMENT_ID")
  private Long appointmentId;
  @Column(name = "PCMID")
  private Long pcmid;
  @Column(name = "APPOINTMENT_STATUS_ID")
  private BigInteger appointmentStatusId;
  @Column(name = "FLAG")
  private Integer flag;
  @JoinColumn(name = "PATID", referencedColumnName = "PATID", insertable = false, updatable = false)
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  private DzPatients dzPatients;
  @JoinColumn(name = "POCID", referencedColumnName = "POCID")
  @ManyToOne(fetch = FetchType.LAZY)
  private DzPoc pocid;

  public DzAppt() {
  }

  public DzAppt(Long patid) {
    this.patid = patid;
  }

  public DzAppt(Long patid, Date apptdate, String dataSource) {
    this.patid = patid;
    this.apptdate = apptdate;
    this.dataSource = dataSource;
  }

  public Long getPatid() {
    return patid;
  }

  public void setPatid(Long patid) {
    this.patid = patid;
  }

  public Date getApptdate() {
    return apptdate;
  }

  public void setApptdate(Date apptdate) {
    this.apptdate = apptdate;
  }

  public Date getInsertedDt() {
    return insertedDt;
  }

  public void setInsertedDt(Date insertedDt) {
    this.insertedDt = insertedDt;
  }

  public Integer getDiseasetypeId() {
    return diseasetypeId;
  }

  public void setDiseasetypeId(Integer diseasetypeId) {
    this.diseasetypeId = diseasetypeId;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public Long getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
  }

  public Long getPcmid() {
    return pcmid;
  }

  public void setPcmid(Long pcmid) {
    this.pcmid = pcmid;
  }

  public BigInteger getAppointmentStatusId() {
    return appointmentStatusId;
  }

  public void setAppointmentStatusId(BigInteger appointmentStatusId) {
    this.appointmentStatusId = appointmentStatusId;
  }

  public Integer getFlag() {
    return flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }

  public DzPatients getDzPatients() {
    return dzPatients;
  }

  public void setDzPatients(DzPatients dzPatients) {
    this.dzPatients = dzPatients;
  }

  public DzPoc getPocid() {
    return pocid;
  }

  public void setPocid(DzPoc pocid) {
    this.pocid = pocid;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (patid != null ? patid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DzAppt)) {
      return false;
    }
    DzAppt other = (DzAppt) object;
    if ((this.patid == null && other.patid != null) || (this.patid != null && !this.patid.equals(other.patid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "mil.navy.med.dzreg.jpa.DzAppt[patid=" + patid + "]";
  }

}
