package track.gpschamp.com.gpschamp.model.object;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.POST;

/**
 * Created by sudhirharit on 06/02/18.
 */


public class FieldObjects {

    @SerializedName("imei")
    private String imei;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("angle")
    private String angle;

    @SerializedName("altitude")
    private String altitude;

    @SerializedName("acc")
    private String acc;

    @SerializedName("speed")
    private String speed;

    @SerializedName("dt_tracker")
    private String dt_tracker;

    @SerializedName("protocol")
    private String protocol;

    @SerializedName("name")
    private String name;

    @SerializedName("icon")
    private String icon;

    @SerializedName("address")
    private String address ="";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @SerializedName("batl")
    private String batteryLevel;

    @SerializedName("ac_status")
    private String acStatus;

    @SerializedName("gpslev")
    private int gpslevel;

    public int getGpslevel() {
        return gpslevel;
    }

    public void setGpslevel(int gpslevel) {
        this.gpslevel = gpslevel;
    }

    public String getDateServer() {
        return dateServer;
    }

    public void setDateServer(String dateServer) {
        this.dateServer = dateServer;
    }

    @SerializedName("dt_server")
    private String dateServer;

    public String getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }

    @SerializedName("device")
    private String device;

    @SerializedName("sim_number")
    private String simNumber;

    @SerializedName("model")
    private String model;

    @SerializedName("active")
    private String active;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
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

    public String getDt_tracker() {
        return dt_tracker;
    }

    public void setDt_tracker(String dt_tracker) {
        this.dt_tracker = dt_tracker;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
