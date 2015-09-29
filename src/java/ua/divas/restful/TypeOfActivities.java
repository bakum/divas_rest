/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bakum
 */
@Entity
@Table(name = "TYPE_OF_ACTIVITIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeOfActivities.findAll", query = "SELECT t FROM TypeOfActivities t"),
    @NamedQuery(name = "TypeOfActivities.findById", query = "SELECT t FROM TypeOfActivities t WHERE t.id = :id"),
    @NamedQuery(name = "TypeOfActivities.findByVersion", query = "SELECT t FROM TypeOfActivities t WHERE t.version = :version"),
    @NamedQuery(name = "TypeOfActivities.findByFullname", query = "SELECT t FROM TypeOfActivities t WHERE t.fullname = :fullname")})
public class TypeOfActivities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "FULLNAME")
    private String fullname;

    public TypeOfActivities() {
    }

    public TypeOfActivities(String id) {
        this.id = id;
    }

    public TypeOfActivities(String id, Date version, String fullname) {
        this.id = id;
        this.version = version;
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeOfActivities)) {
            return false;
        }
        TypeOfActivities other = (TypeOfActivities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.divas.restful.TypeOfActivities[ id=" + id + " ]";
    }
    
}
