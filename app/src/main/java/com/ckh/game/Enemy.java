package com.ckh.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.ckh.gameframework.SpriteAnimation;

import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends SpriteAnimation{
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;
    private Rect hpBar;
    protected int valueScore;
    Random mRandPattern = new Random();
    protected Rect mBoundBox;
    Paint paintBarColor;
    protected int moveType;
    protected int maxHp;
    public int hp;
    protected float speed;

    public Enemy(Bitmap bitmap) {
        super(bitmap);
        mBoundBox = new Rect();
        moveType = mRandPattern.nextInt(3);
        paintBarColor = new Paint();
        paintBarColor.setColor(Color.RED);
    }

    public int getHp() {
        return this.hp;
    }

    public void drawHpBar(Canvas canvas){
        hpBar = new Rect(mX, mY, mX + (getSpriteWidth() *  hp / maxHp), mY+20);
        canvas.drawRect(hpBar, paintBarColor);
    }
    public void Draw(Canvas canvas){
        super.Draw(canvas);
        drawHpBar(canvas);
   }
    public void Update(long gameTime){
        super.Update(gameTime);
        Move();
    }
    public void attackedByMissile(){
        this.hp--;
    }

    protected abstract void doMove() ;

    public void Move(){
        this.doMove();
        if (mX < -300) state = STATE_OUT;
    }

    public void destroy(Player player, ArrayList<Item> itemList){
        player.accumulateScore(valueScore);
        Item item = Item.generateRandomItem(mX, mY);
        if (item != null) itemList.add(item);
    }

}
