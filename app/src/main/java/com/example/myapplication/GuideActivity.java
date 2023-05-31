package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.GuideActivity;
import com.example.myapplication.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<ImageView> imageViews;
    private int[] imgs = {R.drawable.y0, R.drawable.y1, R.drawable.y2, R.drawable.y3};
    private Button btn;
    private ImageView[] dotViews;
    private GuideAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        vp = findViewById(R.id.guide_vp);
        btn = findViewById(R.id.guide_btn);

//        初始化图片
        initImages();
//        初始化底部圆点指示器
        initDots();
        adapter = new GuideAdapter(imageViews);
        vp.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                SharedPreferences.Editor editor = getSharedPreferences("data",
//                        MODE_PRIVATE).edit();
//                editor.putString("isFirst", "1");
//                editor.commit();
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i < dotViews.length; i++) {
                    if(position == i){
                        dotViews[i].setImageResource(R.drawable.guide_selector);
                    }else{
                        dotViews[i].setImageResource(R.drawable.guide_white);
                    }

                    if (position == dotViews.length - 1) {
                        btn.setVisibility(View.VISIBLE);
                    }else {
                        btn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /**
     * 初始化底部圆点指示器
     */
    private void initDots() {
        LinearLayout layout = findViewById(R.id.guide_ll);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
        params.setMargins(10, 0, 10, 0);
        dotViews = new ImageView[imgs.length];
        for(int i = 0; i < imageViews.size(); i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.guide_white);
            if (i == 0){
                imageView.setImageResource(R.drawable.guide_selector);
            }else {
                imageView.setImageResource(R.drawable.guide_white);
            }
            dotViews[i] = imageView;
            final int finalI = i;
            dotViews[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    vp.setCurrentItem(finalI);
                }
            });
            layout.addView(imageView);
        }
    }
    /**
     * 初始化图片
     */
    private void initImages(){
//        适应屏幕
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        imageViews = new ArrayList<ImageView>();
        for(int i = 0; i < imgs.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageResource(imgs[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
    }
}
















