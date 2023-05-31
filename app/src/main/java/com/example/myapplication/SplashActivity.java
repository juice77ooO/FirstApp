package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;



public class SplashActivity extends AppCompatActivity {

    private LinearLayout ll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        指定id
        ll = findViewById(R.id.main_ll);

//        设置图标渐变并跳转
        setAlphaAnimation();
    }
//    渐变
    private void setAlphaAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(3000);
        ll.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jump2Activity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
//    跳转页面
    private void jump2Activity() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String First = sharedPreferences.getString("isFirst", "0");
        Intent intent = new Intent();
        if ("0".equals(First)){
            intent.setClass(this, GuideActivity.class);
        }else{
            intent.setClass(this, MainActivity.class);
        }
        startActivity(intent);

    }
}
