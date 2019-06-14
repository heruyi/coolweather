package com.example.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
//    7304050204b4414b926419cfa4d4b85c
    public static void sendOkHttpRequest(String address ,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(address).build();
        client.newCall(req).enqueue(callback);
    }
}
