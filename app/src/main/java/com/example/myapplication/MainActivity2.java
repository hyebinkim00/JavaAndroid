package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent intent = getIntent();

        if (intent.hasExtra("json")) {
            try {
                JSONObject mJsonObject = new JSONObject(intent.getStringExtra("json"));

                String ss = mJsonObject.getString("key1");
                Log.d("JSON",ss);


            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
 /*       Uri uri = intent.getData();

        String para = uri.getQueryParameter("pp");

        Log.d("DeepLink_Param",para);
*/
        bottomNavigationView = findViewById(R.id.bottomNavi);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new HomeFragment()).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
                        break;
                    case R.id.menu2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new LikeFragment()).commit();
                        break;
                    case R.id.menu3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new SearchFragment()).commit();
                        break;

                }

                return true;
            }
        });


        JsonArray jsonArray = new JsonArray();


        for (int i = 0; i <5 ; i++){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("obj",i);
            jsonArray.add(jsonObject);
        }


        Log.d("Json",jsonArray.toString());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "tt", Toast.LENGTH_SHORT).show();


        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new SearchFragment()).commit();


        return false;

    }




}