package track.gpschamp.com.gpschamp.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 04/02/18.
 */

public class Ohc {

    @SerializedName("no_connection")
    private boolean noConnection;

    @SerializedName("no_connection_color")
    private String noConnectionColor;

    @SerializedName("stopped")
    private boolean stopped;

    @SerializedName("stopped_color")
    private boolean stoppedColor;

    @SerializedName("moving")
    private boolean moving;

    @SerializedName("moving_color")
    private String movingColor;

    @SerializedName("engine_idle")
    private boolean engineIdle;

    @SerializedName("engine_idle_color")
    private boolean engineIdleColor;

    @SerializedName("event_sos")
    private boolean eventSos;

    @SerializedName("event_sos_color")
    private String eventSosColor;

    public boolean isNoConnection() {
        return noConnection;
    }

    public void setNoConnection(boolean noConnection) {
        this.noConnection = noConnection;
    }

    public String getNoConnectionColor() {
        return noConnectionColor;
    }

    public void setNoConnectionColor(String noConnectionColor) {
        this.noConnectionColor = noConnectionColor;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isStoppedColor() {
        return stoppedColor;
    }

    public void setStoppedColor(boolean stoppedColor) {
        this.stoppedColor = stoppedColor;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public String getMovingColor() {
        return movingColor;
    }

    public void setMovingColor(String movingColor) {
        this.movingColor = movingColor;
    }

    public boolean isEngineIdle() {
        return engineIdle;
    }

    public void setEngineIdle(boolean engineIdle) {
        this.engineIdle = engineIdle;
    }

    public boolean isEngineIdleColor() {
        return engineIdleColor;
    }

    public void setEngineIdleColor(boolean engineIdleColor) {
        this.engineIdleColor = engineIdleColor;
    }

    public boolean isEventSos() {
        return eventSos;
    }

    public void setEventSos(boolean eventSos) {
        this.eventSos = eventSos;
    }

    public String getEventSosColor() {
        return eventSosColor;
    }

    public void setEventSosColor(String eventSosColor) {
        this.eventSosColor = eventSosColor;
    }
}
