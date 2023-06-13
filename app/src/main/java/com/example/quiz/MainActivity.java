package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.quiz.Activities.Fases1Activity;
import com.example.quiz.Activities.Fases2Activity;
import com.example.quiz.Activities.Fases3Activity;
import com.example.quiz.Activities.FasesActivity;
import com.example.quiz.Activities.TelaPerfilActivity;

public class MainActivity extends AppCompatActivity {
    CardView tema_java,tema_html,tema_mat,tema_css;
    ImageView acessPerfil;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tema_java = findViewById(R.id.tema_java);
        tema_html = findViewById(R.id.tema_html);
        tema_mat = findViewById(R.id.tema_mat);
        tema_css = findViewById(R.id.tema_css);
        acessPerfil = findViewById(R.id.acessPerfil);

        acessPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, TelaPerfilActivity.class);
                startActivity(intent);
            }
        });

        tema_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FasesActivity.class);
                startActivity(intent);
            }
        });

        tema_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Fases1Activity.class);
                startActivity(intent);
            }
        });

        tema_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Fases2Activity.class);
                startActivity(intent);
            }
        });

        tema_css.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Fases3Activity.class);
                startActivity(intent);
            }
        });
    }
}