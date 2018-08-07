package com.maning.tinkerdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.maning.tinkerdemo.tinker.TinkerManager;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_show = findViewById(R.id.tv_show);
        tv_show.setText("我是一个bug");

        if (!PermissionUtils.isGrantSDCardReadPermission(this)) {
            PermissionUtils.requestSDCardReadPermission(this, 10010);
        }
    }

    public void btnLoadPath(View view) {
        //创建路径
        String patchDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "tinker" + File.separator;
        File file = new File(patchDir);
        if (file.exists()) {
            file.mkdirs();
        }
        TinkerManager.loadPatch(patchDir.concat("patch.apk"));
    }

    public void btnKillSelf(View view) {
        TinkerManager.killAllOtherProcess();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
