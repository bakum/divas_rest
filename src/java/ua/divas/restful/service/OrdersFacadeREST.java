/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import ua.divas.restful.ContactDetails;
import ua.divas.restful.Currency;
import ua.divas.restful.Divisions;
import ua.divas.restful.Groupmembers;
import ua.divas.restful.Kassa;
import ua.divas.restful.Kontragents;
import ua.divas.restful.OrderStatus;
import ua.divas.restful.Orders;
import ua.divas.restful.OrdersTpOplaty;
import ua.divas.restful.OrdersTpUslugi;
import ua.divas.restful.UserSettings;
import ua.divas.restful.Users;
import ua.divas.util.DivasEntry;

/**
 *
 * @author bakum
 */
@Stateless
@Path("orders")
public class OrdersFacadeREST extends AbstractFacade<Orders> {

    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public OrdersFacadeREST() {
        super(Orders.class);
    }

    private Users getCurrentUser(String user) {
        try {
            Users u = em.createNamedQuery("Users.findByLogin", Users.class)
                    .setParameter("login", user)
                    .getSingleResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private UserSettings getCurrentUserSettings(Users userid) {
        try {
            UserSettings u = em.createNamedQuery("UserSettings.findByUser", UserSettings.class)
                    .setParameter("userid", userid.getId())
                    .getSingleResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private OrderStatus getStatus() {
        try {
            OrderStatus u = em.createNamedQuery("OrderStatus.findByName", OrderStatus.class)
                    .setParameter("name", "Замер")
                    .getSingleResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private OrderStatus getNewStatus() {
        try {
            OrderStatus u = em.createNamedQuery("OrderStatus.findByName", OrderStatus.class)
                    .setParameter("name", "Новый")
                    .getSingleResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private OrderStatus getNotPayStatus() {
        try {
            OrderStatus u = em.createNamedQuery("OrderStatus.findByName", OrderStatus.class)
                    .setParameter("name", "НеОплачен")
                    .getSingleResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Kontragents getCurrenZamer(String key) {
        try {
            Kontragents u = em.createNamedQuery("Kontragents.findById", Kontragents.class)
                    .setParameter("id", key)
                    .getSingleResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    private Kontragents getZamer(String user) {
        Users u = getCurrentUser(user);
        UserSettings us = getCurrentUserSettings(u);
        return getCurrenZamer(us.getZamerkontragId().getId());
    }

    private List<Groupmembers> getPrivilege(String un) {
        return getEntityManager().createNamedQuery("Groupmembers.findByGMember", Groupmembers.class)
                .setParameter("gMember", un).getResultList();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Orders entity) throws SQLException, NamingException {
        super.edit(entity);
        DivasEntry.entryOrders(entity);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Orders find(@PathParam("id") String id) {
        return super.find(id);
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Orders> findAll() {
                return getEntityManager().createNamedQuery("Orders.findAllForDispatch", Orders.class)
                        .setParameter("name1", this.getStatus())
                        .setParameter("name2", this.getNewStatus())
                        .setParameter("name3", this.getNotPayStatus())
                        .getResultList();
    }
    
    private String usrPriv(List<Groupmembers> mem){
        String pr = "none";
        for (Groupmembers temp : mem) {
            pr = temp.getGName();
	}
        return pr;
    }

    @GET
    @Path("{user}/userOrders")
    @Produces({"application/xml", "application/json"})
    public List<Orders> findAllByPrivilege(@PathParam("user") String user) {
        List<Groupmembers> priv = getPrivilege(user);
        switch (usrPriv(priv)) {
            case "tester":
            case "administrator":
            case "z_dispatcher":
                return getEntityManager().createNamedQuery("Orders.findAllForDispatch", Orders.class)
                        .setParameter("name1", this.getStatus())
                        .setParameter("name2", this.getNewStatus())
                        .setParameter("name3", this.getNotPayStatus())
                        .getResultList();
            case "z_manager":
                return getEntityManager().createNamedQuery("Orders.findAll", Orders.class)
                        .setParameter("statusid", this.getStatus())
                        .setParameter("zamerid", this.getZamer(user))
                        .getResultList();
            default: return  null;
        }
    }

    @GET
    @Path("{id}/oplatyList")
    @Produces({"application/xml", "application/json"})
    public List<OrdersTpOplaty> findOplaty(@PathParam("id") String id) {
        Orders o = getOrder(id);
        try {
            return getEntityManager().createNamedQuery("OrdersTpOplaty.findByOrder", OrdersTpOplaty.class)
                .setParameter("orderId", o)
                .getResultList();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    @GET
    @Path("{id}/uslugiList")
    @Produces({"application/xml", "application/json"})
    public List<OrdersTpUslugi> findUslugi(@PathParam("id") String id) {
        Orders o = getOrder(id);
        try {
            return getEntityManager().createNamedQuery("OrdersTpUslugi.findByOrder", OrdersTpUslugi.class)
                .setParameter("orderId", o)
                .getResultList();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    private Orders getOrder(String id) {
        return getEntityManager().createNamedQuery("Orders.findById", Orders.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @GET
    @Path("{id}/kontragent")
    @Produces({"application/xml", "application/json"})
    public Kontragents findKontragById(@PathParam("id") String id) {
        Orders o = getOrder(id);
        return getEntityManager().createNamedQuery("Kontragents.findById", Kontragents.class)
                .setParameter("id", o.getKontragId().getId()).getSingleResult();
    }

    @GET
    @Path("{id}/zamer")
    @Produces({"application/xml", "application/json"})
    public Kontragents findZamerById(@PathParam("id") String id) {
        Orders o = getOrder(id);
        try {
            return getEntityManager().createNamedQuery("Kontragents.findById", Kontragents.class)
                    .setParameter("id", o.getZamerId().getId()).getSingleResult();
        } catch (NoResultException | NonUniqueResultException | NullPointerException nre) {
            return null;
        }
    }

    @GET
    @Path("{id}/kontragent/contactsList")
    @Produces({"application/xml", "application/json"})
    public List<ContactDetails> findContactByKontragId(@PathParam("id") String id) {
//        Orders o = getOrder(id);
        Kontragents k = findKontragById(id);
        return getEntityManager().createNamedQuery("ContactDetails.findByKontragId", ContactDetails.class)
                .setParameter("kontragId", k).getResultList();
    }

    @GET
    @Path("{id}/currency")
    @Produces({"application/xml", "application/json"})
    public Currency findCurrencyById(@PathParam("id") String id) {
        Orders o = getOrder(id);
        return getEntityManager().createNamedQuery("Currency.findById", Currency.class)
                .setParameter("id", o.getCurrId()).getSingleResult();
    }

    @GET
    @Path("{id}/division")
    @Produces({"application/xml", "application/json"})
    public Divisions findDivisionById(@PathParam("id") String id) {
        Orders o = getOrder(id);
        return getEntityManager().createNamedQuery("Divisions.findById", Divisions.class)
                .setParameter("id", o.getDivisionId()).getSingleResult();
    }

    @GET
    @Path("{id}/kassa")
    @Produces({"application/xml", "application/json"})
    public Kassa findKassaById(@PathParam("id") String id) {
        Orders o = getOrder(id);
        return getEntityManager().createNamedQuery("Kassa.findById", Kassa.class)
                .setParameter("id", o.getKassaId()).getSingleResult();
    }

    @GET
    @Path("{id}/status")
    @Produces({"application/xml", "application/json"})
    public OrderStatus findStatusById(@PathParam("id") String id) {
        Orders o = getOrder(id);
        return getEntityManager().createNamedQuery("OrderStatus.findById", OrderStatus.class)
                .setParameter("id", o.getStatusId()).getSingleResult();
    }

    @GET
    @Path("{id}/user")
    @Produces({"application/xml", "application/json"})
    public Users findUserById(@PathParam("id") String id) {
        Orders o = getOrder(id);
        return getEntityManager().createNamedQuery("Users.findById", Users.class)
                .setParameter("id", o.getUserId()).getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
