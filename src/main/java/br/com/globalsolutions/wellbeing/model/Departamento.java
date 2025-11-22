package br.com.globalsolutions.wellbeing.model;

public class Departamento {

    private int id;
    private String nome;
    private String descricao;
    private Gestor supervisor;

    public Departamento() {
    }

    public Departamento(int id, String nome, String descricao, Gestor supervisor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.supervisor = supervisor;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Gestor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Gestor supervisor) {
        this.supervisor = supervisor;
    }
}


