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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ORDERS_TP_USLUGI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersTpUslugi.findAll", query = "SELECT o FROM OrdersTpUslugi o"),
    @NamedQuery(name = "OrdersTpUslugi.findById", query = "SELECT o FROM OrdersTpUslugi o WHERE o.id = :id"),
    @NamedQuery(name = "OrdersTpUslugi.findByOrder", query = "SELECT o FROM OrdersTpUslugi o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrdersTpUslugi.findByPrice", query = "SELECT o FROM OrdersTpUslugi o WHERE o.price = :price"),
    @NamedQuery(name = "OrdersTpUslugi.findBySumm", query = "SELECT o FROM OrdersTpUslugi o WHERE o.summ = :summ"),
    @NamedQuery(name = "OrdersTpUslugi.findByDatComplete", query = "SELECT o FROM OrdersTpUslugi o WHERE o.datComplete = :datComplete"),
    @NamedQuery(name = "OrdersTpUslugi.findByDatToPay", query = "SELECT o FROM OrdersTpUslugi o WHERE o.datToPay = :datToPay"),
    @NamedQuery(name = "OrdersTpUslugi.findByQuantity", query = "SELECT o FROM OrdersTpUslugi o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrdersTpUslugi.findByAddWork", query = "SELECT o FROM OrdersTpUslugi o WHERE o.addWork = :addWork"),
    @NamedQuery(name = "OrdersTpUslugi.findByPriceAdd", query = "SELECT o FROM OrdersTpUslugi o WHERE o.priceAdd = :priceAdd")})
public class OrdersTpUslugi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ID")
    private String id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUMM")
    private BigDecimal summ;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAT_COMPLETE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datComplete;
    @Column(name = "DAT_TO_PAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datToPay;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private BigDecimal quantity;
    @Size(max = 1000)
    @Column(name = "ADD_WORK")
    private String addWork;
    @Column(name = "PRICE_ADD")
    private BigDecimal priceAdd;
//    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Orders orderId;
    @Column(name = "ORDER_ID")
    private String orderId;
//    @JoinColumn(name = "NOM_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Nomenklatura nomId;
    @Column(name = "NOM_ID")
    private String nomId;
//    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
//    @ManyToOne
//    private Nomenklatura groupId;
    @Column(name = "GROUP_ID")
    private String groupId;
//    @JoinColumn(name = "MEASURE_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private MeasureUnit measureId;
    @Column(name = "MEASURE_ID")
    private String measureId;
//    @JoinColumn(name = "SOTR_ID", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Kontragents sotrId;
    @Column(name = "SOTR_ID")
    private String sotrId;

    public OrdersTpUslugi() {
    }

    public OrdersTpUslugi(String id) {
        this.id = id;
    }

    public OrdersTpUslugi(String id, BigDecimal price, BigDecimal summ, Date datComplete, BigDecimal quantity) {
        this.id = id;
        this.price = price;
        this.summ = summ;
        this.datComplete = datComplete;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public Date getDatComplete() {
        return datComplete;
    }

    public void setDatComplete(Date datComplete) {
        this.datComplete = datComplete;
    }

    public Date getDatToPay() {
        return datToPay;
    }

    public void setDatToPay(Date datToPay) {
        this.datToPay = datToPay;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getAddWork() {
        return addWork;
    }

    public void setAddWork(String addWork) {
        this.addWork = addWork;
    }

    public BigDecimal getPriceAdd() {
        return priceAdd;
    }

    public void setPriceAdd(BigDecimal priceAdd) {
        this.priceAdd = priceAdd;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNomId() {
        return nomId;
    }

    public void setNomId(String nomId) {
        this.nomId = nomId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMeasureId() {
        return measureId;
    }

    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    public String getSotrId() {
        return sotrId;
    }

    public void setSotrId(String sotrId) {
        this.sotrId = sotrId;
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
        if (!(object instanceof OrdersTpUslugi)) {
            return false;
        }
        OrdersTpUslugi other = (OrdersTpUslugi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.divas.restful.OrdersTpUslugi[ id=" + id + " ]";
    }
    
}
