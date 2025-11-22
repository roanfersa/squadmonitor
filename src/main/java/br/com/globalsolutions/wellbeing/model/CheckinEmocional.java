package br.com.globalsolutions.wellbeing.model;

import java.time.LocalDateTime;

public class CheckinEmocional {

    private int id;
    private LocalDateTime dataHora;
    private int humorGeral;      // 1-5
    private int energia;         // 1-5
    private int estresse;        // 1-5
    private int foco;            // 1-5
    private int sono;            // 1-5
    private int cargaTrabalho;   // 1-5
    private String sentimentoDominante;
    private String comentarioOpcional;
    private Usuario usuario;

    public CheckinEmocional() {
    }

    public CheckinEmocional(int id, LocalDateTime dataHora, int humorGeral, int energia,
                            int estresse, int foco, int sono, int cargaTrabalho,
                            String sentimentoDominante, String comentarioOpcional,
                            Usuario usuario) {
        this.id = id;
        this.dataHora = dataHora;
        this.humorGeral = humorGeral;
        this.energia = energia;
        this.estresse = estresse;
        this.foco = foco;
        this.sono = sono;
        this.cargaTrabalho = cargaTrabalho;
        this.sentimentoDominante = sentimentoDominante;
        this.comentarioOpcional = comentarioOpcional;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getHumorGeral() {
        return humorGeral;
    }

    public void setHumorGeral(int humorGeral) {
        this.humorGeral = humorGeral;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getEstresse() {
        return estresse;
    }

    public void setEstresse(int estresse) {
        this.estresse = estresse;
    }

    public int getFoco() {
        return foco;
    }

    public void setFoco(int foco) {
        this.foco = foco;
    }

    public int getSono() {
        return sono;
    }

    public void setSono(int sono) {
        this.sono = sono;
    }

    public int getCargaTrabalho() {
        return cargaTrabalho;
    }

    public void setCargaTrabalho(int cargaTrabalho) {
        this.cargaTrabalho = cargaTrabalho;
    }

    public String getSentimentoDominante() {
        return sentimentoDominante;
    }

    public void setSentimentoDominante(String sentimentoDominante) {
        this.sentimentoDominante = sentimentoDominante;
    }

    public String getComentarioOpcional() {
        return comentarioOpcional;
    }

    public void setComentarioOpcional(String comentarioOpcional) {
        this.comentarioOpcional = comentarioOpcional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


