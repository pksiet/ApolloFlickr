package in.apollo.model.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static in.apollo.model.Network.WebUrls.BASE_URL_FLICKER;

public class RetrofitClient {
    private static ApiInterface apiInterface = null;

    public static ApiInterface instance() {
        if (apiInterface==null) {
            apiInterface = retrofitBuilder().create(ApiInterface.class);
        }
        return apiInterface;
    }

    private static Retrofit retrofitBuilder(){
        return  new Retrofit.Builder()
                .client(okhttp())
                .baseUrl(BASE_URL_FLICKER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient okhttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
