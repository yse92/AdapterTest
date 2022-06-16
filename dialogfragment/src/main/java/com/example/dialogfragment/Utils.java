package com.example.dialogfragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils {
    private static Gson gson;

    public static Gson getGsonParser() {
        if(null == gson) {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }
    public static Phone getPhoneFromJson(String json) {
        return gson.fromJson(json, Phone.class);
    }
}
