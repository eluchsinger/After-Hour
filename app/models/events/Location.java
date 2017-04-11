package models.events;

import javax.persistence.*;

/**
 * Created by Fabian on 25.03.2017.
 * Location for Events.
 */
@Entity
@Table(name = "tbl_location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String placeId;

    public Location() {
        this(null, "", "", "");
    }

    public Location(Integer id, String name, String description, String placeId){
        this.name = name;
        this.description = description;
        this.placeId = placeId;
    }

    public Integer getId() {
        return this.id;
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
