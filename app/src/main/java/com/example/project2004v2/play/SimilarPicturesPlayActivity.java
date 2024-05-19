package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.SimilarPicturesActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimilarPicturesPlayActivity extends AppCompatActivity {

    private static final String TAG = "SimilarPicturesPlay";
    private TextView scoreText;
    private ImageView mainPicture;
    private ImageView option1, option2, option3, option4;
    private int score = 0;
    private Random random;
    private int correctOptionIndex;

    private int[] pictureSet = {R.drawable.similar_picture_1, R.drawable.similar_picture_1, R.drawable.similar_picture_1, R.drawable.similar_picture_1,
            R.drawable.similar_picture_1, R.drawable.similar_picture_1, R.drawable.similar_picture_1, R.drawable.similar_picture_1};
    private List<Integer> pictureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pictures_play);

        scoreText = findViewById(R.id.score_text);
        mainPicture = findViewById(R.id.main_picture);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        findViewById(R.id.btn_go_back).setOnClickListener(v -> {
            Intent intent = new Intent(SimilarPicturesPlayActivity.this, SimilarPicturesActivity.class);
            startActivity(intent);
            finish();
        });

        random = new Random();
        pictureList = new ArrayList<>();
        for (int picture : pictureSet) {
            pictureList.add(picture);
        }

        option1.setOnClickListener(v -> checkAnswer(0));
        option2.setOnClickListener(v -> checkAnswer(1));
        option3.setOnClickListener(v -> checkAnswer(2));
        option4.setOnClickListener(v -> checkAnswer(3));

        // İlk problemi göster
        generateNewProblem();
    }

    private void generateNewProblem() {
        try {
            // Rastgele bir ana resim seç
            Collections.shuffle(pictureList);
            int mainPictureRes = pictureList.get(0);
            mainPicture.setImageResource(mainPictureRes);

            // Doğru cevabı rastgele bir butona yerleştir
            correctOptionIndex = random.nextInt(4);
            Log.d(TAG, "Correct option index: " + correctOptionIndex);

            // Kalan seçenekleri yerleştir
            List<Integer> optionPictures = new ArrayList<>(pictureList.subList(1, 5));
            optionPictures.add(correctOptionIndex, mainPictureRes);

            option1.setImageResource(optionPictures.get(0));
            option2.setImageResource(optionPictures.get(1));
            option3.setImageResource(optionPictures.get(2));
            option4.setImageResource(optionPictures.get(3));
        } catch (Exception e) {
            Log.e(TAG, "Error in generateNewProblem: " + e.getMessage());
        }
    }

    private void checkAnswer(int selectedOptionIndex) {
        try {
            if (selectedOptionIndex == correctOptionIndex) {
                score++;
                scoreText.setText("Score: " + score);
            } else {
                scoreText.setText("Try again! Score: " + score);
            }
            generateNewProblem();
        } catch (Exception e) {
            Log.e(TAG, "Error in checkAnswer: " + e.getMessage());
        }
    }
}
