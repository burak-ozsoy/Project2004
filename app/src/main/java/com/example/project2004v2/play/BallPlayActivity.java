package com.example.project2004v2.play;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.R;
import com.example.project2004v2.BallActivity;
import java.util.Random;

public class BallPlayActivity extends AppCompatActivity {

    private View ball;
    private View ballArea;
    private AnimatorSet animatorSet;
    private TextView scoreText;
    private TextView feedbackText;
    private int score = 0;
    private Random random = new Random();
    private Handler handler = new Handler();

    private int currentRow = 0;
    private int areaWidth;
    private int areaHeight;
    private int ballWidth;
    private int ballHeight;
    private int rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_play);

        ball = findViewById(R.id.ball);
        ballArea = findViewById(R.id.ball_area);
        scoreText = findViewById(R.id.score_text);
        feedbackText = findViewById(R.id.feedback_text);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        ball.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    score++;
                    scoreText.setText("Score: " + score);
                    feedbackText.setText("Great job!");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            feedbackText.setText("");
                        }
                    }, 1000);
                    startBallAnimation();
                    return true;
                }
                return false;
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animatorSet != null && animatorSet.isRunning()) {
                    animatorSet.cancel();
                }
                Intent intent = new Intent(BallPlayActivity.this, BallActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Ekran yüklendikten sonra topu başlat
        ballArea.post(new Runnable() {
            @Override
            public void run() {
                initializeBallPosition();
                startBallAnimation();
            }
        });
    }

    private void initializeBallPosition() {
        areaWidth = ballArea.getWidth();
        areaHeight = ballArea.getHeight();
        ballWidth = ball.getWidth();
        ballHeight = ball.getHeight();
        rows = areaHeight / ballHeight;
        currentRow = 0;

        ball.setX(0);
        ball.setY(0);
    }

    private void startBallAnimation() {
        if (animatorSet != null && animatorSet.isRunning()) {
            animatorSet.cancel();
        }

        float targetX;
        float targetY = currentRow * ballHeight;

        if (ball.getX() + ballWidth < areaWidth) {
            targetX = areaWidth - ballWidth;
        } else {
            currentRow++;
            if (currentRow >= rows) {
                currentRow = 0;
            }
            targetX = 0;
            targetY = currentRow * ballHeight;
        }

        ObjectAnimator moveX = ObjectAnimator.ofFloat(ball, "x", targetX);
        ObjectAnimator moveY = ObjectAnimator.ofFloat(ball, "y", targetY);

        moveX.setDuration(500);
        moveY.setDuration(500);

        animatorSet = new AnimatorSet();
        animatorSet.playTogether(moveX, moveY);
        animatorSet.start();
    }
}
