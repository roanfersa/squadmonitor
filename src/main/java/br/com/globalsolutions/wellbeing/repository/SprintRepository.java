package br.com.globalsolutions.wellbeing.repository;

import br.com.globalsolutions.wellbeing.model.Sprint;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SprintRepository {

    private static final List<Sprint> SPRINTS = new ArrayList<>();
    private static int sequence = 1;

    private SprintRepository() {
    }

    public static Sprint salvar(Sprint sprint) {
        if (sprint.getId() == 0) {
            sprint.setId(sequence++);
            SPRINTS.add(sprint);
        } else if (!SPRINTS.contains(sprint)) {
            SPRINTS.add(sprint);
        }
        return sprint;
    }

    public static List<Sprint> listarTodos() {
        return new ArrayList<>(SPRINTS);
    }

    public static Optional<Sprint> buscarPorId(int id) {
        return SPRINTS.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
}


