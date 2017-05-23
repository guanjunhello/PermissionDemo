package com.kwanidea.permissionchecher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;


/**
 * author: $USER_NAME
 * created on: 2017/4/20 0020 上午 9:53
 * description:
 */
public class PermissionUtil {
    public final static int PERMISSION_REQUEST_CODE = 0xffe;
    private Activity activity;
    private String[] permissions;
    public PermissionUtil(Activity activity) {
        this.activity = activity;
    }

    public boolean isPermissionNotAllowed(String[] permissions){
        this.permissions = permissions;
        PermissionChecker permissionChecker = new PermissionChecker(activity);
        return permissionChecker.isPremissionNotAllowed(permissions);
    }
    public boolean isPermissionNotAllowed(String permission){
        permissions = new String[1];
        permissions[0] = permission;
        PermissionChecker permissionChecker = new PermissionChecker(activity);
        return permissionChecker.isPremissionNotAllowed(permissions);
    }

    public void requestPermissions() {
        requestPermissions(PERMISSION_REQUEST_CODE);
    }
    public void requestPermissions(int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public boolean isAllPermissionsAllowed(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    public void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}
