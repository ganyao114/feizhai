package net.swiftos.feizhai.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ganyao on 2016/11/7.
 */

public class NetworkUtil {
    public static boolean checkNetWorkStatus(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
