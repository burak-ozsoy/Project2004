package com.example.project2004v2.learn;

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

public class SpellLearnActivity extends AppCompatActivity {

    private TextView wordToSpell;
    private TextView letterText;
    private ArrayList<String> words;
    private int currentWordIndex = 0;
    private String currentWord;
    private int currentLetterIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_learn);

        wordToSpell = findViewById(R.id.word_to_spell);
        letterText = findViewById(R.id.letter_text);
        MaterialButton btnNextLetter = findViewById(R.id.btn_next_letter);
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

        btnNextLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextLetter();
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
                Intent intent = new Intent(SpellLearnActivity.this, SpellActivity.class);
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
        currentLetterIndex = 0;
        updateWordDisplay();
        showNextLetter();
        currentWordIndex++;
    }

    private void showNextLetter() {
        if (currentLetterIndex < currentWord.length()) {
            letterText.setText(String.valueOf(currentWord.charAt(currentLetterIndex)));
            currentLetterIndex++;
        } else {
            letterText.setText(" ");
        }
        updateWordDisplay();
    }

    private void updateWordDisplay() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < currentWord.length(); i++) {
            if (i < currentLetterIndex) {
                display.append(currentWord.charAt(i));
            } else {
                display.append("_");
            }
            if (i < currentWord.length() - 1) {
                display.append(" ");
            }
        }
        wordToSpell.setText(display.toString());
    }
}
