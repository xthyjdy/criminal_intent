package vch.proj.classes;

import android.util.Log;

public class VchHelper {
    public static void l(String data) { Log.e("my_log", data); }
    public static void l(int data) { Log.e("my_log", String.valueOf(data)); }
    public static void l(Long data) { Log.e("my_log", String.valueOf(data)); }
    public static void l(double data) { Log.e("my_log", String.valueOf(data)); }
    public static void l(Object... data) {
        for (Object object : data) {
            Log.e("my_log", object.toString());
        }
    }
}