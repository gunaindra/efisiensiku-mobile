package com.busefisensi.efisiensiku.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SpUser {


    private static final String PREFS_NAME = "sharedpreferencesuser";

    static SharedPreferences sp;
    static SharedPreferences.Editor prefEditor = null;

//    private static Context mContext = null;
//    public static User instance = null;

    public SpUser (Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setNamaDepan(String namaDepan){
        sp.edit().putString("namaDepan", namaDepan).apply();
    }

    public String getNamaDepan() {
        String namaDepan = sp.getString("namaDepan", "");
        return namaDepan;
    }

    public void setNamaBelakang(String namaBelakang){
        sp.edit().putString("namaBelakang", namaBelakang).apply();
    }

    public String getNamaBelakang(){
        String namaBelakang = sp.getString("namaBelakang", "");
        return namaBelakang;
    }

    public void setEmail(String email){
        sp.edit().putString("email", email).apply();
    }

    public String getEmail(){
        String email = sp.getString("email", "");
        return email;
    }

    public void setHandphone(String handphone){
        sp.edit().putString("handphone", handphone).apply();
    }

    public String getHandphone(){
        String handphone = sp.getString("handphone", "");
        return handphone;
    }

    public void setIsLogin(boolean value){
        sp.edit().putBoolean("isLogin", value).apply();
    }

    public Boolean getIsLogin(){
        Boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

}
