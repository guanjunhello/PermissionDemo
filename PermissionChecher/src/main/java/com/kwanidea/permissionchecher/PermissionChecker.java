package com.kwanidea.permissionchecher;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * author: $USER_NAME
 * created on: 2017/4/20 0020 上午 9:50
 * description:
 */
class PermissionChecker {

    private final Context mContext;

    public PermissionChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    public boolean isPremissionNotAllowed(String[] permissions){
        for (String permission : permissions) {
            if (isPremissionNotAllowed(permission)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPremissionNotAllowed(String permission){
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
    }
}
