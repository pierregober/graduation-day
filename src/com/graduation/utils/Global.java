package com.graduation.utils;

public class Global {

    public static float volume = 1.0f;
    public static boolean mute = false;

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volume) {
        Global.volume = volume;
    }

    public static boolean isMute() {
        return mute;
    }

    public static void setMute(boolean mute) {
        Global.mute = mute;
    }
}
