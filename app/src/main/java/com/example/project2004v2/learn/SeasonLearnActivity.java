package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.SeasonActivity;
import java.util.ArrayList;

public class SeasonLearnActivity extends AppCompatActivity {

    private ImageView currentImageView;
    private TextView seasonTextView;

    private ArrayList<Integer> seasonExamples;
    private ArrayList<String> seasonNames;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_learn);

        Button btnNextExample = findViewById(R.id.btn_next_example);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        // Initialize the ArrayList
        seasonExamples = new ArrayList<>();
        seasonExamples.add(R.drawable.spring);
        seasonExamples.add(R.drawable.summer);
        seasonExamples.add(R.drawable.fall);
        seasonExamples.add(R.drawable.winter);

        // Initialize the ArrayList for season names
        seasonNames = new ArrayList<>();
        seasonNames.add("Spring");
        seasonNames.add("Summer");
        seasonNames.add("Fall");
        seasonNames.add("Winter");

        // Initialize the ImageView and TextView
        currentImageView = findViewById(R.id.spring);
        seasonTextView = findViewById(R.id.seasonTextView);

        // Set the initial image and text
        currentImageView.setImageResource(seasonExamples.get(currentIndex));
        seasonTextView.setText(seasonNames.get(currentIndex));

        // Set OnClickListener for the next example button
        btnNextExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment the index to get the next image and text
                currentIndex = (currentIndex + 1) % seasonExamples.size();
                // Set the next image and text
                currentImageView.setImageResource(seasonExamples.get(currentIndex));
                seasonTextView.setText(seasonNames.get(currentIndex));
            }
        });

        // Set OnClickListener for the go back button
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonLearnActivity.this, SeasonActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
