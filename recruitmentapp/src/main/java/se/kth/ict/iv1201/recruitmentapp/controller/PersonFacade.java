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

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.recruitmentapp.model.DBHandler;
import se.kth.ict.iv1201.recruitmentapp.model.Person;
import se.kth.ict.iv1201.recruitmentapp.model.Role;
import se.kth.ict.iv1201.recruitmentapp.utils.GeneralUtils;

/**
 * A controller. Calls to the model that are executed because of an action taken
 * by the user pass through here.
 */
@Stateless
public class PersonFacade {

    @PersistenceContext(unitName = "se.kth.ict.iv1201_recruitmentapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    /**
     * @return the EntityManager instance em.
     */
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Creates a new user (Person) with the specified parameters and persists
     * its data.
     *
     * @param username user's username.
     * @param password user's password.
     * @param name user's name.
     * @param surname user's surname.
     * @param ssn user's Social Security Number (ssn).
     * @param email user's email.
     * @param role user's role.
     *
     * @throws Exception
     */
    public void Save(String username, String password, String name, String surname, String ssn, String email, String role) throws Exception {
        DBHandler db = new DBHandler(em);
        Role r = db.getRole(role);
        Person mPerson = new Person(username, GeneralUtils.encryptPass(password), name, surname, ssn, email, r);
        db.Save(mPerson);
    }

    /**
     * Get list of all roles
     *
     * @return getlist of all roles
     */
    public List<Role> getRoles() {
        DBHandler db = new DBHandler(em);
        return db.getRoles();
    }

    /**
     * Extracts the root message of a Exception
     *
     * @param e Exception with Root message wanting to be extracted
     *
     * @return a Exception containing the root message
     */
    public Throwable getRootCause(Exception e) {
        return GeneralUtils.getRootCause(e);
    }
}
