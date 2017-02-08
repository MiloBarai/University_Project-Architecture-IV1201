/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.model;

import javax.persistence.EntityManager;

public class DBHandler {
    EntityManager em;
    public DBHandler(EntityManager em){
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

        try {
            String reply = errorFinder(p.getUsername(), p.getSsn(), p.getEmail());
            if (!reply.equals("none")) {
                throw new Exception(reply);
            }
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
     *
     * @return a proper error message if any of the parameters were duplicates
     * in database.
     */
    private String errorFinder(String username, String ssn, String email) {

        int i = em.createNamedQuery("Person.findBySsn").setParameter("ssn", ssn).getResultList().size();
        if (i > 0) {
            return "SSN already registered!";
        }
        i = em.createNamedQuery("Person.findByUsername").setParameter("username",username).getResultList().size();
        if (i > 0) {
            return "Username already registered!";
        }
        i = em.createNamedQuery("Person.findByEmail").setParameter("email",email).getResultList().size();
        if (i > 0) {
            return "Email already registered!";
        }
        return "none";
    }
}
