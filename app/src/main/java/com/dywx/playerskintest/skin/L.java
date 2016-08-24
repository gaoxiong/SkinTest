package com.dywx.playerskintest.skin;

import android.util.Log;

/**
 * Created by xgao on 24/8/2016.
 */
public class L
{
  private static final String TAG = "Skin";
  private static boolean debug = true;

  public static void e(String msg)
  {
    if (debug)
      Log.e(TAG, msg);
  }

}
