package com.ckh.game;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class ItemScore extends Item {
    public ItemScore(int x, int y){
        super(AppManager.getInstance().getBitmap(R.drawable.coin));
        this.initSpriteData(130,130,4,4);
        setPosition(x,y);
    }
    public void Update(long gameTime){
        super.Update(gameTime);
    }

    @Override
    public void applyToPlayer(Player player) {
        player.accumulateScore(1000);
    }
}
