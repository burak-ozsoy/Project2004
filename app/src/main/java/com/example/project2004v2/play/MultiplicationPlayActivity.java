package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.MultiplicationActivity;
import com.example.project2004v2.R;

import java.util.Random;

public class MultiplicationPlayActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView multiplicationProblem;
    private EditText answerInput;
    private int score = 0;
    private Random random;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_play);

        scoreText = findViewById(R.id.score_text);
        multiplicationProblem = findViewById(R.id.multiplication_problem);
        answerInput = findViewById(R.id.answer_input);
        Button btnSubmit = findViewById(R.id.btn_submit);
        Button btnNewGame = findViewById(R.id.btn_new_game);
        Button btnGoBack = findViewById(R.id.btn_go_back);

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
                Intent intent = new Intent(MultiplicationPlayActivity.this, MultiplicationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk problemi göster
        generateNewProblem();
    }

    private void startNewGame() {
        score = 0;
        scoreText.setText("Score: " + score);
        generateNewProblem();
    }

    private void generateNewProblem() {
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;
        correctAnswer = num1 * num2;
        multiplicationProblem.setText(num1 + " x " + num2 + " = ?");
    }

    private void checkAnswer() {
        String input = answerInput.getText().toString().trim();
        if (!input.isEmpty()) {
            int answer = Integer.parseInt(input);
            if (answer == correctAnswer) {
                score++;
                scoreText.setText("Score: " + score);
                generateNewProblem();
            } else {
                scoreText.setText("Try again! Score: " + score);
            }
            answerInput.setText("");
        }
    }
}
