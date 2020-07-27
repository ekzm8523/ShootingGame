package com.ckh.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.ckh.game.model.Ranking;
import com.ckh.game.model.RankingListVO;
import com.ckh.game.network.RetrofitClient;
import com.ckh.game.service.RankingService;
import com.ckh.gameframework.IState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GameEndState implements IState {
    RankingService rankingService;
    Call<RankingListVO> mCallRankingList;
    Call<Ranking> mCallPostRanking;
    private RankingListVO rankingsList = null;
    private Callback<RankingListVO> getRankingsCallback;
    private Callback<Ranking> postRankingCallback;

    String username;
    public GameEndState(){
        getRankingsCallback = new Callback<RankingListVO>(){

            @Override
            public void onResponse(Call<RankingListVO> call, Response<RankingListVO> response) {
                rankingsList = response.body();
            }

            @Override
            public void onFailure(Call<RankingListVO> call, Throwable t) {
                Log.e("response failed", "" + t.toString());
            }
        };

        postRankingCallback = new Callback<Ranking>() {
            @Override
            public void onResponse(Call<Ranking> call, Response<Ranking> response) {
                mCallRankingList.enqueue(getRankingsCallback);
            }

            @Override
            public void onFailure(Call<Ranking> call, Throwable t) {
                Log.e("response failed", "" + t.toString());
            }
        };
    }
    @Override
    public void Init() {
        rankingService = RetrofitClient.getRankingService();
        mCallPostRanking = rankingService.postRanking();
        mCallRankingList = rankingService.getRankings();
        mCallPostRanking.enqueue(postRankingCallback);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
    }

    @Override
    public void Render(Canvas canvas) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }


}
