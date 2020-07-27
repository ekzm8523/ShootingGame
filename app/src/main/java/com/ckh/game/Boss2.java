package com.ckh.game;

import android.graphics.Bitmap;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class Boss2 extends Boss{
    public Boss2(Bitmap bitmap) {
        super(bitmap);
    }
    long lastShoot = System.currentTimeMillis();
    public Boss2() {
        super(AppManager.getInstance().getBitmap(R.drawable.boss_2));
        hp = 10;
        maxHp = 10;
        speed = 2.0f;
        valueScore = 7000;
        nextStage = 3;
        this.initSpriteData(332*2,317*2,6,16);
    }

    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        Attack();
        mBoundBox.set(mX+100, mY+100, mX + 392*2-100, mY + 390*2-100);
    }

    void Attack(){
        if(System.currentTimeMillis() - lastShoot >= 750 ) {
            lastShoot = System.currentTimeMillis();
            AppManager.getInstance().mGameState.enemyMissileList.add(new MissileEnemy(mX-80, mY+90));
        }
    }
}
