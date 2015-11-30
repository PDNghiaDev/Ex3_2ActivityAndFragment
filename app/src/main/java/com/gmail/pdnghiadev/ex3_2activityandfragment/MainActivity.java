package com.gmail.pdnghiadev.ex3_2activityandfragment;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int TIME_COUNT = 10000; // 10s
    private Button mBtnStart, mBtnTap;
    private Chronometer mTime;
    private TextView mCountTap;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadComponents();

        mTime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime() - startTime >= TIME_COUNT) {
                    pauseTapping();
                }
            }
        });

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTapping();
            }
        });
    }

    private void startTapping() {
        startTime = SystemClock.elapsedRealtime();
        mTime.setBase(SystemClock.elapsedRealtime());
        mTime.start();
        mBtnTap.setEnabled(true);
        mBtnStart.setEnabled(false);
    }

    private void pauseTapping() {
        mBtnTap.setEnabled(false);
        mTime.stop();
        mBtnTap.setEnabled(false);
        mBtnStart.setEnabled(true);
    }

    private void loadComponents() {
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnTap = (Button) findViewById(R.id.btn_tap);
        mTime = (Chronometer) findViewById(R.id.ch_time);
        mCountTap = (TextView) findViewById(R.id.tv_time);
    }
}
