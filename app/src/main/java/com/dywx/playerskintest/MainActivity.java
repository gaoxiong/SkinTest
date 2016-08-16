package com.dywx.playerskintest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.changeskin.SkinManager;
import com.zhy.changeskin.base.BaseSkinActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;

public class MainActivity extends BaseSkinActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  Context context;
  Context mSkinContext;
  TextView textView;
  ArrayList<PluginWrapper> mPluginWrapperArrayList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);

    context = this;

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String appName = "";
        Class cls = null;

        int c;
        try {
          cls = mSkinContext.getClassLoader().loadClass("com.dywx.playerskincn.R");
          c = mSkinContext.getResources().getColor(getResourceIdByName(cls, "color", "skin_text_color"));
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }

//        SkinEngine.getInstances().init(MyApplication.getAppResources());
//        SkinEngine.getInstances().addResource(MyApplication.getAppResources(), getResourceIdByName(cls, "string", "skin_text_color"));
//        SkinEngine.getInstances().run();

//        reloadDrawable();
//        try {
//          Class cls = mSkinContext.getClassLoader().loadClass("com.dywx.playerskincn.R");
//          appName = mSkinContext.getResources().getString(getResourceIdByName(cls, "string", "app_name"));
//          Class cls = context.getClassLoader().loadClass("com.dywx.playerskintest.R");
//          appName = context.getResources().getString(getResourceIdByName(cls, "string", "app_name"));
//          SkinEngine.getInstances().init(mSkinContext.getResources());
//          SkinEngine.getInstances().run();
//        } catch (ClassNotFoundException e) {
//          e.printStackTrace();
//        }
        Snackbar.make(view, appName, Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();





//        SkinManager.getInstance().changeSkin(sourceDir, "com.dywx.playerskincn", new ISkinChangingCallback() {
//          @Override
//          public void onStart() {
//
//          }
//
//          @Override
//          public void onError(Exception e) {
//
//          }
//
//          @Override
//          public void onComplete() {
//
//          }
//        });
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);


    try {
      mSkinContext = MyApplication.getAppContext().createPackageContext(
        "com.dywx.playerskincn", CONTEXT_IGNORE_SECURITY | CONTEXT_INCLUDE_CODE);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    findViewById(R.id.btn_change_by_plugin).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        try {
//          Class cls = mSkinContext.getClassLoader().loadClass("com.dywx.playerskincn.R");
//          int colorValue = mSkinContext.getResources().getColor(getResourceIdByName(cls, "color", "skin_text_color"));
//          textView.setTextColor(colorValue);
//        } catch (ClassNotFoundException e) {
//          e.printStackTrace();
//        }
        PluginWrapper pluginWrapper = mPluginWrapperArrayList.get(0);
        SkinManager.getInstance().changeSkin(pluginWrapper.getPath(), pluginWrapper.getPakcageName(), null);
      }
    });

    findViewById(R.id.btn_change_by_internal).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        SkinManager.getInstance().changeSkin("green");
      }
    });

    findViewById(R.id.btn_change_reset).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        SkinManager.getInstance().removeAnySkin();
      }
    });

    findViewById(R.id.btn_goto_color_test).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ColorTestActivity.class);
        MainActivity.this.startActivity(intent);
      }
    });
    textView = (TextView) findViewById(R.id.textView);
    fetchAllPlugins("com.dywx.playerskintest");

  }

  private void reloadDrawable(){
//    mImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private int getResourceIdByName(Class clazz, String className, String name) {
    int id = 0;
    try {
      Class[] classes = clazz.getClasses();
      Class desireClass = null;

      for (int i = 0; i < classes.length; i++) {
        if (classes[i].getName().split("\\$")[1].equals(className)) {
          desireClass = classes[i];
          break;
        }
      }
      if (desireClass != null) {
        id = desireClass.getField(name).getInt(desireClass);
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    return id;
  }

  private int dynamicLoadApk(String packageName, Context pluginContext) throws Exception {
    PathClassLoader pathClassLoader = new PathClassLoader(pluginContext.getPackageResourcePath(), ClassLoader.getSystemClassLoader());

//        Class<?> clazz = pathClassLoader.loadClass(packageName + ".R$mipmap");//通过使用自身的加载器反射出mipmap类进而使用该类的功能
    //参数：1、类的全名，2、是否初始化类，3、加载时使用的类加载器
    Class<?> clazz = Class.forName(packageName + ".R$mipmap", true, pathClassLoader);
    //使用上述两种方式都可以，这里我们得到R类中的内部类mipmap，通过它得到对应的图片id，进而给我们使用
    Field field = clazz.getDeclaredField("one");
    int resourceId = field.getInt(R.mipmap.class);
    return resourceId;
  }

  private void fetchAllPlugins(String sharedUsedId) {
    PackageManager pm = getPackageManager();
    List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
    String sourceDir = "";
    for (PackageInfo info : packageInfos) {
      //得到当前apk的包名
      String pkgName = info.packageName;
      //判断这个apk是否是我们应用程序的插件
      if (info.sharedUserId != null && info.sharedUserId.equals(sharedUsedId) && !pkgName.equals(context.getPackageName())) {
        sourceDir = info.applicationInfo.dataDir;
        PluginWrapper pluginWrapper = new PluginWrapper(sourceDir, pkgName);
        if (mPluginWrapperArrayList == null) {
          mPluginWrapperArrayList = new ArrayList<>();
        }
        mPluginWrapperArrayList.add(pluginWrapper);
      }
    }
  }


}
