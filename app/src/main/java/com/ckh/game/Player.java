package com.ckh.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.ckh.gameframework.SpriteAnimation;

public class Player extends SpriteAnimation{
    private VibratorView mVibe;
    Rect mBoundBox = new Rect();
    Boolean mPlayerHit = false;
    private Player player;

    int mLife = 3;

    int mSkill = 3;
    int score = 0;

    public Player(Bitmap bitmap) {
        super(bitmap);
        // 고정값 중
        this.initSpriteData(271,273,6,6);
        this.setPosition(120, 400);
    }
    public int getLife() {return  mLife; }
    public void addLife() {mLife++;}
    public void destroyPlayer(){
        mLife--;
       // mVibe.m_vibrator.vibrate(1000);
    }
    public int getSkill(){return mSkill;}
    public void addSkill(){mSkill++;}
    public void useSkill(){mSkill--;}
    public int getScore(){
        return score;
    }
    public void accumulateScore(int value){
        this.score += value;
    }
    public void invincibility(long gameTime) {
        if(System.currentTimeMillis() - gameTime >= 1000) {
            mPlayerHit = false;
            SetAlpha(255);
        }
    }
    public void Update(long gameTime){
        super.Update(gameTime);
        mBoundBox.set(mX+100, mY+100, mX+271-15, mY+273-83);
    }
    public void SetAlpha(int alpha){
        alphaPaint.setAlpha(alpha);
    }
}
