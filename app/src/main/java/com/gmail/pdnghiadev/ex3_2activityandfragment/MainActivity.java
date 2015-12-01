package com.gmail.pdnghiadev.ex3_2activityandfragment;

import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import com.gmail.pdnghiadev.ex3_2activityandfragment.model.ResultItem;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int TIME_COUNT = 10000; // 10s
    private Button mBtnStart, mBtnTap;
    private Chronometer mTime;
    private TextView mCountTap;
    private long startTime;
    private Date date;
    private int count;
    TapCountResultFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
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

        mBtnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                mCountTap.setText(String.valueOf(count));
            }
        });

        fragment = new TapCountResultFragment();
        getFragmentManager().beginTransaction().add(R.id.fl_result_fragment, fragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void startTapping() {
        startTime = SystemClock.elapsedRealtime();
        mTime.setBase(SystemClock.elapsedRealtime());
        mTime.start();
        mBtnTap.setEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mBtnTap.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        }
        mBtnStart.setEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mBtnStart.setBackground(new ColorDrawable(getResources().getColor(R.color.colorDivider)));
        }

        count = 0;
        mCountTap.setText(String.valueOf(count));
        date = new Date();
    }

    private void pauseTapping() {
        mBtnTap.setEnabled(false);
        mTime.stop();
        mBtnTap.setEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mBtnTap.setBackground(new ColorDrawable(getResources().getColor(R.color.colorDivider)));
        }
        mBtnStart.setEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mBtnStart.setBackground(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        }

        fragment.onClick(new ResultItem(date, count));

        TapCountResultFragment fragment = (TapCountResultFragment) getFragmentManager().findFragmentById(R.id.fl_result_fragment);

        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.detach(fragment);
        fragTransaction.attach(fragment);
        fragTransaction.commit();
    }

    private void loadComponents() {
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnTap = (Button) findViewById(R.id.btn_tap);
        mTime = (Chronometer) findViewById(R.id.ch_time);
        mCountTap = (TextView) findViewById(R.id.tv_time);
    }

}
