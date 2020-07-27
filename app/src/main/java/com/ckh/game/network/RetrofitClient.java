package com.ckh.game.network;

import android.service.notification.NotificationListenerService;

import com.ckh.game.service.RankingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RankingService getRankingService(){
        return getInstance().create(RankingService.class);
    }

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
