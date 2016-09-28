package com.xys.naturalanim.views;

import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * 动画图表绘制
 * <p>
 * Created by xuyisheng on 16/9/27.
 */
public class GraphicsView extends View {

    private Paint mPaint;
    public float mViewWidth;
    public float mViewHeight;
    private Path mPath;

    private Interpolator mInterpolator;
    private TypeEvaluator<Number> mTypeEvaluator;

    public GraphicsView(Context context) {
        super(context);
    }

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
    }

    public GraphicsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mPath.moveTo(0, mViewHeight);
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setTypeEvaluator(TypeEvaluator<Number> typeEvaluator) {
        mTypeEvaluator = typeEvaluator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        canvas.drawRect(0, 0, mViewWidth, mViewHeight, mPaint);
        canvas.translate(0, mViewHeight * 0.3F);
        if (mInterpolator != null) {
            for (int i = 0; i < mViewWidth; i++) {
                mPath.lineTo(i, (mViewHeight - mInterpolator.getInterpolation(i * 1.0F / mViewHeight) * mViewHeight) * 0.6F);
            }
        } else {
            for (int i = 0; i < mViewWidth; i++) {
                mPath.lineTo(i, (mViewHeight - (float) mTypeEvaluator.evaluate(i * 1.0F / mViewHeight, 0F, mViewHeight)) * 0.6F);
            }
        }
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }
}
