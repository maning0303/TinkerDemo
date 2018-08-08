package com.maning.tinkerdemo.tinker;

import android.content.Context;

import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author : maning
 * @desc :  1.较验patch文件是否合法  2.启动Service去安装patch文件
 */
public class TinkerPatchListener extends DefaultPatchListener {

    private String currentMD5;

    public void setCurrentMD5(String md5Value) {

        this.currentMD5 = md5Value;
    }

    public TinkerPatchListener(Context context) {
        super(context);
    }

    @Override
    protected int patchCheck(String path, String patchMd5) {
        //patch文件ms5较验
        if (!currentMD5.equals(patchMd5)) {
            return ShareConstants.ERROR_PATCH_DISABLE;
        }
        return super.patchCheck(path, patchMd5);
    }
}
