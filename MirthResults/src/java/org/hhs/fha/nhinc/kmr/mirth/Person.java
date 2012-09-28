/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
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
@Table(name = "person")
@NamedQueries({@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"), @NamedQuery(name = "Person.findByPersonkey", query = "SELECT p FROM Person p WHERE p.personkey = :personkey"), @NamedQuery(name = "Person.findByPrincipalname", query = "SELECT p FROM Person p WHERE p.principalname = :principalname"), @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"), @NamedQuery(name = "Person.findByRowversion", query = "SELECT p FROM Person p WHERE p.rowversion = :rowversion")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "personkey")
    private Integer personkey;
    @Basic(optional = false)
    @Column(name = "principalname")
    private String principalname;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;

    public Person() {
    }

    public Person(Integer personkey) {
        this.personkey = personkey;
    }

    public Person(Integer personkey, String principalname, short rowversion) {
        this.personkey = personkey;
        this.principalname = principalname;
        this.rowversion = rowversion;
    }

    public Integer getPersonkey() {
        return personkey;
    }

    public void setPersonkey(Integer personkey) {
        this.personkey = personkey;
    }

    public String getPrincipalname() {
        return principalname;
    }

    public void setPrincipalname(String principalname) {
        this.principalname = principalname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personkey != null ? personkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personkey == null && other.personkey != null) || (this.personkey != null && !this.personkey.equals(other.personkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Person[personkey=" + personkey + "]";
    }

}
