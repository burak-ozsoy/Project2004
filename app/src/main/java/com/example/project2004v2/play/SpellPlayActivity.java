package com.example.project2004v2.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.SpellActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellPlayActivity extends AppCompatActivity {

    private TextView fullWord;
    private TextView wordToSpell;
    private TextView scoreText;
    private int score = 0;
    private List<String> words;
    private String currentWord;
    private int currentWordIndex = 0;
    private int currentLetterIndex = 0;
    private StringBuilder currentWordDisplay;
    private MaterialButton btnOption1, btnOption2, btnOption3;
    private List<String> letterOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_play);

        fullWord = findViewById(R.id.full_word);
        wordToSpell = findViewById(R.id.word_to_spell);
        scoreText = findViewById(R.id.score_text);
        btnOption1 = findViewById(R.id.btn_option_1);
        btnOption2 = findViewById(R.id.btn_option_2);
        btnOption3 = findViewById(R.id.btn_option_3);
        MaterialButton btnNextWord = findViewById(R.id.btn_next_word);
        MaterialButton btnGoBack = findViewById(R.id.btn_go_back);

        // Öğrenilecek kelimeleri ekliyoruz
        words = new ArrayList<>();
        words.add("Apple");
        words.add("Banana");
        words.add("Orange");
        words.add("Grapes");
        words.add("Pineapple");
        words.add("Strawberry");
        words.add("Watermelon");
        words.add("Mango");
        words.add("Blueberry");
        words.add("Peach");

        // Kelimeleri karıştırıyoruz
        Collections.shuffle(words);

        // İlk kelimeyi gösteriyoruz
        showNextWord();

        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnOption1.getText().toString());
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnOption2.getText().toString());
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btnOption3.getText().toString());
            }
        });

        btnNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextWord();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpellPlayActivity.this, SpellActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showNextWord() {
        if (currentWordIndex >= words.size()) {
            currentWordIndex = 0;
        }
        currentWord = words.get(currentWordIndex);
        fullWord.setText(currentWord);
        currentWordDisplay = new StringBuilder();
        for (int i = 0; i < currentWord.length(); i++) {
            currentWordDisplay.append("_");
        }
        wordToSpell.setText(currentWordDisplay.toString());
        currentLetterIndex = 0;
        setLetterOptions();
        currentWordIndex++;
    }

    private void setLetterOptions() {
        if (currentLetterIndex < currentWord.length()) {
            letterOptions = new ArrayList<>();
            letterOptions.add(String.valueOf(currentWord.charAt(currentLetterIndex)));

            while (letterOptions.size() < 3) {
                char randomChar = (char) ('A' + Math.random() * 26);
                if (!letterOptions.contains(String.valueOf(randomChar))) {
                    letterOptions.add(String.valueOf(randomChar));
                }
            }

            Collections.shuffle(letterOptions);

            btnOption1.setText(letterOptions.get(0));
            btnOption2.setText(letterOptions.get(1));
            btnOption3.setText(letterOptions.get(2));
        }
    }

    private void checkAnswer(String selectedLetter) {
        if (selectedLetter.equals(String.valueOf(currentWord.charAt(currentLetterIndex)))) {
            currentWordDisplay.setCharAt(currentLetterIndex, currentWord.charAt(currentLetterIndex));
            currentLetterIndex++;
            wordToSpell.setText(currentWordDisplay.toString());

            if (currentLetterIndex >= currentWord.length()) {
                score++;
                scoreText.setText("Score: " + score);
                showNextWord();
            } else {
                setLetterOptions();
            }
        }
    }
}
