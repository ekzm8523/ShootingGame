package com.ckh.gameframework;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;

import com.ckh.game.GameState;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private GameViewThread mThread;
    private IState mState;
    private SpriteAnimation mSpr;
    //private GraphicObject mImage;

    public GameView(Context context) {
        super(context);

        setFocusable(true);
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());
        changeGameState(new GameState());
        getHolder().addCallback(this);
        mThread = new GameViewThread(getHolder(), this);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        mState.Render(canvas);
    }

    public void Update(){
        mState.Update();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        mState.onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mState.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void changeGameState(IState _state){
        if (mState != null) mState.Destroy();
        _state.Init();
        mState = _state;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        // Surface가 실행될대 스레드 실행
        //Toast.makeText(getContext(),"SurfaceView created", Toast.LENGTH_LONG).show();
        mThread.setRunning(true);
        mThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        // Surface가 파괴될 때 스레드 정상 종료
        //Toast.makeText(getContext(),"SurfaceView destroyed", Toast.LENGTH_LONG).show();
        boolean retry = true;
        mThread.setRunning(false);
        while(retry){
            try{
                // 실행되고 있는 쓰레드의 종료를 기다림
                mThread.join();
                retry = false;
            } catch(InterruptedException e) {}
        }
    }
}
