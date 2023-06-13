package com.example.quiz.Models;

public class QuestoesModel {

    private String questao, op1, op2, op3, op4, resp_certa;

    public QuestoesModel(String questao, String op1, String op2, String op3, String op4, String resp_certa) {
        this.questao = questao;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.resp_certa = resp_certa;
    }

    public QuestoesModel() {
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getResp_certa() {
        return resp_certa;
    }

    public void setResp_certa(String resp_certa) {
        this.resp_certa = resp_certa;
    }
}
