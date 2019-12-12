package com.example.libgen;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Network mInstance;
    private Retrofit retrofit;
    private String BASE_URL = "http://gen.lib.rus.ec/";
    private String proxyHost = "118.174.232.106";
    private Integer proxyPort = 50491;

    public static Network getInstance(){
        if(mInstance == null)
            mInstance = new Network();
        return mInstance;
    }

    public Network(){
  //      Proxy proxy = new Proxy(Proxy.Type.HTTP,  new InetSocketAddress(proxyHost, proxyPort));
   //     OkHttpClient client = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).proxy(proxy).build();

        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
