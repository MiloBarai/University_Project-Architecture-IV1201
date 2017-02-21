/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.recruitmentapp.model.DBHandler;
import se.kth.ict.iv1201.recruitmentapp.utils.GeneralUtils;

/**
 *
 * @author MediaMarkt
 */
@Stateless
public class LoginBean {
    
    @PersistenceContext(unitName = "se.kth.ict.iv1201_recruitmentapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     * @return the EntityManager instance em.
     */
    protected EntityManager getEntityManager() {
        return em;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public boolean authenticateUser(String username, String password) throws Exception{
        DBHandler db = new DBHandler(em);
           boolean res= db.authenticate(username,GeneralUtils.encryptPass(password));
        return res;
    }

    public Throwable getRootcause(Exception ex) {
        return GeneralUtils.getRootCause(ex);
    }
    
}
