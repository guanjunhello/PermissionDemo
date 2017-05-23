package com.kwanidea.permissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kwanidea.permissionchecher.PermissionSimpleDialog;
import com.kwanidea.permissionchecher.PermissionUtil;

public class MainActivity extends AppCompatActivity implements PermissionSimpleDialog.PermissionDialogListener {

    PermissionUtil permissionUtil;

    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private PermissionSimpleDialog permissionSimpleDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        permissionUtil = new PermissionUtil(this);
        if(permissionUtil.isPermissionNotAllowed(permissions)){
            permissionUtil.requestPermissions();
        }else{
            // do sth
            toasty("存储权限已允许");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!permissionUtil.isPermissionNotAllowed(permissions)){
            //do sth
            if(permissionSimpleDialog!=null)
                permissionSimpleDialog.dismiss();
            toasty("存储权限已允许");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(permissionUtil.isAllPermissionsAllowed(grantResults)){
            //do sth
            toasty("存储权限已允许");
        }else{
            if(permissionSimpleDialog==null){
                permissionSimpleDialog = new PermissionSimpleDialog(this, "权限申请", "应用需要获取存储权限\n设置-权限-存储", permissionUtil);
                permissionSimpleDialog.setPermissionDialogListener(this);
            }
            permissionSimpleDialog.show();
        }

    }

    @Override
    public void onPermissionSetting() {
    }

    @Override
    public void onPermissioncancel() {
        if(permissionSimpleDialog!=null)
            permissionSimpleDialog.dismiss();
        finish();
    }

    private void toasty(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }


}
