package track.gpschamp.com.gpschamp.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 04/02/18.
 */

public class UserResponse {


    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private UserData userData;

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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
