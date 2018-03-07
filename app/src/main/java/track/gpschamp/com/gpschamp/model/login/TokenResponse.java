package track.gpschamp.com.gpschamp.model.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 30/01/18.
 */

public class TokenResponse {

    @SerializedName("token")
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
