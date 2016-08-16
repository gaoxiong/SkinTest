package com.dywx.playerskintest;

/**
 * Created by xgao on 16/8/2016.
 */
public class PluginWrapper {

  private String mPath;
  private String mPakcageName;

  public PluginWrapper(String mPath, String mPakcageName) {

    this.mPath = mPath;
    this.mPakcageName = mPakcageName;
  }


  public String getPath() {
    return mPath;
  }

  public void setPath(String mPath) {
    this.mPath = mPath;
  }

  public String getPakcageName() {
    return mPakcageName;
  }

  public void setPakcageName(String mPakcageName) {
    this.mPakcageName = mPakcageName;
  }
}
