package br.com.globalsolutions.wellbeing.repository;

import br.com.globalsolutions.wellbeing.model.Release;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReleaseRepository {

    private static final List<Release> RELEASES = new ArrayList<>();
    private static int sequence = 1;

    private ReleaseRepository() {
    }

    public static Release salvar(Release release) {
        if (release.getId() == 0) {
            release.setId(sequence++);
            RELEASES.add(release);
        } else if (!RELEASES.contains(release)) {
            RELEASES.add(release);
        }
        return release;
    }

    public static List<Release> listarTodos() {
        return new ArrayList<>(RELEASES);
    }

    public static Optional<Release> buscarPorId(int id) {
        return RELEASES.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }
}


