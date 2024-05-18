package com.example.project2004v2.learn;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2004v2.BallActivity;
import com.example.project2004v2.R;

public class BallLearnActivity extends AppCompatActivity {

    private View ball;
    private View ballArea;
    private AnimatorSet animatorSet;
    private Button btnStartContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_learn);

        ball = findViewById(R.id.ball);
        ballArea = findViewById(R.id.ball_area);
        btnStartContinue = findViewById(R.id.btn_start_continue);
        Button btnGoBack = findViewById(R.id.btn_go_back);

        btnStartContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnStartContinue.getText().equals("Start")) {
                    btnStartContinue.setText("Continue");
                }
                startBallAnimation();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animatorSet != null && animatorSet.isRunning()) {
                    animatorSet.cancel();
                }
                Intent intent = new Intent(BallLearnActivity.this, BallActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startBallAnimation() {
        float startX = ball.getX();
        float startY = ball.getY();

        // Topu başlangıç konumuna yerleştirin
        ball.setX(0);
        ball.setY(0);

        // Animasyon alanının boyutlarını alın
        int areaWidth = ballArea.getWidth() - ball.getWidth();
        int areaHeight = ballArea.getHeight() - ball.getHeight();

        // Animasyonlar
        ObjectAnimator moveRight = ObjectAnimator.ofFloat(ball, "x", 0, areaWidth);
        ObjectAnimator moveDown = ObjectAnimator.ofFloat(ball, "y", 0, areaHeight);
        ObjectAnimator moveLeft = ObjectAnimator.ofFloat(ball, "x", areaWidth, 0);
        ObjectAnimator moveUp = ObjectAnimator.ofFloat(ball, "y", areaHeight, 0);

        moveRight.setDuration(1000);
        moveDown.setDuration(1000);
        moveLeft.setDuration(1000);
        moveUp.setDuration(1000);

        // Animasyon setini tekrarlar
        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveRight, moveDown, moveLeft, moveUp);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                // Animasyon bittiğinde tekrar başlatın
                if (btnStartContinue.getText().equals("Continue")) {
                    startBallAnimation();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        animatorSet.start();
    }
}
