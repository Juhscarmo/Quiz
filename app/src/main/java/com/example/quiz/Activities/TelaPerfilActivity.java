package com.example.quiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quiz.MainActivity;
import com.example.quiz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class TelaPerfilActivity extends AppCompatActivity {

    private TextView emailUser;
    private Button buttonDeslogar;
    ImageView ic_voltar2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        emailUser = findViewById(R.id.emailUser);

        buttonDeslogar = findViewById(R.id.buttonDeslogar);
        buttonDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaPerfilActivity.this, TelaLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ic_voltar2 = findViewById(R.id.ic_voltar2);
        ic_voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPerfilActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        emailUser.setText(email);
    }
}