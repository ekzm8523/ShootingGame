package com.ckh.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.ckh.gameframework.AppManager;
import com.ckh.gameframework.GraphicObject;
import com.ckh.gameframework.R;

public class BackGround extends GraphicObject {
    static final float SCROLL_SPEED = 5f;
    private float mScroll = 0;
    public boolean backgroundEnd = false;


    public BackGround(int backtype) {
        super(null);
        if (backtype == 1)
            mBitmap = Bitmap.createScaledBitmap(
                    AppManager.getInstance().getBitmap(R.drawable.background_1),
                    3310,
                    1080,
                    true
            );
        else if( backtype == 2)
            mBitmap = Bitmap.createScaledBitmap(
                    AppManager.getInstance().getBitmap(R.drawable.background_23),
                    3310,
                    1080,
                    true);
        else if( backtype == 3)
            mBitmap = Bitmap.createScaledBitmap(
                    AppManager.getInstance().getBitmap(R.drawable.background_24),
                    3310,
                    1080,
                    true);
        setPosition((int) mScroll,0 );
    }

    @Override
    public void Draw(Canvas canvas){
        canvas.drawBitmap(mBitmap, mX, mY, null);
    }
    void Update(){
        mScroll = mScroll - SCROLL_SPEED;
        if (mScroll <= -1500) {mScroll = -1500; backgroundEnd = true;}
        setPosition((int) mScroll, 0);

    }
}
