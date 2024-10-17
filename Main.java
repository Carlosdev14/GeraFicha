package geraFicha;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        boolean logado = false;

        
        while (!logado) {
            System.out.println("\n\nBem-vindo ao sistema de personagens!");
            System.out.println("1. Entrar");
            System.out.println("2. Cadastrar Usuário");
            System.out.print("\nEscolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    logado = login.entrar();  
                    break;
                case 2:
                    login.cadastrarUsuario(); // Cadastro de um novo usuário
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        boolean sair = false; // menu após o login
        String usuarioLogado = login.getUsuarioLogado(); // armazena o usuário logado

        while (!sair) {
            System.out.println("\nMenu de opções:");
            System.out.println("1. Cadastrar Personagem");
            System.out.println("2. Exibir Personagens");
            System.out.println("3. Excluir Personagem");
            System.out.println("4. Excluir Usuário");
            System.out.println("5. Sair");
            System.out.print("\nEscolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    login.cadastrarPersonagem(usuarioLogado);  
                    break;
                case 2:
                    login.exibirPersonagens(usuarioLogado);  
                    break;
                case 3:
                    login.excluirPersonagem(usuarioLogado);  
                    break;
                case 4:
                    login.excluirUsuario();  
                    logado = false; 
                    break;
                case 5:
                    sair = true;  
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}
