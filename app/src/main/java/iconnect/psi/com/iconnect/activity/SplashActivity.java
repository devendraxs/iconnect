package iconnect.psi.com.iconnect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import iconnect.psi.com.iconnect.R;
import iconnect.psi.com.iconnect.constant.AppConstant;
import iconnect.psi.com.iconnect.sharedprefs.SharedPrefs;

public class SplashActivity extends AppCompatActivity {
    private String mRememberMe;
   private SharedPrefs mSharedPerferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSharedPerferences.getInstance(SplashActivity.this);
        mRememberMe = mSharedPerferences.getString(this, AppConstant.KEY_REMEMBER_ME);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRememberMe.equals("1")){
                    startActivity(new Intent(SplashActivity.this,NavigationDrawerActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }

            }
        },3000);
    }
}
