package com.example.lg_animal.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PetInfoSharedPreference {

    public static final String PREFERENCES_NAME = "pet_preference";
    private static final int DEFAULT_VALUE_INT = 0;
    private static final String DEFAULT_VALUE_STRING = "";
    private static int tempInt = 0;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getInt(key, DEFAULT_VALUE_INT);
    }

    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(key, DEFAULT_VALUE_STRING);
    }
}
