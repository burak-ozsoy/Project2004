package com.example.project2004v2.play;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

public class AnalogClockView extends View {
    private Paint paint;
    private int hour, minute;
    private float centerX, centerY;
    private int radius;
    private boolean isSettingHour;

    public AnalogClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        hour = 0;
        minute = 0;
        isSettingHour = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = Math.min(getWidth(), getHeight()) / 2 - 20;

        // Çemberi çiz
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(centerX, centerY, radius, paint);

        // Saat işaretlerini çiz
        drawClockNumbers(canvas);

        // Akrep ve yelkovanı çiz
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        drawHand(canvas, hour * 30, radius - 60); // Akrep (hour)
        paint.setColor(Color.BLUE);
        drawHand(canvas, minute * 6, radius - 40); // Yelkovan (minute)
    }

    private void drawClockNumbers(Canvas canvas) {
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30 - 90);
            float x = (float) (centerX + Math.cos(angle) * (radius - 40));
            float y = (float) (centerY + Math.sin(angle) * (radius - 40) + paint.getTextSize() / 2);
            canvas.drawText(String.valueOf(i), x, y, paint);
        }

        paint.setTextSize(20);
        for (int i = 1; i <= 60; i++) {
            if (i % 5 != 0) {
                double angle = Math.toRadians(i * 6 - 90);
                float x = (float) (centerX + Math.cos(angle) * (radius - 20));
                float y = (float) (centerY + Math.sin(angle) * (radius - 20));
                canvas.drawCircle(x, y, 5, paint);
            }
        }
    }

    private void drawHand(Canvas canvas, int angle, int length) {
        double radian = Math.toRadians(angle - 90);
        float x = (float) (centerX + Math.cos(radian) * length);
        float y = (float) (centerY + Math.sin(radian) * length);
        canvas.drawLine(centerX, centerY, x, y, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX() - centerX;
            float y = event.getY() - centerY;
            double angle = Math.toDegrees(Math.atan2(y, x)) + 90;
            if (angle < 0) angle += 360;

            if (isSettingHour) {
                hour = (int) (angle / 30);
            } else {
                minute = (int) (angle / 6);
            }
            invalidate();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            isSettingHour = !isSettingHour;
        }
        return super.onTouchEvent(event);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        invalidate();
    }

    public boolean isSettingHour() {
        return isSettingHour;
    }
}
