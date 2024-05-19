package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.SimilarPicturesActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimilarPicturesPlayActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView feedbackText;
    private TextView questionText;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private String[] images = {"pic1a", "pic1b", "pic2a", "pic2b", "pic3a", "pic3b", "pic4a", "pic4b", "pic5a", "pic5b", "pic6a", "pic6b"};
    private ImageView questionImageView;
    private ImageView option1, option2, option3;
    private Map<String, String> correctImageMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pictures_play);

        scoreText = findViewById(R.id.score_text);
        feedbackText = findViewById(R.id.feedback_text);
        questionText = findViewById(R.id.question_text);
        questionImageView = findViewById(R.id.question_image);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        correctImageMap = new HashMap<>();
        correctImageMap.put("pic1a", "pic1b");
        correctImageMap.put("pic1b", "pic1a");
        correctImageMap.put("pic2a", "pic2b");
        correctImageMap.put("pic2b", "pic2a");
        correctImageMap.put("pic3a", "pic3b");
        correctImageMap.put("pic3b", "pic3a");
        correctImageMap.put("pic4a", "pic4b");
        correctImageMap.put("pic4b", "pic4a");
        correctImageMap.put("pic5a", "pic5b");
        correctImageMap.put("pic5b", "pic5a");
        correctImageMap.put("pic6a", "pic6b");
        correctImageMap.put("pic6b", "pic6a");

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((String) option1.getTag());
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((String) option2.getTag());
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((String) option3.getTag());
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimilarPicturesPlayActivity.this, SimilarPicturesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk soruyu göster
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex >= images.length) {
            currentQuestionIndex = 0;
        }
        String currentImage = images[currentQuestionIndex];
        String correctAnswer = correctImageMap.get(currentImage);

        questionImageView.setImageResource(getImageResourceId(currentImage));
        questionText.setText("Find the similar picture.");

        ArrayList<String> options = new ArrayList<>();
        options.add(correctAnswer);

        while (options.size() < 3) {
            String randomImage = images[(int) (Math.random() * images.length)];
            if (!options.contains(randomImage) && !randomImage.equals(currentImage)) {
                options.add(randomImage);
            }
        }

        Collections.shuffle(options);

        option1.setImageResource(getImageResourceId(options.get(0)));
        option1.setTag(options.get(0));
        option2.setImageResource(getImageResourceId(options.get(1)));
        option2.setTag(options.get(1));
        option3.setImageResource(getImageResourceId(options.get(2)));
        option3.setTag(options.get(2));

        currentQuestionIndex++;
    }

    private void checkAnswer(String selectedImage) {
        String correctAnswer = correctImageMap.get(images[currentQuestionIndex - 1]);
        if (selectedImage.equals(correctAnswer)) {
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

    private int getImageResourceId(String imageName) {
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }
}
