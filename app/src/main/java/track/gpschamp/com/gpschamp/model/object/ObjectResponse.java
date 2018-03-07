package track.gpschamp.com.gpschamp.model.object;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by sudhirharit on 06/02/18.
 */

public class ObjectResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;

    public List<FieldObjects> getObjectsData() {
        return objectsData;
    }

    public void setObjectsData(List<FieldObjects> objectsData) {
        this.objectsData = objectsData;
    }

    @SerializedName("data")
    private List<FieldObjects> objectsData;

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


}
