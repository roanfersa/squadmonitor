package br.com.globalsolutions.wellbeing.model;

import java.time.LocalDateTime;

public class Alerta {

    private int id;
    private String tipo;
    private NivelAlerta nivel;
    private String mensagem;
    private LocalDateTime dataGeracao;
    private Squad squad;
    private Gestor gestorDestino;

    public Alerta() {
    }

    public Alerta(int id, String tipo, NivelAlerta nivel, String mensagem,
                  LocalDateTime dataGeracao, Squad squad, Gestor gestorDestino) {
        this.id = id;
        this.tipo = tipo;
        this.nivel = nivel;
        this.mensagem = mensagem;
        this.dataGeracao = dataGeracao;
        this.squad = squad;
        this.gestorDestino = gestorDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public NivelAlerta getNivel() {
        return nivel;
    }

    public void setNivel(NivelAlerta nivel) {
        this.nivel = nivel;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
    }

    public Gestor getGestorDestino() {
        return gestorDestino;
    }

    public void setGestorDestino(Gestor gestorDestino) {
        this.gestorDestino = gestorDestino;
    }
}


