package track.gpschamp.com.gpschamp.core.login;

import track.gpschamp.com.gpschamp.model.login.LoginResponse;

/**
 * Created by sudhirharit on 23/01/18.
 */

public interface LoginContractor {

    interface View{
        void onLoginResponse(LoginResponse loginResponse);
    }

    interface Presenter{
        void loginUser(String userName, String password, String macId);
        void loginResponse(LoginResponse loginResponse);
    }

    interface Interactor{
        void loginUser(String userName, String password, String macId);
    }
}
