package myp2pinverst.ldgd.com.myp2pinverst.fragment.invest;

import android.content.Context;
import android.view.View;

import com.loopj.android.http.RequestParams;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;

/**
 * Created by ldgd on 2018/11/6.
 * 功能：
 * 说明：
 */

public class ProductHotFragment extends BaseFragment {
    public ProductHotFragment(Context context) {
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
        return R.layout.fragment_producthot;
    }

    @Override
    public View initView(View view) {
        return null;
    }

    @Override
    protected void initData(String content) {

    }
}
