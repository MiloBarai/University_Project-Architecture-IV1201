/**
 * @author Arvid Persson Moosavi <amoosavi at kth.se>
 */
package se.kth.ict.iv1201.recruitmentapp.controller;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.recruitmentapp.model.Person;

@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "se.kth.ict.iv1201_recruitmentapp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }

    public void Save(String username, String password, String name, String surname, String ssn, String email, long role) {
        try {
            Person mPerson = new Person(username, password, name, surname, ssn, email, role);
            em.persist(mPerson);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

}
