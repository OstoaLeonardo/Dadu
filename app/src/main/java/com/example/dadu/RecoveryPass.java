package com.example.dadu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecoveryPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_pass);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerRP, GetCodeFragment.class, null)
                .commit();
    }
}