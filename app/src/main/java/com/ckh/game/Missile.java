package com.ckh.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.ckh.gameframework.GraphicObject;

public class Missile extends GraphicObject {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;

    Rect mBoundBox;

    public Missile(Bitmap bitmap) {
        super(bitmap);
        mBoundBox = new Rect();
    }

    public Rect getBoundBox(){
        return mBoundBox;
    }

    public void Update(){
        if (mX > 1920) state = STATE_OUT;

    }
}
