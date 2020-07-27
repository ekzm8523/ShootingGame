package com.ckh.game;

import android.graphics.Bitmap;

import com.ckh.gameframework.AppManager;

import java.util.ArrayList;

public class Boss extends Enemy {
    protected int nextStage;
    public Boss(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    protected void doMove(){
        mY -= (speed*3);
        if(mY<0 || mY>1080-390) speed = -speed;
    }

    @Override
    public void destroy(Player player, ArrayList<Item> itemList){
        super.destroy(player, itemList);
        AppManager.getInstance().mGameState.initStage(nextStage);
        AppManager.getInstance().mGameState.bossState=false;
    }
}
