package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.DirectionsActivity;
import com.example.project2004v2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectionsPlayActivity extends AppCompatActivity {

    private TextView scoreText;
    private ImageView directionsImage;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnGoBack;
    private int score = 0;
    private String correctDirection;
    private List<String> directions;
    private List<Integer> directionImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_play);

        scoreText = findViewById(R.id.score_text);
        directionsImage = findViewById(R.id.directions_image);
        btnOption1 = findViewById(R.id.btn_option_1);
        btnOption2 = findViewById(R.id.btn_option_2);
        btnOption3 = findViewById(R.id.btn_option_3);
        btnOption4 = findViewById(R.id.btn_option_4);
        btnGoBack = findViewById(R.id.btn_go_back);

        directions = new ArrayList<>();
        Collections.addAll(directions, "Left", "Right", "Up", "Down", "In front of", "Behind");

        directionImages = new ArrayList<>();
        Collections.addAll(directionImages, R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.down, R.drawable.in_front_of, R.drawable.behind);

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

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnOption4.getText().toString());
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DirectionsPlayActivity.this, DirectionsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk soruyu göster
        showNextDirection();
    }

    private void showNextDirection() {
        // Rastgele bir yön seç
        int randomIndex = (int) (Math.random() * directions.size());
        correctDirection = directions.get(randomIndex);
        directionsImage.setImageResource(directionImages.get(randomIndex));

        // Seçenekleri karıştır ve doğru seçeneği de ekleyerek butonlara ata
        List<String> options = new ArrayList<>(directions);
        options.remove(correctDirection);
        Collections.shuffle(options);
        options = options.subList(0, 3); // Yanlış seçeneklerden 3 tanesini al
        options.add(correctDirection); // Doğru seçeneği ekle
        Collections.shuffle(options); // Seçenekleri tekrar karıştır

        btnOption1.setText(options.get(0));
        btnOption2.setText(options.get(1));
        btnOption3.setText(options.get(2));
        btnOption4.setText(options.get(3));
    }

    private void checkAnswer(String selectedDirection) {
        if (selectedDirection.equals(correctDirection)) {
            score++;
            scoreText.setText("Score: " + score);
        } else {
            scoreText.setText("Try again! Score: " + score);
        }
        showNextDirection();
    }
}
