package com.example.dadu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
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

public class MainActivity extends AppCompatActivity {

    private LinearLayout homeLayout, storeLayout, achievementsLayout;
    private LottieAnimationView homeImage, storeImage, achievementsImage;
    private TextView homeText, storeText, achievementsText;
    private int selectedTab = 1;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_RUN = "firstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        DrawerManager drawerManager = new DrawerManager();
        drawerManager.setupDrawer(this, drawerLayout);

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
                    animateIcon(homeLayout, R.drawable.round_back_home);
                    selectedTab = 1;
                }
                break;
            case 2:
                if (selectedTab != 2) {
                    replaceFragment(new StoreFragment());
                    animateIcon(storeLayout, R.drawable.round_back_store);
                    selectedTab = 2;
                }
                break;
            case 3:
                if (selectedTab != 3) {
                    replaceFragment(new AchievementsFragment());
                    animateIcon(achievementsLayout, R.drawable.round_back_achievements);
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

    private void animateIcon(LinearLayout layout, int animationResource) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setFillAfter(true);
        layout.startAnimation(scaleAnimation);

        layout.setBackgroundResource(animationResource);

        LottieAnimationView animationView;
        TextView textView;

        switch (layout.getId()) {
            case R.id.homeLayout:
                animationView = homeImage;
                textView = homeText;
                break;
            case R.id.storeLayout:
                animationView = storeImage;
                textView = storeText;
                break;
            case R.id.achievementsLayout:
                animationView = achievementsImage;
                textView = achievementsText;
                break;
            default:
                return;
        }

        animationView.setAnimation(animationResource);
        animationView.playAnimation();
        textView.setVisibility(View.VISIBLE);
    }

    public void doThis(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEY_FIRST_RUN, true);
        editor.apply();
    }

    public void goTools(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Tools.class);
        startActivity(intent);
    }

    public void goMyAccount(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, MyAccount.class);
        startActivity(intent);
    }
}