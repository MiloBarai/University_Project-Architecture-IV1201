/**
 * @author Arvid Persson Moosavi <amoosavi at kth.se>
 */
package se.kth.ict.iv1201.recruitmentapp.controller;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.iv1201.recruitmentapp.model.Person;

/**
 * A controller. Calls to the model that are executed because of an action taken
 * by the user pass through here.
 */
@Stateless
public class PersonFacade {

    @PersistenceContext(unitName = "se.kth.ict.iv1201_recruitmentapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

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
     * @throws EJBException
     */
    public void Save(String username, String password, String name, String surname, String ssn, String email, long role) throws Exception {

        try {
            Person mPerson = new Person(username, password, name, surname, ssn, email, role);
            String reply=errorFinder(username,ssn,email);
            if(!reply.equals("none"))
                throw new Exception(reply);
                em.persist(mPerson);
        } catch (Exception e) {
            throw new Exception(e.getMessage());   
            
                
        }
    }
    private String errorFinder(String username, String ssn, String email){
    int i=em.createNativeQuery("SELECT * FROM person WHERE ssn='"+ssn+"'").getResultList().size();
    if(i>0)
        return "SSN taken";
     i=em.createNativeQuery("SELECT * FROM person WHERE username='"+username+"'").getResultList().size();
    if(i>0)
        return "Username taken";
     i=em.createNativeQuery("SELECT * FROM person WHERE email='"+email+"'").getResultList().size();
    if(i>0)
        return "Email taken";
    return "none";
    }
}
