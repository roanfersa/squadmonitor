package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.Departamento;
import br.com.globalsolutions.wellbeing.repository.DepartamentoRepository;

import java.util.List;
import java.util.Optional;

public class DepartamentoService {

    private DepartamentoService() {
    }

    public static List<Departamento> listarTodos() {
        return DepartamentoRepository.listarTodos();
    }

    public static Optional<Departamento> buscarPorId(int id) {
        return DepartamentoRepository.buscarPorId(id);
    }
}


