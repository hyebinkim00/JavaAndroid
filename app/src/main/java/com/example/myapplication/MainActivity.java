package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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

    String[] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("key1","value2");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                intent.putExtra("json",jsonObject.toString());
                startActivity(intent);


                break;

        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "tt", Toast.LENGTH_SHORT).show();

        return false;

    }


}