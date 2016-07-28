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
import ua.divas.restful.KassaSettings;

/**
 *
 * @author root
 */
@Stateless
@Path("kassasettings")
public class KassaSettingsFacadeREST extends AbstractFacade<KassaSettings> {
    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public KassaSettingsFacadeREST() {
        super(KassaSettings.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(KassaSettings entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, KassaSettings entity) {
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
    public KassaSettings find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<KassaSettings> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("{id}/user")
    @Produces({"application/xml", "application/json"})
    public List<KassaSettings> findByUser(@PathParam("id") String id) {
//        return super.find(id);
        return getEntityManager().createNamedQuery("KassaSettings.findByUser", KassaSettings.class)
                .setParameter("userId", id).getResultList();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<KassaSettings> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
