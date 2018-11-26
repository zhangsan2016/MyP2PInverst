package myp2pinverst.ldgd.com.myp2pinverst.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.bean.Product;
import myp2pinverst.ldgd.com.myp2pinverst.ui.RoundProgress;
import myp2pinverst.ldgd.com.myp2pinverst.util.UIUtils;


/**
 * Created by shkstart on 2016/12/5 0005.
 */
public class MyHolder extends BaseHolder<Product> {

    @BindView(R.id.p_name)
    TextView pName;
    @BindView(R.id.p_money)
    TextView pMoney;
    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @BindView(R.id.p_minnum)
    TextView pMinnum;
    @BindView(R.id.p_progresss)
    RoundProgress pProgresss;



    @Override
    protected View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_product_list, null);
    }

    @Override
    protected void refreshData() {
        Product data = this.getData();

        //装数据
        pMinnum.setText(data.memberNum);
        pMinzouzi.setText(data.minTouMoney);
        pMoney.setText(data.money);
        pName.setText(data.name);
        pProgresss.setProgress(Integer.parseInt(data.progress));
        pYearlv.setText(data.yearRate);

    }
}
