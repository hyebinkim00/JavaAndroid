package com.example.myapplication.retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

   /* @GET("/api/v1/weathers")
    Call <airInfo1> aDayInfo(@Query("sidoName") String param1, @Query("cityName") String param2);
*/


    @GET("/v3/251dbd63-7082-43ac-beac-b0ab21dbc2e5")
    Call <ResponseBody> DayInfo();

}
