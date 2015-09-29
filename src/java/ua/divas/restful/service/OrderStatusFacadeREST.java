/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import ua.divas.restful.OrderStatus;

/**
 *
 * @author bakum
 */
@Stateless
@Path("status")
public class OrderStatusFacadeREST extends AbstractFacade<OrderStatus> {
    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public OrderStatusFacadeREST() {
        super(OrderStatus.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public OrderStatus find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<OrderStatus> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
