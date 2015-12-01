package com.gmail.pdnghiadev.ex3_2activityandfragment.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import com.gmail.pdnghiadev.ex3_2activityandfragment.R;

import java.util.Date;

/**
 * Created by PDNghiaDev on 12/1/2015.
 */
public class ResultItem {
    private Date date;
    private int countTap;

    public ResultItem(Date date, int countTap) {
        this.date = date;
        this.countTap = countTap;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCountTap() {
        return countTap;
    }

    public void setCountTap(int countTap) {
        this.countTap = countTap;
    }



}
