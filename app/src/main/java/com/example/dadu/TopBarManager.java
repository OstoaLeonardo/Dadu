package com.example.dadu;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

public class TopBarManager {
    private MaterialToolbar topAppBar;

    public void setupTopBar(AppCompatActivity activity) {
        topAppBar = activity.findViewById(R.id.topAppBar);
        activity.setSupportActionBar(topAppBar);
    }

    public void setNavigationIcon(Drawable icon, View.OnClickListener listener) {
        topAppBar.setNavigationIcon(icon);
        topAppBar.setNavigationOnClickListener(listener);
    }

    public void setMenu(@MenuRes int menuRes, Toolbar.OnMenuItemClickListener listener) {
        topAppBar.getMenu().clear();
        topAppBar.inflateMenu(menuRes);
        topAppBar.setOnMenuItemClickListener(listener);
    }
}
