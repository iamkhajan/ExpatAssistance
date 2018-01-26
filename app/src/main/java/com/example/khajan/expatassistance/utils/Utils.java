package com.example.khajan.expatassistance.utils;

import android.content.Context;

import com.example.khajan.expatassistance.model.explore.ExploreMainData;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Khajan on 11/16/17.
 */

public class Utils {

    public static ExploreMainData loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("explore_mock_response");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json!=null?new Gson().fromJson(json,ExploreMainData.class):null;
    }
}
