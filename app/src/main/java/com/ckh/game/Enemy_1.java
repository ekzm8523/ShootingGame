package com.ckh.game;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class Enemy_1 extends Enemy {
    long lastShoot = System.currentTimeMillis();
    public Enemy_1() {
        super(AppManager.getInstance().getBitmap(R.drawable.enemy1));
        maxHp = 2;
        hp = 2;
        speed = 2.0f;
        valueScore = 100;
        this.initSpriteData(327,203,6,4);
    }

    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        Attack();
        mBoundBox.set(mX, mY+20, mX + 327, mY + 203-20);
    }

    @Override
    protected void doMove(){
        if (mX >= 1440) mX -= speed;
        else mX -= speed * 3;
    }

    void Attack(){
        if(System.currentTimeMillis() - lastShoot >= 1000 && mX >= 1440) {
            lastShoot = System.currentTimeMillis();
            AppManager.getInstance().mGameState.enemyMissileList.add(new MissileEnemy(mX-180, mY+90));
        }
    }
}
