package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.DaysActivity;
import com.example.project2004v2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DaysPlayActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView feedbackText;
    private TextView questionText;
    private int score = 0;
    private String[] daysOfWeek;
    private int currentDayIndex;
    private List<String> options;
    private Button btnOption1, btnOption2, btnOption3;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_play);

        scoreText = findViewById(R.id.score_text);
        feedbackText = findViewById(R.id.feedback_text);
        questionText = findViewById(R.id.question_text);
        btnOption1 = findViewById(R.id.btn_option_1);
        btnOption2 = findViewById(R.id.btn_option_2);
        btnOption3 = findViewById(R.id.btn_option_3);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        daysOfWeek = getResources().getStringArray(R.array.days_of_week);
        options = new ArrayList<>();
        random = new Random();

        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnOption1.getText().toString());
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnOption2.getText().toString());
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnOption3.getText().toString());
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaysPlayActivity.this, DaysActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk soruyu göster
        showNextQuestion();
    }

    private void showNextQuestion() {
        currentDayIndex = random.nextInt(daysOfWeek.length);
        String currentDay = daysOfWeek[currentDayIndex];
        String nextDay = daysOfWeek[(currentDayIndex + 1) % daysOfWeek.length];

        options.clear();
        options.add(nextDay);

        // Farklı iki yanlış seçeneği ekle
        while (options.size() < 3) {
            String day = daysOfWeek[random.nextInt(daysOfWeek.length)];
            if (!options.contains(day) && !day.equals(currentDay)) {
                options.add(day);
            }
        }

        Collections.shuffle(options);

        btnOption1.setText(options.get(0));
        btnOption2.setText(options.get(1));
        btnOption3.setText(options.get(2));

        questionText.setText("Which day comes after " + currentDay + "?");
    }

    private void checkAnswer(String selectedDay) {
        String correctDay = daysOfWeek[(currentDayIndex + 1) % daysOfWeek.length];
        if (selectedDay.equals(correctDay)) {
            score++;
            feedbackText.setText("Correct!");
        } else {
            feedbackText.setText("Try again!");
        }
        scoreText.setText("Score: " + score);

        // Bir süre sonra geri bildirim metnini temizle
        feedbackText.postDelayed(new Runnable() {
            @Override
            public void run() {
                feedbackText.setText("");
            }
        }, 1000);

        // Bir sonraki soruya geç
        showNextQuestion();
    }
}
