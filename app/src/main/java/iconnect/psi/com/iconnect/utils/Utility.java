package iconnect.psi.com.iconnect.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;

public class Utility {

    @SuppressLint("MissingPermission")
    public static boolean isNetworkConnected(Context mContext){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
