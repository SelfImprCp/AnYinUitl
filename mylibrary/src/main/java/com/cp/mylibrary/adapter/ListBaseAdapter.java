package com.cp.mylibrary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.cp.mylibrary.R;
import com.cp.mylibrary.bean.MyEntity;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.NetWorkUtil;
import com.cp.mylibrary.utils.StringUtils;
import com.nineoldandroids.view.ViewPropertyAnimator;

import org.kymjs.kjframe.widget.ViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 2016/6/17.
 * <p/>
 *
 *   带刷新加载更多的listView 要继承的
 * 封装好的类,正常情况下无需修改,子类继承只需要复写两个方法就可以了
 */
public abstract class ListBaseAdapter<T extends MyEntity> extends BaseAdapter {
   //没有数据 了，已加载全部
   public static final int STATE_EMPTY_ITEM = 0;
    //加载更多
    public static final int STATE_LOAD_MORE = 1;
    //没有更多了，已加载全部
    public static final int STATE_NO_MORE = 2;
    //没有任何数据
    public static final int STATE_NO_DATA = 3;
    public static final int STATE_LESS_ONE_PAGE = 4;
    //网络错误
    public static final int STATE_NETWORK_ERROR = 5;
//    public static final int STATE_OTHER = 6;

    protected int state = STATE_LESS_ONE_PAGE;

    protected int _loadmoreText;
    protected int _loadFinishText;
    protected int _noDateText;
    protected int mScreenWidth;




    private LayoutInflater mInflater;

    protected Context mContext;

    public ListBaseAdapter(Context context) {
        mContext = context;
    }

