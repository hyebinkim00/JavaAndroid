package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URISyntaxException;

public class WebViewActivity extends AppCompatActivity {

    WebView webView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


        webView.loadUrl("https://www.instagram.com/p/CdX6iy6hdpB");
    }


    @Override
    protected void onResume() {
        super.onResume();
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && url.startsWith("intent://")) {

                  try {
                      Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                      Intent existPackage = getPackageManager().getLaunchIntentForPackage(intent.getPackage());


                      if (existPackage != null) {
                          startActivity(intent);
                      } else {
                          Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                          marketIntent.setData(Uri.parse("market://details?id=" + intent.getPackage()));
                          startActivity(marketIntent);
                      }
                      return true;

                  } catch (URISyntaxException e) {

                      e.printStackTrace();
                      return false;

                  }

                }

                return false;
            }

        });

    }
}