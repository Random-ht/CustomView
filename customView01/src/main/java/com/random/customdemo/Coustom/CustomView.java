package com.random.customdemo.Coustom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.random.customdemo.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by hutao on 2018/4/19.
 * 自定义属性
 */

public class CustomView extends View {

    private String textContent;
    private int textColor;
    private int background;
    private int textSize;
    private Paint paint;
    private Rect rect;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();//获取总共几个自定义的属性
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);// 可以理解为 得到对应属性的名字  供下面做对比   对号入座
            switch (index) {
                case R.styleable.CustomView_textContent:
                    textContent = typedArray.getString(index);
                    break;
                case R.styleable.CustomView_textColor:
                    textColor = typedArray.getColor(index, Color.GREEN);
                    break;
                case R.styleable.CustomView_textSize:
                    textSize = typedArray.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomView_textBackground:
                    background = typedArray.getColor(index, Color.YELLOW);
                    break;
            }
        }
        typedArray.recycle();

        init(context);

    }

    private void init(final Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);

        paint.setTextSize(textSize);
        rect = new Rect();


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                textContent = randomText();
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;

        paint.getTextBounds(textContent, 0, textContent.length(), rect);//测量文字的长宽
        /**
         * 设置宽
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:// 明确指定了
                width = getPaddingLeft() + getPaddingRight() + widthSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                width = getPaddingLeft() + getPaddingRight() + rect.width();//后面要加上文字的宽
                break;
        }

        /**
         * 设置高
         */
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:// 明确指定了
                height = getPaddingTop() + getPaddingBottom() + heightSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                height = getPaddingTop() + getPaddingBottom() + rect.height(); //后面要加上文字的高
                break;
        }

        setMeasuredDimension(width, height);//设置设定的宽和高
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(background);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredWidth(), paint);

        paint.setColor(textColor);
        canvas.drawText(textContent, getMeasuredWidth() / 2 - ((float) rect.width()) / 2, getMeasuredHeight() / 2 + ((float) rect.height()) / 2, paint);
    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }

}
