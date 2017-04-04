package models.events;

/**
 * Created by Fabian on 25.03.2017.
 * Location for Events.
 */
public class Location {
    private String name;
    private String description;
    private int placeId;

    public Location(String name, String description, int placeId){
        this.name = name;
        this.description = description;
        this.placeId = placeId;
    }
}
