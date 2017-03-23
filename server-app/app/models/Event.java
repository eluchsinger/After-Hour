package models;

/**
 * Created by Esteban Luchsinger on 23.03.2017.
 * An event (a Party).
 */
public class Event {
    private int id;
    private String title;
    private String description;

    public Event(final int id, final String title, final String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setId(final int value) {
        this.id = value;
    }

    public int getId() {
        return id;
    }

    public void setTitle(final String value) {
        this.title = value;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(final String value) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }
}
