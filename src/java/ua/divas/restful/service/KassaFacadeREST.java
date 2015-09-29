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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import ua.divas.restful.Kassa;
import ua.divas.restful.Users;

/**
 *
 * @author bakum
 */
@Stateless
@Path("kassa")
public class KassaFacadeREST extends AbstractFacade<Kassa> {
    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public KassaFacadeREST() {
        super(Kassa.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Kassa find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Kassa> findAll(@Context SecurityContext context) {
        String user = context.getUserPrincipal().getName();
        Users u = getEntityManager().createNamedQuery("Users.findByLogin", Users.class)
                .setParameter("login", user).getSingleResult();
        return getEntityManager().createNamedQuery("Kassa.findBySettings", Kassa.class)
                .setParameter("userId", u.getId()).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
