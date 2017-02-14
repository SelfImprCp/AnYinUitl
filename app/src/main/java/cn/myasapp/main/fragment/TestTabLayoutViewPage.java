package cn.myasapp.main.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.myasapp.main.R;
import cn.myasapp.main.adapter.TestTabLyoutViewPageAdapter;

/**
 * Created by Jerry on 2017/2/14.
 */

public class TestTabLayoutViewPage extends Fragment {

    private TabLayout tab_FindFragment_title;                            //定义TabLayout
    private ViewPager vp_FindFragment_pager;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter


    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表


    private TestViewPageFragment testViewPageFragment;

    private TestViewPageFragment2 testViewPageFragment2;

    private TestViewPageFragment3 testViewPageFragment3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_tablayout_viewpage,container,false);
        initControls(view);


        return  view;
    }

    /**
     *
     * @param view
     */
     private  void initControls(View view)
     {

         tab_FindFragment_title = (TabLayout)view.findViewById(R.id.tab_FindFragment_title);
         vp_FindFragment_pager = (ViewPager)view.findViewById(R.id.vp_FindFragment_pager);

         //初始化各fragment
         testViewPageFragment = new TestViewPageFragment();
         testViewPageFragment2 = new TestViewPageFragment2();
         testViewPageFragment3 = new TestViewPageFragment3();

         //将fragment装进列表中
         list_fragment = new ArrayList<>();
         list_fragment.add(testViewPageFragment);
         list_fragment.add(testViewPageFragment2);
         list_fragment.add(testViewPageFragment3);

         //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
         list_title = new ArrayList<>();
         list_title.add("热门推荐");
         list_title.add("热门收藏");
         list_title.add("本月热榜");

         //设置TabLayout的模式
         tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
         //为TabLayout添加tab名称
         tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
         tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
         tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
         fAdapter = new TestTabLyoutViewPageAdapter(getActivity().getSupportFragmentManager(),list_fragment,list_title);
         //viewpager加载adapter
         vp_FindFragment_pager.setAdapter(fAdapter);
         //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
         //TabLayout加载viewpager
         tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
         //tab_FindFragment_title.set

     }


}
