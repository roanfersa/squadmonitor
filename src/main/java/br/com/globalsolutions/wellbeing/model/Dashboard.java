package br.com.globalsolutions.wellbeing.model;

import java.time.LocalDateTime;

public class Dashboard {

    private double mediaHumorGeral;
    private double tendenciaEstresse;
    private int totalCheckins;
    private int riscosAtivos;
    private LocalDateTime ultimaAtualizacao;
    private Squad squad;

    public Dashboard() {
    }

    public Dashboard(Squad squad) {
        this.squad = squad;
    }

    public double getMediaHumorGeral() {
        return mediaHumorGeral;
    }

    public void setMediaHumorGeral(double mediaHumorGeral) {
        this.mediaHumorGeral = mediaHumorGeral;
    }

    public double getTendenciaEstresse() {
        return tendenciaEstresse;
    }

    public void setTendenciaEstresse(double tendenciaEstresse) {
        this.tendenciaEstresse = tendenciaEstresse;
    }

    public int getTotalCheckins() {
        return totalCheckins;
    }

    public void setTotalCheckins(int totalCheckins) {
        this.totalCheckins = totalCheckins;
    }

    public int getRiscosAtivos() {
        return riscosAtivos;
    }

    public void setRiscosAtivos(int riscosAtivos) {
        this.riscosAtivos = riscosAtivos;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
    }
}


