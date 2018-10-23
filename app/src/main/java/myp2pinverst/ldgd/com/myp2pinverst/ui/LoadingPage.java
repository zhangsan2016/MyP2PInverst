package myp2pinverst.ldgd.com.myp2pinverst.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.util.UIUtils;

/**
 * Created by ldgd on 2018/10/22.
 * 功能： 配合HomeFragment类，显示加载图片的状态
 * 说明：
 */

public abstract class LoadingPage extends FrameLayout {

    //1.定义4种不同的显示状态
    // 正在加载
    private static final int STATE_LOADING = 1;
    // 加载失败
    private static final int STATE_ERROR = 2;
    // 加载成功-内容为空
    private static final int STATE_EMPTY = 3;
    // 加载成功-内容不为空
    private static final int STATE_SUCCESS = 4;

    //默认情况下，当前状态为正在加载
    private int state_current = STATE_LOADING;

    private LayoutParams params;
    private Context mContext;

    //提供4种不同的界面
    private View view_loading;
    private View view_error;
    private View view_empty;
    private View view_success;


    public LoadingPage(@NonNull Context context) {
        this(context,null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        init();
    }


    /**
     *  初始化方法
     */
    private void init() {
           // 实例化view
        // 提供布局显示的参数
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(view_loading == null ){
           // 加载布局
           view_loading =  UIUtils.getView(R.layout.page_loading);
            // 添加到当前的frameLayout中
            addView(view_loading,params);
        }

        if(view_empty == null ){
            // 加载布局
            view_empty =  UIUtils.getView(R.layout.page_empty);
            // 添加到当前的frameLayout中
            addView(view_empty,params);
        }

        if(view_error == null ){
            // 加载布局
            view_error =  UIUtils.getView(R.layout.page_error);
            // 添加到当前的frameLayout中
            addView(view_error,params);
        }

        showSafePage();


    }


    //保证如下的操作在主线程中执行的：更新界面
    private void showSafePage() {
        UIUtils.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                //保证run()中的操作在主线程中执行
                showPage();
            }
        });
    }

    private void showPage() {

        //根据当前state_current的值，决定显示哪个view
        view_loading.setVisibility(state_current == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        view_error.setVisibility(state_current == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        view_empty.setVisibility(state_current == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);

        // 如果成功，加载主界面
        if (view_success == null) {
            //  view_success = UIUtils.getView(layoutId());//加载布局使用的是Application
            view_success = View.inflate(mContext,layoutId(),null);//加载布局使用的是activity
            addView(view_success, params);
        }
        view_success.setVisibility(state_current == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);

    }

    private ResultState resultState;

    //在show()中实现联网加载数据
    public void show() {

        String url = url();

        // 判断地址是否为空，是否需要加载数据，不需要直接执行返回
        if(TextUtils.isEmpty(url)){
            resultState = resultState.SUCCESS;
            resultState.setContent("");
            //修改state_current，并且决定加载哪个页面：showSafePage()
            loadImage();
            return;
        }

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url(), params(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String content) {
                        if (TextUtils.isEmpty(content)) {// "" or null
//                    state_current = STATE_EMPTY;
                            resultState = ResultState.EMPTY;
                            resultState.setContent("");
                        } else {
//                    state_current = STATE_SUCCESS;
                            resultState = ResultState.SUCCESS;
                            resultState.setContent(content);
                        }

//                showSafePage();
                        loadImage();
                    }

                    @Override
                    public void onFailure(Throwable error, String content) {
//                state_current = STATE_ERROR;
                        resultState = ResultState.ERROR;
                        resultState.setContent("");

//                showSafePage();
                        loadImage();

                    }
                });
            }
        }, 2000);


    }

    protected  void loadImage(){

        switch (resultState){
            case ERROR:
                state_current = STATE_ERROR;
                break;
            case EMPTY:
                state_current = STATE_EMPTY;
                break;
            case SUCCESS:
                state_current = STATE_SUCCESS;
                break;
        }

        //根据修改以后的state_current，更新视图的显示。
        showSafePage();

        if (state_current == STATE_SUCCESS) {
            onSuccss(resultState, view_success);
        }
    }

    public abstract int layoutId();

    protected abstract void onSuccss(ResultState resultState, View view_success);

    //提供联网的请求参数
    protected abstract RequestParams params();

    //提供联网的请求地址
    public abstract String url();

    //提供枚举类，封装联网以后的状态值和数据
    public enum ResultState {

        ERROR(2), EMPTY(3), SUCCESS(4);

        int state;

        ResultState(int state) {
            this.state = state;
        }

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }




}










































