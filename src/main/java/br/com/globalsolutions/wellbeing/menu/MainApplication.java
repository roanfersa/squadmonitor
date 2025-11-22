package br.com.globalsolutions.wellbeing.menu;

import br.com.globalsolutions.wellbeing.model.Funcionario;
import br.com.globalsolutions.wellbeing.model.Gestor;
import br.com.globalsolutions.wellbeing.model.Usuario;
import br.com.globalsolutions.wellbeing.service.UsuarioService;
import br.com.globalsolutions.wellbeing.util.DataSeeder;
import br.com.globalsolutions.wellbeing.util.InputUtil;

import java.util.Optional;

public class MainApplication {

    public static void main(String[] args) {
        DataSeeder.inicializarDados();

        System.out.println("==============================================");
        System.out.println("   Monitor de Bem-Estar do Time Scrum");
        System.out.println("==============================================");

        boolean executando = true;
        while (executando) {
            System.out.println();
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro rápido de funcionário");
            System.out.println("0 - Sair");
            int opcao = InputUtil.lerInt("Escolha uma opção: ", 0, 2);

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;
                case 2:
                    cadastrarFuncionario();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        System.out.println("Sistema encerrado.");
    }

    private static void realizarLogin() {
        System.out.println();
        System.out.println("--- Login ---");
        String email = InputUtil.lerString("E-mail: ");
        String senha = InputUtil.lerString("Senha: ");

        Optional<Usuario> usuarioOpt = UsuarioService.autenticar(email, senha);
        if (usuarioOpt.isEmpty()) {
            System.out.println("Credenciais inválidas. Tente novamente.");
            return;
        }

        Usuario usuario = usuarioOpt.get();
        if (usuario instanceof Funcionario) {
            MenuFuncionario.exibir((Funcionario) usuario);
        } else if (usuario instanceof Gestor) {
            MenuGestor.exibir((Gestor) usuario);
        } else {
            System.out.println("Tipo de usuário não suportado.");
        }

        UsuarioService.logout();
    }

    private static void cadastrarFuncionario() {
        System.out.println();
        System.out.println("--- Cadastro rápido de funcionário ---");
        String email = InputUtil.lerString("E-mail: ");
        String senha = InputUtil.lerString("Senha: ");
        String cargo = InputUtil.lerString("Cargo (ex.: Desenvolvedor Backend): ");
        String funcaoScrum = InputUtil.lerString("Função Scrum (ex.: Developer, QA): ");
        String turno = InputUtil.lerString("Turno de trabalho (ex.: Integral): ");

        Funcionario funcionario = UsuarioService.cadastrarFuncionarioSimples(
                email,
                senha,
                cargo,
                funcaoScrum,
                turno
        );

        System.out.printf("Funcionário cadastrado com sucesso: %s%n", funcionario.getEmail());
        System.out.println("Dica: faça login com esse e-mail e senha para realizar check-ins.");
    }
}


