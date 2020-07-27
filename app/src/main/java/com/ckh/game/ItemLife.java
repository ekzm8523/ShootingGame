package com.ckh.game;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class ItemLife extends Item{
    public ItemLife(int x, int y){
        super(AppManager.getInstance().getBitmap(R.drawable.item2));
        this.initSpriteData(130,130,4,4);
        setPosition(x,y);
    }
    public void Update(long gameTime){
        super.Update(gameTime);
    }
    public void applyToPlayer(Player player){
        player.addLife();
    }
}
