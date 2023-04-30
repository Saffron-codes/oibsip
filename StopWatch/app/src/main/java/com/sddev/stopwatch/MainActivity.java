package com.sddev.stopwatch;

import static java.lang.String.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    boolean isRunning = false;
    MaterialButton startPauseBtn;
    TextView timeTV;
    int seconds = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startPauseBtn = findViewById(R.id.startPauseBtn);
        timeTV = findViewById(R.id.timeTV);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isRunning) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        seconds++;
                        int hours = seconds / 3600;
                        int minutes = (seconds % 3600) / 60;
                        int secondsToShow = seconds % 60;
                        @SuppressLint("DefaultLocale") String time = format("%02d:%02d:%02d",hours, minutes, secondsToShow);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timeTV.setText(time);
                            }
                        });
                    }
                }
            }
        });
        thread.start();

    }

    @SuppressLint("SetTextI18n")
    public void startPauseClicked(View view) {
        Drawable playIcon = ContextCompat.getDrawable(this, R.drawable.baseline_play_arrow_24);
        Drawable pauseIcon = ContextCompat.getDrawable(this, R.drawable.baseline_pause_24);
        isRunning = !isRunning;
        if(isRunning){
            startPauseBtn.setText("Pause");
            startPauseBtn.setIcon(pauseIcon);
        }
        else{
            startPauseBtn.setText("Start");
            startPauseBtn.setIcon(playIcon);
        }
    }

    @SuppressLint("SetTextI18n")
    public void resetClicked(View view) {
        isRunning = false;
        seconds = 0;
        timeTV.setText("00:00:00");
    }
}