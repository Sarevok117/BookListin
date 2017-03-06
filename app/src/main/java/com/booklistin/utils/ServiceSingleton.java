package com.booklistin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Varkiil on 06/03/2017.
 */

public class ServiceSingleton {

    private static ServiceSingleton mInstance = null;
    private Retrofit retrofit = null;
    private GloseApi service = null;

    public final static String BASE_URL = "https://api.glose.com/v1/";

    public ServiceSingleton(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public GloseApi getService() {
        return service;
    }

    public void setService(GloseApi service) {
        this.service = service;
    }

    public ServiceSingleton() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Gson gson = new GsonBuilder()
                .create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        service = this.retrofit.create(GloseApi.class);
    }

    public static void setmInstance(ServiceSingleton mInstance) {
        ServiceSingleton.mInstance = mInstance;
    }

    public static ServiceSingleton getmInstance() {
        if (mInstance == null) {
            mInstance = new ServiceSingleton();
        }
        return mInstance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

}
