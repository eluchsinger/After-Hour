package models.users;

<<<<<<< HEAD
import models.tickets.TicketInstance;
=======

import models.tickets.Ticket;
import models.utils.TimeIgnoringDateComparator;
import org.joda.time.DateTimeComparator;
>>>>>>> refs/remotes/origin/developer

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Fabian on 24.03.17.
 * User
 */
@Entity
<<<<<<< HEAD
@Table(name = "tbl_user", schema = "public")
=======
@Table(name = "tbl_users", schema = "public")
@NamedNativeQueries({
        @NamedNativeQuery(name = "User.count", query = "SELECT COUNT(*) FROM tbl_users"),
        @NamedNativeQuery(name = "User.reset", query = "TRUNCATE tbl_users CASCADE"),
        //@NamedNativeQuery(name = "User.getUserByEmail", query = "SELECT * FROM tbl_users WHERE email = 'silvio.berlusconi@italy.it'" )
})
@NamedQueries({
        @NamedQuery(name = "User.getUserByEmail", query="SELECT u FROM User u WHERE u.email = :email")
})
>>>>>>> refs/remotes/origin/developer
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String firstName;
    private Date dateOfBirth;
    private Gender gender;
    @Transient
    private ArrayList<TicketInstance> tickets;

    public User(){
    }

    public User(int id, String name, String firstName, Date dateOfBirth, Gender gender){
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        tickets = new ArrayList<>();
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getName(){
        return name;
    }

    public void addTicket(TicketInstance ticket){
        tickets.add(ticket);
    }

    public ArrayList<TicketInstance> getTickets(){
        return tickets;
    }

    public void addTickets(ArrayList<TicketInstance> tickets) {
        this.tickets = tickets;
    }
<<<<<<< HEAD
=======

    public Gender getGender(){ return this.gender; }

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
        return tickets.equals(user.tickets);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + tickets.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + "(" + this.getId() + ")";
    }

    //endregion
>>>>>>> refs/remotes/origin/developer
}