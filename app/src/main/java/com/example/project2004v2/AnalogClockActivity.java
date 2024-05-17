package com.example.project2004v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class AnalogClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button btnBackToMenu, btnLearn, btnPlay;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock);

        btnBackToMenu = findViewById(R.id.btn_back_to_menu);
        btnLearn = findViewById(R.id.btn_learn);
        btnPlay = findViewById(R.id.btn_play);
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalogClockActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalogClockActivity.this, com.example.project2004v2.learn.AnalogClockLearnActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalogClockActivity.this, com.example.project2004v2.play.AnalogClockPlayActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
