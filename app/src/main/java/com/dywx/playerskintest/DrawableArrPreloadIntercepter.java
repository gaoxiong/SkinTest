package com.dywx.playerskintest;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build;
import android.util.LongSparseArray;
import android.util.TypedValue;

/**
 * Created by xgao on 15/8/2016.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class DrawableArrPreloadIntercepter extends LongSparseArray<ConstantState> {

  public LongSparseArray<ConstantState>[] mOldPreloadCache;
  private LongSparseArray<Integer> mKeyToResourcesId = new LongSparseArray<Integer>();
  private SkinEngine mSkinEngine;

  public DrawableArrPreloadIntercepter(SkinEngine skinEngine, LongSparseArray<ConstantState>[] preloadCache) {
    mSkinEngine = skinEngine;
    mOldPreloadCache = preloadCache.clone();
  }

  public void add(Resources resources, int resId) {
    TypedValue value = new TypedValue();
    resources.getValue(resId, value, true);
    if ((value.string != null)&& !(value.string.toString().endsWith(".xml"))) {
      final long key = (((long) value.assetCookie) << 32) | value.data;
      mKeyToResourcesId.put(key, Integer.valueOf(resId));
    }
  }

  @Override
  public ConstantState get(long key) {
    if ((Integer) mKeyToResourcesId.get(key) == null) {
      return (ConstantState) mOldPreloadCache[0].get(key);
    }
    return mSkinEngine.loadConstantState(((Integer) this.mKeyToResourcesId.get(key)).intValue());
  }

  @Override
  public void put(long key, ConstantState value) {
    super.put(key, value);
  }

  @Override
  public int size() {
    return super.size();
  }

  @Override
  public ConstantState get(long key, ConstantState valueIfKeyNotFound) {
    return super.get(key, valueIfKeyNotFound);
  }

}
