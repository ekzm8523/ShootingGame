package com.ckh.gameframework;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager {
    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundPoolMap;
    private AudioManager mAudioManager;
    private Context mActivity;

    public void init(Context _context){
        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();
        mSoundPoolMap = new HashMap<>();
        mAudioManager = (AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
        mActivity = _context;
    }

    public void addSound(int _index, int _soundID){
        int id = mSoundPool.load(mActivity, _soundID, 1);
        mSoundPoolMap.put(_index, id);
    }

    public void play(int _index){
        float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mSoundPool.play((Integer)mSoundPoolMap.get(_index), streamVolume, streamVolume,
                1,0, 1f);
    }

    public void playLooped(int _index){
        float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mSoundPool.play((Integer)mSoundPoolMap.get(_index), streamVolume, streamVolume,
                1,-1, 1f);
    }

    private static SoundManager s_instance;

    public static SoundManager getInstance(){
        if(s_instance == null) s_instance = new SoundManager();
        return s_instance;
    }
}
