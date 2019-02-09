package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.loopj.android.http.RequestParams;

import butterknife.OnClick;
import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.activity.UserInfoActivity;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：我的Fragment
 * 说明：
 */

public class MeFragment extends BaseFragment {


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
        return  R.layout.fragment_me;
    }

    public MeFragment(Context context) {
        super(context);
    }

    @Override
    public View initView(View view) {
       // View view = View.inflate(context,getLayoutId(), null);
        return view;
    }

    @Override
    protected void initData(String content) {

    }

    @OnClick(R.id.iv_title_setting)
    public void setting(View view){
        //启动用户信息界面的Activity
        Intent intent = new Intent(context,UserInfoActivity.class);
       startActivity(intent);
    }

}
