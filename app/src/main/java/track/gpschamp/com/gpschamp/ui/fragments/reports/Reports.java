package track.gpschamp.com.gpschamp.ui.fragments.reports;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.ui.activities.PlayBackFormAcitivity;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reports extends Fragment {


    public Reports() {
        // Required empty public constructor
    }

    @BindView(R.id.history_layout)
    RelativeLayout historyLayout;

    @BindView(R.id.playback_layout)
    RelativeLayout playbackLayout;

    @BindView(R.id.ac_report_layout)
    RelativeLayout acReportLayout;

    @BindView(R.id.fuel_report)
    RelativeLayout fuelReportLayout;

    @BindView(R.id.driver_behaviour)
    RelativeLayout driverBehaviour;

    @BindView(R.id.graphics_report)
    RelativeLayout graphicsLayout;

    @BindView(R.id.history)
    TextView history;

    @BindView(R.id.playback)
    TextView playback;

    @BindView(R.id.ac)
    TextView ac;

    @BindView(R.id.fuel)
    TextView fuel;

    @BindView(R.id.driver)
    TextView driver;

    @BindView(R.id.graph)
    TextView graph;


    Typeface semibold;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_acreports, container, false);
        ButterKnife.bind(this, rootView);

        semibold = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        history.setTypeface(semibold);
        playback.setTypeface(semibold);
        ac.setTypeface(semibold);
        fuel.setTypeface(semibold);
        driver.setTypeface(semibold);
        graph.setTypeface(semibold);


        return rootView;
    }


    @OnClick(R.id.ac_report_layout)
    void acClick(){
        Toast.makeText(getActivity(), "This feature will be available soon", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.history_layout)
    void historyClick(){
        Toast.makeText(getActivity(), "This feature will be available soon", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.playback_layout)
    void playbackClick(){
        Intent intent = new Intent(getActivity(), PlayBackFormAcitivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.ac_report_layout)
    void fuelClick(){
        Toast.makeText(getActivity(), "This feature will be available soon", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.driver_behaviour)
    void driverClick(){
        Toast.makeText(getActivity(), "This feature will be available soon", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.graphics_report)
    void graphClick(){
        Toast.makeText(getActivity(), "This feature will be available soon", Toast.LENGTH_SHORT).show();
    }
}
