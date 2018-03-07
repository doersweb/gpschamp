package track.gpschamp.com.gpschamp.model.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sudhirharit on 04/02/18.
 */

public class UserData {


    @SerializedName("username")
    private String userName;

    @SerializedName("email")
    private String  email;

    @SerializedName("manager_id")
    private String managerId;

    @SerializedName("cpanel_privileges")
    private String cpanelPrivileges;

    @SerializedName("privileges")
    private String privileges;

    @SerializedName("privileges_imei")
    private String privilegesImei;

    @SerializedName("privileges_marker")
    private String privilegesMarker;

    @SerializedName("privileges_route")
    private String privilegesRoute;

    @SerializedName("privileges_zone")
    private String privilegesZone;

    @SerializedName("privileges_history")
    private boolean privilegesHistory;

    @SerializedName("privileges_reports")
    private boolean privilegesReports;

    @SerializedName("privileges_rilogbook")
    private boolean privilegesRilogBook;

    @SerializedName("privileges_dtc")
    private boolean privilegesDtc;

    @SerializedName("privileges_object_control")
    private boolean privilegesobjectControl;

    @SerializedName("privileges_image_gallery")
    private boolean privilegesImageGallery;

    @SerializedName("privileges_chat")
    private boolean privilegesChat;

    @SerializedName("privileges_subaccounts")
    private boolean privilegesSubAccounts;

    @SerializedName("billing")
    private boolean billing;

    @SerializedName("obj_add")
    private boolean objAdd;

    @SerializedName("obj_limit")
    private boolean objLimit;

    @SerializedName("obj_limit_num")
    private int objLimitNumber;

    @SerializedName("obj_days")
    private boolean objDays;

    @SerializedName("obj_days_dt")
    private String objDaysDate;

    @SerializedName("obj_edit")
    private boolean objEdit;

    @SerializedName("obj_history_clear")
    private boolean objHistoryClear;

    @SerializedName("map_sp")
    private String mapSp;

    @SerializedName("map_is")
    private String mapIs;

    @SerializedName("map_rc")
    private String mapRc;

    @SerializedName("map_rhc")
    private String mapRhc;

    @SerializedName("groups_collapsed")
    private GroupsCollapsed groupsCollapsed;

    @SerializedName("od")
    private String od;

    @SerializedName("ohc")
    private Ohc ohc;

    @SerializedName("sms_gateway")
    private boolean smsGateway;

    @SerializedName("sms_gateway_type")
    private String smsGatewayType;

    @SerializedName("sms_gateway_url")
    private String smsGatewayUrl;

    @SerializedName("sms_gateway_identifier")
    private String smsGatewayIdentifier;

    @SerializedName("sms_gateway_total_in_queue")
    private int smsGatewayTotalInQueue;

    @SerializedName("language")
    private String language;

    @SerializedName("unit_distance")
    private String unitDistance;

    @SerializedName("unit_capacity")
    private String unitCapacity;

    @SerializedName("unit_temperature")
    private String unitTemperature;

    @SerializedName("currency")
    private String currency;

    @SerializedName("time_zone")
    private String timeZone;

    @SerializedName("dst")
    private boolean dst;

    @SerializedName("dst_start")
    private String dstStart;

    @SerializedName("dst_end")
    private String dstEnd;

    @SerializedName("info")
    private UserInfo userInfo;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCpanelPrivileges() {
        return cpanelPrivileges;
    }

