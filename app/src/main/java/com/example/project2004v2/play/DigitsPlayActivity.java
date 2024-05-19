package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.DigitsActivity;
import com.example.project2004v2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DigitsPlayActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView digitText;
    private EditText inputText;
    private int score = 0;
    private List<Integer> digitSequence;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_play);

        scoreText = findViewById(R.id.score_text);
        digitText = findViewById(R.id.digit_text);
        inputText = findViewById(R.id.input_text);
        Button btnSubmit = findViewById(R.id.btn_submit);
        Button btnNewGame = findViewById(R.id.btn_new_game);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        digitSequence = new ArrayList<>();
        random = new Random();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DigitsPlayActivity.this, DigitsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk sayıları göster
        startNewGame();
    }

    private void startNewGame() {
        score = 0;
        scoreText.setText("Score: " + score);
        generateNewDigits();
    }

    private void generateNewDigits() {
        digitSequence.clear();
        for (int i = 0; i < 4; i++) {
            digitSequence.add(random.nextInt(10));
        }
        digitText.setText(digitSequence.toString().replaceAll("[\\[\\],]", ""));
    }

    private void checkAnswer() {
        String input = inputText.getText().toString().trim();
        inputText.setText("");

        List<Integer> inputSequence = new ArrayList<>();
        for (char ch : input.toCharArray()) {
            inputSequence.add(Character.getNumericValue(ch));
        }

        List<Integer> sortedSequence = new ArrayList<>(digitSequence);
        Collections.sort(sortedSequence);

        if (inputSequence.equals(sortedSequence)) {
            score++;
            generateNewDigits();
        } else {
            digitText.setText("Try Again");
        }
        scoreText.setText("Score: " + score);
    }
}
