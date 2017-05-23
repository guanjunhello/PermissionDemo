package com.kwanidea.permissionchecher;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


/**
 * author: $USER_NAME
 * created on: 2017/4/20 0020 上午 10:17
 * description:
 */
public class PermissionSimpleDialog extends Dialog {
    private String title;
    private String message;
    private TextView tv_permission_dialog_title, tv_permission_dialog_message;
    private TextView tv_permission_dialog_cancel, tv_permission_dialog_setting;

    private PermissionDialogListener permissionDialogListener;
    private PermissionUtil permissionUtil;

    public PermissionSimpleDialog(Context context, PermissionUtil permissionUtil) {
        super(context, R.style.myDialog);
        this.permissionUtil = permissionUtil;
    }
    public PermissionSimpleDialog(Context context, String title, String message, PermissionUtil permissionUtil) {
        super(context);
        this.title = title;
        this.message = message;
        this.permissionUtil = permissionUtil;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_permission_simple);
        tv_permission_dialog_title = (TextView) findViewById(R.id.tv_permission_dialog_title);
        if(!TextUtils.isEmpty(title))
            tv_permission_dialog_title.setText(title);
        tv_permission_dialog_message = (TextView) findViewById(R.id.tv_permission_dialog_message);
        if(!TextUtils.isEmpty(message))
            tv_permission_dialog_message.setText(message);
        tv_permission_dialog_cancel = (TextView) findViewById(R.id.tv_permission_dialog_cancel);
        tv_permission_dialog_setting = (TextView) findViewById(R.id.tv_permission_dialog_setting);
        tv_permission_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(permissionDialogListener!=null)permissionDialogListener.onPermissioncancel();
            }
        });
        tv_permission_dialog_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(permissionDialogListener!=null)permissionDialogListener.onPermissionSetting();
                if(permissionUtil!=null)permissionUtil.openAppSettings();
            }
        });

        setCanceledOnTouchOutside(false);
    }



    public void setPermissionDialogListener(PermissionDialogListener permissionDialogListener) {
        this.permissionDialogListener = permissionDialogListener;
    }


    public interface PermissionDialogListener{
        public abstract void onPermissionSetting();
        public abstract void onPermissioncancel();
    }
}
