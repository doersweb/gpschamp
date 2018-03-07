package track.gpschamp.com.gpschamp.model.singlevent;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 14/02/18.
 */

public class SingleEventObject {

    @SerializedName("name")
    private String name;

    @SerializedName("imei")
    private String imei;

    @SerializedName("event_desc")
    private String eventDescription;

    @SerializedName("dt_server")
    private String dateServer;

    @SerializedName("dt_tracker")
    private String dateTracker;

    @SerializedName("lat")
    private String latitude;

    @SerializedName("lng")
    private String longitude;

    @SerializedName("altitude")
    private String altitude;

    @SerializedName("angle")
    private String angle;

    @SerializedName("speed")
    private int speed;

    @SerializedName("params")
    private EventObjectParams eventObjectParams;

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

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getDateServer() {
        return dateServer;
    }

    public void setDateServer(String dateServer) {
        this.dateServer = dateServer;
    }

    public String getDateTracker() {
        return dateTracker;
    }

    public void setDateTracker(String dateTracker) {
        this.dateTracker = dateTracker;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public EventObjectParams getEventObjectParams() {
        return eventObjectParams;
    }

    public void setEventObjectParams(EventObjectParams eventObjectParams) {
        this.eventObjectParams = eventObjectParams;
    }
}
