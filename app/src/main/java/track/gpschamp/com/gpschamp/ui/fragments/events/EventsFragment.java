package track.gpschamp.com.gpschamp.ui.fragments.events;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.core.events.EventsContractor;
import track.gpschamp.com.gpschamp.core.events.EventsPresenter;
import track.gpschamp.com.gpschamp.model.events.EventRow;
import track.gpschamp.com.gpschamp.model.events.EventsReponse;
import track.gpschamp.com.gpschamp.ui.activities.EventMapActivity;
import track.gpschamp.com.gpschamp.ui.adapters.EventsAdapter;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment implements EventsContractor.View , EventsAdapter.Communicator{


    public EventsFragment() {
        // Required empty public constructor
    }


    @BindView(R.id.event_list_view)
    ListView eventListView;

    EventsPresenter eventsPresenter;
    PrefsUtil prefsUtil;
    int page = 1;

    Typeface semibold;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, rootView);
        prefsUtil = new PrefsUtil(getActivity());

        semibold = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        eventsPresenter = new EventsPresenter(this);
        eventsPresenter.fetchEventsList(prefsUtil.getString(Constants.API_TOKEN), "", page, 10, "dt_tracker");

        return rootView;
    }

    List<EventRow> eventRowList = new ArrayList<>();
    EventsAdapter eventsAdapter;
    @Override
    public void onEventsResponse(EventsReponse eventsReponse) {
        if (eventsReponse != null) {
            if(eventRowList.size()>0){
                eventRowList.addAll(eventsReponse.getEventsObjectList().getEventRows());
                eventsAdapter.notifyDataSetChanged();

            }else {
                eventRowList = eventsReponse.getEventsObjectList().getEventRows();
                eventsAdapter = new EventsAdapter(getActivity(), eventsReponse.getEventsObjectList().getEventRows(), this);
                eventListView.setAdapter(eventsAdapter);
            }
        } else {
            Toast.makeText(getActivity(), "Some problem occured, please try again!", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void loadMore() {
        page = page+1;
        eventsPresenter.fetchEventsList(prefsUtil.getString(Constants.API_TOKEN), "", page, 25, "dt_tracker");
    }
}
