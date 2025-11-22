package br.com.globalsolutions.wellbeing.repository;

import br.com.globalsolutions.wellbeing.model.Funcionario;
import br.com.globalsolutions.wellbeing.model.Gestor;
import br.com.globalsolutions.wellbeing.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioRepository {

    private static final List<Usuario> USUARIOS = new ArrayList<>();
    private static int sequence = 1;

    private UsuarioRepository() {
    }

    public static void salvar(Usuario usuario) {
        if (!USUARIOS.contains(usuario)) {
            USUARIOS.add(usuario);
        }
    }

    public static List<Usuario> listarTodos() {
        return new ArrayList<>(USUARIOS);
    }

    public static Optional<Usuario> buscarPorEmail(String email) {
        return USUARIOS.stream()
                .filter(u -> u.getEmail() != null && u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public static List<Funcionario> listarFuncionarios() {
        return USUARIOS.stream()
                .filter(u -> u instanceof Funcionario)
                .map(u -> (Funcionario) u)
                .collect(Collectors.toList());
    }

    public static List<Gestor> listarGestores() {
        return USUARIOS.stream()
                .filter(u -> u instanceof Gestor)
                .map(u -> (Gestor) u)
                .collect(Collectors.toList());
    }

    public static int proximoId() {
        return sequence++;
    }
}


