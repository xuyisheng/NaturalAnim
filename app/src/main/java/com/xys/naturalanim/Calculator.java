package com.xys.naturalanim;

import android.animation.TypeEvaluator;
import android.view.animation.Interpolator;

/**
 * Interpolator与TypeEvaluator封装
 * <p>
 * Created by xuyisheng on 16/9/27.
 */
public class Calculator implements Interpolator, TypeEvaluator<Number> {

    @Override
    public float getInterpolation(float input) {
        return input;
    }

    @Override
    public Number evaluate(float fraction, Number startValue, Number endValue) {
        return startValue.floatValue() + fraction * (endValue.floatValue() - startValue.floatValue());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
