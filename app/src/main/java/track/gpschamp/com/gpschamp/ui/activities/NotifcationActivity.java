package track.gpschamp.com.gpschamp.ui.activities;

import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.GpsChamp;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.database.Notification;
import track.gpschamp.com.gpschamp.ui.adapters.NotificationAdapter;
import track.gpschamp.com.gpschamp.utils.Constants;

public class NotifcationActivity extends AppCompatActivity {

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @BindView(R.id.back_image)
    ImageView backNavigation;

    @BindView(R.id.heading)
    TextView heading;

    @BindView(R.id.notification_count)
    TextView notificationCount;

    @BindView(R.id.fallback)
    TextView fallbackTv;

    @BindView(R.id.list_view)
    ListView listView;

    NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifcation);

        ButterKnife.bind(this);

        Typeface bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);
        Typeface semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        setSupportActionBar(toolbar);
        heading.setText("Notifications");

        fallbackTv.setTypeface(semibold);

        List<Notification> notificationList = GpsChamp.daoSession.getNotificationDao().loadAll();
        if(notificationList.size()>0){
            notificationAdapter = new NotificationAdapter(this, notificationList);
            listView.setAdapter(notificationAdapter);
            fallbackTv.setVisibility(View.GONE);
        }else {
            fallbackTv.setVisibility(View.VISIBLE);
        }
        backNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        heading.setTypeface(bold);
        notificationCount.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
