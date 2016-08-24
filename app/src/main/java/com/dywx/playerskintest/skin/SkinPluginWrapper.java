package com.dywx.playerskintest.skin;

/**
 * Created by xgao on 24/8/2016.
 */
public class SkinPluginWrapper {

  private String mPath;
  private String mPakcageName;

  public SkinPluginWrapper(String mPath, String mPakcageName) {
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
