/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.view;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import se.kth.ict.iv1201.recruitmentapp.controller.LoginBean;

/**
 *
 * @author MediaMarkt
 */
@Named(value = "login")
@RequestScoped
public class Login {

    @EJB
    LoginBean loginController;

    private String username;
    private String password;
    private String msg;

    public String getMsg() {
        if (msg == null) {
            return "";
        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // dont want to be able to return
    public String getUsername() {
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // dont want to be able to return
    public String getPassword() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    public String auth() {
        try {
            loginController.authenticateUser(username, password);
        } catch (Exception ex) {
            String status = loginController.getRootcause(ex).getMessage();
            if (status != null) {
                msg = getStatus(status);
            } else {
                msg = ex.getMessage();
            }
            return "failure";
        }
        return "success";
    }

    private String getStatus(String status) {
        String ret = "";
        switch (status) {
            case "106":
                ret = "Sorry we could not find you! ";
                break;
            case "107":
                ret = "Woops! incorrect password!";
                break;
            default:
                ret = "Unknow Error Occured, please contact us at mail@kth.se";
        }
        return ret;
    }
}
