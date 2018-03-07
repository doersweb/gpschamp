package track.gpschamp.com.gpschamp.model.images;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 27/02/18.
 */

public class ImagesData {


    @SerializedName("obj_name")
    private String objectName;

    @SerializedName("img_file")
    private String imageFile;

    @SerializedName("dt_server")
    private String dateServer;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getDateServer() {
        return dateServer;
    }

    public void setDateServer(String dateServer) {
        this.dateServer = dateServer;
    }
}
