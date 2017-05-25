package models.events;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 25.03.2017.
 * Location for Events.
 */
@Entity
@Table(name = "tbl_location")
@NamedQueries({
        @NamedQuery(name = "Location.getLocationByName", query="SELECT l FROM Location l WHERE l.name = :name")
})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String placeId;
    @OneToMany(mappedBy = "location")
    private List<CoatHanger> coatHangers;

    public Location() {
        this(null, "", "", "");
    }

    public Location(Integer locationId, String name, String description, String placeId){
        this.name = name;
        this.description = description;
        this.placeId = placeId;
        this.coatHangers = new ArrayList<>();
        this.id = locationId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer locationId){
        this.id = locationId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPlaceId() {
        return this.placeId;
    }
}
