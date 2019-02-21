package com.cp.mylibrary.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import static com.cp.mylibrary.R.id.listView;

/**
 * 自定义适应ScrollView的ListView
 * 
 * @author Administrator
 * 
 */
public class ListViewForScrollView extends ListView {

	public ListViewForScrollView(Context context) {
		super(context);
	}

	public ListViewForScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ListViewForScrollView(Context context, AttributeSet attrs,
								 int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	/**
	 * 重写该方法，达到使ListView适应ScrollView的效果
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}


	/**
	 * 覆写onInterceptTouchEvent方法，点击操作发生在ListView的区域的时候,
	 * 返回false让ScrollView的onTouchEvent接收不到MotionEvent，而是把Event传到下一级的控件中
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (    checkArea(this, ev)) {
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

	/**
	 *  测试view是否在点击范围内
	 * @param
	 * @param v
	 * @return
	 */
	private boolean checkArea(View v, MotionEvent event){
		float x = event.getRawX();
		float y = event.getRawY();
		int[] locate = new int[2];
		v.getLocationOnScreen(locate);
		int l = locate[0];
		int r = l + v.getWidth();
		int t = locate[1];
		int b = t + v.getHeight();
		if (l < x && x < r && t < y && y < b) {
			return true;
		}
		return false;
	}
//
//	public ListView getListView() {
//		return listView;
//	}
//
//	public void setListView(ListView listView) {
//		this.listView = listView;
//	}


}
