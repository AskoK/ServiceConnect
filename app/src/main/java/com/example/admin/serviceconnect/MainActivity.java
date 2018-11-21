package com.example.admin.serviceconnect;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.ServiceConnection;
import com.example.admin.serviceconnect.MyService.LocalBinder;

import android.widget.Toast;
import android.app.Service;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button bconnect, bdate, bclock;
    TextView txt;

    MyService myService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bconnect = findViewById(R.id.btnConnect);
        bdate = findViewById(R.id.btnDate);
        bclock = findViewById(R.id.btnClock);
        txt = findViewById(R.id.message);

        //Starts Service
        bconnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //If Service is running, stop it
                if(isMyServiceRunning(MyService.class)) {
                    onStop();
                    txt.setText("Disconected");
                    bconnect.setText("Connect");
                //If service is not running, Start it
                } else {
                    Intent serviceToggle = new Intent(MainActivity.this, MyService.class);
                    bindService(serviceToggle, myConnection, Context.BIND_AUTO_CREATE);
                    txt.setText("Connected");
                    bconnect.setText("Disconnect");
                }
            }
        });

        //Displays Date
        bdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isMyServiceRunning(MyService.class)) {
                    txt.setText(myService.getDate());
                }
            }
        });

        //Displays Clock
        bclock.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isMyServiceRunning(MyService.class)) {
                    txt.setText(myService.getTime());
                }
            }
        });
    }

    //Service stop
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(myConnection);
        mBound = false;
    }

    private ServiceConnection myConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            LocalBinder binder = (LocalBinder) service;
            myService = binder.getService();
            mBound = true;
        }
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }

    };

    //Check if Service is running or not
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        @SuppressLint("ServiceCast") ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if(serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
