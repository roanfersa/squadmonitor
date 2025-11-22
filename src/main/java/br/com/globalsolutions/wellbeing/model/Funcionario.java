package br.com.globalsolutions.wellbeing.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario {

    private String funcaoScrum;
    private String turnoTrabalho;
    private List<Squad> squads = new ArrayList<>();

    public Funcionario() {
    }

    public Funcionario(String cpfCriptografado, String email, LocalDate dataNascimento,
                       String cargo, String senha, LocalDate dataEntradaEmpresa,
                       Departamento departamento, String funcaoScrum, String turnoTrabalho) {
        super(cpfCriptografado, email, dataNascimento, cargo, senha, dataEntradaEmpresa, departamento);
        this.funcaoScrum = funcaoScrum;
        this.turnoTrabalho = turnoTrabalho;
    }

    @Override
    public void realizarCheckinEmocional() {
        // A lógica real será feita via serviços/menus.
        // Aqui apenas deixamos o método para cumprir o contrato da modelagem.
        System.out.println("[Simulação] Funcionario realizando check-in emocional via console.");
    }

    @Override
    public void visualizarMeusCheckins() {
        System.out.println("[Simulação] Exibindo check-ins do funcionário via console.");
    }

    public String getFuncaoScrum() {
        return funcaoScrum;
    }

    public void setFuncaoScrum(String funcaoScrum) {
        this.funcaoScrum = funcaoScrum;
    }

    public String getTurnoTrabalho() {
        return turnoTrabalho;
    }

    public void setTurnoTrabalho(String turnoTrabalho) {
        this.turnoTrabalho = turnoTrabalho;
    }

    public List<Squad> getSquads() {
        return squads;
    }

    public void setSquads(List<Squad> squads) {
        this.squads = squads;
    }

    public void adicionarSquad(Squad squad) {
        if (!squads.contains(squad) && squads.size() < 2) {
            squads.add(squad);
        }
    }
}


