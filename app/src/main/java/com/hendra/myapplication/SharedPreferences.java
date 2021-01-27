package com.hendra.myapplication;

import android.content.Context;
import android.util.Log;

import com.hendra.myapplication.model.Pahlawan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedPreferences {
    private static final String PREF_FILE = "com.hendra.myapplication.DATA";
    private static final String TRANS_KEY = "TRANS";
    private static final String USER_NAME_KEY = "USERNAME";

    private static android.content.SharedPreferences getSharedPref(Context ctx) {
        android.content.SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE);
        return sharedPref;
    }

    /*
       Ambil data username dari Shared Preference
    */
    public static String getUserName(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY, "NO NAME");
    }

    /*
        Simpan data username ke Shared Preference
     */
    public static void saveUserName(Context ctx, String userName) {
        Log.d("SH PREF", "Change user name to" + userName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY, userName).apply();
    }

    public static List<Pahlawan> getAllLPahlawan(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY, null);
        List<Pahlawan> trs = new ArrayList<Pahlawan>();
        if (jsonString != null) {
            Log.d("SH PREF", "json string is:" + jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    trs.add(Pahlawan.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(trs, (Pahlawan, t1) -> {
            return Pahlawan.getId().compareTo(t1.getId());
        });
        return trs;
    }


    private static void saveAllPahlawan(Context ctx, List<Pahlawan> trs) {
        List<JSONObject> jsonTrs = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();
        for (Pahlawan tr : trs) {
            jsonArr.put(tr.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY, jsonArr.toString()).apply();
    }


    public static void addPahlawan(Context ctx, Pahlawan tr) {
        List<Pahlawan> trs = getAllLPahlawan(ctx);
        trs.add(tr);
        saveAllPahlawan(ctx, trs);

    }
}
