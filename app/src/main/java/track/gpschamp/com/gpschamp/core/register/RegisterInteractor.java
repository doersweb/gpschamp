package track.gpschamp.com.gpschamp.core.register;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class RegisterInteractor implements RegisterContractor.Interactor{

    RegisterPresenter registerPresenter;
    public RegisterInteractor(RegisterPresenter registerPresenter){
        this.registerPresenter = registerPresenter;
    }

    @Override
    public void registerUser(String name, String email, String phone) {

    }
}
