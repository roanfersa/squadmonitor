package br.com.globalsolutions.wellbeing.util;

import br.com.globalsolutions.wellbeing.model.Departamento;
import br.com.globalsolutions.wellbeing.model.Funcionario;
import br.com.globalsolutions.wellbeing.model.Gestor;
import br.com.globalsolutions.wellbeing.model.Squad;
import br.com.globalsolutions.wellbeing.model.Release;
import br.com.globalsolutions.wellbeing.model.Sprint;
import br.com.globalsolutions.wellbeing.repository.DepartamentoRepository;
import br.com.globalsolutions.wellbeing.repository.ReleaseRepository;
import br.com.globalsolutions.wellbeing.repository.SprintRepository;
import br.com.globalsolutions.wellbeing.repository.SquadRepository;
import br.com.globalsolutions.wellbeing.repository.UsuarioRepository;

import java.time.LocalDate;

public class DataSeeder {

    private DataSeeder() {
    }

    public static void inicializarDados() {
        // Release e Sprint
        Release release1 = new Release(0, "Release 1", "Q1", LocalDate.now().minusWeeks(4), LocalDate.now().plusWeeks(4));
        release1 = ReleaseRepository.salvar(release1);

        Sprint sprintAtual = new Sprint(0, "Sprint Atual", LocalDate.now().minusDays(7), LocalDate.now().plusDays(7), release1);
        SprintRepository.salvar(sprintAtual);

        // Gestor e Departamento
        Gestor gestor = new Gestor(
                "cpf-gestor",
                "gestor@empresa.com",
                LocalDate.of(1985, 1, 1),
                "Gestor de TI",
                "123",
                LocalDate.now().minusYears(5),
                null,
                "Scrum Master"
        );

        Departamento departamentoTi = new Departamento(0, "TI", "Tecnologia da Informação", gestor);
        DepartamentoRepository.salvar(departamentoTi);
        gestor.setDepartamento(departamentoTi);
        UsuarioRepository.salvar(gestor);

        // Squad
        Squad squadA = new Squad(0, "Squad A - Plataforma", gestor, departamentoTi);
        SquadRepository.salvar(squadA);
        gestor.adicionarSquadResponsavel(squadA);

        // Funcionários
        Funcionario dev1 = new Funcionario(
                "cpf-dev1",
                "dev1@empresa.com",
                LocalDate.of(1995, 3, 10),
                "Desenvolvedor Backend",
                "123",
                LocalDate.now().minusYears(2),
                departamentoTi,
                "Developer",
                "Integral"
        );
        dev1.adicionarSquad(squadA);
        squadA.adicionarMembro(dev1);
        UsuarioRepository.salvar(dev1);

        Funcionario qa1 = new Funcionario(
                "cpf-qa1",
                "qa1@empresa.com",
                LocalDate.of(1992, 7, 20),
                "QA Engineer",
                "123",
                LocalDate.now().minusYears(3),
                departamentoTi,
                "QA",
                "Integral"
        );
        qa1.adicionarSquad(squadA);
        squadA.adicionarMembro(qa1);
        UsuarioRepository.salvar(qa1);
    }
}


