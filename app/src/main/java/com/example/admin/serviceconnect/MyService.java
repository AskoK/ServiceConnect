package com.example.admin.serviceconnect;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.serviceconnect.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {

    private final IBinder mBinder = new LocalBinder();


    public MyService() {
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }

    //Method to display Date
    public String getDate() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return mDateFormat.format(new Date());
    }

    //Method to display Time
    public String getTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat("HH:mm:ss");
        return mDateFormat.format(new Date());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
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
