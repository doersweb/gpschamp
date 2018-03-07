package track.gpschamp.com.gpschamp.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sudhirharit on 04/03/18.
 */
@Entity(nameInDb = "notification")
public class Notification {

    @Id
    private String eventId;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "body")
    private String message;

    @Generated(hash = 1941078704)
    public Notification(String eventId, String title, String message) {
        this.eventId = eventId;
        this.title = title;
        this.message = message;
    }

    @Generated(hash = 1855225820)
    public Notification() {
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
