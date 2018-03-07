package track.gpschamp.com.gpschamp.ui.fragments.object;

import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.object.FieldObjects;
import track.gpschamp.com.gpschamp.model.object.ObjectResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;
import track.gpschamp.com.gpschamp.ui.adapters.ObjectsAdapter;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;


public class ObjectListFragment extends Fragment {

    public ObjectListFragment() {
        // Required empty public constructor
    }


    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.divider)
    TextView divider;

    PrefsUtil prefsUtil;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_object_list, container, false);
        ButterKnife.bind(this, rootView);

        prefsUtil = new PrefsUtil(getActivity());

        divider.setVisibility(View.INVISIBLE);
        progressBackground.setVisibility(View.VISIBLE);
        finObjects(prefsUtil.getString(Constants.API_TOKEN));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {

                    TabLayout.Tab tab1 = tabLayout.getTabAt(i);
                    TextView textView1 = (TextView) tab1.getCustomView();
                    textView1.setTextColor(Color.parseColor("#9b9b9b"));
                }

                TabLayout.Tab tab = tabLayout.getTabAt(position);
                TextView textView = (TextView) tab.getCustomView();
                textView.setTextColor(Color.parseColor("#0099f1"));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        return rootView;
    }

    public static List<FieldObjects> objects = new ArrayList<>();
    ObjectsAdapter objectsAdapter;

    TextView tv;
    private void finObjects(String token) {

        GpsWebService gpsWebService = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        retrofit2.Call<ObjectResponse> call = gpsWebService.getObjectList(token);

        call.enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ObjectResponse> call, Response<ObjectResponse> response) {

                progressBackground.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    if (response.body().isStatus()) {
                        if (objects.size() > 0)
                            objects.clear();

                        objects = response.body().getObjectsData();

                        try {
                            findAddress();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setupViewPager(viewPager);
                        tabLayout.setupWithViewPager(viewPager);
                        divider.setVisibility(View.VISIBLE);

                        String[] tabText = {"ALL", "MOVING", "IDLE", "OFFLINE"};
                        for (int i = 0; i < tabLayout.getTabCount(); i++) {
                            //noinspection ConstantConditions
                            Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
                            tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tab_textview, null);
                            tv.setTypeface(typeface);
                            tv.setText(tabText[i]);
                            tabLayout.getTabAt(i).setCustomView(tv);

                        }

                        TabLayout.Tab tab1 = tabLayout.getTabAt(0);
                        TextView rTv = (TextView) tab1.getCustomView();
                        rTv.setTextColor(Color.parseColor("#0099f1"));
                        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#0099f1"));
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ObjectResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
            }
        });
    }

    private void findAddress() throws IOException {

        for(int i = 0; i<objects.size(); i++){
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getActivity(), Locale.getDefault());

            addresses = geocoder.getFromLocation(Double.parseDouble(objects.get(i).getLat()), Double.parseDouble(objects.get(i).getLng()), 1);

            String add = "";
            try {
                add = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            }catch (Exception e){
                e.printStackTrace();
            }
            objects.get(i).setAddress(add);

        }


    }

    ObjectsViewPagerAdapter objectsViewPagerAdapter;

    private void setupViewPager(ViewPager viewPager) {
        objectsViewPagerAdapter = new ObjectsViewPagerAdapter(getActivity().getSupportFragmentManager());
        objectsViewPagerAdapter.addFragment(new AllObjectsFragment(), "ALL");
        objectsViewPagerAdapter.addFragment(new RunningFragment(), "MOVING" );
        objectsViewPagerAdapter.addFragment(new IdleFragment(), "IDLE");
        objectsViewPagerAdapter.addFragment(new OfflineFragment(), "OFFLINE");
        viewPager.setAdapter(objectsViewPagerAdapter);
        objectsViewPagerAdapter.notifyDataSetChanged();
    }

    class ObjectsViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ObjectsViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
