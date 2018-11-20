package com.example.admin.serviceconnect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.serviceconnect.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //Service destroy method
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
