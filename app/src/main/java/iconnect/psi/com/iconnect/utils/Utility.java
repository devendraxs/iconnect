package iconnect.psi.com.iconnect.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utility {

    public static boolean isNetworkConnected(Context mContext){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
