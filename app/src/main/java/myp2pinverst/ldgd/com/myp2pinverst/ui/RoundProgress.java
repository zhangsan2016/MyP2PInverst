package myp2pinverst.ldgd.com.myp2pinverst.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.util.UIUtils;

/**
 * Created by ldgd on 2018/10/24.
 * 功能：自定义圆形进度条
 * 说明：
 */

public class RoundProgress extends View {

    /**
     * 圆弧的颜色
     */
    private final int roundProgressColor;
    /**
     * 圆弧的颜色
     */
    private final int textColor;
    /**
     * 圆环的宽度
     */
    private final float roundWidth;
    /**
     * 文本的字体大小
     */
    private final float textSize;
    /**
     * 圆环的最大值
     */
    private final int max;
    /**
     * 圆环的进度
     */
    private final int progress;
    /**
     * 圆环的颜色
     */
    private int roundColor;
    /**
     * 视图宽
     */
    private int width;
    /**
     * 画笔
     */
    private Paint paint;

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);

        // 获取自定义属性
/*       <attr name="roundColor" format="color"></attr>
        <attr name="roundProgressColor" format="color"></attr>
        <attr name="textColor" format="color"></attr>
        <attr name="roundWith" format="dimension"></attr>
        <attr name="textSize" format="dimension"></attr>
        <attr name="progress" format="integer"></attr>
        <attr name="max" format="integer"></attr>*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);
        roundProgressColor = typedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.RED);
        textColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);
        roundWidth = typedArray.getDimension(R.styleable.RoundProgress_roundWith, UIUtils.dp2px(10));
        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize, UIUtils.dp2px(20));
        max = typedArray.getInteger(R.styleable.RoundProgress_max, 100);
        progress = typedArray.getInteger(R.styleable.RoundProgress_progress,  UIUtils.dp2px(10));


        typedArray.recycle();

    }

    //测量：获取当前视图宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = this.getMeasuredWidth();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1.绘制圆环
        //获取圆心坐标
        int cx = width / 2;
        int cy = width / 2;
        float radius = width / 2 - roundWidth / 2;
        paint.setColor(roundColor);  //设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);  //设置圆环的样式
        paint.setStrokeWidth(roundWidth);  //设置圆环的宽度
        canvas.drawCircle(cx, cy, radius, paint);


        // 2.绘制圆弧
        RectF rectF = new RectF(roundWidth / 2, roundWidth / 2, width - roundWidth / 2, width - roundWidth / 2);
        paint.setColor(roundProgressColor);//设置画笔颜色
        canvas.drawArc(rectF, 0, progress * 360 / max, false, paint);

        // 3.绘制文本
        String text = progress * 100 / max + "%";
        // 设置paint
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(0);
        Rect rect = new Rect(); // 创建了一个矩形，此时矩形没有具体的宽度和高度
        paint.getTextBounds(text, 0, text.length(), rect);
        // 获取左下顶点的坐标
        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;
        canvas.drawText(text, x, y, paint);

    }


}
