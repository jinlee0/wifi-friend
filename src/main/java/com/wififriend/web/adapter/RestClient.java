package com.wififriend.web.adapter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RestClient {
    public Response get(String url) {
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            if (response.isSuccessful()) {
                return response;
            } else {
                System.out.println("error in RestClient.get(String)");
                throw new RuntimeException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
