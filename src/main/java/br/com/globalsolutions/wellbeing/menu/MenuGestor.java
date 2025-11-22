package br.com.globalsolutions.wellbeing.menu;

import br.com.globalsolutions.wellbeing.model.Alerta;
import br.com.globalsolutions.wellbeing.model.CheckinEmocional;
import br.com.globalsolutions.wellbeing.model.Dashboard;
import br.com.globalsolutions.wellbeing.model.Gestor;
import br.com.globalsolutions.wellbeing.model.Squad;
import br.com.globalsolutions.wellbeing.service.AlertaService;
import br.com.globalsolutions.wellbeing.service.CheckinService;
import br.com.globalsolutions.wellbeing.service.DashboardService;
import br.com.globalsolutions.wellbeing.service.IAService;
import br.com.globalsolutions.wellbeing.util.InputUtil;
import java.util.Random;
import java.util.List;

public class MenuGestor {

    private MenuGestor() {
    }

    public static void exibir(Gestor gestor) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println();
            System.out.printf("=== Menu Gestor (%s) ===%n", gestor.getEmail());
            System.out.println("1 - Visualizar dashboard de um squad");
            System.out.println("2 - Ver alertas de risco emocional");
            System.out.println("3 - Gerar relatório de clima do time");
            System.out.println("4 - Realizar meu próprio check-in emocional");
            System.out.println("5 - Visualizar meus check-ins");
            System.out.println("6 - Gerar dados de simulação (IA/Dashboard)");
            System.out.println("0 - Logout");

