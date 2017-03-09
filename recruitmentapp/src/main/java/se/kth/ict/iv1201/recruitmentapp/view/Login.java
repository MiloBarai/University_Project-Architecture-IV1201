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

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import se.kth.ict.iv1201.recruitmentapp.controller.LoginBean;

/**
 * A view manager. All login calls from JSF web view are handled through this
 * class.
 */
@Named(value = "login")
@RequestScoped
public class Login {

    @EJB
    LoginBean loginController;

    private String username;
    private String password;
    private String msg;

    @Inject
    private User userData;

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    /**
     * Get Msg value
     *
     * @return msg value or Empty String
     */
    public String getMsg() {
        if (msg == null) {
            return "";
        }
        return msg;
    }

    /**
     * Set the value of msg
     *
     * @param msg new value of msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
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
     * @param password new value of username
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Authenticates user
     *
     * @return String success or failure
     */
    public String auth() {
        try {
            loginController.authenticateUser(username, password);
        } catch (Exception ex) {
            String status = loginController.getRootcause(ex).getMessage();
            if (status != null) {
                msg = getStatus(status);
            } else {
                msg = ex.getMessage();
            }
            return "failure";
        }
        userData.setName(username);
        userData.setPassword(password);
        return userData.getRole().toLowerCase();
    }

    /**
     * Used onload to check if user loged in
     *
     * @return String, returns navigation case or ""
     */
    public String logedCheck() {
        if (userData.getName() != null) {
            username = userData.getName();
            password = userData.getPass();
            boolean success = loginController.authenticateUserEncrypted(username, password);
            String outcome = auth();
            if (success) {
                try {
                    return loginController.getUserRole(username);
                } catch (Exception e) {
                    msg = "Invalid active session";
                }

            }
        }
        return "";
    }

    private String getStatus(String status) {
        String ret = "";
        switch (status) {
            case "106":
                ret = "Sorry, we could not find you! ";
                break;
            case "107":
                ret = "Woops! Incorrect password!";
                break;
            default:
                ret = "An Error Occured, please send us an email at mail@kth.se";
        }
        return ret;
    }
}
