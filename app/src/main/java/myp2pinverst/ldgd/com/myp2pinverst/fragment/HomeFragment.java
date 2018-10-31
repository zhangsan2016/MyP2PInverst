package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import java.util.List;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;
import myp2pinverst.ldgd.com.myp2pinverst.bean.Image;
import myp2pinverst.ldgd.com.myp2pinverst.bean.Index;
import myp2pinverst.ldgd.com.myp2pinverst.bean.Product;
import myp2pinverst.ldgd.com.myp2pinverst.common.AppNetConfig;
import myp2pinverst.ldgd.com.myp2pinverst.ui.RoundProgress;
import myp2pinverst.ldgd.com.myp2pinverst.util.UIUtils;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：首页
 * 说明：
 */
public class HomeFragment extends BaseFragment {

    private TextView tvHomeProduct;
    private TextView tvHomeYearrate;
    private ImageView ivTitleBack;
    private TextView tvTitle;
    private ImageView ivTitleSetting;
    private RoundProgress roundProHome;
    /**
     *  当前进度
     */
    private int currentProress;


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            roundProHome.setMax(100);
            for (int i = 0; i < currentProress; i++) {
                roundProHome.setProgress(i+1);
                SystemClock.sleep(20);
                // 强制重绘
                //  roundProHome.invalidate();//只有主线程才可以如此调用
                roundProHome.postInvalidate();//主线程、分线程都可以如此调用
            }
        }
    };


    public HomeFragment(Context context) {
        super(context);

    }

    @Override
    public View initView(View view) {
       // View view = View.inflate(context, getLayoutId(), null);
        tvHomeProduct =  view.findViewById(R.id.tv_home_product);
        tvHomeYearrate =  view.findViewById(R.id.tv_home_yearrate);

        ivTitleBack = view.findViewById(R.id.iv_title_back);
        tvTitle = view.findViewById(R.id.tv_title);
        ivTitleSetting = view.findViewById(R.id.iv_title_setting);
        roundProHome = view.findViewById(R.id.roundPro_home);

        return view;
    }


    private Index index;
    @Override
    protected void initData(String content) {

        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(UIUtils.getContext(), "成功！", Toast.LENGTH_SHORT).show();

            index = new Index();

            // 解析json数据： GSON/ FASTJSON
            JSONObject jsonObject = JSON.parseObject(content);

            // 解析json对象数组
            String proInfo = jsonObject.getString("proInfo");
            Product product = JSON.parseObject(proInfo, Product.class);

            // 解析json数组
            String imageArr = jsonObject.getString("imageArr");
            List<Image> images = JSON.parseArray(imageArr, Image.class);
            index.product = product;
            index.images = images;

            // 更新页面数据
            tvHomeProduct.setText(product.name);
            tvHomeYearrate.setText(product.yearRate + "%");

            //获取数据中的进度值
            currentProress = Integer.parseInt(index.product.progress);
            //在分线程中，实现进度的动态变化
            new Thread(runnable).start();

        }else{
            Toast.makeText(UIUtils.getContext(),"失败！" , Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("首页");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
























}
