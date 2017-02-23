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
package se.kth.ict.iv1201.recruitmentapp.view;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.kth.ict.iv1201.recruitmentapp.controller.PersonFacade;
import se.kth.ict.iv1201.recruitmentapp.model.Role;

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
    private String[] errorMsg = new String[1];
    private String[] roles;
    private String role;

    /**
     * Never used but JSF does not support write-only properties.
     *
     * @return null
     */
    public String getRole() {
        return null;
    }

    /**
     * Set the value of role
     *
     * @param role new value of role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get a list of available roles by calling
     * <code>PersonFacade.getRoles()</code>.
     *
     * @return an array of available roles
     */
    public String[] getRoles() {
        if (roles == null) {
            List<Role> temp = pf.getRoles();
            roles = new String[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                roles[i] = temp.get(i).getName();
            }
        }
        return roles;
    }

    /**
     * Get the value of errorMsg
     *
     * @return the value of errorMsg
     */
    public String[] getErrorMsg() {
        return errorMsg;
    }

    /**
     * Set the value of errorMsg
     *
     * @param errorMsg new value of errorMsg
     */
    public void setErrorMsg(String[] errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Never used but JSF does not support write-only properties.
     *
     * @return null
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
     *
     * @return null
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
     *
     * @return null
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
     *
     * @return null
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
     *
     * @return null
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
     *
     * @return null
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
     * Calls <code>PersonFacade.Save()</code> with given arguments. Castes
     * Exception if failed with status code
     *
     * @return Failure or Success depending on outcome of method call.
     */
    public String save() {
        try {
            pf.Save(username, password, name, surname, ssn, email, role);
        } catch (Exception e) {
            showErrorMsg(e);
            resetFields();
            return "failure";
        }
        return "success";
    }

    /**
     * Fetches error codes and translates them by calling
     * <code>errorTranslator()</code>
     */
    private void showErrorMsg(Exception e) {

        String error = pf.getRootCause(e).getMessage();
        String[] errorlist = error.split(":");

        for (int i = 0; i < errorlist.length; i++) {
            errorlist[i] = errorTranslator(errorlist[i]);
        }
        errorMsg = errorlist;
    }

    /**
     * Clear all user registration fields
     */
    private void resetFields() {
        username = "";
        password = "";
        name = "";
        surname = "";
        ssn = "";
        email = "";
    }

    /**
     * A collection of error messages which are translated by the error code
     */
    private String errorTranslator(String code) {
        String ret;
        switch (code) {
            case "100":
                ret = "User with SSN already registered";
                break;
            case "101":
                ret = "Username is taken";
                break;
            case "102":
                ret = "Email is already registered";
            case "103":
                ret = "Invalid format on SSN";
                break;
            case "104":
                ret = "Unreasonable SSN";
                break;
            case "105":
                ret = "Email not Reachable";
                break;
            default:
                ret = "Unknow Error Occured, please contact us at mail@kth.se";
        }
        return ret;
    }
}
