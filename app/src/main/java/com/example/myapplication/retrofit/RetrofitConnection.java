package com.example.myapplication.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {


    private final static String URL = "https://run.mocky.io/";

    public static RetrofitInterface getApi(){return getInstance().create(RetrofitInterface.class);
    }



    private static Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpLoggingInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private static OkHttpClient httpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
/*        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
*//*                Request newRequest =chain.request().newBuilder().addHeader("Content-Type","application/json; charset=utf-8").
                        addHeader("x-tdi-client-secret","ZwyXj7jBjUFAxhpemyYMlzA6wFEzr5qyRuGsiABksqnBShuc25pLUsfUfOEJyiVSKCBUCiTrEl89JWQd9HCh29U80QleHvuu0YCAbVvvDOILTKyblLVAQnQorzeMR").build();*//*
                return chain.proceed(request);
            }
        }).addInterceptor(interceptor).build();*/
           OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }





}
