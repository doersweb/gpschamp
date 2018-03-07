package track.gpschamp.com.gpschamp.core.register;

import track.gpschamp.com.gpschamp.model.GeneralResponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public interface RegisterContractor {

    interface View{
        void onRegisterResponse(GeneralResponse generalResponse);
    }

    interface Presenter{
        void registerUser(String name, String email, String phone);
        void onRegisterResponse(GeneralResponse generalResponse);
    }

    interface Interactor{
        void registerUser(String name, String email, String phone);
    }
}
