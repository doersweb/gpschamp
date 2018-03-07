package track.gpschamp.com.gpschamp.core.history;

import track.gpschamp.com.gpschamp.model.history.HistoryResponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class HistoryPresenter implements HistoryContractor.Presenter {

    HistoryContractor.View mView;
    HistoryInteractor historyInteractor;
    public HistoryPresenter(HistoryContractor.View mView){
        this.mView = mView;
        this.historyInteractor = new HistoryInteractor(this);
    }
    @Override
    public void onHistoryResponse(HistoryResponse historyResponse) {
        mView.onHistoryResponse(historyResponse);
    }

    @Override
    public void fetchHistory(String token, String imei, String dateFrom, String dateTo, String minDuration) {
        historyInteractor.fetchHistory(token, imei, dateFrom, dateTo, minDuration);
    }
}
