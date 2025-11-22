package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.Alerta;
import br.com.globalsolutions.wellbeing.model.CheckinEmocional;
import br.com.globalsolutions.wellbeing.model.Gestor;
import br.com.globalsolutions.wellbeing.model.NivelAlerta;
import br.com.globalsolutions.wellbeing.model.Squad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IAService {

    private IAService() {
    }

    /**
     * Regras simples de exemplo:
     * - Média de estresse >= 4 nos últimos 5 dias -> alerta ALTO.
     * - Energia <= 2 e sono <= 2 em pelo menos 3 check-ins recentes -> alerta CRITICO.
     */
    public static List<Alerta> analisarPadroes(List<CheckinEmocional> checkins, Squad squad, Gestor gestor) {
        List<Alerta> alertas = new ArrayList<>();
        if (checkins == null || checkins.isEmpty()) {
            return alertas;
        }

        LocalDate hoje = LocalDate.now();
        List<CheckinEmocional> ultimos5Dias = checkins.stream()
                .filter(c -> !c.getDataHora().toLocalDate().isBefore(hoje.minusDays(5)))
                .collect(Collectors.toList());

        if (!ultimos5Dias.isEmpty()) {
            double mediaEstresse = ultimos5Dias.stream()
                    .mapToInt(CheckinEmocional::getEstresse)
                    .average()
                    .orElse(0);

            if (mediaEstresse >= 4.0) {
                String mensagem = String.format("Média de estresse alta (%.2f) nos últimos dias.", mediaEstresse);
                alertas.add(AlertaService.criarAlerta(
                        "PADRAO_ESTRESSE_ALTO",
                        NivelAlerta.ALTO,
                        mensagem,
                        squad,
                        gestor
                ));
            }
        }

        long criticos = checkins.stream()
                .filter(c -> c.getEnergia() <= 2 && c.getSono() <= 2)
                .count();

        if (criticos >= 3) {
            String mensagem = "Baixa energia e sono recorrentes indicam forte risco de burnout.";
            alertas.add(AlertaService.criarAlerta(
                    "RISCO_BURNOUT",
                    NivelAlerta.CRITICO,
                    mensagem,
                    squad,
                    gestor
            ));
        }

        return alertas;
    }

    public static boolean identificarRiscoBurnout(Squad squad, List<CheckinEmocional> checkins) {
        if (squad == null || checkins == null || checkins.isEmpty()) {
            return false;
        }
        long criticos = checkins.stream()
                .filter(c -> c.getEnergia() <= 2 && c.getSono() <= 2)
                .count();
        return criticos >= 3;
    }

    public static String gerarRecomendacao(Squad squad, List<CheckinEmocional> checkins) {
        if (checkins == null || checkins.isEmpty()) {
            return "Sem dados suficientes para recomendações.";
        }

        double mediaEstresse = checkins.stream()
                .mapToInt(CheckinEmocional::getEstresse)
                .average()
                .orElse(0);

        if (mediaEstresse >= 4) {
            return "Recomenda-se retrospectiva focada em carga de trabalho e prioridades, além de revisar WIP da sprint.";
        }

        double mediaHumor = checkins.stream()
                .mapToInt(CheckinEmocional::getHumorGeral)
                .average()
                .orElse(0);

        if (mediaHumor <= 2) {
            return "Promover conversa 1:1 e atividades de team building para melhorar o clima do time.";
        }

        return "Time em nível estável. Manter cadência de cerimônias e monitorar sinais de estresse.";
    }
}


