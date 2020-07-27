package com.ckh.game;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.R;

public class ItemSpecialAttack extends Item{
    public ItemSpecialAttack(int x, int y){
        super(AppManager.getInstance().getBitmap(R.drawable.item1));
        this.initSpriteData(130,130,4,4);
        setPosition(x,y);
    }
    public void Update(long gameTime){
        super.Update(gameTime);
    }

    @Override
    public void applyToPlayer(Player player) {
        player.addSkill();
    }
}
