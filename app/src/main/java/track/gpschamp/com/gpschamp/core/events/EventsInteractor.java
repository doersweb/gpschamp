package track.gpschamp.com.gpschamp.core.events;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.model.events.EventsReponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class EventsInteractor implements EventsContractor.Interactor {

    EventsPresenter eventsPresenter;
    public EventsInteractor(EventsPresenter eventsPresenter){
        this.eventsPresenter = eventsPresenter;
    }

    @Override
    public void fetchEventsList(String token, String s, int page, int rows, String filter) {
        GpsWebService service = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        Call<EventsReponse> call = service.getEventsList(token, s, page, rows, filter);

        call.enqueue(new Callback<EventsReponse>() {
            @Override
            public void onResponse(Call<EventsReponse> call, Response<EventsReponse> response) {
                if(response.isSuccessful()){
                    eventsPresenter.onEventsResponse(response.body());
                }else {
                    eventsPresenter.onEventsResponse(null);
                }
            }

            @Override
            public void onFailure(Call<EventsReponse> call, Throwable t) {
                    eventsPresenter.onEventsResponse(null);
            }
        });
    }
}
