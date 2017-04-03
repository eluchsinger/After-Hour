package dataLayer.users;

import dataLayer.tickets.TicketInstance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Fabian on 24.03.17.
 * User
 */
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String firstName;
    private Date dateOfBirth;
    private Gender gender;
    private ArrayList<TicketInstance> tickets;

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
}
