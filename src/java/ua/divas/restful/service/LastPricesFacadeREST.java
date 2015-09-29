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
import ua.divas.restful.LastPrices;

/**
 *
 * @author bakum
 */
@Stateless
@Path("price")
public class LastPricesFacadeREST extends AbstractFacade<LastPrices> {
    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public LastPricesFacadeREST() {
        super(LastPrices.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public LastPrices find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<LastPrices> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
