package track.gpschamp.com.gpschamp.model.images;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 27/02/18.
 */

public class ObjectsImagesResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private DataResponse data;

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

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }
}
