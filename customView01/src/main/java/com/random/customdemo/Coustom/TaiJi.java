package com.random.customdemo.Coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hutao on 2018/4/20.
 */

public class TaiJi extends View {

    private Paint blackPaint;
    private Paint whitePaint;
    private int width;
    private int height;

    public TaiJi(Context context) {
        this(context, null);
    }

    public TaiJi(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiJi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setColor(Color.BLACK);

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();

        canvas.translate(this.width / 2, height / 2);//画布移动到中点

        canvas.drawColor(Color.GRAY);
        canvas.rotate(degrees);
        Log.i("--------", degrees + "");

        //绘制两个半圆
        int radius = Math.min(this.width, height) / 2 - 100;                //太极半径
        RectF rect = new RectF(-radius, -radius, radius, radius);   //绘制区域
        canvas.drawArc(rect, 90, 180, true, blackPaint);            //绘制黑色半圆
        canvas.drawArc(rect, -90, 180, true, whitePaint);           //绘制白色半圆


        //绘制两个小圆
        int smallRadius = radius / 2;                                //小圆半径为大圆的一半
        canvas.drawCircle(0, -smallRadius, smallRadius, blackPaint);
        canvas.drawCircle(0, smallRadius, smallRadius, whitePaint);

        //绘制鱼眼（两个更小的圆）
        canvas.drawCircle(0, -smallRadius, smallRadius / 4, whitePaint);
        canvas.drawCircle(0, smallRadius, smallRadius / 4, blackPaint);

    }

    private float degrees = 0;                  //旋转角度

    public void setRotate(float degrees) {
        this.degrees = degrees;
        invalidate();                           //重绘界面
    }
}
