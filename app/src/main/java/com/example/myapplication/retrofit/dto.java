package com.example.myapplication.retrofit;

import com.google.gson.annotations.SerializedName;

public class dto {

    @SerializedName("response")
    String response;


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
