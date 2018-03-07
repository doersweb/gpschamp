package track.gpschamp.com.gpschamp.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * Created by sudhirharit on 27/01/18.
 */

public class WebServiceClient {


    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        return retrofit;

    }

}
