package com.xys.naturalanim.interpolator;

import com.xys.naturalanim.Calculator;

/**
 * 自定义Interpolator
 * <p>
 * Created by xuyisheng on 16/9/27.
 */
public class ElasticOutInterpolator extends Calculator {

    private static final float TWO_PI = (float) (Math.PI * 2);
    private int mDuration;

    public ElasticOutInterpolator(int duration) {
        mDuration = duration;
    }

    @Override
    public float getInterpolation(float input) {
        float fDuration = mDuration / 1000f;
        float s;
        float p;
        float a;
        if (input == 0) {
            return 0;
        }
        if (input == 1) {
            return 1;
        }
        p = fDuration * 0.3f;
        a = 1;
        s = p / 4;
        return (float) (a * Math.pow(2, -10 * input) * Math.sin((input * fDuration - s) * TWO_PI / p) + 1 + 0);
    }
}
