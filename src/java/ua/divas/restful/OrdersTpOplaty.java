/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bakum
 */
@Entity
@Table(name = "ORDERS_TP_OPLATY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersTpOplaty.findAll", query = "SELECT o FROM OrdersTpOplaty o"),
    @NamedQuery(name = "OrdersTpOplaty.findByOrder", query = "SELECT o FROM OrdersTpOplaty o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrdersTpOplaty.findById", query = "SELECT o FROM OrdersTpOplaty o WHERE o.id = :id"),
    @NamedQuery(name = "OrdersTpOplaty.findByDat", query = "SELECT o FROM OrdersTpOplaty o WHERE o.dat = :dat"),
    @NamedQuery(name = "OrdersTpOplaty.findByComments", query = "SELECT o FROM OrdersTpOplaty o WHERE o.comments = :comments")})
public class OrdersTpOplaty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUM")
    private BigDecimal sum1;
    @Size(max = 1000)
    @Column(name = "COMMENTS")
    private String comments;
//    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Users userId;
    @Column(name = "USER_ID")
    private String userId;
//    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Orders orderId;
    @Column(name = "ORDER_ID")
    private String orderId;
//    @JoinColumn(name = "ZAMER_ID", referencedColumnName = "ID")
//    @ManyToOne
//    private Kontragents zamerId;
    @Column(name = "ZAMER_ID")
    private String zamerId;
//    @JoinColumn(name = "KASSA_ID", referencedColumnName = "ID")
//    @ManyToOne
//    private Kassa kassaId;
    @Column(name = "KASSA_ID")
    private String kassaId;

    public OrdersTpOplaty() {
    }

    public OrdersTpOplaty(String id) {
        this.id = id;
    }

    public OrdersTpOplaty(String id, Date dat, BigDecimal sum) {
        this.id = id;
        this.dat = dat;
        this.sum1 = sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public BigDecimal getSum() {
        return sum1;
    }

    public void setSum(BigDecimal sum) {
        this.sum1 = sum;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getZamerId() {
        return zamerId;
    }

    public void setZamerId(String zamerId) {
        this.zamerId = zamerId;
    }

    public String getKassaId() {
        return kassaId;
    }

    public void setKassaId(String kassaId) {
        this.kassaId = kassaId;
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
        if (!(object instanceof OrdersTpOplaty)) {
            return false;
        }
        OrdersTpOplaty other = (OrdersTpOplaty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.divas.restful.OrdersTpOplaty[ id=" + id + " ]";
    }
    
}
