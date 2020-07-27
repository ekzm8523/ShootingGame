package com.ckh.game;

import android.graphics.Bitmap;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class Boss1 extends Boss {
    public Boss1(Bitmap bitmap) {
        super(bitmap);
    }
    long lastShoot = System.currentTimeMillis();
    public Boss1() {
        super(AppManager.getInstance().getBitmap(R.drawable.boss_1));
        hp = 10;
        maxHp = 10;
        speed = 2.0f;
        valueScore = 5000;
        nextStage=2;
        this.initSpriteData(392*2,390*2,6,11);
    }

    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        Attack();
        mBoundBox.set(mX+100, mY+100, mX + 392*2-100, mY + 390*2-100);
    }

    void Attack(){
        if(System.currentTimeMillis() - lastShoot >= 1000 ) {
            lastShoot = System.currentTimeMillis();
            AppManager.getInstance().mGameState.enemyMissileList.add(new MissileEnemy(mX-80, mY+90));
        }
    }
}
