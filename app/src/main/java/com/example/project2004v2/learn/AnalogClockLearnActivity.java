package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.AnalogClockActivity;

public class AnalogClockLearnActivity extends AppCompatActivity {

    private ImageView analogClockImage;
    private TextView descriptionText;
    private Button btnNextStep;
    private int currentStep = 0;

    private int[] clockImages = {
            R.drawable.analog_clock_step1,
            R.drawable.analog_clock_step2,
            R.drawable.analog_clock_step3
    };

    private String[] descriptions = {
            "Step 1: The hour hand shows the hour. The hour hand is shorter and moves slowly.",
            "Step 2: The minute hand shows the minutes. The minute hand is longer and moves faster.",
            "Step 3: Both hands together show the full time. The hour hand and the minute hand work together to show the exact time."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock_learn);

        analogClockImage = findViewById(R.id.analog_clock_image);
        descriptionText = findViewById(R.id.description_text);
        btnNextStep = findViewById(R.id.btn_next_step);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        updateContent();

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStep++;
                if (currentStep < clockImages.length) {
                    updateContent();
                } else {
                    btnNextStep.setEnabled(false);
                    btnNextStep.setText("Finished");
                }
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

    private void updateContent() {
        analogClockImage.setImageResource(clockImages[currentStep]);
        descriptionText.setText(descriptions[currentStep]);
    }
}
