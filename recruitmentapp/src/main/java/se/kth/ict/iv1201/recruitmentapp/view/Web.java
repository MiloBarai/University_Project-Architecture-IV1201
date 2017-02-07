/**
 * @author Arvid Persson Moosavi <amoosavi at kth.se>
 */
package se.kth.ict.iv1201.recruitmentapp.view;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.kth.ict.iv1201.recruitmentapp.controller.PersonFacade;

/**
 * A view manager. All calls from JSF web view are handled through this class.
 */
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

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getUsername() {
        return null;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getPassword() {
        return null;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getName() {
        return null;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getSurname() {
        return null;
    }

    /**
     * Set the value of surname
     *
     * @param surname new value of surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getSsn() {
        return null;
    }

    /**
     * Set the value of ssn
     *
     * @param ssn new value of ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getEmail() {
        return null;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Calls <code>PersonFacade.Save()</code> with given arguments. Default role
     * set to 2, which is applicant.
     *
     * @throws EJBException
     */
    public String save() {
        try {
            // Default role set to applicant (2)
            this.role = 2;
            pf.Save(username, password, name, surname, ssn, email, role);
        } catch (Exception e) {
            username="";
            password="";
            name="";
            surname="";
            ssn="";
            email="";
            return "failure";
        }
        return "success";
    }

    /**
     * Calls <code>PersonFacade.Save()</code> with given arguments.
     *
     * @param role user's role.
     * @throws EJBException
     */
    public void save(long role) {
        try {
            this.role = role;
            pf.Save(username, password, name, surname, ssn, email, role);
        } catch (Exception e) {
            
        }
    }
}
