package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RemoteViews;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.retrofit.RetrofitConnection;
import com.example.myapplication.retrofit.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Switch swh;

    Button button;

    RemoteViews remoteViews2;

    String[] permissions = {Manifest.permission.RECORD_AUDIO};


    // Channel에 대한 id 생성 : Channel을 구부하기 위한 ID 이다.
    private static final String CHANNEL_ID = "NOTI_CHANNEL";
    // Channel을 생성 및 전달해 줄 수 있는 Manager 생성
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder builder;


    // Notivication에 대한 ID 생성
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toast.makeText(getApplicationContext(),"ToastToastToastToastToastdddddddddd",Toast.LENGTH_SHORT).show();

        checkPermission();

/*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
        }
*/

        swh = findViewById(R.id.modeSwitch);
        swh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });


        button = findViewById(R.id.btn);

        button.setOnClickListener(this);


        //notification manager 생성
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 기기(device)의 SDK 버전 확인 ( SDK 26 버전 이상인지 - VERSION_CODES.O = 26)
        if(android.os.Build.VERSION.SDK_INT
                >= android.os.Build.VERSION_CODES.O){
            //Channel 정의 생성자( construct 이용 )
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"Test Notification",mNotificationManager.IMPORTANCE_DEFAULT);
            //Channel에 대한 기본 설정
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            // Manager을 이용하여 Channel 생성
            mNotificationManager.createNotificationChannel(notificationChannel);


        }


        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(),R.layout.remoteview);

        remoteViews2 = new RemoteViews(getApplicationContext().getPackageName(),R.layout.remoteview2);

/*        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img);*/

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//               .setCustomContentView(remoteViews)
                .setCustomHeadsUpContentView(remoteViews)
                .setCustomBigContentView(remoteViews2)
                .setContentTitle("타이틀")
                .setContentText("텍스트")

                .setSmallIcon(R.drawable.ic_baseline_home_24)
             /*   .setLargeIcon(bitmap)*/
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());




    }




    private void checkPermission(){

        ArrayList<String> deny = new ArrayList<>();

        for (int i = 0 ; i < permissions.length ; i++){

            if (ContextCompat.checkSelfPermission(this,permissions[i]) != PackageManager.PERMISSION_GRANTED){
                deny.add(permissions[i]);
            }

        }


        if (deny.size()!=0){
            for (int i = 0 ; i < deny.size() ; i++){
                ActivityCompat.requestPermissions(this,new String[]{deny.get(i)},0);
            }

        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean userAllowed = true;
        for (final int result : grantResults) {
            /*userAllowed == result == PackageManager.PERMISSION_GRANTED;*/
            userAllowed &= result == PackageManager.PERMISSION_GRANTED;
        }

        if (userAllowed){
            Toast.makeText(this,"권한 획득 성공",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"권한 획득 실패",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn :
                mNotificationManager.notify(NOTIFICATION_ID,builder.build());
           break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "tt", Toast.LENGTH_SHORT).show();

        return false;

    }


}