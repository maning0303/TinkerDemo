package com.maning.tinkerdemo.tinker;

import android.content.Context;

import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

/**
 * @author : maning
 * @desc :  初始化等核心方法
 */
public class TinkerManager {


    private static boolean isInstalled = false;

    private static ApplicationLike mAppLike;


    public static void installTinker(ApplicationLike applicationLike) {
        mAppLike = applicationLike;
        if (isInstalled) {
            return;
        }
        LoadReporter loadReporter = new DefaultLoadReporter(mAppLike.getApplication());
        PatchReporter patchReporter = new DefaultPatchReporter(mAppLike.getApplication());
        PatchListener patchListener = new DefaultPatchListener(mAppLike.getApplication());
        AbstractPatch upgradePatchProcessor = new UpgradePatch();
        TinkerInstaller.install(mAppLike,
                loadReporter, patchReporter, patchListener,
                TinkerResultService.class, upgradePatchProcessor);

        isInstalled = true;
    }

    public static void loadPatch(String path) {
        if (Tinker.isTinkerInstalled()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    public static void killAllOtherProcess() {
        ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
    }


    public static Context getApplicationContext() {
        if (mAppLike != null) {
            return mAppLike.getApplication().getApplicationContext();
        }
        return null;
    }


}
