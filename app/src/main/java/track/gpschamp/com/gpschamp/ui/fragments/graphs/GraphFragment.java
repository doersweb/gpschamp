package track.gpschamp.com.gpschamp.ui.fragments.graphs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import track.gpschamp.com.gpschamp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {


    public GraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

}
