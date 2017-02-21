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
package se.kth.ict.iv1201.recruitmentapp.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * A handler for database calls.
 */
public class DBHandler {

    EntityManager em;

    public DBHandler(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates a new user (Person) with the specified parameters and persists
     * its data.
     *
     * @param p person to be saved to the database
     * @throws Exception
     */
    public void Save(Person p) throws Exception {
        //Remove "-" if any
        p.setSsn(p.getSsn().replace("-", ""));
        try {
            inputValidation(p.getUsername(), p.getSsn(), p.getEmail());
            em.persist(p);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Checks for duplicate entries in the database. If any of the parameters
     * were already registered in the db a corresponding error message is
     * returned to caller.
     *
     * @param username user's username.
     * @param ssn user's Social Security Number (ssn).
     * @param email user's email.
     */
    private void inputValidation(String username, String ssn, String email) throws Exception {

        String errors = "";

        int i = em.createNamedQuery("Person.findBySsn").setParameter("ssn", ssn).getResultList().size();
        if (i > 0) {
            errors = "100:";
        }
        i = em.createNamedQuery("Person.findByUsername").setParameter("username", username).getResultList().size();
        if (i > 0) {
            errors += "101:";
        }
        i = em.createNamedQuery("Person.findByEmail").setParameter("email", email).getResultList().size();
        if (i > 0) {
            errors += "102:";
        }
        if (ssn.length() != 12) {
            errors += "103:";
        } else {
            if (!(ssn.matches("[0-9]+"))) {
                errors += "103:";
            }
            if (((Calendar.getInstance().get(Calendar.YEAR)) - Integer.parseInt(ssn.substring(0, 4))) > 122) {
                errors += "104:";
            }
        }
        try {
            InetAddress ia = InetAddress.getByName(email.split("@")[1]);
        } catch (UnknownHostException e) {
            errors += "105:";
        }
        if (!errors.equals("")) {
            throw new Exception(errors);
        }
    }

    public List<Role> getRoles() {
        return em.createNamedQuery("Role.findAll").getResultList();
    }

    public Role getRole(String role) {
        return (Role) em.createNamedQuery("Role.findByName").setParameter("name", role).getSingleResult();
    }
}
