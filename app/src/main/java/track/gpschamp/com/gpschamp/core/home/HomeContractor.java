package track.gpschamp.com.gpschamp.core.home;

/**
 * Created by sudhirharit on 04/02/18.
 */

public interface HomeContractor {

    interface View{
        void objectListResponse();
    }

    interface Presenter{
        void objectListResponse();
        void objectListRequest();
    }

    interface Interactor{
        void objectListRequest();
    }
}
