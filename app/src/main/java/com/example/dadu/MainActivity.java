package com.example.dadu;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout homeLayout, storeLayout, achievementsLayout, menuLayout;
    private LottieAnimationView homeImage, storeImage, achievementsImage, menuImage;
    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeLayout = findViewById(R.id.homeLayout);
        storeLayout = findViewById(R.id.storeLayout);
        achievementsLayout = findViewById(R.id.achievementsLayout);
        menuLayout = findViewById(R.id.menuLayout);

        homeImage = findViewById(R.id.homeImage);
        storeImage = findViewById(R.id.storeImage);
        achievementsImage = findViewById(R.id.achievementsImage);
        menuImage = findViewById(R.id.menuImage);

        replaceFragment(new HomeFragment());
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_15));

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(1);
            }
        });

        storeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(2);
            }
        });

        achievementsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(3);
            }
        });

        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(4);
            }
        });
    }

    private void changeFragment(int tab) {
        changeColors();
        resetIcons();

        switch (tab) {
            case 1:
                if (selectedTab != 1) {
                    replaceFragment(new HomeFragment());
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.blue_15));
                    selectedTab = 1;
                }
                break;
            case 2:
                if (selectedTab != 2) {
                    replaceFragment(new StoreFragment());
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.green_15));
                    selectedTab = 2;
                }
                break;
            case 3:
                if (selectedTab != 3) {
                    replaceFragment(new AchievementsFragment());
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.yellow_15));
                    selectedTab = 3;
                }
                break;
            case 4:
                if (selectedTab != 4) {
                    replaceFragment(new StoreFragment());
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.green_15));
                    selectedTab = 4;
                }
                break;
        }

        changeIcons();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void changeColors() {
        homeLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        storeLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        achievementsLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        menuLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void resetIcons() {
        homeImage.clearAnimation();
        storeImage.clearAnimation();
        achievementsImage.clearAnimation();
        menuImage.clearAnimation();

        homeImage.setAnimation(R.raw.menu_outline);
        storeImage.setAnimation(R.raw.menu_outline);
        achievementsImage.setAnimation(R.raw.menu_outline);
        menuImage.setAnimation(R.raw.menu_outline);
    }

    private void changeIcons() {
        switch (selectedTab) {
            case 1:
                homeImage.setAnimation(R.raw.menu_outline_selected);
                homeImage.setColorFilter(R.color.blue);
                homeImage.playAnimation();
                break;
            case 2:
                storeImage.setAnimation(R.raw.menu_outline_selected);
                storeImage.setColorFilter(R.color.green);
                storeImage.playAnimation();
                break;
            case 3:
                achievementsImage.setAnimation(R.raw.menu_outline_selected);
                achievementsImage.setColorFilter(R.color.yellow);
                achievementsImage.playAnimation();
                break;
            case 4:
                menuImage.setAnimation(R.raw.menu_outline_selected);
                menuImage.setColorFilter(R.color.purple);
                menuImage.playAnimation();
                break;
        }
    }
}