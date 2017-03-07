/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.view;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author MediaMarkt
 */
@Named(value = "recWeb")
@Dependent
public class RecWeb {

    @Inject
    private User userData;
    
    /**
     * Creates a new instance of RecWeb
     */
    public RecWeb() {
    }
    
        /**
     * Used to get Username of current user
     *
     * @return String, Username of current user
     */
    public String getUsername() {
        return userData.getName();
    }
    /**
     * Used to check the role of the user
     * 
     * 
     * @return String describing outcome of check
     */
    public String checkUser() {
        if (userData.getRole().equalsIgnoreCase("Recruit")) {
            return "";
        }
        return "failure";
    }
    
    /**
     * Used to Logout user 
     * invalidates entire session
     * 
     * @return String, returns index page redirect
     */
    public String logout(){
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    
}
