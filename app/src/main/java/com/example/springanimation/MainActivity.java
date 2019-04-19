package com.example.springanimation;

import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    AppCompatImageView ivPlay;
    View vBack;
    ConstraintLayout clBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clBody = findViewById(R.id.cl_body);
        ivPlay = findViewById(R.id.iv_play);
        vBack = findViewById(R.id.v_back);

        ivPlay.post(() -> {
            final float offset = vBack.getHeight() - ivPlay.getHeight() + 0.5f * getResources().getDimensionPixelSize(R.dimen.margin_18);
            final float max = vBack.getHeight() - ivPlay.getHeight() + getResources().getDimensionPixelSize(R.dimen.margin_18);
            final SpringAnimation springAnim = new SpringAnimation(ivPlay, DynamicAnimation.TRANSLATION_Y, offset);
            Log.d("helloCL", String.valueOf(clBody.getHeight()));
            Log.d("helloV", String.valueOf(vBack.getHeight()));
            Log.d("helloIV", String.valueOf(ivPlay.getHeight()));
            Log.d("helloOFF", String.valueOf(offset));
            springAnim.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
            springAnim.setMinValue(0);
            springAnim.setMaxValue(max);
            Log.d("hello1", String.valueOf(springAnim.getSpring().getFinalPosition()));
            springAnim.addEndListener((dynamicAnimation, b, v, v1) -> {
                Log.d("hello", String.valueOf(springAnim.getSpring().getFinalPosition()));
                springAnim.animateToFinalPosition(springAnim.getSpring().getFinalPosition() > 0 ? 0 : offset);
            });
            springAnim.start();
        });
    }
}
