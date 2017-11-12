package com.example.smra_pc.circleimageinsidetoolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Smra_PC on 11/12/2017.
 */

public class FirebaseJobService extends JobService {

    private AsyncTask mBackgroundTask;


    @Override
    public boolean onStartJob(final JobParameters job) {
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String time = df.format(c.getTime());
        Log.d("firebase job", "firebase job started at " + time);

        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Calendar c = Calendar.getInstance();

                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                String time = df.format(c.getTime());
                Log.d("firebase job", "firebase job onPostExecute() called at " + time);
                super.onPostExecute(o);

                Intent i = new Intent("com.samer.USER_ACTION");
                i.putExtra("Time",time);
                sendBroadcast(i);

                jobFinished(job, false);
            }
        };

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}
