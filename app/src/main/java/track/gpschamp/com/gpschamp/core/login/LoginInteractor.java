package track.gpschamp.com.gpschamp.core.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.model.login.LoginResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;

/**
 * Created by sudhirharit on 23/01/18.
 */

public class LoginInteractor implements LoginContractor.Interactor{

    LoginPresenter mPresenter;

    public LoginInteractor(LoginPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void loginUser(String userName, String password, String macId) {

        GpsWebService service = WebServiceClient.getRetrofitClient().create(GpsWebService.class);

        Call<LoginResponse> call = service.generateUserToken(userName, password, macId);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    mPresenter.loginResponse(response.body());
                }else {
                    mPresenter.loginResponse(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mPresenter.loginResponse(null);
            }
        });
    }
}
