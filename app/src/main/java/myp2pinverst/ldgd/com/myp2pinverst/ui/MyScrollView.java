package myp2pinverst.ldgd.com.myp2pinverst.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import myp2pinverst.ldgd.com.myp2pinverst.util.LogUtil;

/**
 * Created by ldgd on 2018/10/31.
 * 功能：
 * 说明：
 */

public class MyScrollView extends ScrollView {

    private View childView;


    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 获取子视图
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }

    //上一次y轴方向操作的坐标位置
    private int lastY;
    //用于记录临界状态的左、上、右、下
    private Rect normal = new Rect();
   // 动画是否结束
    private boolean isFinishAnimation = true;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (childView == null || !isFinishAnimation) {
            return super.onTouchEvent(ev);
        }


        //获取当前的y轴坐标
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:

                //微小的移动量
                int dy = eventY - lastY;

                if (isNeedMove()) {
                    LogUtil.e(" childView.getBottom() = " +  childView.getBottom());
                     // 使用 normal.isEmpty() 方法作判断，使得临界值只保留一次
                    if(normal.isEmpty()){
                        //记录了childView的临界状态的左、上、右、下
                        normal.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
                        LogUtil.e(normal.left + ":"  + normal.right + ":" + normal.right + ":" + normal.bottom);
                    }
                    //重新布局
                    childView.layout(childView.getLeft(), childView.getTop() + dy / 2, childView.getRight(), childView.getBottom() + dy / 2);
                }
                lastY = eventY;//重新赋值
                break;
            case MotionEvent.ACTION_UP:


                if(isNeedAnimation()){
                    // 使用平移动画
                    int translateY = childView.getBottom() - normal.bottom;
                    TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-translateY);
                    translateAnimation.setDuration(2000);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isFinishAnimation = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            isFinishAnimation = true;
                            //清除动画
                            childView.clearAnimation();
                            //恢复布局
                            childView.layout(normal.left, normal.top, normal.right, normal.bottom);
                            normal.setEmpty();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.setAnimation(translateAnimation);
                }

                break;

        }


        return super.onTouchEvent(ev);
    }

    public boolean isNeedMove() {
        // 获取子视图的高度
        int childMeasuredHeight = childView.getMeasuredHeight();
        // 获取scrollView布局的高度
        int scrollViewMeasuredHeight = this.getMeasuredHeight();
         //dy >= 0
        int dy = childMeasuredHeight - scrollViewMeasuredHeight;
        // //获取用户在y轴方向上的偏移量 （上 + 下 -）
        int scrollY = this.getScrollY();
        LogUtil.e("scrollY = " + scrollY + "   " +  "dy == " + dy);
        if(scrollY <= 0 || scrollY >= dy ){
            return true;//按照我们自定义的MyScrollView的方式处理
        }
        //其他处在临界范围内的，返回false。即表示，仍按照ScrollView的方式处理
        return false;
    }


    //判断是否需要执行平移动画
    private boolean isNeedAnimation() {
        return !normal.isEmpty();

    }



}
