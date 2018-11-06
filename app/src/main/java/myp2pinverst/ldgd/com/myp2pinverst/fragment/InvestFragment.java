package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;
import myp2pinverst.ldgd.com.myp2pinverst.fragment.invest.ProductHotFragment;
import myp2pinverst.ldgd.com.myp2pinverst.fragment.invest.ProductListFragment;
import myp2pinverst.ldgd.com.myp2pinverst.fragment.invest.ProductRecommondFragment;
import myp2pinverst.ldgd.com.myp2pinverst.util.UIUtils;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：投资Fragment
 * 说明：
 */

public class InvestFragment extends BaseFragment {


    private TabPageIndicator tabpageInvest;
    private ViewPager vpInvest;
    private ImageView ivTitleBack;
    private TextView tvTitle;
    private ImageView ivTitleSetting;
    private List<Fragment> fragmentList = new ArrayList<>();

    public InvestFragment(Context context) {
        super(context);
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("投资");

        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }


    @Override
    public View initView(View view) {
        vpInvest = view.findViewById(R.id.vp_invest);
        tabpageInvest = view.findViewById(R.id.tabpage_invest);
        ivTitleBack = view.findViewById(R.id.iv_title_back);
        tvTitle = view.findViewById(R.id.tv_title);
        ivTitleSetting = view.findViewById(R.id.iv_title_setting);
        return view;
    }

    @Override
    protected void initData(String content) {

        //1.加载三个不同的Fragment：ProductListFragment,ProductRecommondFragment,ProductHotFragment.
        initFragments();
        //2.ViewPager设置三个Fragment的显示
        MyAdapter adapter = new MyAdapter(getFragmentManager());
        vpInvest.setAdapter(adapter);
        //将TabPagerIndicator与ViewPager关联
        tabpageInvest.setViewPager(vpInvest);
    }

    private void initFragments() {
        if(fragmentList!= null || fragmentList.size() > 0){
            fragmentList.clear();
        }
        ProductListFragment productListFragment = new ProductListFragment(context);
        ProductRecommondFragment productRecommondFragment = new ProductRecommondFragment(context);
        ProductHotFragment productHotFragment = new ProductHotFragment(context);
        //添加到集合中
        fragmentList.add(productListFragment);
        fragmentList.add(productRecommondFragment);
        fragmentList.add(productHotFragment);
    }


    /**
     * 提供PagerAdapter的实现
     * 如果ViewPager中加载的是Fragment,则提供的Adpater可以继承于具体的：FragmentStatePagerAdapter或FragmentPagerAdapter
     * FragmentStatePagerAdapter:适用于ViewPager中加载的Fragment过多，会根据最近最少使用算法，实现内存中Fragment的清理，避免溢出
     * FragmentPagerAdapter:适用于ViewPager中加载的Fragment不多时，系统不会清理已经加载的Fragment。
     */
    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        //提供TabPageIndicator的显示内容
        @Override
        public CharSequence getPageTitle(int position) {
       /*     //方式一：
            if(position == 0){
                return "全部理财";
            }else if(position == 1){
                return "推荐理财";
            }else if(position == 2){
                return "热门理财";
            }*/
            //方式二：
           return UIUtils.getStringArr(R.array.invest_tab)[position];
        }
    }


}
