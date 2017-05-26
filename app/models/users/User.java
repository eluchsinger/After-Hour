package models.users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.tickets.CoatCheck;
import models.tickets.Ticket;
import models.utils.TimeIgnoringDateComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fabian Schwyter on 24.03.17.
 * User Account of the service After-Hour.
 */
@Entity
@Table(name = "tbl_users", schema = "public")
@NamedNativeQueries({
        @NamedNativeQuery(name = "User.count", query = "SELECT COUNT(*) FROM tbl_users"),
        @NamedNativeQuery(name = "User.reset", query = "TRUNCATE tbl_users CASCADE"),
        //@NamedNativeQuery(name = "User.getUserByEmail", query = "SELECT * FROM tbl_users WHERE email = 'silvio.berlusconi@italy.it'" )
})
@NamedQueries({
        @NamedQuery(name = "User.getUserByEmail", query="SELECT u FROM User u WHERE u.email = :email")
})
public class User {
    private static final int TICKETS_INIT_SIZE = 2;
    private static final int COATCHECKS_INIT_SIZE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean isEmployee;

    // Mapped by the name of the attribute on the other side.
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    private List<CoatCheck> coatChecks;

    @JsonIgnore
    private String pictureName;

    //region Constructors
    public User(){
        this.id = null;
    }

    public User(final Integer id, final String email, final String lastName, final String firstName, final Date dateOfBirth, final Gender gender, final String password, final boolean isEmployee, final String pictureName){
        this.id = id;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
        this.isEmployee = isEmployee;
        this.pictureName = pictureName;

        tickets = new ArrayList<>(TICKETS_INIT_SIZE);
        coatChecks = new ArrayList<>(COATCHECKS_INIT_SIZE);
    }

    public User(final Integer id, final String email, final String lastName, final String firstName, final Date dateOfBirth, final Gender gender, final String password){
        this(id, email, lastName, firstName, dateOfBirth, gender, password, false, "" );
    }

    //Default Password
    public User(final Integer id, final String email, final String lastName, final String firstName, final Date dateOfBirth, final Gender gender) {
        this(id, email, lastName, firstName, dateOfBirth, gender, "123456");
    }

    //DB generated ID
    public User(final String email, final String lastName, final String firstName, final Date dateOfBirth, final Gender gender, final String password) {
        this(null, email, lastName, firstName, dateOfBirth, gender, password);
    }

    //DB generated ID and default password
    public User(final String email, final String lastName, final String firstName, final Date dateOfBirth, final Gender gender) {
        this(null, email, lastName, firstName, dateOfBirth, gender, "123456");
    }


    //endregion Constructors

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public List<Ticket> getTickets(){
        return tickets;
    }

    public List<CoatCheck> getCoatChecks() { return coatChecks; }

    public void setCoatChecks(List<CoatCheck> coatChecks) {
        this.coatChecks = coatChecks;
    }

    public void addTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Gender getGender(){ return this.gender; }

    public String getPictureName(){
        return this.pictureName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPictureName(java.lang.String pictureName) {
        this.pictureName = pictureName;
    }

    public boolean compareWithPassword(String inputPassword){
        return inputPassword.equals(password);
    }

    //region Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (new TimeIgnoringDateComparator().compare(dateOfBirth, user.dateOfBirth) != 0) return false;
        if (gender != user.gender) return false;
        if (coatChecks.equals(user.coatChecks)) return false;
        return tickets.equals(user.tickets);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + "(" + this.getId() + ")";
    }

    //endregion
}