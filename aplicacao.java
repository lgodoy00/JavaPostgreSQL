package APP;

import dao.usuarioDAO;
import java.util.List;
import model.usuario;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.sql.SQLException;

public class Aplicacao {

    private static usuarioDAO usuarioDAO = new usuarioDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 5) {
            System.out.println("Menu:");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Atualizar");
            System.out.println("4. Excluir");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        listarUsuarios();
                        break;
                    case 2:
                        inserirUsuario(scanner);
                        break;
                    case 3:
                        atualizarUsuario(scanner);
                        break;
                    case 4:
                        excluirUsuario(scanner);
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }

    private static void listarUsuarios() {
        try {
            for (usuario usuario : usuarioDAO.listarTodos()) {
                System.out.println(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    private static void inserirUsuario(Scanner scanner) {
        System.out.print("Digite o código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o login: ");
        String login = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        System.out.print("Digite o sexo: ");
        String sexo = scanner.nextLine();

        usuario usuario = new usuario(codigo, login, senha, sexo);

        try {
            usuarioDAO.inserir(usuario);
            System.out.println("Usuário inserido com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    private static void atualizarUsuario(Scanner scanner) {
        System.out.print("Digite o código do usuário a ser atualizado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o novo login: ");
        String login = scanner.nextLine();

        System.out.print("Digite a nova senha: ");
        String senha = scanner.nextLine();

        System.out.print("Digite o novo sexo: ");
        String sexo = scanner.nextLine();

        usuario usuario = new usuario(codigo, login, senha, sexo);

        try {
            usuarioDAO.atualizar(usuario);
            System.out.println("Usuário atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private static void excluirUsuario(Scanner scanner) {
        System.out.print("Digite o código do usuário a ser excluído: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 

        try {
            usuarioDAO.excluir(codigo);
            System.out.println("Usuário excluído com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
