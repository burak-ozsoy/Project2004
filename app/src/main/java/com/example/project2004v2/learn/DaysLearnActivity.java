package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.DaysActivity;
import com.example.project2004v2.R;

public class DaysLearnActivity extends AppCompatActivity {

    private TextView dayText;
    private String[] daysOfWeek;
    private int currentDayIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_learn);

        dayText = findViewById(R.id.day_text);
        daysOfWeek = getResources().getStringArray(R.array.days_of_week);

        Button btnNextDay = findViewById(R.id.btn_next_day);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        btnNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextDay();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaysLearnActivity.this, DaysActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk günü göster
        showNextDay();
    }

    private void showNextDay() {
        if (currentDayIndex >= daysOfWeek.length) {
            currentDayIndex = 0;
        }
        dayText.setText(daysOfWeek[currentDayIndex]);
        currentDayIndex++;
    }
}
