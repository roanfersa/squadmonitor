package br.com.globalsolutions.wellbeing.menu;

import br.com.globalsolutions.wellbeing.model.CheckinEmocional;
import br.com.globalsolutions.wellbeing.model.Funcionario;
import br.com.globalsolutions.wellbeing.service.CheckinService;
import br.com.globalsolutions.wellbeing.util.InputUtil;

import java.util.List;

public class MenuFuncionario {

    private MenuFuncionario() {
    }

    public static void exibir(Funcionario funcionario) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println();
            System.out.printf("=== Menu Funcionário (%s) ===%n", funcionario.getEmail());
            System.out.println("1 - Realizar check-in emocional");
            System.out.println("2 - Visualizar meus check-ins");
            System.out.println("0 - Logout");

            int opcao = InputUtil.lerInt("Escolha uma opção: ", 0, 2);
            switch (opcao) {
                case 1:
                    realizarCheckin(funcionario);
                    break;
                case 2:
                    listarCheckins(funcionario);
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void realizarCheckin(Funcionario funcionario) {
        System.out.println();
        System.out.println("--- Check-in emocional ---");

        int humorGeral = InputUtil.lerInt("Humor geral (1-5): ", 1, 5);
        int energia = InputUtil.lerInt("Energia (1-5): ", 1, 5);
        int estresse = InputUtil.lerInt("Estresse (1-5): ", 1, 5);
        int foco = InputUtil.lerInt("Foco (1-5): ", 1, 5);
        int sono = InputUtil.lerInt("Sono (1-5): ", 1, 5);
        int cargaTrabalho = InputUtil.lerInt("Carga de trabalho (1-5): ", 1, 5);
        String sentimento = InputUtil.lerString("Sentimento dominante: ");
        String comentario = InputUtil.lerString("Comentário (opcional): ");

        CheckinService.registrarCheckin(
                funcionario,
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

    private static void listarCheckins(Funcionario funcionario) {
        System.out.println();
        System.out.println("--- Meus check-ins ---");
        List<CheckinEmocional> checkins = CheckinService.listarPorUsuario(funcionario);
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
}


