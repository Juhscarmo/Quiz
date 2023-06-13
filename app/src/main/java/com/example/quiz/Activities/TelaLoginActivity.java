package com.example.quiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.MainActivity;
import com.example.quiz.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class TelaLoginActivity extends AppCompatActivity {

    private TextView textViewCadastro;
    private FirebaseAuth auth;
    private EditText editTextSenha;
    private EditText editTextEmail;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        auth = FirebaseAuth.getInstance();
        IniciarComponentes();
        addEventTextView();
        addEventButtonLogin();

    }

    private void addEventTextView() {
        textViewCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaLoginActivity.this, TelaCadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addEventButtonLogin(){
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha = String.valueOf(editTextSenha.getText());
                String email = String.valueOf(editTextEmail.getText());
                if (senha.isEmpty() || email.isEmpty()) {
                    Toast.makeText(TelaLoginActivity.this, "Informe todos os campos!", Toast.LENGTH_LONG).show();
                }
                else if (senha.length() < 6) {
                    Toast.makeText(TelaLoginActivity.this, "A senha deve conter pelo menos 6 caracteres!", Toast.LENGTH_LONG).show();
                } else {
                    auth.signInWithEmailAndPassword(email,senha)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    // login válido, pode entrar
                                    Intent intent = new Intent(TelaLoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e){
                                    if(e instanceof FirebaseAuthInvalidCredentialsException){
                                        Toast.makeText(TelaLoginActivity.this,"Login inválido",Toast.LENGTH_LONG).show();
                                    }
                                    else if (e instanceof FirebaseAuthException){
                                        Toast.makeText(TelaLoginActivity.this,"Falha ao realizar login!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    //Inicia os componentes presentes na tela de login
    private void IniciarComponentes() {
        textViewCadastro = findViewById(R.id.textViewCadastro);
        editTextEmail = findViewById(R.id.editTextEmailLogin);
        editTextSenha = findViewById(R.id.editTextSenhaLogin);
        buttonLogin = findViewById(R.id.button_login);
    }
}