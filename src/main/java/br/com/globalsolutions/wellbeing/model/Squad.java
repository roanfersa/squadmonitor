package br.com.globalsolutions.wellbeing.model;

import java.util.ArrayList;
import java.util.List;

public class Squad {

    private int id;
    private String descricao;
    private Gestor gestor;
    private Departamento departamento;
    private List<Funcionario> membros = new ArrayList<>();

    public Squad() {
    }

    public Squad(int id, String descricao, Gestor gestor, Departamento departamento) {
        this.id = id;
        this.descricao = descricao;
        this.gestor = gestor;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Funcionario> getMembros() {
        return membros;
    }

    public void setMembros(List<Funcionario> membros) {
        this.membros = membros;
    }

    public void adicionarMembro(Funcionario funcionario) {
        if (!membros.contains(funcionario) && membros.size() < 50) {
            membros.add(funcionario);
        }
    }
}


