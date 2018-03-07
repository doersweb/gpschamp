package track.gpschamp.com.gpschamp.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 04/02/18.
 */

public class GroupsCollapsed {

    @SerializedName("objects")
    private boolean objects;

    @SerializedName("markers")
    private boolean markers;

    @SerializedName("routes")
    private boolean routes;

    @SerializedName("zones")
    private boolean zones;

    public boolean isObjects() {
        return objects;
    }

    public void setObjects(boolean objects) {
        this.objects = objects;
    }

    public boolean isMarkers() {
        return markers;
    }

    public void setMarkers(boolean markers) {
        this.markers = markers;
    }

    public boolean isRoutes() {
        return routes;
    }

    public void setRoutes(boolean routes) {
        this.routes = routes;
    }

    public boolean isZones() {
        return zones;
    }

    public void setZones(boolean zones) {
        this.zones = zones;
    }
}
