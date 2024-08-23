package modelo.projeto.para.cliente;

import java.util.Scanner;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteDAO dao = new ClienteDAO();
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("------ Menu ------");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Inserir Cliente");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Excluir Cliente");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    listarClientes(dao);
                    break;
                case 2:
                    inserirCliente(dao, scanner);
                    break;
                case 3:
                    atualizarCliente(dao, scanner);
                    break;
                case 4:
                    excluirCliente(dao, scanner);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void listarClientes(ClienteDAO dao) {
        List<Cliente> clientes = dao.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | Idade: " + cliente.getIdade());
            }
        }
    }

    private static void inserirCliente(ClienteDAO dao, Scanner scanner) {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Idade do cliente: ");
        int idade = scanner.nextInt();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setIdade(idade);

        dao.inserir(cliente);
        System.out.println("Cliente inserido com sucesso!");
    }

    private static void atualizarCliente(ClienteDAO dao, Scanner scanner) {
        System.out.print("ID do cliente a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Novo nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Nova idade do cliente: ");
        int idade = scanner.nextInt();

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setIdade(idade);

        dao.atualizar(cliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    private static void excluirCliente(ClienteDAO dao, Scanner scanner) {
        System.out.print("ID do cliente a ser excluído: ");
        int id = scanner.nextInt();

        dao.deletar(id);
        System.out.println("Cliente excluído com sucesso!");
    }
}
