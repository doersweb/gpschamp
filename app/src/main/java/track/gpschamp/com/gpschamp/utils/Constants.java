package track.gpschamp.com.gpschamp.utils;

import java.security.PublicKey;

/**
 * Created by sudhirharit on 23/01/18.
 */

public class Constants {

    public static final String BASE_URL = "http://track.gpschamp.com/";

    public static final String FCM_TOKEN = "fcmToken";

    public static final String IS_LOGGED_IN = "isLogin";
    public static final String API_TOKEN = "token";


    public static final int SUCCESS_RESULT = 0;

    public static final int FAILURE_RESULT = 1;

    private static final String PACKAGE_NAME =
            "track.gpschamp.com.gpschamp";

    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";

    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";

    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

    public static final String PATH_CUSTOM_FONT_REGULAR = "fonts/segoeui.ttf";
    public static final String PATH_CUSTOM_FONT_BOLD = "fonts/segoiuibold.ttf";
    public static final String PATH_CUSTOM_FONT_SEMI_BOLD = "fonts/seguisb.ttf";

    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";

    public static final String NOTIFCIATION_COUNT = "notification_count";
}
