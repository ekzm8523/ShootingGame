package com.ckh.game;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;


public class MissileSpecialSkill extends Missile  {
    public MissileSpecialSkill(int x, int y){
            super(AppManager.getInstance().getBitmap(R.drawable.skill));
            this.setPosition(x,y);
    }

    @Override
    public void Update(){
        super.Update();
        mX +=14;
        mBoundBox.left =mX;
        mBoundBox.top =mY;
        mBoundBox.right =mX + 1300;
        mBoundBox.bottom =mY+476;
    }
}