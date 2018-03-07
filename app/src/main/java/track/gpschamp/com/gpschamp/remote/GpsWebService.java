package track.gpschamp.com.gpschamp.remote;


import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import track.gpschamp.com.gpschamp.model.GeneralResponse;
import track.gpschamp.com.gpschamp.model.events.EventsReponse;
import track.gpschamp.com.gpschamp.model.history.HistoryResponse;
import track.gpschamp.com.gpschamp.model.images.ImagesObject;
import track.gpschamp.com.gpschamp.model.images.ImagesObjectsResponse;
import track.gpschamp.com.gpschamp.model.images.ObjectsImagesResponse;
import track.gpschamp.com.gpschamp.model.login.LoginResponse;
import track.gpschamp.com.gpschamp.model.object.ObjectResponse;
import track.gpschamp.com.gpschamp.model.singlevent.SingleEventResponse;

/**
 * Created by sudhirharit on 27/01/18.
 */

public interface GpsWebService {


    @POST("rest/user/generateToken")
    @FormUrlEncoded
    Call<LoginResponse> generateUserToken(@Field("username") String userName,
                                          @Field("password") String password,
                                          @Field("mac_id") String fcmId);

    @POST("rest/objects/getAllObjects")
    @FormUrlEncoded
    Call<ObjectResponse> getObjectList(@Field("token") String tokrn);

    @POST("rest/history/getObjectHistory")
    @FormUrlEncoded
    Call<HistoryResponse> getObjectHistory(@Field("token") String token,
                                           @Field("imei") String imei,
                                           @Field("dtf") String dateFrom,
                                           @Field("dtt") String dateTo,
                                           @Field("min_stop_duration") String duration);

    @POST("rest/events/getEventsList")
    @FormUrlEncoded
    Call<EventsReponse> getEventsList(@Field("token") String token,
                                      @Field("s") String s,
                                      @Field("page") int page,
                                      @Field("rows") int rows,
                                      @Field("sidx") String filter);

    @POST("rest/events/getEventData")
    @FormUrlEncoded
    Call<SingleEventResponse> getSingleEventDetails(@Field("token") String token,
                                                    @Field("event_id") String event_id);


    @POST("rest/user/forgotPassword")
    @FormUrlEncoded
    Call<GeneralResponse> forgotPassword(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("phone") String phone);

    @POST("rest/user/signUp")
    @FormUrlEncoded
    Call<GeneralResponse> registerUser(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("phone") String phone);

    @POST("rest/user/logoutUser")
    @FormUrlEncoded
    Call<GeneralResponse> logoutUser(@Field("token") String token);

    @POST("rest/commands/sendCommand")
    @FormUrlEncoded
    Call<GeneralResponse> sendCommand(@Field("imei") String imei,
                                      @Field("name") String name,
                                      @Field("gateway") String gateway,
                                      @Field("type") String type,
                                      @Field("cmd_") String command);


    @POST("rest/images/getObjectsWithImages")
    @FormUrlEncoded
    Call<ImagesObjectsResponse> getObjectWithImages(@Field("token") String token);

    @POST("rest/images/getObjectImages")
    @FormUrlEncoded
    Call<ObjectsImagesResponse> getImages(@Field("token") String token,
                                          @Field("rows") int rows,
                                          @Field("page") int page,
                                          @Field("sidx") String sidx,
                                          @Field("sord") String desc,
                                          @Field("imei") String imei,
                                          @Field("dtf") String dateFrom,
                                          @Field("dtt") String dateTo);



}
