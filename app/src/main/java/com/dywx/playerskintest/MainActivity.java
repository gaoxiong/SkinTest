package com.dywx.playerskintest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {

  Context context;

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

        SkinEngine.getInstances().init(MyApplication.getAppResources());
        SkinEngine.getInstances().addResource(MyApplication.getAppResources(), R.color.skin_text_color);
        SkinEngine.getInstances().run();

        reloadDrawable();
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
//        Snackbar.make(view, appName, Snackbar.LENGTH_LONG)
//          .setAction("Action", null).show();




//        PackageManager pm = getPackageManager();
//        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
//        String sourceDir = "";
//        for (PackageInfo info : packageInfos) {
//          //得到当前apk的包名
//          String pkgName = info.packageName;
//          //得到当前apk的sharedUserId
//          String shareUesrId = info.sharedUserId;
//          //判断这个apk是否是我们应用程序的插件
//          if (shareUesrId != null && shareUesrId.equals("com.dywx.playerskintest") && !pkgName.equals(context.getPackageName())) {
//            String label = pm.getApplicationLabel(info.applicationInfo).toString();//得到插件apk的名称
//            sourceDir = info.applicationInfo.sourceDir;
//          }
//        }
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
}
