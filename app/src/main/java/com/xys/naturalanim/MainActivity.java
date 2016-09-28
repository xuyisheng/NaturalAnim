package com.xys.naturalanim;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.xys.naturalanim.interpolator.BackOutInterpolator;
import com.xys.naturalanim.interpolator.BounceOutInterpolator;
import com.xys.naturalanim.interpolator.BreathInterpolator;
import com.xys.naturalanim.interpolator.CircleOutInterpolator;
import com.xys.naturalanim.interpolator.CubicOutInterpolator;
import com.xys.naturalanim.interpolator.ElasticOutInterpolator;
import com.xys.naturalanim.interpolator.ExpoOutInterpolator;
import com.xys.naturalanim.interpolator.QuadOutInterpolator;
import com.xys.naturalanim.interpolator.QuartOutInterpolator;
import com.xys.naturalanim.interpolator.QuintOutInterpolator;
import com.xys.naturalanim.interpolator.SineOutInterpolator;
import com.xys.naturalanim.views.GraphicsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View mTestView;
    private View mTestRefView;
    private GraphicsView mGraphicsView;
    private int mDuration;
    private float mEndValue;
    private Spinner mSpinnerChoose;
    private Spinner mSpinnerType;

    private Interpolator mInterpolator;
    private ObjectAnimator mAnimator;
    private ObjectAnimator mAnimatorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestView = findViewById(R.id.test_view);
        mTestRefView = findViewById(R.id.test_ref_view);
        mSpinnerChoose = (Spinner) findViewById(R.id.sp_choose);
        mSpinnerType = (Spinner) findViewById(R.id.sp_type);
        mGraphicsView = (GraphicsView) findViewById(R.id.graphics);
        initConfig();
        initSpinner();
    }

    private void initSpinner() {
        final List<Calculator> listChoose = new ArrayList<>();
        listChoose.add(new BackOutInterpolator());
        listChoose.add(new BounceOutInterpolator());
        listChoose.add(new BreathInterpolator());
        listChoose.add(new CircleOutInterpolator());
        listChoose.add(new CubicOutInterpolator());
        listChoose.add(new ElasticOutInterpolator(mDuration));
        listChoose.add(new ExpoOutInterpolator());
        listChoose.add(new QuadOutInterpolator());
        listChoose.add(new QuartOutInterpolator());
        listChoose.add(new QuintOutInterpolator());
        listChoose.add(new SineOutInterpolator());
        ArrayAdapter adapterChoose = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listChoose);
        adapterChoose.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChoose.setAdapter(adapterChoose);
        mSpinnerChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInterpolator = listChoose.get(position);
                mGraphicsView.setInterpolator(mInterpolator);
                mGraphicsView.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final List<String> listType = new ArrayList<>();
        listType.add("Translation");
        listType.add("Scaling");
        listType.add("Rotation");
        listType.add("Alpha");
        ArrayAdapter adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listType);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerType.setAdapter(adapterType);
        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mAnimator = ObjectAnimator.ofFloat(mTestView, "translationX", 0, mEndValue);
                        mAnimatorRef = ObjectAnimator.ofFloat(mTestRefView, "translationX", 0, mEndValue);
                        break;
                    case 1:
                        mAnimator = ObjectAnimator.ofFloat(mTestView, "scaleX", 0, 1);
                        mAnimatorRef = ObjectAnimator.ofFloat(mTestRefView, "scaleX", 0, 1);
                        break;
                    case 2:
                        mAnimator = ObjectAnimator.ofFloat(mTestView, "rotationX", 0, 360);
                        mAnimatorRef = ObjectAnimator.ofFloat(mTestRefView, "rotationX", 0, 360);
                        break;
                    case 3:
                        mAnimator = ObjectAnimator.ofFloat(mTestView, "alpha", 0, 1);
                        mAnimatorRef = ObjectAnimator.ofFloat(mTestRefView, "alpha", 0, 1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initConfig() {
        mDuration = 1000;
        ViewTreeObserver vto = mGraphicsView.getViewTreeObserver();
        mInterpolator = new BackOutInterpolator();
        mGraphicsView.setInterpolator(mInterpolator);
        if (mEndValue == 0) {
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mGraphicsView.getViewTreeObserver().addOnGlobalLayoutListener(this);
                    mEndValue = mGraphicsView.getWidth();
                    if (mAnimator == null) {
                        mAnimator = ObjectAnimator.ofFloat(mTestView, "translationX", 0, mEndValue);
                        mAnimatorRef = ObjectAnimator.ofFloat(mTestRefView, "translationX", 0, mEndValue);
                    }
                }
            });
        }
    }

    public void startAnim(View view) {
        mAnimator.setDuration(mDuration);
        mAnimator.setInterpolator(mInterpolator);

        mAnimatorRef.setDuration(mDuration);
        mAnimatorRef.setInterpolator(new LinearInterpolator());

        mAnimator.start();
        mAnimatorRef.start();
    }

    public void advancedEvaluator(View view) {
        startActivity(new Intent(this, AdvancedEvaluatorActivity.class));
    }
}
