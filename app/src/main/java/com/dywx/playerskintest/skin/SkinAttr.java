package com.dywx.playerskintest.skin;

import android.view.View;

/**
 * Created by xgao on 24/8/2016.
 */
public class SkinAttr
{
  String resName;
  SkinAttrType attrType;


  public SkinAttr(SkinAttrType attrType, String resName)
  {
    this.resName = resName;
    this.attrType = attrType;
  }

  public void apply(View view)
  {
    attrType.apply(view, resName);
  }
}
