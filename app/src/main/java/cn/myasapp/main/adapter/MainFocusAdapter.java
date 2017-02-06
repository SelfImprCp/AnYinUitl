package cn.myasapp.main.adapter;

import android.content.Context;

import com.cp.mylibrary.adapter.ListBaseAdapter;
import com.cp.mylibrary.adapter.ViewHolder;

import cn.myasapp.main.R;
import cn.myasapp.main.bean.MainFocus;


/**
 * 主页我的关注
 *
 * @author Administrator
 */

public class MainFocusAdapter extends ListBaseAdapter<MainFocus> {


    public MainFocusAdapter(Context context) {
        mContext = context;

    }


    @Override
    public void convert(ViewHolder helper, MainFocus item, int postion) {
        helper.setText(R.id.item_focus_text, item.getName());

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_focus;
    }


}
