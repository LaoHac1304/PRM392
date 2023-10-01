package com.example.labgame;

import android.content.Context;
import android.media.MediaPlayer;

public class ThemeSong {
    MediaPlayer theme;
    static ThemeSong instance = null;
    Context mContext;
    private ThemeSong(Context context) {
        mContext = context.getApplicationContext();
    }
    public static ThemeSong getInstance(Context context)
    {
        if (instance == null){
            synchronized (ThemeSong.class) {
                instance = new ThemeSong(context);
            }
        }
        return instance;
    }
    public void create() {
        if (theme != null) {
            return;
        }
        theme = MediaPlayer.create(mContext, R.raw.theme);
    }

    public void start() {
        if (theme.isPlaying()) {
            return;
        }
        theme.start();
    }

    public void start(boolean isLooping) {
        if (theme.isPlaying()) {
            return;
        }
        theme.setLooping(true);
        theme.start();
    }
    public  void stop() {
        theme.stop();
    }

    public void changeMusic (int song) {
        if (theme.isPlaying()) {
            theme.stop();
        }
        theme = MediaPlayer.create(mContext, song);
    }
}
