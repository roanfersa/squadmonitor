package br.com.globalsolutions.wellbeing.repository;

import br.com.globalsolutions.wellbeing.model.Squad;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SquadRepository {

    private static final List<Squad> SQUADS = new ArrayList<>();
    private static int sequence = 1;

    private SquadRepository() {
    }

    public static Squad salvar(Squad squad) {
        if (squad.getId() == 0) {
            squad.setId(sequence++);
            SQUADS.add(squad);
        } else if (!SQUADS.contains(squad)) {
            SQUADS.add(squad);
        }
        return squad;
    }

    public static List<Squad> listarTodos() {
        return new ArrayList<>(SQUADS);
    }

    public static Optional<Squad> buscarPorId(int id) {
        return SQUADS.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
}


