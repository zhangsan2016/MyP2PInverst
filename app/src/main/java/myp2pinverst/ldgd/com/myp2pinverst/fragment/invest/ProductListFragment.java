package myp2pinverst.ldgd.com.myp2pinverst.fragment.invest;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import java.util.List;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.adapter.ProductAdapter3;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;
import myp2pinverst.ldgd.com.myp2pinverst.bean.Product;
import myp2pinverst.ldgd.com.myp2pinverst.common.AppNetConfig;

/**
 * Created by ldgd on 2018/11/5.
 * 功能：产品列表
 * 说明：
 */

public class ProductListFragment   extends BaseFragment {

    private TextView productTitle;
    private ListView lvProductList;
    private List<Product> productList;


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
        lvProductList = view.findViewById(R.id.lv_product_list);

        return null;
    }

    @Override
    protected void initData(String content) {

        JSONObject jsonObject = JSON.parseObject(content);

        boolean success = jsonObject.getBoolean("success");
        if(success){
            String data = jsonObject.getString("data");
            //获取集合数据
            productList = JSON.parseArray(data, Product.class);

            //方式一：没有抽取
//            ProductAdapter productAdapter = new ProductAdapter(productList);
//            lvProductList.setAdapter(productAdapter);//显示列表

//            //方式二：抽取了，但是抽取力度小 （可以作为选择）
//            ProductAdapter1 productAdapter1 = new ProductAdapter1(productList);
//            lvProductList.setAdapter(productAdapter1);//显示列表

            //方式三：抽取了，但是没有使用ViewHolder，getView()优化的不够
//            ProductAdapter2 productAdapter2 = new ProductAdapter2(productList);
//            lvProductList.setAdapter(productAdapter2);//显示列表

            //方式四：抽取了，最好的方式.（可以作为选择）
            ProductAdapter3 productAdapter3 = new ProductAdapter3(productList);
            lvProductList.setAdapter(productAdapter3);//显示列表
        }

    }
}
