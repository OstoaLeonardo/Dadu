package com.example.dadu;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.dadu.db.DBHelper;
import com.example.dadu.db.DBManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question extends AppCompatActivity {

    private TextView lblSubject;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private ImageView imgQuestion;
    private String tableName;
    private String[] questionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        lblSubject = findViewById(R.id.lblSubject);
        TextView lblQuestion = findViewById(R.id.lblQuestion);
        imgQuestion = findViewById(R.id.imgQuestion);
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);

        TopBarManager topBar = new TopBarManager();
        topBar.setupTopBar(this);
        topBar.setNavigationIcon(
                ContextCompat.getDrawable(this, R.drawable.arrow_small_left),
                view -> onBackPressed()
        );

        showSubject();

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String subject = getIntent().getStringExtra("subject");

        tableName = getTableName(subject);
        questionData = DBManager.getRandomQuestion(db, tableName);

        lblQuestion.setText(questionData[0]);
        shuffleAndSetAnswers(questionData);

        String imageUrl = questionData[5];
        Glide.with(this)
                .load(imageUrl)
                .into(imgQuestion);

        db.close();

        btnAnswer1.setOnClickListener(view -> {
            String selectedAnswer = btnAnswer1.getText().toString();
            checkAnswer(selectedAnswer, questionData[1]);
        });

        btnAnswer2.setOnClickListener(view -> {
            String selectedAnswer = btnAnswer2.getText().toString();
            checkAnswer(selectedAnswer, questionData[1]);
        });

        btnAnswer3.setOnClickListener(view -> {
            String selectedAnswer = btnAnswer3.getText().toString();
            checkAnswer(selectedAnswer, questionData[1]);
        });

        btnAnswer4.setOnClickListener(view -> {
            String selectedAnswer = btnAnswer4.getText().toString();
            checkAnswer(selectedAnswer, questionData[1]);
        });

    }

    private String getTableName(String subject) {
        switch (subject) {
            case "mathematics":
                return DBHelper.TABLE_MATHEMATICS;
            case "geography":
                return DBHelper.TABLE_GEOGRAPHY;
            case "history":
                return DBHelper.TABLE_HISTORY;
            case "sports":
                return DBHelper.TABLE_SPORTS;
            case "sciences":
                return DBHelper.TABLE_SCIENCES;
            case "random":
                return "random";
            default:
                return "";
        }
    }

    private void shuffleAndSetAnswers(String[] questionData) {
        List<String> answers = Arrays.asList(questionData[1], questionData[2], questionData[3], questionData[4]);
        Collections.shuffle(answers);

        btnAnswer1.setText(answers.get(0));
        btnAnswer2.setText(answers.get(1));
        btnAnswer3.setText(answers.get(2));
        btnAnswer4.setText(answers.get(3));
    }

    private void checkAnswer(String selectedAnswer, String correctAnswer) {
        String message;

        if (selectedAnswer.equals(correctAnswer)) {
            message = "Respuesta correcta";
            getNextRandomQuestion();

        } else {
            message = "Respuesta incorrecta";
        }

        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    private void getNextRandomQuestion() {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        questionData = DBManager.getRandomQuestion(db, tableName);

        TextView lblQuestion = findViewById(R.id.lblQuestion);
        lblQuestion.setText(questionData[0]);
        shuffleAndSetAnswers(questionData);

        String imageUrl = questionData[5];
        Glide.with(this)
                .load(imageUrl)
                .into(imgQuestion);

        db.close();
    }

    private void showSubject() {
        String subject = getIntent().getStringExtra("subject");

        switch (subject) {
            case "mathematics": lblSubject.setText(R.string.mathematics); break;
            case "geography": lblSubject.setText(R.string.geography); break;
            case "history": lblSubject.setText(R.string.history); break;
            case "sports": lblSubject.setText(R.string.sports); break;
            case "sciences": lblSubject.setText(R.string.natural_sciences); break;
            case "random" : lblSubject.setText(R.string.random); break;
        }
    }
}