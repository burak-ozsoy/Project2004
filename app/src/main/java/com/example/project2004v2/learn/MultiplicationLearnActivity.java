package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.MultiplicationActivity;
import com.example.project2004v2.R;

public class MultiplicationLearnActivity extends AppCompatActivity {

    private TextView multiplicationExample;
    private TextView explanation;
    private int currentExampleIndex = 0;
    private String[] examples = {"3 x 4 = 12", "5 x 2 = 10", "6 x 7 = 42", "8 x 3 = 24"};
    private String[] explanations = {
            "Explanation: 3 groups of 4 items each equals 12 items in total.",
            "Explanation: 5 groups of 2 items each equals 10 items in total.",
            "Explanation: 6 groups of 7 items each equals 42 items in total.",
            "Explanation: 8 groups of 3 items each equals 24 items in total."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication_learn);

        multiplicationExample = findViewById(R.id.multiplication_example);
        explanation = findViewById(R.id.explanation);

        Button btnNextExample = findViewById(R.id.btn_next_example);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        btnNextExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextExample();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MultiplicationLearnActivity.this, MultiplicationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk örneği göster
        showNextExample();
    }

    private void showNextExample() {
        if (currentExampleIndex >= examples.length) {
            currentExampleIndex = 0;
        }
        multiplicationExample.setText(examples[currentExampleIndex]);
        explanation.setText(explanations[currentExampleIndex]);
        currentExampleIndex++;
    }
}
