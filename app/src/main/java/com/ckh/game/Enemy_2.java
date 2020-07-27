package com.ckh.game;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

import java.util.Random;

public class Enemy_2 extends Enemy {
    float dy;

    public Enemy_2() {
        super(AppManager.getInstance().getBitmap(R.drawable.enemy2));
        hp = 1;
        maxHp = 2;
        speed = 3.0f;
        valueScore = 500;
        Random rand = new Random();
        this.initSpriteData(117,91,6,4);
        if (rand.nextBoolean()) dy = -speed;
        else dy = speed;
    }

    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        mBoundBox.set(mX, mY, mX + 117, mY + 70);
    }

    @Override
    protected void doMove() {
        if (mX >= 1440) mX -= speed;
        else {
            mX -= (speed * 3);
            mY += dy;
            // 화면밖으로 이동시 방향 전환
            if(mY + mBoundBox.height() > 1080 || mY < 0) dy *= -1;
        }
    }
}
