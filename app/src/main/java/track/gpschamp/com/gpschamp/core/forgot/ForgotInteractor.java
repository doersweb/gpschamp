package track.gpschamp.com.gpschamp.core.forgot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.model.GeneralResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class ForgotInteractor implements ForgotContractor.Interactor {

    ForgotPresenter forgotPresenter;
    public ForgotInteractor(ForgotPresenter forgotPresenter){
        this.forgotPresenter = forgotPresenter;
    }

    @Override
    public void forgotPassword(String name, String email, String phone) {
        GpsWebService service = WebServiceClient.getRetrofitClient().create(GpsWebService.class);

        Call<GeneralResponse> call = service.forgotPassword(name, email, phone);
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if(response.isSuccessful()){
                    forgotPresenter.onForgotPasswordResponse(response.body());
                }else {
                    forgotPresenter.onForgotPasswordResponse(null);
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                forgotPresenter.onForgotPasswordResponse(null);
            }
        });
    }
}
