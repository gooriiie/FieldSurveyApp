package com.example.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class My_Shared_Preference {

    static String email = "email";
    static String password = "password";

    static public SharedPreferences getSharedPreference(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserEmailPassword(Context ctx, String userEmail, String userPassword) {
        SharedPreferences.Editor editor = getSharedPreference(ctx).edit();
        editor.putString(email, userEmail);
        editor.putString(password, userPassword);
        editor.commit();
    }

    public static String getUserEmail(Context ctx) {
        return getSharedPreference(ctx).getString(email, null);
    }

    public static String getUserPassword(Context ctx) {
        return getSharedPreference(ctx).getString(password, null);
    }

    public static void clearUser(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreference(ctx).edit();
        editor.clear();
        editor.commit();
    }

}
