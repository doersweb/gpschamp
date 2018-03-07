package track.gpschamp.com.gpschamp.model.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 06/02/18.
 */

public class ObjectAccuracy {

    @SerializedName("stops")
    private String stops;

    @SerializedName("min_moving_speed")
    private String min_moving_speed;

    @SerializedName("min_idle_speed")
    private String min_idle_speed;

    @SerializedName("min_diff_points")
    private String min_diff_points;

    @SerializedName("use_gpslev")
    private String use_gpslev;

    @SerializedName("min_gpslev")
    private String min_gpslev;

    @SerializedName("use_hdop")
    private boolean use_hdop;

    @SerializedName("max_hdop")
    private String max_hdop;

    @SerializedName("min_fuel_speed")
    private String min_fuel_speed;

    @SerializedName("imin_ffmei")
    private String min_ff;

    @SerializedName("min_ft")
    private String min_ft;

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getMin_moving_speed() {
        return min_moving_speed;
    }

    public void setMin_moving_speed(String min_moving_speed) {
        this.min_moving_speed = min_moving_speed;
    }

    public String getMin_idle_speed() {
        return min_idle_speed;
    }

    public void setMin_idle_speed(String min_idle_speed) {
        this.min_idle_speed = min_idle_speed;
    }

    public String getMin_diff_points() {
        return min_diff_points;
    }

    public void setMin_diff_points(String min_diff_points) {
        this.min_diff_points = min_diff_points;
    }

    public String getUse_gpslev() {
        return use_gpslev;
    }

    public void setUse_gpslev(String use_gpslev) {
        this.use_gpslev = use_gpslev;
    }

    public String getMin_gpslev() {
        return min_gpslev;
    }

    public void setMin_gpslev(String min_gpslev) {
        this.min_gpslev = min_gpslev;
    }

    public boolean isUse_hdop() {
        return use_hdop;
    }

    public void setUse_hdop(boolean use_hdop) {
        this.use_hdop = use_hdop;
    }


    public String getMax_hdop() {
        return max_hdop;
    }

    public void setMax_hdop(String max_hdop) {
        this.max_hdop = max_hdop;
    }

    public String getMin_fuel_speed() {
        return min_fuel_speed;
    }

    public void setMin_fuel_speed(String min_fuel_speed) {
        this.min_fuel_speed = min_fuel_speed;
    }

    public String getMin_ff() {
        return min_ff;
    }

    public void setMin_ff(String min_ff) {
        this.min_ff = min_ff;
    }

    public String getMin_ft() {
        return min_ft;
    }

    public void setMin_ft(String min_ft) {
        this.min_ft = min_ft;
    }
}
