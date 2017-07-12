package com.led_demo2.led_demo2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class gsensor_Service extends Service {
    private static final String TAG = "testService";
    public gsensor_Service() {

        Log.d(TAG, "gsensor_Service entry" );
    }
    @Override
    public void onCreate() {
        Log.e(TAG, "MusicSerice onCreate()");
    }
    @Override
    public void onStart(Intent intent, int startId) {

        Log.e(TAG, "MusicSerice onStart()");


        super.onStart(intent, startId);
    }
    @Override
    public void onDestroy() {

        Log.e(TAG, "MusicSerice onDestroy()");


        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "gsensor_Service onBind" );
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public boolean onUnbind(Intent intent) {

        Log.e(TAG, "MusicSerice onUnbind()");
        return super.onUnbind(intent);
    }
}
