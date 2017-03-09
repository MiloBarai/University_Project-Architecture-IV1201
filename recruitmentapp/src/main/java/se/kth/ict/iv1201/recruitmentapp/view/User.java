/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import se.kth.ict.iv1201.recruitmentapp.controller.LoginBean;

/**
 *
 * @author MediaMarkt
 */
@Named(value = "user")
@SessionScoped
public class User implements Serializable {

    private String name;
    private String password;
    private boolean editmode = true;
    private String role="";

    private String error;

    @EJB
    LoginBean loginController;

    /**
     * Creates a new instance of User
     */
    public User() {
    }

    /**
     * Creates a new instance of user
     *
     * @param name, String name value to be set for user
     * @param password, String password value to be encrypted and set for user
     */
    public User(String name, String password) {
        this.name = name;
        try {
            this.password = loginController.encrypt(password);
        } catch (Exception e) {
            error = "Unknown Algorithm";
        }
        editmode = false;
    }

    /**
     * Sets Username for user if editmode is true
     *
     * @param name String, Name value to be set
     */
    public void setName(String name) {
        if (editmode) {
            this.name = name;
        }
    }

    private void fetchRole() {
        try{
        role = loginController.getUserRole(name);
        }catch(Exception e){
            error = "User not found";
        }
    }

    /**
     * Sets Password for user if edit mode is true and sets editmode to false
     * after success
     *
     * @param password, String password value to be encrypted and set for user
     */
    public void setPassword(String password) {
        if (editmode) {
            try {
                this.password = loginController.encrypt(password);
                fetchRole();
            } catch (Exception e) {
                error = "Unknown Algorithm";
            }
            editmode = false;
        }

    }

    /**
     * Used to get name of user
     *
     * @return String, returns value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Used to get encryptedpassword
     *
     * @return String, return encrypted password
     */
    public String getPass() {
        return password;
    }
    
    public String getRole(){
        return role;
    }

}
