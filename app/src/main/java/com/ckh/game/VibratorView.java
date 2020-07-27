package com.ckh.game;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;

public class VibratorView extends View {
    Vibrator m_vibrator;
    public VibratorView(Context context) {
        super(context);
        //m_vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

}
