package com.example.quiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.Models.QuestoesModel;
import com.example.quiz.R;
import com.example.quiz.databinding.ActivityQuestoes1Binding;

import java.util.ArrayList;

//Questões HTML
public class Questoes1Activity extends AppCompatActivity {

    ActivityQuestoes1Binding binding;

    ArrayList<QuestoesModel> list = new ArrayList<>();

    private int count = 0;
    private int posicao = 0;
    private int pontuacao = 0;
    CountDownTimer tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestoes1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        resetarTempo();
        tempo.start();

        String setNome = getIntent().getStringExtra("fase1");

        if(setNome.equals("FASE 1")){
            setFaseUm();

        } else if (setNome.equals("FASE 2")) {
            setFaseDois();

        } else if (setNome.equals("FASE 3")) {
            setFaseTres();

        }

        for(int i = 0; i < 4; i++){
            binding.alternativas.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    verificarResp((Button) view);
                }
            });
        }

        animacao(binding.pergunta, 0, list.get(posicao).getQuestao());

        binding.buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tempo != null){
                    tempo.cancel();
                }

                tempo.start();

                binding.buttonProximo.setEnabled(false);
                binding.buttonProximo.setAlpha((float) 0.3);
                ativarOp(true);
                posicao++;

                if (posicao == list.size()){
                    Intent intent = new Intent(Questoes1Activity.this, Pontuacao1Activity.class);
                    intent.putExtra("fase1",setNome);
                    intent.putExtra("pontos", pontuacao);
                    intent.putExtra("total", list.size());
                    startActivity(intent);
                    finish();
                    return;
                }

                count = 0;
                animacao(binding.pergunta, 0, list.get(posicao).getQuestao());

            }
        });

    }

    private void setFaseUm() {

        list.add(new QuestoesModel("1. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 1"));

        list.add(new QuestoesModel("2. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 2"));

        list.add(new QuestoesModel("3. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 3"));

        list.add(new QuestoesModel("4. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 4"));

        list.add(new QuestoesModel("5. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 4"));
    }

    private void setFaseDois() {

        list.add(new QuestoesModel("1. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 1"));

        list.add(new QuestoesModel("2. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 2"));

        list.add(new QuestoesModel("3. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 3"));

        list.add(new QuestoesModel("4. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 4"));

        list.add(new QuestoesModel("5. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 4"));

    }

    private void setFaseTres() {

        list.add(new QuestoesModel("1. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 1"));

        list.add(new QuestoesModel("2. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 2"));

        list.add(new QuestoesModel("3. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 3"));

        list.add(new QuestoesModel("4. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 4"));

        list.add(new QuestoesModel("5. Pergunta aleatória de HTML aqui.",
                "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4",
                "Resposta 4"));

    }

    private void verificarResp(Button opSelecionada) {

        if (tempo != null){
            tempo.cancel();
        }

        binding.buttonProximo.setEnabled(true);
        binding.buttonProximo.setAlpha(1);

        if (opSelecionada.getText().toString().equals(list.get(posicao).getResp_certa())){
            pontuacao++;
            opSelecionada.setBackgroundResource(R.drawable.style_resp_certa);
        }
        else {
            opSelecionada.setBackgroundResource(R.drawable.style_resp_errada);
            Button respCerta = (Button) binding.alternativas.findViewWithTag(list.get(posicao).getResp_certa());
            respCerta.setBackgroundResource(R.drawable.style_resp_certa);
        }
    }

    private void ativarOp(boolean ativar) {

        for (int i = 0; i < 4; i++){
            binding.alternativas.getChildAt(i).setEnabled(ativar);

            if (ativar){
                binding.alternativas.getChildAt(i).setBackgroundResource(R.drawable.style_alternativas);
            }
        }

    }

    private void animacao(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {
                        if (value == 0 && count < 4) {
                            String op = "";
                            if (count == 0) {
                                op = list.get(posicao).getOp4();
                            } else if (count == 1) {
                                op = list.get(posicao).getOp3();
                            } else if (count == 2) {
                                op = list.get(posicao).getOp2();
                            } else if (count == 3) {
                                op = list.get(posicao).getOp1();
                            }

                            animacao(binding.alternativas.getChildAt(count), 0, op);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        if (value == 0){
                            try {
                                ((TextView)view).setText(data);
                                binding.qntQuestoes.setText(posicao + 1 + "/" + list.size());
                            } catch (Exception e){
                                ((Button)view).setText(data);
                            }

                            view.setTag(data);
                            animacao(view, 1, data);

                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });

    }

    private void resetarTempo() {

        tempo = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long t) {

                binding.tempo.setText(String.valueOf(t/1000));

            }

            @Override
            public void onFinish() {

                Dialog dialog = new Dialog(Questoes1Activity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.tempo_esgotado);
                dialog.findViewById(R.id.button_tentardnv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Questoes1Activity.this, Fases1Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dialog.show();

            }
        };
    }

}