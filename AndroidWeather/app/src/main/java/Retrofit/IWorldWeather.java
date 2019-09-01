package Retrofit;

import Model.ResultWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWorldWeather {
    @GET("weather")
    Call<ResultWeather> getWeather(@Query("lat") double lat,
                                   @Query("lon") double lon,
                                   @Query("units") String units,
                                   @Query("appid") String appid);
}