    public void setCpanelPrivileges(String cpanelPrivileges) {
        this.cpanelPrivileges = cpanelPrivileges;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getPrivilegesImei() {
        return privilegesImei;
    }

    public void setPrivilegesImei(String privilegesImei) {
        this.privilegesImei = privilegesImei;
    }

    public String getPrivilegesMarker() {
        return privilegesMarker;
    }

    public void setPrivilegesMarker(String privilegesMarker) {
        this.privilegesMarker = privilegesMarker;
    }

    public String getPrivilegesRoute() {
        return privilegesRoute;
    }

    public void setPrivilegesRoute(String privilegesRoute) {
        this.privilegesRoute = privilegesRoute;
    }

    public String getPrivilegesZone() {
        return privilegesZone;
    }

    public void setPrivilegesZone(String privilegesZone) {
        this.privilegesZone = privilegesZone;
    }

    public boolean isPrivilegesHistory() {
        return privilegesHistory;
    }

    public void setPrivilegesHistory(boolean privilegesHistory) {
        this.privilegesHistory = privilegesHistory;
    }

    public boolean isPrivilegesReports() {
        return privilegesReports;
    }

    public void setPrivilegesReports(boolean privilegesReports) {
        this.privilegesReports = privilegesReports;
    }

    public boolean isPrivilegesRilogBook() {
        return privilegesRilogBook;
    }

    public void setPrivilegesRilogBook(boolean privilegesRilogBook) {
        this.privilegesRilogBook = privilegesRilogBook;
    }

    public boolean isPrivilegesDtc() {
        return privilegesDtc;
    }

    public void setPrivilegesDtc(boolean privilegesDtc) {
        this.privilegesDtc = privilegesDtc;
    }

    public boolean isPrivilegesobjectControl() {
        return privilegesobjectControl;
    }

    public void setPrivilegesobjectControl(boolean privilegesobjectControl) {
        this.privilegesobjectControl = privilegesobjectControl;
    }

    public boolean isPrivilegesImageGallery() {
        return privilegesImageGallery;
    }

    public void setPrivilegesImageGallery(boolean privilegesImageGallery) {
        this.privilegesImageGallery = privilegesImageGallery;
    }

    public boolean isPrivilegesChat() {
        return privilegesChat;
    }

    public void setPrivilegesChat(boolean privilegesChat) {
        this.privilegesChat = privilegesChat;
    }

    public boolean isPrivilegesSubAccounts() {
        return privilegesSubAccounts;
    }

    public void setPrivilegesSubAccounts(boolean privilegesSubAccounts) {
        this.privilegesSubAccounts = privilegesSubAccounts;
    }

    public boolean isBilling() {
        return billing;
    }

    public void setBilling(boolean billing) {
        this.billing = billing;
    }

    public boolean isObjAdd() {
        return objAdd;
    }

    public void setObjAdd(boolean objAdd) {
        this.objAdd = objAdd;
    }

    public boolean isObjLimit() {
        return objLimit;
    }

    public void setObjLimit(boolean objLimit) {
        this.objLimit = objLimit;
    }

    public int getObjLimitNumber() {
        return objLimitNumber;
    }

    public void setObjLimitNumber(int objLimitNumber) {
        this.objLimitNumber = objLimitNumber;
    }

    public boolean isObjDays() {
        return objDays;
    }

    public void setObjDays(boolean objDays) {
        this.objDays = objDays;
    }

    public String getObjDaysDate() {
        return objDaysDate;
    }

    public void setObjDaysDate(String objDaysDate) {
        this.objDaysDate = objDaysDate;
    }

    public boolean isObjEdit() {
        return objEdit;
    }

    public void setObjEdit(boolean objEdit) {
        this.objEdit = objEdit;
    }

    public boolean isObjHistoryClear() {
        return objHistoryClear;
    }

    public void setObjHistoryClear(boolean objHistoryClear) {
        this.objHistoryClear = objHistoryClear;
    }

    public String getMapSp() {
        return mapSp;
    }

    public void setMapSp(String mapSp) {
        this.mapSp = mapSp;
    }

    public String getMapIs() {
        return mapIs;
    }

    public void setMapIs(String mapIs) {
        this.mapIs = mapIs;
    }

    public String getMapRc() {
        return mapRc;
    }

    public void setMapRc(String mapRc) {
        this.mapRc = mapRc;
    }

    public String getMapRhc() {
        return mapRhc;
    }

    public void setMapRhc(String mapRhc) {
        this.mapRhc = mapRhc;
    }

    public GroupsCollapsed getGroupsCollapsed() {
        return groupsCollapsed;
    }

    public void setGroupsCollapsed(GroupsCollapsed groupsCollapsed) {
        this.groupsCollapsed = groupsCollapsed;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public Ohc getOhc() {
        return ohc;
    }

    public void setOhc(Ohc ohc) {
        this.ohc = ohc;
    }

    public boolean isSmsGateway() {
        return smsGateway;
    }

    public void setSmsGateway(boolean smsGateway) {
        this.smsGateway = smsGateway;
    }

    public String getSmsGatewayType() {
        return smsGatewayType;
    }

    public void setSmsGatewayType(String smsGatewayType) {
        this.smsGatewayType = smsGatewayType;
    }

    public String getSmsGatewayUrl() {
        return smsGatewayUrl;
    }

    public void setSmsGatewayUrl(String smsGatewayUrl) {
        this.smsGatewayUrl = smsGatewayUrl;
    }

    public String getSmsGatewayIdentifier() {
        return smsGatewayIdentifier;
    }

    public void setSmsGatewayIdentifier(String smsGatewayIdentifier) {
        this.smsGatewayIdentifier = smsGatewayIdentifier;
    }

    public int getSmsGatewayTotalInQueue() {
        return smsGatewayTotalInQueue;
    }

    public void setSmsGatewayTotalInQueue(int smsGatewayTotalInQueue) {
        this.smsGatewayTotalInQueue = smsGatewayTotalInQueue;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnitDistance() {
        return unitDistance;
    }

    public void setUnitDistance(String unitDistance) {
        this.unitDistance = unitDistance;
    }

    public String getUnitCapacity() {
        return unitCapacity;
    }

    public void setUnitCapacity(String unitCapacity) {
        this.unitCapacity = unitCapacity;
    }

    public String getUnitTemperature() {
        return unitTemperature;
    }

    public void setUnitTemperature(String unitTemperature) {
        this.unitTemperature = unitTemperature;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public boolean isDst() {
        return dst;
    }

    public void setDst(boolean dst) {
        this.dst = dst;
    }

    public String getDstStart() {
        return dstStart;
    }

    public void setDstStart(String dstStart) {
        this.dstStart = dstStart;
    }

    public String getDstEnd() {
        return dstEnd;
    }

    public void setDstEnd(String dstEnd) {
        this.dstEnd = dstEnd;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
