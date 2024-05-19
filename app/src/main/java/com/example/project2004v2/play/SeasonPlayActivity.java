package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.SeasonActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SeasonPlayActivity extends AppCompatActivity {

    private ImageView seasonImageView;
    private Button option1Button, option2Button, option3Button;
    private TextView titleTextView, scoreTextView, feedbackTextView;

    private ArrayList<Integer> seasonImages;
    private ArrayList<String> seasonNames;
    private int currentIndex;
    private String correctAnswer;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_play);

        seasonImageView = findViewById(R.id.seasonImageView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        Button btnGoBack = findViewById(R.id.btn_go_back);
        titleTextView = findViewById(R.id.title);
        scoreTextView = findViewById(R.id.score_text);
        feedbackTextView = findViewById(R.id.feedback_text);

        // Initialize the ArrayLists
        seasonImages = new ArrayList<>();
        seasonImages.add(R.drawable.spring);
        seasonImages.add(R.drawable.summer);
        seasonImages.add(R.drawable.fall);
        seasonImages.add(R.drawable.winter);

        seasonNames = new ArrayList<>();
        seasonNames.add("Spring");
        seasonNames.add("Summer");
        seasonNames.add("Fall");
        seasonNames.add("Winter");

        // Initialize the game
        startNewRound();

        // Set OnClickListener for the go back button
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonPlayActivity.this, SeasonActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startNewRound() {
        // Randomly select an image and its corresponding name
        Random random = new Random();
        currentIndex = random.nextInt(seasonImages.size());
        seasonImageView.setImageResource(seasonImages.get(currentIndex));
        correctAnswer = seasonNames.get(currentIndex);

        // Generate options
        List<String> options = new ArrayList<>(seasonNames);
        options.remove(correctAnswer);
        Collections.shuffle(options);

        // Select two wrong options
        List<String> displayedOptions = options.subList(0, 2);
        displayedOptions.add(correctAnswer);
        Collections.shuffle(displayedOptions);

        // Set the text for the buttons
        option1Button.setText(displayedOptions.get(0));
        option2Button.setText(displayedOptions.get(1));
        option3Button.setText(displayedOptions.get(2));
    }

    public void checkAnswer(View view) {
        Button clickedButton = (Button) view;
        String chosenAnswer = clickedButton.getText().toString();

        if (chosenAnswer.equals(correctAnswer)) {
            score++;
            feedbackTextView.setText("Correct!");
            Toast.makeText(this, "Correct! Your score: " + score, Toast.LENGTH_SHORT).show();
        } else {
            feedbackTextView.setText("Incorrect! Try again.");
            Toast.makeText(this, "Incorrect! Try again. Your score: " + score, Toast.LENGTH_SHORT).show();
        }

        // Update the score
        scoreTextView.setText("Score: " + score);

        // Start a new round
        startNewRound();
    }
}
