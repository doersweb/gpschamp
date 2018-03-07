package track.gpschamp.com.gpschamp.model.events;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 14/02/18.
 */

public class EventRow {

    @SerializedName("id")
    private String id;

    @SerializedName("cell")
    private EventCell eventCell;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventCell getEventCell() {
        return eventCell;
    }

    public void setEventCell(EventCell eventCell) {
        this.eventCell = eventCell;
    }
}
