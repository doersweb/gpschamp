package track.gpschamp.com.gpschamp.model.singlevent;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 14/02/18.
 */

public class SingleEventResponse {


    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private SingleEventObject singleEventObject;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SingleEventObject getSingleEventObject() {
        return singleEventObject;
    }

    public void setSingleEventObject(SingleEventObject singleEventObject) {
        this.singleEventObject = singleEventObject;
    }
}
