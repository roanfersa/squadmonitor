package br.com.globalsolutions.wellbeing.repository;

import br.com.globalsolutions.wellbeing.model.Alerta;
import br.com.globalsolutions.wellbeing.model.Gestor;
import br.com.globalsolutions.wellbeing.model.Squad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlertaRepository {

    private static final List<Alerta> ALERTAS = new ArrayList<>();
    private static int sequence = 1;

    private AlertaRepository() {
    }

    public static Alerta salvar(Alerta alerta) {
        if (alerta.getId() == 0) {
            alerta.setId(sequence++);
        }
        ALERTAS.add(alerta);
        return alerta;
    }

    public static List<Alerta> listarTodos() {
        return new ArrayList<>(ALERTAS);
    }

    public static List<Alerta> listarPorGestor(Gestor gestor) {
        return ALERTAS.stream()
                .filter(a -> a.getGestorDestino() != null && a.getGestorDestino().equals(gestor))
                .collect(Collectors.toList());
    }

    public static List<Alerta> listarPorSquad(Squad squad) {
        return ALERTAS.stream()
                .filter(a -> a.getSquad() != null && a.getSquad().equals(squad))
                .collect(Collectors.toList());
    }
}


