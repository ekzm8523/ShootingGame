package com.ckh.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.ckh.gameframework.SpriteAnimation;

import java.util.Random;

public abstract class Item extends SpriteAnimation {
    Rect mBoundBox;

    public boolean bOut = false;
    public Item(Bitmap bitmap){
        super(bitmap);
        mBoundBox = new Rect();
    }

    public void Update(long gameTime){
        super.Update(gameTime);
        mX -= 6;
        if (mX > 1920) bOut = true;
        mBoundBox.set(mX, mY, mX+130, mY+130);
    }
    public static Item generateRandomItem(int x, int y){
        Random rand = new Random();
        Item itemObject = null;
        if (rand.nextInt(4) == 0 ) return itemObject;

        switch (rand.nextInt(3)){
            case 1:
                itemObject =  new ItemScore(x, y);
                break;
            case 2:
                itemObject =  new ItemLife(x, y);
                break;
            case 3:
                itemObject = new ItemSpecialAttack(x, y);
                break;
        }
        return itemObject;
    }

    public abstract void applyToPlayer( Player player );
}
