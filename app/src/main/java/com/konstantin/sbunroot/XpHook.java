package com.konstantin.sbunroot;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by konstantin on 08.09.18.
 */

public class XpHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(!lpparam.packageName.equals("ru.sberbankmobile"))return;
        XposedBridge.log("We in sberbank");
        try{
            String clname="com.kavsdk.rootdetector.RootDetector";

            XposedHelpers.findAndHookMethod(clname, lpparam.classLoader, "checkRoot", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return false;
                }
            });

        }catch (Exception e){XposedBridge.log(e);}
    }
}
