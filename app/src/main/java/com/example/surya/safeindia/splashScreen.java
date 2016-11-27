package com.example.surya.safeindia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class splashScreen extends AppCompatActivity {

    public static int SPLASH_TIME_OUT=3000;
  //  public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    private static boolean Access=false;
   // public static SharedPreferences sharedPreferences;
    public SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.preferences=getApplicationContext().getSharedPreferences("FirstLogin",Context.MODE_PRIVATE);

       if(!preferences.contains("FirstLogin")) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              //  if (preferences.getBoolean("FirstSignIN", true)) {
                    Intent intent = new Intent(splashScreen.this, SignInActivity.class);
                    //  intent.putExtra("SharedPref",sharedPreferences);
                    splashScreen.this.startActivity(intent);
                    splashScreen.this.finish();
                }
            },SPLASH_TIME_OUT);
        }
        else {
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {

                   Intent intent = new Intent(splashScreen.this,MapDrawer.class);
                   splashScreen.this.startActivity(intent);
                   splashScreen.this.finish();
               }
           },SPLASH_TIME_OUT);

        }
    }
//    public static void setDefault(String Key,boolean value,Context context){
//        //sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putBoolean(Key,value);
//        editor.commit();
//    }
//
//    public static boolean getDefault(String key,Context context){
//        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
//        return sharedPreferences.getBoolean(key,false);
//    }
}
