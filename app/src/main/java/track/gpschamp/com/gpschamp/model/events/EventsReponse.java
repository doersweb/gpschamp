package track.gpschamp.com.gpschamp.model.events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudhirharit on 14/02/18.
 */

public class EventsReponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    EventsObject eventsObjectList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EventsObject getEventsObjectList() {
        return eventsObjectList;
    }

    public void setEventsObjectList(EventsObject eventsObjectList) {
        this.eventsObjectList = eventsObjectList;
    }
}
