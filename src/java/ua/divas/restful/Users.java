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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByIsAdmin", query = "SELECT u FROM Users u WHERE u.isAdmin = :isAdmin"),
//    @NamedQuery(name = "Users.findByPassWd", query = "SELECT u FROM Users u WHERE u.passWd = :passWd"),
    @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login"),
    @NamedQuery(name = "Users.findByVersion", query = "SELECT u FROM Users u WHERE u.version = :version"),
    @NamedQuery(name = "Users.findByPredefined", query = "SELECT u FROM Users u WHERE u.predefined = :predefined"),
    @NamedQuery(name = "Users.findByUDescription", query = "SELECT u FROM Users u WHERE u.uDescription = :uDescription"),
    @NamedQuery(name = "Users.findByIsZamer", query = "SELECT u FROM Users u WHERE u.isZamer = :isZamer"),
    @NamedQuery(name = "Users.findByIsActive", query = "SELECT u FROM Users u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "Users.findByDateBefore", query = "SELECT u FROM Users u WHERE u.dateBefore = :dateBefore"),
    @NamedQuery(name = "Users.findByDateBefireAccept", query = "SELECT u FROM Users u WHERE u.dateBefireAccept = :dateBefireAccept")})
public class Users implements Serializable {
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
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ADMIN")
    private short isAdmin;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 100)
//    @Column(name = "PASS_WD")
//    private String passWd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date version;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PREDEFINED")
    private short predefined;
    @Size(max = 1000)
    @Column(name = "U_DESCRIPTION")
    private String uDescription;
    @Column(name = "IS_ZAMER")
    private Short isZamer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ACTIVE")
    private short isActive;
    @Column(name = "DATE_BEFORE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBefore;
    @Column(name = "DATE_BEFIRE_ACCEPT")
    private Short dateBefireAccept;
    @Lob
    @Column(name = "PHOTO")
    private Serializable photo;
    @OneToMany(mappedBy = "userId")
    private Collection<Kontragents> kontragentsCollection;
    @OneToMany(mappedBy = "mainUser")
    private Collection<Divisions> divisionsCollection;

    public Users() {
    }

    public Users(String id) {
        this.id = id;
    }

    public Users(String id, String firstName, String lastName, String email, short isAdmin, String passWd, String login, Date version, short predefined, short isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isAdmin = isAdmin;
//        this.passWd = passWd;
        this.login = login;
        this.version = version;
        this.predefined = predefined;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(short isAdmin) {
        this.isAdmin = isAdmin;
    }

//    public String getPassWd() {
//        return passWd;
//    }
//
//    public void setPassWd(String passWd) {
//        this.passWd = passWd;
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public short getPredefined() {
        return predefined;
    }

    public void setPredefined(short predefined) {
        this.predefined = predefined;
    }

    public String getUDescription() {
        return uDescription;
    }

    public void setUDescription(String uDescription) {
        this.uDescription = uDescription;
    }

    public Short getIsZamer() {
        return isZamer;
    }

    public void setIsZamer(Short isZamer) {
        this.isZamer = isZamer;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    public Date getDateBefore() {
        return dateBefore;
    }

    public void setDateBefore(Date dateBefore) {
        this.dateBefore = dateBefore;
    }

    public Short getDateBefireAccept() {
        return dateBefireAccept;
    }

    public void setDateBefireAccept(Short dateBefireAccept) {
        this.dateBefireAccept = dateBefireAccept;
    }

    public Serializable getPhoto() {
        return photo;
    }

    public void setPhoto(Serializable photo) {
        this.photo = photo;
    }

    @XmlTransient
    public Collection<Kontragents> getKontragentsCollection() {
        return kontragentsCollection;
    }

    public void setKontragentsCollection(Collection<Kontragents> kontragentsCollection) {
        this.kontragentsCollection = kontragentsCollection;
    }

    @XmlTransient
    public Collection<Divisions> getDivisionsCollection() {
        return divisionsCollection;
    }

    public void setDivisionsCollection(Collection<Divisions> divisionsCollection) {
        this.divisionsCollection = divisionsCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.divas.restful.Users[ id=" + id + " ]";
    }
    
}
