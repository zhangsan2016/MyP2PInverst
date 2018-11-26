package myp2pinverst.ldgd.com.myp2pinverst.adapter;


import java.util.List;

import myp2pinverst.ldgd.com.myp2pinverst.bean.Product;

/**
 * Created by shkstart on 2016/12/5 0005.
 */
public class ProductAdapter3 extends MyBaseAdapter3<Product> {


    public ProductAdapter3(List<Product> list) {
        super(list);
    }

    @Override
    protected BaseHolder<Product> getHolder() {
        return new MyHolder();
    }
}
