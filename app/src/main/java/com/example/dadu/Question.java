package com.example.dadu;

import android.animation.ObjectAnimator;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.dadu.db.DBHelper;
import com.example.dadu.db.DBManager;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question extends AppCompatActivity {

    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private ImageView imgQuestion;
    private String tableName;
    private String[] questionData;
    private LinearProgressIndicator progressBar;
    private CountDownTimer countDownTimer;
    private final long TIMER_DURATION = 15000;
    private final int PROGRESS_BAR_MAX = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        progressBar = findViewById(R.id.progressBar);
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

        String subject = getIntent().getStringExtra("subject");
        setTitle(getSubjectTitle(subject));

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        tableName = getTableName(subject);
        questionData = DBManager.getRandomQuestion(db, tableName);

        lblQuestion.setText(questionData[0]);
        shuffleAndSetAnswers(questionData);

        String imageUrl = questionData[5];
        Glide.with(this)
                .load(imageUrl)
                .into(imgQuestion);

        db.close();

        startTimer();

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
        } else {
            message = "Respuesta incorrecta";
        }

        getNextRandomQuestion();
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

        cancelTimer();
        startTimer();
    }

    private void startTimer() {
        progressBar.setMax(PROGRESS_BAR_MAX);
        progressBar.setProgress(PROGRESS_BAR_MAX);

        countDownTimer = new CountDownTimer(TIMER_DURATION, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                long elapsedTime = TIMER_DURATION - millisUntilFinished;
                int progress = (int) (elapsedTime * PROGRESS_BAR_MAX / TIMER_DURATION);
                progressBar.setProgress(PROGRESS_BAR_MAX - progress, true);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0, true);
                Snackbar.make(findViewById(android.R.id.content), "Tiempo agotado", Snackbar.LENGTH_SHORT).show();
                getNextRandomQuestion();
            }
        };

        countDownTimer.start();
    }

    private void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private String getSubjectTitle(String subject) {
        switch (subject) {
            case "mathematics":
                return getResources().getString(R.string.mathematics);
            case "geography":
                return getResources().getString(R.string.geography);
            case "history":
                return getResources().getString(R.string.history);
            case "sports":
                return getResources().getString(R.string.sports);
            case "sciences":
                return getResources().getString(R.string.natural_sciences);
            case "random":
                return getResources().getString(R.string.random);
            default:
                return "";
        }
    }
}