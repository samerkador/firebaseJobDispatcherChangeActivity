package com.example.smra_pc.circleimageinsidetoolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    IntentFilter intentFilter;
    ChangeTextViewBroadCastReceiver changeTextViewBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = (ImageView)toolbar.findViewById(R.id.image_view);

        imageView.setOnClickListener(this);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TakeText.class));
            }
        });


        intentFilter = new IntentFilter();
        intentFilter.addAction("com.samer.USER_ACTION");

        changeTextViewBroadCastReceiver = new ChangeTextViewBroadCastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(changeTextViewBroadCastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(changeTextViewBroadCastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.image_view:
                Toast.makeText(this,"hello from the image", Toast.LENGTH_LONG).show();
                break;
        }
    }


    public class ChangeTextViewBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().compareTo("com.samer.USER_ACTION") == 0){
                if(intent.hasExtra("Time")){
                    TextView textView = (TextView) findViewById(R.id.text_view_time);
                    String time = intent.getStringExtra("Time");
                    textView.setText(time);
                }
            }
        }
    }
}
