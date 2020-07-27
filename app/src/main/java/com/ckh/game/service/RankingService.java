package com.ckh.game.service;

import com.ckh.game.model.Ranking;
import com.ckh.game.model.RankingListVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RankingService {
    @GET("/api/rankings")
    Call<RankingListVO> getRankings();

    @POST("api/rankings")
    Call<Ranking> postRanking();

}
