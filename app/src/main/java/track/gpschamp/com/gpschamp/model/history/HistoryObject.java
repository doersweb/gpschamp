package track.gpschamp.com.gpschamp.model.history;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 14/02/18.
 */

public class HistoryObject {

    @SerializedName("lat")
    private String latitude;

    @SerializedName("lng")
    private String logitude;

    @SerializedName("acc")
    private String acc;

    @SerializedName("speed")
    private String speed;

    @SerializedName("dt_server")
    private String dt_server;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDt_server() {
        return dt_server;
    }

    public void setDt_server(String dt_server) {
        this.dt_server = dt_server;
    }
}
