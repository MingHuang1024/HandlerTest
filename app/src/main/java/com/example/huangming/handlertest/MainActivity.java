package com.example.huangming.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // private SubThread subThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        System.out.println("UI线程id = " + Thread.currentThread().getId() + " name = " + Thread
            .currentThread().getName());
        final SubThread subThread = new SubThread();
        subThread.start();
        findViewById(R.id.btnSendMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注意新建Handler的时候传了子线程的looper变量进去
                Handler handler = new Handler(subThread.getLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        System.out.println("msg.what = " + msg.what);
                        System.out.println("处理消息的线程id = " + Thread.currentThread().getId()
                            + " name = " + Thread.currentThread().getName());
                        return false;
                    }
                });
                System.out.println("发消息的线程id = " + Thread.currentThread().getId() + " name = " + Thread
                    .currentThread().getName());
                // 传一个空消息，msg.what等于2
                handler.sendEmptyMessage(2);
            }
        });

    }
}
