package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.DigitsBackwardActivity;
import com.example.project2004v2.R;

public class DigitsBackwardLearnActivity extends AppCompatActivity {

    private TextView digitText;
    private int currentDigit = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_backward_learn);

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
                Intent intent = new Intent(DigitsBackwardLearnActivity.this, DigitsBackwardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk sayıyı göster
        showNextDigit();
    }

    private void showNextDigit() {
        if (currentDigit < 1) {
            currentDigit = 9;
        }
        digitText.setText(String.valueOf(currentDigit));
        currentDigit--;
    }
}
