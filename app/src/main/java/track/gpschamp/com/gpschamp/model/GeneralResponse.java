package track.gpschamp.com.gpschamp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class GeneralResponse {


    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
