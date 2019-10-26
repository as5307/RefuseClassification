package com.umeng.soexample.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SharedPreferencesHelper {
    private static SharedPreferences sharedPreferences;

    private static SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /*
   保存数据
   */

    public static void put(String key, Object o) {
        if (o instanceof String) {
            editor.putString(key, (String) o);
        }else if (o instanceof  Integer){
            editor.putInt(key, (Integer) o);
        }else if (o instanceof  Boolean){
            editor.putBoolean(key, (Boolean) o);
        }
        editor.apply();
    }

    /*
    获取数据
    */

    public static Object get(String key, Object o){
        if (o instanceof String) {
            return sharedPreferences.getString(key, (String) o);
        }else if (o instanceof  Integer){
            return sharedPreferences.getInt(key, (Integer) o);
        }else if (o instanceof  Boolean){
            return sharedPreferences.getBoolean(key, (Boolean) o);
        }else {
            return sharedPreferences.getString(key, null);
        }
    }


    /*
    清除所有数据
    */

    public static void clean(){
        editor.clear();
        editor.apply();
    }
}