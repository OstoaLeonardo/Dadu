package com.example.dadu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.BuildConfig;

public class MainActivity extends AppCompatActivity {

    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout storeLayout = findViewById(R.id.storeLayout);
        final LinearLayout achievementsLayout = findViewById(R.id.achievementsLayout);
        final LinearLayout menuLayout = findViewById(R.id.menuLayout);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView storeImage = findViewById(R.id.storeImage);
        final ImageView achievementsImage = findViewById(R.id.achievementsImage);
        final ImageView menuImage = findViewById(R.id.menuImage);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, HomeFragment.class, null).commit();

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 1) {
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, HomeFragment.class, null).commit();

                    getWindow().setStatusBarColor(getResources().getColor(R.color.blue_15));

                    storeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    achievementsLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    menuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeImage.setImageResource(R.drawable.home_selected);
                    storeImage.setImageResource(R.drawable.store);
                    achievementsImage.setImageResource(R.drawable.achievements);
                    menuImage.setImageResource(R.drawable.menu);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    selectedTab = 1;
                }
            }
        });

        storeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 2) {
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, StoreFragment.class, null).commit();

                    getWindow().setStatusBarColor(getResources().getColor(R.color.green_15));

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    achievementsLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    menuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeImage.setImageResource(R.drawable.home);
                    storeImage.setImageResource(R.drawable.store_selected);
                    achievementsImage.setImageResource(R.drawable.achievements);
                    menuImage.setImageResource(R.drawable.menu);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    storeLayout.startAnimation(scaleAnimation);

                    selectedTab = 2;
                }
            }
        });

        achievementsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 3) {
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, AchievementsFragment.class, null).commit();

                    getWindow().setStatusBarColor(getResources().getColor(R.color.yellow_15));

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    storeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    menuLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeImage.setImageResource(R.drawable.home);
                    storeImage.setImageResource(R.drawable.store);
                    achievementsImage.setImageResource(R.drawable.achievements_selected);
                    menuImage.setImageResource(R.drawable.menu);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    achievementsLayout.startAnimation(scaleAnimation);

                    selectedTab = 3;
                }
            }
        });

        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab != 4) {
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragmentContainer, HomeFragment.class, null).commit();

                    getWindow().setStatusBarColor(getResources().getColor(R.color.blue_15));

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    storeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    achievementsLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeImage.setImageResource(R.drawable.home);
                    storeImage.setImageResource(R.drawable.store);
                    achievementsImage.setImageResource(R.drawable.achievements);
                    menuImage.setImageResource(R.drawable.menu_selected);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    menuLayout.startAnimation(scaleAnimation);

                    selectedTab = 4;
                }
            }
        });
    }

}