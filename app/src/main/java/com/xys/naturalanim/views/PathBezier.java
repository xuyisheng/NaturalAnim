package com.xys.naturalanim.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.xys.naturalanim.evaluator.BezierEvaluator;

public class PathBezier extends View implements View.OnClickListener {

    private Paint mPathPaint;
    private Paint mCirclePaint;

    private int mStartPointX;
    private int mStartPointY;
    private int mEndPointX;
    private int mEndPointY;

    private int mMovePointX;
    private int mMovePointY;

    private int mControlPointX;
    private int mControlPointY;

    private Path mPath;

    public PathBezier(Context context) {
        super(context);
    }

    public PathBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(5);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mStartPointX = 100;
        mStartPointY = 100;
        mEndPointX = 600;
        mEndPointY = 600;
        mMovePointX = mStartPointX;
        mMovePointY = mStartPointY;
        mControlPointX = 500;
        mControlPointY = 0;
        mPath = new Path();
        setOnClickListener(this);
    }

    public PathBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        canvas.drawCircle(mStartPointX, mStartPointY, 30, mCirclePaint);
        canvas.drawCircle(mEndPointX, mEndPointY, 30, mCirclePaint);
        mPath.moveTo(mStartPointX, mStartPointY);
        mPath.quadTo(mControlPointX, mControlPointY, mEndPointX, mEndPointY);
        canvas.drawPath(mPath, mPathPaint);
        canvas.drawCircle(mMovePointX, mMovePointY, 30, mCirclePaint);
    }

    @Override
    public void onClick(View view) {
        BezierEvaluator bezierEvaluator = new BezierEvaluator(new PointF(mControlPointX, mControlPointY));
        ValueAnimator anim = ValueAnimator.ofObject(bezierEvaluator,
                new PointF(mStartPointX, mStartPointY),
                new PointF(mEndPointX, mEndPointY));
        anim.setDuration(600);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF point = (PointF) valueAnimator.getAnimatedValue();
                mMovePointX = (int) point.x;
                mMovePointY = (int) point.y;
                invalidate();
            }
        });
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
    }
}