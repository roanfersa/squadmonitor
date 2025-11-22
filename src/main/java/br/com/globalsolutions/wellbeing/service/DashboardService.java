package br.com.globalsolutions.wellbeing.service;

import br.com.globalsolutions.wellbeing.model.CheckinEmocional;
import br.com.globalsolutions.wellbeing.model.Dashboard;
import br.com.globalsolutions.wellbeing.model.Squad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardService {

    private DashboardService() {
    }

    public static Dashboard atualizarDados(Squad squad) {
        Dashboard dashboard = new Dashboard(squad);
        List<CheckinEmocional> checkins = CheckinService.listarPorSquad(squad);
        if (checkins.isEmpty()) {
            dashboard.setMediaHumorGeral(0);
            dashboard.setTendenciaEstresse(0);
            dashboard.setTotalCheckins(0);
            dashboard.setRiscosAtivos(0);
            dashboard.setUltimaAtualizacao(LocalDateTime.now());
            return dashboard;
        }

        double mediaHumor = checkins.stream()
                .mapToInt(CheckinEmocional::getHumorGeral)
                .average()
                .orElse(0);
        dashboard.setMediaHumorGeral(mediaHumor);

        // Tendência simples: média de estresse últimos 3 dias - média 3 dias anteriores
        LocalDate hoje = LocalDate.now();
        double mediaRecente = checkins.stream()
                .filter(c -> !c.getDataHora().toLocalDate().isBefore(hoje.minusDays(3)))
                .mapToInt(CheckinEmocional::getEstresse)
                .average()
                .orElse(0);

        double mediaAnterior = checkins.stream()
                .filter(c -> c.getDataHora().toLocalDate().isBefore(hoje.minusDays(3))
                        && !c.getDataHora().toLocalDate().isBefore(hoje.minusDays(6)))
                .mapToInt(CheckinEmocional::getEstresse)
                .average()
                .orElse(0);

        dashboard.setTendenciaEstresse(mediaRecente - mediaAnterior);
        dashboard.setTotalCheckins(checkins.size());

        // Riscos ativos = quantidade de check-ins com estresse >= 4 nos últimos 3 dias
        long riscos = checkins.stream()
                .filter(c -> !c.getDataHora().toLocalDate().isBefore(hoje.minusDays(3)))
                .filter(c -> c.getEstresse() >= 4)
                .count();
        dashboard.setRiscosAtivos((int) riscos);
        dashboard.setUltimaAtualizacao(LocalDateTime.now());
        return dashboard;
    }
}


