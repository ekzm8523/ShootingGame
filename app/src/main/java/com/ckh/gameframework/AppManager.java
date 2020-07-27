package com.ckh.gameframework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ckh.game.GameState;

public class AppManager {
    private GameView mGameView;
    private Resources mResources;
    public GameState mGameState;
    public int lastScore;

    void setGameView(GameView _gameView){
        mGameView = _gameView;
    }

    void setResources(Resources _resources){
        mResources = _resources;
    }

    public GameView getGameView(){
         return mGameView;
    }

    Resources getResources(){
        return  mResources;
    }
    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(mResources, r);
    }

    private static AppManager s_Instance;

    public static AppManager getInstance(){
        if(s_Instance == null) s_Instance = new AppManager();
        return s_Instance;
    }

}
