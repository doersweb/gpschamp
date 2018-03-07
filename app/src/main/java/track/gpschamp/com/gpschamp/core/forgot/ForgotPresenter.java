package track.gpschamp.com.gpschamp.core.forgot;

import java.util.FormatFlagsConversionMismatchException;

import retrofit2.http.GET;
import track.gpschamp.com.gpschamp.model.GeneralResponse;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class ForgotPresenter implements ForgotContractor.Presenter {

    ForgotContractor.View mView;
    ForgotInteractor forgotInteractor;
    public ForgotPresenter(ForgotContractor.View mView){
        this.mView = mView;
        forgotInteractor = new ForgotInteractor(this);
    }

    @Override
    public void onForgotPasswordResponse(GeneralResponse generalResponse) {
        mView.onForgotPasswordResponse(generalResponse);
    }

    @Override
    public void forgotPassword(String name, String email, String phone) {
        forgotInteractor.forgotPassword(name, email, phone);
    }
}
