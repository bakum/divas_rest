/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import ua.divas.restful.Groupmembers;

/**
 *
 * @author bakum
 */
@Stateless
@Path("authorize")
@Consumes("application/json")
@Produces("application/json")
public class GroupmembersFacadeREST extends AbstractFacade<Groupmembers> {

    @PersistenceContext(unitName = "divas_restPU")
    private EntityManager em;

    public GroupmembersFacadeREST() {
        super(Groupmembers.class);
    }
    
    private String[] getRoles (String uId){
        List<String> list =new ArrayList<>();
        List<Groupmembers> lst = getEntityManager().createNamedQuery("Groupmembers.findByGMember", Groupmembers.class)
                .setParameter("gMember", uId).getResultList();
        for (Groupmembers el : lst) {
            list.add(el.getGName());
        }
        return list.toArray(new String[list.size()]);
    }

    @POST
    public Response postData(ACSRequest request) {

        // Replace this with actual logic.
        ACSResponse rolesAndPrivileges = new ACSResponse(request.getUserId(),
                getRoles(request.getUserId()),
                new String[]{"privilege"});
        Response.ResponseBuilder builder = Response.ok(rolesAndPrivileges);
        return builder.build();
    }
    
//    @GET
//    @Override
//    public List<Groupmembers> findAll() {
//        return super.findAll();
//    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}