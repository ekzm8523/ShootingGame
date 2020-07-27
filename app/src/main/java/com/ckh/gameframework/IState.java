package com.ckh.gameframework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {
    // 이상태로 바뀌었을때 실행할
    public void Init();

    // 다른 상태로 바뀌었을때 실행 할 것
    public void Destroy();

    // 지속적으로 수행할 것
    public void Update();

    // 그려야 하는것
    public void Render(Canvas canvas);

    // 키 입력 처리
    public boolean onKeyDown(int keyCode, KeyEvent event);

    // 터치 입력 처리
    public boolean onTouchEvent(MotionEvent event);
}
