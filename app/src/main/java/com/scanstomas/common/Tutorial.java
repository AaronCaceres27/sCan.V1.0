package com.scanstomas.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scanstomas.R;
import com.scanstomas.RegistroActivity;
import com.scanstomas.adapter.SliderAdapter;

public class Tutorial extends AppCompatActivity {

    ViewPager viewPager2;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button comencemos;

    Animation animacion;
    Button next;
    Button skip;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutorial);

        //hooks
        viewPager2 = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        comencemos = findViewById(R.id.BtnComencemos);
        next = findViewById(R.id.next_btn);
        skip = findViewById(R.id.skip_btn);

        //llamar adapter
        sliderAdapter = new SliderAdapter(Tutorial.this);
        viewPager2.setAdapter(sliderAdapter);

        addDots(0);
        viewPager2.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
        finish();
    }
    public void next(View view){
        viewPager2.setCurrentItem(currentPos+1);
    }
    public void sig(View view){
        startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
        finish();
    }
    private void addDots(int position){

        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i] =new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.teal_200));
        }

    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;
            if(position == 0){
                comencemos.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
            }
            else if (position == 1){
                comencemos.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
            }
            else if (position ==2){
                comencemos.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
            }else{
                animacion = AnimationUtils.loadAnimation(Tutorial.this,R.anim.bottom_anim);
                comencemos.setAnimation(animacion);
                comencemos.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);
                skip.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}