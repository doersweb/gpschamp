package track.gpschamp.com.gpschamp.core.history;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.model.history.HistoryResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class HistoryInteractor implements HistoryContractor.Interactor{

    HistoryPresenter historyPresenter;
    public HistoryInteractor(HistoryPresenter historyPresenter){
        this.historyPresenter = historyPresenter;
    }

    @Override
    public void fetchHistory(String token, String imei, String dateFrom, String dateTo, String minDuration) {
        GpsWebService service = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        Call<HistoryResponse> call = service.getObjectHistory(token, imei, dateFrom, dateTo, minDuration);

        call.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isStatus()){
                        historyPresenter.onHistoryResponse(response.body());
                    }else {
                        historyPresenter.onHistoryResponse(null);
                    }
                }else {
                    historyPresenter.onHistoryResponse(null);
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                historyPresenter.onHistoryResponse(null);
            }
        });
    }
}
