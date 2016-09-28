package com.xys.naturalanim.interpolator;

import com.xys.naturalanim.Calculator;

/**
 * 自定义Interpolator
 * <p>
 * Created by xuyisheng on 16/9/27.
 */
public class ExpoOutInterpolator extends Calculator {

    @Override
    public float getInterpolation(float input) {
        return (float) ((input == 1) ? 1 : (-Math.pow(2, -10 * input) + 1));
    }
}
