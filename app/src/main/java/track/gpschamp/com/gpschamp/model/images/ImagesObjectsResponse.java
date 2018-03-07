package track.gpschamp.com.gpschamp.model.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudhirharit on 27/02/18.
 */

public class ImagesObjectsResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private List<ImagesObject> imagesObjectList;

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

    public List<ImagesObject> getImagesObjectList() {
        return imagesObjectList;
    }

    public void setImagesObjectList(List<ImagesObject> imagesObjectList) {
        this.imagesObjectList = imagesObjectList;
    }
}
