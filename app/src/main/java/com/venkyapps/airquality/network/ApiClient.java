package com.venkyapps.airquality.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.venkyapps.airquality.features.airquality.model.AirQualityResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class ApiClient {

    private static Retrofit retrofit = null;
    public static String breezometerApiBaseUrl = "https://api.breezometer.com/";
    private static HttpLoggingInterceptor loggingInterceptor;

    private static OkHttpClient.Builder client;


    public Retrofit initializeRetrofit() {

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder();
        client.addInterceptor(loggingInterceptor);

        Gson gson = new GsonBuilder().setLenient().create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(breezometerApiBaseUrl)
                    .client(client.build())               // only for debugging. remove when production apk
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    // .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public AirQualityApiInterface getClient(Retrofit retrofit) {
        return retrofit.create(AirQualityApiInterface.class);
    }

    public interface AirQualityApiInterface {
        @GET("baqi")
        Call<AirQualityResponse> getAirQualityValues(@Query("lat") String lat, @Query("lon") String longitude,
                                                     @Query("key") String apiKey);

    }

}