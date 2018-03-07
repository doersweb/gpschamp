package track.gpschamp.com.gpschamp.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class SingleObjectActivity extends AppCompatActivity implements OnMapReadyCallback {


    @BindView(R.id.app_bar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.speed)
    TextView speed;

    @BindView(R.id.ignition)
    TextView ignitionTv;

    @BindView(R.id.ac)
    TextView acTv;

    @BindView(R.id.duration)
    TextView durationTv;

    @BindView(R.id.immobilize)
    TextView immobilizeTv;

    @BindView(R.id.playback)
    TextView playbackTv;

    @BindView(R.id.odomoter)
    TextView odometerTv;

    @BindView(R.id.reports)
    TextView reportsTv;

    Typeface semibold, bold;

    @BindView(R.id.heading)
    TextView headingTv;

    @BindView(R.id.back_image)
    ImageView backImageView;

    @BindView(R.id.share)
    ImageView share;

    @BindView(R.id.direct)
    ImageView direction;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.notification_count)
    TextView notificationCount;

    double lat = 0.0, lng = 0.0;
    PrefsUtil prefsUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_object);

        ButterKnife.bind(this);


        semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
        bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);

        prefsUtil = new PrefsUtil(this);
        headingTv.setTypeface(bold);
        speed.setTypeface(semibold);
        ignitionTv.setTypeface(semibold);
        durationTv.setTypeface(semibold);
        acTv.setTypeface(semibold);
        immobilizeTv.setTypeface(semibold);
        playbackTv.setTypeface(semibold);
        odometerTv.setTypeface(semibold);
        reportsTv.setTypeface(semibold);
        address.setTypeface(semibold);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setSupportActionBar(toolbar);

        headingTv.setText(getIntent().getStringExtra("name"));

        speed.setText(getIntent().getStringExtra("speed") + " km/h");

        lat = Double.parseDouble(getIntent().getStringExtra("lat"));
        lng = Double.parseDouble(getIntent().getStringExtra("lng"));

        try {
            findAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }

        notificationCount.setTypeface(semibold);
        notificationCount.setText(prefsUtil.getInt(Constants.NOTIFCIATION_COUNT)+"");

        notificationCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleObjectActivity.this, NotifcationActivity.class);
                startActivity(intent);
            }
        });

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.notification_rel)
    void onNotificaitonClick(){
        Intent intent = new Intent(SingleObjectActivity.this, NotifcationActivity.class);
        startActivity(intent);
    }

    private void findAddress() throws IOException {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        addresses = geocoder.getFromLocation(lat, lng, 1);

        String add = "";
        try {
            add = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        } catch (Exception e) {
            e.printStackTrace();
        }

        address.setText(add);

    }

    @OnClick(R.id.direct)
    void onDirectionClick() {
        String uri = "geo:" + lat + ","
                + lng + "?q=" + lat
                + "," + lng;
        startActivity(new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(uri)));
    }

    @OnClick(R.id.share)
    void onShareClick() {
        Intent intent1 = new Intent(Intent.ACTION_SEND);
        intent1.setType("text/plain");
        intent1.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("name"));
        startActivity(Intent.createChooser(intent1, "Select prefered Service"));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

//        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
//                .clickable(true)
//                .add(
//                        new LatLng(-35.016, 143.321),
//                        new LatLng(-34.747, 145.592),
//                        new LatLng(-34.364, 147.891),
//                        new LatLng(-33.501, 150.217),
//                        new LatLng(-32.306, 149.248),
//                        new LatLng(-32.491, 147.309)));
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(getIntent().getStringExtra("imei")));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 16.0f));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
