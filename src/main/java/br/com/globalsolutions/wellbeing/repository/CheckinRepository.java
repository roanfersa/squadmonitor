package br.com.globalsolutions.wellbeing.repository;

import br.com.globalsolutions.wellbeing.model.CheckinEmocional;
import br.com.globalsolutions.wellbeing.model.Squad;
import br.com.globalsolutions.wellbeing.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckinRepository {

    private static final List<CheckinEmocional> CHECKINS = new ArrayList<>();
    private static int sequence = 1;

    private CheckinRepository() {
    }

    public static CheckinEmocional salvar(CheckinEmocional checkin) {
        if (checkin.getId() == 0) {
            checkin.setId(sequence++);
        }
        CHECKINS.add(checkin);
        return checkin;
    }

    public static List<CheckinEmocional> listarTodos() {
        return new ArrayList<>(CHECKINS);
    }

    public static List<CheckinEmocional> listarPorUsuario(Usuario usuario) {
        return CHECKINS.stream()
                .filter(c -> c.getUsuario() != null && c.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    public static List<CheckinEmocional> listarPorUsuarioEData(Usuario usuario, LocalDate data) {
        return CHECKINS.stream()
                .filter(c -> c.getUsuario() != null && c.getUsuario().equals(usuario))
                .filter(c -> c.getDataHora().toLocalDate().equals(data))
                .collect(Collectors.toList());
    }

    public static List<CheckinEmocional> listarPorSquad(Squad squad) {
        if (squad == null || squad.getMembros() == null) {
            return new ArrayList<>();
        }
        return CHECKINS.stream()
                .filter(c -> squad.getMembros().contains(c.getUsuario()))
                .collect(Collectors.toList());
    }
}