            int opcao = InputUtil.lerInt("Escolha uma opção: ", 0, 6);
            switch (opcao) {
                case 1:
                    visualizarDashboard(gestor);
                    break;
                case 2:
                    listarAlertas(gestor);
                    break;
                case 3:
                    gerarRelatorioClima(gestor);
                    break;
                case 4:
                    realizarCheckinProprio(gestor);
                    break;
                case 5:
                    visualizarMeusCheckins(gestor);
                    break;
                case 6:
                    gerarDadosSimulacao(gestor);
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void visualizarDashboard(Gestor gestor) {
        List<Squad> squads = gestor.getSquadsResponsaveis();
        if (squads == null || squads.isEmpty()) {
            System.out.println("Você ainda não possui squads cadastrados.");
            InputUtil.pausar();
            return;
        }

        System.out.println();
        System.out.println("--- Squads sob sua responsabilidade ---");
        for (int i = 0; i < squads.size(); i++) {
            Squad s = squads.get(i);
            System.out.printf("%d - %s (id=%d)%n", i + 1, s.getDescricao(), s.getId());
        }
        int escolha = InputUtil.lerInt("Escolha um squad: ", 1, squads.size());
        Squad selecionado = squads.get(escolha - 1);

        Dashboard dashboard = DashboardService.atualizarDados(selecionado);
        System.out.println();
        System.out.printf("=== Dashboard Squad: %s ===%n", selecionado.getDescricao());
        System.out.printf("Média de humor geral: %.2f%n", dashboard.getMediaHumorGeral());
        System.out.printf("Tendência de estresse (últimos dias): %.2f%n", dashboard.getTendenciaEstresse());
        System.out.printf("Total de check-ins no período: %d%n", dashboard.getTotalCheckins());
        System.out.printf("Riscos ativos (estresse alto recente): %d%n", dashboard.getRiscosAtivos());
        System.out.printf("Última atualização: %s%n", dashboard.getUltimaAtualizacao());

        List<CheckinEmocional> checkinsSquad = CheckinService.listarPorSquad(selecionado);
        String recomendacao = IAService.gerarRecomendacao(selecionado, checkinsSquad);
        System.out.println("Recomendação da IA: " + recomendacao);

        InputUtil.pausar();
    }

    private static void visualizarMeusCheckins(Gestor gestor) {
        System.out.println();
        System.out.println("--- Meus check-ins (Gestor) ---");
        List<CheckinEmocional> checkins = CheckinService.listarPorUsuario(gestor);
        if (checkins.isEmpty()) {
            System.out.println("Nenhum check-in encontrado.");
        } else {
            checkins.forEach(c -> System.out.printf(
                    "%s | Humor: %d | Energia: %d | Estresse: %d | Comentário: %s%n",
                    c.getDataHora(),
                    c.getHumorGeral(),
                    c.getEnergia(),
                    c.getEstresse(),
                    c.getComentarioOpcional() == null ? "" : c.getComentarioOpcional()
            ));
        }
        InputUtil.pausar();
    }

    private static void listarAlertas(Gestor gestor) {
        System.out.println();
        System.out.println("--- Alertas de risco emocional ---");
        List<Alerta> alertas = AlertaService.listarPorGestor(gestor);
        if (alertas.isEmpty()) {
            System.out.println("Nenhum alerta encontrado.");
        } else {
            alertas.forEach(a -> System.out.printf(
                    "[%s] Nível: %s | Squad: %s | Mensagem: %s%n",
                    a.getDataGeracao(),
                    a.getNivel(),
                    a.getSquad() != null ? a.getSquad().getDescricao() : "(N/A)",
                    a.getMensagem()
            ));
        }
        InputUtil.pausar();
    }

    private static void gerarRelatorioClima(Gestor gestor) {
        System.out.println();
        System.out.println("--- Relatório de clima do time ---");
        List<Squad> squads = gestor.getSquadsResponsaveis();
        if (squads == null || squads.isEmpty()) {
            System.out.println("Você ainda não possui squads cadastrados.");
            InputUtil.pausar();
            return;
        }

        for (Squad s : squads) {
            Dashboard dashboard = DashboardService.atualizarDados(s);
            List<CheckinEmocional> checkins = CheckinService.listarPorSquad(s);
            String recomendacao = IAService.gerarRecomendacao(s, checkins);

            System.out.println("--------------------------------");
            System.out.printf("Squad: %s%n", s.getDescricao());
            System.out.printf("Média de humor: %.2f | Tendência estresse: %.2f | Total check-ins: %d | Riscos ativos: %d%n",
                    dashboard.getMediaHumorGeral(),
                    dashboard.getTendenciaEstresse(),
                    dashboard.getTotalCheckins(),
                    dashboard.getRiscosAtivos());
            System.out.println("Recomendação: " + recomendacao);
        }

        System.out.println("--------------------------------");
        System.out.println("Observação: dados exibidos de forma agregada, sem identificação individual dos membros.");
        InputUtil.pausar();
    }

    private static void realizarCheckinProprio(Gestor gestor) {
        // Reutiliza o fluxo do funcionário, mas sem exibir squads.
        System.out.println();
        System.out.println("--- Meu check-in emocional (Gestor) ---");

        int humorGeral = InputUtil.lerInt("Humor geral (1-5): ", 1, 5);
        int energia = InputUtil.lerInt("Energia (1-5): ", 1, 5);
        int estresse = InputUtil.lerInt("Estresse (1-5): ", 1, 5);
        int foco = InputUtil.lerInt("Foco (1-5): ", 1, 5);
        int sono = InputUtil.lerInt("Sono (1-5): ", 1, 5);
        int cargaTrabalho = InputUtil.lerInt("Carga de trabalho (1-5): ", 1, 5);
        String sentimento = InputUtil.lerString("Sentimento dominante: ");
        String comentario = InputUtil.lerString("Comentário (opcional): ");

        CheckinService.registrarCheckin(
                gestor,
                humorGeral,
                energia,
                estresse,
                foco,
                sono,
                cargaTrabalho,
                sentimento,
                comentario
        );

        System.out.println("Check-in registrado com sucesso!");
        InputUtil.pausar();
    }

    /**
     * Gera um conjunto de check-ins simulados para os membros dos squads do gestor,
     * incluindo alguns cenários de alto estresse para acionar alertas e IA.
     */
    private static void gerarDadosSimulacao(Gestor gestor) {
        System.out.println();
        System.out.println("--- Geração de dados de simulação ---");
        List<Squad> squads = gestor.getSquadsResponsaveis();
        if (squads == null || squads.isEmpty()) {
            System.out.println("Nenhum squad encontrado para o gestor. Nada a simular.");
            InputUtil.pausar();
            return;
        }

        Random random = new Random();
        int totalGerado = 0;

        for (Squad squad : squads) {
            if (squad.getMembros() == null || squad.getMembros().isEmpty()) {
                continue;
            }
            // Para cada membro do squad, gera alguns check-ins misturando dias bons e ruins
            squad.getMembros().forEach(membro -> {
                // 3 check-ins "bons"
                for (int i = 0; i < 3; i++) {
                    CheckinService.registrarCheckin(
                            membro,
                            4 + random.nextInt(2), // humor 4-5
                            3 + random.nextInt(3), // energia 3-5
                            1 + random.nextInt(2), // estresse 1-2
                            3 + random.nextInt(3), // foco 3-5
                            3 + random.nextInt(3), // sono 3-5
                            2 + random.nextInt(3), // carga 2-4
                            "Motivado",
                            "Dia tranquilo de trabalho"
                    );
                }
                // 5 check-ins "críticos" para gerar risco de burnout/estresse alto
                for (int i = 0; i < 5; i++) {
                    CheckinService.registrarCheckin(
                            membro,
                            1 + random.nextInt(2), // humor 1-2
                            1 + random.nextInt(2), // energia 1-2
                            4 + random.nextInt(2), // estresse 4-5
                            1 + random.nextInt(3), // foco 1-3
                            1 + random.nextInt(2), // sono 1-2
                            4 + random.nextInt(2), // carga 4-5
                            "Sobrecarga",
                            "Alta pressão e muitas demandas na sprint"
                    );
                }
            });

            totalGerado += squad.getMembros().size() * (3 + 5);
        }

        System.out.printf("Foram gerados aproximadamente %d check-ins simulados para os squads do gestor.%n", totalGerado);
        System.out.println("Agora você pode visualizar dashboards, alertas e relatórios com dados mais ricos.");
        InputUtil.pausar();
    }
}


