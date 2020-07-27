package com.ckh.gameframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class SpriteAnimation extends GraphicObject {
    private Rect mRect;
    private int fps;
    private int frames;

    private long m_frameTimer;
    private int mCurrentFrame;
    private int mSpriteWidth;
    private int mSpriteHeight;
    protected Paint alphaPaint;

    // 최근 프레임
    public SpriteAnimation(Bitmap bitmap) {
        super(bitmap);
        alphaPaint = new Paint();
        mRect = new Rect(0, 0, 0, 0);
        mCurrentFrame = 0;
        m_frameTimer = 0;

    }

    protected int getSpriteWidth(){
        return mSpriteWidth;
    }

    @Override
    public void Draw(Canvas canvas){
        Rect dest = new Rect(mX, mY, mX + mSpriteWidth, mY +mSpriteHeight);
        canvas.drawBitmap(mBitmap, mRect, dest, alphaPaint);
    }

    public void Update(long gameTime){
        if (gameTime > m_frameTimer + fps){
            m_frameTimer = gameTime;
            mCurrentFrame += 1;
            if ( mCurrentFrame >= frames) mCurrentFrame = 0;
        }
        mRect.left = mCurrentFrame * mSpriteWidth;
        mRect.right = mRect.left + mSpriteWidth;
    }
    public void initSpriteData(int _width, int _height, int _fps, int _iframe){
        mSpriteWidth = _width;
        mSpriteHeight =  _height;
        mRect.top = 0; mRect.bottom = mSpriteHeight;
        mRect.left = 0; mRect.right = mSpriteWidth;
        fps = 1000 / _fps;
        frames = _iframe;
    }

}
