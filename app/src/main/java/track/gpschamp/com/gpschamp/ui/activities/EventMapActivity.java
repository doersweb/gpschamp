package track.gpschamp.com.gpschamp.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.nio.file.attribute.FileAttribute;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.singlevent.SingleEventResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class EventMapActivity extends AppCompatActivity {


    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @BindView(R.id.heading)
    TextView headingTv;

    @BindView(R.id.back_image)
    ImageView backImage;

    @BindView(R.id.notification_count)
    TextView notificationCount;

    PrefsUtil prefsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_map);

        ButterKnife.bind(this);


        Typeface bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);

        headingTv.setTypeface(bold);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);

        headingTv.setText("#" + getIntent().getStringExtra("id") + " - " + getIntent().getStringExtra("event_name"));

        prefsUtil = new PrefsUtil(this);
        Typeface semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
        notificationCount.setTypeface(semibold);
        notificationCount.setText(prefsUtil.getInt(Constants.NOTIFCIATION_COUNT)+"");

        progressBackground.setVisibility(View.VISIBLE);

        fetchEventsData();

    }

    @OnClick(R.id.notification_rel)
    void onNotificaitonClick(){
        Intent intent = new Intent(EventMapActivity.this, NotifcationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void fetchEventsData() {

        GpsWebService webService = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        retrofit2.Call<SingleEventResponse> call = webService.getSingleEventDetails(prefsUtil.getString(Constants.API_TOKEN), getIntent().getStringExtra("id"));


        call.enqueue(new Callback<SingleEventResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SingleEventResponse> call, final Response<SingleEventResponse> response) {
                progressBackground.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(response.body().getSingleEventObject().getLatitude()), Double.parseDouble(response.body().getSingleEventObject().getLongitude()))).title(
                                    "Object : " + response.body().getSingleEventObject().getName() + "\n" + "Event :" + response.body().getSingleEventObject().getEventDescription() + "\n" +
                                            "Speed :" + response.body().getSingleEventObject().getSpeed()
                            ));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(response.body().getSingleEventObject().getLatitude()), Double.parseDouble(response.body().getSingleEventObject().getLongitude())), 16.0f));
                        }
                    });
                } else {

                }
            }

            @Override
            public void onFailure(retrofit2.Call<SingleEventResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
                Toast.makeText(EventMapActivity.this, "Couldn't fetch event details, please try again!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
