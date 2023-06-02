package com.example.dadu;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout homeLayout, storeLayout, achievementsLayout;
    private LottieAnimationView homeImage, storeImage, achievementsImage;

    private MaterialToolbar topAppBar;
    private TextView homeText, storeText, achievementsText;
    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        DrawerManager drawerManager = new DrawerManager();
        drawerManager.setupDrawer(this, drawerLayout);

        topAppBar = findViewById(R.id.topAppBar);

        TopBarManager topBar = new TopBarManager();
        topBar.setupTopBar(this);
        topBar.setNavigationIcon(
                ContextCompat.getDrawable(this, R.drawable.menu),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            drawerLayout.openDrawer(GravityCompat.START);
                        }
                    }
                }
        );

        homeLayout = findViewById(R.id.homeLayout);
        storeLayout = findViewById(R.id.storeLayout);
        achievementsLayout = findViewById(R.id.achievementsLayout);

        homeImage = findViewById(R.id.homeImage);
        storeImage = findViewById(R.id.storeImage);
        achievementsImage = findViewById(R.id.achievementsImage);

        homeText = findViewById(R.id.homeText);
        storeText = findViewById(R.id.storeText);
        achievementsText = findViewById(R.id.achievementsText);

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
    }

    private void changeFragment(int tab) {
        changeColors();
        resetIcons();

        switch (tab) {
            case 1:
                if (selectedTab != 1) {
                    replaceFragment(new HomeFragment());
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.blue_15));
                    topAppBar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.blue_15));
                    homeLayout.setBackgroundResource(R.drawable.round_back_home);
                    homeText.setVisibility(View.VISIBLE);
                    selectedTab = 1;
                }
                break;
            case 2:
                if (selectedTab != 2) {
                    replaceFragment(new StoreFragment());
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.green_15));
                    topAppBar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.green_15));
                    storeLayout.setBackgroundResource(R.drawable.round_back_store);
                    storeText.setVisibility(View.VISIBLE);
                    selectedTab = 2;
                }
                break;
            case 3:
                if (selectedTab != 3) {
                    replaceFragment(new AchievementsFragment());
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.yellow_15));
                    topAppBar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.yellow_15));
                    achievementsLayout.setBackgroundResource(R.drawable.round_back_achievements);
                    achievementsText.setVisibility(View.VISIBLE);
                    selectedTab = 3;
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
    }

    private void resetIcons() {
        homeText.setVisibility(View.GONE);
        storeText.setVisibility(View.GONE);
        achievementsText.setVisibility(View.GONE);

        homeImage.setAnimation(R.raw.gamepad);
        storeImage.setAnimation(R.raw.coin);
        achievementsImage.setAnimation(R.raw.trophy);
    }

    private void changeIcons() {
        switch (selectedTab) {
            case 1:
                homeImage.setAnimation(R.raw.gamepad);
                homeImage.playAnimation();
                break;
            case 2:
                storeImage.setAnimation(R.raw.coin);
                storeImage.playAnimation();
                break;
            case 3:
                achievementsImage.setAnimation(R.raw.trophy);
                achievementsImage.playAnimation();
                break;
        }
    }
}