package iconnect.psi.com.iconnect.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    private static String PREF_NAME = "APP_PREF";

    public static void getInstance(Context mContext)
    {
        if(mSharedPreferences == null)
        {
            mSharedPreferences = mContext.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }
    public static void putInt(Context mContext, String key, int value)
    {
        getInstance(mContext);
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }
    public static int getInt(Context mContext, String key)
    {
        getInstance(mContext);
        return mSharedPreferences.getInt(key, 0);
    }
    public static void putString(Context mContext, String key, String value)
    {
        getInstance(mContext);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }
    public static String getString(Context mContext, String key){

        getInstance(mContext);
        return mSharedPreferences.getString(key, "");
    }
    public void setLogin(boolean isLogin){
        this.mEditor=mSharedPreferences.edit();
        mEditor.putBoolean("session",isLogin);
        mEditor.commit();
    }
    public Boolean isLogin(){
        return  mSharedPreferences.getBoolean("session",false);
    }

}
