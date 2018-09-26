package myp2pinverst.ldgd.com.myp2pinverst.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

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

public abstract class BasePager extends Fragment {
    public  final Context context;
    public boolean isInitData;
    public View rootView;

    public BasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    public abstract View initView();
    /*
       子类根据需要实现
     */
    public void initData(){
    };
}
