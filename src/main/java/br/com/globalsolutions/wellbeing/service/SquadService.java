package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.Squad;
import br.com.globalsolutions.wellbeing.repository.SquadRepository;

import java.util.List;
import java.util.Optional;

public class SquadService {

    private SquadService() {
    }

    public static List<Squad> listarTodos() {
        return SquadRepository.listarTodos();
    }

    public static Optional<Squad> buscarPorId(int id) {
        return SquadRepository.buscarPorId(id);
    }
}


