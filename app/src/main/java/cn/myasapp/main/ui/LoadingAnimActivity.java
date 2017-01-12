package cn.myasapp.main.ui;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.dialog.DialogHelper;
import com.cp.mylibrary.dialog.ShareDialog;
import com.cp.mylibrary.dialog.WaitDialog;
import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.StatusBarUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2016/6/28.
 * <p>
 * 测试对话框
 */
public class LoadingAnimActivity extends BaseActivity {

    @BindView(id = R.id.loading_image_1)
    private ImageView loading_image_1;

    @BindView(id = R.id.loading_image_2)
    private ImageView loading_image_2;
    // 动画
    private AnimationDrawable mAnimationDrawable;

    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_loading_anim);


    }

    @Override
    protected void initView() {
        super.initView();

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) loading_image_1.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }


        // 加载动画
        mAnimationDrawable = (AnimationDrawable) loading_image_2.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }
}
