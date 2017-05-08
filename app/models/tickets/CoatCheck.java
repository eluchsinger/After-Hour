package models.tickets;

import models.events.CoatHanger;
import models.users.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fabian on 25.03.2017.
 * A Coatcheck
 */
@Entity
@Table(name = "tbl_coatChecks")
public class CoatCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = CoatHanger.class)
    private CoatHanger coatHanger;
    private Date handOverOn;
    private Date fetchedOn;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public CoatCheck(){
        this.id = null;
    }

    public CoatCheck(CoatHanger coatHanger, Date handOverOn, User user){
        this.coatHanger = coatHanger;
        this.handOverOn = handOverOn;
        this.user = user;
        this.fetchedOn = null;
    }

    public Integer getId(){
        return id;
    }

    public CoatHanger getCoatHanger(){
        return coatHanger;
    }

    /**
     * Checks if the jacket is already fetched,
     * sets the date when the jacket was fetched
     * and resets the hanger to null.
     */
    public CoatHanger fetch(Date fetchedOn) {
        CoatHanger hangerToFetchJacket = this.coatHanger;
        if(hangerToFetchJacket != null) {
            this.fetchedOn = fetchedOn;
            this.coatHanger = null;
            return hangerToFetchJacket;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoatCheck coatCheck = (CoatCheck) o;

        if (!id.equals(coatCheck.id)) return false;
        if (!coatHanger.equals(coatCheck.coatHanger)) return false;
        if (!handOverOn.equals(coatCheck.handOverOn)) return false;
        if (fetchedOn != null ? !fetchedOn.equals(coatCheck.fetchedOn) : coatCheck.fetchedOn != null) return false;
        return user.equals(coatCheck.user);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + coatHanger.hashCode();
        result = 31 * result + handOverOn.hashCode();
        result = 31 * result + (fetchedOn != null ? fetchedOn.hashCode() : 0);
        result = 31 * result + user.hashCode();
        return result;
    }
}
