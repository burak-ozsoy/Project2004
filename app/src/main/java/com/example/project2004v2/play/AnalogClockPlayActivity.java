package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.AnalogClockActivity;
import java.util.Random;

public class AnalogClockPlayActivity extends AppCompatActivity {

    private AnalogClockView analogClockView;
    private TextView timeToSet;
    private TextView resultText;
    private TextView scoreText;
    private TextView handToSet;
    private int score = 0;
    private int targetHour;
    private int targetMinute;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock_play);

        analogClockView = findViewById(R.id.analog_clock_view);
        timeToSet = findViewById(R.id.time_to_set);
        resultText = findViewById(R.id.result_text);
        scoreText = findViewById(R.id.score_text);
        handToSet = findViewById(R.id.hand_to_set);
        Button btnCheckTime = findViewById(R.id.btn_check_time);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        random = new Random();
        generateRandomTime();
        updateHandToSet();

        btnCheckTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTime();
                updateHandToSet();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalogClockPlayActivity.this, AnalogClockActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void generateRandomTime() {
        targetHour = random.nextInt(12);
        targetMinute = random.nextInt(60);
        timeToSet.setText(String.format("Set the time to: %02d:%02d", targetHour, targetMinute));
    }

    private void checkTime() {
        int currentHour = analogClockView.getHour();
        int currentMinute = analogClockView.getMinute();

        if (currentHour == targetHour && currentMinute == targetMinute) {
            score++;
            resultText.setText("Correct! The time is " + String.format("%02d:%02d", targetHour, targetMinute));
            resultText.setTextColor(getResources().getColor(R.color.correct_color));
        } else {
            resultText.setText("Try again! The correct time is " + String.format("%02d:%02d", targetHour, targetMinute));
            resultText.setTextColor(getResources().getColor(R.color.error_color));
        }
        scoreText.setText("Score: " + score);
        generateRandomTime();
    }

    private void updateHandToSet() {
        if (analogClockView.isSettingHour()) {
            handToSet.setText("Set the hour hand (red)");
        } else {
            handToSet.setText("Set the minute hand (blue)");
        }
    }
}
