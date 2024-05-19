package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.MonthsActivity;
import com.example.project2004v2.R;

public class MonthsLearnActivity extends AppCompatActivity {

    private TextView monthText;
    private String[] months_of_year;
    private int currentMonthIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months_learn);

        monthText = findViewById(R.id.monthText);
        months_of_year = getResources().getStringArray(R.array.months_of_year);

        Button btnNextMonth = findViewById(R.id.btn_next_month);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextMonth();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonthsLearnActivity.this, MonthsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk günü göster
        showNextMonth();
    }

    private void showNextMonth() {
        if (currentMonthIndex >= months_of_year.length) {
            currentMonthIndex = 0;
        }
        monthText.setText(months_of_year[currentMonthIndex]);
        currentMonthIndex++;
    }
}