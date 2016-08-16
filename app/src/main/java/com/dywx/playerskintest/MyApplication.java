package com.dywx.playerskintest;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.zhy.changeskin.SkinManager;

/**
 * Created by xgao on 12/8/2016.
 */
public class MyApplication extends Application {
  private static MyApplication instance;
  private static Context mSkinContext;

  @Override
  public void onCreate() {
    super.onCreate();

    instance = this;
    SkinManager.getInstance().init(this);
    try {
      mSkinContext= createPackageContext("com.dywx.playerskincn",
        Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    SkinEngine.getInstances().init(getResources());
  }

  /**
   * @return the main context of the Application
   */
  public static Context getAppContext()
  {
    return instance;
  }

  /**
   * @return the main context of the Application
   */
  public static Context getPluginContext()
  {
    return mSkinContext;
  }

  /**
   * @return the main resources from the Application
   */
  public static Resources getAppResources()
  {
//    return mSkinContext.getResources();
    return instance.getResources();
  }
}
