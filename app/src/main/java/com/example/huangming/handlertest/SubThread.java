package com.example.huangming.handlertest;

import android.os.Looper;

/**
 * 自定义子线程
 *
 * @author Huangming  2017/10/25
 */
public class SubThread extends Thread {
    private Looper mLooper;

    public SubThread() {
        super("SubThread");
    }

    @Override
    public void run() {
        Looper.prepare();
        mLooper = Looper.myLooper();
        Looper.loop();
    }

    public Looper getLooper() {
        return mLooper;
    }
}
