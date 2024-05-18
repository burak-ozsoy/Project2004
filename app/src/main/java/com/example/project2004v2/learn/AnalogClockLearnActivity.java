package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.AnalogClockActivity;

public class AnalogClockLearnActivity extends AppCompatActivity {

    private EditText timeInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock_learn);

        timeInput = findViewById(R.id.time_input);
        resultText = findViewById(R.id.result_text);
        Button btnCheckTime = findViewById(R.id.btn_check_time);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        btnCheckTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTime();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalogClockLearnActivity.this, AnalogClockActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkTime() {
        String inputTime = timeInput.getText().toString().trim();
        // Örnek doğru zaman: 10:15
        String correctTime = "10:15";

        if (inputTime.equals(correctTime)) {
            resultText.setText("Correct! The time is " + correctTime);
            resultText.setTextColor(getResources().getColor(R.color.correct_color));
        } else {
            resultText.setText("Try again! The correct time is " + correctTime);
            resultText.setTextColor(getResources().getColor(R.color.error_color));
        }
    }
}
