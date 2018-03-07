package track.gpschamp.com.gpschamp.core.register;

import track.gpschamp.com.gpschamp.model.GeneralResponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class RegisterPresenter implements RegisterContractor.Presenter{

    RegisterContractor.View mView;
    RegisterInteractor registerInteractor;

    public RegisterPresenter(RegisterContractor.View mView){
        this.mView = mView;
        registerInteractor = new RegisterInteractor(this);
    }

    @Override
    public void registerUser(String name, String email, String phone) {
        registerInteractor.registerUser(name, email, phone);
    }

    @Override
    public void onRegisterResponse(GeneralResponse generalResponse) {
        mView.onRegisterResponse(generalResponse);
    }
}
