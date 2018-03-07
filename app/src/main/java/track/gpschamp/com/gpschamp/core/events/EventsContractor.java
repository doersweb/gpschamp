package track.gpschamp.com.gpschamp.core.events;

import track.gpschamp.com.gpschamp.model.events.EventsReponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public interface EventsContractor {


    interface View{
        void onEventsResponse(EventsReponse eventsReponse);
    }

    interface Presenter{
        void onEventsResponse(EventsReponse eventsReponse);
        void fetchEventsList(String token, String s, int page, int rows, String filter);
    }

    interface Interactor{
        void fetchEventsList(String token, String s, int page, int rows, String filter);
    }
}
