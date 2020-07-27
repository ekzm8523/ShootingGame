package com.ckh.game;

import android.graphics.Bitmap;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class Boss3 extends Boss{
    public Boss3(Bitmap bitmap) {
        super(bitmap);
    }
    long lastShoot = System.currentTimeMillis();
    public Boss3() {
        super(AppManager.getInstance().getBitmap(R.drawable.boss_3));
        hp = 10;
        maxHp = 10;
        speed = 2.0f;
        valueScore = 15000;
        nextStage = 3;
        this.initSpriteData(390*2,358*2,6,12);
    }

    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        Attack();
        mBoundBox.set(mX+200, mY+100, mX + 392*2-100, mY + 390*2-100);
    }

    void Attack(){
        if(System.currentTimeMillis() - lastShoot >= 500 ) {
            lastShoot = System.currentTimeMillis();
            AppManager.getInstance().mGameState.enemyMissileList.add(new MissileEnemy(mX-80, mY+90));
        }
    }
}
