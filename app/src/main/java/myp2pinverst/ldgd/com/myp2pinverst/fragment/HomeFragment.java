package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.content.Context;
import android.view.View;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BasePager;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：首页
 * 说明：
 */

public class HomeFragment extends BasePager {


    public HomeFragment(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void initData() {

        super.initData();
    }
}
