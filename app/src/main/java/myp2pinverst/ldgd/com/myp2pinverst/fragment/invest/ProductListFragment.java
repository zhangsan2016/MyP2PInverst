package myp2pinverst.ldgd.com.myp2pinverst.fragment.invest;

import android.content.Context;
import android.view.View;

import com.loopj.android.http.RequestParams;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;

/**
 * Created by ldgd on 2018/11/5.
 * 功能：产品列表
 * 说明：
 */

public class ProductListFragment   extends BaseFragment {


    public ProductListFragment(Context context) {
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

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_productlist;
    }

    @Override
    public View initView(View view) {
        return null;
    }

    @Override
    protected void initData(String content) {

    }
}
