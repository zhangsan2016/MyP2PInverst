package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.content.Context;
import android.view.View;

import com.loopj.android.http.RequestParams;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：投资Fragment
 * 说明：
 */

public class InvestFragment   extends BaseFragment {


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

    }

    @Override
    public int getLayoutId() {
        return  R.layout.fragment_invest;
    }

    public InvestFragment(Context context) {
        super(context);
    }

    @Override
    public View initView(View view) {
    //    View view = View.inflate(context,getLayoutId(), null);
        return view;
    }

    @Override
    protected void initData(String content) {

    }


}
