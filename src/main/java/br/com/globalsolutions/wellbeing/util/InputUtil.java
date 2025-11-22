package br.com.globalsolutions.wellbeing.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {
    }

    public static int lerInt(String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(SCANNER.nextLine());
                if (valor < min || valor > max) {
                    System.out.printf("Informe um valor entre %d e %d.%n", min, max);
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return SCANNER.nextLine();
    }

    public static void pausar() {
        System.out.println("Pressione ENTER para continuar...");
        SCANNER.nextLine();
    }
}


