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
package se.kth.ict.iv1201.recruitmentapp.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;
import se.kth.ict.iv1201.DTO.RegisterDTO;

/**
 * A persistent representation of a Person.
 */
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId")
    , @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name")
    , @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.surname = :surname")
    , @NamedQuery(name = "Person.findBySsn", query = "SELECT p FROM Person p WHERE p.ssn = :ssn")
    , @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email")
    , @NamedQuery(name = "Person.findByPassword", query = "SELECT p FROM Person p WHERE p.password = :password")
    , @NamedQuery(name = "Person.findByUsername", query = "SELECT p FROM Person p WHERE p.username = :username")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "ssn")
    @NotNull
    private String ssn;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "email")
    @NotNull
    private String email;
    @Column(name = "password")
    @NotNull
    private String password;
    @Column(name = "username")
    @NotNull
    private String username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private Collection<Application> applicationCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne
    private Role roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private Collection<CompetenceProfile> competenceProfileCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private Collection<Availability> availabilityCollection;

    /**
     * Creates a new instance of Person
     */
    public Person() {
    }

    /**
     * Creates a new instance of Person
     *
     * @param username given username of Person instance
     * @param password given password of Person instance
     * @param name given name of Person instance
     * @param surname given surname of Person instance
     * @param ssn given Social Security Number (SSN) of Person instance
     * @param email given email of Person instance
     * @param role given role object
     *
     */
    public Person(String username, String password, String name, String surname, String ssn, String email, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.roleId = role;
    }

    public Person(RegisterDTO regDTO, Role personRole) {
        this.username = regDTO.getUsername();
        this.password = regDTO.getPassword();
        this.name = regDTO.getName();
        this.surname = regDTO.getSurname();
        this.ssn = regDTO.getSsn();
        this.email=regDTO.getEmail();
        this.roleId=personRole;
    }

    /**
     * Get the value of personId
     *
     * @return the value of personId
     */
    public Long getPersonId() {
        return personId;
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
     * Sets the value of username
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
     * Sets the value of password
     *
     * @param password value of username
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
     * Sets the value of name
     *
     * @param name value of username
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
     * Sets the value of surname
     *
     * @param surname value of surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the value of Social Security Number (SSN)
     *
     * @return the value of ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the value of ssn
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
     * Sets the value of email
     *
     * @param email value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of roleId
     *
     * @return the value of roleId
     */
    public Role getRoleId() {
        return roleId;
    }

    /**
     * Sets the value of roleId
     *
     * @param roleId value of roleId
     */
    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }

    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

    @XmlTransient
    public Collection<CompetenceProfile> getCompetenceProfileCollection() {
        return competenceProfileCollection;
    }

    public void setCompetenceProfileCollection(Collection<CompetenceProfile> competenceProfileCollection) {
        this.competenceProfileCollection = competenceProfileCollection;
    }

    @XmlTransient
    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.recruitmentapp.entity.Person[ personId=" + personId + " ]";
    }
}
