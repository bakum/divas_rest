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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import ua.divas.restful.Kontragents;

/**
 *
 * @author bakum
 */
@Stateless
@Path("kontragents")
public class KontragentsFacadeREST extends AbstractFacade<Kontragents> {
    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public KontragentsFacadeREST() {
        super(Kontragents.class);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Kontragents entity) {
        super.edit(entity);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Kontragents find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Kontragents> findAll() {
        return super.findAll();
//        return getEntityManager().createNamedQuery("Kontragents.findAll", Kontragents.class).getResultList();
    }

//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<Kontragents> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}