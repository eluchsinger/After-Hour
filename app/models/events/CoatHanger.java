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


    public CoatHanger(int coatHangerNumber){
        this.coatHangerNumber = coatHangerNumber;
    }

    public CoatHanger(){
        this.id = null;
        this.coatChecks = new ArrayList<>();
    }


}
