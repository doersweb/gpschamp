package track.gpschamp.com.gpschamp.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.history.HistoryResponse;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class PlaybackActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @BindView(R.id.max_speed)
    TextView maxSpeed;

    @BindView(R.id.stop_duration)
    TextView stopDuration;

    @BindView(R.id.ac)
    TextView ac;

    @BindView(R.id.km)
    TextView km;

    @BindView(R.id.time)
    TextView timeTv;

    @BindView(R.id.play)
    ImageView playImg;

    boolean isPlay = false;

    @BindView(R.id.heading)
    TextView heading;

    @BindView(R.id.back_image)
    ImageView backImg;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.tv2)
    TextView tv2;

    @BindView(R.id.tv3)
    TextView tv3;

    @BindView(R.id.tv4)
    TextView tv4;

    @BindView(R.id.tv5)
    TextView tv5;

    @BindView(R.id.to_tv)
    TextView toTv;

    @BindView(R.id.from_tv)
    TextView fromTv;

    @BindView(R.id.from_address)
    TextView fromAddress;

    @BindView(R.id.to_address)
    TextView toAddress;

    Bitmap mMarkerIcon;

    @BindView(R.id.notification_count)
    TextView notificationCount;

    HistoryResponse historyResponse;
    PrefsUtil prefsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Typeface custom_bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);
        Typeface regular = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
        Typeface semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        toTv.setTypeface(semibold);
        fromTv.setTypeface(semibold);
        toAddress.setTypeface(regular);
        fromAddress.setTypeface(regular);

        heading.setTypeface(custom_bold);
        heading.setText("Playback");

        tv1.setTypeface(semibold);
        tv2.setTypeface(semibold);
        tv3.setTypeface(semibold);
        tv4.setTypeface(semibold);
        tv5.setTypeface(semibold);

        timeTv.setTypeface(regular);
        ac.setTypeface(regular);
        stopDuration.setTypeface(regular);
        maxSpeed.setTypeface(regular);
        km.setTypeface(regular);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        historyResponse = PlayBackFormAcitivity.response;
        maxSpeed.setText(historyResponse.getData().getTopSpeed() + "Km/h");
        ac.setText("AC on");
        stopDuration.setText(historyResponse.getData().getStopDuration());
        km.setText(historyResponse.getData().getRouteLength() + " Km");

        for (int i = 0; i < historyResponse.getData().getHistoryObjects().size(); i++) {
            mPathPolygonPoints.add(new LatLng(Double.parseDouble(historyResponse.getData().getHistoryObjects().get(i).getLatitude()), Double.parseDouble(historyResponse.getData().getHistoryObjects().get(i).getLogitude())));
        }

        try {
            findAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }


        prefsUtil = new PrefsUtil(this);

        notificationCount.setTypeface(semibold);
        notificationCount.setText(prefsUtil.getInt(Constants.NOTIFCIATION_COUNT)+"");
        notificationCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaybackActivity.this, NotifcationActivity.class);
                startActivity(intent);
            }
        });

        mMarkerIcon = BitmapFactory.decodeResource(getResources(), R.drawable.unnamed);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.notification_rel)
    void onNotificaitonClick(){
        Intent intent = new Intent(PlaybackActivity.this, NotifcationActivity.class);
        startActivity(intent);
    }

    private void findAddress() throws IOException {
        Geocoder geocoder, geocoder1;
        List<Address> addresses, addresses1;
        geocoder = new Geocoder(this, Locale.getDefault());
        geocoder1 = new Geocoder(this, Locale.getDefault());

        int x = historyResponse.getData().getHistoryObjects().size();
        Log.d("alok", x+" history size----"+historyResponse.getData().getHistoryObjects().get(0).getLatitude()+" --- "+historyResponse.getData().getHistoryObjects().get(0).getLogitude());
        Log.d("alok", x+" history size----"+historyResponse.getData().getHistoryObjects().get(x-1).getLatitude()+" --- "+historyResponse.getData().getHistoryObjects().get(x-1).getLogitude());
        addresses = geocoder.getFromLocation(Double.parseDouble(historyResponse.getData().getHistoryObjects().get(0).getLatitude()), Double.parseDouble(historyResponse.getData().getHistoryObjects().get(0).getLogitude()), 1);
        addresses1 = geocoder1.getFromLocation(Double.parseDouble(historyResponse.getData().getHistoryObjects().get(x-1).getLatitude()), Double.parseDouble(historyResponse.getData().getHistoryObjects().get(x-1).getLogitude()), 1);
        String add = "";
        String add1 = "";
        try {
            add = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            add1 = addresses1.get(0).getAddressLine(0);
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.d("alok", add1);
        Log.d("alok", add);
        fromAddress.setText(add);
        toAddress.setText(add1);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.play)
    void onPlayClick() {
        if (!isPlay) {
            isPlay = true;
            if (mIndexCurrentPoint == 0) {
                animateCarMove(mCarMarker, mPathPolygonPoints.get(0), mPathPolygonPoints.get(1), MOVE_ANIMATION_DURATION);
            } else {
                animateCarMove(mCarMarker, saveFirstLtln, saveSecondLtLn, MOVE_ANIMATION_DURATION);
            }

            playImg.setImageResource(R.drawable.ic_pause);
        } else {
            isPlay = false;
            carTurnHandler.removeCallbacksAndMessages(null);
            carMoveHandler.removeCallbacksAndMessages(null);
            playImg.setImageResource(R.drawable.ic_play);
        }
    }

    List<LatLng> mPathPolygonPoints = new ArrayList<>();
    Marker mCarMarker;

    GoogleMap gmap;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gmap = googleMap;
        if (mPathPolygonPoints.size() > 0) {
            for (int i = 0; i < mPathPolygonPoints.size() - 1; i++) {
                LatLng src = mPathPolygonPoints.get(i);
                LatLng dest = mPathPolygonPoints.get(i + 1);
                Polyline line = googleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude),
                                new LatLng(dest.latitude, dest.longitude))
                        .width(10).color(Color.RED).geodesic(true));

            }

