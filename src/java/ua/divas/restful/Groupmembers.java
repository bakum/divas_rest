/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bakum
 */
@Entity
@Table(name = "GROUPMEMBERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupmembers.findAll", query = "SELECT g FROM Groupmembers g"),
    @NamedQuery(name = "Groupmembers.findById", query = "SELECT g FROM Groupmembers g WHERE g.id = :id"),
    @NamedQuery(name = "Groupmembers.findByGName", query = "SELECT g FROM Groupmembers g WHERE g.gName = :gName"),
    @NamedQuery(name = "Groupmembers.findByGMember", query = "SELECT g FROM Groupmembers g WHERE g.gMember = :gMember")})
public class Groupmembers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "G_NAME")
    private String gName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "G_MEMBER")
    private String gMember;

    public Groupmembers() {
    }

    public Groupmembers(String id) {
        this.id = id;
    }

    public Groupmembers(String id, String gName, String gMember) {
        this.id = id;
        this.gName = gName;
        this.gMember = gMember;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGName() {
        return gName;
    }

    public void setGName(String gName) {
        this.gName = gName;
    }

    public String getGMember() {
        return gMember;
    }

    public void setGMember(String gMember) {
        this.gMember = gMember;
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
        if (!(object instanceof Groupmembers)) {
            return false;
        }
        Groupmembers other = (Groupmembers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.divas.restful.Groupmembers[ id=" + id + " ]";
    }
    
}
