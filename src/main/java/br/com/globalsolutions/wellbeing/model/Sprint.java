package br.com.globalsolutions.wellbeing.model;

import java.time.LocalDate;

public class Sprint {

    private int id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Release release;

    public Sprint() {
    }

    public Sprint(int id, String nome, LocalDate dataInicio, LocalDate dataFim, Release release) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.release = release;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }
}


