package track.gpschamp.com.gpschamp.core.events;

import track.gpschamp.com.gpschamp.model.events.EventsReponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class EventsPresenter implements EventsContractor.Presenter {

    EventsContractor.View view;
    EventsInteractor eventsInteractor;
    public EventsPresenter(EventsContractor.View view){
        this.view = view;
        eventsInteractor = new EventsInteractor(this);
    }

    @Override
    public void onEventsResponse(EventsReponse eventsReponse) {
        view.onEventsResponse(eventsReponse);
    }

    @Override
    public void fetchEventsList(String token, String s, int page, int rows, String filter) {
        eventsInteractor.fetchEventsList(token, s, page, rows, filter);
    }
}
