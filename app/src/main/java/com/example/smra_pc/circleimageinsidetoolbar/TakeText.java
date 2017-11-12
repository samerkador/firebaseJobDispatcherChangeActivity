package com.example.smra_pc.circleimageinsidetoolbar;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.smra_pc.circleimageinsidetoolbar.databinding.ActivityTakeTextBinding;

import java.util.Calendar;


public class TakeText extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTakeTextBinding bindings = DataBindingUtil.setContentView(this, R.layout.activity_take_text);
        bindings.startJob.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TakeTextUtilities.scheduleJob(this);
    }
}
