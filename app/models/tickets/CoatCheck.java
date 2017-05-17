package models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NamedQueries({
        @NamedQuery(name = "CoatCheck.getByPublicIdentifier", query="SELECT c FROM CoatCheck c WHERE c.publicIdentifier = :publicIdentifier")
})
public class CoatCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private Integer publicIdentifier;
    @ManyToOne(targetEntity = CoatHanger.class)
    private CoatHanger coatHanger;
    private Date handOverOn;
    @JsonIgnore
    private Date fetchedOn;
    @JsonIgnore
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
        this.publicIdentifier = user.hashCode() + handOverOn.hashCode() + coatHanger.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setPublicIdentifier(Integer id){
        this.publicIdentifier = id;
    }

    public Integer getPublicIdentifier(){
        return publicIdentifier;
    }

    public CoatHanger getCoatHanger() {
        return coatHanger;
    }

    public void setCoatHanger(CoatHanger coatHanger){
        this.coatHanger = coatHanger;
    }

    public Date getHandOverOn() {
        return handOverOn;
    }

    public Date getFetchedOn() {
        return fetchedOn;
    }

    public User getUser() {
        return user;
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

        if (id != null ? !id.equals(coatCheck.id) : coatCheck.id != null) return false;
        if (publicIdentifier != null ? !publicIdentifier.equals(coatCheck.publicIdentifier) : coatCheck.publicIdentifier != null)
            return false;
        if (coatHanger != null ? !coatHanger.equals(coatCheck.coatHanger) : coatCheck.coatHanger != null) return false;
        if (handOverOn != null ? !handOverOn.equals(coatCheck.handOverOn) : coatCheck.handOverOn != null) return false;
        if (fetchedOn != null ? !fetchedOn.equals(coatCheck.fetchedOn) : coatCheck.fetchedOn != null) return false;
        return user != null ? user.equals(coatCheck.user) : coatCheck.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (publicIdentifier != null ? publicIdentifier.hashCode() : 0);
        result = 31 * result + (coatHanger != null ? coatHanger.hashCode() : 0);
        result = 31 * result + (handOverOn != null ? handOverOn.hashCode() : 0);
        result = 31 * result + (fetchedOn != null ? fetchedOn.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
