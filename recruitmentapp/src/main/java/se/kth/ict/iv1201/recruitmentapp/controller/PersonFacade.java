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
import se.kth.ict.iv1201.DTO.RegisterDTO;
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
     * Transaction begins: Within a transaction, a subsequent lookup doesn't
     * return any of the detached objects. However, two lookups within the same
     * transaction return the same instance, because the persistence context
     * spans the transaction.
     *
     * TransactionManagement value is CONTAINER and the the default
     * TransactionAttribute value is REQUIRED.
     *
     * Default transaction closes if no exceptions are returned to the view
     * <code>Web.save()</code>
     *
     * @see
     * <a href="http://docs.oracle.com/javaee/6/tutorial/doc/bncij.html">
     * http://docs.oracle.com/javaee/6/tutorial/doc/bncij.html</a>
     *
     * @param regDTO RegisterDTO, containing information for the registration
     *
     * @throws Exception with Error msg.
     */
    public void Save(RegisterDTO regDTO) throws Exception {
        DBHandler db = new DBHandler(em);
        Role personRole = db.getRole(regDTO.getRole());
        regDTO.setPassword(GeneralUtils.encryptPass(regDTO.getPassword()));
        Person mPerson = new Person(regDTO, personRole);
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
