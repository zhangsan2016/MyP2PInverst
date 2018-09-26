package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.content.Context;
import android.view.View;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BasePager;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：我的Fragment
 * 说明：
 */

public class MeFragment extends BasePager {


    public MeFragment(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_me, null);
        return view;
    }

}
