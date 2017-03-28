package models;

/**
 * Created by Esteban Luchsinger on 23.03.2017.
 * An event (a Party).
 */
public class Event {
    private String title;
    private String description;

    public Event(final String title, final String description) {
        this.title = title;
        this.description = description;
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
