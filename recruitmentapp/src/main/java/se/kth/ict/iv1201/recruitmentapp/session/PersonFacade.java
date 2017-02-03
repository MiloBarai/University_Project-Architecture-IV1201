/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.session;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.recruitmentapp.entity.Person;

/**
 *
 * @author Arvid Persson Moosavi <amoosavi at kth.se>
 */
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

    public void Save(String ssn, String email, String password, String username) {
        try {
            Person mPerson = new Person(ssn, email, password, username);
            em.persist(mPerson);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

}
