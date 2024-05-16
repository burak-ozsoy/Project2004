package com.example.project2004v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class AnalogClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock);

        Button btnBackToMenu = findViewById(R.id.btn_back_to_menu);
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalogClockActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Bu activity'yi bitirir, böylece geri tuşuna basıldığında tekrar buraya dönülmez.
            }
        });
    }
}
