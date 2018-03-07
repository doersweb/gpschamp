package track.gpschamp.com.gpschamp.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.GeneralResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;
import track.gpschamp.com.gpschamp.ui.adapters.DrawerListAdapter;
import track.gpschamp.com.gpschamp.ui.fragments.profile.Profile;
import track.gpschamp.com.gpschamp.ui.fragments.reports.Reports;
import track.gpschamp.com.gpschamp.ui.fragments.command.CommandFragment;
import track.gpschamp.com.gpschamp.ui.fragments.events.EventsFragment;
import track.gpschamp.com.gpschamp.ui.fragments.fuel.FuelDataFragment;
import track.gpschamp.com.gpschamp.ui.fragments.graphs.GraphFragment;
import track.gpschamp.com.gpschamp.ui.fragments.images.ImagesFragment;
import track.gpschamp.com.gpschamp.ui.fragments.map.AllObjectMap;
import track.gpschamp.com.gpschamp.ui.fragments.object.ObjectListFragment;
import track.gpschamp.com.gpschamp.ui.fragments.settings.SettingsFragment;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.app_bar)
    Toolbar appBar;

    @BindView(R.id.heading)
    TextView headingTv;

    @BindView(R.id.listMain)
    ListView mainListView;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.notification_count)
    TextView notificationCount;

    String[] drawerItems = {"Objects", "Events", "Playback", "Commands", "Reports", "Images", "Settings", "Profile", "Logout"};
    int[] drawerImages = {R.drawable.ic_menu_object, R.drawable.ic_menu_event, R.drawable.ic_menu_palyback, R.drawable.ic_menu_setting, R.drawable.ic_menu_reports, R.drawable.ic_menu_images, R.drawable.ic_menu_setting, R.drawable.ic_menu_profile, R.drawable.ic_menu_log_out};
    DrawerListAdapter mDrawerAdapter;

    private static final String TAG_HOME = "GPS Trackers List";
    private static final String TAG_MAP = "OBJECTS";
    private static final String TAG_EVENTS = "EVENTS";
    private static final String TAG_PLAYBACK = "PLAYBACK";
    private static final String TAG_COMMANDS = "COMMANDS";
    private static final String TAG_REPORTS = "REPORTS";
    private static final String TAG_FUEL = "FUEL DATA";
    private static final String TAG_IMAGES = "IMAGES";
    private static final String TAG_PROFILE = "MY PROFILE";
    private static final String TAG_SETTINGS = "SETTINGS";
    private static final String TAG_LOGOUT = "LOGOUT";


    public static String CURRENT_TAG = TAG_HOME;

    @BindView(R.id.acronym)
    TextView acronym;

    PrefsUtil prefsUtil;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);
        Typeface regular = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
        Typeface semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        headingTv.setTypeface(bold);


        prefsUtil = new PrefsUtil(this);

        acronym.setTypeface(semibold);
        notificationCount.setTypeface(semibold);

        notificationCount.setText(prefsUtil.getInt(Constants.NOTIFCIATION_COUNT)+"");



        name.setTypeface(semibold);
        email.setTypeface(regular);

        mDrawerAdapter = new DrawerListAdapter(this, drawerItems, drawerImages);
        mainListView.setAdapter(mDrawerAdapter);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, appBar, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        headingTv.setText(TAG_HOME);
        CURRENT_TAG = TAG_HOME;
        addingFragment(new ObjectListFragment());

    }

    @OnClick(R.id.notification_rel)
    void onNotificaitonClick(){
        Intent intent = new Intent(MainActivity.this, NotifcationActivity.class);
        startActivity(intent);
    }

    private void addingFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
        drawerLayout.closeDrawers();

    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("Notification")
        );
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onStop();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            notificationCount.setText(prefsUtil.getInt(Constants.NOTIFCIATION_COUNT)+"");
        }
    };


    @OnClick(R.id.hamburger)
    public void onHamburgerClick() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @OnItemClick(R.id.listMain)
    public void onListItemClick(int position) {

        switch (position) {
            case 0:
                if (CURRENT_TAG.equals(TAG_HOME)) {
                    drawerLayout.closeDrawers();
                } else {
                    headingTv.setText(TAG_HOME);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    CURRENT_TAG = TAG_HOME;
                    addingFragment(new ObjectListFragment());
                }

                break;


            case 1:
                if (CURRENT_TAG.equals(TAG_EVENTS)) {
                    drawerLayout.closeDrawers();
                } else {
                    headingTv.setText(TAG_EVENTS);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    CURRENT_TAG = TAG_EVENTS;
                    addingFragment(new EventsFragment());
                }

                break;

            case 2:
                if (CURRENT_TAG.equals(TAG_PLAYBACK)) {
                    drawerLayout.closeDrawers();
                } else {
                    Intent intent = new Intent(this, PlayBackFormAcitivity.class);
                    startActivity(intent);
                }

                break;

            case 3:
                if (CURRENT_TAG.equals(TAG_COMMANDS)) {
                    drawerLayout.closeDrawers();
                } else {
                    headingTv.setText(TAG_COMMANDS);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    CURRENT_TAG = TAG_COMMANDS;
                    addingFragment(new CommandFragment());
                }


                break;

            case 4:
                if (CURRENT_TAG.equals(TAG_REPORTS)) {
                    drawerLayout.closeDrawers();
                } else {
                    headingTv.setText(TAG_REPORTS);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    CURRENT_TAG = TAG_REPORTS;
                    addingFragment(new Reports());
                }

                break;

            case 5:
                if (CURRENT_TAG.equals(TAG_IMAGES)) {
                    drawerLayout.closeDrawers();
                } else {
                    headingTv.setText(TAG_IMAGES);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    CURRENT_TAG = TAG_IMAGES;
                    addingFragment(new ImagesFragment());
                }
                break;

            case 6:
                if (CURRENT_TAG.equals(TAG_SETTINGS)) {
                    drawerLayout.closeDrawers();
                } else {
                    headingTv.setText(TAG_SETTINGS);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    CURRENT_TAG = TAG_SETTINGS;
                    addingFragment(new SettingsFragment());
                }
                break;


            case 7:
                if (CURRENT_TAG.equals(TAG_PROFILE)) {
                    drawerLayout.closeDrawers();
                } else {
                    headingTv.setText(TAG_PROFILE);
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_TAG);
                    if (fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    CURRENT_TAG = TAG_PROFILE;
                    addingFragment(new Profile());
                }
                break;

            case 8:
                drawerLayout.closeDrawers();
                logoutUser();
                break;
        }
    }

    private void logoutUser() {
        progressBackground.setVisibility(View.VISIBLE);
        GpsWebService webService = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        Call<GeneralResponse> call = webService.logoutUser(new PrefsUtil(this).getString(Constants.API_TOKEN));

        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressBackground.setVisibility(View.GONE);
                new PrefsUtil(MainActivity.this).saveBoolean(Constants.IS_LOGGED_IN, false);
                new PrefsUtil(MainActivity.this).saveInt(Constants.NOTIFCIATION_COUNT, 0);
                Intent intent = new Intent(MainActivity.this, SingupActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
            }
        });
    }
}
