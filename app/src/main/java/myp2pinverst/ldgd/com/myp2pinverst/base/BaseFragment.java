package myp2pinverst.ldgd.com.myp2pinverst.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;

import myp2pinverst.ldgd.com.myp2pinverst.ui.LoadingPage;

/**
 * Created by ldgd on 2018/10/22.
 * 功能：
 * 说明：
 */

public abstract class BaseFragment extends Fragment {


    public final Context context;
    public boolean isInitData;
    public View rootView;

    private LoadingPage loadingPage;
    private int layoutId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.e("sdf","执行线程");

        loadingPage = new LoadingPage(container.getContext()) {

            @Override
            public int layoutId() {
                return getLayoutId();
            }

            @Override
            protected void onSuccss(ResultState resultState, View view_success) {
                initTitle();
                initData(resultState.getContent());
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            public String url() {
                return getUrl();
            }
        };


        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    // 下载地址
    protected abstract String getUrl();
    //初始化界面的数据
    protected abstract RequestParams getParams();
    //初始化title
    protected abstract void initTitle();

    //提供布局
    public abstract int getLayoutId();

    public BaseFragment(Context context) {
        this.context = context;
        rootView = initView();

    }

    public abstract View initView();



    //初始化界面的数据
    protected abstract void initData(String content);

    public void show(){
        loadingPage.show();
    }


}
