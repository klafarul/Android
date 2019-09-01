package Retrofit;

import Model.ResultWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RClient {
    private static RClient mInstance;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private Retrofit mRetrofit;
    private static final String APPID = "d2b673baa442e466b289fca8d5cc747f";

    private RClient(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RClient getInstance(){
        if (mInstance == null){
            mInstance = new RClient();
        }
        return mInstance;
    }

    public IWorldWeather getJSONApi(){
        return mRetrofit.create(IWorldWeather.class);
    }


}
