package com.example.quiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.quiz.MainActivity;
import com.example.quiz.R;

public class TelaInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(TelaInicialActivity.this, TelaLoginActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }
}