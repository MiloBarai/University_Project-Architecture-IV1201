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

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getUsername() {
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getPassword() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getSurname() {
        return null;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getSsn() {
        return null;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getEmail() {
        return null;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Withdraws the amount set by the latest call to
     * <code>setTransactionAmount</code> from the account specified by
     * <code>currentAcct.getAcctNo()</code>.
     */
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
