package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.Release;
import br.com.globalsolutions.wellbeing.model.Sprint;
import br.com.globalsolutions.wellbeing.repository.ReleaseRepository;
import br.com.globalsolutions.wellbeing.repository.SprintRepository;

import java.util.List;

public class SprintReleaseService {

    private SprintReleaseService() {
    }

    public static List<Sprint> listarSprints() {
        return SprintRepository.listarTodos();
    }

    public static List<Release> listarReleases() {
        return ReleaseRepository.listarTodos();
    }
}


