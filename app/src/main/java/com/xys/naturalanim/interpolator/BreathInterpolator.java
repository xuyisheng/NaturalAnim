package com.xys.naturalanim.interpolator;

import com.xys.naturalanim.Calculator;

/**
 * 自定义Interpolator
 * <p>
 * Created by xuyisheng on 16/9/27.
 */
public class BreathInterpolator extends Calculator {

    @Override
    public float getInterpolation(float input) {
        if (input < 0.333) {
            return (float) (0.5f + 0.5f * Math.sin(input * 3.0f * Math.PI - Math.PI * 0.5f));
        } else {
            return (float) Math.pow((0.5 * Math.sin(-3f * Math.PI * input * 0.5f + Math.PI) + 0.5f), 2);
        }
    }
}
