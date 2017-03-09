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
package se.kth.ict.iv1201.DTO;

public class RegisterDTO {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private String role;

    /**
     * Creates an instance of DTO
     */
    public RegisterDTO() {
    }

    /**
     * Get the value of role
     *
     * @return the value of role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the value of role
     *
     * @param role value of role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of surname
     *
     * @return the value of surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the value of surname
     *
     * @param surname value of surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the value of ssn
     *
     * @return the value of ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Set the value of ssn
     *
     * @param ssn value of ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
