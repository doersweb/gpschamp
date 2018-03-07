package track.gpschamp.com.gpschamp.model.events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudhirharit on 14/02/18.
 */

public class EventsObject {

    @SerializedName("page")
    private String page;

    @SerializedName("total")
    private String total;

    @SerializedName("records")
    private String records;

    @SerializedName("rows")
    private List<EventRow> eventRows;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public List<EventRow> getEventRows() {
        return eventRows;
    }

    public void setEventRows(List<EventRow> eventRows) {
        this.eventRows = eventRows;
    }
}