//            LatLngBounds.Builder builder = new LatLngBounds.Builder();
//            builder.include(latLngList.get(0));
//            builder.include(latLngList.get(latLngList.size() - 1));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 48));
//            googleMap.animateCamera(CameraUpdateFactory.zoomTo(7), 1000, null);
//            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.car_icon);
            mCarMarker = googleMap.addMarker(new MarkerOptions()
                    .position(mPathPolygonPoints.get(0))
                    .title("Curr")
                    .snippet("Move"));
//            mCarMarker.setIcon(icon);
//        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
//            .addAll(latLngList)
//            .width(5)
//            .color(Color.RED));

            LatLng coordinate = new LatLng(mPathPolygonPoints.get(0).latitude, mPathPolygonPoints.get(0).longitude); //Store these lat lng values somewhere. These should be constant.
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                    coordinate, 16);
            googleMap.animateCamera(location);


//            googleMap.moveCamera(CameraUpdateFactory.);
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLngList.get(0).latitude, latLngList.get(0).latitude), 10.0f));

        }
    }

    LatLng saveFirstLtln, saveSecondLtLn;
    Handler carMoveHandler, carTurnHandler;

    private void animateCarMove(final Marker marker, final LatLng beginLatLng, final LatLng endLatLng, final long duration) {

        saveFirstLtln = beginLatLng;
        saveSecondLtLn = endLatLng;
        carMoveHandler = new Handler();

        final long startTime = SystemClock.uptimeMillis();

        final Interpolator interpolator = new LinearInterpolator();

        // set car bearing for current part of path
        float angleDeg = (float) (180 * getAngle(beginLatLng, endLatLng) / Math.PI);
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        matrix.postRotate(angleDeg);

        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.unnamed));
