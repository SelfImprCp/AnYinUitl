package com.cp.mylibrary.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cp.mylibrary.R;
import com.cp.mylibrary.app.MyBaseApp;

import com.cp.mylibrary.event.BaseEvent;
import com.cp.mylibrary.utils.ActivityManagerUtil;
import com.cp.mylibrary.utils.AppUtils;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.ShowToastUtil;

import com.cp.mylibrary.utils.StatusBarUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;


import org.kymjs.kjframe.KJActivity;



/**
 * Created by Jerry on 2016/7/6.
 */
public class MyBaseActivity extends KJActivity {

   public Context mContext;
    //为状态栏着色
  public SystemBarTintManager tintManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActivityManagerUtil.getInstance().pushActivty(this);



       mContext = MyBaseApp.getInstance();



        super.onCreate(savedInstanceState);


        //这里注意下 因为在评论区发现有网友调用setRootViewFitsSystemWindows 里面 winContent.getChildCount()=0 导致代码无法继续
        //是因为你需要在setContentView之后才可以调用 setRootViewFitsSystemWindows

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
 StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,   0xffFFFFFF );
        }






        //     android:fitsSystemWindows="true"
        //  android:clipToPadding="false"
        //透明状态栏
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        //只对api19以上版本有效
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }
//        //为状态栏着色
//        tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//
//
//        tintManager.setStatusBarTintResource(R.color.bgColor_overlay);
//
//


        AppUtils. getPromission(this);



    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }





    @Override
    protected void onDestroy() {

        ((MyBaseApp) this.getApplication()).getActivityManager().finishActivity(
                this);
        mContext = null;

        super.onDestroy();
   //     EventBus.getDefault().unregister(this);
    }




    @Override
    public void initWidget() {
        super.initWidget();

        initView();
    }

    @Override
    public void initData() {
        super.initData();

        initMyData();
    }

    /**
     * 子类复写，初始化UI
     */
    protected void initView() {
        if (!NetWorkUtil.hasInternetConnected(this)) {

            ShowToastUtil.showToast(this,"请检查网络");
        }

    }


    @Override
    public void setRootView() {

    }

//
//    /**
//     *  初始化字体
//     */
//    protected void initFont() {
//
//    }


    /**
     * 子类复写，初始化数据
     */
    public void initMyData() {
    }


    @Override
    protected void onStart() {

        super.onStart();
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



}
