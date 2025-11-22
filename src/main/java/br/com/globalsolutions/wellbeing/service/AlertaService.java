package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.Alerta;
import br.com.globalsolutions.wellbeing.model.Gestor;
import br.com.globalsolutions.wellbeing.model.NivelAlerta;
import br.com.globalsolutions.wellbeing.model.Squad;
import br.com.globalsolutions.wellbeing.repository.AlertaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class AlertaService {

    private AlertaService() {
    }

    public static Alerta criarAlerta(String tipo,
                                     NivelAlerta nivel,
                                     String mensagem,
                                     Squad squad,
                                     Gestor gestorDestino) {
        Alerta alerta = new Alerta(
                0,
                tipo,
                nivel,
                mensagem,
                LocalDateTime.now(),
                squad,
                gestorDestino
        );
        return AlertaRepository.salvar(alerta);
    }

    public static List<Alerta> listarPorGestor(Gestor gestor) {
        return AlertaRepository.listarPorGestor(gestor);
    }

    public static List<Alerta> listarPorSquad(Squad squad) {
        return AlertaRepository.listarPorSquad(squad);
    }
}


