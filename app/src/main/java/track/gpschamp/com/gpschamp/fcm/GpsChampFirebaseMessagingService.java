package track.gpschamp.com.gpschamp.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import track.GpsChamp;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.database.Notification;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.ui.activities.MainActivity;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

/**
 * Created by sudhirharit on 23/02/18.
 */

public class GpsChampFirebaseMessagingService extends FirebaseMessagingService {

    LocalBroadcastManager localBroadcastManager;

    @Override
    public void onCreate() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> dataMap = remoteMessage.getData();

        String eventId = dataMap.get("event_id");
        String url = dataMap.get("event_image");
        String title = dataMap.get("title");
        String body = dataMap.get("body");


        Notification notification = new Notification(eventId, title, body);
        GpsChamp.daoSession.getNotificationDao().insert(notification);

        PrefsUtil prefsUtil = new PrefsUtil(getApplicationContext());
        prefsUtil.saveInt(Constants.NOTIFCIATION_COUNT, prefsUtil.getInt(Constants.NOTIFCIATION_COUNT, 0)+1);


        Intent intent = new Intent("Notification");
        intent.putExtra("count", prefsUtil.getInt(Constants.NOTIFCIATION_COUNT));
        localBroadcastManager.sendBroadcast(intent);
//        Log.d("alok", eventId);

        {

//            PrefsUtil prefsUtil = new PrefsUtil(getApplicationContext());
//            int notiCount = prefsUtil.getInt(Constants.NOTIFICATION_COUNT);
//            notiCount = notiCount+1;
//            prefsUtil.saveInt(Constants.NOTIFICATION_COUNT,notiCount);
//
//
//            Intent intent = new Intent("Notification");
//            intent.putExtra("count", notiCount);
//            localBroadcastManager.sendBroadcast(intent);


//
//            Map<String, String> dataMap = remoteMessage.getData();
//            String body = dataMap.get("body");
////            String msgType = dataMap.get("msgtype");
//            String title = dataMap.get("title");
//
////            if(msgType.contains("meeting")){
////                Intent meetingIntent = new Intent("meeting");
////                localBroadcastManager.sendBroadcast(meetingIntent);
////            }else if(msgType.contains("unfriend")){
////                Intent intent1 = new Intent("unfriend");
////                localBroadcastManager.sendBroadcast(intent1);
////            }

            Intent resultIntent = new Intent(this, MainActivity.class);




            // Because clicking the notification opens a new ("special") activity, there's
            // no need to create an artificial back stack.

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            // Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(MainActivity.class);
            // Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent contentIntent = stackBuilder
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT
                            | PendingIntent.FLAG_ONE_SHOT);


            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.logo_gps_champ)
                            .setContentTitle(title)
                            .setContentText(body)
                            .setContentIntent(contentIntent)
                            .setAutoCancel(true);

            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(10, mBuilder.build());

//                HomeActivity homeActivity = new HomeActivity();
//                homeActivity.notification();

        }
    }
}
