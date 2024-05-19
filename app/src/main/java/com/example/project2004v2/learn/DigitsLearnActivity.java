package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.DigitsActivity;
import com.example.project2004v2.R;

public class DigitsLearnActivity extends AppCompatActivity {

    private TextView digitText;
    private int currentDigit = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_learn);

        digitText = findViewById(R.id.digit_text);

        Button btnNextDigit = findViewById(R.id.btn_next_digit);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        btnNextDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextDigit();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DigitsLearnActivity.this, DigitsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk sayıyı göster
        showNextDigit();
    }

    private void showNextDigit() {
        if (currentDigit > 9) {
            currentDigit = 1;
        }
        digitText.setText(String.valueOf(currentDigit));
        currentDigit++;
    }
}
