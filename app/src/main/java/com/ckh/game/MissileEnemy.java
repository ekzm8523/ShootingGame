package com.ckh.game;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class MissileEnemy extends Missile {
    public MissileEnemy(int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.fire2));
        this.setPosition(x, y);
    }
    public void Update(){
        mX -= 8;
        if (mX < 0 ) state = STATE_OUT;
        mBoundBox.set(mX+10, mY+10, mX+72, mY+62);
    }
}

