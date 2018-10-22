package myp2pinverst.ldgd.com.myp2pinverst.base;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import myp2pinverst.ldgd.com.myp2pinverst.common.ActivityManager;

/**
 * Created by ldgd on 2016/12/10.
 * 介绍：基类，公共类，
 * VideoPager
 * <p/>
 * AudioPager
 * <p/>
 * NetVideoPager
 * <p/>
 * NetAudioPager
 * 都继承该类
 */

public abstract class BaseFragment extends FragmentActivity {
    public  final Context context;
    public boolean isInitData;
    public View rootView;

    public BaseFragment(Context context) {
        this.context = context;
        rootView = initView();
        //将当前的activity添加到ActivityManager中
        ActivityManager.getInstance().add(this);

    }

    public abstract View initView();
    /*
       子类根据需要实现
     */
    public void initData(){
    };
}
