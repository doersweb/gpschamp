package track.gpschamp.com.gpschamp.model.images;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 27/02/18.
 */

public class ImagesObject {

    @SerializedName("name")
    private String name;

    @SerializedName("imei")
    private String imei;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
