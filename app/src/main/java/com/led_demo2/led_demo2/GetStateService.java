package com.led_demo2.led_demo2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class GetStateService extends Service {
    public GetStateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
