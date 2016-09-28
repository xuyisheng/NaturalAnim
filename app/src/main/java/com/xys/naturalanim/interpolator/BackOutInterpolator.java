package com.xys.naturalanim.interpolator;

import com.xys.naturalanim.Calculator;

/**
 * 自定义Interpolator
 * <p>
 * Created by xuyisheng on 16/9/27.
 */
public class BackOutInterpolator extends Calculator {

    @Override
    public float getInterpolation(float input) {
        return ((input = input - 1) * input * ((1.70158f + 1) * input + 1.70158f) + 1);
    }
}
