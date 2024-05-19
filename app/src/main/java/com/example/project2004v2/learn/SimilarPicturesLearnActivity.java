package com.example.project2004v2.learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.SimilarPicturesActivity;

public class SimilarPicturesLearnActivity extends AppCompatActivity {

    private ImageView similarPicturesImage1;
    private ImageView similarPicturesImage2;
    private ImageView similarPicturesImage3;
    private int currentExampleIndex = 0;
    private int[][] similarPicturesExamples = {
            {R.drawable.similar_picture_1, R.drawable.similar_picture_2, R.drawable.similar_picture_3},
            {R.drawable.similar_picture_4, R.drawable.similar_picture_5, R.drawable.similar_picture_6},
            {R.drawable.similar_picture_7, R.drawable.similar_picture_8, R.drawable.similar_picture_9}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_pictures_learn);

        similarPicturesImage1 = findViewById(R.id.similar_pictures_image_1);
        similarPicturesImage2 = findViewById(R.id.similar_pictures_image_2);
        similarPicturesImage3 = findViewById(R.id.similar_pictures_image_3);

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
                Intent intent = new Intent(SimilarPicturesLearnActivity.this, SimilarPicturesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // İlk örneği göster
        showNextExample();
    }

    private void showNextExample() {
        if (currentExampleIndex >= similarPicturesExamples.length) {
            currentExampleIndex = 0;
        }
        similarPicturesImage1.setImageResource(similarPicturesExamples[currentExampleIndex][0]);
        similarPicturesImage2.setImageResource(similarPicturesExamples[currentExampleIndex][1]);
        similarPicturesImage3.setImageResource(similarPicturesExamples[currentExampleIndex][2]);
        currentExampleIndex++;
    }
}
