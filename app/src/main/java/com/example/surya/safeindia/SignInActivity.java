package com.example.surya.safeindia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;
import com.example.surya.safeindia.splashScreen;

public class SignInActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "2cmsXIUANsuC3B8aS0VJp6dUY";
    private static final String TWITTER_SECRET = "SX6YmIqxULTARRY5WihaBQnJ76QnlDmXGGCOC8gNw0LPvndVbQ";

    DigitsAuthButton digitsAuthButton;



    int newHighScore=10;

    //    SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPref.edit();
//    editor.putInt(getString(R.string.), newHighScore);
//    editor.commit()

    //editor = editor.putString("FirstLogin","True");
  //  setEditor(editor);






    public static boolean Activityaccess=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        setContentView(R.layout.activity_sign_in);


      final SharedPreferences  preferences=getApplicationContext().getSharedPreferences("FirstLogin",Context.MODE_PRIVATE);
        //preferences=this.getPreferences(Context.MODE_PRIVATE);


        String defaultVale=preferences.getString("FirstLogin","No Name defined");


        if(preferences.contains(getString(R.string.FirstLogin))){
            Toast.makeText(this,defaultVale.toString(),Toast.LENGTH_LONG).show();
        }
//
//         digitsAuthButton=(DigitsAuthButton) findViewById(R.id.authButton);
//        digitsAuthButton.setCallback(((Authenticate)getApplication()).getAuthCallback());
        DigitsAuthButton digitsButton = (DigitsAuthButton) findViewById(R.id.auth_button);
        digitsButton.setCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                // TODO: associate the session userID with your user model
                Toast.makeText(getApplicationContext(), "Authentication successful for "
                        + phoneNumber, Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString(getString(R.string.FirstLogin),"True");
                editor.apply();
            }

            @Override
            public void failure(DigitsException exception) {
                Log.d("Digits", "Sign in with Digits failure", exception);
            }
        });

        if(preferences.contains("FirstLogin")){
            Intent intent=new Intent(SignInActivity.this,UserForm1.class);
            SignInActivity.this.startActivity(intent);
            SignInActivity.this.finish();
        }
     //   AuthCallback authCallback=((Authenticate) getApplication()).getAuthCallback();

//        Intent intent=new Intent(SignInActivity.this,MapActivity.class);
//        SignInActivity.this.startActivity(intent);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(Authenticate.phone!=null) {

            Intent intent = new Intent(this,MapsActivity.class);
            this.startActivity(intent);
            //splashScreen.setDefault("FirstName",true,this);
            SignInActivity.this.finish();
        }
    }
}
