/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful.service;

import java.util.List;
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
import ua.divas.restful.Kontragents;
import ua.divas.restful.MeasureUnit;
import ua.divas.restful.Nomenklatura;
import ua.divas.restful.Orders;
import ua.divas.restful.OrdersTpUslugi;

/**
 *
 * @author bakum
 */
@Stateless
@Path("uslugi")
public class OrdersTpUslugiFacadeREST extends AbstractFacade<OrdersTpUslugi> {

    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public OrdersTpUslugiFacadeREST() {
        super(OrdersTpUslugi.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(OrdersTpUslugi entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, OrdersTpUslugi entity) {
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
    public OrdersTpUslugi find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<OrdersTpUslugi> findAll() {
        return super.findAll();
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<OrdersTpUslugi> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
    private OrdersTpUslugi getOrder(String id) {
        return getEntityManager().createNamedQuery("OrdersTpUslugi.findById", OrdersTpUslugi.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @GET
    @Path("{id}/kontragent")
    @Produces({"application/xml", "application/json"})
    public Kontragents findKontragById(@PathParam("id") String id) {
        OrdersTpUslugi o = getOrder(id);
        try {
            return getEntityManager().createNamedQuery("Kontragents.findById", Kontragents.class)
                    .setParameter("id", o.getSotrId()).getSingleResult();
        } catch (NoResultException | NonUniqueResultException nre) {
            return new Kontragents();
        }
    }

    @GET
    @Path("{id}/nomenklatura")
    @Produces({"application/xml", "application/json"})
    public Nomenklatura findNomById(@PathParam("id") String id) {
        OrdersTpUslugi o = getOrder(id);
        return getEntityManager().createNamedQuery("Nomenklatura.findById", Nomenklatura.class)
                .setParameter("id", o.getNomId()).getSingleResult();
    }

    @GET
    @Path("{id}/group")
    @Produces({"application/xml", "application/json"})
    public Nomenklatura findGroupById(@PathParam("id") String id) {
        OrdersTpUslugi o = getOrder(id);
        return getEntityManager().createNamedQuery("Nomenklatura.findById", Nomenklatura.class)
                .setParameter("id", o.getGroupId()).getSingleResult();
    }

    @GET
    @Path("{id}/measure")
    @Produces({"application/xml", "application/json"})
    public MeasureUnit findMeasureById(@PathParam("id") String id) {
        OrdersTpUslugi o = getOrder(id);
        return getEntityManager().createNamedQuery("MeasureUnit.findById", MeasureUnit.class)
                .setParameter("id", o.getMeasureId()).getSingleResult();
    }

    @GET
    @Path("{id}/order")
    @Produces({"application/xml", "application/json"})
    public Orders findOrderById(@PathParam("id") String id) {
        OrdersTpUslugi o = getOrder(id);
        return getEntityManager().createNamedQuery("Orders.findById", Orders.class)
                .setParameter("id", o.getOrderId()).getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
