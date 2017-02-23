/**
 * @author
 *
 * IV1201 Design of Global Applications: Group 8
 * Arvid Persson Moosavi <amoosavi at kth.se>
 * Arvin Behshad <arvinb at kth.se>
 * Milad Barai <barai at kth.se>
 * Massar Almosawi <massar at kth.se>
 *
 */
package se.kth.ict.iv1201.recruitmentapp.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.recruitmentapp.model.DBHandler;
import se.kth.ict.iv1201.recruitmentapp.utils.GeneralUtils;

/**
 * A controller that handles login attempts
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

    /**
     * Authenticates user to check weather correct or incorrect credentials
     *
     * @param username username of the one wanting to login
     * @param password password (unencrypted)
     *
     * @throws Exception containing message with why it failed
     *
     * @return true if login succeded
     */
    public boolean authenticateUser(String username, String password) throws Exception {
        DBHandler db = new DBHandler(em);
        boolean res = db.authenticate(username, GeneralUtils.encryptPass(password));
        return res;
    }

    /**
     * Gets root cause of an Exception
     *
     * @param e to get root cause from
     *
     * @return gives root exception which message can be extracted from
     */
    public Throwable getRootcause(Exception e) {
        return GeneralUtils.getRootCause(e);
    }

}