//        marker.setIcon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(mMarkerIcon, 0, 0, mMarkerIcon.getWidth(), mMarkerIcon.getHeight(), matrix, true)));
//        marker.setIcon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(mMarkerIcon)));
        carMoveHandler.post(new Runnable() {
            @Override
            public void run() {
                // calculate phase of animation
                long elapsed = SystemClock.uptimeMillis() - startTime;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                // calculate new position for marker
                double lat = (endLatLng.latitude - beginLatLng.latitude) * t + beginLatLng.latitude;
                double lngDelta = endLatLng.longitude - beginLatLng.longitude;

                if (Math.abs(lngDelta) > 180) {
                    lngDelta -= Math.signum(lngDelta) * 360;
                }
                double lng = lngDelta * t + beginLatLng.longitude;

                marker.setPosition(new LatLng(lat, lng));

                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                        new LatLng(lat, lng), 16);
                gmap.animateCamera(location);
                // if not end of line segment of path
                if (t < 1.0) {
                    // call next marker position
                    carMoveHandler.postDelayed(this, 1);
                } else {
                    // call turn animation
                    nextTurnAnimation();
                }
            }
        });
    }

    int mIndexCurrentPoint = 0;

    private void nextTurnAnimation() {
        mIndexCurrentPoint++;

        if (mIndexCurrentPoint < mPathPolygonPoints.size() - 1) {
            LatLng prevLatLng = mPathPolygonPoints.get(mIndexCurrentPoint - 1);
            LatLng currLatLng = mPathPolygonPoints.get(mIndexCurrentPoint);
            LatLng nextLatLng = mPathPolygonPoints.get(mIndexCurrentPoint + 1);

            float beginAngle = (float) (180 * getAngle(prevLatLng, currLatLng) / Math.PI);
            float endAngle = (float) (180 * getAngle(currLatLng, nextLatLng) / Math.PI);

            animateCarTurn(mCarMarker, beginAngle, endAngle, TURN_ANIMATION_DURATION);
        }
    }

    public final int MOVE_ANIMATION_DURATION = 1000;
    public final int TURN_ANIMATION_DURATION = 1000;

    private void animateCarTurn(final Marker marker, final float startAngle, final float endAngle, final long duration) {

        carTurnHandler = new Handler();
        final long startTime = SystemClock.uptimeMillis();
        final Interpolator interpolator = new LinearInterpolator();

        final float dAndgle = endAngle - startAngle;

        android.graphics.Matrix matrix = new android.graphics.Matrix();
        matrix.postRotate(startAngle);
//        Bitmap rotatedBitmap = Bitmap.createBitmap(mMarkerIcon, 0, 0, mMarkerIcon.getWidth(), mMarkerIcon.getHeight(), matrix, true);
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.unnamed));

        carTurnHandler.post(new Runnable() {
            @Override
            public void run() {

                long elapsed = SystemClock.uptimeMillis() - startTime;
                float t = interpolator.getInterpolation((float) elapsed / duration);

                android.graphics.Matrix m = new android.graphics.Matrix();
                m.postRotate(startAngle + dAndgle * t);
//                marker.setIcon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(mMarkerIcon, 0, 0, mMarkerIcon.getWidth(), mMarkerIcon.getHeight(), m, true)));
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.unnamed));
                if (t < 1.0) {
                    carTurnHandler.postDelayed(this, 16);
                } else {
                    nextMoveAnimation();
                }
            }
        });
    }

    private void nextMoveAnimation() {
        if (mIndexCurrentPoint < mPathPolygonPoints.size() - 1) {
            animateCarMove(mCarMarker, mPathPolygonPoints.get(mIndexCurrentPoint), mPathPolygonPoints.get(mIndexCurrentPoint + 1), MOVE_ANIMATION_DURATION);
        }
    }

    private double getAngle(LatLng beginLatLng, LatLng endLatLng) {
        double f1 = Math.PI * beginLatLng.latitude / 180;
        double f2 = Math.PI * endLatLng.latitude / 180;
        double dl = Math.PI * (endLatLng.longitude - beginLatLng.longitude) / 180;
        return Math.atan2(Math.sin(dl) * Math.cos(f2), Math.cos(f1) * Math.sin(f2) - Math.sin(f1) * Math.cos(f2) * Math.cos(dl));
    }
}
