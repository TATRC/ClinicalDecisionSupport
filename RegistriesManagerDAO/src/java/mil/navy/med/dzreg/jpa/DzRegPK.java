/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.med.dzreg.jpa;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kim
 */
@Embeddable
public class DzRegPK implements Serializable {
  @Basic(optional = false)
  @Column(name = "PATID")
  private Long patid;
  @Basic(optional = false)
  @Column(name = "DISEASETYPE_ID")
  private Integer diseasetypeId;

  public DzRegPK() {
  }

  public DzRegPK(Long patid, Integer diseasetypeId) {
    this.patid = patid;
    this.diseasetypeId = diseasetypeId;
  }

  public Long getPatid() {
    return patid;
  }

  public void setPatid(Long patid) {
    this.patid = patid;
  }

  public Integer getDiseasetypeId() {
    return diseasetypeId;
  }

  public void setDiseasetypeId(Integer diseasetypeId) {
    this.diseasetypeId = diseasetypeId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (patid != null ? patid.hashCode() : 0);
    hash += (diseasetypeId != null ? diseasetypeId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DzRegPK)) {
      return false;
    }
    DzRegPK other = (DzRegPK) object;
    if ((this.patid == null && other.patid != null) || (this.patid != null && !this.patid.equals(other.patid))) {
      return false;
    }
    if ((this.diseasetypeId == null && other.diseasetypeId != null) || (this.diseasetypeId != null && !this.diseasetypeId.equals(other.diseasetypeId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "DzRegPK[patid=" + patid + ", diseasetypeId=" + diseasetypeId + "]";
  }

}
