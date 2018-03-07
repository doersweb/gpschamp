package track.gpschamp.com.gpschamp.model.history;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class HistoryData {

    @SerializedName("route")
    List<HistoryObject> historyObjects;

    @SerializedName("stops_duration")
    private String stopDuration;

    @SerializedName("drives_duration")
    private String driveDuration;

    @SerializedName("top_speed")
    private String topSpeed;

    @SerializedName("stop_duration_time")
    private int stopDurationTime;

    @SerializedName("drives_duration_time")
    private int driveDurationTime;

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    @SerializedName("route_length")
    private double routeLength;

    public String getStopDuration() {
        return stopDuration;
    }

    public void setStopDuration(String stopDuration) {
        this.stopDuration = stopDuration;
    }

    public String getDriveDuration() {
        return driveDuration;
    }

    public void setDriveDuration(String driveDuration) {
        this.driveDuration = driveDuration;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public int getStopDurationTime() {
        return stopDurationTime;
    }

    public void setStopDurationTime(int stopDurationTime) {
        this.stopDurationTime = stopDurationTime;
    }

    public int getDriveDurationTime() {
        return driveDurationTime;
    }

    public void setDriveDurationTime(int driveDurationTime) {
        this.driveDurationTime = driveDurationTime;
    }

    public List<HistoryObject> getHistoryObjects() {
        return historyObjects;
    }

    public void setHistoryObjects(List<HistoryObject> historyObjects) {
        this.historyObjects = historyObjects;
    }
}
