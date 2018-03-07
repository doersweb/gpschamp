package track.gpschamp.com.gpschamp.fcm;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

/**
 * Created by sudhirharit on 23/02/18.
 */

public class GpsChampFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        PrefsUtil prefsUtil = new PrefsUtil(getApplicationContext());
        prefsUtil.saveString(Constants.FCM_TOKEN, FirebaseInstanceId.getInstance().getToken());


    }
}
