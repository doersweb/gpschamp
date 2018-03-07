package track.gpschamp.com.gpschamp.ui.fragments.fuel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import track.gpschamp.com.gpschamp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FuelDataFragment extends Fragment {


    public FuelDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fuel_data, container, false);
    }

}
