package track.gpschamp.com.gpschamp.core.forgot;

import track.gpschamp.com.gpschamp.model.GeneralResponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public interface ForgotContractor {

    interface View{
        void onForgotPasswordResponse(GeneralResponse generalResponse);
    }

    interface Presenter{
        void onForgotPasswordResponse(GeneralResponse generalResponse);
        void forgotPassword(String name, String email, String phone);
    }

    interface Interactor{
        void forgotPassword(String name, String email, String phone);
    }
}
