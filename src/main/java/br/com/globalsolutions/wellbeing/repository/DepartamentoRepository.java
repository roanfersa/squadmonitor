package br.com.globalsolutions.wellbeing.repository;

import br.com.globalsolutions.wellbeing.model.Departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartamentoRepository {

    private static final List<Departamento> DEPARTAMENTOS = new ArrayList<>();
    private static int sequence = 1;

    private DepartamentoRepository() {
    }

    public static Departamento salvar(Departamento departamento) {
        if (departamento.getId() == 0) {
            departamento.setId(sequence++);
            DEPARTAMENTOS.add(departamento);
        } else if (!DEPARTAMENTOS.contains(departamento)) {
            DEPARTAMENTOS.add(departamento);
        }
        return departamento;
    }

    public static List<Departamento> listarTodos() {
        return new ArrayList<>(DEPARTAMENTOS);
    }

    public static Optional<Departamento> buscarPorId(int id) {
        return DEPARTAMENTOS.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
    }
}


