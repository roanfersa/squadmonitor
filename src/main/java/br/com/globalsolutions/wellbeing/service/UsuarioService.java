package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.Departamento;
import br.com.globalsolutions.wellbeing.model.Funcionario;
import br.com.globalsolutions.wellbeing.model.Squad;
import br.com.globalsolutions.wellbeing.model.Usuario;
import br.com.globalsolutions.wellbeing.repository.DepartamentoRepository;
import br.com.globalsolutions.wellbeing.repository.SquadRepository;
import br.com.globalsolutions.wellbeing.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private static Usuario usuarioLogado;

    private UsuarioService() {
    }

    public static Optional<Usuario> autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = UsuarioRepository.buscarPorEmail(email)
                .filter(u -> u.getSenha() != null && u.getSenha().equals(senha));

        usuarioOpt.ifPresent(u -> usuarioLogado = u);
        return usuarioOpt;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void logout() {
        usuarioLogado = null;
    }

    /**
     * Cadastro simples de funcionário, reutilizando o primeiro departamento e squad existentes, se houver.
     */
    public static Funcionario cadastrarFuncionarioSimples(String email,
                                                          String senha,
                                                          String cargo,
                                                          String funcaoScrum,
                                                          String turno) {
        // Evita cadastro duplicado por e-mail
        Optional<Usuario> existente = UsuarioRepository.buscarPorEmail(email);
        if (existente.isPresent() && existente.get() instanceof Funcionario) {
            return (Funcionario) existente.get();
        }

        List<Departamento> departamentos = DepartamentoRepository.listarTodos();
        Departamento departamento = departamentos.isEmpty() ? null : departamentos.get(0);

        List<Squad> squads = SquadRepository.listarTodos();
        Squad squad = squads.isEmpty() ? null : squads.get(0);

        Funcionario funcionario = new Funcionario(
                "cpf-" + System.currentTimeMillis(),
                email,
                LocalDate.of(1990, 1, 1),
                cargo,
                senha,
                LocalDate.now(),
                departamento,
                funcaoScrum,
                turno
        );

        if (squad != null) {
            funcionario.adicionarSquad(squad);
            squad.adicionarMembro(funcionario);
        }

        UsuarioRepository.salvar(funcionario);
        return funcionario;
    }
}


