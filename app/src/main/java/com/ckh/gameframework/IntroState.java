package com.ckh.gameframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.ckh.game.GameState;

public class IntroState implements IState {
    Bitmap icon;
    int x, y;

    @Override
    public void Init() {
        Toast.makeText(AppManager.getInstance().getGameView().getContext(),
                "AppManager참조하여 리소스 획득", Toast.LENGTH_SHORT).show();
        icon = AppManager.getInstance().getBitmap(R.drawable.icon);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap(icon, x, y, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppManager.getInstance().getGameView().changeGameState(new GameState());
        return true;
    }
}
