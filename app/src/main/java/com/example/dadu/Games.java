package com.example.dadu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dadu.db.DBHelper;

public class Games extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        // Crear la base de datos
        /*DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.insertMathematics(db);
        dbHelper.insertGeography(db);
        dbHelper.insertHistory(db);
        dbHelper.insertSports(db);
        dbHelper.insertSciences(db);
        db.close();*/

        TopBarManager topBar = new TopBarManager();
        topBar.setupTopBar(this);
        topBar.setNavigationIcon(
                ContextCompat.getDrawable(this, R.drawable.arrow_small_left),
                view -> onBackPressed()
        );
    }

    public void goQuestion(View view) {
        String cardTag = (String) view.getTag();

        Intent intent = new Intent(this, Question.class);
        intent.putExtra("subject", cardTag);
        startActivity(intent);
    }
}