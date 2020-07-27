package com.ckh.game;


import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class MissilePlayer extends Missile {
    private Player player;

    public MissilePlayer(int x, int y, Player _player) {
        super(AppManager.getInstance().getBitmap(R.drawable.shot));
        this.setPosition(x, y);
        this.player = _player;
    }

    public Player getPlayer(){
        return this.player;
    }

    @Override
    public void Update() {
        super.Update();
        mX += 30;
        mBoundBox.left = mX;
        mBoundBox.top = mY;
        mBoundBox.right = mX + 112;
        mBoundBox.bottom = mY + 30;
    }
}
