package com.cardapp.cardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private int _xDelta;
    private int _yDelta;
    private float startX;
    private float startY;
    private List<Card> cardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        setLayout();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setLayout() {
        Collections.shuffle(cardList);
        frameLayout = findViewById(R.id.layoutMain);
        frameLayout.removeAllViewsInLayout();
        Button btnShuffle = new Button(this);
        btnShuffle.setText("Shuffle");
        btnShuffle.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShuffle.setX(10);
        btnShuffle.setY(10);
        btnShuffle.setBackground(getDrawable(R.drawable.btn_background));

        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayout();
            }
        });

        frameLayout.addView(btnShuffle);
        Button btnBack = new Button(this);
        btnBack.setText("Back");
        btnBack.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnBack.setX(300);
        btnBack.setY(btnShuffle.getY());
        btnBack.setBackground(getDrawable(R.drawable.btn_background));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        frameLayout.addView(btnBack);

        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        for(int i = 0; i <= 77; i++){
            ImageView img = new ImageView(this);
            img.setImageResource(R.drawable.bb);
            img.setLayoutParams(new FrameLayout.LayoutParams(263,405));
            img.setX(200);
            img.setY(300);
            img.setZ(i+1);
            frameLayout.addView(img);
            int pos = i;

            img.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int X = (int) event.getRawX();
                    final int Y = (int) event.getRawY();
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_DOWN:
                            FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) v.getLayoutParams();
                            _xDelta = X - lParams.leftMargin;
                            _yDelta = Y - lParams.topMargin;
                            startX = event.getX();
                            startY = event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            float endX = event.getX();
                            float endY = event.getY();
                            if (isAClick(startX, endX, startY, endY)) {
                                img.setImageResource(cardList.get(pos).getImgSrc());
                            }
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) v.getLayoutParams();
                            layoutParams.leftMargin = X- _xDelta;
                            layoutParams.topMargin = Y - _yDelta;
                            layoutParams.rightMargin = -250;
                            layoutParams.bottomMargin = -250;
                            v.setLayoutParams(layoutParams);
                            break;
                    }
                    frameLayout.invalidate();
                    return true;
                }
            });
        }
    }

    private void setData() {
        cardList = new ArrayList<>();

        cardList.add(new Card(R.drawable.c01));
        cardList.add(new Card(R.drawable.c02));
        cardList.add(new Card(R.drawable.c03));
        cardList.add(new Card(R.drawable.c04));
        cardList.add(new Card(R.drawable.c05));
        cardList.add(new Card(R.drawable.c06));
        cardList.add(new Card(R.drawable.c07));
        cardList.add(new Card(R.drawable.c08));
        cardList.add(new Card(R.drawable.c09));
        cardList.add(new Card(R.drawable.c10));
        cardList.add(new Card(R.drawable.c11));
        cardList.add(new Card(R.drawable.c12));
        cardList.add(new Card(R.drawable.c13));
        cardList.add(new Card(R.drawable.c14));

        cardList.add(new Card(R.drawable.m00));
        cardList.add(new Card(R.drawable.m01));
        cardList.add(new Card(R.drawable.m02));
        cardList.add(new Card(R.drawable.m03));
        cardList.add(new Card(R.drawable.m04));
        cardList.add(new Card(R.drawable.m05));
        cardList.add(new Card(R.drawable.m06));
        cardList.add(new Card(R.drawable.m07));
        cardList.add(new Card(R.drawable.m08));
        cardList.add(new Card(R.drawable.m09));
        cardList.add(new Card(R.drawable.m10));
        cardList.add(new Card(R.drawable.m11));
        cardList.add(new Card(R.drawable.m12));
        cardList.add(new Card(R.drawable.m13));
        cardList.add(new Card(R.drawable.m14));
        cardList.add(new Card(R.drawable.m15));
        cardList.add(new Card(R.drawable.m16));
        cardList.add(new Card(R.drawable.m17));
        cardList.add(new Card(R.drawable.m18));
        cardList.add(new Card(R.drawable.m19));
        cardList.add(new Card(R.drawable.m20));
        cardList.add(new Card(R.drawable.m21));

        cardList.add(new Card(R.drawable.p01));
        cardList.add(new Card(R.drawable.p02));
        cardList.add(new Card(R.drawable.p03));
        cardList.add(new Card(R.drawable.p04));
        cardList.add(new Card(R.drawable.p05));
        cardList.add(new Card(R.drawable.p06));
        cardList.add(new Card(R.drawable.p07));
        cardList.add(new Card(R.drawable.p08));
        cardList.add(new Card(R.drawable.p09));
        cardList.add(new Card(R.drawable.p10));
        cardList.add(new Card(R.drawable.p11));
        cardList.add(new Card(R.drawable.p12));
        cardList.add(new Card(R.drawable.p13));
        cardList.add(new Card(R.drawable.p14));

        cardList.add(new Card(R.drawable.s01));
        cardList.add(new Card(R.drawable.s02));
        cardList.add(new Card(R.drawable.s03));
        cardList.add(new Card(R.drawable.s04));
        cardList.add(new Card(R.drawable.s05));
        cardList.add(new Card(R.drawable.s06));
        cardList.add(new Card(R.drawable.s07));
        cardList.add(new Card(R.drawable.s08));
        cardList.add(new Card(R.drawable.s09));
        cardList.add(new Card(R.drawable.s10));
        cardList.add(new Card(R.drawable.s11));
        cardList.add(new Card(R.drawable.s12));
        cardList.add(new Card(R.drawable.s13));
        cardList.add(new Card(R.drawable.s14));

        cardList.add(new Card(R.drawable.w01));
        cardList.add(new Card(R.drawable.w02));
        cardList.add(new Card(R.drawable.w03));
        cardList.add(new Card(R.drawable.w04));
        cardList.add(new Card(R.drawable.w05));
        cardList.add(new Card(R.drawable.w06));
        cardList.add(new Card(R.drawable.w07));
        cardList.add(new Card(R.drawable.w08));
        cardList.add(new Card(R.drawable.w09));
        cardList.add(new Card(R.drawable.w10));
        cardList.add(new Card(R.drawable.w11));
        cardList.add(new Card(R.drawable.w12));
        cardList.add(new Card(R.drawable.w13));
        cardList.add(new Card(R.drawable.w14));

    }

    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > 1 || differenceY > 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}