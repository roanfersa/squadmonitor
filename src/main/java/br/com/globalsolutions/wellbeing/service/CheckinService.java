package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.CheckinEmocional;
import br.com.globalsolutions.wellbeing.model.Squad;
import br.com.globalsolutions.wellbeing.model.Usuario;
import br.com.globalsolutions.wellbeing.repository.CheckinRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CheckinService {

    private CheckinService() {
    }

    public static CheckinEmocional registrarCheckin(Usuario usuario,
                                                    int humorGeral,
                                                    int energia,
                                                    int estresse,
                                                    int foco,
                                                    int sono,
                                                    int cargaTrabalho,
                                                    String sentimentoDominante,
                                                    String comentarioOpcional) {

        // Regra opcional: um check-in por dia por usuário
        List<CheckinEmocional> hoje = CheckinRepository.listarPorUsuarioEData(usuario, LocalDate.now());
        if (!hoje.isEmpty()) {
            // Para simplificar, permitimos mais de um, mas isso poderia bloquear.
            System.out.println("Aviso: já existe check-in hoje. Registrando outro mesmo assim.");
        }

        CheckinEmocional checkin = new CheckinEmocional(
                0,
                LocalDateTime.now(),
                humorGeral,
                energia,
                estresse,
                foco,
                sono,
                cargaTrabalho,
                sentimentoDominante,
                comentarioOpcional,
                usuario
        );
        return CheckinRepository.salvar(checkin);
    }

    public static List<CheckinEmocional> listarPorUsuario(Usuario usuario) {
        return CheckinRepository.listarPorUsuario(usuario);
    }

    public static List<CheckinEmocional> listarPorSquad(Squad squad) {
        return CheckinRepository.listarPorSquad(squad);
    }
}


