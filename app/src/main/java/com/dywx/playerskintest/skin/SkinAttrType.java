package com.dywx.playerskintest.skin;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xgao on 24/8/2016.
 */
public enum SkinAttrType
{
  BACKGROUD("background")
  {
    @Override
    public void apply(View view, String resName)
    {
      Drawable drawable = getResourceManager().getDrawableByName(resName);
      if (drawable == null) return;
      view.setBackgroundDrawable(drawable);
    }
  },
  COLOR("textColor")
  {
    @Override
    public void apply(View view, String resName)
    {
      L.e("apply color , " + view + " , " + resName);
      ColorStateList colorlist = getResourceManager().getColorStateList(resName);
      if (colorlist == null) return;
      ((TextView) view).setTextColor(colorlist);
    }
  },
  SRC("src")
  {
    @Override
    public void apply(View view, String resName)
    {
      if (view instanceof ImageView)
      {
        Drawable drawable = getResourceManager().getDrawableByName(resName);
        if (drawable == null) return;
        ((ImageView) view).setImageDrawable(drawable);
      }

    }
  };

  String attrType;

  SkinAttrType(String attrType)
  {
    this.attrType = attrType;
  }

  public String getAttrType()
  {
    return attrType;
  }


  public abstract void apply(View view, String resName);

  public ResourceManager getResourceManager()
  {
    return SkinManager.getInstance().getResourceManager();
  }

}
