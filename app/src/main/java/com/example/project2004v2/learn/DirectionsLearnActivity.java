package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.DirectionsActivity;
import com.example.project2004v2.R;

public class DirectionsLearnActivity extends AppCompatActivity {

    private TextView directionText;
    private ImageView directionsImage;
    private int currentDirectionIndex = 0;
    private String[] directions = {"Left", "Right", "Up", "Down", "In front of", "Behind"};
    private int[] directionImages = {R.drawable.left, R.drawable.right, R.drawable.up, R.drawable.down, R.drawable.in_front_of, R.drawable.behind};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_learn);

        directionText = findViewById(R.id.direction_text);
        directionsImage = findViewById(R.id.directions_image);

        Button btnNextDirection = findViewById(R.id.btn_next_direction);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        btnNextDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextDirection();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DirectionsLearnActivity.this, DirectionsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk yönü göster
        showNextDirection();
    }

    private void showNextDirection() {
        if (currentDirectionIndex >= directions.length) {
            currentDirectionIndex = 0;
        }
        directionText.setText(directions[currentDirectionIndex]);
        directionsImage.setImageResource(directionImages[currentDirectionIndex]);
        currentDirectionIndex++;
    }
}
