/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ACSResponse {
    
    private String userId;
    private String[] roles;
    private String[] privileges;
    
    public ACSResponse() {
        super();
        roles = new String[0];
        privileges = new String[0];
    }
    
    public ACSResponse(String p_userId, String[] p_roles, String[] p_privileges) {
        super();
        userId = p_userId;
        roles = p_roles;
        privileges = p_privileges;
    }

    
    public String getUserId(){
        return userId;
    }
    
    public void setUserId(String p_id){
        userId = p_id;
    }
    
    public String[] getRoles(){
        return roles;
    }
    
    public void setRoles(String[] p_roles){
        roles = p_roles;
    }

    public String[] getPrivileges(){
        return privileges;
    }
    
    public void setPrivileges(String[] p_privileges){
        roles = p_privileges;
    }
}
