package com.example.project2004v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        Button btnAnalogClock, btnBall, btnDays, btnDigits, btnDigitsBackward, btnDirections, btnMonths, btnMultiplication, btnSeason, btnSimilarPictures, btnSpell;
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details); // TextView'yi bulmayı unutmayın
        user = auth.getCurrentUser();

        btnAnalogClock = findViewById(R.id.btn_analog_clock);
        btnBall = findViewById(R.id.btn_ball);
        btnDays = findViewById(R.id.btn_days);
        btnDigits = findViewById(R.id.btn_digits);
        btnDigitsBackward = findViewById(R.id.btn_digits_backward);
        btnDirections = findViewById(R.id.btn_directions);
        btnMonths = findViewById(R.id.btn_months);
        btnMultiplication = findViewById(R.id.btn_multiplication);
        btnSeason = findViewById(R.id.btn_season);
        btnSimilarPictures = findViewById(R.id.btn_similar_pictures);
        btnSpell = findViewById(R.id.btn_spell);

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }
        btnAnalogClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnalogClockActivity.class);
                startActivity(intent);
            }
        });

        btnBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BallActivity.class);
                startActivity(intent);
            }
        });

        btnDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DaysActivity.class);
                startActivity(intent);
            }
        });

        btnDigits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DigitsActivity.class);
                startActivity(intent);
            }
        });

        btnDigitsBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DigitsBackwardActivity.class);
                startActivity(intent);
            }
        });

        btnDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DirectionsActivity.class);
                startActivity(intent);
            }
        });

        btnMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MonthsActivity.class);
                startActivity(intent);
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MultiplicationActivity.class);
                startActivity(intent);
            }
        });

        btnSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeasonActivity.class);
                startActivity(intent);
            }
        });

        btnSimilarPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SimilarPicturesActivity.class);
                startActivity(intent);
            }
        });

        btnSpell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SpellActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
