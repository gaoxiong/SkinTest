package com.dywx.playerskintest.skin;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xgao on 24/8/2016.
 */
public class SkinAttrSupport {
  public static List<SkinAttr> getSkinAttrs(AttributeSet attrs, Context context)
  {
    List<SkinAttr> skinAttrs = new ArrayList<SkinAttr>();
    SkinAttr skinAttr = null;
    for (int i = 0; i < attrs.getAttributeCount(); i++)
    {
      String attrName = attrs.getAttributeName(i);
      String attrValue = attrs.getAttributeValue(i);

      SkinAttrType attrType = getSupprotAttrType(attrName);
      if (attrType == null) continue;

      if (attrValue.startsWith("@"))
      {
        int id = Integer.parseInt(attrValue.substring(1));
        String entryName = context.getResources().getResourceEntryName(id);

        L.e("entryName = " + entryName);
//        if (entryName.startsWith(SkinConfig.ATTR_PREFIX))
        {
          skinAttr = new SkinAttr(attrType, entryName);
          skinAttrs.add(skinAttr);
        }
      }
    }
    return skinAttrs;

  }

  private static SkinAttrType getSupprotAttrType(String attrName)
  {
    for (SkinAttrType attrType : SkinAttrType.values())
    {
      if (attrType.getAttrType().equals(attrName))
        return attrType;
    }
    return null;
  }
}
