package track.gpschamp.com.gpschamp.model.events;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 14/02/18.
 */

public class EventCell {

    @SerializedName("dt_tracker")
    private String eventTime;

    @SerializedName("object_event")
    private String eventName;

    @SerializedName("object_name")
    private String objectName;


    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
