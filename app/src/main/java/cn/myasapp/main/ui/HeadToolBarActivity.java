package cn.myasapp.main.ui;

import android.graphics.Color;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cp.mylibrary.custom.CPScrollView;
import com.cp.mylibrary.custom.ListViewForScrollView;
import com.cp.mylibrary.utils.StatusBarUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;

/**
 * Created by Jerry on 2017/1/6.
 */

public class HeadToolBarActivity extends BaseActivity implements CPScrollView.ScrollViewListener {

    @BindView(id = R.id.textview)
    private TextView textview;

    @BindView(id = R.id.imageview)
    private ImageView imageview;

    @BindView(id = R.id.listview)

    private ListViewForScrollView listView;

    @BindView(id = R.id.scrollview)
    private CPScrollView scrollview;

    private int imageHeight;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_headtoolbar);
    }

    @Override
    protected void initView() {
        super.initView();

        initListeners();

        initDataS();

    }


    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = imageview.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageview.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = imageview.getHeight();

                scrollview.setScrollViewListener(HeadToolBarActivity.this);
            }
        });
    }


    private void initDataS() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HeadToolBarActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        listView.setAdapter(adapter);
    }


    @Override
    public void onScrollChanged(CPScrollView scrollView, int x, int y, int oldx, int oldy) {
        // TODO Auto-generated method stub
        // Log.i("TAG", "y--->" + y + "    height-->" + height);
        if (y <= 0) {
            textview.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
            StatusBarUtil.transparentStatusBar(HeadToolBarActivity.this);
            StatusBarUtil.setColor(HeadToolBarActivity.this, Color.argb((int) 255, 227, 29, 26));
        } else if (y > 0 && y <= imageHeight) {
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            textview.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));

            StatusBarUtil.transparentStatusBar(HeadToolBarActivity.this);
            StatusBarUtil.setColor(HeadToolBarActivity.this, Color.argb((int) alpha, 227, 29, 26));

        } else {
            textview.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
            StatusBarUtil.transparentStatusBar(HeadToolBarActivity.this);

            StatusBarUtil.setColor(HeadToolBarActivity.this, Color.argb((int) 255, 227, 29, 26));

        }
    }
}

