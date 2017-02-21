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

public class DBHandler {
    
    EntityManager em;
    
    public DBHandler(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates a new user (Person) with the specified parameters and persists
     * its data.
     *
     * @param p person to be saved in db
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
        
        String status = "";
        
        int i = em.createNamedQuery("Person.findBySsn").setParameter("ssn", ssn).getResultList().size();
        if (i > 0) {
            status = "100:";
        }
        i = em.createNamedQuery("Person.findByUsername").setParameter("username", username).getResultList().size();
        if (i > 0) {
            status += "101:";
        }
        i = em.createNamedQuery("Person.findByEmail").setParameter("email", email).getResultList().size();
        if (i > 0) {
            status += "102:";
        }
        if (ssn.length() != 12) {
            status += "103:";
        } else {
            if (!(ssn.matches("[0-9]+"))) {
                status += "103:";
            }
            if (((Calendar.getInstance().get(Calendar.YEAR)) - Integer.parseInt(ssn.substring(0, 4))) > 122) {
                status += "104:";
            }
        }
        if (email.contains("@") && email.contains(".")) {
            try {
                InetAddress ia = InetAddress.getByName(email.split("@")[1]);
            } catch (UnknownHostException e) {
                status += "105:";
            }
        } else {
            status += "105:";
        }
        if (!status.equals("")) {
            throw new Exception(status);
        }
    }
    
    public List<Role> getRoles() {
        return em.createNamedQuery("Role.findAll").getResultList();
    }
    
    public Role getRole(String role) {
        return (Role) em.createNamedQuery("Role.findByName").setParameter("name", role).getSingleResult();
    }
    
    public boolean authenticate(String username, String encryptPass) throws Exception {
        Person auth = getUser(username);
        String password = auth.getPassword();        
        if (encryptPass.equals(password)) {
            return true;
        } else {
            throw new Exception("107");
        }
    }

    private Person getUser(String username) throws Exception {
        int i = em.createNamedQuery("Person.findByUsername").setParameter("username", username).getResultList().size();
        if (i < 1) {
            throw new Exception("106");
        } else {
            return (Person) em.createNamedQuery("Person.findByUsername").setParameter("username", username).getSingleResult();
        }
        
    }
}
