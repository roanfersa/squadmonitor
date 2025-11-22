package br.com.globalsolutions.wellbeing.model;

import java.time.LocalDate;

public abstract class Usuario {

    private String cpfCriptografado;
    private String email;
    private LocalDate dataNascimento;
    private String cargo;
    private String senha;
    private LocalDate dataEntradaEmpresa;
    private Departamento departamento;

    public Usuario() {
    }

    public Usuario(String cpfCriptografado, String email, LocalDate dataNascimento, String cargo,
                   String senha, LocalDate dataEntradaEmpresa, Departamento departamento) {
        this.cpfCriptografado = cpfCriptografado;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.senha = senha;
        this.dataEntradaEmpresa = dataEntradaEmpresa;
        this.departamento = departamento;
    }

    public abstract void realizarCheckinEmocional();

    public abstract void visualizarMeusCheckins();

    public String getCpfCriptografado() {
        return cpfCriptografado;
    }

    public void setCpfCriptografado(String cpfCriptografado) {
        this.cpfCriptografado = cpfCriptografado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataEntradaEmpresa() {
        return dataEntradaEmpresa;
    }

    public void setDataEntradaEmpresa(LocalDate dataEntradaEmpresa) {
        this.dataEntradaEmpresa = dataEntradaEmpresa;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}


