package models.events;

import models.tickets.CoatCheck;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco on 23.04.2017.
 */
@Entity
@Table(name = "tbl_coatHangers")
public class CoatHanger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer coatHangerNumber;
    @OneToMany(mappedBy = "coatHanger")
    private List<CoatCheck> coatChecks;
    @ManyToOne(targetEntity = Location.class)
    private Location location;

    public CoatHanger(){
        this.id = null;
        this.coatChecks = new ArrayList<>();
    }

    public CoatHanger(Integer coatHangerNumber, Location location){
        this.coatHangerNumber = coatHangerNumber;
        this.location = location;
        this.id = null;
    }

    public CoatHanger(Integer id, Integer coatHangerNumber, Location location){
        this.id = id;
        this.coatHangerNumber = coatHangerNumber;
        this.location = location;
        this.id = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoatHanger that = (CoatHanger) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (coatHangerNumber != null ? !coatHangerNumber.equals(that.coatHangerNumber) : that.coatHangerNumber != null)
            return false;
        if (coatChecks != null ? !coatChecks.equals(that.coatChecks) : that.coatChecks != null) return false;
        return location != null ? location.equals(that.location) : that.location == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (coatHangerNumber != null ? coatHangerNumber.hashCode() : 0);
        result = 31 * result + (coatChecks != null ? coatChecks.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