    protected LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null) {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return mInflater;
    }

    public void setScreenWidth(int width) {
        mScreenWidth = width;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    protected ArrayList<T> mDatas = new ArrayList<T>();

    public ListBaseAdapter() {
        _loadmoreText = R.string.loading;
        _loadFinishText = R.string.loading_no_more;
        _noDateText = R.string.error_view_no_data;
    }

    @Override
    public int getCount() {
        switch (getState()) {
            case STATE_EMPTY_ITEM:
                return getDataSizePlus1();
            case STATE_NETWORK_ERROR:
            case STATE_LOAD_MORE:
                return getDataSizePlus1();
            case STATE_NO_DATA:
                return 1;
            case STATE_NO_MORE:
                return getDataSizePlus1();
            case STATE_LESS_ONE_PAGE:
                return getDataSize();
            default:
                break;
        }
        return getDataSize();
    }

    public int getDataSizePlus1() {
        if (hasFooterView()) {
            return getDataSize() + 1;
        }
        return getDataSize();
    }

    public int getDataSize() {
        return mDatas.size();
    }

    @Override
    public T getItem(int arg0) {
        if (mDatas.size() > arg0) {
            return mDatas.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public void setData(ArrayList<T> data) {
        mDatas = data;
        notifyDataSetChanged();
    }

    public ArrayList<T> getData() {
        return mDatas == null ? (mDatas = new ArrayList<T>()) : mDatas;
    }

    public void addData(List<T> data) {
        if (mDatas != null && data != null && !data.isEmpty()) {
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addItem(T obj) {
        if (mDatas != null) {
            mDatas.add(obj);
        }
        notifyDataSetChanged();
    }

    public void addItem(int pos, T obj) {
        if (mDatas != null) {
            mDatas.add(pos, obj);
        }
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        mDatas.remove(obj);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void setLoadmoreText(int loadmoreText) {
        _loadmoreText = loadmoreText;
    }

    public void setLoadFinishText(int loadFinishText) {
        _loadFinishText = loadFinishText;
    }

    public void setNoDataText(int noDataText) {
        _noDateText = noDataText;
    }

    protected boolean loadMoreHasBg() {
        return true;
    }





    /**
     *  子类必须复写，
     * @param holder
     * @param t
     */
    public abstract void convert(ViewHolder holder, T t,int postion);

    /**
     *
     * @return
     */
    public abstract int  getItemLayoutId();




    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == getCount() - 1 && hasFooterView()) {// 最后一条
            // if (position < _data.size()) {
            // position = getCount() - 2; // footview
            // }
            if (getState() == STATE_LOAD_MORE || getState() == STATE_NO_MORE
                    || state == STATE_EMPTY_ITEM
                    || getState() == STATE_NETWORK_ERROR) {
                this.mFooterView = (LinearLayout) LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.list_cell_footer,
                        null);
                if (!loadMoreHasBg()) {
                    mFooterView.setBackgroundDrawable(null);
                }
                ProgressBar progress = (ProgressBar) mFooterView
                        .findViewById(R.id.progressbar);
               TextView text = (TextView) mFooterView.findViewById(R.id.text);
                switch (getState()) {
                    case STATE_LOAD_MORE:
                        setFooterViewLoading();
                        break;
                    case STATE_NO_MORE:
                        mFooterView.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                          text.setVisibility(View.VISIBLE);
                           text.setText(_loadFinishText);
                        break;
                    case STATE_EMPTY_ITEM:
                        progress.setVisibility(View.GONE);
                        mFooterView.setVisibility(View.VISIBLE);
                        //       text.setText(_noDateText);
                        break;
                    case STATE_NETWORK_ERROR:
                        mFooterView.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                        if (NetWorkUtil.hasInternetConnected(mContext) ) {
                            text.setText("加载出错了");
                        } else {
                            text.setText("没有可用的网络");
                        }
                        break;
                    default:
                        progress.setVisibility(View.GONE);
                        mFooterView.setVisibility(View.GONE);
                        break;
                }
                return mFooterView;
            }
        }
        if (position < 0) {
            position = 0; // 若列表没有数据，是没有footview/headview的
        }






        //构造viewHolder ,
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                getItemLayoutId(), position);

        convert(viewHolder, getItem(position),position);



        View itemView = viewHolder.getConvertView();

        ViewHelper.setScaleX(itemView,0.8f);
        ViewHelper.setScaleY(itemView,0.8f);
        ViewPropertyAnimator.animate(itemView).scaleX(1).setDuration(2000).setInterpolator(new OvershootInterpolator()).start();
        ViewPropertyAnimator.animate(itemView).scaleY(1).setDuration(2000).setInterpolator(new OvershootInterpolator()).start();



        return viewHolder.getConvertView();




      //  return getRealView(position, convertView, parent);
    }

//    protected View getRealView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }

    private LinearLayout mFooterView;

    protected boolean hasFooterView() {
        return true;
    }

    public View getFooterView() {
        return this.mFooterView;
    }

    public void setFooterViewLoading(String loadMsg) {
        ProgressBar progress = (ProgressBar) mFooterView
                .findViewById(R.id.progressbar);
        TextView text = (TextView) mFooterView.findViewById(R.id.text);
        mFooterView.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        if (StringUtils.isEmpty(loadMsg)) {
            text.setText(_loadmoreText);
        } else {
            text.setText(loadMsg);
        }
    }

    public void setFooterViewLoading() {
        setFooterViewLoading("");
    }

    public void setFooterViewText(String msg) {
        ProgressBar progress = (ProgressBar) mFooterView
                .findViewById(R.id.progressbar);
        TextView text = (TextView) mFooterView.findViewById(R.id.text);
        mFooterView.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        text.setVisibility(View.VISIBLE);
        text.setText(msg);
    }



    protected void setText(TextView textView, String text, boolean needGone) {
        if (text == null || TextUtils.isEmpty(text)) {
            if (needGone) {
                textView.setVisibility(View.GONE);
            }
        } else {
            textView.setText(text);
        }
    }


    protected void setImageRes(ImageView imageRes, @DrawableRes int resId) {
        imageRes.setImageResource(resId);
    }

    protected void setText(TextView textView, String text) {
        setText(textView, text, false);
    }





}
