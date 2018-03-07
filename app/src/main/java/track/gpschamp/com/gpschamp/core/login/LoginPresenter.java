package track.gpschamp.com.gpschamp.core.login;

import track.gpschamp.com.gpschamp.model.login.LoginResponse;

/**
 * Created by sudhirharit on 23/01/18.
 */

public class LoginPresenter implements LoginContractor.Presenter{

    LoginInteractor mInteractor;
    LoginContractor.View mView;

    public LoginPresenter(LoginContractor.View mView) {
        this.mInteractor = new LoginInteractor(this);
        this.mView = mView;
    }

    @Override
    public void loginUser(String userName, String password, String macId) {
        mInteractor.loginUser(userName, password, macId);
    }

    @Override
    public void loginResponse(LoginResponse loginResponse) {
        mView.onLoginResponse(loginResponse);
    }
}
