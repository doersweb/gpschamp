package track.gpschamp.com.gpschamp.core.history;

import track.gpschamp.com.gpschamp.model.history.HistoryResponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public interface HistoryContractor {

    interface View{
        void onHistoryResponse(HistoryResponse historyResponse);
    }

    interface Presenter{
        void onHistoryResponse(HistoryResponse historyResponse);
        void fetchHistory(String token, String imei, String dateFrom, String dateTo, String minDuration);
    }

    interface Interactor{
        void fetchHistory(String token, String imei, String dateFrom, String dateTo, String minDuration);
    }
}
