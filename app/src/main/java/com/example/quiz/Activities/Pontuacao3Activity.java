package com.example.quiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quiz.R;
import com.example.quiz.databinding.ActivityPontuacao3Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//Pontuação CSS
public class Pontuacao3Activity extends AppCompatActivity {

    ActivityPontuacao3Binding binding;
    ImageView ic_voltar1;
    private FirebaseFirestore firebaseFirestore;
    private String fase3;
    private int totalQuestoes, respCertas, respErradas;
    private boolean status;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);

        firebaseFirestore = FirebaseFirestore.getInstance();

        binding = ActivityPontuacao3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fase3 = getIntent().getStringExtra("fase3");
        totalQuestoes = getIntent().getIntExtra("total", 0);
        respCertas = getIntent().getIntExtra("pontos", 0);
        respErradas = totalQuestoes - respCertas;
        status = true;

        binding.questoesTotais.setText(String.valueOf(totalQuestoes));
        binding.questoesAcertadas.setText(String.valueOf(respCertas));
        binding.questoesErradas.setText(String.valueOf(respErradas));

        binding.buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pontuacao3Activity.this, Fases3Activity.class);
                startActivity(intent);
                finish();
            }
        });

        ic_voltar1 = findViewById(R.id.ic_voltar1);
        ic_voltar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pontuacao3Activity.this, Fases3Activity.class);
                startActivity(intent);
            }
        });
        salvar();
    }

    private void salvar() {
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalQuestoes);
        map.put("respCertas", respCertas);
        map.put("respErradas", respErradas);
        map.put("status", status);
        firebaseFirestore.collection("Css").document(fase3).set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Placar salvo!", Toast.LENGTH_LONG);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro ao salvar placar!", Toast.LENGTH_LONG);
                    }
                });
    }
}