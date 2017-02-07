/**
 * @author Arvid Persson Moosavi <amoosavi at kth.se>
 */
package se.kth.ict.iv1201.recruitmentapp.view;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.kth.ict.iv1201.recruitmentapp.controller.PersonFacade;

@Named
@RequestScoped
public class Web {

    @EJB
    private PersonFacade pf;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private long role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public void save() {
        try {
            // Default role set to applicant (2)
            this.role = 2;
            pf.Save(username, password, name, surname, ssn, email, role);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void save(long role) {
        try {
            this.role = role;
            pf.Save(username, password, name, surname, ssn, email, role);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

}
