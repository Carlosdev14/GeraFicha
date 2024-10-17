package geraFicha;

import java.util.ArrayList; // criar listas dinâmicas que podem crescer e encolher conforme os elementos são adicionados ou removidos.
import java.util.HashMap; // gerenciar usuários e suas respectivas senhas.
import java.util.List; // para usar listas de personagens.
import java.util.Map; // mapear usuários a senhas e personagens a usuários.
import java.util.Scanner; // para capturar entradas do usuário via console.

public class Login {
    private static final Scanner scanner = new Scanner(System.in); 
    private Map<String, String> usuarios; // armazena usuários e suas respectivas senhas
    private Map<String, List<Personagem>> personagensPorUsuario; // armazena personagens associados a cada usuário
    private String usuarioLogado; // armazena o nome do usuário logado

    public Login() {
        usuarios = new HashMap<>(); // inicializa o mapa de usuários
        personagensPorUsuario = new HashMap<>(); // inicializa o mapa de personagens por usuário
    }
    
    public boolean entrar() {
        System.out.print("\nDigite o seu usuário: ");
        String username = scanner.next();

        if (!usuarios.containsKey(username)) {
            System.out.println("\nUsuário não encontrado.\n");
            System.out.println("1. Tentar novamente");
            System.out.println("2. Cadastrar novo usuário\n");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    return entrar();
                case 2:
                    cadastrarUsuario();
                    return entrar();
                default:
                    System.out.println("Opção inválida.");
                    return false;
            }
        } 
        
        else {
            System.out.print("Digite a sua senha: ");
            String password = scanner.next();

            if (usuarios.get(username).equals(password)) {
                System.out.println("Login bem-sucedido! Bem-vindo, " + username + ".");
                usuarioLogado = username; // armazena o nome do usuário logado
                return true;
            } 
            
            else {
                System.out.println("Senha incorreta.");
                return false;
            }
        }
    }

    public void cadastrarUsuario() {
        System.out.print("Digite o nome do novo usuário: ");
        String novoUsuario = scanner.next();

        if (usuarios.containsKey(novoUsuario)) {
            System.out.println("Usuário já cadastrado.");
        } 
        
        else {
            System.out.print("Digite a senha: ");
            String novaSenha = scanner.next();
            usuarios.put(novoUsuario, novaSenha);
            personagensPorUsuario.put(novoUsuario, new ArrayList<>()); // inicializa a lista de personagens do usuário
            System.out.println("Usuário cadastrado com sucesso.");
        }
    }

    public void cadastrarPersonagem(String usuario) {
        if (!usuarios.containsKey(usuario)) {
            System.out.println("Usuário não encontrado.");
            return; // encerra o método se o usuário não for encontrado
        }

        List<Personagem> personagens = personagensPorUsuario.get(usuario); // obtém a lista de personagens do usuário

        if (personagens.size() >= 5) {
            System.out.println("Você já cadastrou o número máximo de 5 personagens.");
            return; // encerra o método se o número máximo for alcançado
        }

        System.out.print("Digite o nome do personagem: ");
        String nome = scanner.next(); // lê o nome do personagem

        System.out.println("Escolha a classe do personagem:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Ladino");
        System.out.println("3. Curandeiro");
        System.out.println("4. Mago");
        System.out.println("5. Bardo");
        System.out.println("6. Druida");
        System.out.print("Digite o número da classe: ");
        int classeEscolhida = scanner.nextInt(); // lê a escolha da classe

        String classe = "";
        switch (classeEscolhida) {
            case 1:
                classe = "Guerreiro";
                break;
            case 2:
                classe = "Ladino";
                break;
            case 3:
                classe = "Curandeiro";
                break;
            case 4:
                classe = "Mago";
                break;
            case 5:
                classe = "Bardo";
                break;
            case 6:
                classe = "Druida";
                break;
            default:
                System.out.println("Classe inválida.");
                return; // encerra o método se a classe for inválida
        }

        Personagem personagem = new Personagem(nome, classe); // cria o personagem com o nome e classe
        personagens.add(personagem); // adiciona o personagem à lista
        System.out.println("Personagem cadastrado com sucesso.");

        System.out.println("Atributos do personagem:");
        System.out.println("Nome: " + personagem.getNome());
        System.out.println("Classe: " + personagem.getClasse());
        System.out.println("Vida: " + personagem.getVida() + " (10 vida base + d10)" + personagem.getVida() + ")"); 
        System.out.println("Força: " + personagem.getForca() + " (d6)");
        System.out.println("Destreza: " + personagem.getDestreza() + " (d6)");
        System.out.println("Inteligência: " + personagem.getInteligencia() + " (d6)");
    }

    public void exibirPersonagens(String usuario) {
        if (!usuarios.containsKey(usuario)) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        List<Personagem> personagens = personagensPorUsuario.get(usuario);

        if (personagens.isEmpty()) {
            System.out.println("Nenhum personagem cadastrado para este usuário.");
        } 
        
        else {
            System.out.println("\nPersonagens cadastrados para " + usuario + ":");
            for (Personagem personagem : personagens) {
                System.out.println("\nNome: " + personagem.getNome());
                System.out.println("Classe: " + personagem.getClasse());
                System.out.println("Vida: " + personagem.getVida());
                System.out.println("Força: " + personagem.getForca());
                System.out.println("Destreza: " + personagem.getDestreza());
                System.out.println("Inteligência: " + personagem.getInteligencia());
                System.out.println(); // linha em branco entre os personagens
            }
        }
    }

    public void excluirPersonagem(String usuario) {
        if (!usuarios.containsKey(usuario)) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        List<Personagem> personagens = personagensPorUsuario.get(usuario);

        if (personagens.isEmpty()) {
            System.out.println("Nenhum personagem cadastrado para este usuário.");
            return;
        }

        System.out.println("Escolha o número do personagem a ser excluído:");
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println((i + 1) + ". " + personagens.get(i).getNome());
        }
        int numero = scanner.nextInt() - 1;

        if (numero >= 0 && numero < personagens.size()) {
            personagens.remove(numero);
            System.out.println("Personagem excluído com sucesso.");
        } 
        
        else {
            System.out.println("Número inválido.");
        }
    }

    public void excluirUsuario() {
        System.out.print("Digite o nome do usuário a ser excluído: ");
        String usuario = scanner.next();

        if (usuarios.containsKey(usuario)) {
            usuarios.remove(usuario);
            personagensPorUsuario.remove(usuario);
            System.out.println("Usuário excluído com sucesso.");
        } 
        
        else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public String getUsuarioLogado() {
        return usuarioLogado;
    }
}
