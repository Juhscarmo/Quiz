package com.example.quiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quiz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class TelaCadastroActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText editTextSenha;
    private EditText editTextEmail;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        auth = FirebaseAuth.getInstance();
        editTextSenha = findViewById(R.id.editTextSenhaCadastro);
        editTextEmail = findViewById(R.id.editTextEmailCadastro);
        buttonCadastrar = findViewById(R.id.button);
        cadastrarUsuario();
    }

    private void cadastrarUsuario() {
        this.buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senha = String.valueOf(editTextSenha.getText());
                String email = String.valueOf(editTextEmail.getText());
                if (senha.isEmpty() || email.isEmpty()) {
                    Toast.makeText(TelaCadastroActivity.this, "Informe todos os campos!", Toast.LENGTH_LONG).show();
                }
                else if (senha.length() < 6) {
                    Toast.makeText(TelaCadastroActivity.this, "A senha deve conter pelo menos 6 caracteres!", Toast.LENGTH_LONG).show();
                } else {
                    auth.createUserWithEmailAndPassword(email, senha).
                            addOnSuccessListener(authResult -> {
                                Toast.makeText(TelaCadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                            })
                            //Lidando com erros de cadastro
                            .addOnFailureListener(failure -> {
                                if (failure instanceof FirebaseAuthEmailException) {
                                    Toast.makeText(TelaCadastroActivity.this, "Email inválido!", Toast.LENGTH_LONG).show();
                                } else if (failure instanceof FirebaseAuthWeakPasswordException) {
                                    Toast.makeText(TelaCadastroActivity.this, "A senha está muito fraca, deve conter pelo menos 6 caracteres!", Toast.LENGTH_LONG).show();
                                } else if (failure instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(TelaCadastroActivity.this, "Essa conta já possui um cadastro!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(TelaCadastroActivity.this, "Houve um erro ao cadastrar novo usuário!", Toast.LENGTH_LONG).show();
                                }
                            });

                }
            }

        });


    }
}
