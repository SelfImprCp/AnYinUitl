package cn.myasapp.main.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;


/**
 * Created by Jerry on 2016/6/24.
 */
public class TestDrawerLayout extends BaseActivity {


    @BindView(id = R.id.nav_view)
    private NavigationView nav_view;
    @BindView(id = R.id.drawer_layout)
    private DrawerLayout drawer_layout;


    @BindView(id = R.id.iv_title_menu, click = true)
    private ImageView iv_title_menu;


    @Override
    public void setRootView() {

        setContentView(R.layout.activity_drawaer_test);


    }

    @Override
    protected void initView() {
        super.initView();

        initDrawerLayout();
    }

    /**
     * inflateHeaderView 进来的布局要宽一些
     */
    private void initDrawerLayout() {
  nav_view.inflateHeaderView(R.layout.nav_header_main);
        View headerView = nav_view.getHeaderView(0);
//        LinearLayout viewById1 = (LinearLayout) headerView.findViewById(R.id.ll_header_bg);
//        viewById1.setBackground();
        ImageView ivAvatar = (ImageView) headerView.findViewById(R.id.iv_avatar);

        LinearLayout llNavHomepage = (LinearLayout) headerView.findViewById(R.id.ll_nav_homepage);
        LinearLayout llNavScanDownload = (LinearLayout) headerView.findViewById(R.id.ll_nav_scan_download);
        LinearLayout llNavDeedback = (LinearLayout) headerView.findViewById(R.id.ll_nav_deedback);
        LinearLayout llNavAbout = (LinearLayout) headerView.findViewById(R.id.ll_nav_about);
        llNavHomepage.setOnClickListener(this);
        llNavScanDownload.setOnClickListener(this);
        llNavDeedback.setOnClickListener(this);
        llNavAbout.setOnClickListener(this);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.iv_title_menu:

                drawer_layout.openDrawer(GravityCompat.START);
                break;

        }
    }
}
