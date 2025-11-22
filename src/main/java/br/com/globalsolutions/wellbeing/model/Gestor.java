package br.com.globalsolutions.wellbeing.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gestor extends Usuario {

    private String funcao; // PO / Scrum Master / Gestor
    private List<Squad> squadsResponsaveis = new ArrayList<>();

    public Gestor() {
    }

    public Gestor(String cpfCriptografado, String email, LocalDate dataNascimento,
                  String cargo, String senha, LocalDate dataEntradaEmpresa,
                  Departamento departamento, String funcao) {
        super(cpfCriptografado, email, dataNascimento, cargo, senha, dataEntradaEmpresa, departamento);
        this.funcao = funcao;
    }

    @Override
    public void realizarCheckinEmocional() {
        System.out.println("[Simulação] Gestor realizando check-in emocional via console.");
    }

    @Override
    public void visualizarMeusCheckins() {
        System.out.println("[Simulação] Exibindo check-ins do gestor via console.");
    }

    public void visualizarDashboardSquad() {
        System.out.println("[Simulação] Visualizando dashboard agregado dos squads.");
    }

    public void receberAlertaRisco() {
        System.out.println("[Simulação] Recebendo alertas de risco emocional.");
    }

    public void gerarRelatorioClima() {
        System.out.println("[Simulação] Gerando relatório de clima do time.");
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<Squad> getSquadsResponsaveis() {
        return squadsResponsaveis;
    }

    public void setSquadsResponsaveis(List<Squad> squadsResponsaveis) {
        this.squadsResponsaveis = squadsResponsaveis;
    }

    public void adicionarSquadResponsavel(Squad squad) {
        if (!squadsResponsaveis.contains(squad)) {
            squadsResponsaveis.add(squad);
        }
    }
}


