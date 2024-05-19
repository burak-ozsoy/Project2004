package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.MonthsActivity;
import com.example.project2004v2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MonthsPlayActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView feedbackText;
    private TextView questionText;
    private int score = 0;
    private String[] months_of_year;
    private int currentMonthIndex;
    private List<String> options;
    private Button btnOption1, btnOption2, btnOption3;
    private Random random;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months_play);

        scoreText = findViewById(R.id.score_text);
        feedbackText = findViewById(R.id.feedback_text);
        questionText = findViewById(R.id.question_text);
        btnOption1 = findViewById(R.id.btn_option_1);
        btnOption2 = findViewById(R.id.btn_option_2);
        btnOption3 = findViewById(R.id.btn_option_3);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        months_of_year = getResources().getStringArray(R.array.months_of_year);
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
                Intent intent = new Intent(MonthsPlayActivity.this, MonthsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        showNextQuestion();
    }



    private void showNextQuestion() {
        currentMonthIndex = random.nextInt(months_of_year.length);
        String currentMonth = months_of_year[currentMonthIndex];
        String nextMonth = months_of_year[(currentMonthIndex + 1) % months_of_year.length];

        options.clear();
        options.add(nextMonth);

        // Farklı iki yanlış seçeneği ekle
        while (options.size() < 3) {
            String month = months_of_year[random.nextInt(months_of_year.length)];
            if (!options.contains(month) && !month.equals(currentMonth)) {
                options.add(month);
            }
        }

        Collections.shuffle(options);

        btnOption1.setText(options.get(0));
        btnOption2.setText(options.get(1));
        btnOption3.setText(options.get(2));

        questionText.setText("Which month comes after " + currentMonth + "?");
    }
    private void checkAnswer(String selectedMonth) {
        String correctMonth = months_of_year[(currentMonthIndex + 1) % months_of_year.length];
        if (selectedMonth.equals(correctMonth)) {
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