package myp2pinverst.ldgd.com.myp2pinverst.fragment.invest;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;
import myp2pinverst.ldgd.com.myp2pinverst.common.AppNetConfig;
import myp2pinverst.ldgd.com.myp2pinverst.util.LogUtil;

/**
 * Created by ldgd on 2018/11/5.
 * 功能：产品列表
 * 说明：
 */

public class ProductListFragment   extends BaseFragment {

    private TextView productTitle;
    private ListView lvProductHot;


    public ProductListFragment(Context context) {
        super(context);


    }

    @Override
    protected String getUrl() {
        return  AppNetConfig.PRODUCT;
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
        productTitle = view.findViewById(R.id.tv_product_title);
        lvProductHot = view.findViewById(R.id.lv_product_hot);

        return null;
    }

    @Override
    protected void initData(String content) {

        JSONObject jsonObject = JSON.parseObject(content);
        LogUtil.e("jsonObject = " + jsonObject.toString());

    }
}
