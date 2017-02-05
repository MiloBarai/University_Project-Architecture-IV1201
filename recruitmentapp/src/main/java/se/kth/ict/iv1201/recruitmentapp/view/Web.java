/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.view;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.kth.ict.iv1201.recruitmentapp.session.PersonFacade;

/**
 *
 * @author Arvid Persson Moosavi <amoosavi at kth.se>
 */
@Named
@RequestScoped

public class Web {

    @EJB
    private PersonFacade pf;

    private Long personId;
    private String ssn;
    private String email;
    private String password;
    private String username;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void save() {
        try {
            pf.Save(personId, ssn, email, password, username);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

}
