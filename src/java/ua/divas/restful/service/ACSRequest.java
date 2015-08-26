/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.divas.restful.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ACSRequest {
    
    private String userId;
    private String[] filterMask;
    private String[] roleFilter;
    private String[] privilegeFilter;

    public ACSRequest() {
        super();
    }

    public ACSRequest(String userId, String[] filterMask, String[] roleFilter, String[] privilegeFilter) {
        super();
        this.userId = userId;
        this.filterMask = filterMask;
        this.roleFilter = roleFilter;
        this.privilegeFilter = privilegeFilter;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setFilterMask(String[] filterMask) {
        this.filterMask = filterMask;
    }

    public String[] getFilterMask() {
        return filterMask;
    }

    public void setRoleFilter(String[] roleFilter) {
        this.roleFilter = roleFilter;
    }

    public String[] getRoleFilter() {
        return roleFilter;
    }

    public void setPrivilegeFilter(String[] privilegeFilter) {
        this.privilegeFilter = privilegeFilter;
    }

    public String[] getPrivilegeFilter() {
        return privilegeFilter;
    }
}
