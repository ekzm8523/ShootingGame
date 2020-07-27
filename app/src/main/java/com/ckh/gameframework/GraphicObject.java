package com.ckh.gameframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
    protected Bitmap mBitmap;
    protected int mX;
    protected int mY;

    public GraphicObject(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mX = 0;
        mY = 0;
    }
    public void setPosition(int x, int y){
        mX = x;
        mY = y;
    }
    public void Draw(Canvas canvas){
        canvas.drawBitmap(mBitmap, mX, mY, null);
    }

    public int getX(){
        return mX;
    }

    public int getY(){
        return mY;
    }
}
