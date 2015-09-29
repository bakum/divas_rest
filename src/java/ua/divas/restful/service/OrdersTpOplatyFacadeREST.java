/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful.service;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import ua.divas.restful.Kassa;
import ua.divas.restful.Kontragents;
import ua.divas.restful.Orders;
import ua.divas.restful.OrdersTpOplaty;
import ua.divas.restful.Users;

/**
 *
 * @author bakum
 */
@Stateless
@Path("oplaty")
public class OrdersTpOplatyFacadeREST extends AbstractFacade<OrdersTpOplaty> {

    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public OrdersTpOplatyFacadeREST() {
        super(OrdersTpOplaty.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(OrdersTpOplaty entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, OrdersTpOplaty entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public OrdersTpOplaty find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<OrdersTpOplaty> findAll() {
        return super.findAll();
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<OrdersTpOplaty> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
    private OrdersTpOplaty getOrder(String id) {
        return getEntityManager().createNamedQuery("OrdersTpOplaty.findById", OrdersTpOplaty.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @GET
    @Path("{id}/zamer")
    @Produces({"application/xml", "application/json"})
    public Kontragents findKontragById(@PathParam("id") String id) {
        OrdersTpOplaty o = getOrder(id);
        try {
            return getEntityManager().createNamedQuery("Kontragents.findById", Kontragents.class)
                    .setParameter("id", o.getZamerId()).getSingleResult();
        } catch (NoResultException | NonUniqueResultException nre) {
            return new Kontragents();
        }

    }

    @GET
    @Path("{id}/order")
    @Produces({"application/xml", "application/json"})
    public Orders findOrderById(@PathParam("id") String id) {
        OrdersTpOplaty o = getOrder(id);
        return getEntityManager().createNamedQuery("Orders.findById", Orders.class)
                .setParameter("id", o.getOrderId()).getSingleResult();
    }

    @GET
    @Path("{id}/kassa")
    @Produces({"application/xml", "application/json"})
    public Kassa findKassaById(@PathParam("id") String id) {
        OrdersTpOplaty o = getOrder(id);
        return getEntityManager().createNamedQuery("Kassa.findById", Kassa.class)
                .setParameter("id", o.getKassaId()).getSingleResult();
    }

    @GET
    @Path("{id}/user")
    @Produces({"application/xml", "application/json"})
    public Users findUserById(@PathParam("id") String id) {
        OrdersTpOplaty o = getOrder(id);
        return getEntityManager().createNamedQuery("Users.findById", Users.class)
                .setParameter("id", o.getUserId()).getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
