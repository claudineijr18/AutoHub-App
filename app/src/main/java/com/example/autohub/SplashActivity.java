package com.example.autohub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Tempo da splash
        new Handler().postDelayed(() -> {

            Intent intent =
                    new Intent(
                            SplashActivity.this,
                            MainActivity.class
                    );

            startActivity(intent);

            // animação suave
            overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
            );
            finish();

        }, 2000); // 2 segundos
    }
}