package com.example.chaitanya.radhakrishna.Util;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref  {

    public static final String MyPREFERENCES = "MyPrefs" ;
    private static final String IS_ALREADY_LOGIN = "is login";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    public static final String PASS = "passkey";
    public static final String NAME = "name";

    public static final String ISREAGISTERED = "isregistered";

    private static final String REMEMBER_LOGIN = "remamber login";

    SharedPreferences sharedpreferences;
    private SharedPreferences preferences;
    private static SharedPref mPreference;
    private SharedPreferences.Editor editor;


    public SharedPref(Context context) {
        preferences = context.getSharedPreferences(MyPREFERENCES, 0);
        editor = preferences.edit();
    }

    public static SharedPref getInstance(Context context) {
        if (mPreference == null) {
            mPreference = new SharedPref(context);
        }
        return mPreference;
    }

    public static String getNAME() {
        return NAME;
    }

    public void setIsLogined(boolean status) {
        editor.putBoolean(IS_ALREADY_LOGIN, status).commit();
    }

    public boolean getIsLogined() {
        return preferences.getBoolean(IS_ALREADY_LOGIN, false);
    }


    public void setUsername(String toDate) {
        editor.putString(Name, toDate).commit();
    }

    public String getUsername() {
        return preferences.getString(Name, "");
    }

    public void setPassword(String adminPhoto) {
        editor.putString(PASS, adminPhoto).commit();
    }

    public String getPassword() {
        return preferences.getString(PASS, "");
    }

    public void setRememberLogin(boolean status) {
        editor.putBoolean(REMEMBER_LOGIN, status).commit();
    }

    public boolean getRememberLogin() {
        return preferences.getBoolean(REMEMBER_LOGIN, false);
    }

    public static String getISREAGISTERED() {
        return ISREAGISTERED;
    }
}
