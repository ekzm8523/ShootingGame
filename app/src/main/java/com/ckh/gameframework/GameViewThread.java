package com.ckh.gameframework;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread {
    private SurfaceHolder mSurfaceHolder;
    private GameView mGameView;

    private boolean mRun = false;
    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView){
        mSurfaceHolder = surfaceHolder;
        mGameView = gameView;
    }

    public void setRunning(boolean run) {
        mRun = run;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        Canvas _canvas;
        while(mRun){
            _canvas = null;
            try {
                mGameView.Update();
                _canvas = mSurfaceHolder.lockCanvas(null);
                synchronized (mSurfaceHolder){
                    //TODO .draw() or .onDraw()
                    mGameView.onDraw(_canvas);
                }
            } finally {
                if(_canvas != null) mSurfaceHolder.unlockCanvasAndPost(_canvas);
            }
        }
    }
}
