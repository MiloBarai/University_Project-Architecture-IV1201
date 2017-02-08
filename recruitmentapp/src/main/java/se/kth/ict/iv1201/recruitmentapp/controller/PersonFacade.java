/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.controller;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.iv1201.recruitmentapp.model.Person;

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
