package myp2pinverst.ldgd.com.myp2pinverst.fragment.invest;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import java.util.Random;

import butterknife.BindView;
import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;
import myp2pinverst.ldgd.com.myp2pinverst.ui.randomLayout.StellarMap;
import myp2pinverst.ldgd.com.myp2pinverst.util.UIUtils;

/**
 * Created by ldgd on 2018/11/6.
 * 功能： 推荐产品Frament
 * 说明：
 */

public class ProductRecommondFragment extends BaseFragment {

    @BindView(R.id.stellar_map)
    StellarMap stellarMap;

    //提供装配的数据
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    //声明两个子数组
    private String[] oneDatas = new String[datas.length / 2];
    private String[] twoDatas = new String[datas.length - datas.length / 2];

    private Random random = new Random();

    public ProductRecommondFragment(Context context) {
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
        return R.layout.fragment_productrecommond;
    }

    @Override
    public View initView(View view) {

        return null;
    }


    @Override
    protected void initData(String content) {
        //初始化子数组的数据
        for (int i = 0; i < datas.length; i++) {
            if (i < datas.length / 2) {
                oneDatas[i] = datas[i];
            } else {
                twoDatas[i - datas.length / 2] = datas[i];
            }
        }


        StellarAdapter stellarAdapter = new StellarAdapter();
        stellarMap.setAdapter(stellarAdapter);


        int left = UIUtils.dp2px(10);
        int top = UIUtils.dp2px(10);
        int right = UIUtils.dp2px(10);
        int bottom = UIUtils.dp2px(10);

        stellarMap.setInnerPadding(left,top,right,bottom);

        //必须调用如下的两个方法，否则stellarMap不能显示数据
        //设置显示的数据在x轴、y轴方向上的稀疏度
        stellarMap.setRegularity(5, 7);
        //设置初始化显示的组别，以及是否需要使用动画
        stellarMap.setGroup(0, true);
    }


    class StellarAdapter implements StellarMap.Adapter {


        // 获取组的个数
        @Override
        public int getGroupCount() {
            return 2;
        }


        // 返回每组中显示的数据的个数
        @Override
        public int getCount(int group) {

            if (group == 0) {
                return datas.length / 2;
            } else {
                return datas.length - datas.length / 2;
            }
        }

        //返回具体的view.
        //position:不同的组别，position都是从0开始。
        @Override
        public View getView(int group, int position, View convertView) {

            //设置属性
            //设置文本的内容
            final TextView textView = new TextView(getActivity());
            if (group == 0) {
                textView.setText(oneDatas[position]);
            } else {
                textView.setText(twoDatas[position]);
            }


            //设置字体的大小
            textView.setTextSize(UIUtils.dp2px(5) + UIUtils.dp2px(random.nextInt(10)));
            //设置字体的颜色
            int red = random.nextInt(211);//00 ~ ff ; 0 ~ 255
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            textView.setTextColor(Color.rgb(red, green, blue));

            //设置TextView的点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIUtils.toast(textView.getText().toString(),false);
                }
            });

            return textView;
        }

        //返回下一组显示平移动画的组别。查看源码发现，此方法从未被调用。所以可以不重写
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        //返回下一组显示缩放动画的组别。
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            if (group == 0)
                return 1;
            else
                return 0;
        }
    }


}
